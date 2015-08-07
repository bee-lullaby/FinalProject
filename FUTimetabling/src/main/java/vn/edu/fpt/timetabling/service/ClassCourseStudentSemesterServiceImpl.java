package vn.edu.fpt.timetabling.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.fpt.timetabling.dao.ClassCourseStudentSemesterDAO;
import vn.edu.fpt.timetabling.model.ClassCourseStudentSemester;

@Service
@Transactional(rollbackFor = Exception.class)
public class ClassCourseStudentSemesterServiceImpl implements ClassCourseStudentSemesterService {
	private ClassCourseStudentSemesterDAO classCourseStudentSemesterDAO;

	public void setClassCourseStudentSemesterDAO(ClassCourseStudentSemesterDAO classCourseStudentSemesterDAO) {
		this.classCourseStudentSemesterDAO = classCourseStudentSemesterDAO;
	}

	@Override
	public void addClassCourseStudentSemester(ClassCourseStudentSemester classCourseStudentSemester) {
		classCourseStudentSemesterDAO.addClassCourseStudentSemester(classCourseStudentSemester);
	}

	@Override
	public void updateClassCourseStudentSemester(ClassCourseStudentSemester classCourseStudentSemester) {
		classCourseStudentSemesterDAO.updateClassCourseStudentSemester(classCourseStudentSemester);
	}

	@Override
	public List<ClassCourseStudentSemester> listClassCourseStudentSemesters() {
		return classCourseStudentSemesterDAO.listClassCourseStudentSemesters();
	}

	@Override
	public ClassCourseStudentSemester getClassCourseStudentSemesterById(int classCourseStudentSemesterId) {
		return classCourseStudentSemesterDAO.getClassCourseStudentSemesterById(classCourseStudentSemesterId);
	}

	@Override
	public void deleteClassCourseStudentSemester(int classCourseStudentSemesterId) {
		classCourseStudentSemesterDAO.deleteClassCourseStudentSemester(classCourseStudentSemesterId);
	}

	@Override
	public int removeStudentFromClassCourseSemester(int studentId, int classCourseSemesterId) {
		return classCourseStudentSemesterDAO.removeStudentFromClassCourseSemester(studentId, classCourseSemesterId);
	}

	@Override
	public int deleteClassCourseStudentSemesters(int semesterId) {
		return classCourseStudentSemesterDAO.deleteClassCourseStudentSemesters(semesterId);
	}

	@Override
	public int deleteClassCourseStudentSemesterByClass(int classSemesterId) {
		return classCourseStudentSemesterDAO.deleteClassCourseStudentSemesterByClass(classSemesterId);
	}
}
