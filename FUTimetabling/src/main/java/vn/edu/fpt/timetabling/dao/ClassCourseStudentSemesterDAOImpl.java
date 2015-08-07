package vn.edu.fpt.timetabling.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import vn.edu.fpt.timetabling.model.ClassCourseStudentSemester;

@Repository
public class ClassCourseStudentSemesterDAOImpl implements ClassCourseStudentSemesterDAO {
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
	}

	@Override
	public void updateClassCourseStudentSemester(ClassCourseStudentSemester classCourseStudentSemester) {
		getCurrentSession().update(classCourseStudentSemester);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClassCourseStudentSemester> listClassCourseStudentSemesters() {
		String hql = "FROM vn.edu.fpt.timetabling.model.ClassCourseStudentSemester";
		Query query = getCurrentSession().createQuery(hql);
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<ClassCourseStudentSemester> classCourseStudentSemesters = (List<ClassCourseStudentSemester>) query.list();
		return classCourseStudentSemesters;
	}

	@Override
	public ClassCourseStudentSemester getClassCourseStudentSemesterById(int classCourseStudentSemesterId) {
		ClassCourseStudentSemester classCourseStudentSemester = (ClassCourseStudentSemester) getCurrentSession()
				.get(ClassCourseStudentSemester.class, new Integer(classCourseStudentSemesterId));
		return classCourseStudentSemester;
	}

	@Override
	public void deleteClassCourseStudentSemester(int classCourseStudentSemesterId) {
		ClassCourseStudentSemester classCourseStudentSemester = getClassCourseStudentSemesterById(
				classCourseStudentSemesterId);
		getCurrentSession().delete(classCourseStudentSemester);
	}

	@Override
	public int removeStudentFromClassCourseSemester(int studentId, int classCourseSemesterId) {
		String hql = "DELETE vn.edu.fpt.timetabling.model.ClassCourseStudentSemester CCSS"
				+ " WHERE CCSS.classCourseSemester.classCourseSemesterId = :classCourseSemesterId"
				+ " AND CCSS.student.studentId = :studentId";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("classCourseSemesterId", classCourseSemesterId);
		query.setParameter("studentId", studentId);
		return query.executeUpdate();
	}

	@Override
	public int deleteClassCourseStudentSemesters(int semesterId) {
		String hql = "DELETE FROM vn.edu.fpt.timetabling.model.ClassCourseStudentSemester CCSS"
				+ " WHERE CCSS.classCourseStudentSemesterId IN (SELECT CCSS2.classCourseStudentSemesterId"
				+ " FROM vn.edu.fpt.timetabling.model.ClassCourseStudentSemester CCSS2"
				+ " WHERE CCS2.classCourseSemester.classSemester.semester.semesterId = :semesterId)";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("semesterId", semesterId);
		return query.executeUpdate();
	}
}
