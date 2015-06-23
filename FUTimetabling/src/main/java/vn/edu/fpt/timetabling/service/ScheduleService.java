package vn.edu.fpt.timetabling.service;

import java.util.List;

import vn.edu.fpt.timetabling.model.ClassSemester;
import vn.edu.fpt.timetabling.model.CourseSemester;
import vn.edu.fpt.timetabling.model.Semester;
import vn.edu.fpt.timetabling.model.TeacherSemester;

public interface ScheduleService {
	
	public Semester getSemesterById(int semesterId);
	
	public List<ClassSemester> listClassBySemester(int semesterId);
	
	public List<CourseSemester> listCourseByClassSemester(int classId, int semesterId);
	
	public List<TeacherSemester> listTeacherByCourseSemester(int courseId, int semesterId);
}
