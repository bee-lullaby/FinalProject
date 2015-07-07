package vn.edu.fpt.timetabling.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.edu.fpt.timetabling.model.ClassCourseSemester;
import vn.edu.fpt.timetabling.model.Specialized;
import vn.edu.fpt.timetabling.model.Student;

@Repository
public class StudentDAOImpl implements StudentDAO {
	private static final Logger logger = LoggerFactory.getLogger(StudentDAOImpl.class);

	private SessionFactory sessionFactory;

	@Autowired
	private SpecializedDAO specializedDAO;
	@Autowired
	private ClassCourseSemesterDAO classCourseSemesterDAO;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addStudent(Student student) {
		getCurrentSession().persist(student);
		logger.info("student was saved successfully, student details=" + student);
	}

	@Override
	public void updateStudent(Student student) {
		getCurrentSession().update(student);
		logger.info("student was updated successfully, student details=" + student);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> listStudents() {
		List<Student> students = (List<Student>) getCurrentSession()
				.createQuery("from vn.edu.fpt.timetabling.model.Student").list();
		for (Student student : students) {
			logger.info("student list:" + student);
		}
		return students;
	}

	@Override
	public Student getStudentById(int studentId) {
		Student student = (Student) getCurrentSession().get(Student.class, new Integer(studentId));
		if (student != null) {
			logger.info("student was loaded successfully, student details=" + student);
		}
		return student;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Student getStudentByCode(String code) {
		String hql = "FROM vn.edu.fpt.timetabling.model.Student S WHERE S.studentCode = :code";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("code", code);
		List<Student> student = (List<Student>) query.list();
		if (!student.isEmpty()) {
			logger.info("student was loaded successfully, student details=" + student.get(0));
			return student.get(0);
		} else {
			return null;
		}
	}

	@Override
	public void deleteStudent(int studentId) {
		Student student = getStudentById(studentId);
		if (student != null) {
			getCurrentSession().delete(student);
			logger.info("student was deleted successfully, student details=" + student);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> listStudentsBySpecializedSemester(int specializedId, int detailspecializedId,
			int semesterNumber, int classCourseSemesterId) {
		Specialized specialized = specializedDAO.getSpecializedById(specializedId);
		Specialized detailSpecialized = specializedDAO.getSpecializedById(detailspecializedId);
		ClassCourseSemester classCourseSemester = classCourseSemesterDAO
				.getClassCourseSemesterById(classCourseSemesterId);
		if (specialized == null || classCourseSemester == null) {
			return null;
		}
		String hql = "FROM vn.edu.fpt.timetabling.model.Student S";
		hql += " WHERE S.specialized = :specialized AND S.semester = :semester";
		hql += " AND S NOT IN (SELECT CCSS.student"
				+ " FROM vn.edu.fpt.timetabling.model.ClassCourseStudentSemester CCSS"
				+ " WHERE CCSS.classCourseSemester = :classCourseSemester)";
		if (detailSpecialized != null) {
			hql += " AND S.detailSpecialized = :detailSpecialized";
		}
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("semester", semesterNumber);
		query.setParameter("specialized", specialized);
		query.setParameter("classCourseSemester", classCourseSemester);
		if (detailSpecialized != null) {
			query.setParameter("detailSpecialized", detailSpecialized);
		}
		List<Student> students = (List<Student>) query.list();
		for (Student student : students) {
			logger.info("student list:" + student);
		}
		return students;
	}

}
