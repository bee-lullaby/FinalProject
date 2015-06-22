package vn.edu.fpt.timetabling.service;

import java.io.File;
import java.util.List;

import vn.edu.fpt.timetabling.model.ClassCourseSemester;

public interface ClassCourseSemesterService {
	public void addClassCourseSemester(ClassCourseSemester classCourseSemester);

	public void addClassCourseSemesterFromFile(File courses);
	
	public void updateClassCourseSemester(ClassCourseSemester classCourseSemester);
	
	public List<ClassCourseSemester> listClassCourseSemesters();

	public ClassCourseSemester getClassCourseSemesterById(int classCourseSemesterId);

	public void deleteClassCourseSemester(int classCourseSemesterId);
}
