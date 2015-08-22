package vn.edu.fpt.timetabling.dao;

import java.util.ArrayList;
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
				+ "WHERE T.classCourseSemester IN (:classCourseSemesters) " + "ORDER BY T.date, T.slot";
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
	public Timetable getTimetableByDateSlotClass(Date date, int slot, int classSemesterId) {
		String hql = "FROM vn.edu.fpt.timetabling.model.Timetable T" + " WHERE T.date = :date AND T.slot = :slot"
				+ " AND T.classCourseSemester.classCourseSemesterId IN (SELECT CCS.classCourseSemesterId"
				+ " FROM vn.edu.fpt.timetabling.model.ClassCourseSemester CCS"
				+ " WHERE CCS.classSemester.classSemesterId = :classSemesterId)"
				+ " AND T.date = :date AND T.slot = :slot";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("date", date);
		query.setParameter("slot", slot);
		query.setParameter("classSemesterId", classSemesterId);
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
		if (classCourseSemesters.isEmpty()) {
			return new ArrayList<Timetable>();
		}
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Timetable> listTimetablesByDate(Date date) {
		String hql = "FROM vn.edu.fpt.timetabling.model.Timetable T " + " WHERE T.date = :date";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("date", date);
		List<Timetable> timetables = (List<Timetable>) query.list();
		return timetables;
	}

	@Override
	public void deleteTimetablesByCCS(int classCourseSemesterId) {
		String hql = "DELETE FROM vn.edu.fpt.timetabling.model.Timetable T "
				+ " WHERE T.classCourseSemester.classCourseSemesterId = :classCourseSemesterId";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("classCourseSemesterId", classCourseSemesterId);
		query.executeUpdate();
	}

	@Override
	public void deleteTimetablesByCCSInWeek(int classSemesterId, Date startWeek, Date endWeek) {
		String hql = "DELETE FROM vn.edu.fpt.timetabling.model.Timetable T "
				+ " WHERE T.classCourseSemester.classCourseSemesterId IN (SELECT CCS.classCourseSemesterId"
				+ " FROM vn.edu.fpt.timetabling.model.ClassCourseSemester CCS"
				+ " WHERE CCS.classSemester.classSemesterId = :classSemesterId)"
				+ " AND T.date >= :startWeek AND T.date <= :endWeek";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("classSemesterId", classSemesterId);
		query.setParameter("startWeek", startWeek);
		query.setParameter("endWeek", endWeek);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Timetable> listTimetablesByClassAndCourseCode(int semesterId, String classCode, String courseCode) {
		String hql = "FROM vn.edu.fpt.timetabling.model.Timetable T "
				+ " WHERE T.classCourseSemester.classSemester.semester.semesterId = :semesterId"
				+ " AND T.classCourseSemester.classSemester.classFPT.code = :classCode"
				+ " AND T.classCourseSemester.courseSemester.course.code = :courseCode";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("semesterId", semesterId);
		query.setParameter("classCode", classCode);
		query.setParameter("courseCode", courseCode);
		List<Timetable> timetables = (List<Timetable>) query.list();
		return timetables;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Timetable> listTimetablesByTeacher(int teacherSemesterId) {
		String hql = "FROM vn.edu.fpt.timetabling.model.Timetable T "
				+ " WHERE T.teacherSemester.teacherSemesterId = :teacherSemesterId" + " ORDER BY T.date, T.slot";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("teacherSemesterId", teacherSemesterId);
		List<Timetable> timetables = (List<Timetable>) query.list();
		return timetables;
	}

	@Override
	public int deleteTimetablesBySemester(int semesterId) {
		String hql = "DELETE FROM vn.edu.fpt.timetabling.model.Timetable T"
				+ " WHERE T.classCourseSemester.classCourseSemesterId IN (SELECT CCS.classCourseSemesterId"
				+ " FROM vn.edu.fpt.timetabling.model.ClassCourseSemester CCS"
				+ " WHERE CCS.classSemester.semester.semesterId = :semesterId)";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("semesterId", semesterId);
		return query.executeUpdate();
	}

	@Override
	public long countNumberSlots(int semesterId, boolean haveTeacher) {
		String hql = "SELECT COUNT(T) FROM vn.edu.fpt.timetabling.model.Timetable T"
				+ " WHERE T.classCourseSemester.classSemester.semester.semesterId = :semesterId";
		if (haveTeacher) {
			hql += " AND T.teacherSemester IS NOT NULL";
		}
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("semesterId", semesterId);
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		Long result = (Long) query.uniqueResult();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Timetable> listTimetablesBySemester(int semesterId) {
		String hql = "FROM vn.edu.fpt.timetabling.model.Timetable T"
				+ " WHERE T.classCourseSemester.classSemester.semester.semesterId = :semesterId";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("semesterId", semesterId);
		List<Timetable> timetables = (List<Timetable>) query.list();
		return timetables;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Timetable> listTimetablesByClassSemester(int classSemesterId) {
		String hql = "FROM vn.edu.fpt.timetabling.model.Timetable T"
				+ " WHERE T.classCourseSemester.classSemester.classSemesterId = :classSemesterId" + " ORDER BY T.date";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("classSemesterId", classSemesterId);
		List<Timetable> timetables = (List<Timetable>) query.list();
		return timetables;
	}
}
