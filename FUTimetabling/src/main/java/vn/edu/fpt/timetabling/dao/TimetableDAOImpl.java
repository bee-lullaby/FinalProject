package vn.edu.fpt.timetabling.dao;

import java.util.ArrayList;
import java.util.Date;
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
import vn.edu.fpt.timetabling.model.Student;
import vn.edu.fpt.timetabling.model.Timetable;

@Repository
public class TimetableDAOImpl implements TimetableDAO {

	private static final Logger logger = LoggerFactory.getLogger(TimetableDAOImpl.class);

	@Autowired
	private ClassCourseSemesterDAO classCourseSemesterDAO;
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addTimetable(Timetable timetable) {
		getCurrentSession().persist(timetable);
		logger.info("Timetable was saved successfully, Timetable details=" + timetable);
	}

	@Override
	public void updateTimetable(Timetable timetable) {
		getCurrentSession().update(timetable);
		logger.info("Timetable was updated successfully, Timetable details=" + timetable);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Timetable> listTimetables() {
		List<Timetable> timetables = (List<Timetable>) getCurrentSession()
				.createQuery("from vn.edu.fpt.timetabling.model.Timetable").list();
		for (Timetable timetable : timetables) {
			logger.info("Timetable list:" + timetable);
		}
		return timetables;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Timetable> listTimetablesByCCSId(List<ClassCourseSemester> classCourseSemesters) {
		String hql = "FROM vn.edu.fpt.timetabling.model.Timetable T "
				+ "WHERE T.classCourseSemester IN (:classCourseSemesters)";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameterList("classCourseSemesters", classCourseSemesters);
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Timetable> timetables = (List<Timetable>) query.list();
		if (!timetables.isEmpty()) {
			for (Timetable timetable : timetables) {
				logger.info("Timetable list:" + timetable);
			}
		}
		return timetables;
	}

	@Override
	public Timetable getTimetableById(int timetableId) {
		Timetable timetable = (Timetable) getCurrentSession().get(Timetable.class, new Integer(timetableId));
		if (timetable != null) {
			logger.info("Timetable was loaded successfully, timetable details=" + timetable);
		}
		return timetable;
	}

	@Override
	public Timetable getTimetableByDateSlotClassCourse(Date date, int slot, int classCourseSemesterId) {
		String hql = "FROM vn.edu.fpt.timetabling.model.Timetable T" + " WHERE T.date = :date AND T.slot = :slot"
				+ " AND T.classCourseSemester.classCourseSemesterId = :classCourseSemesterId";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("date", date);
		query.setParameter("slot", slot);
		query.setParameter("classCourseSemesterId", classCourseSemesterId);
		Object temp = query.uniqueResult();
		if (temp != null) {
			Timetable timetable = (Timetable) temp;
			logger.info("Timetable was loaded successfully, Timetable details=" + timetable);
			return timetable;
		} else {
			return null;
		}
	}

	@Override
	public void deleteTimetable(int timetableId) {
		Timetable timetable = getTimetableById(timetableId);
		if (timetable != null) {
			getCurrentSession().delete(timetable);
			logger.info("Timetable was deleted successfully, timetable details=" + timetable);
		}
	}

	@Override
	public List<Timetable> listTimetablesByStudent(int semesterId, Student student) {
		List<ClassCourseSemester> classCourseSemesters = classCourseSemesterDAO
				.listClassCourseSemesterByStudent(semesterId, student.getStudentId());
		if (classCourseSemesters.isEmpty()) {
			return new ArrayList<Timetable>();
		}
		List<Timetable> timetables = listTimetablesByCCSId(classCourseSemesters);
		return timetables;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Timetable> listTimetablesByClassCourseSemesters(Set<ClassCourseSemester> classCourseSemesters) {
		String hql = "FROM vn.edu.fpt.timetabling.model.Timetable T"
				+ " WHERE T.classCourseSemester IN (:classCourseSemesters)";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("classCourseSemesters", classCourseSemesters);
		List<Timetable> timetables = (List<Timetable>) query.list();
		if (!timetables.isEmpty()) {
			for (Timetable timetable : timetables) {
				logger.info("Timetable list:" + timetable);
			}
		}
		return timetables;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Timetable> listTimetablesByClassCourseSemestersInWeek(Set<ClassCourseSemester> classCourseSemesters,
			Date startWeek, Date endWeek) {
		String hql = "FROM vn.edu.fpt.timetabling.model.Timetable T "
				+ " WHERE T.classCourseSemester IN (:classCourseSemesters)"
				+ " AND T.date >= :startWeek AND T.date <= :endWeek";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("classCourseSemesters", classCourseSemesters);
		query.setParameter("startWeek", startWeek);
		query.setParameter("endWeek", endWeek);
		List<Timetable> timetables = (List<Timetable>) query.list();
		if (!timetables.isEmpty()) {
			for (Timetable timetable : timetables) {
				logger.info("Timetable list:" + timetable);
			}
		}
		return timetables;
	}

}
