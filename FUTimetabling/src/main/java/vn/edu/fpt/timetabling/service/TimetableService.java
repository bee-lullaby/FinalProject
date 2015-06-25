package vn.edu.fpt.timetabling.service;

import java.util.List;
import java.util.Set;

import vn.edu.fpt.timetabling.model.Timetable;

public interface TimetableService {
	public void addTimetable(Timetable timetable);

	public void updateTimetable(Timetable timetable);

	public List<Timetable> listTimetables();

	public Set<Timetable> listTimetablesBySemester(int semesterId);
	
	public Set<Timetable> listTimetablesByClass(int classId, int semesterId);

	public Set<Timetable> listTimetablesByClassCourse(int classCourseSemesterId);
	
	public Timetable getTimetableById(int timetableId);
	
	public void deleteTimetable(int timetableId);
}
