package vn.edu.fpt.timetabling.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import vn.edu.fpt.timetabling.model.TeacherCourseSemester;

public interface TeacherCourseSemesterService {
	public void addTeacherCourseSemester(TeacherCourseSemester teacherCourseSemester);
	
	public List<String> addTeacherCourseSemesterFromFile(File teacherCourses, int semesterId) throws IOException;

	public void updateTeacherCourseSemester(TeacherCourseSemester teacherCourseSemester);

	public List<TeacherCourseSemester> listTeacherCourseSemesters();
	
	public List<TeacherCourseSemester> listTeacherCourseSemestersByCourse(int courseSemesterId);

	public List<TeacherCourseSemester> listTeacherCourseSemestersByTeacher(int teacherSemesterId);

	public TeacherCourseSemester getTeacherCourseSemesterById(int teacherCourseSemesterId);
	
	public TeacherCourseSemester getTeacherCourseSemesterByTeacherCourse(int teacherSemesterId, String courseCode);
	
	public void deleteTeacherCourseSemester(int teacherCourseSemesterId);
}
