package vn.edu.fpt.timetabling.service;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.edu.fpt.timetabling.dao.TimetableDAO;
import vn.edu.fpt.timetabling.model.ClassCourseSemester;
import vn.edu.fpt.timetabling.model.ClassSemester;
import vn.edu.fpt.timetabling.model.Timetable;

@Service
public class TimetableServiceImpl implements TimetableService {

	private TimetableDAO timetableDAO;

	@Autowired
	private SemesterService semesterService;

	@Autowired
	private ClassSemesterService classSemesterService;

	@Autowired
	private ClassCourseSemesterService classCourseSemesterService;

	/**
	 * @param semesterDAO
	 *            the semesterDAO to set
	 */
	public void setTimetableDAO(TimetableDAO timetableDAO) {
		this.timetableDAO = timetableDAO;
	}

	@Transactional
	@Override
	public void addTimetable(Timetable timetable) {
		timetableDAO.addTimetable(timetable);
	}

	@Transactional
	@Override
	public void updateTimetable(Timetable timetable) {
		timetableDAO.updateTimetable(timetable);
	}

	@Transactional
	@Override
	public List<Timetable> listTimetables() {
		return timetableDAO.listTimetables();
	}

	@Transactional
	@Override
	public Set<Timetable> listTimetablesBySemester(int semesterId) {
		Set<ClassSemester> classSemesters = semesterService.getSemesterById(semesterId, true, false, false, false)
				.getClassSemesters();
		Iterator<ClassSemester> classSemester = classSemesters.iterator();

		Set<ClassCourseSemester> classCourseSemesters = new LinkedHashSet<ClassCourseSemester>();
		while (classSemester.hasNext()) {
			classCourseSemesters.addAll(classSemester.next().getClassCourseSemesters());
		}

		Iterator<ClassCourseSemester> classCourseSemester = classCourseSemesters.iterator();

		Set<Timetable> timetable = new LinkedHashSet<Timetable>();
		while (classCourseSemester.hasNext()) {
			timetable.addAll(classCourseSemester.next().getTimetable());
		}

		return timetable;
	}

	@Transactional
	@Override
	public Set<Timetable> listTimetablesByClass(int classId, int semesterId) {
		ClassSemester classSemester = classSemesterService.getClassSemesterByClassSemester(semesterId, classId, true);

		Set<ClassCourseSemester> classCourseSemesters = classSemester.getClassCourseSemesters();
		Iterator<ClassCourseSemester> classCourseSemester = classCourseSemesters.iterator();

		Set<Timetable> timetable = new LinkedHashSet<Timetable>();
		while (classCourseSemester.hasNext()) {
			timetable.addAll(classCourseSemester.next().getTimetable());
		}

		return timetable;
	}

	@Transactional
	@Override
	public Set<Timetable> listTimetablesByClassCourse(int classCourseSemesterId) {
		return classCourseSemesterService.getClassCourseSemesterById(classCourseSemesterId).getTimetable();
	}

	@Transactional
	@Override
	public Timetable getTimetableById(int timetableId) {
		return timetableDAO.getTimetableById(timetableId);
	}

	@Transactional
	@Override
	public void deleteTimetable(int timetableId) {
		timetableDAO.deleteTimetable(timetableId);
	}

	@Override
	@Transactional
	public List<Timetable> listTimetablesByClassCourseSemesters(Set<ClassCourseSemester> classCourseSemesters) {
		return timetableDAO.listTimetablesByClassCourseSemesters(classCourseSemesters);
	}
	
	@Override
	@Transactional
	public List<Timetable> listTimetablesByCCSs(List<ClassCourseSemester> classCourseSemesters) {
		return timetableDAO.listTimetablesByCCSs(classCourseSemesters);
	}
	
	@Override
	@Transactional
	public Timetable getTimetableByDateSlotClassCourse(Date date, int slot, int classCourseSemesterId) {
		return timetableDAO.getTimetableByDateSlotClassCourse(date, slot, classCourseSemesterId);
	}

	@Override
	@Transactional
	public List<Timetable> listTimetablesByClassCourseSemestersInWeek(Set<ClassCourseSemester> classCourseSemesters,
			Date startWeek, Date endWeek) {
		return timetableDAO.listTimetablesByClassCourseSemestersInWeek(classCourseSemesters, startWeek, endWeek);
	}

}
