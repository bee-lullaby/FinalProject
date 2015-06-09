package vn.edu.fpt.timetabling.service;

import java.io.File;
import java.util.List;

import vn.edu.fpt.timetabling.model.Course;

public interface CourseService {
	public void addCourse(Course course);
	
	public void  addCourseFromFile(File courses); 
	
	public void updateCourse(Course course);

	public List<Course> listCourses();

	public Course getCourseById(int courseId);
	
	public Course getCourseByCode(String code);
	
	public void deleteCourse(int courseId);
}
