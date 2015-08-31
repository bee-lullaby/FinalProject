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

	@SuppressWarnings("unchecked")
	@Override
	public List<TeacherSemester> listTeacherSemestersBySemester(int semesterId, boolean jointTeacherCourseSemesters,
			boolean jointTimetables) {
		String hql = "FROM vn.edu.fpt.timetabling.model.TeacherSemester TS";
		if (jointTeacherCourseSemesters) {
			hql += " LEFT OUTER JOIN FETCH TS.teacherCourseSemesters";
		}
		if (jointTimetables) {
			hql += " LEFT OUTER JOIN FETCH TS.timetables";
		}
		hql += " WHERE TS.semester.semesterId = :semesterId";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("semesterId", semesterId);
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

	@SuppressWarnings("unchecked")
	@Override
	public List<TeacherSemester> listTeacherSemestersByTeacherId(int teacherId, boolean jointTeacherCourseSemesters,
			boolean jointTimetables) {
		String hql = "FROM vn.edu.fpt.timetabling.model.TeacherSemester TS";
		if (jointTeacherCourseSemesters) {
			hql += " LEFT OUTER JOIN FETCH TS.teacherCourseSemesters";
		}
		if (jointTimetables) {
			hql += " LEFT OUTER JOIN FETCH TS.timetables";
		}
		hql += " WHERE TS.teacher.teacherId = :teacherId";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("teacherId", teacherId);
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<TeacherSemester> teacherSemesters = (List<TeacherSemester>) query.list();
		return teacherSemesters;
	}
	
	@Override
	public TeacherSemester getTeacherSemesterByTeacherSemester(int teacherId, int semesterId,
			boolean jointTeacherCourseSemesters, boolean jointTimetables) {
		String hql = "FROM vn.edu.fpt.timetabling.model.TeacherSemester TS";
		if (jointTeacherCourseSemesters) {
			hql += " LEFT OUTER JOIN FETCH TS.teacherCourseSemesters";
		}
		if (jointTimetables) {
			hql += " LEFT OUTER JOIN FETCH TS.timetables";
		}
		hql += " WHERE TS.teacher.teacherId = :teacherId AND TS.semester.semesterId = :semesterId";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("semesterId", semesterId);
		query.setParameter("teacherId", teacherId);
		return (TeacherSemester) query.uniqueResult();
	}

	@Override
	public TeacherSemester getTeacherSemesterByAccount(int semesterId, String account,
			boolean jointTeacherCourseSemesters, boolean jointTimetables) {
		String hql = "FROM vn.edu.fpt.timetabling.model.TeacherSemester TS";
		if (jointTeacherCourseSemesters) {
			hql += " LEFT OUTER JOIN FETCH TS.teacherCourseSemesters";
		}
		if (jointTimetables) {
			hql += " LEFT OUTER JOIN FETCH TS.timetables";
		}
		hql += " WHERE TS.semester.semesterId = :semesterId AND TS.teacher.account = :account";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("semesterId", semesterId);
		query.setParameter("account", account);
		return (TeacherSemester) query.uniqueResult();
	}
	


	@SuppressWarnings("unchecked")
	@Override
	public List<TeacherSemester> listTeacherInTimetable(int semesterId) {
		String hql = "FROM vn.edu.fpt.timetabling.model.TeacherSemester TS" + " WHERE TS IN (SELECT T.teacherSemester"
				+ " FROM vn.edu.fpt.timetabling.model.Timetable T"
				+ " WHERE T.classCourseSemester.classSemester.semester.semesterId = :semesterId)";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("semesterId", semesterId);
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<TeacherSemester> teachers = (List<TeacherSemester>) query.list();
		return teachers;
	}

	@Override
	public void deleteTeacherSemester(int teacherSemesterId) {
		TeacherSemester teacherSemester = getTeacherSemesterById(teacherSemesterId, false, false);
		getCurrentSession().delete(teacherSemester);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TeacherSemester> listTeacherSemestersByCourse(int courseId) {
		String hql = "SELECT TCS.teacherSemester FROM vn.edu.fpt.timetabling.model.TeacherCourseSemester TCS"
				+ " WHERE TCS.courseSemester.course.courseId = :courseId";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("courseId", courseId);
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<TeacherSemester> teacherSemesters = (List<TeacherSemester>) query.list();
		return teacherSemesters;
	}
}
