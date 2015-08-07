package vn.edu.fpt.timetabling.dao;

import java.util.List;

import vn.edu.fpt.timetabling.model.ProgramSemester;

public interface ProgramSemesterDAO {
	public void addProgramSemester(ProgramSemester programSemester);

	public void updateProgramSemester(ProgramSemester programSemester);

	public List<ProgramSemester> listProgramSemesters();
	
	public List<ProgramSemester> listProgramSemestersBySemester(int semesterId);
	
	public ProgramSemester getProgramSemesterById(int programSemesterId);

	public void deleteProgramSemester(int programSemesterId);

	public void deleteProgramSemestersBySemester(int semesterId);
	
	public ProgramSemester getProgramSemesterBySpecializedSemester(int semesterId, int specializedId,
			int detailSpecializedId, int semesterNumber);
}
