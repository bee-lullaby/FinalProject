package vn.edu.fpt.timetabling.dao;

import java.util.List;

import vn.edu.fpt.timetabling.model.Teacher;

public interface TeacherDAO {
	public void addTeacher(Teacher teacher);

	public void updateTeacher(Teacher teacher);;

	public List<Teacher> listTeachers();

	public Teacher getTeacherById(int teacherId);

	public Teacher getTeacherByEmail(String email);

	public void deleteTeacher(int teacherId);
}
