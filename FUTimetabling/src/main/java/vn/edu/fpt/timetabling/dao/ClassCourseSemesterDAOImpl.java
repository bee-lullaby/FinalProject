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

	private static final Logger logger = LoggerFactory
			.getLogger(ClassDAOImpl.class);

	private SessionFactory sessionFactory;

	@Autowired
	private ClassSemesterDAO classSemesterDAO;

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
		// TODO Auto-generated method stub
		getCurrentSession().persist(classCourseSemester);
		logger.info("ClassCourseSemester was saved successfully, ClassCourseSemester details="
				+ classCourseSemester);
	}

	@Override
	public void updateClassCourseSemester(
			ClassCourseSemester classCourseSemester) {
		// TODO Auto-generated method stub
		getCurrentSession().update(classCourseSemester);
		logger.info("ClassCourseSemester was updated successfully, class details="
				+ classCourseSemester);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClassCourseSemester> listClassCourseSemesters() {
		String hql = "FROM vn.edu.fpt.timetabling.model.ClassCourseSemester"
				+ " C LEFT OUTER JOIN FETCH C.timetable";
		Query query = getCurrentSession().createQuery(hql);
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<ClassCourseSemester> classCourseSemesters = (List<ClassCourseSemester>) query
				.list();
		for (ClassCourseSemester classCourseSemester : classCourseSemesters) {
			logger.info("ClassCourseSemester list:" + classCourseSemester);
		}
		return classCourseSemesters;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClassCourseSemester> listClassCourseSemesterByClass(
			int classSemesterId) {
		// TODO Auto-generated method stub

		String hql = "FROM vn.edu.fpt.timetabling.model.ClassCourseSemester C WHERE C.classSemester = :classSemester";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("classSemester",
				classSemesterDAO.getClassSemesterById(classSemesterId));
		List<ClassCourseSemester> classCourseSemesters = (List<ClassCourseSemester>) query
				.list();
		for (ClassCourseSemester classCourseSemester : classCourseSemesters) {
			logger.info("ClassCourseSemester list:" + classCourseSemester);
		}
		return classCourseSemesters;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClassCourseSemester> listClassCourseSemesterByCourse(
			int courseSemesterId) {
		// TODO Auto-generated method stub
		String hql = "FROM vn.edu.fpt.timetabling.model.ClassCourseSemester C WHERE C.courseSemester = :courseSemester";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("courseSemester",
				courseSemesterDAO.getCourseSemesterById(courseSemesterId));
		List<ClassCourseSemester> classCourseSemesters = (List<ClassCourseSemester>) query
				.list();
		for (ClassCourseSemester classCourseSemester : classCourseSemesters) {
			logger.info("ClassCourseSemester list:" + classCourseSemester);
		}
		return classCourseSemesters;
	}

	@Override
	public ClassCourseSemester getClassCourseSemesterById(
			int classCourseSemesterId) {
		// TODO Auto-generated method stub
		ClassCourseSemester classCourseSemester = (ClassCourseSemester) getCurrentSession()
				.get(ClassCourseSemester.class,
						new Integer(classCourseSemesterId));
		if (classCourseSemester != null) {
			logger.info("ClassCourseSemester was loaded successfully, ClassCourseSemester details="
					+ classCourseSemester);
		}
		return classCourseSemester;
	}

	@Override
	public void deleteClassCourseSemester(int classCourseSemesterId) {
		ClassCourseSemester classCourseSemester = getClassCourseSemesterById(classCourseSemesterId);
		if (classCourseSemester != null) {
			getCurrentSession().delete(classCourseSemester);
			logger.info("ClassCourseSemester was deleted successfully, classCourseSemester details="
					+ classCourseSemester);
		}

	}

}
