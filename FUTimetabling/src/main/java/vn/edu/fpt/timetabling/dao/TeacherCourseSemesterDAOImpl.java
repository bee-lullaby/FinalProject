package vn.edu.fpt.timetabling.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import vn.edu.fpt.timetabling.model.TeacherCourseSemester;

@Repository
public class TeacherCourseSemesterDAOImpl implements TeacherCourseSemesterDAO {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addTeacherCourseSemester(TeacherCourseSemester teacherCourseSemester) {
		getCurrentSession().persist(teacherCourseSemester);
	}

	@Override
	public void updateTeacherCourseSemester(TeacherCourseSemester teacherCourseSemester) {
		getCurrentSession().update(teacherCourseSemester);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TeacherCourseSemester> listTeacherCourseSemesters() {
		List<TeacherCourseSemester> teacherCourseSemesters = (List<TeacherCourseSemester>) getCurrentSession()
				.createQuery("FROM vn.edu.fpt.timetabling.model.TeacherCourseSemester").list();
		return teacherCourseSemesters;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TeacherCourseSemester> listTeacherCourseSemestersByCourse(int courseSemesterId) {
		String hql = "FROM vn.edu.fpt.timetabling.model.TeacherCourseSemester TCS"
				+ " WHERE TCS.courseSemester.courseSemesterId = :courseSemesterId";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("courseSemesterId", courseSemesterId);
		List<TeacherCourseSemester> teacherCourseSemesters = (List<TeacherCourseSemester>) query.list();
		return teacherCourseSemesters;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TeacherCourseSemester> listTeacherCourseSemestersByTeacher(int teacherSemesterId) {
		String hql = "FROM vn.edu.fpt.timetabling.model.TeacherCourseSemester TCS"
				+ " WHERE TCS.teacherSemester.teacherSemesterId = :teacherSemesterId";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("teacherSemesterId", teacherSemesterId);
		List<TeacherCourseSemester> teacherCourseSemesters = (List<TeacherCourseSemester>) query.list();
		return teacherCourseSemesters;
	}

	@Override
	public TeacherCourseSemester getTeacherCourseSemesterById(int teacherCourseSemesterId) {
		TeacherCourseSemester teacherCourseSemester = (TeacherCourseSemester) getCurrentSession()
				.get(TeacherCourseSemester.class, new Integer(teacherCourseSemesterId));
		return teacherCourseSemester;
	}
	
	@Override
	public TeacherCourseSemester getTeacherCourseSemesterByTeacherCourse(String teacherAccount, String courseCode) {
		String hql = "FROM vn.edu.fpt.timetabling.model.TeacherCourseSemester TCS"
				+" WHERE TCS.teacherSemester.teacher.account = :teacherAccount"
				+" AND TCS.courseSemester.course.code = :courseCode";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("teacherAccount", teacherAccount);
		query.setParameter("courseCode", courseCode);
		return (TeacherCourseSemester) query.uniqueResult();
	}
	
	@Override
	public void deleteTeacherCourseSemester(int teacherCourseSemesterId) {
		TeacherCourseSemester teacherCourseSemester = getTeacherCourseSemesterById(teacherCourseSemesterId);
		getCurrentSession().delete(teacherCourseSemester);
	}
}
