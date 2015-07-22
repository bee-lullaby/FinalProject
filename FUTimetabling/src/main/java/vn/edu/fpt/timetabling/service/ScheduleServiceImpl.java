package vn.edu.fpt.timetabling.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.fpt.timetabling.auto.algorithms.AssignRoom;
import vn.edu.fpt.timetabling.auto.algorithms.AssignTeacher2;
import vn.edu.fpt.timetabling.auto.algorithms.MainApp;
import vn.edu.fpt.timetabling.auto.algorithms.TimeTableAllClass;
import vn.edu.fpt.timetabling.auto.entities.DataCenter;
import vn.edu.fpt.timetabling.auto.entities.SingleSolution;
import vn.edu.fpt.timetabling.model.ClassCourseSemester;
import vn.edu.fpt.timetabling.model.ClassSemester;
import vn.edu.fpt.timetabling.model.CourseSemester;
import vn.edu.fpt.timetabling.model.DataSchedule;
import vn.edu.fpt.timetabling.model.DaySlot;
import vn.edu.fpt.timetabling.model.Room;
import vn.edu.fpt.timetabling.model.Semester;
import vn.edu.fpt.timetabling.model.TeacherCourseSemester;
import vn.edu.fpt.timetabling.model.Timetable;
import vn.edu.fpt.timetabling.utils.TimetableUtils;

@Service
@Transactional(rollbackFor = Exception.class)
public class ScheduleServiceImpl implements ScheduleService {
	@Autowired
	private SemesterService semesterService;
	@Autowired
	private ClassSemesterService classSemesterService;
	@Autowired
	private CourseSemesterService courseSemesterService;
	@Autowired
	private ClassCourseSemesterService classCourseSemesterService;
	@Autowired
	private TimetableService timetableService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private RoomService roomService;

	@Override
	public List<ClassSemester> listClassBySemester(int semesterId) {
		List<ClassSemester> classSemesters = new ArrayList<ClassSemester>();
		classSemesters
				.addAll(semesterService.getSemesterById(semesterId, true, false, false, false).getClassSemesters());
		return classSemesters;
	}

	@Override
	public List<ClassCourseSemester> listClassCourseSemesterByClassSemester(int classId, int semesterId) {
		ClassSemester classSemester = classSemesterService.getClassSemesterByClassSemester(semesterId, classId, true);
		List<ClassCourseSemester> classCourseSemesters = new ArrayList<ClassCourseSemester>();
		classCourseSemesters.addAll(classSemester.getClassCourseSemesters());
		return classCourseSemesters;
	}

	@Override
	public List<CourseSemester> listCourseSemesterByClass(int classId, int semesterId) {
		ClassSemester classSemester = classSemesterService.getClassSemesterByClassSemester(semesterId, classId, true);
		Set<ClassCourseSemester> classCourseSemesters = classSemester.getClassCourseSemesters();
		List<CourseSemester> courseSemesters = new ArrayList<CourseSemester>();
		for (ClassCourseSemester classCourseSemester : classCourseSemesters) {
			courseSemesters.add(courseSemesterService.getCourseSemesterById(
					classCourseSemester.getCourseSemester().getCourseSemesterId(), true, true, false));
		}
		return courseSemesters;
	}

	@Override
	public List<TeacherCourseSemester> listTeacherByCourseSemester(int classId, int semesterId) {
		List<CourseSemester> courseSemesters = listCourseSemesterByClass(classId, semesterId);
		List<TeacherCourseSemester> teacherCourseSemesters = new ArrayList<TeacherCourseSemester>();
		for (CourseSemester courseSemester : courseSemesters) {
			teacherCourseSemesters.addAll(courseSemester.getTeacherCourseSemesters());
		}
		return teacherCourseSemesters;
	}

	@Override
	public List<DaySlot> getListDaySlot(int semesterId, int classId, int week) {
		Semester semester = semesterService.getSemesterById(semesterId, false, false, false, false);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = semester.getStartDate();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, cal.get(Calendar.DATE) - 1 + 7 * (week - 1));

		// Get List Class Course Semester based on Class//
		List<ClassCourseSemester> classCourseSemesters = listClassCourseSemesterByClassSemester(classId, semesterId);

