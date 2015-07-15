package vn.edu.fpt.timetabling.service;

import java.io.File;
import java.util.List;

import vn.edu.fpt.timetabling.model.Room;

public interface RoomService {
	public void addRoom(Room room);
	
	public void addRoomFromFile(File rooms);

	public void updateRoom(Room room);

	public List<Room> listRooms(boolean jointTimetable);

	public Room getRoomById(int roomId, boolean jointTimetable);

	public void deleteRoom(int roomId);
}