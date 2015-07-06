package vn.edu.fpt.timetabling.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.fpt.timetabling.dao.ClassSemesterDAO;
import vn.edu.fpt.timetabling.model.ClassSemester;

@Service
public class ClassSemesterServiceImpl implements ClassSemesterService {

	private ClassSemesterDAO classSemesterDAO;

	public void setClassSemesterDAO(ClassSemesterDAO classSemesterDAO) {
		this.classSemesterDAO = classSemesterDAO;
	}

	@Override
	@Transactional
	public void addClassSemester(ClassSemester classSemester) {
		// TODO Auto-generated method stub
		classSemesterDAO.addClassSemester(classSemester);
	}

	@Override
	@Transactional
	public void updateClassSemester(ClassSemester classSemester) {
		// TODO Auto-generated method stub
		classSemesterDAO.updateClassSemester(classSemester);
	}

	@Override
	@Transactional
	public List<ClassSemester> listClassSemesters(
			boolean jointClassCourseSemester) {
		// TODO Auto-generated method stub
		return classSemesterDAO.listClassSemesters(jointClassCourseSemester);
	}

	@Override
	@Transactional
	public ClassSemester getClassSemesterById(int classSemesterId,
			boolean jointClassCourseSemester) {
		// TODO Auto-generated method stub
		return classSemesterDAO.getClassSemesterById(classSemesterId, jointClassCourseSemester);
	}

	@Override
	@Transactional
	public ClassSemester getClassSemesterByClassSemester(int semesterId,
			int classId, boolean jointClassCourseSemester) {
		// TODO Auto-generated method stub
		return classSemesterDAO.getClassSemesterByClassSemester(semesterId, classId, jointClassCourseSemester);
	}

	@Override
	@Transactional
	public ClassSemester getClassSemesterByCode(String classCode,
			int semesterId, boolean jointClassCourseSemester) {
		// TODO Auto-generated method stub
		return classSemesterDAO.getClassSemesterByCode(classCode, semesterId, jointClassCourseSemester);
	}

	@Override
	@Transactional
	public void deleteClassSemester(int classSemesterId) {
		// TODO Auto-generated method stub
		classSemesterDAO.deleteClassSemester(classSemesterId);
	}


}
