package vn.edu.fpt.timetabling.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vn.edu.fpt.timetabling.model.Student;

public class StudentDAOImpl implements StudentDAO {
	private static final Logger logger = LoggerFactory
			.getLogger(StudentDAOImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addStudent(Student student) {
		getCurrentSession().persist(student);
		logger.info("student was saved successfully, student details="
				+ student);
	}

	@Override
	public void updateStudent(Student student) {
		getCurrentSession().update(student);
		logger.info("student was updated successfully, student details="
				+ student);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> listStudents() {
		List<Student> students = (List<Student>) getCurrentSession()
				.createQuery("from vn.edu.fpt.timetabling.model.Student")
				.list();
		for (Student student : students) {
			logger.info("student list:" + student);
		}
		return students;
	}

	@Override
	public Student getStudentById(int studentId) {
		Student student = (Student) getCurrentSession().load(Student.class,
				new Integer(studentId));
		logger.info("student was loaded successfully, student details="
				+ student);
		return student;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Student getStudentByCode(String code) {
		String hql = "FROM vn.edu.fpt.timetabling.model.Student S WHERE S.student_code = :code";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("code", code);
		List<Student> student = (List<Student>) query.list();
		logger.info("student was loaded successfully, student details="
				+ student.get(0));
		return student.get(0);
	}

	@Override
	public void deleteStudent(int studentId) {
		Student student = getStudentById(studentId);
		if (student != null) {
			getCurrentSession().persist(student);
			logger.info("student was deleted successfully, student details="
					+ student);
		}
	}

}
