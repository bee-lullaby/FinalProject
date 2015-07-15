package vn.edu.fpt.timetabling.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import vn.edu.fpt.timetabling.model.TeacherCourseSemester;

public interface TeacherCourseSemesterService {
	public void addTeacherCourseSemester(TeacherCourseSemester teacherCourseSemester);

	public void addTeacherCourseSemesterFromFile(File teacherCourses) throws IOException;

	public void updateTeacherCourseSemester(TeacherCourseSemester teacherCourseSemester);

	public List<TeacherCourseSemester> listTeacherCourseSemesters();

	public TeacherCourseSemester getTeacherCourseSemesterById(int teacherCourseSemesterId);

	public void deleteTeacherCourseSemester(int teacherCourseSemesterId);
}
