package vn.edu.fpt.timetabling.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.edu.fpt.timetabling.model.ClassCourseSemester;
import vn.edu.fpt.timetabling.model.ClassSemester;
import vn.edu.fpt.timetabling.model.CourseSemester;
import vn.edu.fpt.timetabling.model.Semester;
import vn.edu.fpt.timetabling.model.Student;
import vn.edu.fpt.timetabling.model.Timetable;

@Repository
public class StudentDAOImpl implements StudentDAO {
	private SessionFactory sessionFactory;
	@Autowired
	private ClassCourseSemesterDAO classCourseSemesterDAO;
	@Autowired
	private ClassSemesterDAO classSemesterDAO;
	@Autowired
	private TimetableDAO timetableDAO;
	@Autowired
	private CourseSemesterDAO courseSemesterDAO;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addStudent(Student student) {
		getCurrentSession().persist(student);
	}

	@Override
	public void updateStudent(Student student) {
		getCurrentSession().update(student);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> listStudents() {
		List<Student> students = (List<Student>) getCurrentSession()
				.createQuery("FROM vn.edu.fpt.timetabling.model.Student").list();
		return students;
	}

	@Override
	public Student getStudentById(int studentId) {
		Student student = (Student) getCurrentSession().get(Student.class, new Integer(studentId));
		return student;
	}

	@Override
	public Student getStudentByCode(String code) {
		String hql = "FROM vn.edu.fpt.timetabling.model.Student S WHERE lower(S.studentCode) = lower(:code)";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("code", code);
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (Student) query.uniqueResult();
	}

	@Override
	public void deleteStudent(int studentId) {
		Student student = getStudentById(studentId);
		getCurrentSession().delete(student);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> listStudentsCanBeInClassCourseSemester(int classSemesterId, int specializedId,
			int detailSpecializedId, int semesterNumber, int classCourseSemesterId) {
		ClassSemester classSemester = classSemesterDAO.getClassSemesterById(classSemesterId, true);
		ClassCourseSemester classCourseSemester = classCourseSemesterDAO
				.getClassCourseSemesterById(classCourseSemesterId, false, false);
		if (classSemester == null && classCourseSemester == null) {
			return null;
		}
		Semester semester;
		Set<ClassCourseSemester> classCourseSemesters;
		if (classCourseSemester == null) {
			// get all classCourseSemesters of classSemester
			semester = classSemester.getSemester();
			classCourseSemesters = classSemester.getClassCourseSemesters();
		} else {
			semester = classCourseSemester.getClassSemester().getSemester();
			classCourseSemesters = new HashSet<ClassCourseSemester>();
			classCourseSemesters.add(classCourseSemester);
		}
		// get all timetable of all classCourseSemesters
		List<Timetable> timetablesOfClassSemester = timetableDAO
				.listTimetablesByClassCourseSemesters(classCourseSemesters);
		// select student not in this class
		String hql = "FROM vn.edu.fpt.timetabling.model.Student S"
				+ " WHERE S.specialized.specializedId = :specializedId AND S.semester = :semester";
		if (detailSpecializedId > 0) {
			hql += " AND S.detailSpecialized.specializedId = :detailSpecializedId";
		}
		hql += " AND S NOT IN (SELECT CCSS.student"
				+ " FROM vn.edu.fpt.timetabling.model.ClassCourseStudentSemester CCSS"
				+ " WHERE CCSS.classCourseSemester IN (:classCourseSemesters))";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("semester", semesterNumber);
		query.setParameter("specializedId", specializedId);
		query.setParameterList("classCourseSemesters", classCourseSemesters);
		if (detailSpecializedId > 0) {
			query.setParameter("detailSpecializedId", detailSpecializedId);
		}
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Student> studentsWithSuitableSpecialized = (List<Student>) query.list();
		List<Student> suitableStudents = new ArrayList<Student>();
		for (Student student : studentsWithSuitableSpecialized) {
			// validate each student
			boolean isSuitable = true;
			List<CourseSemester> learningCourseSemesters = courseSemesterDAO
					.listCourseSemestersByStudent(student.getStudentId());
			for (CourseSemester courseSemester : learningCourseSemesters) {
				// check if student learning same courseSemester already
				for (ClassCourseSemester classCourseSemesterTemp : classCourseSemesters) {
					if (classCourseSemesterTemp.getCourseSemester().getCourseSemesterId() == courseSemester
							.getCourseSemesterId()) {
						isSuitable = false;
						break;
					}
				}
				if (!isSuitable) {
					break;
				}
			}
			if (!isSuitable) {
				continue;
			}
			// get all timetables of this student
			List<Timetable> timetablesOfStudent = timetableDAO.listTimetablesByStudent(semester.getSemesterId(),
					student);
			for (Timetable timetableOfStudent : timetablesOfStudent) {
				// check if student's timetable not override timetable of
				// classSemester
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
			// this student is suitable
			if (isSuitable) {
				suitableStudents.add(student);
			}
		}
		return suitableStudents;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> listStudentsInClassCourseSemester(int classSemesterId, int classCourseSemesterId) {
		String hql = "SELECT CCSS.student FROM vn.edu.fpt.timetabling.model.ClassCourseStudentSemester CCSS";
		if (classCourseSemesterId != 0) {
			hql += " WHERE CCSS.classCourseSemester.classCourseSemesterId = :classCourseSemesterId";
		} else if(classSemesterId != 0) {
			hql += " WHERE CCSS.classCourseSemester.classSemester.classSemesterId = :classSemesterId";
		}
		Query query = getCurrentSession().createQuery(hql);
		if (classCourseSemesterId != 0) {
			query.setParameter("classCourseSemesterId", classCourseSemesterId);
		} else if(classSemesterId != 0) {
			query.setParameter("classSemesterId", classSemesterId);
		}
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Student> students = (List<Student>) query.list();
		return students;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> listStudentsWithoutClass(int semesterId) {
		String hql = "FROM vn.edu.fpt.timetabling.model.Student S" + " WHERE S NOT IN (SELECT CCSS.student"
				+ " FROM vn.edu.fpt.timetabling.model.ClassCourseStudentSemester CCSS"
				+ " WHERE CCSS.classCourseSemester.classSemester.semester.semesterId = :semesterId)";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("semesterId", semesterId);
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Student> students = (List<Student>) query.list();
		return students;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Student> listStudentsInClasses(int semesterId) {
		String hql = "FROM vn.edu.fpt.timetabling.model.Student S" + " WHERE S IN (SELECT CCSS.student"
				+ " FROM vn.edu.fpt.timetabling.model.ClassCourseStudentSemester CCSS"
				+ " WHERE CCSS.classCourseSemester.classSemester.semester.semesterId = :semesterId)";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("semesterId", semesterId);
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Student> students = (List<Student>) query.list();
		return students;
	}
	
	@Override
	public Student getStudentByEmail(String email) {
		String hql = "FROM vn.edu.fpt.timetabling.model.Student S WHERE lower(S.email) = lower(:email)";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("email", email);
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (Student) query.uniqueResult();
	}

	@Override
	public Student getLastStudent(int specializedId) {
		String hql = "FROM vn.edu.fpt.timetabling.model.Student S"
				+ " WHERE S.specialized.specializedId = :specializedId" + " ORDER BY S.studentCode DESC";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("specializedId", specializedId);
		query.setFirstResult(0);
		query.setMaxResults(1);
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		Object temp = query.uniqueResult();
		Student student;
		if (temp == null) {
			student = null;
		} else {
			student = (Student) temp;
		}
		return student;
	}
}
