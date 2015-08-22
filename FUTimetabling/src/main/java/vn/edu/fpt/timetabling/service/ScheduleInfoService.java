package vn.edu.fpt.timetabling.service;

import java.util.List;

import vn.edu.fpt.timetabling.model.ClassCourseSemester;

public interface ScheduleInfoService {
	public List<ClassCourseSemester> getListClassWasSetCourse(int semesterId);
	
	public List<ClassCourseSemester> getListClassWasSetTimetablesDone(int semesterId);

	public List<ClassCourseSemester> getListClassWasSetRoomsDone(int semesterId);
	
	public List<ClassCourseSemester> getListClassWasSetTeachersDone(int semesterId);
}