		// Get List Timetable based on Class //
		List<Timetable> timetableBasedClass = timetableService.listTimetablesByCCSs(classCourseSemesters);

		// Create List Course Semester of Class //
		List<CourseSemester> courseSemesters = new ArrayList<CourseSemester>();
		for (ClassCourseSemester classCourseSemester : classCourseSemesters) {
			courseSemesters.add(courseSemesterService.getCourseSemesterById(
					classCourseSemester.getCourseSemester().getCourseSemesterId(), true, true, false));
		}

		// Create Map between course and timetable //
		HashMap<CourseSemester, List<Timetable>> mapCourseTimetable = new HashMap<CourseSemester, List<Timetable>>();
		for (CourseSemester courseSemester : courseSemesters) {
			List<ClassCourseSemester> list = new ArrayList<ClassCourseSemester>();
			list.addAll(courseSemester.getClassCourseSemesters());
			mapCourseTimetable.put(courseSemester, timetableService.listTimetablesByCCSs(list));
		}

		// List All Timetable
		List<Timetable> allTimetable = timetableService.listTimetables();
		List<Room> allRooms = roomService.listRooms(true);

		// Map date with number of Classes
		HashMap<String, Set<Integer>> mapDateAndNumberOfClasses = new HashMap<String, Set<Integer>>();
		for (Timetable t : allTimetable) {
			String key = sdf.format(t.getDate()) + " " + t.getSlot();
			if (mapDateAndNumberOfClasses.containsKey(key)) {
				mapDateAndNumberOfClasses.get(key)
						.add(t.getClassCourseSemester().getClassSemester().getClassSemesterId());
			} else {
				Set<Integer> setClasses = new LinkedHashSet<Integer>();
				setClasses.add(t.getClassCourseSemester().getClassSemester().getClassSemesterId());
				mapDateAndNumberOfClasses.put(key, setClasses);

			}

		}

		// Map teacher with course of class
		// ...//

