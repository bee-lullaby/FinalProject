package vn.edu.fpt.timetabling.dao;

import java.util.List;
import java.util.Set;

import vn.edu.fpt.timetabling.model.ClassCourseSemester;
import vn.edu.fpt.timetabling.model.Student;
import vn.edu.fpt.timetabling.model.Timetable;

public interface TimetableDAO {
	public void addTimetable(Timetable timetable);

	public void updateTimetable(Timetable timetable);

	public List<Timetable> listTimetables();

	public List<Timetable> listTimetablesByCCSId(List<ClassCourseSemester> classCourseSemesters);

	public List<Timetable> listTimetablesByClassCourseSemesters(Set<ClassCourseSemester> classCourseSemesters);

	public Timetable getTimetableById(int timetableId);

	public void deleteTimetable(int timetableId);

	public List<Timetable> listTimetablesByStudent(int semesterId, Student student);
}
