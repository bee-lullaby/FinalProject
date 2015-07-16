package vn.edu.fpt.timetabling.dao;

import java.util.List;

import vn.edu.fpt.timetabling.model.ClassCourseSemester;

public interface ClassCourseSemesterDAO {
	public void addClassCourseSemester(ClassCourseSemester classCourseSemester);

	public void updateClassCourseSemester(ClassCourseSemester classCourseSemester);

	public List<ClassCourseSemester> listClassCourseSemesters();

	public List<ClassCourseSemester> listClassCourseSemesterByClass(int classSemesterId);

	public List<ClassCourseSemester> listClassCourseSemesterByCourse(int courseSemesterId);

	public List<ClassCourseSemester> listClassCourseSemesterBySemester(int semesterId);

	public List<ClassCourseSemester> listClassCourseSemesterByStudent(int semesterId, int studentId);

	public ClassCourseSemester getClassCourseSemesterById(int classCourseSemesterId);

	public void deleteClassCourseSemester(int classCourseSemesterId);

	public long getNumberOfStudents(int classCourseSemesterId);
}
