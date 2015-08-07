package vn.edu.fpt.timetabling.service;

import java.util.List;

import vn.edu.fpt.timetabling.model.ClassSemester;

public interface ScheduleInfoService {
	public List<ClassSemester> getListClassWasSetCourse(int semesterId);
	
	public List<ClassSemester> getListClassWasSetTimetablesDone(int semesterId);

	public List<ClassSemester> getListClassWasSetRoomsDone(int semesterId);
	
	public List<ClassSemester> getListClassWasSetTeachersDone(int semesterId);
}
