package vn.edu.fpt.timetabling.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import vn.edu.fpt.timetabling.model.Teacher;

@Repository
public class TeacherDAOImpl implements TeacherDAO {
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
	}

	@Override
	public void updateTeacher(Teacher teacher) {
		getCurrentSession().update(teacher);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Teacher> listTeachers() {
		List<Teacher> teachers = (List<Teacher>) getCurrentSession()
				.createQuery("FROM vn.edu.fpt.timetabling.model.Teacher").list();
		return teachers;
	}

	@Override
	public Teacher getTeacherById(int teacherId) {
		Teacher teacher = (Teacher) getCurrentSession().get(Teacher.class, new Integer(teacherId));
		return teacher;
	}

	@Override
	public Teacher getTeacherByEmail(String email) {
		String hql = "FROM vn.edu.fpt.timetabling.model.Teacher S WHERE S.email = :email";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("email", email);
		return (Teacher) query.uniqueResult();
	}

	@Override
	public Teacher getTeacherByAccount(String account) {
		String hql = "FROM vn.edu.fpt.timetabling.model.Teacher S WHERE S.account = :account";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("account", account);
		return (Teacher) query.uniqueResult();
	}

	@Override
	public void deleteTeacher(int teacherId) {
		Teacher teacher = getTeacherById(teacherId);
		getCurrentSession().delete(teacher);
	}
}
