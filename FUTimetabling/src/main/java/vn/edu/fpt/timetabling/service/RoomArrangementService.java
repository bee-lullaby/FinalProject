package vn.edu.fpt.timetabling.service;

import java.util.HashMap;
import java.util.List;

import vn.edu.fpt.timetabling.model.ClassSemester;
import vn.edu.fpt.timetabling.model.DataRoomArrangement;
import vn.edu.fpt.timetabling.model.Room;
import vn.edu.fpt.timetabling.model.Timetable;

public interface RoomArrangementService {
	public List<ClassSemester> getListClassesCoursesOfSemester(int semesterId);

	public List<DataRoomArrangement> getDataRoomArrangement(int semesterId, int classId);

	public HashMap<Room, List<Timetable>> getListRoomsOfSemester(int semesterId);
}
