package com.fpt.academic.dao;

import java.io.File;

import com.fpt.academic.model.ClassCourse;

public interface ClassCourseDAO {
	public void addClassCourse(ClassCourse class_course, int semester_id);
	public void addListClassCourse(File class_course, int semester_id);
}
