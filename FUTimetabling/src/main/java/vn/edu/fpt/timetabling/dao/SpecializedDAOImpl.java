package vn.edu.fpt.timetabling.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import vn.edu.fpt.timetabling.model.Specialized;

@Repository
public class SpecializedDAOImpl implements SpecializedDAO {
	private static final Logger logger = LoggerFactory
			.getLogger(SpecializedDAOImpl.class);

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
		logger.info("Specialized was saved successfully, specialized="
				+ specialized);
	}

	@Override
	public void updateSpecialized(Specialized specialized) {
		getCurrentSession().update(specialized);
		logger.info("Specialized was saved successfully, specialized="
				+ specialized);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Specialized> listSpecializeds() {
		String hql = "FROM vn.edu.fpt.timetabling.model.Specialized"
				+ " S LEFT OUTER JOIN FETCH S.classes "
				+ "   LEFT OUTER JOIN FETCH S.students"
				+ " WHERE S.detailSpecialized IS FALSE";
		Query query = getCurrentSession().createQuery(hql);
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Specialized> specializeds = (List<Specialized>) query.list();
		for (Specialized specialized : specializeds) {
			logger.info("Specialized list:" + specialized);
		}
		return specializeds;
	}

	@Override
	public Specialized getSpecializedById(int specializedId) {
		String hql = "FROM vn.edu.fpt.timetabling.model.Specialized"
				+ " S LEFT OUTER JOIN FETCH S.classes "
				+ "   LEFT OUTER JOIN FETCH S.students"
				+ " WHERE S.specializedId = :specializedId";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("specializedId", specializedId);
		Object temp = query.uniqueResult();
		if (temp != null) {
			Specialized specialized = (Specialized) temp;
			logger.info("Specialized was loaded successfully, specialized="
					+ specialized);
			return specialized;
		} else {
			return null;
		}
	}

	@Override
	public Specialized getSpecializedByCode(String code) {
		String hql = "FROM vn.edu.fpt.timetabling.model.Specialized"
				+ " S LEFT OUTER JOIN FETCH S.classes "
				+ "   LEFT OUTER JOIN FETCH S.students "
				+ "WHERE S.code = :code";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("code", code);
		Object temp = query.uniqueResult();
		if (temp != null) {
			Specialized specialized = (Specialized) temp;
			logger.info("Specialized was loaded successfully, specialized="
					+ specialized);
			return specialized;
		} else {
			return null;
		}
	}

	@Override
	public void deleteSpecialized(int specializedId) {
		Specialized specialized = getSpecializedById(specializedId);
		if (specialized != null) {
			getCurrentSession().delete(specialized);
			logger.info("Specialized was deleted successfully, specialized="
					+ specialized);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Specialized> listDetailSpecializeds() {
		String hql = "FROM vn.edu.fpt.timetabling.model.Specialized"
				+ " S LEFT OUTER JOIN FETCH S.classes LEFT OUTER JOIN FETCH S.students"
				+ " WHERE S.detailSpecialized IS TRUE";
		Query query = getCurrentSession().createQuery(hql);
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Specialized> specializeds = (List<Specialized>) query.list();
		for (Specialized specialized : specializeds) {
			logger.info("Specialized list:" + specialized);
		}
		return specializeds;
	}

}
