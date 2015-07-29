package vn.edu.fpt.timetabling.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import vn.edu.fpt.timetabling.model.TeacherSemester;

public interface TeacherSemesterService {
	public void addTeacherSemester(TeacherSemester teacherSemester);

	public void addTeacherSemesterFromFile(File courseSemesters, int semesterId)
			throws IOException;
	
	public void updateTeacherSemester(TeacherSemester teacherSemester);

	public List<TeacherSemester> listTeacherSemesters(boolean jointTeacherCourseSemesters, boolean jointTimetables);

	public List<TeacherSemester> listTeacherSemestersBySemester(int semesterId, boolean jointTeacherCourseSemesters, boolean jointTimetables);
	
	public TeacherSemester getTeacherSemesterById(int teacherSemesterId, boolean jointTeacherCourseSemesters,
			boolean jointTimetables);

	public void deleteTeacherSemester(int teacherSemesterId);

	public TeacherSemester getTeacherSemesterByAccount(int semesterId, String account, boolean jointTeacherCourseSemesters,
			boolean jointTimetables);
	
	public TeacherSemester getTeacherSemesterByTeacherSemester(int teacherId, int semesterId,  boolean jointTeacherCourseSemesters,
			boolean jointTimetables);
	
	public List<TeacherSemester> listTeacherSemestersForView(int semesterId);
}
