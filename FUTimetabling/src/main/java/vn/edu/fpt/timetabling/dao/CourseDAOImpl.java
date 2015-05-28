package vn.edu.fpt.timetabling.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import vn.edu.fpt.timetabling.model.Course;

@Repository
public class CourseDAOImpl implements CourseDAO {

	private static final Logger logger = LoggerFactory
			.getLogger(CourseDAOImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addCourse(Course course) {
		getCurrentSession().persist(course);
		logger.info("Course saved successfully, course details=" + course);
	}

	@Override
	public void updateCourse(Course course) {
		getCurrentSession().update(course);
		logger.info("Course updated successfully, course details=" + course);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> listCourses() {
		List<Course> courses = (List<Course>) getCurrentSession().createQuery(
				"from vn.edu.fpt.timetabling.model.Course").list();
		for (Course course : courses) {
			logger.info("Course list::" + course);
		}
		return courses;
	}

	@Override
	public Course getCourseById(int courseId) {
		Course course = (Course) getCurrentSession().load(Course.class,
				new Integer(courseId));
		logger.info("Course loaded successfully, course details=" + course);
		return course;
	}

	@Override
	public void deleteCourse(int courseId) {
		Course course = getCourseById(courseId);
		if (course != null) {
			getCurrentSession().delete(course);
		}
		logger.info("Course deleted successfully, course details=" + course);
	}
}
