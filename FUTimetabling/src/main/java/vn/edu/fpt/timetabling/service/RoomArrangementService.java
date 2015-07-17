package vn.edu.fpt.timetabling.service;

import java.util.List;

import vn.edu.fpt.timetabling.model.ClassSemester;
import vn.edu.fpt.timetabling.model.Room;

public interface RoomArrangementService {
	
	public List<ClassSemester> getListClassesCoursesOfSemester(int semesterId);
	
	public List<Room> getListRoomsOfSemester(int semesterId);
	
	public boolean saveTimetables(List<ClassSemester> data, List<ClassSemester> prev);
}
