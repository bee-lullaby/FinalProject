package com.fpt.academic.dao;

import java.io.File;
import java.util.List;

import com.fpt.academic.model.Course;
import com.fpt.academic.model.Teacher;

public interface TeacherDAO {

	public void addTeacher(Teacher teacher);
	void addListTeacher(File teachers);
	public void updateTeacher(Teacher teacher);
	public void deleteTeacher(Teacher teacher);
	public List<Teacher> list();
	public List<Teacher> listTeachersOfCourse(String code);
	public String display();
	public String displayTeachersOfCourse(String code);
	public Teacher getTeacher(int teacher_id);
}
