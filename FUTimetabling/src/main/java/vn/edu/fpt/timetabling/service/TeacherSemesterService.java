package vn.edu.fpt.timetabling.service;

import java.util.List;

import vn.edu.fpt.timetabling.model.TeacherSemester;

public interface TeacherSemesterService {
	public void addTeacherSemester(TeacherSemester teacherSemester);

	public void updateTeacherSemester(TeacherSemester teacherSemester);

	public List<TeacherSemester> listTeacherSemesters();

	public TeacherSemester getTeacherSemesterById(int teacherSemesterId);

	public void deleteTeacherSemester(int teacherSemesterId);

	public TeacherSemester getTeacherSemesterByAccount(String account);
}
