package vn.edu.fpt.timetabling.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import vn.edu.fpt.timetabling.model.ClassSemester;

public class ClassSemesterDAOImpl implements ClassSemesterDAO {
	private static final Logger logger = LoggerFactory
			.getLogger(ClassSemesterDAO.class);

	private SessionFactory sessionFactory;
	
	@Autowired
	private SemesterDAO semesterDAO;

	@Autowired
	private ClassDAO classDAO;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	
	@Override
	public void addClassSemester(ClassSemester classSemester) {
		getCurrentSession().persist(classSemester);
		logger.info("ClassSemester was saved successfully, ClassSemester details="
				+ classSemester);
	}

	@Override
	public void updateClassSemester(ClassSemester classSemester) {
		getCurrentSession().update(classSemester);
		logger.info("ClassSemester was updated successfully, ClassSemester details="
				+ classSemester);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClassSemester> listClassSemesters() {
		List<ClassSemester> classSemesters = (List<ClassSemester>) getCurrentSession()
				.createQuery("from vn.edu.fpt.timetabling.model.ClassSemester")
				.list();
		for (ClassSemester classSemester : classSemesters) {
			logger.info("ClassSemester list:" + classSemester);
		}
		return classSemesters;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ClassSemester> listClassSemesterBySemester(int semesterId) {
		String hql = "FROM vn.edu.fpt.timetabling.model.ClassSemester C WHERE C.semester = :semester";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("semester", semesterDAO.getSemesterById(semesterId));
		List<ClassSemester> classSemesters = (List<ClassSemester>) query.list();
		for (ClassSemester classSemester : classSemesters) {
			logger.info("ClassSemester list:" + classSemester);
		}
		return classSemesters;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ClassSemester getClassSemesterByClassSemester(int semesterId, int classId) {
		String hql = "FROM vn.edu.fpt.timetabling.model.ClassSemester C WHERE C.semester = :semester AND C.classFPT = :classFPT";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("semester", semesterDAO.getSemesterById(semesterId));
		query.setParameter("classFPT", classDAO.getClassById(classId));
		List<ClassSemester> classSemesters = (List<ClassSemester>) query.list();
		if (!classSemesters.isEmpty()) {
			logger.info("ClassSemester was loaded successfully, ClassSemester details="
					+ classSemesters.get(0));
			return classSemesters.get(0);
		} else {
			return null;
		}
	}
	
	@Override
	public ClassSemester getClassSemesterById(int classSemesterId) {
		ClassSemester classSemester = (ClassSemester) getCurrentSession().get(
				ClassSemester.class, new Integer(classSemesterId));
		if (classSemester != null) {
			logger.info("classSemester was loaded successfully, classSemester details="
					+ classSemester);
		}
		return classSemester;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ClassSemester getClassSemesterByCode(String code) {
		String hql = "FROM vn.edu.fpt.timetabling.model.ClassSemester C WHERE C.classFPT = :classFPT";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("classFPT", classDAO.getClassByCode(code));
		List<ClassSemester> classSemesters = (List<ClassSemester>) query.list();
		if (!classSemesters.isEmpty()) {
			logger.info("ClassSemester was loaded successfully, ClassSemester details="
					+ classSemesters.get(0));
			return classSemesters.get(0);
		} else {
			return null;
		}
	}
	
	@Override
	public void deleteClassSemester(int classSemesterId) {
		ClassSemester classSemester = getClassSemesterById(classSemesterId);
		if (classSemester != null) {
			getCurrentSession().delete(classSemester);
			logger.info("classSemester was deleted successfully, classSemester details="
					+ classSemester);
		}
	}

}
