package vn.edu.fpt.timetabling.service;

import java.util.Set;

import vn.edu.fpt.timetabling.model.ClassCourseSemester;
import vn.edu.fpt.timetabling.model.ClassSemester;
import vn.edu.fpt.timetabling.model.CourseSemester;
import vn.edu.fpt.timetabling.model.TeacherCourseSemester;

public interface ScheduleService {

	public ClassSemester getClassSemesterByClassSemester(int semesterId, int classId);

	public Set<ClassSemester> listClassBySemester(int semesterId);

	public Set<ClassCourseSemester> listClassCourseSemesterByClassSemester(int classId, int semesterId);

	public Set<TeacherCourseSemester> listTeacherByCourseSemester(int classId, int semesterId);

	public Set<CourseSemester> listCourseSemesterByClass(int classId, int semesterId);
}
