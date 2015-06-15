package vn.edu.fpt.timetabling.dao;

import java.util.List;

import vn.edu.fpt.timetabling.model.ClassSemester;

public interface ClassSemesterDAO {
	public void addClassSemester(ClassSemester classSemester);

	public void updateClassSemester(ClassSemester classSemester);

	public List<ClassSemester> listClassSemesters();
	
	public List<ClassSemester> listClassSemesterBySemester(int classSemesterId);
	
	public ClassSemester getClassSemesterById(int classSemesterId);
	
	public void deleteClassSemester(int classSemesterId);
}
