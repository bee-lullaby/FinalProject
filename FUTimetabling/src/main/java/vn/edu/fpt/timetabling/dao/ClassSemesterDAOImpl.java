package vn.edu.fpt.timetabling.dao;

import java.util.ArrayList;
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
import vn.edu.fpt.timetabling.model.ClassCourseStudentSemester;
import vn.edu.fpt.timetabling.model.ClassFPT;
import vn.edu.fpt.timetabling.model.ClassSemester;
import vn.edu.fpt.timetabling.model.Course;
import vn.edu.fpt.timetabling.model.CourseSemester;
import vn.edu.fpt.timetabling.model.ProgramSemester;
import vn.edu.fpt.timetabling.model.ProgramSemesterDetail;
import vn.edu.fpt.timetabling.model.Semester;
import vn.edu.fpt.timetabling.model.Specialized;
import vn.edu.fpt.timetabling.model.Student;
import vn.edu.fpt.timetabling.service.ClassCourseSemesterService;
import vn.edu.fpt.timetabling.service.ClassSemesterService;
import vn.edu.fpt.timetabling.service.ClassService;
import vn.edu.fpt.timetabling.utils.Const;
import vn.edu.fpt.timetabling.utils.Const.ClassType;

@Repository
public class ClassSemesterDAOImpl implements ClassSemesterDAO {
	private static final Logger logger = LoggerFactory.getLogger(ClassSemesterDAO.class);

	private SessionFactory sessionFactory;

	@Autowired
	private SemesterDAO semesterDAO;

	@Autowired
	private StudentDAO studentDAO;

	@Autowired
	private ClassService classService;
	@Autowired
	private ClassCourseSemesterService classCourseSemesterService;
	@Autowired
	private ClassSemesterService classSemesterService;

	@Autowired
	private SpecializedDAO specializedDAO;
	@Autowired
	private ProgramSemesterDAO programSemesterDAO;

	@Autowired
	private ClassCourseStudentSemesterDAO classCourseStudentSemesterDAO;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addClassSemester(ClassSemester classSemester) {
		getCurrentSession().persist(classSemester);
		logger.info("ClassSemester was saved successfully, ClassSemester details=" + classSemester);
	}

