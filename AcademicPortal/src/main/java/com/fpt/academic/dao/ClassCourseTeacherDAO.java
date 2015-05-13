package com.fpt.academic.dao;

import java.io.File;
import java.util.List;

import com.fpt.academic.model.ClassCourse;
import com.fpt.academic.model.TeacherCourse;

public interface ClassCourseTeacherDAO {
	public void createClassCourseTeacher(int semester_id);
	public List<ClassCourse> getClassCourse();
	public List<TeacherCourse> getTeacherCourse();
}
