package vn.edu.fpt.timetabling.dao;

import java.util.List;

import vn.edu.fpt.timetabling.model.Semester;

public interface SemesterDAO {
	public void addSemester(Semester semester);

	public void updateSemester(Semester semester);

	public List<Semester> listSemesters();

	public Semester getSemesterById(int semesterId);

	public void deleteSemester(int semesterId);

}
