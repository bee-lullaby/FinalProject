package vn.edu.fpt.timetabling.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import vn.edu.fpt.timetabling.model.ClassCourseSemester;
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

	public List<Timetable> listTimetablesByClassCourseSemesters(Set<ClassCourseSemester> classCourseSemesters);

	public List<Timetable> listTimetablesByCCSs(List<ClassCourseSemester> classCourseSemesters);

	public Timetable getTimetableByDateSlotClassCourse(Date date, int slot, int classCourseSemesterId);

	public Timetable getTimetableByDateSlotClass(Date date, int slot, int classSemesterId);

	public List<Timetable> listTimetablesByClassCourseSemestersInWeek(Set<ClassCourseSemester> classCourseSemesters,
			Date startWeek, Date endWeek);

	public List<Timetable> listTimetablesByDate(Date date);

	public List<Timetable> listTimetableByClass(int semesterId, int classId);

	public List<Timetable> listTimetableByTeacher(int teacherSemesterId);

	public void deleteTimetablesByCCS(int classCourseSemesterId);

	public void deleteTimetablesByCCSInWeek(int classSemesterId, Date startWeek, Date endWeek);

	public List<Timetable> listTimetablesByClassAndCourseCode(int semesterId, String classCode, String courseCode);

	public int deleteTimetablesBySemester(int semesterId);
}
