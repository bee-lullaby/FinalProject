package vn.edu.fpt.timetabling.dao;

import java.util.List;

import vn.edu.fpt.timetabling.model.Semester;

public interface SemesterDAO {
	public void addSemester(Semester semester);

	public void updateSemester(Semester semester);

	public List<Semester> listSemesters(boolean jointClassSemester,
			boolean jointCourseSemester, boolean jointProgramSemester,
			boolean jointTeacherSemester);

	public Semester getSemesterById(int semesterId, boolean jointClassSemester,
			boolean jointCourseSemester, boolean jointProgramSemester,
			boolean jointTeacherSemester);

	public Semester getSemesterByCode(String code, boolean jointClassSemester,
			boolean jointCourseSemester, boolean jointProgramSemester,
			boolean jointTeacherSemester);

	public void deleteSemester(int semesterId);
}
