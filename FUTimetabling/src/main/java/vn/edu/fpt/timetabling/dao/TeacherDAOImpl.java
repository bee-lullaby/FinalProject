package vn.edu.fpt.timetabling.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import vn.edu.fpt.timetabling.model.Teacher;

@Repository
public class TeacherDAOImpl implements TeacherDAO {
	
	private static final Logger logger = LoggerFactory
			.getLogger(TeacherDAOImpl.class);
	
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addTeacher(Teacher teacher) {
		getCurrentSession().persist(teacher);
		logger.info("Teacher saved successfully, teacher details=" + teacher);
	}

	@Override
	public void updateTeacher(Teacher teacher) {
		getCurrentSession().update(teacher);
		logger.info("Teacher updated successfully, teacher details=" + teacher);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Teacher> listTeachers() {
		List<Teacher> teachers = (List<Teacher>) getCurrentSession().createQuery(
				"FROM vn.edu.fpt.timetabling.model.Teacher").list();
		for (Teacher teacherTemp : teachers) {
			logger.info("Teacher list:" + teacherTemp);
		}
		return teachers;
	}

	@Override
	public Teacher getTeacherById(int teacherId) {
		Teacher teacher = (Teacher) getCurrentSession().load(Teacher.class,
				new Integer(teacherId));
		logger.info("Teacher loaded successfully, teacher details=" + teacher);
		return teacher;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Teacher getTeacherByEmail(String email) {
		String hql = "FROM vn.edu.fpt.timetabling.model.Teacher S WHERE S.email = :email";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("email", email);
		List<Teacher> teacher = (List<Teacher>) query.list();
		if (teacher != null && !teacher.isEmpty()) {
			return teacher.get(0);
		} else {
			return null;
		}
	}

	@Override
	public void deleteTeacher(int teacherId) {
		Teacher teacher = getTeacherById(teacherId);
		if (teacher != null) {
			getCurrentSession().delete(teacher);
		}
		logger.info("Teacher deleted successfully, teacher details=" + teacher);
	}

}
