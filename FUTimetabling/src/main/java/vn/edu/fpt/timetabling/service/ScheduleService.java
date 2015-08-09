package vn.edu.fpt.timetabling.service;

import java.text.ParseException;
import java.util.List;

import vn.edu.fpt.timetabling.model.ClassCourseSemester;
import vn.edu.fpt.timetabling.model.ClassSemester;
import vn.edu.fpt.timetabling.model.CourseSemester;
import vn.edu.fpt.timetabling.model.DaySlot;
import vn.edu.fpt.timetabling.model.TeacherCourseSemester;

public interface ScheduleService {
	public List<ClassSemester> listClassBySemester(int semesterId);

	public List<ClassCourseSemester> listClassCourseSemesterByClassSemester(
			int classId, int semesterId);

	public List<TeacherCourseSemester> listTeacherByCourseSemester(int classId,
			int semesterId);

	public List<CourseSemester> listCourseSemesterByClass(int classId,
			int semesterId);

	public List<DaySlot> getListDaySlot(int semesterId, int classId, int week);

	public boolean saveTimetable(int semesterId, List<DaySlot> daySlots,
			List<DaySlot> prevDaySlots) throws ParseException;

	public boolean generateFromPreviousWeek(int semesterId, int classId,
			int week);

	public void autoSchedule(int semesterId);
}
