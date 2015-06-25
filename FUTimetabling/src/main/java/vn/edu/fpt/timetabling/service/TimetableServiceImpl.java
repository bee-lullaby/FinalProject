package vn.edu.fpt.timetabling.service;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.edu.fpt.timetabling.dao.ClassCourseSemesterDAO;
import vn.edu.fpt.timetabling.dao.ClassSemesterDAO;
import vn.edu.fpt.timetabling.dao.SemesterDAO;
import vn.edu.fpt.timetabling.dao.TimetableDAO;
import vn.edu.fpt.timetabling.model.ClassCourseSemester;
import vn.edu.fpt.timetabling.model.ClassSemester;
import vn.edu.fpt.timetabling.model.Timetable;

@Service
public class TimetableServiceImpl implements TimetableService {

	@Autowired
	private TimetableDAO timetableDAO;

	@Autowired
	private SemesterDAO semesterDAO;

	@Autowired
	private ClassSemesterDAO classSemesterDAO;

	@Autowired
	private ClassCourseSemesterDAO classCourseSemesterDAO;

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
		// TODO Auto-generated method stub
		timetableDAO.addTimetable(timetable);
	}

	@Transactional
	@Override
	public void updateTimetable(Timetable timetable) {
		// TODO Auto-generated method stub
		timetableDAO.updateTimetable(timetable);
	}

	@Transactional
	@Override
	public List<Timetable> listTimetables() {
		// TODO Auto-generated method stub
		return timetableDAO.listTimetables();
	}

	@Transactional
	@Override
	public Set<Timetable> listTimetablesBySemester(int semesterId) {
		// TODO Auto-generated method stub
		Set<ClassSemester> classSemesters = semesterDAO.getSemesterById(
				semesterId).getClassSemesters();
		Iterator<ClassSemester> classSemester = classSemesters.iterator();

		Set<ClassCourseSemester> classCourseSemesters = new LinkedHashSet<ClassCourseSemester>();
		while (classSemester.hasNext()) {
			classCourseSemesters.addAll(classSemester.next()
					.getClassCourseSemester());
		}

		Iterator<ClassCourseSemester> classCourseSemester = classCourseSemesters
				.iterator();

		Set<Timetable> timetable = new LinkedHashSet<Timetable>();
		while (classCourseSemester.hasNext()) {
			timetable.addAll(classCourseSemester.next().getTimetable());
		}

		return timetable;
	}

	@Transactional
	@Override
	public Set<Timetable> listTimetablesByClass(int classId, int semesterId) {
		// TODO Auto-generated method stub
		ClassSemester classSemester = classSemesterDAO
				.getClassSemesterByClassSemester(semesterId, classId);

		Set<ClassCourseSemester> classCourseSemesters = classSemester
				.getClassCourseSemester();
		Iterator<ClassCourseSemester> classCourseSemester = classCourseSemesters
				.iterator();

		Set<Timetable> timetable = new LinkedHashSet<Timetable>();
		while (classCourseSemester.hasNext()) {
			timetable.addAll(classCourseSemester.next().getTimetable());
		}

		return timetable;
	}
	
	@Transactional
	@Override
	public Set<Timetable> listTimetablesByClassCourse(int classCourseSemesterId) {
		// TODO Auto-generated method stub
		return classCourseSemesterDAO.getClassCourseSemesterById(classCourseSemesterId).getTimetable();
	}

	
	@Transactional
	@Override
	public Timetable getTimetableById(int timetableId) {
		// TODO Auto-generated method stub
		return timetableDAO.getTimetableById(timetableId);
	}

	@Transactional
	@Override
	public void deleteTimetable(int timetableId) {
		// TODO Auto-generated method stub
		timetableDAO.deleteTimetable(timetableId);
	}

}