		List<DaySlot> list = new ArrayList<DaySlot>();
		for (int i = 0; i < 7; i++) {
			for (int j = 1; j < 7; j++) {
				DaySlot ds = new DaySlot();
				HashMap<String, DataSchedule> dataSchedule = new HashMap<String, DataSchedule>();

				// Set Day And Slot
				ds.setDate(sdf.format(cal.getTime()));
				ds.setSlot(j);

				Object o = TimetableUtils.containsTimetable(timetableBasedClass, cal.getTime(), j);
				if (o != null) {
					Timetable t = (Timetable) o;
					ds.setSetCourseSlot(t.getClassCourseSemester().getClassCourseSemesterId());
				} else {
					ds.setSetCourseSlot(-1);
				}
				for (CourseSemester cs : courseSemesters) {
					DataSchedule dataS = new DataSchedule();
					dataS.setNumOfClasses(cs.getClassCourseSemesters().size());
					dataS.setLearnCourseInSlot(
							TimetableUtils.findNumberSameDaySlot(mapCourseTimetable.get(cs), cal.getTime(), j));

					// Set Rooms
					dataS.setTotalRooms(allRooms.size());

					// Set Remains Room for this day and this slot.
					String key = ds.getDate() + " " + ds.getSlot();
					if (mapDateAndNumberOfClasses.containsKey(key)) {
						dataS.setClassesInSlot(mapDateAndNumberOfClasses.get(key).size());
					} else {
						dataS.setClassesInSlot(0);
					}

					for (ClassCourseSemester ccs : classCourseSemesters) {
						if (ccs.getCourseSemester().compareTo(cs) == 0) {
							if (ccs.getTimetable() != null) {
								dataS.setRemainSlots(ccs.getCourseSemester().getSlots() - ccs.getTimetable().size());
							} else {
								dataS.setRemainSlots(ccs.getCourseSemester().getSlots());
							}
						}
					}

					dataS.setNumOfTeachers(cs.getTeacherCourseSemesters().size());
					// dataS.setNumOfTeachers(5);
					dataSchedule.put(cs.getCourse().getCode(), dataS);
				}
				ds.setDataSchedule(dataSchedule);
				list.add(ds);
			}
			cal.set(Calendar.DATE, cal.get(Calendar.DATE) + 1);
		}
		return list;
	}

	@Override
	public boolean saveTimetable(List<DaySlot> daySlots, List<DaySlot> prevDaySlots) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 0; i < daySlots.size(); i++) {
			DaySlot dayslot = daySlots.get(i);
			DaySlot prevDaySlot = prevDaySlots.get(i);
			if (prevDaySlot.getSetCourseSlot() != dayslot.getSetCourseSlot()) {
				Timetable timetable;
				Date date;
				date = sdf.parse(dayslot.getDate());
				if (prevDaySlot.getSetCourseSlot() != -1) {
					timetable = timetableService.getTimetableByDateSlotClassCourse(date, prevDaySlot.getSlot(),
							prevDaySlot.getSetCourseSlot());
					if (dayslot.getSetCourseSlot() != -1) {
						timetable.setClassCourseSemester(classCourseSemesterService
								.getClassCourseSemesterById(dayslot.getSetCourseSlot(), false, false));
						timetableService.updateTimetable(timetable);
					} else {
						timetableService.deleteTimetable(timetable.getTimeTableId());
					}
				} else {
					timetable = new Timetable();
					timetable.setClassCourseSemester(classCourseSemesterService
							.getClassCourseSemesterById(dayslot.getSetCourseSlot(), false, false));
					timetable.setDate(date);
					timetable.setSlot(dayslot.getSlot());
					timetableService.addTimetable(timetable);
				}
			}
		}
		return true;
	}

	@Override
	public boolean generateFromPreviousWeek(int semesterId, int classId, int week) {
		if (week == 1) {
			return true;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Semester semester = semesterService.getSemesterById(semesterId, false, false, false, false);
		Date startDate = semester.getStartDate();
		Calendar startWeek = Calendar.getInstance();
		Calendar endWeek = Calendar.getInstance();
		startWeek.setTime(startDate);
		startWeek.set(Calendar.DATE, startWeek.get(Calendar.DATE) + 7 * (week - 2));
		endWeek.setTime(startWeek.getTime());
		endWeek.set(Calendar.DATE, endWeek.get(Calendar.DATE) + 6);
		ClassSemester classSemester = classSemesterService.getClassSemesterByClassSemester(semesterId, classId, true);
		List<Timetable> timetableOfClassInPrevWeek = timetableService.listTimetablesByClassCourseSemestersInWeek(
				classSemester.getClassCourseSemesters(), startWeek.getTime(), endWeek.getTime());
		startWeek.set(Calendar.DATE, startWeek.get(Calendar.DATE) + 7);
		endWeek.set(Calendar.DATE, endWeek.get(Calendar.DATE) + 7);
		List<Timetable> timetableOfClassInCurrentWeek = timetableService.listTimetablesByClassCourseSemestersInWeek(
				classSemester.getClassCourseSemesters(), startWeek.getTime(), endWeek.getTime());
		HashMap<String, Timetable> curWeek = new HashMap<String, Timetable>();
		for (Timetable timetable : timetableOfClassInCurrentWeek) {
			String key = sdf.format(timetable.getDate()) + "-" + timetable.getSlot();
			curWeek.put(key, timetable);
		}
		for (Timetable timetable : timetableOfClassInPrevWeek) {
			Timetable t;
			Calendar c = Calendar.getInstance();
			c.setTime(timetable.getDate());
			c.set(Calendar.DATE, c.get(Calendar.DATE) + 7);
			String key = sdf.format(c.getTime()) + "-" + timetable.getSlot();
			if (curWeek.containsKey(key)) {
				t = curWeek.get(key);
				t.setClassCourseSemester(timetable.getClassCourseSemester());
				t.setTeacherSemester(timetable.getTeacherSemester());
				t.setRoom(timetable.getRoom());
				timetableService.updateTimetable(t);
			} else {
				t = new Timetable();
				t.setDate(c.getTime());
				t.setSlot(timetable.getSlot());
				t.setRoom(timetable.getRoom());
				t.setClassCourseSemester(timetable.getClassCourseSemester());
				t.setTeacherSemester(timetable.getTeacherSemester());
				timetableService.addTimetable(t);
			}
		}
		return true;
	}

	@Override
	public void autoSchedule() {
		DataCenter DA;
		TimeTableAllClass TA;

		AssignTeacher2 teacherAssigner2;
		AssignRoom roomAssigner;
		long startTime = System.currentTimeMillis();
		// =================================================================
		MainApp mainApp = new MainApp();
		DA = new DataCenter();
		TA = new TimeTableAllClass();
		TA.DA = DA;
		teacherAssigner2 = new AssignTeacher2();
		teacherAssigner2.DA = DA;
		roomAssigner = new AssignRoom();
		roomAssigner.DA = DA;

		String fn_data_course_class = "data/data_course_class_merged.txt";
		String fn_data_teacher = "data/data_teacher.txt";
		String fn_data_room_building = "data/data_room_building.txt";
		String fn_data_mergedCases = "data/data_mergedCases.txt";
		String fn_classTimeTableSolutions = "data/data_solutionWarehouses.dat";
		String fn_conflictMatrix = "data/data_conflictMatrix.txt";
		String fn_beingUsedTimeTable = "data/data_beingusedTT.dat";
		String fn_allTeacherTTB = "teacherTimeTable.html";
		String fn_allClass_TimetableFull = "allClassTimeTable.html";

		DA.loadData_Course_Class(fn_data_course_class); // for all phases
		DA.loadData_Teacher_UsingCode(fn_data_teacher); // for phase 3
		DA.loadData_Room_Building(fn_data_room_building); // for phase 4
		DA.loadData_mergedCases(fn_data_mergedCases); // for phase 1

		// ---------------------------------------------------------------------------
		// PHASE 1:
		long startTime_phase1 = System.currentTimeMillis();
		// //TA.makeSolutionWarehouse(fn_classTimeTableSolutions);
		long estimatedTime_phase1 = System.currentTimeMillis() - startTime_phase1;

		// ---------------------------------------------------------------------------
		// PHASE 2:
		long startTime_phase2 = System.currentTimeMillis();
		TA.findOptimalTimeTable_v2_2(fn_classTimeTableSolutions);
		TA.savebeingUsedTimeTable(fn_beingUsedTimeTable);
		TA.writeMatrix2File(fn_conflictMatrix, TA.beingUsedTimeTable);

		SingleSolution[] sol = TA.loadbeingUsedTimeTable(fn_beingUsedTimeTable);
		// TA.printTimeTableAllClass(fn_allClassTTB,sol);

		long estimatedTime_phase2 = System.currentTimeMillis() - startTime_phase2;

		// ---------------------------------------------------------------------------
		// PHASE 3:
		long startTime_phase3 = System.currentTimeMillis();
		teacherAssigner2.assignTeacherUsingLS(fn_conflictMatrix);
		// teacherAssigner2.printAllTeacherHTML(fn_allTeacherTTB2, sol);

		long estimatedTime_phase3 = System.currentTimeMillis() - startTime_phase3;

		// ---------------------------------------------------------------------------
		// PHASE 4:
		long startTime_phase4 = System.currentTimeMillis();
		roomAssigner.assignRoomUsingFor(fn_beingUsedTimeTable);
		System.out.println("\nRoom demand of every slot:");
		TA.calRoomDemandEverySlot(sol);
		System.out.println();
		long estimatedTime_phase4 = System.currentTimeMillis() - startTime_phase4;

		// ==================================================================
		mainApp.printTimeTableHTML_AllClass(DA, fn_allClass_TimetableFull, sol);
		mainApp.printTimeTable_AllTeacher(DA, fn_allTeacherTTB, sol);
		mainApp.PoiWriteExcelFile(DA, TA, "timetable.xls", sol);
		// ==================================================================
		long estimatedTime = System.currentTimeMillis() - startTime;
		System.out.println("\nTime-consuming phase 1: " + (double) estimatedTime_phase1 / 1000 + " secs");
		System.out.println("Time-consuming phase 2: " + (double) estimatedTime_phase2 / 1000 + " secs");
		System.out.println("Time-consuming phase 3: " + (double) estimatedTime_phase3 / 1000 + " secs");
		System.out.println("Time-consuming phase 4: " + (double) estimatedTime_phase4 / 1000 + " secs");
		System.out.println("Time-consuming all phases and loading data: " + (double) estimatedTime / 1000 + " secs");
	}
}
