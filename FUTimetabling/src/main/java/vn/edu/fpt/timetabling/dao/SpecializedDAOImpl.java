package vn.edu.fpt.timetabling.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import vn.edu.fpt.timetabling.model.Specialized;

@Repository
public class SpecializedDAOImpl implements SpecializedDAO {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addSpecialized(Specialized specialized) {
		getCurrentSession().persist(specialized);
	}

	@Override
	public void updateSpecialized(Specialized specialized) {
		getCurrentSession().update(specialized);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Specialized> listSpecializeds(boolean jointClasses, boolean jointStudents) {
		String hql = "FROM vn.edu.fpt.timetabling.model.Specialized S";
		if (jointClasses) {
			hql += " LEFT OUTER JOIN FETCH S.classes";
		}
		if (jointStudents) {
			hql += " LEFT OUTER JOIN FETCH S.students";
		}
		hql += " WHERE S.detailSpecialized IS FALSE";
		Query query = getCurrentSession().createQuery(hql);
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Specialized> specializeds = (List<Specialized>) query.list();
		return specializeds;
	}

	@Override
	public Specialized getSpecializedById(int specializedId, boolean jointClasses, boolean jointStudents) {
		String hql = "FROM vn.edu.fpt.timetabling.model.Specialized S";
		if (jointClasses) {
			hql += " LEFT OUTER JOIN FETCH S.classes";
		}
		if (jointStudents) {
			hql += " LEFT OUTER JOIN FETCH S.students";
		}
		hql += " WHERE S.specializedId = :specializedId";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("specializedId", specializedId);
		return (Specialized) query.uniqueResult();
	}

	@Override
	public Specialized getSpecializedByCode(String code, boolean jointClasses, boolean jointStudents) {
		String hql = "FROM vn.edu.fpt.timetabling.model.Specialized S";
		if (jointClasses) {
			hql += " LEFT OUTER JOIN FETCH S.classes";
		}
		if (jointStudents) {
			hql += " LEFT OUTER JOIN FETCH S.students";
		}
		hql += " WHERE S.code = :code";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("code", code);
		return (Specialized) query.uniqueResult();
	}

	@Override
	public void deleteSpecialized(int specializedId) {
		Specialized specialized = getSpecializedById(specializedId, false, false);
		getCurrentSession().delete(specialized);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Specialized> listDetailSpecializeds(boolean jointClasses, boolean jointStudents) {
		String hql = "FROM vn.edu.fpt.timetabling.model.Specialized S";
		if (jointClasses) {
			hql += " LEFT OUTER JOIN FETCH S.classes";
		}
		if (jointStudents) {
			hql += " LEFT OUTER JOIN FETCH S.students";
		}
		hql += " WHERE S.detailSpecialized IS TRUE";
		Query query = getCurrentSession().createQuery(hql);
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Specialized> specializeds = (List<Specialized>) query.list();
		return specializeds;
	}
}
