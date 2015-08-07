package vn.edu.fpt.timetabling.dao;

import java.util.List;

import vn.edu.fpt.timetabling.model.ClassCourseStudentSemester;

public interface ClassCourseStudentSemesterDAO {
	public void addClassCourseStudentSemester(ClassCourseStudentSemester classCourseStudentSemester);

	public void updateClassCourseStudentSemester(ClassCourseStudentSemester classCourseStudentSemester);

	public List<ClassCourseStudentSemester> listClassCourseStudentSemesters();

	public ClassCourseStudentSemester getClassCourseStudentSemesterById(int classCourseStudentSemesterId);
	
	public void deleteClassCourseStudentSemester(int classCourseStudentSemesterId);

	public int removeStudentFromClassCourseSemester(int studentId, int classCourseSemesterId);
	
	public int deleteClassCourseStudentSemesters(int semesterId);
}
