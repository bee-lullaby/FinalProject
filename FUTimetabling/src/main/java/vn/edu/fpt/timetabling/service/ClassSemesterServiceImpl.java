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
		classSemesterDAO.addClassSemester(classSemester);
	}

	@Override
	@Transactional
	public void updateClassSemester(ClassSemester classSemester) {
		classSemesterDAO.updateClassSemester(classSemester);
	}

	@Override
	@Transactional
	public List<ClassSemester> listClassSemesters(boolean jointClassCourseSemester) {
		return classSemesterDAO.listClassSemesters(jointClassCourseSemester);
	}

	@Override
	@Transactional
	public ClassSemester getClassSemesterById(int classSemesterId, boolean jointClassCourseSemester) {
		return classSemesterDAO.getClassSemesterById(classSemesterId, jointClassCourseSemester);
	}

	@Override
	@Transactional
	public ClassSemester getClassSemesterByClassSemester(int semesterId, int classId,
			boolean jointClassCourseSemester) {
		return classSemesterDAO.getClassSemesterByClassSemester(semesterId, classId, jointClassCourseSemester);
	}

	@Override
	@Transactional
	public ClassSemester getClassSemesterByCode(String classCode, int semesterId, boolean jointClassCourseSemester) {
		return classSemesterDAO.getClassSemesterByCode(classCode, semesterId, jointClassCourseSemester);
	}

	@Override
	@Transactional
	public void deleteClassSemester(int classSemesterId) {
		classSemesterDAO.deleteClassSemester(classSemesterId);
	}

	@Override
	@Transactional
	public long getNumberOfStudents(int classSemesterId) {
		return classSemesterDAO.getNumberOfStudents(classSemesterId);
	}

	@Override
	@Transactional
	public void autoPutStudentsIntoClassSemester(int classSemesterId) {
		classSemesterDAO.autoPutStudentsIntoClassSemester(classSemesterId);
	}

	@Override
	@Transactional
	public List<ClassSemester> listClassSemestersBySpecializedSemester(int semesterId, int specializedId,
			int detailSpecializedId, int semesterNumber) {
		return classSemesterDAO.listClassSemestersBySpecializedSemester(semesterId, specializedId, detailSpecializedId,
				semesterNumber);
	}

	@Override
	@Transactional
	public void autoPutStudentsIntoClassSemesters(int semesterId) {
		classSemesterDAO.autoPutStudentsIntoClassSemesters(semesterId);
	}

	@Override
	@Transactional
	public boolean isStudentInClassSemester(int studentId, int classSemesterId) {
		return classSemesterDAO.isStudentInClassSemester(studentId, classSemesterId);
	}

}
