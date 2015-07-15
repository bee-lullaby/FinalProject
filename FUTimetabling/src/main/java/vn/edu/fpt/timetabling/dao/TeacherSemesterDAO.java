package vn.edu.fpt.timetabling.dao;

import java.util.List;

import vn.edu.fpt.timetabling.model.TeacherSemester;

public interface TeacherSemesterDAO {
	public void addTeacherSemester(TeacherSemester teacherSemester);

	public void updateTeacherSemester(TeacherSemester teacherSemester);

	public List<TeacherSemester> listTeacherSemesters(boolean jointTeacherCourseSemesters, boolean jointTimetables);

	public TeacherSemester getTeacherSemesterById(int teacherSemesterId, boolean jointTeacherCourseSemesters,
			boolean jointTimetables);

	public TeacherSemester getTeacherSemesterByAccount(String account, boolean jointTeacherCourseSemesters,
			boolean jointTimetables);

	public void deleteTeacherSemester(int teacherSemesterId);
}
