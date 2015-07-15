package vn.edu.fpt.timetabling.dao;

import java.util.List;

import vn.edu.fpt.timetabling.model.Room;

public interface RoomDAO {
	public void addRoom(Room room);

	public void updateRoom(Room room);

	public List<Room> listRooms(boolean jointTimetable);

	public Room getRoomById(int roomId, boolean jointTimetable);

	public void deleteRoom(int roomId);
}
