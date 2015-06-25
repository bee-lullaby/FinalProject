package vn.edu.fpt.timetabling.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import vn.edu.fpt.timetabling.model.CourseSemester;

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
	public List<CourseSemester> listCourseSemesters() {
		String hql = "FROM vn.edu.fpt.timetabling.model.CourseSemester"
				+ " C LEFT OUTER JOIN FETCH C.teacherCourseSemester "
				+ "   LEFT OUTER JOIN FETCH C.classCourseSemester"
				+ "   LEFT OUTER JOIN FETCH C.programSemesterDetails";
		Query query = getCurrentSession().createQuery(hql);
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<CourseSemester> courseSemesters = (List<CourseSemester>) query.list();
		for (CourseSemester courseSemester : courseSemesters) {
			logger.info("CourseSemester list:" + courseSemester);
		}
		return courseSemesters;
	}

	@Override
	public CourseSemester getCourseSemesterById(int courseSemesterId) {
		String hql = "FROM vn.edu.fpt.timetabling.model.CourseSemester"
				+ " C LEFT OUTER JOIN FETCH C.teacherCourseSemester "
				+ "   LEFT OUTER JOIN FETCH C.classCourseSemester"
				+ "   LEFT OUTER JOIN FETCH C.programSemesterDetails"
				+ " WHERE C.courseSemesterId = :courseSemesterId";
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
	public CourseSemester getCourseSemesterByCode(String code) {
		String hql = "FROM vn.edu.fpt.timetabling.model.CourseSemester"
				+ " C LEFT OUTER JOIN FETCH C.teacherCourseSemester "
				+ "   LEFT OUTER JOIN FETCH C.classCourseSemester"
				+ "   LEFT OUTER JOIN FETCH C.programSemesterDetails"
				+ " WHERE C.course = :course";
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
	public CourseSemester getCourseSemesterByCourseSemester(int courseId, int semesterId) {
		String hql = "FROM vn.edu.fpt.timetabling.model.CourseSemester"
				+ " C LEFT OUTER JOIN FETCH C.teacherCourseSemester "
				+ "   LEFT OUTER JOIN FETCH C.classCourseSemester"
				+ "   LEFT OUTER JOIN FETCH C.programSemesterDetails"
				+ " WHERE C.course = :course AND C.semester = :semester";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("course", courseDAO.getCourseById(courseId));
		query.setParameter("course", semesterDAO.getSemesterById(semesterId));
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
		CourseSemester courseSemester = getCourseSemesterById(courseSemesterId);
		if (courseSemester != null) {
			getCurrentSession().delete(courseSemester);
			logger.info("CourseSemester was deleted successfully, CourseSemester details="
					+ courseSemester);
		}
	}
}
