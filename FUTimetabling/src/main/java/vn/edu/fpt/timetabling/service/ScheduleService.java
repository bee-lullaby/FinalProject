package vn.edu.fpt.timetabling.service;

import java.util.List;

import vn.edu.fpt.timetabling.model.ClassCourseSemester;
import vn.edu.fpt.timetabling.model.ClassSemester;
import vn.edu.fpt.timetabling.model.CourseSemester;
import vn.edu.fpt.timetabling.model.DaySlot;
import vn.edu.fpt.timetabling.model.TeacherCourseSemester;
import vn.edu.fpt.timetabling.model.Timetable;

public interface ScheduleService {

	public ClassSemester getClassSemesterByClassSemester(int semesterId, int classId);

	public List<ClassSemester> listClassBySemester(int semesterId);

	public List<ClassCourseSemester> listClassCourseSemesterByClassSemester(int classId, int semesterId);

	public List<TeacherCourseSemester> listTeacherByCourseSemester(int classId, int semesterId);

	public List<CourseSemester> listCourseSemesterByClass(int classId, int semesterId);
	
	public List<Timetable> listTimetableOfClassCourseSemester(List<ClassCourseSemester> classCourseSemesters);
	
	public List<DaySlot> getListDaySlot(int semesterId, int classId, int week);

	public boolean addTimetable(List<DaySlot> daySlots);
}
