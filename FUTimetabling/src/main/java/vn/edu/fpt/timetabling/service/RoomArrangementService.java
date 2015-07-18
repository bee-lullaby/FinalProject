package vn.edu.fpt.timetabling.service;

import java.util.List;

import vn.edu.fpt.timetabling.model.ClassSemester;
import vn.edu.fpt.timetabling.model.DataRoomArrangement;
import vn.edu.fpt.timetabling.model.Room;

public interface RoomArrangementService {
	
	public List<ClassSemester> getListClassesCoursesOfSemester(int semesterId, int classId);
	
	public List<Room> getListRoomsOfSemester(int semesterId);
	
	public boolean saveTimetables(List<ClassSemester> data);
	
	public List<DataRoomArrangement> getDataRoomArrangement(int semesterId);
}
