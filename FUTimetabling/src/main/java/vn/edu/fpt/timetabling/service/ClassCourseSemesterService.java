package vn.edu.fpt.timetabling.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import vn.edu.fpt.timetabling.model.ClassCourseSemester;

public interface ClassCourseSemesterService {
	public void addClassCourseSemester(ClassCourseSemester classCourseSemester);

	public void addClassCourseSemesterFromFile(File classCourses, int semesterId) throws IOException;

	public void updateClassCourseSemester(ClassCourseSemester classCourseSemester);

	public List<ClassCourseSemester> listClassCourseSemesters(boolean jointTimetable, boolean jointClassCourseStudentSemesters);

	public List<ClassCourseSemester> listClassCourseSemesterBySemester(int semesterId, boolean jointTimetable, boolean jointClassCourseStudentSemesters);

	public ClassCourseSemester getClassCourseSemesterById(int classCourseSemesterId, boolean jointTimetable, boolean jointClassCourseStudentSemesters);

	public void deleteClassCourseSemester(int classCourseSemesterId);

	public long getNumberOfStudents(int classCourseSemesterId);
}
