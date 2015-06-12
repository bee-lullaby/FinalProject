package vn.edu.fpt.timetabling.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vn.edu.fpt.timetabling.model.ClassFPT;

public class ClassDAOImpl implements ClassDAO {
	private static final Logger logger = LoggerFactory
			.getLogger(ClassDAOImpl.class);

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
		logger.info("Class was saved successfully, class details=" + classFPT);
	}

	@Override
	public void updateClass(ClassFPT classFPT) {
		getCurrentSession().update(classFPT);
		logger.info("Class was updated successfully, class details=" + classFPT);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClassFPT> listClasses() {
		List<ClassFPT> classFPTs = (List<ClassFPT>) getCurrentSession()
				.createQuery("from vn.edu.fpt.timetabling.model.ClassFPT")
				.list();
		for (ClassFPT classFPT : classFPTs) {
			logger.info("Class list:" + classFPT);
		}
		return classFPTs;
	}

	@Override
	public ClassFPT getClassById(int classId) {
		ClassFPT classFPT = (ClassFPT) getCurrentSession().get(ClassFPT.class,
				new Integer(classId));
		if (classFPT != null) {
			logger.info("Class was loaded successfully, class details="
					+ classFPT);
		}
		return classFPT;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ClassFPT getClassByCode(String code) {
		String hql = "FROM vn.edu.fpt.timetabling.model.ClassFPT C WHERE C.code = :code";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("code", code);
		List<ClassFPT> classFPT = (List<ClassFPT>) query.list();
		if (!classFPT.isEmpty()) {
			logger.info("Class was loaded successfully, class details="
					+ classFPT.get(0));
			return classFPT.get(0);
		} else {
			return null;
		}
	}

	@Override
	public void deleteClass(int classId) {
		ClassFPT classFPT = getClassById(classId);
		if (classFPT != null) {
			getCurrentSession().delete(classFPT);
			logger.info("Class was deleted successfully, class details="
					+ classFPT);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClassFPT> getClassesWithPrefix(String prefix) {
		String hql = "FROM vn.edu.fpt.timetabling.model.ClassFPT C"
				+ " WHERE C.code LIKE :prefix ORDER BY C.number DESC";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("prefix", prefix + "%");
		List<ClassFPT> classFPTs = (List<ClassFPT>) query.list();
		for (ClassFPT classFPT : classFPTs) {
			logger.info("Class list:" + classFPT);
		}
		return classFPTs;
	}
}
