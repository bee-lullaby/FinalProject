package vn.edu.fpt.timetabling.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import vn.edu.fpt.timetabling.model.ClassSemester;

@Repository
public class ClassSemesterDAOImpl implements ClassSemesterDAO {
	private static final Logger logger = LoggerFactory.getLogger(ClassSemesterDAO.class);

	private SessionFactory sessionFactory;

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
		hql += " WHERE C.semester.semesterId = :semesterId AND C.classFPT.classId = :classId";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("semesterId", semesterId);
		query.setParameter("classId", classId);
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
		hql += " WHERE C.semester.semesterId = :semesterId AND C.classFPT.code = :classCode";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("semesterId", semesterId);
		query.setParameter("classCode", classCode);
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

	@SuppressWarnings("unchecked")
	@Override
	public List<ClassSemester> listClassSemestersBySpecializedSemester(int semesterId, int specializedId,
			int detailSpecializedId, int semesterNumber) {
		String hql = "FROM vn.edu.fpt.timetabling.model.ClassSemester CS"
				+ " WHERE CS.classFPT.specialized.specializedId = :specializedId"
				+ " AND CS.semesterNumber = :semesterNumber" + " AND CS.semester.semesterId = :semesterId";
		if (detailSpecializedId != 0) {
			hql += " AND CS.classFPT.detailSpecialized.specializedId = :detailSpecializedId";
		}
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("specializedId", specializedId);
		query.setParameter("semesterNumber", semesterNumber);
		query.setParameter("semesterId", semesterId);
		if (detailSpecializedId != 0) {
			query.setParameter("detailSpecializedId", detailSpecializedId);
		}
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<ClassSemester> classSemesters = (List<ClassSemester>) query.list();
		return classSemesters;
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
