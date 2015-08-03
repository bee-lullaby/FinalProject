package vn.edu.fpt.timetabling.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import vn.edu.fpt.timetabling.model.Room;

public interface RoomService {
	public void addRoom(Room room);

	public List<String> addRoomFromFile(File rooms) throws IOException;

	public void updateRoom(Room room);

	public List<Room> listRooms(boolean jointTimetable);

	public Room getRoomById(int roomId, boolean jointTimetable);
	
	public Room getRoomByCode(String code, boolean jointTimetable);
	
	public void deleteRoom(int roomId);
}
