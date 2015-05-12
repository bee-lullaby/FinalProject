package com.fpt.academic.dao;

import java.io.File;
import java.util.List;

import com.fpt.academic.model.Course;

public interface CourseDAO {
	public void addCourse(Course course);
	public void addListCourse(File courses);
	public void updateCourse(Course course);
	public void deleteCourse(Course course);
	public List<Course> list();
	public List<Course> listCourseOfClass(String code);
	public String display();
	public String displayCourseOfClass(String code);
}
