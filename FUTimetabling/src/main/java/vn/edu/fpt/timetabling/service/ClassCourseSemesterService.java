package vn.edu.fpt.timetabling.service;

import java.io.File;
import java.util.List;

import vn.edu.fpt.timetabling.model.ClassCourseSemester;

public interface ClassCourseSemesterService {
	public void addClassCourseSemester(ClassCourseSemester classCourseSemester);

	public void addClassCourseSemesterFromFile(File classCourses, int semesterId);

	public void updateClassCourseSemester(ClassCourseSemester classCourseSemester);

	public List<ClassCourseSemester> listClassCourseSemesters();

	public List<ClassCourseSemester> listClassCourseSemesterBySemester(int semesterId);

	public ClassCourseSemester getClassCourseSemesterById(int classCourseSemesterId);

	public void deleteClassCourseSemester(int classCourseSemesterId);
}
