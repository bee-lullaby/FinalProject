package vn.edu.fpt.timetabling.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vn.edu.fpt.timetabling.model.TeacherCourseSemester;

public class TeacherCourseSemesterDAOImpl implements TeacherCourseSemesterDAO {
	private static final Logger logger = LoggerFactory
			.getLogger(TeacherCourseSemesterDAO.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addTeacherCourseSemester(
			TeacherCourseSemester teacherCourseSemester) {
		getCurrentSession().persist(teacherCourseSemester);
		logger.info("TeacherCourseSemester was saved successfully, TeacherCourseSemester details="
				+ teacherCourseSemester);
	}

	@Override
	public void updateTeacherCourseSemester(
			TeacherCourseSemester teacherCourseSemester) {
		getCurrentSession().update(teacherCourseSemester);
		logger.info("TeacherCourseSemester was updated successfully, TeacherCourseSemester details="
				+ teacherCourseSemester);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TeacherCourseSemester> listTeacherCourseSemesters() {
		List<TeacherCourseSemester> teacherCourseSemesters = (List<TeacherCourseSemester>) getCurrentSession()
				.createQuery(
						"from vn.edu.fpt.timetabling.model.TeacherSemester")
				.list();
		for (TeacherCourseSemester teacherCourseSemester : teacherCourseSemesters) {
			logger.info("TeacherCourseSemester list:" + teacherCourseSemester);
		}
		return teacherCourseSemesters;
	}

	@Override
	public TeacherCourseSemester getTeacherCourseSemesterById(
			int teacherCourseSemesterId) {
		TeacherCourseSemester teacherCourseSemester = (TeacherCourseSemester) getCurrentSession()
				.get(TeacherCourseSemester.class,
						new Integer(teacherCourseSemesterId));
		if (teacherCourseSemester != null) {
			logger.info("TeacherCourseSemester was loaded successfully, TeacherCourseSemester details="
					+ teacherCourseSemester);
		}
		return teacherCourseSemester;
	}

	@Override
	public void deleteTeacherCourseSemester(int teacherCourseSemesterId) {
		TeacherCourseSemester teacherCourseSemester = getTeacherCourseSemesterById(teacherCourseSemesterId);
		if (teacherCourseSemester != null) {
			getCurrentSession().delete(teacherCourseSemester);
			logger.info("TeacherCourseSemester was deleted successfully, TeacherCourseSemester details="
					+ teacherCourseSemester);
		}
	}
}