package vn.edu.fpt.timetabling.service;

import java.util.List;

import vn.edu.fpt.timetabling.model.ClassSemester;

public interface ClassSemesterService {
	public void addClassSemester(ClassSemester classSemester);

	public void updateClassSemester(ClassSemester classSemester);

	public List<ClassSemester> listClassSemesters();
	
	public List<ClassSemester> listClassSemesterBySemester(int semesterId);
	
	public ClassSemester getClassSemesterByClassSemester(int semesterId, int classId);
	
	public ClassSemester getClassSemesterById(int classSemesterId);

	public void deleteClassSemester(int classSemesterId);
}
