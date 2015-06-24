package vn.edu.fpt.timetabling.dao;

import java.util.List;

import vn.edu.fpt.timetabling.model.ProgramSemester;

public interface ProgramSemesterDAO {
	public void addProgramSemester(ProgramSemester programSemester);

	public void updateProgramSemester(ProgramSemester programSemester);

	public List<ProgramSemester> listProgramSemesters();

	public ProgramSemester getProgramSemesterById(int programSemesterId);

	public void deleteProgramSemester(int programSemesterId);
}
