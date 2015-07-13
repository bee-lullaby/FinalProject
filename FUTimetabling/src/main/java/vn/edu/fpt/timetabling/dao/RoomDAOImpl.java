package vn.edu.fpt.timetabling.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vn.edu.fpt.timetabling.model.Room;

public class RoomDAOImpl implements RoomDAO {

	private static final Logger logger = LoggerFactory.getLogger(RoomDAOImpl.class);

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
		logger.info("Room was saved successfully, Room details=" + room);
	}

	@Override
	public void updateRoom(Room room) {
		getCurrentSession().update(room);
		logger.info("Room was updated successfully, Room details=" + room);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Room> listRooms() {
		List<Room> rooms = (List<Room>) getCurrentSession()
				.createQuery("from vn.edu.fpt.timetabling.model.Room").list();
		for (Room room : rooms) {
			logger.info("Room list:" + room);
		}
		return rooms;
	}

	@Override
	public Room getRoomById(int roomId) {
		Room room = (Room) getCurrentSession().get(Room.class, new Integer(roomId));
		if (room != null) {
			logger.info("Room was loaded successfully, Room details=" + room);
		}
		return room;
	}

//	@Override
//	public Room getCourseByCode(String code) {
//		String hql = "FROM vn.edu.fpt.timetabling.model.Course C WHERE C.code = :code";
//
//		Query query = getCurrentSession().createQuery(hql);
//		query.setParameter("code", code);
//		Object temp = query.uniqueResult();
//		if (temp != null) {
//			Course course = (Course) temp;
//			logger.info("Course was loaded successfully, course details=" + course);
//			return course;
//		} else {
//			return null;
//		}
//	}

	@Override
	public void deleteRoom(int roomId) {
		Room room = getRoomById(roomId);
		if (room != null) {
			getCurrentSession().delete(room);
			logger.info("Course was deleted successfully, course details=" + room);
		}
	}
}
