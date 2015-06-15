package vn.edu.fpt.timetabling.dao;

import java.util.List;

import vn.edu.fpt.timetabling.model.ClassCourseSemester;

public interface ClassCourseSemesterDAO {
	public void addClassCourseSemester(ClassCourseSemester classCourseSemester);

	public void updateClassCourseSemester(ClassCourseSemester classCourseSemester);

	public List<ClassCourseSemester> listClassCourseSemesters();
	
	public List<ClassCourseSemester> listClassCourseSemesterByClass(int classSemesterId);
	
	public List<ClassCourseSemester> listClassCourseSemesterByCourse(int courseSemesterId);
	
	public ClassCourseSemester getClassCourseSemesterById(int classCourseSemesterId);
	
	public void deleteClassCourseSemester(int classCourseSemesterId);
}