	@Override
	public void updateClassSemester(ClassSemester classSemester) {
		getCurrentSession().update(classSemester);
		logger.info("ClassSemester was updated successfully, ClassSemester details=" + classSemester);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClassSemester> listClassSemesters(boolean jointClassCourseSemester) {
		String hql = "FROM vn.edu.fpt.timetabling.model.ClassSemester C";
		if (jointClassCourseSemester) {
			hql += " LEFT OUTER JOIN FETCH C.classCourseSemesters";
		}
		Query query = getCurrentSession().createQuery(hql);
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<ClassSemester> classSemesters = (List<ClassSemester>) query.list();
		for (ClassSemester classSemester : classSemesters) {
			logger.info("ClassSemester list:" + classSemester);
		}
		return classSemesters;
	}

	@Override
	public ClassSemester getClassSemesterById(int classSemesterId, boolean jointClassCourseSemester) {
		String hql = "FROM vn.edu.fpt.timetabling.model.ClassSemester C";
		if (jointClassCourseSemester) {
			hql += " LEFT OUTER JOIN FETCH C.classCourseSemesters";
		}
		hql += " WHERE C.classSemesterId = :classSemesterId";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("classSemesterId", classSemesterId);
		Object temp = query.uniqueResult();
		if (temp != null) {
			ClassSemester classSemester = (ClassSemester) temp;
			logger.info("ClassSemester was loaded successfully, ClassSemester details=" + classSemester);
			return classSemester;
		} else {
			return null;
		}
	}

	@Override
	public ClassSemester getClassSemesterByClassSemester(int semesterId, int classId,
			boolean jointClassCourseSemester) {
		String hql = "FROM vn.edu.fpt.timetabling.model.ClassSemester C";
		if (jointClassCourseSemester) {
			hql += " LEFT OUTER JOIN FETCH C.classCourseSemesters";
		}
		hql += " WHERE C.semester = :semester AND C.classFPT = :classFPT";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("semester", semesterDAO.getSemesterById(semesterId, false, false, false, false));
		query.setParameter("classFPT", classService.getClassById(classId));
		Object temp = query.uniqueResult();
		if (temp != null) {
			ClassSemester classSemester = (ClassSemester) temp;
			logger.info("ClassSemester was loaded successfully, ClassSemester details=" + classSemester);
			return classSemester;
		} else {
			return null;
		}
	}

	@Override
	public ClassSemester getClassSemesterByCode(String classCode, int semesterId, boolean jointClassCourseSemester) {
		String hql = "FROM vn.edu.fpt.timetabling.model.ClassSemester C";
		if (jointClassCourseSemester) {
			hql += " LEFT OUTER JOIN FETCH C.classCourseSemesters";
		}
		hql += " WHERE C.semester = :semester AND C.classFPT = :classFPT";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("semester", semesterDAO.getSemesterById(semesterId, false, false, false, false));
		query.setParameter("classFPT", classService.getClassByCode(classCode));
		Object temp = query.uniqueResult();
		if (temp != null) {
			ClassSemester classSemester = (ClassSemester) temp;
			logger.info("ClassSemester was loaded successfully, ClassSemester details=" + classSemester);
			return classSemester;
		} else {
			return null;
		}
	}

	@Override
	public void deleteClassSemester(int classSemesterId) {
		ClassSemester classSemester = getClassSemesterById(classSemesterId, false);
		if (classSemester != null) {
			getCurrentSession().delete(classSemester);
			logger.info("classSemester was deleted successfully, classSemester details=" + classSemester);
		}
	}

	@Override
	public long getNumberOfStudents(int classSemesterId) {
		ClassSemester classSemester = getClassSemesterById(classSemesterId, false);
		if (classSemester == null) {
			return 0;
		}
		String hql = "SELECT COUNT(DISTINCT CCSS.student) FROM vn.edu.fpt.timetabling.model.ClassSemester CS"
				+ " JOIN CS.classCourseSemesters CCS" + " JOIN CCS.classCourseStudentSemesters CCSS"
				+ " WHERE CS.classSemesterId = :classSemesterId";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("classSemesterId", classSemesterId);
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		long numberOfStudents = (Long) query.uniqueResult();
		return numberOfStudents;
	}

	@Override
	public void autoPutStudentsIntoClassSemester(int classSemesterId) {
		ClassSemester classSemester = getClassSemesterById(classSemesterId, true);
		if (classSemester == null) {
			return;
		}
		ClassFPT classFPT = classSemester.getClassFPT();
		int specializedId = classFPT.getSpecialized().getSpecializedId();
		int detailSpecializedId = 0;
		if (classFPT.getDetailSpecialized() != null) {
			detailSpecializedId = classFPT.getDetailSpecialized().getSpecializedId();
		}
		int semesterNumber = classSemester.getSemesterNumber();
		List<Student> students = studentDAO.listStudentsCanBeInClassCourseSemester(classSemesterId, specializedId,
				detailSpecializedId, semesterNumber, 0);
		Set<ClassCourseSemester> classCourseSemesters = classSemester.getClassCourseSemesters();
		if (getNumberOfStudents(classSemesterId) < Const.MAX_NUMBER_OF_STUDENTS_IN_CLASS && students.size() > 0) {
			for (Student student : students) {
				for (ClassCourseSemester classCourseSemester : classCourseSemesters) {
					ClassCourseStudentSemester classCourseStudentSemester = new ClassCourseStudentSemester();
					classCourseStudentSemester.setClassCourseSemester(classCourseSemester);
					classCourseStudentSemester.setStudent(student);
					classCourseStudentSemesterDAO.addClassCourseStudentSemester(classCourseStudentSemester);
				}
				student.setClassSemester(classSemester);
				if (getNumberOfStudents(classSemesterId) == Const.MAX_NUMBER_OF_STUDENTS_IN_CLASS) {
					break;
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClassSemester> listClassSemestersBySpecializedSemester(int semesterId, int specializedId,
			int detailSpecializedId, int semesterNumber) {
		Semester semester = semesterDAO.getSemesterById(semesterId, false, false, false, false);
		Specialized specialized = specializedDAO.getSpecializedById(specializedId);
		Specialized detailSpecialized = specializedDAO.getSpecializedById(detailSpecializedId);
		if (semester == null || specialized == null) {
			return new ArrayList<ClassSemester>();
		}
		String hql = "FROM vn.edu.fpt.timetabling.model.ClassSemester CS"
				+ " WHERE CS.classFPT.specialized = :specialized" + " AND CS.semesterNumber = :semesterNumber"
				+ " AND CS.semester = :semester";
		if (detailSpecialized != null) {
			hql += " AND CS.classFPT.detailSpecialized = :detailSpecialized";
		}
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("specialized", specialized);
		query.setParameter("semesterNumber", semesterNumber);
		query.setParameter("semester", semester);
		if (detailSpecialized != null) {
			query.setParameter("detailSpecialized", detailSpecialized);
		}
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<ClassSemester> classSemesters = (List<ClassSemester>) query.list();
		return classSemesters;
	}

	private void createNewClass(Specialized specialized, Specialized detailSpecialized, Semester semester,
			int semesterNumber, Student student) {
		int specializedId = specialized.getSpecializedId();
		int detailSpecializedId = 0;
		int semesterId = semester.getSemesterId();
		if (detailSpecialized != null) {
			detailSpecializedId = detailSpecialized.getSpecializedId();
		}
		ClassFPT classFPT = new ClassFPT();
		classFPT.setType(ClassType.SPECIALIZED);
		classFPT.setSpecialized(specialized);
		classFPT.setDetailSpecialized(detailSpecialized);
		ProgramSemester programSemester = programSemesterDAO.getProgramSemesterBySpecializedSemester(semesterId,
				specializedId, detailSpecializedId, semesterNumber);
		int batch = programSemester.getBatch();
		classFPT.setBatch(batch);
		classFPT.setCourse(null);
		String classCodePrefix = classFPT.getSpecialized().getCode() + String.format("%02d", batch);
		classFPT.setNumber(classService.getNextClassNumber(classCodePrefix));
		classFPT.setCode(classCodePrefix + String.format("%02d", classFPT.getNumber()));
		classService.addClass(classFPT);
		ClassSemester classSemester = new ClassSemester();
		classSemester.setSemester(semester);
		classSemester.setClassFPT(classFPT);
		classSemester.setSemesterNumber(semesterNumber);
		classSemesterService.addClassSemester(classSemester);
		Set<ProgramSemesterDetail> programSemesterDetails = programSemester.getProgramSemesterDetails();
		List<CourseSemester> courseSemesters = new ArrayList<CourseSemester>();
		CourseSemester semesterLong = null;
		for (ProgramSemesterDetail programSemesterDetail : programSemesterDetails) {
			CourseSemester courseSemester = programSemesterDetail.getCourseSemester();
			courseSemesters.add(courseSemester);
			if (programSemesterDetail.isSemesterLong()) {
				semesterLong = courseSemester;
			}
		}
		List<CourseSemester> allBlock = new ArrayList<CourseSemester>();
		List<CourseSemester> block1 = new ArrayList<CourseSemester>();
		List<CourseSemester> block2 = new ArrayList<CourseSemester>();
		for (int i = 0; i < courseSemesters.size(); i++) {
			CourseSemester courseSemester = courseSemesters.get(i);
			Course condition = courseSemester.getCourseCondition();
			boolean notCondition = true;
			for (int j = i + 1; j < courseSemesters.size(); j++) {
				CourseSemester courseSemesterTemp = courseSemesters.get(j);
				Course condition2 = courseSemesterTemp.getCourseCondition();
				if (condition != null && condition.getCourseId() == courseSemesterTemp.getCourse().getCourseId()) {
					block2.add(courseSemester);
					block1.add(courseSemesterTemp);
					courseSemesters.remove(i);
					courseSemesters.remove(j);
					notCondition = false;
					break;
				} else if (condition2 != null && courseSemester.getCourse().getCourseId() == condition2.getCourseId()) {
					block1.add(courseSemester);
					block2.add(courseSemesterTemp);
					courseSemesters.remove(i);
					courseSemesters.remove(j);
					notCondition = false;
					break;
				}
			}
			if (notCondition) {
				allBlock.add(courseSemester);
				courseSemesters.remove(i);
			}
			i--;
		}
		List<List<CourseSemester>> courseSemestersTemp = new ArrayList<List<CourseSemester>>();
		courseSemestersTemp.add(allBlock);
		courseSemestersTemp.add(block1);
		courseSemestersTemp.add(block2);
		for (List<CourseSemester> list : courseSemestersTemp) {
			for (CourseSemester courseSemester : list) {
				ClassCourseSemester classCourseSemester = new ClassCourseSemester();
				classCourseSemester.setClassSemester(classSemester);
				classCourseSemester.setCourseSemester(courseSemester);
				classCourseSemester.setBlockCondition(0);
				if (semesterLong != null
						&& courseSemester.getCourseSemesterId() == semesterLong.getCourseSemesterId()) {
					classCourseSemester.setSemesterLong(true);
				} else {
					classCourseSemester.setSemesterLong(false);
				}
				classCourseSemesterService.addClassCourseSemester(classCourseSemester);
				ClassCourseStudentSemester classCourseStudentSemester = new ClassCourseStudentSemester();
				classCourseStudentSemester.setClassCourseSemester(classCourseSemester);
				classCourseStudentSemester.setStudent(student);
				classCourseStudentSemesterDAO.addClassCourseStudentSemester(classCourseStudentSemester);
			}
		}
	}

	@Override
	public void autoPutStudentsIntoClassSemesters(int semesterId) {
		Semester semester = semesterDAO.getSemesterById(semesterId, false, false, false, false);
		if (semester == null) {
			return;
		}
		List<Student> students = studentDAO.listStudentsWithoutClass();
		for (Student student : students) {
			Specialized specialized = student.getSpecialized();
			Specialized detailSpecialized = student.getDetailSpecialized();
			int detailSpecializedId = 0;
			if (detailSpecialized != null) {
				detailSpecializedId = detailSpecialized.getSpecializedId();
			}
			int semesterNumber = student.getSemester();
			List<ClassSemester> classSemesters = listClassSemestersBySpecializedSemester(semesterId,
					specialized.getSpecializedId(), detailSpecializedId, semesterNumber);
			boolean put = false;
			for (ClassSemester classSemester : classSemesters) {
				Set<ClassCourseSemester> classCourseSemesters = classSemester.getClassCourseSemesters();
				if (getNumberOfStudents(classSemester.getClassSemesterId()) < Const.MAX_NUMBER_OF_STUDENTS_IN_CLASS) {
					for (ClassCourseSemester classCourseSemester : classCourseSemesters) {
						ClassCourseStudentSemester classCourseStudentSemester = new ClassCourseStudentSemester();
						classCourseStudentSemester.setClassCourseSemester(classCourseSemester);
						classCourseStudentSemester.setStudent(student);
						classCourseStudentSemesterDAO.addClassCourseStudentSemester(classCourseStudentSemester);
					}
					student.setClassSemester(classSemester);
					put = true;
					break;
				}
			}
			if (!put) {
				createNewClass(specialized, detailSpecialized, semester, semesterNumber, student);
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean isStudentInClassSemester(int studentId, int classSemesterId) {
		String hql = "FROM vn.edu.fpt.timetabling.model.ClassSemester CS" + " JOIN CS.classCourseSemesters CCS"
				+ " JOIN CCS.classCourseStudentSemesters CCSS"
				+ " WHERE CS.classSemesterId = :classSemesterId AND CCSS.student.studentId = :studentId";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("studentId", studentId);
		query.setParameter("classSemesterId", classSemesterId);
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<ClassSemester> classSemesters = (List<ClassSemester>) query.list();
		return (classSemesters != null && !classSemesters.isEmpty());
	}
}
