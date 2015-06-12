package vn.edu.fpt.timetabling.dao;

import java.util.List;

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
		List<Semester> semesters = (List<Semester>) getCurrentSession()
				.createQuery("from vn.edu.fpt.timetabling.model.Semester")
				.list();
		for (Semester semester : semesters) {
			logger.info("Semester list:" + semester);
		}
		return semesters;
	}

	@Override
	public Semester getSemesterById(int semesterId) {
		Semester semester = (Semester) getCurrentSession().get(Semester.class,
				new Integer(semesterId));
		if (semester != null) {
			logger.info("Semester was loaded successfully, semester details="
					+ semester);
		}
		return semester;
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
