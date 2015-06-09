package vn.edu.fpt.timetabling.service;

import java.util.List;

import vn.edu.fpt.timetabling.model.Teacher;

public interface TeacherService {
	public void addTeacher(Teacher teacher);

	public void updateTeacher(Teacher teacher);;

	public List<Teacher> listTeachers();

	public Teacher getTeacherById(int teacherId);

	public Teacher getTeacherByEmail(String email);

	public void deleteTeacher(int teacherId);
}
