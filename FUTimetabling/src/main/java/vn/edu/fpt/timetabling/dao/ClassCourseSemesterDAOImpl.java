package vn.edu.fpt.timetabling.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vn.edu.fpt.timetabling.model.ClassCourseSemester;

public class ClassCourseSemesterDAOImpl implements ClassCourseSemesterDAO {

	private static final Logger logger = LoggerFactory
			.getLogger(ClassDAOImpl.class);

	private SessionFactory sessionFactory;

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
		// TODO Auto-generated method stub
		List<ClassCourseSemester> classCourseSemesters = (List<ClassCourseSemester>) getCurrentSession()
				.createQuery(
						"from vn.edu.fpt.timetabling.model.ClassCourseSemester")
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
		String hql = "FROM vn.edu.fpt.timetabling.model.ClassCourseSemester C WHERE C.class_semester_id = :class_semester_id";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("class_semester_id", classSemesterId);
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
		String hql = "FROM vn.edu.fpt.timetabling.model.ClassCourseSemester C WHERE C.course_semester_id = :course_semester_id";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("course_semester_id", courseSemesterId);
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
