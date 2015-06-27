package vn.edu.fpt.timetabling.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import vn.edu.fpt.timetabling.model.Timetable;

@Repository
public class TimetableDAOImpl implements TimetableDAO {

	private static final Logger logger = LoggerFactory
			.getLogger(ClassDAOImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addTimetable(Timetable timetable) {
		// TODO Auto-generated method stub
		getCurrentSession().persist(timetable);
		logger.info("Timetable was saved successfully, Timetable details="
				+ timetable);
	}

	@Override
	public void updateTimetable(Timetable timetable) {
		// TODO Auto-generated method stub
		getCurrentSession().update(timetable);
		logger.info("Timetable was updated successfully, Timetable details="
				+ timetable);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Timetable> listTimetables() {
		// TODO Auto-generated method stub
		List<Timetable> timetables = (List<Timetable>) getCurrentSession()
				.createQuery("from vn.edu.fpt.timetabling.model.Timetable")
				.list();
		for (Timetable timetable : timetables) {
			logger.info("Timetable list:" + timetable);
		}
		return timetables;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Timetable> listTimetablesByCCSId(
			List<Integer> classCourseSemesterIds) {
		// TODO Auto-generated method stub
		List<Timetable> timetables = new ArrayList<Timetable>();
		String hql = "FROM vn.edu.fpt.timetabling.model.Timetable T WHERE T.class_course_semester_id = :class_course_semester_id";
		for (int classCourseSemesterId : classCourseSemesterIds) {
			Query query = getCurrentSession().createQuery(hql);
			query.setParameter("class_course_semester_id",
					classCourseSemesterId);
			timetables.addAll((List<Timetable>) query.list());
		}

		if (!timetables.isEmpty()) {
			for (Timetable timetable : timetables) {
				logger.info("Timetable list:" + timetable);
			}
		}
		return timetables;
	}

	@Override
	public Timetable getTimetableById(int timetableId) {
		// TODO Auto-generated method stub
		Timetable timetable = (Timetable) getCurrentSession().get(
				Timetable.class, new Integer(timetableId));
		if (timetable != null) {
			logger.info("Timetable was loaded successfully, timetable details="
					+ timetable);
		}
		return timetable;
	}

	@Override
	public void deleteTimetable(int timetableId) {
		// TODO Auto-generated method stub
		Timetable timetable = getTimetableById(timetableId);
		if (timetable != null) {
			getCurrentSession().delete(timetable);
			logger.info("Timetable was deleted successfully, timetable details="
					+ timetable);
		}
	}

}
