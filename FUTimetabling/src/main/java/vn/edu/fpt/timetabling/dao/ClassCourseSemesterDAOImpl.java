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

import vn.edu.fpt.timetabling.model.ClassCourseSemester;

@Repository
public class ClassCourseSemesterDAOImpl implements ClassCourseSemesterDAO {

	private static final Logger logger = LoggerFactory.getLogger(ClassCourseSemesterDAOImpl.class);

	private SessionFactory sessionFactory;

	@Autowired
	private ClassSemesterDAO classSemesterDAO;

	@Autowired
	private SemesterDAO semesterDAO;

	@Autowired
	private CourseSemesterDAO courseSemesterDAO;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addClassCourseSemester(ClassCourseSemester classCourseSemester) {
		getCurrentSession().persist(classCourseSemester);
		logger.info("ClassCourseSemester was saved successfully, ClassCourseSemester details=" + classCourseSemester);
	}

	@Override
	public void updateClassCourseSemester(ClassCourseSemester classCourseSemester) {
		getCurrentSession().update(classCourseSemester);
		logger.info("ClassCourseSemester was updated successfully, class details=" + classCourseSemester);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClassCourseSemester> listClassCourseSemesters() {
		String hql = "FROM vn.edu.fpt.timetabling.model.ClassCourseSemester CCS" 
				+ " LEFT OUTER JOIN FETCH CCS.timetable"
				+ " ORDER BY CCS.classCourseSemesterId";
		Query query = getCurrentSession().createQuery(hql);
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<ClassCourseSemester> classCourseSemesters = (List<ClassCourseSemester>) query.list();
		for (ClassCourseSemester classCourseSemester : classCourseSemesters) {
			logger.info("ClassCourseSemester list:" + classCourseSemester);
		}
		return classCourseSemesters;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClassCourseSemester> listClassCourseSemesterByClass(int classSemesterId) {
		String hql = "FROM vn.edu.fpt.timetabling.model.ClassCourseSemester C WHERE C.classSemester = :classSemester";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("classSemester", classSemesterDAO.getClassSemesterById(classSemesterId, true));
		List<ClassCourseSemester> classCourseSemesters = (List<ClassCourseSemester>) query.list();
		for (ClassCourseSemester classCourseSemester : classCourseSemesters) {
			logger.info("ClassCourseSemester list:" + classCourseSemester);
		}
		return classCourseSemesters;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClassCourseSemester> listClassCourseSemesterByCourse(int courseSemesterId) {
		String hql = "FROM vn.edu.fpt.timetabling.model.ClassCourseSemester C WHERE C.courseSemester = :courseSemester";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("courseSemester",
				courseSemesterDAO.getCourseSemesterById(courseSemesterId, false, false, false));
		List<ClassCourseSemester> classCourseSemesters = (List<ClassCourseSemester>) query.list();
		for (ClassCourseSemester classCourseSemester : classCourseSemesters) {
			logger.info("ClassCourseSemester list:" + classCourseSemester);
		}
		return classCourseSemesters;
	}

	@Override
	public ClassCourseSemester getClassCourseSemesterById(int classCourseSemesterId) {
		ClassCourseSemester classCourseSemester = (ClassCourseSemester) getCurrentSession()
				.get(ClassCourseSemester.class, new Integer(classCourseSemesterId));
		if (classCourseSemester != null) {
			logger.info(
					"ClassCourseSemester was loaded successfully, ClassCourseSemester details=" + classCourseSemester);
		}
		return classCourseSemester;
	}

	@Override
	public void deleteClassCourseSemester(int classCourseSemesterId) {
		ClassCourseSemester classCourseSemester = getClassCourseSemesterById(classCourseSemesterId);
		if (classCourseSemester != null) {
			getCurrentSession().delete(classCourseSemester);
			logger.info(
					"ClassCourseSemester was deleted successfully, classCourseSemester details=" + classCourseSemester);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClassCourseSemester> listClassCourseSemesterBySemester(int semesterId) {
		String hql = "FROM vn.edu.fpt.timetabling.model.ClassCourseSemester C";
		hql += " WHERE C.classSemester.semester = :semester";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("semester", semesterDAO.getSemesterById(semesterId, false, false, false, false));
		List<ClassCourseSemester> classCourseSemesters = (List<ClassCourseSemester>) query.list();
		for (ClassCourseSemester classCourseSemester : classCourseSemesters) {
			logger.info("ClassCourseSemester list:" + classCourseSemester);
		}
		return classCourseSemesters;
	}

}
