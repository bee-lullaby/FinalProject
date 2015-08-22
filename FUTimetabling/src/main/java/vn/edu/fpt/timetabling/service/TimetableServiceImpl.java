package vn.edu.fpt.timetabling.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.fpt.timetabling.dao.TimetableDAO;
import vn.edu.fpt.timetabling.model.ClassCourseSemester;
import vn.edu.fpt.timetabling.model.ClassSemester;
import vn.edu.fpt.timetabling.model.Timetable;

@Service
@Transactional(rollbackFor = Exception.class)
public class TimetableServiceImpl implements TimetableService {
	private TimetableDAO timetableDAO;
	@Autowired
	private SemesterService semesterService;
	@Autowired
	private ClassSemesterService classSemesterService;
	@Autowired
	private ClassCourseSemesterService classCourseSemesterService;
	@Autowired
	private TeacherSemesterService teacherSemesterService;

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
	public Set<Timetable> listTimetablesBySemester(int semesterId) {
		Set<ClassSemester> classSemesters = semesterService.getSemesterById(
				semesterId, true, false, false, false).getClassSemesters();
		Iterator<ClassSemester> classSemester = classSemesters.iterator();
		Set<ClassCourseSemester> classCourseSemesters = new LinkedHashSet<ClassCourseSemester>();
		while (classSemester.hasNext()) {
			classCourseSemesters.addAll(classSemester.next()
					.getClassCourseSemesters());
		}
		Iterator<ClassCourseSemester> classCourseSemester = classCourseSemesters
				.iterator();
		Set<Timetable> timetable = new LinkedHashSet<Timetable>();
		while (classCourseSemester.hasNext()) {
			timetable.addAll(classCourseSemester.next().getTimetable());
		}
		return timetable;
	}

	@Override
	public Set<Timetable> listTimetablesByClassCourse(int classCourseSemesterId) {
		return classCourseSemesterService.getClassCourseSemesterById(
				classCourseSemesterId, true, false).getTimetable();
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
	public List<Timetable> listTimetablesByClassCourseSemesters(
			Set<ClassCourseSemester> classCourseSemesters) {
		return timetableDAO
				.listTimetablesByClassCourseSemesters(classCourseSemesters);
	}

	@Override
	public List<Timetable> listTimetablesByCCSs(
			List<ClassCourseSemester> classCourseSemesters) {
		return timetableDAO.listTimetablesByCCSs(classCourseSemesters);
	}

	@Override
	public Timetable getTimetableByDateSlotClassCourse(Date date, int slot,
			int classCourseSemesterId) {
		return timetableDAO.getTimetableByDateSlotClassCourse(date, slot,
				classCourseSemesterId);
	}

	@Override
	public Timetable getTimetableByDateSlotClass(Date date, int slot,
			int classSemesterId) {
		return timetableDAO.getTimetableByDateSlotClass(date, slot,
				classSemesterId);
	}

	@Override
	public List<Timetable> listTimetablesByClassCourseSemestersInWeek(
			Set<ClassCourseSemester> classCourseSemesters, Date startWeek,
			Date endWeek) {
		return timetableDAO.listTimetablesByClassCourseSemestersInWeek(
				classCourseSemesters, startWeek, endWeek);
	}

	@Override
	public List<Timetable> listTimetablesByDate(Date date) {
		return timetableDAO.listTimetablesByDate(date);
	}

	@Override
	public List<Timetable> listTimetableByClass(int classSemesterId) {
		List<Timetable> result = new ArrayList<Timetable>();
		ClassSemester classSemester = classSemesterService
				.getClassSemesterById(classSemesterId, true);
		List<ClassCourseSemester> ccss = new ArrayList<ClassCourseSemester>();
		ccss.addAll(classSemester.getClassCourseSemesters());
		result.addAll(timetableDAO.listTimetablesByCCSs(ccss));
		return result;
	}

	@Override
	public List<Timetable> listTimetableByTeacher(int teacherSemesterId) {
		List<Timetable> result = new ArrayList<Timetable>();
		// TeacherSemester teacherSemester =
		// teacherSemesterService.getTeacherSemesterById(teacherSemesterId,
		// false, true);
		// for (Timetable t : teacherSemester.getTimetables()) {
		// Timetable newT = new Timetable();
		// newT.setDate(t.getDate());
		// newT.setSlot(t.getSlot());
		//
		// ClassCourseSemester newCCS =
		// classCourseSemesterService.createNewCCS(t.getClassCourseSemester());
		// newT.setClassCourseSemester(newCCS);
		// newT.setRoom(t.getRoom());
		//
		// TeacherSemester ts = new TeacherSemester();
		// ts.setTeacherSemesterId(teacherSemester.getTeacherSemesterId());
		// Teacher te = new Teacher();
		// te.setTeacherId(teacherSemester.getTeacher().getTeacherId());
		// te.setAccount(teacherSemester.getTeacher().getAccount());
		// ts.setTeacher(te);
		// newT.setTeacherSemester(ts);
		//
		// result.add(newT);
		// }
		result.addAll(timetableDAO.listTimetablesByTeacher(teacherSemesterId));
		return result;
	}

	@Override
	public List<Timetable> listTimetableByStudent(int semesterId, int studentId) {
		List<Timetable> result = new ArrayList<Timetable>();

		List<ClassCourseSemester> classCourseSemesters = classCourseSemesterService
				.listClassCourseSemesterByStudent(semesterId, studentId);
		if (!classCourseSemesters.isEmpty()) {
			result.addAll(timetableDAO
					.listTimetablesByCCSs(classCourseSemesters));
		}
		return result;
	}

	@Override
	public void deleteTimetablesByCCS(int classCourseSemesterId) {
		timetableDAO.deleteTimetablesByCCS(classCourseSemesterId);
	}

	@Override
	public void deleteTimetablesByCCSInWeek(int classSemesterId,
			Date startWeek, Date endWeek) {
		timetableDAO.deleteTimetablesByCCSInWeek(classSemesterId, startWeek,
				endWeek);
	}

	@Override
	public List<Timetable> listTimetablesByClassAndCourseCode(int semesterId,
			String classCode, String courseCode) {
		return timetableDAO.listTimetablesByClassAndCourseCode(semesterId,
				classCode, courseCode);
	}

	@Override
	public int deleteTimetablesBySemester(int semesterId) {
		return timetableDAO.deleteTimetablesBySemester(semesterId);
	}
}
