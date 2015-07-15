package vn.edu.fpt.timetabling.dao;

import java.util.List;

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
	public List<Room> listRooms() {
		List<Room> rooms = (List<Room>) getCurrentSession().createQuery("FROM vn.edu.fpt.timetabling.model.Room")
				.list();
		return rooms;
	}

	@Override
	public Room getRoomById(int roomId) {
		Room room = (Room) getCurrentSession().get(Room.class, new Integer(roomId));
		return room;
	}

	@Override
	public void deleteRoom(int roomId) {
		Room room = getRoomById(roomId);
		getCurrentSession().delete(room);
	}
}
