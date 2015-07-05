package vn.edu.fpt.timetabling.service;

import java.util.List;

import vn.edu.fpt.timetabling.model.Semester;

public interface SemesterService {
	public void addSemester(Semester semester);

	public void updateSemester(Semester semester);

	public List<Semester> listSemesters(boolean jointClassSemester, boolean jointCourseSemester,
			boolean jointProgramSemester, boolean jointTeacherSemester);

	public Semester getSemesterById(int semesterId, boolean jointClassSemester, boolean jointCourseSemester,
			boolean jointProgramSemester, boolean jointTeacherSemester);

	public void deleteSemester(int semesterId);
}
