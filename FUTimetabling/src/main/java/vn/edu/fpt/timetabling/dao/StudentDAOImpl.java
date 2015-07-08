package vn.edu.fpt.timetabling.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.edu.fpt.timetabling.model.ClassCourseSemester;
import vn.edu.fpt.timetabling.model.ClassSemester;
import vn.edu.fpt.timetabling.model.Semester;
import vn.edu.fpt.timetabling.model.Specialized;
import vn.edu.fpt.timetabling.model.Student;
import vn.edu.fpt.timetabling.model.Timetable;

@Repository
public class StudentDAOImpl implements StudentDAO {
	private static final Logger logger = LoggerFactory.getLogger(StudentDAOImpl.class);

	private SessionFactory sessionFactory;

	@Autowired
	private SpecializedDAO specializedDAO;
	@Autowired
	private ClassCourseSemesterDAO classCourseSemesterDAO;
	@Autowired
	private ClassSemesterDAO classSemesterDAO;
	@Autowired
	private TimetableDAO timetableDAO;

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
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
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
	public List<Student> listStudentsCanBeInClassCourseSemester(int classSemesterId, int specializedId,
			int detailspecializedId, int semesterNumber, int classCourseSemesterId) {
		ClassSemester classSemester = classSemesterDAO.getClassSemesterById(classSemesterId, true);
		Specialized specialized = specializedDAO.getSpecializedById(specializedId);
		Specialized detailSpecialized = specializedDAO.getSpecializedById(detailspecializedId);
		ClassCourseSemester classCourseSemester = classCourseSemesterDAO
				.getClassCourseSemesterById(classCourseSemesterId);
		if ((classSemester == null && classCourseSemester == null) || specialized == null) {
			return null;
		}
		Semester semester;
		Set<ClassCourseSemester> classCourseSemesters;
		if (classCourseSemester == null) {
			semester = classSemester.getSemester();
			classCourseSemesters = classSemester.getClassCourseSemester();
		} else {
			semester = classCourseSemester.getClassSemester().getSemester();
			classCourseSemesters = new HashSet<ClassCourseSemester>();
			classCourseSemesters.add(classCourseSemester);
		}
		List<Timetable> timetablesOfClassSemester = timetableDAO
				.listTimetablesByClassCourseSemesters(classCourseSemesters);
		String hql = "FROM vn.edu.fpt.timetabling.model.Student S"
				+ " WHERE S.specialized = :specialized AND S.semester = :semester";
		if (detailSpecialized != null) {
			hql += " AND S.detailSpecialized = :detailSpecialized";
		}
		hql += " AND S NOT IN (SELECT CCSS.student"
				+ " FROM vn.edu.fpt.timetabling.model.ClassCourseStudentSemester CCSS"
				+ " WHERE CCSS.classCourseSemester IN (:classCourseSemesters))";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("semester", semesterNumber);
		query.setParameter("specialized", specialized);
		query.setParameterList("classCourseSemesters", classCourseSemesters);
		if (detailSpecialized != null) {
			query.setParameter("detailSpecialized", detailSpecialized);
		}
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Student> studentsWithSuitableSpecialized = (List<Student>) query.list();
		List<Student> suitableStudents = new ArrayList<Student>();
		for (Student student : studentsWithSuitableSpecialized) {
			List<Timetable> timetablesOfStudent = timetableDAO.listTimetablesByStudent(semester.getSemesterId(),
					student);
			boolean isSuitable = true;
			for (Timetable timetableOfStudent : timetablesOfStudent) {
				for (Timetable timetableOfClassSemester : timetablesOfClassSemester) {
					if (timetableOfStudent.isSameTime(timetableOfClassSemester)) {
						isSuitable = false;
						break;
					}
				}
				if (!isSuitable) {
					break;
				}
			}
			if (isSuitable) {
				suitableStudents.add(student);
			}
		}
		return suitableStudents;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> listStudentsInClassCourseSemester(int classSemesterId, int classCourseSemesterId) {
		ClassSemester classSemester = classSemesterDAO.getClassSemesterById(classSemesterId, false);
		ClassCourseSemester classCourseSemester = classCourseSemesterDAO
				.getClassCourseSemesterById(classCourseSemesterId);
		if (classCourseSemester == null && classSemester == null) {
			return new ArrayList<Student>();
		}
		String hql = "SELECT CCSS.student FROM vn.edu.fpt.timetabling.model.ClassCourseStudentSemester CCSS";
		if (classCourseSemester != null) {
			hql += " WHERE CCSS.classCourseSemester = :classCourseSemester";
		} else {
			hql += " WHERE CCSS.classCourseSemester.classSemester = :classSemester";
		}
		Query query = getCurrentSession().createQuery(hql);
		if (classCourseSemester != null) {
			query.setParameter("classCourseSemester", classCourseSemester);
		} else {
			query.setParameter("classSemester", classSemester);
		}
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Student> students = (List<Student>) query.list();
		return students;
	}

}
