package vn.edu.fpt.timetabling.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.fpt.timetabling.dao.CourseDAO;
import vn.edu.fpt.timetabling.model.Course;

@Service
public class CourseServiceImpl implements CourseService {

	private CourseDAO courseDAO;

	public void setCourseDAO(CourseDAO courseDAO) {
		this.courseDAO = courseDAO;
	}

	@Override
	@Transactional
	public void addCourse(Course course) {
		courseDAO.addCourse(course);
	}

	@Override
	@Transactional
	public void updateCourse(Course course) {
		courseDAO.updateCourse(course);
	}

	@Override
	@Transactional
	public List<Course> listCourses() {
		return courseDAO.listCourses();
	}

	@Override
	@Transactional
	public Course getCourseById(int courseId) {
		return courseDAO.getCourseById(courseId);
	}

	@Override
	@Transactional
	public void deleteCourse(int courseId) {
		courseDAO.deleteCourse(courseId);
	}
}
