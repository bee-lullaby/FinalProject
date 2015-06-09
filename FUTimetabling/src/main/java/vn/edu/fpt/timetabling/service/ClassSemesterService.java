package vn.edu.fpt.timetabling.service;

import java.util.List;

import vn.edu.fpt.timetabling.model.ClassSemester;

public interface ClassSemesterService {
	public void addClassSemester(ClassSemester classSemester);

	public void updateClassSemester(ClassSemester classSemester);

	public List<ClassSemester> listClassSemesters();

	public ClassSemester getClassSemesterById(int classSemesterId);

	public void deleteClassSemester(int classSemesterId);
}
