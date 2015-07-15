package vn.edu.fpt.timetabling.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import vn.edu.fpt.timetabling.model.Semester;

@Repository
public class SemesterDAOImpl implements SemesterDAO {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addSemester(Semester semester) {
		getCurrentSession().persist(semester);
	}

	@Override
	public void updateSemester(Semester semester) {
		getCurrentSession().update(semester);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Semester> listSemesters(boolean jointClassSemester, boolean jointCourseSemester,
			boolean jointProgramSemester, boolean jointTeacherSemester) {
		String hql = "FROM vn.edu.fpt.timetabling.model.Semester S";
		if (jointClassSemester) {
			hql += " LEFT OUTER JOIN FETCH S.classSemesters";
		}
		if (jointCourseSemester) {
			hql += " LEFT OUTER JOIN FETCH S.courseSemesters";
		}
		if (jointProgramSemester) {
			hql += " LEFT OUTER JOIN FETCH S.programSemesters";
		}
		if (jointTeacherSemester) {
			hql += " LEFT OUTER JOIN FETCH S.teacherSemesters";
		}
		Query query = getCurrentSession().createQuery(hql);
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Semester> semesters = (List<Semester>) query.list();
		return semesters;
	}

	@Override
	public Semester getSemesterById(int semesterId, boolean jointClassSemester, boolean jointCourseSemester,
			boolean jointProgramSemester, boolean jointTeacherSemester) {
		String hql = "FROM vn.edu.fpt.timetabling.model.Semester S";
		if (jointClassSemester) {
			hql += " LEFT OUTER JOIN FETCH S.classSemesters";
		}
		if (jointCourseSemester) {
			hql += " LEFT OUTER JOIN FETCH S.courseSemesters";
		}
		if (jointProgramSemester) {
			hql += " LEFT OUTER JOIN FETCH S.programSemesters";
		}
		if (jointTeacherSemester) {
			hql += " LEFT OUTER JOIN FETCH S.teacherSemesters";
		}
		hql += " WHERE S.semesterId = :semesterId";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("semesterId", semesterId);
		return (Semester) query.uniqueResult();
	}

	@Override
	public void deleteSemester(int semesterId) {
		Semester semester = getSemesterById(semesterId, false, false, false, false);
		getCurrentSession().delete(semester);
	}
}
