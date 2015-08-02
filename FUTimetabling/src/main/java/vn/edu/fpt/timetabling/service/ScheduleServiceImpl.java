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

import vn.edu.fpt.timetabling.auto.entities.DataCenter;
import vn.edu.fpt.timetabling.model.ClassCourseSemester;
import vn.edu.fpt.timetabling.model.ClassCourseSemesterMerge;
import vn.edu.fpt.timetabling.model.ClassSemester;
import vn.edu.fpt.timetabling.model.Course;
import vn.edu.fpt.timetabling.model.CourseSemester;
import vn.edu.fpt.timetabling.model.DataSchedule;
import vn.edu.fpt.timetabling.model.DaySlot;
import vn.edu.fpt.timetabling.model.Department;
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
	private ClassCourseSemesterMergeService classCourseSemesterMergeService;
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
	public void autoSchedule(int semesterId) {
		Semester semester = semesterService.getSemesterById(semesterId, true, true, false, false);
		List<Department> departments = departmentService.listDepartments();
		List<Room> rooms = roomService.listRooms(false);
		Set<ClassSemester> classSemesters = semester.getClassSemesters();
		Set<CourseSemester> courseSemesters = semester.getCourseSemesters();
		List<ClassCourseSemesterMerge> classCourseSemesterMerges = classCourseSemesterMergeService
				.listClassCourseSemesterMerges();
		List<String> departmentData = new ArrayList<>();
		List<String> roomData = new ArrayList<>();
		List<String> classData = new ArrayList<>();
		List<String> courseData = new ArrayList<>();
		List<String> classCourseData = new ArrayList<>();
		List<String> mergeClassData = new ArrayList<>();
		for (Department department : departments) {
			departmentData.add(department.getDepartmentId() + "|" + department.getCode());
		}
		for (Room room : rooms) {
			roomData.add(room.getRoomId() + "|" + room.getCode() + "|" + room.getBuilding().getCode() + "|"
					+ room.getCapacity());
		}
		for (ClassSemester classSemester : classSemesters) {
			classData.add(classSemester.getClassSemesterId() + "|" + classSemester.getClassFPT().getCode());
			Set<ClassCourseSemester> classCourseSemesters = classSemester.getClassCourseSemesters();
			for (ClassCourseSemester classCourseSemester : classCourseSemesters) {
				String data = classCourseSemester.getClassCourseSemesterId() + "|"
						+ classCourseSemester.getCourseSemester().getCourse().getCode() + "|"
						+ classCourseSemester.getClassSemester().getClassFPT().getCode() + "|";
				if (classCourseSemester.isSemesterLong()) {
					data += "3";
				} else {
					data += classCourseSemester.getBlockCondition();
				}
				classCourseData.add(data);
			}
		}
		for (CourseSemester courseSemester : courseSemesters) {
			Course course = courseSemester.getCourse();
			courseData.add(courseSemester.getCourseSemesterId() + "|" + course.getCode() + "|"
					+ course.getDepartment().getCode());
		}
		for (ClassCourseSemesterMerge classCourseSemesterMerge : classCourseSemesterMerges) {
			mergeClassData.add(
					classCourseSemesterMerge.getClassCourseSemester1().getCourseSemester().getCourse().getCode() + "|"
							+ classCourseSemesterMerge.getClassCourseSemester1().getClassSemester().getClassFPT()
									.getCode()
							+ "|" + classCourseSemesterMerge.getClassCourseSemester2().getClassSemester().getClassFPT()
									.getCode());
		}
		DataCenter dataCenter = new DataCenter();
		dataCenter.loadData_Department_v2(departmentData);
		dataCenter.loadData_Class_v2(classData);
		dataCenter.loadData_Course_v2(courseData);
		dataCenter.loadData_ClassCourse_v2(classCourseData);
		dataCenter.loadData_mergedCases(mergeClassData);
	}
}
