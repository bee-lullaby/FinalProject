package vn.edu.fpt.timetabling.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import vn.edu.fpt.timetabling.model.ClassCourseSemester;

@Repository
public class ClassCourseSemesterDAOImpl implements ClassCourseSemesterDAO {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addClassCourseSemester(ClassCourseSemester classCourseSemester) {
		getCurrentSession().persist(classCourseSemester);
	}

	@Override
	public void updateClassCourseSemester(ClassCourseSemester classCourseSemester) {
		getCurrentSession().update(classCourseSemester);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClassCourseSemester> listClassCourseSemesters() {
		String hql = "FROM vn.edu.fpt.timetabling.model.ClassCourseSemester CCS"
				+ " LEFT OUTER JOIN FETCH CCS.timetable" + " ORDER BY CCS.classCourseSemesterId";
		Query query = getCurrentSession().createQuery(hql);
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<ClassCourseSemester> classCourseSemesters = (List<ClassCourseSemester>) query.list();
		return classCourseSemesters;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClassCourseSemester> listClassCourseSemesterByClass(int classSemesterId) {
		String hql = "FROM vn.edu.fpt.timetabling.model.ClassCourseSemester CCS"
				+ " WHERE CCS.classSemester.classSemesterId = :classSemesterId";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("classSemesterId", classSemesterId);
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<ClassCourseSemester> classCourseSemesters = (List<ClassCourseSemester>) query.list();
		return classCourseSemesters;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClassCourseSemester> listClassCourseSemesterByCourse(int courseSemesterId) {
		String hql = "FROM vn.edu.fpt.timetabling.model.ClassCourseSemester CCS"
				+ " WHERE CCS.courseSemester.courseSemesterId = :courseSemesterId";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("courseSemesterId", courseSemesterId);
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<ClassCourseSemester> classCourseSemesters = (List<ClassCourseSemester>) query.list();
		return classCourseSemesters;
	}

	@Override
	public ClassCourseSemester getClassCourseSemesterById(int classCourseSemesterId) {
		ClassCourseSemester classCourseSemester = (ClassCourseSemester) getCurrentSession()
				.get(ClassCourseSemester.class, new Integer(classCourseSemesterId));
		return classCourseSemester;
	}

	@Override
	public void deleteClassCourseSemester(int classCourseSemesterId) {
		ClassCourseSemester classCourseSemester = getClassCourseSemesterById(classCourseSemesterId);
		getCurrentSession().delete(classCourseSemester);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClassCourseSemester> listClassCourseSemesterBySemester(int semesterId) {
		String hql = "FROM vn.edu.fpt.timetabling.model.ClassCourseSemester CCS";
		hql += " WHERE CCS.classSemester.semester.semesterId = :semesterId";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("semesterId", semesterId);
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<ClassCourseSemester> classCourseSemesters = (List<ClassCourseSemester>) query.list();
		return classCourseSemesters;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClassCourseSemester> listClassCourseSemesterByStudent(int semesterId, int studentId) {
		String hql = "FROM vn.edu.fpt.timetabling.model.ClassCourseSemester CCS"
				+ " LEFT OUTER JOIN FETCH CCS.classCourseStudentSemesters CCSS"
				+ " WHERE CCS.classSemester.semester.semesterId = :semesterId"
				+ " AND CCSS.student.studentId = :studentId";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("studentId", studentId);
		query.setParameter("semesterId", semesterId);
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<ClassCourseSemester> classCourseSemesters = (List<ClassCourseSemester>) query.list();
		return classCourseSemesters;
	}

	@Override
	public long getNumberOfStudents(int classCourseSemesterId) {
		String hql = "SELECT COUNT(DISTINCT CCSS.student) FROM vn.edu.fpt.timetabling.model.ClassCourseSemester CCS"
				+ " JOIN CCS.classCourseStudentSemesters CCSS"
				+ " WHERE CCS.classCourseSemesterId = :classCourseSemesterId";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("classCourseSemesterId", classCourseSemesterId);
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		long numberOfStudents = (Long) query.uniqueResult();
		return numberOfStudents;
	}
}
