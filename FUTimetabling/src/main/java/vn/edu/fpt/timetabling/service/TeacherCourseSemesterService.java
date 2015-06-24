package vn.edu.fpt.timetabling.service;

import java.io.File;
import java.util.List;

import vn.edu.fpt.timetabling.model.TeacherCourseSemester;

public interface TeacherCourseSemesterService {
	public void addTeacherCourseSemester(
			TeacherCourseSemester teacherCourseSemester);

	public void addTeacherCourseSemesterFromFile(File teacherCourses);

	public void updateTeacherCourseSemester(
			TeacherCourseSemester teacherCourseSemester);

	public List<TeacherCourseSemester> listTeacherCourseSemesters();

	public TeacherCourseSemester getTeacherCourseSemesterById(
			int teacherCourseSemesterId);

	public void deleteTeacherCourseSemester(int teacherCourseSemesterId);
}
