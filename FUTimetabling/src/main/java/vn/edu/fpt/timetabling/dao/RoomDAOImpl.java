package vn.edu.fpt.timetabling.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import vn.edu.fpt.timetabling.model.Room;

public class RoomDAOImpl implements RoomDAO {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addRoom(Room room) {
		getCurrentSession().persist(room);
	}

	@Override
	public void updateRoom(Room room) {
		getCurrentSession().update(room);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Room> listRooms(boolean jointTimetable) {
		String hql = "FROM vn.edu.fpt.timetabling.model.Room R";
		if (jointTimetable) {
			hql += " LEFT OUTER JOIN FETCH R.timetables";
		}
		Query query = getCurrentSession().createQuery(hql);
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Room> rooms = (List<Room>) query.list();
		return rooms;
	}

	@Override
	public Room getRoomById(int roomId, boolean jointTimetable) {
		String hql = "FROM vn.edu.fpt.timetabling.model.Room R";
		if (jointTimetable) {
			hql += " LEFT OUTER JOIN FETCH R.timetables";
		}
		hql += " WHERE R.roomId = :roomId";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("roomId", roomId);
		Object temp = query.uniqueResult();
		if (temp != null) {
			Room room = (Room) temp;
			return room;
		} else {
			return null;
		}
	}

	@Override
	public Room getRoomByCode(String code, boolean jointTimetable) {
		String hql = "FROM vn.edu.fpt.timetabling.model.Room R";
		if (jointTimetable) {
			hql += " LEFT OUTER JOIN FETCH R.timetables";
		}
		hql += " WHERE R.code = :code";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("code", code);
		Object temp = query.uniqueResult();
		if (temp != null) {
			Room room = (Room) temp;
			return room;
		} else {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Room> listRoomsInTimetable(int semesterId) {
		String hql = "FROM vn.edu.fpt.timetabling.model.Room R" + " WHERE R IN (SELECT T.room"
				+ " FROM vn.edu.fpt.timetabling.model.Timetable T"
				+ " WHERE T.classCourseSemester.classSemester.semester.semesterId = :semesterId)";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("semesterId", semesterId);
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Room> rooms = (List<Room>) query.list();
		return rooms;
	}
	
	@Override
	public void deleteRoom(int roomId) {
		Room room = getRoomById(roomId, false);
		if (room != null) {
			getCurrentSession().delete(room);
		}
	}
}
