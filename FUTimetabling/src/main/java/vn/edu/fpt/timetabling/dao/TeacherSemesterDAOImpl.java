package vn.edu.fpt.timetabling.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import vn.edu.fpt.timetabling.model.TeacherSemester;

@Repository
public class TeacherSemesterDAOImpl implements TeacherSemesterDAO {
	private static final Logger logger = LoggerFactory.getLogger(TeacherSemesterDAO.class);

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
		logger.info("TeacherSemester was saved successfully, TeacherSemester details=" + teacherSemester);
	}

	@Override
	public void updateTeacherSemester(TeacherSemester teacherSemester) {
		getCurrentSession().update(teacherSemester);
		logger.info("TeacherSemester was updated successfully, TeacherSemester details=" + teacherSemester);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TeacherSemester> listTeacherSemesters() {
		String hql = "FROM vn.edu.fpt.timetabling.model.TeacherSemester"
				+ " T LEFT OUTER JOIN FETCH T.teacherCourseSemester" + "   LEFT OUTER JOIN FETCH T.timetable";
		Query query = getCurrentSession().createQuery(hql);
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<TeacherSemester> teacherSemesters = (List<TeacherSemester>) query.list();
		for (TeacherSemester teacherSemester : teacherSemesters) {
			logger.info("TeacherSemester list:" + teacherSemester);
		}
		return teacherSemesters;
	}

	@Override
	public TeacherSemester getTeacherSemesterById(int teacherSemesterId) {
		String hql = "FROM vn.edu.fpt.timetabling.model.Specialized"
				+ " T LEFT OUTER JOIN FETCH T.teacherCourseSemester" + "   LEFT OUTER JOIN FETCH T.timetable"
				+ " WHERE T.teacherSemesterId = :teacherSemesterId";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("teacherSemesterId", teacherSemesterId);
		Object temp = query.uniqueResult();
		if (temp != null) {
			TeacherSemester teacherSemester = (TeacherSemester) temp;
			logger.info("TeacherSemester was loaded successfully, TeacherSemester=" + teacherSemester);
			return teacherSemester;
		} else {
			return null;
		}
	}

	@Override
	public TeacherSemester getTeacherSemesterByAccount(String account) {
		String hql = "FROM vn.edu.fpt.timetabling.model.TeacherSemester TS" + " WHERE TS.teacher.account = :account";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("account", account);
		Object temp = query.uniqueResult();
		if (temp != null) {
			TeacherSemester teacherSemester = (TeacherSemester) temp;
			logger.info("TeacherSemester was loaded successfully, TeacherSemester=" + teacherSemester);
			return teacherSemester;
		} else {
			return null;
		}
	}

	@Override
	public void deleteTeacherSemester(int teacherSemesterId) {
		TeacherSemester teacherSemester = getTeacherSemesterById(teacherSemesterId);
		if (teacherSemester != null) {
			getCurrentSession().delete(teacherSemester);
			logger.info("TeacherSemester was deleted successfully, TeacherSemester details=" + teacherSemester);
		}
	}
}
