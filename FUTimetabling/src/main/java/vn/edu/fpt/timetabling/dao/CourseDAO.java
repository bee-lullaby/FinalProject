package vn.edu.fpt.timetabling.dao;

import java.util.List;

import vn.edu.fpt.timetabling.model.Course;

public interface CourseDAO {
	public void addCourse(Course course);
	
	public void updateCourse(Course course);

	public List<Course> listCourses();

	public Course getCourseById(int courseId);
	
	public Course getCourseByCode(String code);

	public void deleteCourse(int courseId);
}
