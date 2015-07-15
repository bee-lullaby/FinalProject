package vn.edu.fpt.timetabling.service;

import java.util.List;

import vn.edu.fpt.timetabling.model.ClassSemester;
import vn.edu.fpt.timetabling.model.DataRoomArrangement;

public interface RoomArrangementService {
	
	public List<ClassSemester> getListClassesCoursesOfSemester(int semesterId);
	
	public List<DataRoomArrangement> getDataRoomArrangement(int semesterId, int classId);
}
