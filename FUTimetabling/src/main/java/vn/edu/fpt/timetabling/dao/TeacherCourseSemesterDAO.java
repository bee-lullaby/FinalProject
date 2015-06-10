package vn.edu.fpt.timetabling.dao;

import java.util.List;

import vn.edu.fpt.timetabling.model.TeacherCourseSemester;

public interface TeacherCourseSemesterDAO {
	public void addTeacherCourseSemester(TeacherCourseSemester teacherCourseSemester);

	public void updateTeacherCourseSemester(TeacherCourseSemester teacherCourseSemester);

	public List<TeacherCourseSemester> listTeacherCourseSemesters();

	public TeacherCourseSemester getTeacherCourseSemesterById(int teacherCourseSemesterId);

	public void deleteTeacherCourseSemester(int teacherCourseSemesterId);
}
