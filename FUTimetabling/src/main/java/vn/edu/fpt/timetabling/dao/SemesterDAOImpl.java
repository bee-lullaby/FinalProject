package vn.edu.fpt.timetabling.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import vn.edu.fpt.timetabling.model.Semester;

@Repository
public class SemesterDAOImpl implements SemesterDAO {

	private static final Logger logger = LoggerFactory
			.getLogger(SemesterDAOImpl.class);

	private SessionFactory sessionFactory;

	/**
	 * @param sessionFactory
	 *            the sessionFactory to set
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addSemester(Semester semester) {
		getCurrentSession().persist(semester);
		logger.info("Semester was saved successfully, semester details="
				+ semester);
	}

	@Override
	public void updateSemester(Semester semester) {
		getCurrentSession().update(semester);
		logger.info("Semester was updated successfully, semester details="
				+ semester);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Semester> listSemesters() {
		String hql = "FROM vn.edu.fpt.timetabling.model.Semester"
				+ " S LEFT OUTER JOIN FETCH S.classSemesters"
				+ " LEFT OUTER JOIN FETCH S.courseSemesters";
		Query query = getCurrentSession().createQuery(hql);
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Semester> semesters = (List<Semester>) query.list();
		for (Semester semester : semesters) {
			logger.info("Semester list:" + semester);
		}
		return semesters;
	}

	@Override
	public Semester getSemesterById(int semesterId) {
		String hql = "FROM vn.edu.fpt.timetabling.model.Semester"
				+ " S LEFT OUTER JOIN FETCH S.classSemesters"
				+ " LEFT OUTER JOIN FETCH S.courseSemesters"
				+ " WHERE S.semesterId = :semesterId";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("semesterId", semesterId);
		Object temp = query.uniqueResult();
		if (temp != null) {
			Semester semester = (Semester) temp;
			logger.info("Semester was loaded successfully, semester details="
					+ semester);
			return semester;
		} else {
			return null;
		}
	}

	@Override
	public void deleteSemester(int semesterId) {
		Semester semester = getSemesterById(semesterId);
		if (semester != null) {
			getCurrentSession().delete(semester);
			logger.info("semester was deleted successfully, semester details="
					+ semester);
		}
	}

}
