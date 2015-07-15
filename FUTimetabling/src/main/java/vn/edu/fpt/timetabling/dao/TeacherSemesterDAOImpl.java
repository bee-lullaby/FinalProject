package vn.edu.fpt.timetabling.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import vn.edu.fpt.timetabling.model.TeacherSemester;

@Repository
public class TeacherSemesterDAOImpl implements TeacherSemesterDAO {
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
	}

	@Override
	public void updateTeacherSemester(TeacherSemester teacherSemester) {
		getCurrentSession().update(teacherSemester);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TeacherSemester> listTeacherSemesters(boolean jointTeacherCourseSemesters, boolean jointTimetables) {
		String hql = "FROM vn.edu.fpt.timetabling.model.TeacherSemester TS";
		if (jointTeacherCourseSemesters) {
			hql += " LEFT OUTER JOIN FETCH TS.teacherCourseSemesters";
		}
		if (jointTimetables) {
			hql += " LEFT OUTER JOIN FETCH TS.timetables";
		}
		Query query = getCurrentSession().createQuery(hql);
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<TeacherSemester> teacherSemesters = (List<TeacherSemester>) query.list();
		return teacherSemesters;
	}

	@Override
	public TeacherSemester getTeacherSemesterById(int teacherSemesterId, boolean jointTeacherCourseSemesters,
			boolean jointTimetables) {
		String hql = "FROM vn.edu.fpt.timetabling.model.TeacherSemester TS";
		if (jointTeacherCourseSemesters) {
			hql += " LEFT OUTER JOIN FETCH TS.teacherCourseSemesters";
		}
		if (jointTimetables) {
			hql += " LEFT OUTER JOIN FETCH TS.timetables";
		}
		hql += " WHERE TS.teacherSemesterId = :teacherSemesterId";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("teacherSemesterId", teacherSemesterId);
		return (TeacherSemester) query.uniqueResult();
	}

	@Override
	public TeacherSemester getTeacherSemesterByAccount(String account, boolean jointTeacherCourseSemesters,
			boolean jointTimetables) {
		String hql = "FROM vn.edu.fpt.timetabling.model.TeacherSemester TS";
		if (jointTeacherCourseSemesters) {
			hql += " LEFT OUTER JOIN FETCH TS.teacherCourseSemesters";
		}
		if (jointTimetables) {
			hql += " LEFT OUTER JOIN FETCH TS.timetables";
		}
		hql += " WHERE TS.teacher.account = :account";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("account", account);
		return (TeacherSemester) query.uniqueResult();
	}

	@Override
	public void deleteTeacherSemester(int teacherSemesterId) {
		TeacherSemester teacherSemester = getTeacherSemesterById(teacherSemesterId, false, false);
		getCurrentSession().delete(teacherSemester);
	}
}
