package vn.edu.fpt.timetabling.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

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

	@Autowired
	private SemesterService semesterService;

	@Autowired
	private ClassSemesterService classSemesterService;

	@Autowired
	private ClassCourseSemesterService classCourseSemesterService;

	@Autowired
	private TimetableService timetableService;

	@Autowired
	private RoomService roomService;

	@Override
	@Transactional
	public List<ClassSemester> getListClassesCoursesOfSemester(int semesterId,
			int classId) {
		// TODO Auto-generated method stub

		List<ClassSemester> classSemesters = classSemesterService
				.listClassSemestersBySemester(semesterId, true);
		Collections.sort(classSemesters, new Comparator<ClassSemester>() {
			@Override
			public int compare(ClassSemester o1, ClassSemester o2) {
				// TODO Auto-generated method stub
				return o1.getClassFPT().getClassId()
						- o2.getClassFPT().getClassId();
			}
		});

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
			if (classId == classFPT.getClassId()) {
				Set<ClassCourseSemester> classCourseSemesters = new LinkedHashSet<ClassCourseSemester>();
				for (ClassCourseSemester ccs : cs.getClassCourseSemesters()) {
					ClassCourseSemester newCcs = new ClassCourseSemester();
					CourseSemester courseSemesterToSet = new CourseSemester();
					courseSemesterToSet.setCourseSemesterId(ccs
							.getCourseSemester().getCourseSemesterId());

					Course course = new Course();
					course.setCourseId(ccs.getCourseSemester().getCourse()
							.getCourseId());
					course.setCode(ccs.getCourseSemester().getCourse()
							.getCode());
					course.setName(ccs.getCourseSemester().getCourse()
							.getName());
					courseSemesterToSet.setCourse(course);

					ClassSemester classSemesterToSet = new ClassSemester();
					classSemesterToSet.setClassSemesterId(classSemester
							.getClassSemesterId());
					classSemesterToSet.setClassFPT(classFPT);

					newCcs.setClassCourseSemesterId(ccs
							.getClassCourseSemesterId());
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
						classSemesterNew.setClassSemesterId(ccs
								.getClassSemester().getClassSemesterId());

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
			}

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
				course.setName(ccs.getCourseSemester().getCourse().getName());
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
	public boolean saveTimetables(List<ClassSemester> data) {
		List<Timetable> dataTimetable = new ArrayList<Timetable>();
		for (ClassSemester cs : data) {
			Iterator<ClassCourseSemester> i = cs.getClassCourseSemesters()
					.iterator();

			while (i.hasNext()) {
				dataTimetable.addAll(i.next().getTimetable());
			}
		}

		for (Timetable t : dataTimetable) {

			Timetable timetable = timetableService.getTimetableById(t
					.getTimeTableId());
			if (t.getRoom() != null) {
				timetable.setRoom(t.getRoom());
			} else {
				timetable.setRoom(null);
			}

			timetableService.updateTimetable(timetable);
		}
		return true;
	}

	public List<DataRoomArrangement> getDataRoomArrangement(int semesterId) {
		List<DataRoomArrangement> dras = new ArrayList<DataRoomArrangement>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		List<ClassSemester> classSemesters = classSemesterService
				.listClassSemestersBySemester(semesterId, true);

		for (ClassSemester classSemester : classSemesters) {
			DataRoomArrangement dra = new DataRoomArrangement();

			// Set ClassSemester for dra
			ClassSemester newCS = new ClassSemester();
			newCS.setClassSemesterId(classSemester.getClassSemesterId());

			ClassFPT newClass = new ClassFPT();
			newClass.setClassId(classSemester.getClassFPT().getClassId());
			newClass.setCode(classSemester.getClassFPT().getCode());
			newCS.setClassFPT(newClass);

			dra.setClassSemester(newCS);

			boolean checkDone = true;
			StringBuilder sb = new StringBuilder();
			int count = 0;
			int countSlotWasSet = 0;
			for (ClassCourseSemester classCourseSemester : classSemester
					.getClassCourseSemesters()) {
				ClassCourseSemester newCCS = classCourseSemesterService
						.getClassCourseSemesterById(
								classCourseSemester.getClassCourseSemesterId(),
								true, false);
				count += newCCS.getTimetable().size();
				countSlotWasSet += newCCS.getTimetable().size();
				for (Timetable timetable : newCCS.getTimetable()) {
					if (timetable.getRoom() == null) {
						checkDone = false;
						countSlotWasSet--;
						sb.append(sdf.format(timetable.getDate()) + "/"
								+ timetable.getSlot() + "; ");

					}
				}
			}

			if (checkDone) {
				dra.setNote("All Slots of this class was set room successful!");
			} else {
				dra.setNote("This Class still has " + (count - countSlotWasSet)
						+ " were not set room!");
			}

			dra.setSetRoomSuccessful(checkDone);
			dra.setNumberOfSlots(count);
			dra.setNumberOfSlots_WereSetSuccessful(countSlotWasSet);

			dras.add(dra);
		}

		return dras;
	}
}
