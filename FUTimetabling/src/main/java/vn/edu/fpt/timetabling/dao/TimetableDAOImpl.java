package vn.edu.fpt.timetabling.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.edu.fpt.timetabling.model.ClassCourseSemester;
import vn.edu.fpt.timetabling.model.Student;
import vn.edu.fpt.timetabling.model.Timetable;

@Repository
public class TimetableDAOImpl implements TimetableDAO {
	private SessionFactory sessionFactory;
	@Autowired
	private ClassCourseSemesterDAO classCourseSemesterDAO;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addTimetable(Timetable timetable) {
		getCurrentSession().persist(timetable);
	}

	@Override
	public void updateTimetable(Timetable timetable) {
		getCurrentSession().update(timetable);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Timetable> listTimetables() {
		List<Timetable> timetables = (List<Timetable>) getCurrentSession()
				.createQuery("FROM vn.edu.fpt.timetabling.model.Timetable").list();
		return timetables;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Timetable> listTimetablesByCCSs(List<ClassCourseSemester> classCourseSemesters) {
		String hql = "FROM vn.edu.fpt.timetabling.model.Timetable T "
				+ "WHERE T.classCourseSemester IN (:classCourseSemesters)";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameterList("classCourseSemesters", classCourseSemesters);
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Timetable> timetables = (List<Timetable>) query.list();
		return timetables;
	}

	@Override
	public Timetable getTimetableById(int timetableId) {
		Timetable timetable = (Timetable) getCurrentSession().get(Timetable.class, new Integer(timetableId));
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
		return (Timetable) query.uniqueResult();
	}

	@Override
	public void deleteTimetable(int timetableId) {
		Timetable timetable = getTimetableById(timetableId);
		getCurrentSession().delete(timetable);
	}

	@Override
	public List<Timetable> listTimetablesByStudent(int semesterId, Student student) {
		List<ClassCourseSemester> classCourseSemesters = classCourseSemesterDAO
				.listClassCourseSemesterByStudent(semesterId, student.getStudentId());
		List<Timetable> timetables = listTimetablesByCCSs(classCourseSemesters);
		return timetables;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Timetable> listTimetablesByClassCourseSemesters(Set<ClassCourseSemester> classCourseSemesters) {
		String hql = "FROM vn.edu.fpt.timetabling.model.Timetable T"
				+ " WHERE T.classCourseSemester IN (:classCourseSemesters)";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameterList("classCourseSemesters", classCourseSemesters);
		List<Timetable> timetables = (List<Timetable>) query.list();
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
		query.setParameterList("classCourseSemesters", classCourseSemesters);
		query.setParameter("startWeek", startWeek);
		query.setParameter("endWeek", endWeek);
		List<Timetable> timetables = (List<Timetable>) query.list();
		return timetables;
	}
}
