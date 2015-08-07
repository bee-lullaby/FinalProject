package vn.edu.fpt.timetabling.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import vn.edu.fpt.timetabling.model.CourseSemester;

@Repository
public class CourseSemesterDAOImpl implements CourseSemesterDAO {
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
	}

	@Override
	public void updateCourseSemester(CourseSemester courseSemester) {
		getCurrentSession().update(courseSemester);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CourseSemester> listCourseSemesters(boolean jointClassCourseSemester,
			boolean jointTeacherCourseSemester, boolean jointProgramSemesterDetails) {
		String hql = "FROM vn.edu.fpt.timetabling.model.CourseSemester C";
		if (jointClassCourseSemester) {
			hql += " LEFT OUTER JOIN FETCH C.classCourseSemesters";
		}
		if (jointTeacherCourseSemester) {
			hql += " LEFT OUTER JOIN FETCH C.teacherCourseSemesters";
		}
		if (jointProgramSemesterDetails) {
			hql += " LEFT OUTER JOIN FETCH C.programSemesterDetails";
		}
		Query query = getCurrentSession().createQuery(hql);
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<CourseSemester> courseSemesters = (List<CourseSemester>) query.list();
		return courseSemesters;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CourseSemester> listCourseSemestersByDepartment(int semesterId, int departmentId,
			boolean jointClassCourseSemester, boolean jointTeacherCourseSemester, boolean jointProgramSemesterDetails) {
		String hql = "FROM vn.edu.fpt.timetabling.model.CourseSemester C";
		if (jointClassCourseSemester) {
			hql += " LEFT OUTER JOIN FETCH C.classCourseSemesters";
		}
		if (jointTeacherCourseSemester) {
			hql += " LEFT OUTER JOIN FETCH C.teacherCourseSemesters";
		}
		if (jointProgramSemesterDetails) {
			hql += " LEFT OUTER JOIN FETCH C.programSemesterDetails";
		}
		hql += " WHERE C.course.department.departmentId = :departmentId" + " AND C.semester.semesterId = :semesterId";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("departmentId", departmentId);
		query.setParameter("semesterId", semesterId);
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<CourseSemester> courseSemesters = (List<CourseSemester>) query.list();
		return courseSemesters;
	}

	@Override
	public CourseSemester getCourseSemesterById(int courseSemesterId, boolean jointClassCourseSemester,
			boolean jointTeacherCourseSemester, boolean jointProgramSemesterDetails) {
		String hql = "FROM vn.edu.fpt.timetabling.model.CourseSemester C";
		if (jointClassCourseSemester) {
			hql += " LEFT OUTER JOIN FETCH C.classCourseSemesters";
		}
		if (jointTeacherCourseSemester) {
			hql += " LEFT OUTER JOIN FETCH C.teacherCourseSemesters";
		}
		if (jointProgramSemesterDetails) {
			hql += " LEFT OUTER JOIN FETCH C.programSemesterDetails";
		}
		hql += " WHERE C.courseSemesterId = :courseSemesterId";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("courseSemesterId", courseSemesterId);
		return (CourseSemester) query.uniqueResult();
	}

	@Override
	public CourseSemester getCourseSemesterByCourseSemester(int courseId, int semesterId,
			boolean jointClassCourseSemester, boolean jointTeacherCourseSemester, boolean jointProgramSemesterDetails) {
		String hql = "FROM vn.edu.fpt.timetabling.model.CourseSemester C";
		if (jointClassCourseSemester) {
			hql += " LEFT OUTER JOIN FETCH C.classCourseSemesters";
		}
		if (jointTeacherCourseSemester) {
			hql += " LEFT OUTER JOIN FETCH C.teacherCourseSemesters";
		}
		if (jointProgramSemesterDetails) {
			hql += " LEFT OUTER JOIN FETCH C.programSemesterDetails";
		}
		hql += " WHERE C.course.courseId = :courseId AND C.semester.semesterId = :semesterId";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("courseId", courseId);
		query.setParameter("semesterId", semesterId);
		return (CourseSemester) query.uniqueResult();
	}

	@Override
	public CourseSemester getCourseSemesterByCourseCodeSemester(String code, int semesterId,
			boolean jointClassCourseSemester, boolean jointTeacherCourseSemester, boolean jointProgramSemesterDetails) {
		String hql = "FROM vn.edu.fpt.timetabling.model.CourseSemester C";
		if (jointClassCourseSemester) {
			hql += " LEFT OUTER JOIN FETCH C.classCourseSemesters";
		}
		if (jointTeacherCourseSemester) {
			hql += " LEFT OUTER JOIN FETCH C.teacherCourseSemesters";
		}
		if (jointProgramSemesterDetails) {
			hql += " LEFT OUTER JOIN FETCH C.programSemesterDetails";
		}
		hql += " WHERE C.course.code = :code AND C.semester.semesterId = :semesterId";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("code", code);
		query.setParameter("semesterId", semesterId);
		return (CourseSemester) query.uniqueResult();
	}

	@Override
	public void deleteCourseSemester(int courseSemesterId) {
		CourseSemester courseSemester = getCourseSemesterById(courseSemesterId, false, false, false);
		getCurrentSession().delete(courseSemester);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CourseSemester> listCourseSemestersByStudent(int studentId) {
		String hql = "FROM vn.edu.fpt.timetabling.model.CourseSemester C"
				+ " LEFT OUTER JOIN FETCH C.classCourseSemesters CCS"
				+ " LEFT OUTER JOIN FETCH CCS.classCourseStudentSemesters CCSS"
				+ " WHERE CCSS.student.studentId = :studentId";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("studentId", studentId);
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<CourseSemester> courseSemesters = (List<CourseSemester>) query.list();
		return courseSemesters;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CourseSemester> listCourseSemestersBySemester(int semesterId, boolean jointClassCourseSemester,
			boolean jointTeacherCourseSemester, boolean jointProgramSemesterDetails) {
		String hql = "FROM vn.edu.fpt.timetabling.model.CourseSemester C";
		if (jointClassCourseSemester) {
			hql += " LEFT OUTER JOIN FETCH C.classCourseSemesters";
		}
		if (jointTeacherCourseSemester) {
			hql += " LEFT OUTER JOIN FETCH C.teacherCourseSemesters";
		}
		if (jointProgramSemesterDetails) {
			hql += " LEFT OUTER JOIN FETCH C.programSemesterDetails";
		}
		hql += " WHERE C.semester.semesterId = :semesterId";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("semesterId", semesterId);
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<CourseSemester> courseSemesters = (List<CourseSemester>) query.list();
		return courseSemesters;
	}

	@Override
	public int deleteCourseSemesters(int semesterId) {
		String hql = "DELETE FROM vn.edu.fpt.timetabling.model.CourseSemester CS"
				+ " WHERE CS.courseSemesterId IN (SELECT CS2.courseSemesterId"
				+ " FROM vn.edu.fpt.timetabling.model.CourseSemester CS2"
				+ " WHERE CS2.semester.semesterId = :semesterId)";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("semesterId", semesterId);
		return query.executeUpdate();
	}
}
