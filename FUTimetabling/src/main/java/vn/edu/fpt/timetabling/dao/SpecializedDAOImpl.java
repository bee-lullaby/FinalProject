package vn.edu.fpt.timetabling.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vn.edu.fpt.timetabling.model.Specialized;

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
		List<Specialized> specializeds = (List<Specialized>) getCurrentSession()
				.createQuery("from vn.edu.fpt.timetabling.model.Specialized")
				.list();
		for (Specialized specialized : specializeds) {
			logger.info("Specialized list:" + specialized);
		}
		return specializeds;
	}

	@Override
	public Specialized getSpecializedById(int specializedId) {
		Specialized specialized = (Specialized) getCurrentSession().load(
				Specialized.class, new Integer(specializedId));
		logger.info("Specialized was loaded successfully, specialized="
				+ specialized);
		return specialized;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Specialized getSpecializedByCode(String code) {
		String hql = "FROM vn.edu.fpt.timetabling.model.Specialized S WHERE S.code = :code";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("code", code);
		List<Specialized> specialized = (List<Specialized>) query.list();
		logger.info("Specialized was loaded successfully, specialized="
				+ specialized.get(0));
		return specialized.get(0);
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

}
