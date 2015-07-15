package vn.edu.fpt.timetabling.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import vn.edu.fpt.timetabling.model.Course;

@Repository
public class CourseDAOImpl implements CourseDAO {
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
	}

	@Override
	public void updateCourse(Course course) {
		getCurrentSession().update(course);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> listCourses() {
		List<Course> courses = (List<Course>) getCurrentSession()
				.createQuery("FROM vn.edu.fpt.timetabling.model.Course").list();
		return courses;
	}

	@Override
	public Course getCourseById(int courseId) {
		Course course = (Course) getCurrentSession().get(Course.class, new Integer(courseId));
		return course;
	}

	@Override
	public Course getCourseByCode(String code) {
		String hql = "FROM vn.edu.fpt.timetabling.model.Course C WHERE C.code = :code";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("code", code);
		return (Course) query.uniqueResult();
	}

	@Override
	public void deleteCourse(int courseId) {
		Course course = getCourseById(courseId);
		getCurrentSession().delete(course);
	}
}
