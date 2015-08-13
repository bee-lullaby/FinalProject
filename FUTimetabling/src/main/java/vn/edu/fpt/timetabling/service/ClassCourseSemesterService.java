package vn.edu.fpt.timetabling.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import vn.edu.fpt.timetabling.model.ClassCourseSemester;

public interface ClassCourseSemesterService {
	public void addClassCourseSemester(ClassCourseSemester classCourseSemester);

	public HashMap<String, List<String>> addClassCourseSemesterFromFile(File classCourses, int semesterId)
			throws IOException;

	public void updateClassCourseSemester(ClassCourseSemester classCourseSemester);

	public List<ClassCourseSemester> listClassCourseSemesters(boolean jointTimetable,
			boolean jointClassCourseStudentSemesters);

	public List<ClassCourseSemester> listClassCourseSemesterByClass(int classSemesterId, boolean jointTimetable,
			boolean jointClassCourseStudentSemesters);

	public List<ClassCourseSemester> listClassCourseSemesterByCourse(int courseSemesterId, boolean jointTimetable,
			boolean jointClassCourseStudentSemesters);

	public List<ClassCourseSemester> listClassCourseSemesterBySemester(int semesterId, boolean jointTimetable,
			boolean jointClassCourseStudentSemesters);

	public List<ClassCourseSemester> listClassCourseSemesterByStudent(int semesterId, int studentId);

	public ClassCourseSemester getClassCourseSemesterById(int classCourseSemesterId, boolean jointTimetable,
			boolean jointClassCourseStudentSemesters);

	public ClassCourseSemester getClassCourseSemesterByClassAndCourseSemester(int classSemesterId, int courseSemesterId,
			boolean jointTimetable, boolean jointClassCourseStudentSemesters);

	public void deleteClassCourseSemester(int classCourseSemesterId);

	public long getNumberOfStudents(int classCourseSemesterId);

	public int deleteClassCourseSemesters(int semesterId);

	public ClassCourseSemester createNewCCS(ClassCourseSemester ccs);
}
