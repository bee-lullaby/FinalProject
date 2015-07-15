package vn.edu.fpt.timetabling.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.edu.fpt.timetabling.model.ClassCourseSemester;
import vn.edu.fpt.timetabling.model.ClassFPT;
import vn.edu.fpt.timetabling.model.ClassSemester;
import vn.edu.fpt.timetabling.model.Course;
import vn.edu.fpt.timetabling.model.CourseSemester;
import vn.edu.fpt.timetabling.model.DataRoomArrangement;
import vn.edu.fpt.timetabling.model.Room;
import vn.edu.fpt.timetabling.model.Semester;
import vn.edu.fpt.timetabling.model.Timetable;

@Service
public class RoomArrangementServiceImpl implements RoomArrangementService {

	private static final Logger logger = LoggerFactory
			.getLogger(RoomArrangementServiceImpl.class);

	@Autowired
	private SemesterService semesterService;

	@Autowired
	private ClassSemesterService classSemesterService;

	@Autowired
	private TimetableService timetableService;

	@Autowired
	private RoomService roomService;

	@Override
	@Transactional
	public List<ClassSemester> getListClassesCoursesOfSemester(int semesterId) {
		// TODO Auto-generated method stub

		List<ClassSemester> classSemesters = classSemesterService
				.listClassSemestersBySemester(semesterId, true);
		List<ClassSemester> JSONccs = new ArrayList<ClassSemester>();

		for (ClassSemester cs : classSemesters) {
			ClassSemester classSemester = new ClassSemester();
			classSemester.setClassSemesterId(cs.getClassSemesterId());

			ClassFPT classFPT = new ClassFPT();
			classFPT.setClassId(cs.getClassFPT().getClassId());
			classFPT.setCode(cs.getClassFPT().getCode());
			classSemester.setClassFPT(classFPT);

			Semester semester = new Semester();
			semester.setSemesterId(cs.getSemester().getSemesterId());
			semester.setCode(cs.getSemester().getCode());
			classSemester.setSemester(semester);

			Set<ClassCourseSemester> classCourseSemesters = new LinkedHashSet<ClassCourseSemester>();
			for (ClassCourseSemester ccs : cs.getClassCourseSemesters()) {
				ClassCourseSemester newCcs = new ClassCourseSemester();
				CourseSemester courseSemester = new CourseSemester();
				courseSemester.setCourseSemesterId(ccs.getCourseSemester()
						.getCourseSemesterId());

				Course course = new Course();
				course.setCourseId(ccs.getCourseSemester().getCourse()
						.getCourseId());
				course.setCode(ccs.getCourseSemester().getCourse().getCode());
				courseSemester.setCourse(course);

				newCcs.setClassCourseSemesterId(ccs.getClassCourseSemesterId());
				newCcs.setCourseSemester(courseSemester);
				classCourseSemesters.add(newCcs);

			}

			classSemester.setClassCourseSemesters(classCourseSemesters);
			JSONccs.add(classSemester);
		}
		return JSONccs;
	}

	@Override
	@Transactional
	public HashMap<Room, List<Timetable>> getListRoomsOfSemester(int semesterId) {
		// TODO Auto-generated method stub

		HashMap<Room, List<Timetable>> mapRoomTimetable = new HashMap<Room, List<Timetable>>();
		List<ClassSemester> classSemesters = classSemesterService
				.listClassSemestersBySemester(semesterId, true);

		List<Timetable> timetables = new ArrayList<Timetable>();
		for (ClassSemester classSemester : classSemesters) {
			if(classSemester.getClassCourseSemesters() != null && !classSemester.getClassCourseSemesters().isEmpty()) {
				timetables.addAll(timetableService
					.listTimetablesByClassCourseSemesters(classSemester.getClassCourseSemesters()));
			}
		}

		for (Room room : roomService.listRooms(false)) {
			Room r = new Room();
			r.setRoomId(room.getRoomId());
			r.setCode(room.getCode());
			if (room.getClasses() != null && !room.getClasses().isEmpty()) {
				r.setClasses(room.getClasses());
			}
			List<Timetable> list = new ArrayList<Timetable>();
			mapRoomTimetable.put(r, list);
		}

		for (Timetable timetable : timetables) {

			if (timetable.getRoom() != null) {

				Timetable t = new Timetable();
				t.setTimeTableId(timetable.getTimeTableId());
				t.setDate(timetable.getDate());
				t.setSlot(timetable.getSlot());
				
				ClassCourseSemester classCourseSemester = new ClassCourseSemester();
				ClassCourseSemester ccs = timetable.getClassCourseSemester();
				classCourseSemester.setClassCourseSemesterId(ccs.getClassCourseSemesterId());
				
				ClassSemester classSemester = new ClassSemester();
				classSemester.setClassSemesterId(ccs.getClassSemester().getClassSemesterId());
				
				ClassFPT classFPT = new ClassFPT();
				classFPT.setClassId(ccs.getClassSemester().getClassFPT().getClassId());
				classFPT.setCode(ccs.getClassSemester().getClassFPT().getCode());
				classSemester.setClassFPT(classFPT);
				
				CourseSemester courseSemester = new CourseSemester();
				courseSemester.setCourseSemesterId(ccs.getCourseSemester().getCourseSemesterId());
				
				Course course = new Course();
				course.setCourseId(ccs.getCourseSemester().getCourse().getCourseId());
				course.setCode(ccs.getCourseSemester().getCourse().getCode());
				courseSemester.setCourse(course);
			
				classCourseSemester.setClassSemester(classSemester);
				classCourseSemester.setCourseSemester(courseSemester);
				
				t.setClassCourseSemester(classCourseSemester);
				
				Room r = new Room();
				r.setRoomId(timetable.getRoom().getRoomId());
				r.setCode(timetable.getRoom().getCode());
				if (timetable.getRoom().getClasses() != null && !timetable.getRoom().getClasses().isEmpty()) {
					r.setClasses(timetable.getRoom().getClasses());
				}
				
				mapRoomTimetable.get(r).add(t);
			}
		}

		return mapRoomTimetable;
	}

	@Override
	@Transactional
	public List<DataRoomArrangement> getDataRoomArrangement(int semesterId,
			int classId) {
		// TODO Auto-generated method stub
		List<DataRoomArrangement> dataRoomArrangements = new ArrayList<DataRoomArrangement>();
		// List<ClassSemester> classSemestersConflict;
		//
		// ClassSemester classSemester =
		// classSemesterService.getClassSemesterByClassSemester(semesterId,
		// classId, true);
		// List<Timetable> allTimetable = timetableService.listTimetables();
		//
		// List<ClassCourseSemester> classCourseSemesterOfClass = new
		// ArrayList<ClassCourseSemester>();
		// classCourseSemesterOfClass.addAll(classSemester.getClassCourseSemesters());
		// List<Timetable> timetableOfClass =
		// timetableService.listTimetablesByCCSId(classCourseSemesterOfClass);
		return dataRoomArrangements;

	}

}
