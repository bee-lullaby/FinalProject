package vn.edu.fpt.timetabling.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vn.edu.fpt.timetabling.model.CourseSemester;

public class CourseSemesterDAOImpl implements CourseSemesterDAO {
	private static final Logger logger = LoggerFactory
			.getLogger(CourseSemesterDAO.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addCourseSemester(CourseSemester courseSemester) {
		getCurrentSession().persist(courseSemester);
		logger.info("CourseSemester was saved successfully, ClassSemester details="
				+ courseSemester);
	}

	@Override
	public void updateCourseSemester(CourseSemester courseSemester) {
		getCurrentSession().update(courseSemester);
		logger.info("CourseSemester was updated successfully, ClassSemester details="
				+ courseSemester);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CourseSemester> listCourseSemesters() {
		List<CourseSemester> courseSemesters = (List<CourseSemester>) getCurrentSession()
				.createQuery("from vn.edu.fpt.timetabling.model.CourseSemester")
				.list();
		for (CourseSemester courseSemester : courseSemesters) {
			logger.info("CourseSemester list:" + courseSemester);
		}
		return courseSemesters;
	}

	@Override
	public CourseSemester getCourseSemesterById(int courseSemesterId) {
		CourseSemester courseSemester = (CourseSemester) getCurrentSession().load(
				CourseSemester.class, new Integer(courseSemesterId));
		logger.info("CourseSemester was loaded successfully, classSemester details="
				+ courseSemester);
		return courseSemester;
	}

	@Override
	public void deleteCourseSemester(int courseSemesterId) {
		CourseSemester courseSemester = getCourseSemesterById(courseSemesterId);
		if (courseSemester != null) {
			getCurrentSession().delete(courseSemester);
			logger.info("CourseSemester was deleted successfully, CourseSemester details="
					+ courseSemester);
		}
	}
}
