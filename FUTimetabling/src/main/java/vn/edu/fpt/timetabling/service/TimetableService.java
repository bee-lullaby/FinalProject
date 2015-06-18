package vn.edu.fpt.timetabling.service;

import java.util.List;

import vn.edu.fpt.timetabling.model.Timetable;

public interface TimetableService {
	public void addTimetable(Timetable timetable);

	public void updateTimetable(Timetable timetable);

	public List<Timetable> listTimetables();

	public List<Timetable> listTimetablesBySemester(int semesterId);
	
	public List<Timetable> listTimetablesByClass(int classId, int semesterId);
	
	public Timetable getTimetableById(int timetableId);

	public void deleteTimetable(int timetableId);
}
