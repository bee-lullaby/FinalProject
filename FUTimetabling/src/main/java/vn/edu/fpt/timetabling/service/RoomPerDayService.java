package vn.edu.fpt.timetabling.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.fpt.timetabling.model.Room;
import vn.edu.fpt.timetabling.model.Timetable;


@Service
@Transactional(rollbackFor = Exception.class)
public interface RoomPerDayService {
	 
	public List<Timetable> getListTimetableOfDay(String date);
	
	public HashMap<String, List<Room>> getListRoom();
	
	public boolean saveRoom(int timetableId, int roomId);
}
