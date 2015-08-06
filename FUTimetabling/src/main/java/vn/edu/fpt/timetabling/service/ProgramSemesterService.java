package vn.edu.fpt.timetabling.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import vn.edu.fpt.timetabling.model.ProgramSemester;

public interface ProgramSemesterService {
	public void addProgramSemester(ProgramSemester programSemester);

	public void updateProgramSemester(ProgramSemester programSemester);

	public List<ProgramSemester> listProgramSemesters();
	
	public List<ProgramSemester> listProgramSemestersBySemester(int semesterId);
	
	public ProgramSemester getProgramSemesterById(int programSemesterId);

	public void deleteProgramSemester(int programSemesterId);

	public ProgramSemester getProgramSemesterBySpecializedSemester(int semesterId, int specializedId,
			int detailSpecializedId, int semesterNumber);
	
	public HashMap<String, List<String>> addProgramSemesterFromFile(int semesterId, File programs) throws IOException;
}
