package vn.edu.fpt.timetabling.service;

import java.util.List;

import vn.edu.fpt.timetabling.model.ClassSemester;

public interface ScheduleInfoService {
	public List<ClassSemester> getListWasNotSetTimetablesDone(int semesterId);

	public List<ClassSemester> getListWasNotSetRoomsDone(int semesterId);
	
	public List<ClassSemester> getListWasNoteSetTeachersDone(int semesterId);
}
