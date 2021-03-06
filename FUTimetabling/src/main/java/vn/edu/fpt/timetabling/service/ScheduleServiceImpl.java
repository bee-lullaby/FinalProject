package vn.edu.fpt.timetabling.service;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.fpt.timetabling.auto.algorithms.AssignRoom;
import vn.edu.fpt.timetabling.auto.algorithms.AssignTeacher;
import vn.edu.fpt.timetabling.auto.algorithms.TimeTableAllClass;
import vn.edu.fpt.timetabling.auto.entities.ClassCourse;
import vn.edu.fpt.timetabling.auto.entities.DataCenter;
import vn.edu.fpt.timetabling.auto.entities.SingleSolution;
import vn.edu.fpt.timetabling.model.Building;
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
import vn.edu.fpt.timetabling.model.Teacher;
import vn.edu.fpt.timetabling.model.TeacherCourseSemester;
import vn.edu.fpt.timetabling.model.TeacherSemester;
import vn.edu.fpt.timetabling.model.Timetable;
import vn.edu.fpt.timetabling.utils.Const;
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
	@Autowired
	private TeacherSemesterService teacherSemesterService;
	@Autowired
	private BuildingService buildingService;

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

		SimpleDateFormat sdf = new SimpleDateFormat(Const.DATE);
		Date date = semester.getStartDate();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, cal.get(Calendar.DATE) - 1 + 7 * (week - 1));

		// Get List Class Course Semester based on Class//
		List<ClassCourseSemester> classCourseSemesters = listClassCourseSemesterByClassSemester(classId, semesterId);

		// Get List Timetable based on Class //
		List<Timetable> timetableBasedClass = timetableService
				.listTimetablesByClassCourseSemesters(classCourseSemesters);

		// Create List Course Semester of Class //
		List<CourseSemester> courseSemesters = new ArrayList<CourseSemester>();
		for (ClassCourseSemester classCourseSemester : classCourseSemesters) {
			courseSemesters.add(courseSemesterService.getCourseSemesterById(
					classCourseSemester.getCourseSemester().getCourseSemesterId(), true, true, false));
		}

		// Create Map between course and timetable //
		HashMap<CourseSemester, List<Timetable>> mapCourseTimetable = new HashMap<CourseSemester, List<Timetable>>();
		HashMap<String, Set<Integer>> mMergeClass = classCourseSemesterMergeService
				.getMapCourseWithMergeClassInSemester(semesterId);
		for (CourseSemester courseSemester : courseSemesters) {
			List<ClassCourseSemester> list = new ArrayList<ClassCourseSemester>();
			list.addAll(courseSemester.getClassCourseSemesters());
			List<Integer> checkList = new ArrayList<Integer>();
			for (String key : mMergeClass.keySet()) {
				if (key.contains(Integer.toString(courseSemester.getCourseSemesterId()))) {
					int count = 0;
					for (int ccsId : mMergeClass.get(key)) {
						if (count == 0) {
							count++;
							continue;
						} else {
							checkList.add(ccsId);
						}
					}
				}
			}
			for (int i = 0; i < list.size(); i++) {
				if (checkList.contains(list.get(i).getClassCourseSemesterId())) {
					list.remove(i--);
				}
			}
			mapCourseTimetable.put(courseSemester, timetableService.listTimetablesByClassCourseSemesters(list));
		}

		// List All Timetable
		List<Timetable> allTimetable = timetableService.listTimetables();

		// List Rooms
		List<Room> listNormalRooms = roomService.listNormalRooms();

		// Map of Special Course
		Map<Course, Map<String, Room>> mSpecialCourseRoom = roomService.getCourseRoomMap();
		Map<Course, Set<Timetable>> mSpecialCourseTimetable = roomService
				.getSpecialCourseMapTimetableOfItsClasses(semesterId);

		// Get Set Merge Classes will not get timetable
		Set<Integer> sCCSIdMergeClasses = new LinkedHashSet<Integer>();
		for (String key : mMergeClass.keySet()) {
			int count = 0;
			for (int id : mMergeClass.get(key)) {
				// dont add first value
				if (count == 0) {
					count++;
					continue;
				} else {
					sCCSIdMergeClasses.add(id);
				}
			}
		}

		// Map date with number of Normal Classes
		HashMap<String, Set<Integer>> mapDateAndNumberOfNormalClassesCourses = new HashMap<String, Set<Integer>>();
		for (Timetable t : allTimetable) {
			if (sCCSIdMergeClasses.contains(t.getClassCourseSemester().getClassCourseSemesterId())) {
				continue;
			}
			String key = sdf.format(t.getDate()) + " " + t.getSlot();
			if (!mSpecialCourseRoom.containsKey(t.getClassCourseSemester().getCourseSemester().getCourse())) {
				if (mapDateAndNumberOfNormalClassesCourses.containsKey(key)) {
					mapDateAndNumberOfNormalClassesCourses.get(key)
							.add(t.getClassCourseSemester().getClassSemester().getClassSemesterId());
				} else {
					Set<Integer> setClasses = new LinkedHashSet<Integer>();
					setClasses.add(t.getClassCourseSemester().getClassSemester().getClassSemesterId());
					mapDateAndNumberOfNormalClassesCourses.put(key, setClasses);
				}
			}
		}

		// Map date with number of Special Courses

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
					if (mSpecialCourseRoom.containsKey(cs.getCourse())) {
						dataS.setTotalRooms(mSpecialCourseRoom.get(cs.getCourse()).size());
					} else {
						dataS.setTotalRooms(listNormalRooms.size());
					}

					// Set Remains Room for this day and this slot.
					String key = ds.getDate() + " " + ds.getSlot();
					if (mSpecialCourseRoom.containsKey(cs.getCourse())) {
						List<Timetable> newList = new ArrayList<Timetable>();
						newList.addAll(mSpecialCourseTimetable.get(cs.getCourse()));
						dataS.setClassesInSlot(TimetableUtils.findNumberSameDaySlot(newList, cal.getTime(), j));
					} else if (mapDateAndNumberOfNormalClassesCourses.containsKey(key)) {
						dataS.setClassesInSlot(mapDateAndNumberOfNormalClassesCourses.get(key).size());
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
	public HashMap<Integer, Set<Timetable>> getMapCourseTimetable(int semesterId, int classId) {
		HashMap<String, Set<Integer>> mMergeClass = classCourseSemesterMergeService
				.getMapCourseWithMergeClassInSemester(semesterId);
		ClassSemester classSemester = classSemesterService.getClassSemesterByClassSemester(semesterId, classId, true);

		// m class course Semester with Timetable of Merge Class
		HashMap<Integer, Set<Timetable>> result = new HashMap<Integer, Set<Timetable>>();

		for (ClassCourseSemester ccs : classSemester.getClassCourseSemesters()) {
			int ccsId = ccs.getClassCourseSemesterId();
			for (String key : mMergeClass.keySet()) {
				if (mMergeClass.get(key).contains(ccsId)) {
					result.put(ccs.getClassCourseSemesterId(), new LinkedHashSet<Timetable>());
					for (int ccsId2 : mMergeClass.get(key)) {
						if (ccsId2 != ccsId) {
							ClassCourseSemester ccs2 = classCourseSemesterService.getClassCourseSemesterById(ccsId2,
									false, false);
							ClassSemester cs = classSemesterService
									.getClassSemesterById(ccs2.getClassSemester().getClassSemesterId(), true);
							for (ClassCourseSemester ccs3 : cs.getClassCourseSemesters()) {
								for (Timetable t : ccs3.getTimetable()) {
									Timetable newTimetable = new Timetable();
									newTimetable.setClassCourseSemester(
											classCourseSemesterService.createNewCCS(t.getClassCourseSemester()));
									newTimetable.setDate(t.getDate());
									if (t.getRoom() != null) {
										Room r = new Room();
										r.setRoomId(t.getRoom().getRoomId());
										r.setCode(t.getRoom().getCode());
										r.setCourses(t.getRoom().getCourses());
										newTimetable.setRoom(r);
									}

									newTimetable.setSlot(t.getSlot());

									if (t.getTeacherSemester() != null) {
										TeacherSemester ts = new TeacherSemester();
										ts.setTeacherSemesterId(t.getTeacherSemester().getTeacherSemesterId());
										Teacher te = new Teacher();
										te.setTeacherId(t.getTeacherSemester().getTeacher().getTeacherId());
										te.setAccount(t.getTeacherSemester().getTeacher().getAccount());

										Semester s = new Semester();
										s.setSemesterId(t.getTeacherSemester().getSemester().getSemesterId());

										ts.setTeacher(te);
										ts.setSemester(s);
										newTimetable.setTeacherSemester(ts);
									}
									result.get(ccsId).add(newTimetable);
								}
							}
						}
					}
				}
			}
		}
		return result;
	}

	@Override
	public boolean updateTimetable(int semesterId, List<DaySlot> daySlots, List<DaySlot> prevDaySlots)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(Const.DATE);

		HashMap<String, Set<Integer>> mMergeClass = classCourseSemesterMergeService
				.getMapCourseWithMergeClassInSemester(semesterId);
		HashMap<String, Set<Timetable>> mActionTimetable = new HashMap<String, Set<Timetable>>();

		mActionTimetable.put("add", new LinkedHashSet<Timetable>());
		mActionTimetable.put("delete", new LinkedHashSet<Timetable>());

		for (int i = 0; i < daySlots.size(); i++) {
			System.out.println("Dayslot: " + i);
			DaySlot dayslot = daySlots.get(i);
			DaySlot prevDaySlot = prevDaySlots.get(i);
			if (prevDaySlot.getSetCourseSlot() != dayslot.getSetCourseSlot()) {
				Timetable timetable;
				Date date = sdf.parse(dayslot.getDate());

				if (prevDaySlot.getSetCourseSlot() != -1) {
					System.out.println("aa");
					timetable = timetableService.getTimetableByDateSlotClassCourse(date, prevDaySlot.getSlot(),
							prevDaySlot.getSetCourseSlot());
					if (dayslot.getSetCourseSlot() != -1) {
						ClassCourseSemester ccs = classCourseSemesterService
								.getClassCourseSemesterById(dayslot.getSetCourseSlot(), false, false);
						int csIdPrev = timetable.getClassCourseSemester().getCourseSemester().getCourseSemesterId();
						for (String key : mMergeClass.keySet()) {
							if (key.contains(Integer.toString(csIdPrev))
									&& mMergeClass.get(key).contains(prevDaySlot.getSetCourseSlot())) {
								Timetable newT = new Timetable();
								newT.setDate(timetable.getDate());
								newT.setSlot(timetable.getSlot());
								newT.setClassCourseSemester(timetable.getClassCourseSemester());
								mActionTimetable.get("delete").add(newT);
								break;
							}
						}

						timetable.setClassCourseSemester(ccs);
						int csIdNow = ccs.getCourseSemester().getCourseSemesterId();
						for (String key : mMergeClass.keySet()) {
							if (key.contains(Integer.toString(csIdNow))
									&& mMergeClass.get(key).contains(dayslot.getSetCourseSlot())) {
								mActionTimetable.get("add").add(timetable);
							}
						}
						timetableService.updateTimetable(timetable);
					} else {
						int csId = classCourseSemesterService
								.getClassCourseSemesterById(prevDaySlot.getSetCourseSlot(), false, false)
								.getCourseSemester().getCourseSemesterId();
						for (String key : mMergeClass.keySet()) {
							if (key.contains(Integer.toString(csId))
									&& mMergeClass.get(key).contains(prevDaySlot.getSetCourseSlot())) {
								mActionTimetable.get("delete").add(timetable);
							}
						}
						timetableService.deleteTimetable(timetable.getTimeTableId());
					}
				} else {
					System.out.println("bb");
					timetable = new Timetable();
					ClassCourseSemester ccs = classCourseSemesterService
							.getClassCourseSemesterById(dayslot.getSetCourseSlot(), false, false);
					int csId = ccs.getCourseSemester().getCourseSemesterId();
					if (mMergeClass.isEmpty()) {
						timetable.setClassCourseSemester(ccs);
						timetable.setDate(date);
						timetable.setSlot(dayslot.getSlot());
						timetableService.addTimetable(timetable);
					} else {
						for (String key : mMergeClass.keySet()) {
							System.out.println("cc: " + key);
							if (key.contains(Integer.toString(csId))
									&& mMergeClass.get(key).contains(dayslot.getSetCourseSlot())) {
								mActionTimetable.get("add").add(timetable);
							}
							timetable.setClassCourseSemester(ccs);
							timetable.setDate(date);
							timetable.setSlot(dayslot.getSlot());
							timetableService.addTimetable(timetable);
						}
					}
				}
			}
		}
		// handle merge class

		// add merge class
		for (Timetable t : mActionTimetable.get("add")) {
			int ccsId = t.getClassCourseSemester().getClassCourseSemesterId();
			int csId = t.getClassCourseSemester().getCourseSemester().getCourseSemesterId();
			for (String key : mMergeClass.keySet()) {
				if (key.contains(Integer.toString(csId)) && mMergeClass.get(key).contains(ccsId)) {
					for (int ccsId2 : mMergeClass.get(key)) {
						if (ccsId2 != ccsId) {
							ClassCourseSemester ccsMerge = classCourseSemesterService.getClassCourseSemesterById(ccsId2,
									true, false);
							Timetable newTimetable = new Timetable();
							newTimetable.setClassCourseSemester(ccsMerge);
							newTimetable.setDate(t.getDate());
							newTimetable.setRoom(t.getRoom());
							newTimetable.setSlot(t.getSlot());
							newTimetable.setTeacherSemester(t.getTeacherSemester());
							if (timetableService.getTimetableByDateSlotClassCourse(newTimetable.getDate(),
									newTimetable.getSlot(),
									newTimetable.getClassCourseSemester().getClassCourseSemesterId()) == null) {
								timetableService.addTimetable(newTimetable);
							}
						}
					}
				}
			}
		}

		// delete merge class when updated or deleted
		for (Timetable t : mActionTimetable.get("delete")) {
			int ccsId = t.getClassCourseSemester().getClassCourseSemesterId();
			int csId = t.getClassCourseSemester().getCourseSemester().getCourseSemesterId();
			for (String key : mMergeClass.keySet()) {
				if (key.contains(Integer.toString(csId)) && mMergeClass.get(key).contains(ccsId)) {
					for (int ccsId2 : mMergeClass.get(key)) {
						if (ccsId2 != ccsId) {
							Timetable dT = timetableService.getTimetableByDateSlotClassCourse(t.getDate(), t.getSlot(),
									ccsId2);
							if (dT != null) {
								timetableService.deleteTimetable(dT.getTimeTableId());
							}
						}
					}
				}
			}
		}
		return true;
	}

	@Override
	public boolean generateFromPreviousWeek(int semesterId, int classId, int week) {
		// Get Semester
		Semester semester = semesterService.getSemesterById(semesterId, false, false, false, false);

		// Get Start Calendar and End Calendar
		Date startDate = semester.getStartDate();
		Calendar startWeek = Calendar.getInstance();
		Calendar endWeek = Calendar.getInstance();
		startWeek.setTime(startDate);
		startWeek.set(Calendar.DATE, startWeek.get(Calendar.DATE) + 7 * (week - 2));
		endWeek.setTime(startWeek.getTime());
		endWeek.set(Calendar.DATE, endWeek.get(Calendar.DATE) + 6);

		// get Class Semester
		ClassSemester classSemester = classSemesterService.getClassSemesterByClassSemester(semesterId, classId, true);

		// Get List Timetable of class in prev week
		List<Timetable> timetableOfClassInPrevWeek = timetableService.listTimetablesByClassCourseSemestersInWeek(
				classSemester.getClassCourseSemesters(), startWeek.getTime(), endWeek.getTime());
		startWeek.set(Calendar.DATE, startWeek.get(Calendar.DATE) + 7);
		endWeek.set(Calendar.DATE, endWeek.get(Calendar.DATE) + 7);
		timetableService.deleteTimetablesByCCSInWeek(classSemester.getClassSemesterId(), startWeek.getTime(),
				endWeek.getTime());

		HashMap<String, Set<Integer>> mMergeClass = classCourseSemesterMergeService
				.getMapCourseWithMergeClassInSemester(semesterId);

		Set<Timetable> sTimetable = new LinkedHashSet<Timetable>();
		for (Timetable timetable : timetableOfClassInPrevWeek) {
			Timetable t = new Timetable();
			Calendar c = Calendar.getInstance();
			c.setTime(timetable.getDate());
			c.set(Calendar.DATE, c.get(Calendar.DATE) + 7);

			t.setDate(c.getTime());
			t.setSlot(timetable.getSlot());
			t.setRoom(timetable.getRoom());
			t.setClassCourseSemester(timetable.getClassCourseSemester());
			t.setTeacherSemester(timetable.getTeacherSemester());
			timetableService.addTimetable(t);

			int csId = t.getClassCourseSemester().getCourseSemester().getCourseSemesterId();
			int ccsId = t.getClassCourseSemester().getClassCourseSemesterId();
			for (String key : mMergeClass.keySet()) {
				if (key.contains(Integer.toString(csId)) && mMergeClass.get(key).contains(ccsId)) {
					sTimetable.add(t);
					break;
				}
			}
		}

		for (Timetable t : sTimetable) {
			// get Course Semester Id and classCourseSemesterId
			int csId = t.getClassCourseSemester().getCourseSemester().getCourseSemesterId();
			int ccsId = t.getClassCourseSemester().getClassCourseSemesterId();

			// loop through key set and find key that has this csId and ccsId
			for (String key : mMergeClass.keySet()) {
				if (key.contains(Integer.toString(csId)) && mMergeClass.get(key).contains(ccsId)) {
					for (int ccsId2 : mMergeClass.get(key)) {
						if (ccsId2 != ccsId) {
							ClassCourseSemester ccsMerge = classCourseSemesterService.getClassCourseSemesterById(ccsId2,
									true, false);
							Timetable newTimetable = new Timetable();
							newTimetable.setClassCourseSemester(ccsMerge);
							newTimetable.setDate(t.getDate());
							newTimetable.setRoom(t.getRoom());
							newTimetable.setSlot(t.getSlot());
							newTimetable.setTeacherSemester(t.getTeacherSemester());
							timetableService.addTimetable(newTimetable);
						}
					}
				}
			}
		}
		return true;
	}

	private void processMergeCourses(List<List<String>> mergeCourses, int semesterId) {
		for (List<String> list : mergeCourses) {
			String courseCode = list.get(0);
			String hostClassCode = list.get(1);
			String guestClassCode = list.get(2);
			ClassSemester hostClassSemester = classSemesterService.getClassSemesterByCode(hostClassCode, semesterId,
					false);
			ClassSemester guestClassSemester = classSemesterService.getClassSemesterByCode(guestClassCode, semesterId,
					false);
			CourseSemester courseSemester = courseSemesterService.getCourseSemesterByCourseCodeSemester(courseCode,
					semesterId, false, false, false);
			ClassCourseSemester hostClassCourseSemester = classCourseSemesterService
					.getClassCourseSemesterByClassAndCourseSemester(hostClassSemester.getClassSemesterId(),
							courseSemester.getCourseSemesterId(), false, false);
			ClassCourseSemester guestClassCourseSemester = classCourseSemesterService
					.getClassCourseSemesterByClassAndCourseSemester(guestClassSemester.getClassSemesterId(),
							courseSemester.getCourseSemesterId(), false, false);
			ClassCourseSemesterMerge classCourseSemesterMerge = new ClassCourseSemesterMerge();
			classCourseSemesterMerge.setClassCourseSemester1(hostClassCourseSemester);
			classCourseSemesterMerge.setClassCourseSemester2(guestClassCourseSemester);
			classCourseSemesterMergeService.addClassCourseSemesterMerge(classCourseSemesterMerge);
		}
	}

	private void copyTimetableMerge(Map<ClassCourseSemester, ClassCourseSemester> guestHostMap) {
		for (Entry<ClassCourseSemester, ClassCourseSemester> entry : guestHostMap.entrySet()) {
			ClassCourseSemester guestClassCourseSemester = entry.getKey();
			ClassCourseSemester hostClassCourseSemester = entry.getValue();
			Set<ClassCourseSemester> hostClassCourseSemesters = new LinkedHashSet<>();
			hostClassCourseSemesters.add(hostClassCourseSemester);
			List<Timetable> hostTimetables = timetableService
					.listTimetablesByClassCourseSemesters(hostClassCourseSemesters);
			for (Timetable hostTimetable : hostTimetables) {
				Date date = hostTimetable.getDate();
				int slot = hostTimetable.getSlot();
				Timetable guestTimetable = timetableService.getTimetableByDateSlotClassCourse(date, slot,
						guestClassCourseSemester.getClassCourseSemesterId());
				boolean update = true;
				if (guestTimetable == null) {
					guestTimetable = new Timetable();
					update = false;
				}
				guestTimetable.setDate(hostTimetable.getDate());
				guestTimetable.setSlot(hostTimetable.getSlot());
				guestTimetable.setRoom(hostTimetable.getRoom());
				guestTimetable.setClassCourseSemester(guestClassCourseSemester);
				guestTimetable.setTeacherSemester(hostTimetable.getTeacherSemester());
				if (update) {
					timetableService.updateTimetable(guestTimetable);
				} else {
					timetableService.addTimetable(guestTimetable);
				}
			}
		}
	}

	private void buildTimetable(Semester semester, SingleSolution[] singleSolutions,
			HashMap<ClassCourse, vn.edu.fpt.timetabling.auto.entities.Room> roomMap,
			HashMap<ClassCourse, vn.edu.fpt.timetabling.auto.entities.Teacher> teacherMap, DataCenter dataCenter,
			List<List<String>> mergeCourses) {
		int semesterId = semester.getSemesterId();
		Date startDate = semester.getStartDate();
		Calendar calendar = Calendar.getInstance();
		HashMap<ClassCourseSemester, Room> roomMapConverted = new HashMap<>();
		HashMap<ClassCourseSemester, TeacherSemester> teacherMapConverted = new HashMap<>();
		for (Entry<ClassCourse, vn.edu.fpt.timetabling.auto.entities.Room> entry : roomMap.entrySet()) {
			ClassCourseSemester classCourseSemester = classCourseSemesterService
					.getClassCourseSemesterById(entry.getKey().ID, false, false);
			Room room = roomService.getRoomById(entry.getValue().ID, false);
			roomMapConverted.put(classCourseSemester, room);
		}
		for (Entry<ClassCourse, vn.edu.fpt.timetabling.auto.entities.Teacher> entry : teacherMap.entrySet()) {
			ClassCourseSemester classCourseSemester = classCourseSemesterService
					.getClassCourseSemesterById(entry.getKey().ID, false, false);
			TeacherSemester teacherSemester = teacherSemesterService.getTeacherSemesterById(entry.getValue().ID, false,
					false);
			teacherMapConverted.put(classCourseSemester, teacherSemester);
		}
		for (SingleSolution singleSolution : singleSolutions) {
			int[][] classCourses = singleSolution.T;
			for (int weekDiff = 0; weekDiff < Const.Timetable.NUMBER_WEEKS_DIFF; weekDiff++) {
				int baseDay = weekDiff * Const.Timetable.NUMBER_WORKING_DAYS_IN_WEEK;
				for (int dayInWeek = 0; dayInWeek < Const.Timetable.NUMBER_WORKING_DAYS_IN_WEEK; dayInWeek++) {
					int day = baseDay + dayInWeek;
					for (int slot = 0; slot < Const.Timetable.NUMBER_SLOTS_PER_DAY; slot++) {
						int classCourseId = classCourses[slot][day];
						if (classCourseId == -1) {
							continue;
						}
						ClassCourseSemester classCourseSemester = classCourseSemesterService
								.getClassCourseSemesterById(classCourseId, false, false);
						for (int week = 0; week < Const.Timetable.NUMBER_WEEKS_SIMILAR; week++) {
							int dayFromStart = weekDiff * Const.Timetable.NUMBER_WEEKS_SIMILAR
									* Const.Timetable.NUMBER_DAYS_IN_WEEK + dayInWeek
									+ week * Const.Timetable.NUMBER_DAYS_IN_WEEK;
							if (weekDiff * Const.Timetable.NUMBER_WEEKS_SIMILAR
									+ week >= Const.Timetable.NUMBER_WEEKS_PER_BLOCK) {
								dayFromStart += Const.Timetable.NUMBER_WEEKS_BETWEEN_BLOCK
										* Const.Timetable.NUMBER_DAYS_IN_WEEK;
							}
							calendar.setTime(startDate);
							calendar.add(Calendar.DATE, dayFromStart);
							Date date = calendar.getTime();
							Timetable timetable = new Timetable();
							timetable.setDate(date);
							timetable.setSlot(slot + 1);
							timetable.setClassCourseSemester(classCourseSemester);
							timetable.setRoom(roomMapConverted.get(classCourseSemester));
							timetable.setTeacherSemester(teacherMapConverted.get(classCourseSemester));
							timetableService.addTimetable(timetable);
						}
					}
				}
			}
		}
		processMergeCourses(mergeCourses, semesterId);
		Map<ClassCourseSemester, ClassCourseSemester> guestHostMap = classCourseSemesterMergeService
				.getGuestHostMap(semesterId);
		copyTimetableMerge(guestHostMap);
		tryAssignRooms(semesterId);
		tryAssignTeachers(semesterId);
		System.out.println("Percent of slot 3+4: " + timetableService.countPercentSlots34(semesterId));
	}

	@Override
	public void autoSchedule(int semesterId) {
		classCourseSemesterMergeService.deleteClassCourseSemesterMerges(semesterId);
		timetableService.deleteTimetablesBySemester(semesterId);
		Semester semester = semesterService.getSemesterById(semesterId, true, true, false, false);
		List<Department> departments = departmentService.listDepartments();
		List<Room> rooms = roomService.listRooms(false);
		Set<ClassSemester> classSemesters = semester.getClassSemesters();
		Set<CourseSemester> courseSemesters = semester.getCourseSemesters();
		List<TeacherSemester> teacherSemesters = teacherSemesterService.listTeacherSemestersBySemester(semesterId, true,
				false);
		List<ClassCourseSemesterMerge> classCourseSemesterMerges = classCourseSemesterMergeService
				.listClassCourseSemesterMerges();
		List<Building> buildings = buildingService.listBuildings();
		List<String> departmentData = new ArrayList<>();
		List<String> roomData = new ArrayList<>();
		List<String> classData = new ArrayList<>();
		List<String> courseData = new ArrayList<>();
		List<String> classCourseData = new ArrayList<>();
		List<String> mergeClassData = new ArrayList<>();
		List<String> teacherData = new ArrayList<>();
		List<String> buildingData = new ArrayList<>();
		List<String> teacherCourseData = new ArrayList<>();
		for (Department department : departments) {
			departmentData.add(department.getDepartmentId() + "|" + department.getCode());
		}
		for (Room room : rooms) {
			String row = room.getRoomId() + "|" + room.getCode() + "|" + room.getBuilding().getCode() + "|"
					+ room.getCapacity();
			if (room.getCourses() == null || room.getCourses().isEmpty()) {
				row += "|0";
			} else {
				StringTokenizer stringTokenizer = new StringTokenizer(room.getCourses(), ", ");
				int count = 0;
				while (stringTokenizer.hasMoreTokens()) {
					count++;
					stringTokenizer.nextToken();
				}
				row += "|" + count + "|" + room.getCourses().replace(", ", "|");
			}
			roomData.add(row);
		}
		for (ClassSemester classSemester : classSemesters) {
			classData.add(classSemester.getClassSemesterId() + "|" + classSemester.getClassFPT().getCode());
			Set<ClassCourseSemester> classCourseSemesters = classSemester.getClassCourseSemesters();
			for (ClassCourseSemester classCourseSemester : classCourseSemesters) {
				int classCourseSemesterId = classCourseSemester.getClassCourseSemesterId();
				String data = classCourseSemesterId + "|"
						+ classCourseSemester.getCourseSemester().getCourse().getCode() + "|"
						+ classCourseSemester.getClassSemester().getClassFPT().getCode() + "|";
				if (classCourseSemester.isSemesterLong()) {
					data += "3";
				} else {
					data += classCourseSemester.getBlockCondition();
				}
				data += "|" + classCourseSemesterService.getNumberOfStudents(classCourseSemesterId);
				classCourseData.add(data);
			}
		}
		for (CourseSemester courseSemester : courseSemesters) {
			Course course = courseSemester.getCourse();
			courseData.add(courseSemester.getCourseSemesterId() + "|" + course.getCode() + "|"
					+ course.getDepartment().getCode());
			List<TeacherSemester> teacherSemesterCourse = teacherSemesterService
					.listTeacherSemestersByCourse(semesterId, course.getCourseId());
			String row = course.getCode() + "|" + teacherSemesterCourse.size();
			for (TeacherSemester teacherSemester : teacherSemesterCourse) {
				row += "|" + teacherSemester.getTeacher().getAccount();
			}
			teacherCourseData.add(row);
		}
		for (ClassCourseSemesterMerge classCourseSemesterMerge : classCourseSemesterMerges) {
			mergeClassData.add(
					classCourseSemesterMerge.getClassCourseSemester1().getCourseSemester().getCourse().getCode() + "|"
							+ classCourseSemesterMerge.getClassCourseSemester1().getClassSemester().getClassFPT()
									.getCode()
							+ "|" + classCourseSemesterMerge.getClassCourseSemester2().getClassSemester().getClassFPT()
									.getCode());
		}
		for (TeacherSemester teacherSemester : teacherSemesters) {
			teacherData.add(teacherSemester.getTeacherSemesterId() + "|" + teacherSemester.getTeacher().getAccount());
		}
		for (Building building : buildings) {
			buildingData.add(building.getBuildingId() + "|" + building.getCode());
		}
		new File("D://datafall").mkdirs();
		TimeTableAllClass TA = new TimeTableAllClass();
		TA.DA = new DataCenter();
		TA.DA.loadData_Department_v2(departmentData);
		TA.DA.loadData_Course_v2(courseData);
		TA.DA.loadData_Class_v2(classData);
		TA.DA.loadData_Building_v2(buildingData);
		TA.DA.loadData_Room_v2(roomData);
		TA.DA.loadData_ClassCourse_v2(classCourseData);
		TA.DA.assignStudentEachClassCourse();
		TA.DA.loadData_Teacher_v2(teacherData);
		TA.DA.loadData_Course_Teacher_v2(teacherCourseData);
		TA.DA.mergeClassCourse();
		TA.DA.reloadData_ClassCourse_v2(classCourseData);
		TA.DA.loadData_RoomEachCourse();
		if (!TA.DA.isDataValidForTemplate2()) {
			System.out.println("Not feasible to make timetable.");
			System.exit(1);
		}

		TA.makeSolutionWarehouse_Template2("D:/datafall/data_solutionWarehouses_Temp2.dat");
		TA.stateModel("D:/datafall/data_solutionWarehouses_Temp2.dat");
		TA.initRandom();
		TA.findRandomFeasibleSolution();
		TA.demand_OfACourse = TA.calculateTeacherDemand(TA.beingUsedTimeTable);
		TA.totalDemand = TA.sumArray(TA.demand_OfACourse);
		TA.fStar = TA.totalDemand;
		TA.sStar = TA.beingUsedTimeTable;
		try {
			TA.findOptimalTimetable_TabuSearch2();
		} catch (IOException e) {
			e.printStackTrace();
		}
		TA.savebeingUsedTimeTable("D:/datafall/data_beingusedTT_Temp2.dat");
		SingleSolution[] ttb = TA.loadbeingUsedTimeTable("D:/datafall/data_beingusedTT_Temp2.dat");
		TA.beingUsedTimeTable = ttb;
		TA.writeMatrix2File("D:/datafall/data_conflictMatrix_Temp2.txt", TA.beingUsedTimeTable);
		List<List<String>> mergeCourses = new ArrayList<>();
		if (TA.isTimeTableCorrect("D:/datafall/data_conflictMatrix_Temp2.txt")) {
			System.out.println("Timetable is correct.");
			TA.printTimeTableAllClass("D:/datafall/optimizedTTB_fall.html", TA.beingUsedTimeTable);
			AssignTeacher teacherAssigner = new AssignTeacher();
			teacherAssigner.DA = TA.DA;
			teacherAssigner.assignTeacherUsingScore("D:/datafall/data_conflictMatrix_Temp2.txt",
					"D:/datafall/data_beingusedTT_Temp2.dat", "D:/datafall/data_scoreMatrix.txt",
					teacherAssigner.DA.classCourses);
			AssignRoom roomAssigner;
			roomAssigner = new AssignRoom();
			roomAssigner.DA = TA.DA;
			roomAssigner.assignRoomUsingFor("D:/datafall/data_beingusedTT_Temp2.dat");
			TA.calRoomDemandEverySlot(TA.beingUsedTimeTable);
			System.out.println();
			TA.demand_OfACourse = new int[TA.DA.nbCourse];
			TA.demand_OfACourse = TA.calculateTeacherDemand(ttb);
			int[] ss = TA.findSessionAllClass(ttb);
			System.out.println("sang = " + ss[0]);
			System.out.println("chieu = " + ss[1]);
			TA.testDepartMentDemand(ttb);
			mergeCourses = TA.recoverTimeTableMergedCase(ttb);
			TA.PoiWriteExcelFile(TA, "D:/datafall/ttb.xls", ttb);
			// TA.beingUsedTimeTable);
		} else {
			System.out.println("Timetable is incorrect.");
		}
		buildTimetable(semester, ttb, TA.DA.mClassCourse2AssignedRoom, TA.DA.mClassCourse2AssignedTeacher, TA.DA,
				mergeCourses);
	}

	@Override
	public void autoScheduleRoom(int semesterId) {
		List<Timetable> timetables = timetableService.listTimetablesWithoutRoom(semesterId);
		if (timetables.isEmpty()) {
			return;
		}
		Map<ClassCourseSemester, ClassCourseSemester> guestHostMap = classCourseSemesterMergeService
				.getGuestHostMap(semesterId);
		TeacherSemester teacherSemester = timetables.get(0).getTeacherSemester();
		Map<Course, Map<String, Room>> courseRoomMap = roomService.getCourseRoomMap();
		List<Room> allRooms = roomService.listRooms(false);
		List<Room> suitableRooms = new ArrayList<>();
		Map<ClassCourseSemester, List<Timetable>> classCourseMap = getClassCourseMap(timetables);
		for (Timetable timetable : timetables) {
			timetableService.deleteTimetable(timetable.getTimeTableId());
		}
		Map<ClassCourseSemester, List<ClassCourseSemester>> hostGuestMap = classCourseSemesterMergeService
				.getHostGuestMap(semesterId);
		Date startDate = semesterService.getSemesterById(semesterId, false, false, false, false).getStartDate();
		Map<ClassCourseSemester, ClassCourseSemester> guestHostMapUpdate = new HashMap<>();
		for (Entry<ClassCourseSemester, List<Timetable>> entry : classCourseMap.entrySet()) {
			ClassCourseSemester classCourseSemester = entry.getKey();
			if (guestHostMap.containsKey(classCourseSemester)) {
				guestHostMapUpdate.put(classCourseSemester, guestHostMap.get(classCourseSemester));
				continue;
			}
			List<ClassCourseSemester> guestClassCourseSemesters;
			if (hostGuestMap.containsKey(classCourseSemester)) {
				guestClassCourseSemesters = hostGuestMap.get(classCourseSemester);
			} else {
				guestClassCourseSemesters = new ArrayList<>();
			}
			ClassSemester classSemester = classCourseSemester.getClassSemester();
			Course course = classCourseSemester.getCourseSemester().getCourse();
			System.out.println(
					"Process for class-course: " + classSemester.getClassFPT().getCode() + " / " + course.getCode());
			if (courseRoomMap.containsKey(course)) {
				for (Room room : courseRoomMap.get(course).values()) {
					suitableRooms.add(room);
				}
			} else {
				suitableRooms = allRooms;
			}
			int numberOfSlots = entry.getValue().size();
			for (int week = 0; week < 15; week++) {
				System.out.println("Loop week: " + week);
				if (week == Const.Timetable.NUMBER_WEEKS_PER_BLOCK) {
					week += Const.Timetable.NUMBER_WEEKS_BETWEEN_BLOCK;
				}
				int slotPerWeek = 0;
				for (int dayInWeek = 0; dayInWeek < Const.Timetable.NUMBER_WORKING_DAYS_IN_WEEK + 1; dayInWeek++) {
					int plusDate = week * Const.Timetable.NUMBER_DAYS_IN_WEEK + dayInWeek;
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(startDate);
					calendar.add(Calendar.DATE, plusDate);
					Date date = calendar.getTime();
					System.out.println("	Loop day: " + date);
					for (int slot = 1; slot <= Const.Timetable.NUMBER_SLOTS_PER_DAY; slot++) {
						if (slot == 1 && dayInWeek == 5) {
							continue;
						}
						boolean conflictClass = false;
						for (ClassCourseSemester guestClassCourseSemester : guestClassCourseSemesters) {
							if (timetableService.getTimetableByDateSlotClass(date, slot,
									guestClassCourseSemester.getClassSemester().getClassSemesterId()) != null) {
								conflictClass = true;
								break;
							}
						}
						if (!conflictClass
								&& timetableService.getTimetableByDateSlotClass(date, slot,
										classSemester.getClassSemesterId()) == null
								&& (teacherSemester == null || timetableService.getTimetableByDateSlotTeacher(date,
										slot, teacherSemester.getTeacherSemesterId()).isEmpty())) {
							for (Room room : suitableRooms) {
								if (timetableService.getTimetableByDateSlotRoom(date, slot, room.getRoomId())
										.isEmpty()) {
									Timetable timetable = new Timetable();
									timetable.setDate(date);
									timetable.setTeacherSemester(teacherSemester);
									timetable.setSlot(slot);
									timetable.setClassCourseSemester(classCourseSemester);
									timetable.setRoom(room);
									timetableService.addTimetable(timetable);
									System.out.println("		Found: " + date + " " + slot + " " + course.getCode());
									slotPerWeek++;
									numberOfSlots--;
									break;
								}
							}
						}
						if (slotPerWeek >= 5 || numberOfSlots <= 0) {
							break;
						}
					}
					if (slotPerWeek >= 5 || numberOfSlots <= 0) {
						break;
					}
				}
				if (numberOfSlots <= 0) {
					break;
				}
			}
		}
		copyTimetableMerge(guestHostMapUpdate);
	}

	private void tryAssignRooms(int semesterId) {
		System.out.println("Try to assign rooms.");
		List<Timetable> timetables = timetableService.listTimetablesWithoutRoom(semesterId);
		Map<ClassCourseSemester, ClassCourseSemester> guestHostMap = classCourseSemesterMergeService
				.getGuestHostMap(semesterId);
		List<Room> allRooms = roomService.listRooms(false);
		List<Room> suitableRooms = new ArrayList<>();
		Map<Course, Map<String, Room>> courseRoomMap = roomService.getCourseRoomMap();
		Map<ClassCourseSemester, ClassCourseSemester> guestHostMapUpdate = new HashMap<>();
		Map<ClassCourseSemester, List<Timetable>> classCourseMap = getClassCourseMap(timetables);
		int count = 0;
		for (Entry<ClassCourseSemester, List<Timetable>> entry : classCourseMap.entrySet()) {
			int countEachClassCourse = 0;
			ClassCourseSemester classCourseSemester = entry.getKey();
			if (guestHostMap.containsKey(classCourseSemester)) {
				guestHostMapUpdate.put(classCourseSemester, guestHostMap.get(classCourseSemester));
				continue;
			}
			ClassSemester classSemester = classCourseSemester.getClassSemester();
			Course course = classCourseSemester.getCourseSemester().getCourse();
			System.out.println(
					"Process for class-course: " + classSemester.getClassFPT().getCode() + " / " + course.getCode());
			if (courseRoomMap.containsKey(course)) {
				for (Room room : courseRoomMap.get(course).values()) {
					suitableRooms.add(room);
				}
			} else {
				suitableRooms = allRooms;
			}
			for (Room room : suitableRooms) {
				boolean allSuitable = true;
				for (Timetable timetable : entry.getValue()) {
					if (!timetableService
							.getTimetableByDateSlotRoom(timetable.getDate(), timetable.getSlot(), room.getRoomId())
							.isEmpty()) {
						allSuitable = false;
						break;
					}
				}
				if (allSuitable) {
					for (Timetable timetable : entry.getValue()) {
						timetable.setRoom(room);
						timetableService.updateTimetable(timetable);
						count++;
						countEachClassCourse++;
					}
				}
			}
			System.out.println("Try assign teachers classCourse success: " + countEachClassCourse);
		}
		System.out.println("Try assign rooms success: " + count);
		copyTimetableMerge(guestHostMapUpdate);
	}

	private Map<ClassCourseSemester, List<Timetable>> getClassCourseMap(List<Timetable> timetables) {
		Map<ClassCourseSemester, List<Timetable>> classCourseMap = new HashMap<>();
		for (Timetable timetable : timetables) {
			ClassCourseSemester classCourseSemester = timetable.getClassCourseSemester();
			if (!classCourseMap.containsKey(classCourseSemester)) {
				classCourseMap.put(classCourseSemester, new ArrayList<>());
			}
			classCourseMap.get(classCourseSemester).add(timetable);
		}
		return classCourseMap;
	}

	private void tryAssignTeachers(int semesterId) {
		System.out.println("Try to assign teachers.");
		List<Timetable> timetables = timetableService.listTimetablesWithoutTeacher(semesterId);
		Map<ClassCourseSemester, ClassCourseSemester> guestHostMap = classCourseSemesterMergeService
				.getGuestHostMap(semesterId);
		Map<ClassCourseSemester, ClassCourseSemester> guestHostMapUpdate = new HashMap<>();
		Map<ClassCourseSemester, List<Timetable>> classCourseMap = getClassCourseMap(timetables);
		int count = 0;
		for (Entry<ClassCourseSemester, List<Timetable>> entry : classCourseMap.entrySet()) {
			int countEachClassCourse = 0;
			ClassCourseSemester classCourseSemester = entry.getKey();
			if (guestHostMap.containsKey(classCourseSemester)) {
				guestHostMapUpdate.put(classCourseSemester, guestHostMap.get(classCourseSemester));
				continue;
			}
			ClassSemester classSemester = classCourseSemester.getClassSemester();
			Course course = classCourseSemester.getCourseSemester().getCourse();
			System.out.println(
					"Process for class-course: " + classSemester.getClassFPT().getCode() + " / " + course.getCode());
			List<TeacherSemester> suitableTeachers = teacherSemesterService.listTeacherSemestersByCourse(semesterId,
					course.getCourseId());
			for (TeacherSemester suitableTeacher : suitableTeachers) {
				boolean allSuitable = true;
				for (Timetable timetable : entry.getValue()) {
					if (!timetableService.getTimetableByDateSlotTeacher(timetable.getDate(), timetable.getSlot(),
							suitableTeacher.getTeacherSemesterId()).isEmpty()) {
						allSuitable = false;
						break;
					}
				}
				if (allSuitable) {
					for (Timetable timetable : entry.getValue()) {
						timetable.setTeacherSemester(suitableTeacher);
						timetableService.updateTimetable(timetable);
						count++;
						countEachClassCourse++;
					}
				}
			}
			System.out.println("Try assign teachers classCourse success: " + countEachClassCourse);
		}
		System.out.println("Try assign teachers success: " + count);
		copyTimetableMerge(guestHostMapUpdate);
	}
}
