package vn.edu.fpt.timetabling.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vn.edu.fpt.timetabling.model.ClassSemester;

public class ClassSemesterDAOImpl implements ClassSemesterDAO {
	private static final Logger logger = LoggerFactory
			.getLogger(ClassSemesterDAO.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addClassSemester(ClassSemester classSemester) {
		getCurrentSession().persist(classSemester);
		logger.info("ClassSemester was saved successfully, ClassSemester details="
				+ classSemester);
	}

	@Override
	public void updateClassSemester(ClassSemester classSemester) {
		getCurrentSession().update(classSemester);
		logger.info("ClassSemester was updated successfully, ClassSemester details="
				+ classSemester);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClassSemester> listClassSemesters() {
		List<ClassSemester> classSemesters = (List<ClassSemester>) getCurrentSession()
				.createQuery("from vn.edu.fpt.timetabling.model.ClassSemester")
				.list();
		for (ClassSemester classSemester : classSemesters) {
			logger.info("ClassSemester list:" + classSemester);
		}
		return classSemesters;
	}

	@Override
	public ClassSemester getClassSemesterById(int classSemesterId) {
		ClassSemester classSemester = (ClassSemester) getCurrentSession().load(
				ClassSemester.class, new Integer(classSemesterId));
		logger.info("classSemester was loaded successfully, classSemester details="
				+ classSemester);
		return classSemester;
	}

	@Override
	public void deleteClassSemester(int classSemesterId) {
		ClassSemester classSemester = getClassSemesterById(classSemesterId);
		if (classSemester != null) {
			getCurrentSession().persist(classSemester);
			logger.info("classSemester was deleted successfully, classSemester details="
					+ classSemester);
		}
	}
}
