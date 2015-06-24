package vn.edu.fpt.timetabling.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.edu.fpt.timetabling.dao.ClassCourseSemesterDAO;
import vn.edu.fpt.timetabling.dao.ClassSemesterDAO;
import vn.edu.fpt.timetabling.dao.TimetableDAO;
import vn.edu.fpt.timetabling.model.ClassCourseSemester;
import vn.edu.fpt.timetabling.model.ClassSemester;
import vn.edu.fpt.timetabling.model.Timetable;

@Service
public class TimetableServiceImpl implements TimetableService {

	@Autowired
	private TimetableDAO timetableDAO;

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
	public List<Timetable> listTimetablesBySemester(int semesterId) {
		// TODO Auto-generated method stub

		List<ClassSemester> classSemesters = classSemesterDAO
				.listClassSemesterBySemester(semesterId);
		List<ClassCourseSemester> classCourseSemesters = new ArrayList<ClassCourseSemester>();
		List<Integer> classCourseSemesterIds = new ArrayList<Integer>();

		for (ClassSemester classSemester : classSemesters) {
			classCourseSemesters.addAll(classCourseSemesterDAO
					.listClassCourseSemesterByClass(classSemester
							.getClassSemesterId()));
		}

		for (ClassCourseSemester classCourseSemester : classCourseSemesters) {
			classCourseSemesterIds.add(classCourseSemester
					.getClassCourseSemesterId());
		}
		return timetableDAO.listTimetablesByCCSId(classCourseSemesterIds);
	}

	@Transactional
	@Override
	public List<Timetable> listTimetablesByClass(int classId, int semesterId) {
		// TODO Auto-generated method stub
		ClassSemester classSemester = classSemesterDAO
				.getClassSemesterByClassSemester(semesterId, classId);
		List<ClassCourseSemester> classCourseSemesters = new ArrayList<ClassCourseSemester>();
		List<Integer> classCourseSemesterIds = new ArrayList<Integer>();

		classCourseSemesters.addAll(classCourseSemesterDAO
				.listClassCourseSemesterByClass(classSemester
						.getClassSemesterId()));

		for (ClassCourseSemester classCourseSemester : classCourseSemesters) {
			classCourseSemesterIds.add(classCourseSemester
					.getClassCourseSemesterId());
		}
		return timetableDAO.listTimetablesByCCSId(classCourseSemesterIds);
	}

	@Transactional
	@Override
	public Timetable getTimetableById(int timetableId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public void deleteTimetable(int timetableId) {
		// TODO Auto-generated method stub

	}

}
