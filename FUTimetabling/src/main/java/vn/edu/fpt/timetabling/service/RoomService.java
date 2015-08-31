package vn.edu.fpt.timetabling.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import vn.edu.fpt.timetabling.model.Course;
import vn.edu.fpt.timetabling.model.Room;
import vn.edu.fpt.timetabling.model.Timetable;

public interface RoomService {
	public void addRoom(Room room);

	public List<String> addRoomFromFile(File rooms) throws IOException;

	public void updateRoom(Room room);

	public List<Room> listRooms(boolean jointTimetable);

	public Room getRoomById(int roomId, boolean jointTimetable);

	public Room getRoomByCode(String code, boolean jointTimetable);

	public void deleteRoom(int roomId);

	public Map<Course, Map<String, Room>> getCourseRoomMap();

	public Map<String, Set<Integer>> getRoomMapClassCourse(int semesterId);
	
	public Map<Course, Set<Timetable>> getSpecialCourseMapTimetableOfItsClasses(int semesterId);
	
	public List<Room> listRoomsInTimetable(int semesterId);
	
	public List<Room> listNormalRooms();
	
}
