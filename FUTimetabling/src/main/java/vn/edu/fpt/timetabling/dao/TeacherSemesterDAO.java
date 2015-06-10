package vn.edu.fpt.timetabling.dao;

import java.util.List;

import vn.edu.fpt.timetabling.model.TeacherSemester;

public interface TeacherSemesterDAO {
	public void addTeacherSemester(TeacherSemester teacherSemester);

	public void updateTeacherSemester(TeacherSemester teacherSemester);

	public List<TeacherSemester> listTeacherSemesters();

	public TeacherSemester getTeacherSemesterById(int teacherSemesterId);

	public void deleteTeacherSemester(int teacherSemesterId);
}
