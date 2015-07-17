package vn.edu.fpt.timetabling.service;

import java.util.ArrayList;
import java.util.Iterator;
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
				CourseSemester courseSemesterToSet = new CourseSemester();
				courseSemesterToSet.setCourseSemesterId(ccs.getCourseSemester()
						.getCourseSemesterId());

				Course course = new Course();
				course.setCourseId(ccs.getCourseSemester().getCourse()
						.getCourseId());
				course.setCode(ccs.getCourseSemester().getCourse().getCode());
				courseSemesterToSet.setCourse(course);

				ClassSemester classSemesterToSet = new ClassSemester();
				classSemesterToSet.setClassSemesterId(classSemester
						.getClassSemesterId());
				classSemesterToSet.setClassFPT(classFPT);

				newCcs.setClassCourseSemesterId(ccs.getClassCourseSemesterId());
				newCcs.setCourseSemester(courseSemesterToSet);
				newCcs.setClassSemester(classSemesterToSet);

				Set<Timetable> timetables = timetableService
						.listTimetablesByClassCourse(ccs
								.getClassCourseSemesterId());
				Set<Timetable> timetablesToSet = new LinkedHashSet<Timetable>();
				for (Timetable timetable : timetables) {
					Timetable t = new Timetable();
					t.setTimeTableId(timetable.getTimeTableId());
					t.setDate(timetable.getDate());
					t.setSlot(timetable.getSlot());

					ClassSemester classSemesterNew = new ClassSemester();
					classSemesterNew.setClassSemesterId(ccs.getClassSemester()
							.getClassSemesterId());

					if (timetable.getRoom() != null) {
						Room r = new Room();
						r.setRoomId(timetable.getRoom().getRoomId());
						r.setCode(timetable.getRoom().getCode());
						t.setRoom(r);
					}
					timetablesToSet.add(t);
				}

				newCcs.setTimetable(timetablesToSet);
				classCourseSemesters.add(newCcs);
			}

			classSemester.setClassCourseSemesters(classCourseSemesters);
			JSONccs.add(classSemester);
		}
		return JSONccs;
	}

	@Override
	@Transactional
	public List<Room> getListRoomsOfSemester(int semesterId) {
		// TODO Auto-generated method stub

		List<ClassSemester> classSemesters = classSemesterService
				.listClassSemestersBySemester(semesterId, true);

		// Get all timetable in semester
		List<Timetable> timetables = new ArrayList<Timetable>();
		for (ClassSemester classSemester : classSemesters) {
			if (classSemester.getClassCourseSemesters() != null
					&& !classSemester.getClassCourseSemesters().isEmpty()) {
				timetables.addAll(timetableService
						.listTimetablesByClassCourseSemesters(classSemester
								.getClassCourseSemesters()));
			}
		}

		// get List of Room
		List<Room> rooms = new ArrayList<Room>();
		for (Room room : roomService.listRooms(false)) {
			Room r = new Room();
			r.setRoomId(room.getRoomId());
			r.setCode(room.getCode());
			r.setCapacity(room.getCapacity());
			if (room.getCourses() != null && !room.getCourses().isEmpty()) {
				r.setCourses(room.getCourses());
			}

			List<Timetable> list = new ArrayList<Timetable>();
			for (Timetable timetable : room.getTimetables()) {
				Timetable t = new Timetable();
				t.setTimeTableId(timetable.getTimeTableId());
				t.setDate(timetable.getDate());
				t.setSlot(timetable.getSlot());

				ClassCourseSemester classCourseSemester = new ClassCourseSemester();
				ClassCourseSemester ccs = timetable.getClassCourseSemester();
				classCourseSemester.setClassCourseSemesterId(ccs
						.getClassCourseSemesterId());

				ClassSemester classSemester = new ClassSemester();
				classSemester.setClassSemesterId(ccs.getClassSemester()
						.getClassSemesterId());

				ClassFPT classFPT = new ClassFPT();
				classFPT.setClassId(ccs.getClassSemester().getClassFPT()
						.getClassId());
				classFPT.setCode(ccs.getClassSemester().getClassFPT().getCode());
				classSemester.setClassFPT(classFPT);

				CourseSemester courseSemester = new CourseSemester();
				courseSemester.setCourseSemesterId(ccs.getCourseSemester()
						.getCourseSemesterId());

				Course course = new Course();
				course.setCourseId(ccs.getCourseSemester().getCourse()
						.getCourseId());
				course.setCode(ccs.getCourseSemester().getCourse().getCode());
				courseSemester.setCourse(course);

				classCourseSemester.setClassSemester(classSemester);
				classCourseSemester.setCourseSemester(courseSemester);

				t.setClassCourseSemester(classCourseSemester);

				list.add(t);
			}
			if (list != null && !list.isEmpty()) {
				Set<Timetable> ts = new LinkedHashSet<Timetable>();
				ts.addAll(list);
				r.setTimetables(ts);
			}
			rooms.add(r);
		}
		return rooms;
	}
	
	@Override
	@Transactional
	public boolean saveTimetables(List<ClassSemester> data, List<ClassSemester> prev) {
		List<Timetable> dataTimetable = new ArrayList<Timetable>();
		List<Timetable> prevTimetable = new ArrayList<Timetable>();
		
		for (ClassSemester cs: data) {
			Iterator<ClassCourseSemester> i = cs.getClassCourseSemesters().iterator();
			
			while(i.hasNext()) {
				dataTimetable.addAll(i.next().getTimetable());
			}
		}
		
		for(Timetable t : dataTimetable) {
			if(t.getRoom() != null) {
				Timetable timetable = timetableService.getTimetableById(t.getTimeTableId());
				timetable.setRoom(t.getRoom());
				timetableService.updateTimetable(timetable);
			}
		}
		return true;
	}
}
