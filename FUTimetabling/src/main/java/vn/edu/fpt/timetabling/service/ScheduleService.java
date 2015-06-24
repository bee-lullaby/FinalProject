package vn.edu.fpt.timetabling.service;

import java.util.List;

import vn.edu.fpt.timetabling.model.ClassCourseSemester;
import vn.edu.fpt.timetabling.model.ClassSemester;
import vn.edu.fpt.timetabling.model.CourseSemester;
import vn.edu.fpt.timetabling.model.Semester;
import vn.edu.fpt.timetabling.model.TeacherCourseSemester;

public interface ScheduleService {
	
	public Semester getSemesterById(int semesterId);
	
	public List<ClassSemester> listClassBySemester(int semesterId);
	
	public List<ClassCourseSemester> listClassCourseSemesterByClassSemester(int classId, int semesterId);
	
	public List<TeacherCourseSemester> listTeacherByCourseSemester(int classId,
			int semesterId);
	
	public List<CourseSemester> listCourseSemesterByClass(int classId,
			int semesterId);
}
