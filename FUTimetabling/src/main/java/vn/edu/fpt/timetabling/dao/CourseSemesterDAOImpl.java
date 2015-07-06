package vn.edu.fpt.timetabling.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.edu.fpt.timetabling.model.CourseSemester;

@Repository
public class CourseSemesterDAOImpl implements CourseSemesterDAO {
	private static final Logger logger = LoggerFactory
			.getLogger(CourseSemesterDAO.class);

	private SessionFactory sessionFactory;

	@Autowired
	private SemesterDAO semesterDAO;

	@Autowired
	private CourseDAO courseDAO;

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
	public List<CourseSemester> listCourseSemesters(
			boolean jointClassCourseSemester,
			boolean jointTeacherCourseSemester,
			boolean jointProgramSemesterDetails) {
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
		List<CourseSemester> courseSemesters = (List<CourseSemester>) query
				.list();
		for (CourseSemester courseSemester : courseSemesters) {
			logger.info("CourseSemester list:" + courseSemester);
		}
		return courseSemesters;
	}

	@Override
	public CourseSemester getCourseSemesterById(int courseSemesterId,
			boolean jointClassCourseSemester,
			boolean jointTeacherCourseSemester,
			boolean jointProgramSemesterDetails) {
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
		Object temp = query.uniqueResult();
		if (temp != null) {
			CourseSemester courseSemester = (CourseSemester) temp;
			logger.info("CourseSemester was loaded successfully, CourseSemester="
					+ courseSemester);
			return courseSemester;
		} else {
			return null;
		}
	}

	@Override
	public CourseSemester getCourseSemesterByCode(String code,
			boolean jointClassCourseSemester,
			boolean jointTeacherCourseSemester,
			boolean jointProgramSemesterDetails) {
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
		hql += " WHERE C.course = :course";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("course", courseDAO.getCourseByCode(code));
		Object temp = query.uniqueResult();
		if (temp != null) {
			CourseSemester courseSemester = (CourseSemester) temp;
			logger.info("CourseSemester was loaded successfully, CourseSemester="
					+ courseSemester);
			return courseSemester;
		} else {
			return null;
		}
	}

	@Override
	public CourseSemester getCourseSemesterByCourseSemester(int courseId,
			int semesterId, boolean jointClassCourseSemester,
			boolean jointTeacherCourseSemester,
			boolean jointProgramSemesterDetails) {
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
		hql += "WHERE C.course = :course AND C.semester = :semester";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("course", courseDAO.getCourseById(courseId));
		query.setParameter("course", semesterDAO.getSemesterById(semesterId,
				false, false, false, false));
		Object temp = query.uniqueResult();
		if (temp != null) {
			CourseSemester courseSemester = (CourseSemester) temp;
			logger.info("CourseSemester was loaded successfully, CourseSemester="
					+ courseSemester);
			return courseSemester;
		} else {
			return null;
		}
	}

	@Override
	public void deleteCourseSemester(int courseSemesterId) {
		CourseSemester courseSemester = getCourseSemesterById(courseSemesterId, false, false, false);
		if (courseSemester != null) {
			getCurrentSession().delete(courseSemester);
			logger.info("CourseSemester was deleted successfully, CourseSemester details="
					+ courseSemester);
		}
	}
}
