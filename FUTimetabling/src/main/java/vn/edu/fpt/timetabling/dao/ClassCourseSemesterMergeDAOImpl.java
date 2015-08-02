package vn.edu.fpt.timetabling.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import vn.edu.fpt.timetabling.model.ClassCourseSemesterMerge;

@Repository
public class ClassCourseSemesterMergeDAOImpl implements ClassCourseSemesterMergeDAO {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addClassCourseSemesterMerge(ClassCourseSemesterMerge classCourseSemesterMerge) {
		getCurrentSession().persist(classCourseSemesterMerge);
	}

	@Override
	public void updateClassCourseSemesterMerge(ClassCourseSemesterMerge classCourseSemesterMerge) {
		getCurrentSession().update(classCourseSemesterMerge);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClassCourseSemesterMerge> listClassCourseSemesterMerges() {
		String hql = "FROM vn.edu.fpt.timetabling.model.ClassCourseSemesterMerge";
		Query query = getCurrentSession().createQuery(hql);
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<ClassCourseSemesterMerge> classCourseSemesterMerges = (List<ClassCourseSemesterMerge>) query.list();
		return classCourseSemesterMerges;
	}

	@Override
	public ClassCourseSemesterMerge getClassCourseSemesterMergeById(int classCourseSemesterMergeId) {
		String hql = "FROM vn.edu.fpt.timetabling.model.ClassCourseSemesterMerge CCSM"
				+ " WHERE CCSM.classCourseSemesterMergeId = : classCourseSemesterMergeId";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("classCourseSemesterMergeId", classCourseSemesterMergeId);
		return (ClassCourseSemesterMerge) query.uniqueResult();
	}

	@Override
	public void deleteClassCourseSemesterMerge(int classCourseSemesterMergeId) {
		ClassCourseSemesterMerge classCourseSemesterMerge = getClassCourseSemesterMergeById(classCourseSemesterMergeId);
		getCurrentSession().delete(classCourseSemesterMerge);
	}
}
