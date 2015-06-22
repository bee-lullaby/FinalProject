package vn.edu.fpt.timetabling.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import vn.edu.fpt.timetabling.dao.ClassSemesterDAO;
import vn.edu.fpt.timetabling.model.ClassSemester;

public class ClassSemesterServiceImpl implements ClassSemesterService {

	private ClassSemesterDAO classSemesterDAO;

	public void setClassSemesterDAO(ClassSemesterDAO classSemesterDAO) {
		this.classSemesterDAO = classSemesterDAO;
	}

	@Override
	@Transactional
	public void addClassSemester(ClassSemester classSemester) {
		classSemesterDAO.addClassSemester(classSemester);
	}

	@Override
	@Transactional
	public void updateClassSemester(ClassSemester classSemester) {
		classSemesterDAO.updateClassSemester(classSemester);
	}

	@Override
	@Transactional
	public List<ClassSemester> listClassSemesters() {
		return classSemesterDAO.listClassSemesters();
	}


	@Override
	@Transactional
	public List<ClassSemester> listClassSemesterBySemester(int semesterId) {
		// TODO Auto-generated method stub
		return classSemesterDAO.listClassSemesterBySemester(semesterId);
	}

	@Override
	@Transactional
	public List<ClassSemester> listClassSemesterByClassSemester(int semesterId,
			int classId) {
		// TODO Auto-generated method stub
		return classSemesterDAO.listClassSemesterByClassSemester(semesterId, classId);
	}
	
	@Override
	@Transactional
	public ClassSemester getClassSemesterById(int classSemesterId) {
		return classSemesterDAO.getClassSemesterById(classSemesterId);
	}

	@Override
	@Transactional
	public void deleteClassSemester(int classSemesterId) {
		classSemesterDAO.deleteClassSemester(classSemesterId);
	}


}
