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
import vn.edu.fpt.timetabling.model.ClassCourseStudentSemester;
import vn.edu.fpt.timetabling.model.Student;

@Repository
public class ClassCourseStudentSemesterDAOImpl implements ClassCourseStudentSemesterDAO {

	private static final Logger logger = LoggerFactory.getLogger(ClassCourseStudentSemesterDAOImpl.class);

	@Autowired
	private ClassCourseSemesterDAO classCourseSemesterDAO;

	@Autowired
	private StudentDAO studentDAO;

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addClassCourseStudentSemester(ClassCourseStudentSemester classCourseStudentSemester) {
		getCurrentSession().persist(classCourseStudentSemester);
		logger.info("ClassCourseStudentSemester was saved successfully, ClassCourseStudentSemester details="
				+ classCourseStudentSemester);
	}

	@Override
	public void updateClassCourseStudentSemester(ClassCourseStudentSemester classCourseStudentSemester) {
		getCurrentSession().update(classCourseStudentSemester);
		logger.info("ClassCourseStudentSemester was saved successfully, ClassCourseStudentSemester details="
				+ classCourseStudentSemester);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClassCourseStudentSemester> listClassCourseStudentSemesters() {
		String hql = "FROM vn.edu.fpt.timetabling.model.ClassCourseStudentSemester";
		Query query = getCurrentSession().createQuery(hql);
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<ClassCourseStudentSemester> classCourseStudentSemesters = (List<ClassCourseStudentSemester>) query.list();
		for (ClassCourseStudentSemester classCourseStudentSemester : classCourseStudentSemesters) {
			logger.info("ClassCourseSemester list:" + classCourseStudentSemester);
		}
		return classCourseStudentSemesters;
	}

	@Override
	public ClassCourseStudentSemester getClassCourseStudentSemesterById(int classCourseStudentSemesterId) {
		ClassCourseStudentSemester classCourseStudentSemester = (ClassCourseStudentSemester) getCurrentSession()
				.get(ClassCourseStudentSemester.class, new Integer(classCourseStudentSemesterId));
		if (classCourseStudentSemester != null) {
			logger.info("ClassCourseStudentSemester was loaded successfully, ClassCourseStudentSemester details="
					+ classCourseStudentSemester);
		}
		return classCourseStudentSemester;
	}

	@Override
	public void deleteClassCourseStudentSemester(int classCourseStudentSemesterId) {
		ClassCourseStudentSemester classCourseStudentSemester = getClassCourseStudentSemesterById(
				classCourseStudentSemesterId);
		if (classCourseStudentSemester != null) {
			getCurrentSession().delete(classCourseStudentSemester);
			logger.info("ClassCourseSemester was deleted successfully, classCourseSemester details="
					+ classCourseStudentSemester);
		}
	}

	@Override
	public void removeStudentFromClassCourseSemester(int studentId, int classCourseSemesterId) {
		Student student = studentDAO.getStudentById(studentId);
		ClassCourseSemester classCourseSemester = classCourseSemesterDAO
				.getClassCourseSemesterById(classCourseSemesterId);
		if (student == null || classCourseSemester == null) {
			return;
		}
		String hql = "DELETE vn.edu.fpt.timetabling.model.ClassCourseStudentSemester CCSS"
				+ " WHERE CCSS.classCourseSemester = :classCourseSemester" + " AND CCSS.student = :student";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("classCourseSemester", classCourseSemester);
		query.setParameter("student", student);
		query.executeUpdate();
	}

}
