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
				+ " WHERE CCSM.classCourseSemesterMergeId = :classCourseSemesterMergeId";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("classCourseSemesterMergeId", classCourseSemesterMergeId);
		return (ClassCourseSemesterMerge) query.uniqueResult();
	}

	@Override
	public ClassCourseSemesterMerge getClassCourseSemesterMergeBy2CCS(int classCourseSemesterId1,
			int classCourseSemesterId2) {
		String hql = "FROM vn.edu.fpt.timetabling.model.ClassCourseSemesterMerge CCSM"
				+ " WHERE CCSM.classCourseSemester1.classCourseSemesterId = :classCourseSemesterId1"
				+ " AND CCSM.classCourseSemester2.classCourseSemesterId = :classCourseSemesterId2";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("classCourseSemesterId1", classCourseSemesterId1);
		query.setParameter("classCourseSemesterId2", classCourseSemesterId2);
		return (ClassCourseSemesterMerge) query.uniqueResult();
	}

	@Override
	public void deleteClassCourseSemesterMerge(int classCourseSemesterMergeId) {
		ClassCourseSemesterMerge classCourseSemesterMerge = getClassCourseSemesterMergeById(classCourseSemesterMergeId);
		getCurrentSession().delete(classCourseSemesterMerge);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClassCourseSemesterMerge> listClassCourseSemesterMerges(int semesterId) {
		String hql = "FROM vn.edu.fpt.timetabling.model.ClassCourseSemesterMerge CCSM"
				+ " WHERE CCSM.classCourseSemester1.classSemester.semester.semesterId = :semesterId";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("semesterId", semesterId);
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<ClassCourseSemesterMerge> classCourseSemesterMerges = (List<ClassCourseSemesterMerge>) query.list();
		return classCourseSemesterMerges;
	}

	@Override
	public int deleteClassCourseSemesterMerges(int semesterId) {
		String hql = "DELETE FROM vn.edu.fpt.timetabling.model.ClassCourseSemesterMerge CCSM"
				+ " WHERE CCSM.classCourseSemester1.classCourseSemesterId IN (SELECT CCS.classCourseSemesterId"
				+ " FROM vn.edu.fpt.timetabling.model.ClassCourseSemester CCS"
				+ " WHERE CCS.classSemester.semester.semesterId = :semesterId)";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("semesterId", semesterId);
		return query.executeUpdate();
	}
}
