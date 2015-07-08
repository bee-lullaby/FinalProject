package vn.edu.fpt.timetabling.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.fpt.timetabling.dao.ClassCourseStudentSemesterDAO;
import vn.edu.fpt.timetabling.model.ClassCourseStudentSemester;

@Service
public class ClassCourseStudentSemesterServiceImpl implements ClassCourseStudentSemesterService {

	private ClassCourseStudentSemesterDAO classCourseStudentSemesterDAO;

	public void setClassCourseStudentSemesterDAO(ClassCourseStudentSemesterDAO classCourseStudentSemesterDAO) {
		this.classCourseStudentSemesterDAO = classCourseStudentSemesterDAO;
	}

	@Override
	@Transactional
	public void addClassCourseStudentSemester(ClassCourseStudentSemester classCourseStudentSemester) {
		classCourseStudentSemesterDAO.addClassCourseStudentSemester(classCourseStudentSemester);
	}

	@Override
	@Transactional
	public void updateClassCourseStudentSemester(ClassCourseStudentSemester classCourseStudentSemester) {
		classCourseStudentSemesterDAO.updateClassCourseStudentSemester(classCourseStudentSemester);
	}

	@Override
	@Transactional
	public List<ClassCourseStudentSemester> listClassCourseStudentSemesters() {
		return classCourseStudentSemesterDAO.listClassCourseStudentSemesters();
	}

	@Override
	@Transactional
	public ClassCourseStudentSemester getClassCourseStudentSemesterById(int classCourseStudentSemesterId) {
		return classCourseStudentSemesterDAO.getClassCourseStudentSemesterById(classCourseStudentSemesterId);
	}

	@Override
	@Transactional
	public void deleteClassCourseStudentSemester(int classCourseStudentSemesterId) {
		classCourseStudentSemesterDAO.deleteClassCourseStudentSemester(classCourseStudentSemesterId);
	}

	@Override
	@Transactional
	public void removeStudentFromClassCourseSemester(int studentId, int classCourseSemesterId) {
		classCourseStudentSemesterDAO.removeStudentFromClassCourseSemester(studentId, classCourseSemesterId);
	}

}
