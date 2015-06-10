package vn.edu.fpt.timetabling.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vn.edu.fpt.timetabling.model.TeacherSemester;

public class TeacherSemesterDAOImpl implements TeacherSemesterDAO {
	private static final Logger logger = LoggerFactory
			.getLogger(TeacherSemesterDAO.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addTeacherSemester(TeacherSemester teacherSemester) {
		getCurrentSession().persist(teacherSemester);
		logger.info("TeacherSemester was saved successfully, TeacherSemester details="
				+ teacherSemester);
	}

	@Override
	public void updateTeacherSemester(TeacherSemester teacherSemester) {
		getCurrentSession().update(teacherSemester);
		logger.info("TeacherSemester was updated successfully, TeacherSemester details="
				+ teacherSemester);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TeacherSemester> listTeacherSemesters() {
		List<TeacherSemester> teacherSemesters = (List<TeacherSemester>) getCurrentSession()
				.createQuery("from vn.edu.fpt.timetabling.model.TeacherSemester")
				.list();
		for (TeacherSemester teacherSemester : teacherSemesters) {
			logger.info("TeacherSemester list:" + teacherSemester);
		}
		return teacherSemesters;
	}

	@Override
	public TeacherSemester getTeacherSemesterById(int teacherSemesterId) {
		TeacherSemester teacherSemester = (TeacherSemester) getCurrentSession().load(
				TeacherSemester.class, new Integer(teacherSemesterId));
		logger.info("TeacherSemester was loaded successfully, TeacherSemester details="
				+ teacherSemester);
		return teacherSemester;
	}

	@Override
	public void deleteTeacherSemester(int teacherSemesterId) {
		TeacherSemester teacherSemester = getTeacherSemesterById(teacherSemesterId);
		if (teacherSemester != null) {
			getCurrentSession().delete(teacherSemester);
			logger.info("TeacherSemester was deleted successfully, TeacherSemester details="
					+ teacherSemester);
		}
	}
}
