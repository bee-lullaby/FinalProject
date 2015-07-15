package vn.edu.fpt.timetabling.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import vn.edu.fpt.timetabling.model.ClassFPT;

@Repository
public class ClassDAOImpl implements ClassDAO {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addClass(ClassFPT classFPT) {
		getCurrentSession().persist(classFPT);
	}

	@Override
	public void updateClass(ClassFPT classFPT) {
		getCurrentSession().update(classFPT);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClassFPT> listClasses() {
		List<ClassFPT> classFPTs = (List<ClassFPT>) getCurrentSession()
				.createQuery("FROM vn.edu.fpt.timetabling.model.ClassFPT").list();
		return classFPTs;
	}

	@Override
	public ClassFPT getClassById(int classId) {
		ClassFPT classFPT = (ClassFPT) getCurrentSession().get(ClassFPT.class, new Integer(classId));
		return classFPT;
	}

	@Override
	public ClassFPT getClassByCode(String code) {
		String hql = "FROM vn.edu.fpt.timetabling.model.ClassFPT C WHERE C.code = :code";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("code", code);
		return (ClassFPT) query.uniqueResult();
	}

	@Override
	public void deleteClass(int classId) {
		ClassFPT classFPT = getClassById(classId);
		getCurrentSession().delete(classFPT);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClassFPT> getClassesWithPrefix(String prefix) {
		String hql = "FROM vn.edu.fpt.timetabling.model.ClassFPT C"
				+ " WHERE C.code LIKE :prefix ORDER BY C.number DESC";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("prefix", prefix + "%");
		List<ClassFPT> classFPTs = (List<ClassFPT>) query.list();
		return classFPTs;
	}
}
