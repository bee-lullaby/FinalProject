package vn.edu.fpt.timetabling.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.fpt.timetabling.dao.TimetableDAO;
import vn.edu.fpt.timetabling.model.ClassCourseSemester;
import vn.edu.fpt.timetabling.model.ClassSemester;
import vn.edu.fpt.timetabling.model.TeacherSemester;
import vn.edu.fpt.timetabling.model.Timetable;

@Service
@Transactional(rollbackFor = Exception.class)
public class TimetableServiceImpl implements TimetableService {
	private TimetableDAO timetableDAO;
	@Autowired
	private ClassSemesterService classSemesterService;
	@Autowired
	private ClassCourseSemesterService classCourseSemesterService;

	public void setTimetableDAO(TimetableDAO timetableDAO) {
		this.timetableDAO = timetableDAO;
	}

	@Override
	public void addTimetable(Timetable timetable) {
		timetableDAO.addTimetable(timetable);
	}

	@Override
	public void updateTimetable(Timetable timetable) {
		timetableDAO.updateTimetable(timetable);
	}

	@Override
	public List<Timetable> listTimetables() {
		return timetableDAO.listTimetables();
	}

	@Override
	public List<Timetable> listTimetablesBySemester(int semesterId) {
		return timetableDAO.listTimetablesBySemester(semesterId);
	}

	@Override
	public Set<Timetable> listTimetablesByClassCourse(int classCourseSemesterId) {
		return classCourseSemesterService.getClassCourseSemesterById(classCourseSemesterId, true, false).getTimetable();
	}

	@Override
	public Timetable getTimetableById(int timetableId) {
		return timetableDAO.getTimetableById(timetableId);
	}

	@Override
	public void deleteTimetable(int timetableId) {
		timetableDAO.deleteTimetable(timetableId);
	}

	@Override
	public List<Timetable> listTimetablesByClassCourseSemesters(Collection<ClassCourseSemester> classCourseSemesters) {
		return timetableDAO.listTimetablesByClassCourseSemesters(classCourseSemesters);
	}

	@Override
	public Timetable getTimetableByDateSlotClassCourse(Date date, int slot, int classCourseSemesterId) {
		return timetableDAO.getTimetableByDateSlotClassCourse(date, slot, classCourseSemesterId);
	}

	@Override
	public Timetable getTimetableByDateSlotClass(Date date, int slot, int classSemesterId) {
		return timetableDAO.getTimetableByDateSlotClass(date, slot, classSemesterId);
	}

	@Override
	public List<Timetable> listTimetablesByClassCourseSemestersInWeek(Set<ClassCourseSemester> classCourseSemesters,
			Date startWeek, Date endWeek) {
		return timetableDAO.listTimetablesByClassCourseSemestersInWeek(classCourseSemesters, startWeek, endWeek);
	}

	@Override
	public List<Timetable> listTimetablesByDate(Date date) {
		return timetableDAO.listTimetablesByDate(date);
	}

	@Override
	public List<Timetable> listTimetableByClass(int classSemesterId) {
		ClassSemester classSemester = classSemesterService.getClassSemesterById(classSemesterId, true);
		return timetableDAO.listTimetablesByClassCourseSemesters(classSemester.getClassCourseSemesters());
	}

	@Override
	public List<Timetable> listTimetableByTeacher(int teacherSemesterId) {
		return timetableDAO.listTimetablesByTeacher(teacherSemesterId);
	}

	@Override
	public List<Timetable> listTimetableByStudent(int semesterId, int studentId) {
		List<Timetable> result = new ArrayList<Timetable>();

		List<ClassCourseSemester> classCourseSemesters = classCourseSemesterService
				.listClassCourseSemesterByStudent(semesterId, studentId);
		if (!classCourseSemesters.isEmpty()) {
			result.addAll(timetableDAO.listTimetablesByClassCourseSemesters(classCourseSemesters));
		}
		return result;
	}

	@Override
	public void deleteTimetablesByCCS(int classCourseSemesterId) {
		timetableDAO.deleteTimetablesByCCS(classCourseSemesterId);
	}

	@Override
	public void deleteTimetablesByCCSInWeek(int classSemesterId, Date startWeek, Date endWeek) {
		timetableDAO.deleteTimetablesByCCSInWeek(classSemesterId, startWeek, endWeek);
	}

	@Override
	public List<Timetable> listTimetablesByClassAndCourseCode(int semesterId, String classCode, String courseCode) {
		return timetableDAO.listTimetablesByClassAndCourseCode(semesterId, classCode, courseCode);
	}

	@Override
	public int deleteTimetablesBySemester(int semesterId) {
		return timetableDAO.deleteTimetablesBySemester(semesterId);
	}

	@Override
	public long countNumberSlots(int semesterId, boolean haveTeacher) {
		return timetableDAO.countNumberSlots(semesterId, haveTeacher);
	}

	@Override
	public double countPercentSlots34(int semesterId) {
		List<Timetable> timetables = listTimetablesBySemester(semesterId);
		Map<TeacherSemester, Set<String>> teacherDayMap = new HashMap<>();
		Map<TeacherSemester, Map<String, Integer>> teacherSlotMap = new HashMap<>();
		for (Timetable timetable : timetables) {
			int slot = timetable.getSlot();
			TeacherSemester teacherSemester = timetable.getTeacherSemester();
			if (teacherSemester != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(timetable.getDate());
				String keyString = calendar.get(Calendar.YEAR) + "/" + calendar.get(Calendar.MONTH) + "/"
						+ calendar.get(Calendar.DAY_OF_MONTH);
				Set<String> days;
				if (teacherDayMap.containsKey(teacherSemester)) {
					days = teacherDayMap.get(teacherSemester);
				} else {
					days = new HashSet<>();
					teacherDayMap.put(teacherSemester, days);
				}
				days.add(keyString);
				if (slot == 3 || slot == 4) {
					Map<String, Integer> slotMap;
					if (teacherSlotMap.containsKey(teacherSemester)) {
						slotMap = teacherSlotMap.get(teacherSemester);
						if (slotMap.containsKey(keyString)) {
							slotMap.put(keyString, slotMap.get(keyString) + slot);
						} else {
							slotMap.put(keyString, slot);
						}
					} else {
						slotMap = new HashMap<String, Integer>();
						teacherSlotMap.put(teacherSemester, slotMap);
					}
				}
			}
		}
		double totalDay = 0;
		for (Set<String> value : teacherDayMap.values()) {
			totalDay += value.size();
		}
		double countSlots34 = 0;
		for (Map<String, Integer> slotMap : teacherSlotMap.values()) {
			for (Integer slot : slotMap.values()) {
				if (slot == 7) {
					countSlots34++;
				}
			}
		}
		System.out.println("Total teaching days: " + totalDay);
		System.out.println("Total time slot 3+4: " + countSlots34);
		return totalDay / countSlots34;
	}

	@Override
	public List<Timetable> listTimetablesByClassSemester(int classSemesterId) {
		return timetableDAO.listTimetablesByClassSemester(classSemesterId);
	}

	@Override
	public List<Timetable> listTimetablesByRoom(int semesterId, int roomId) {
		return timetableDAO.listTimetablesByRoom(semesterId, roomId);
	}
}
