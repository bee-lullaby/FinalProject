package vn.edu.fpt.timetabling.service;

import java.util.List;

import vn.edu.fpt.timetabling.model.ProgramSemesterDetail;

public interface ProgramSemesterDetailService {
	public void addProgramSemesterDetail(
			ProgramSemesterDetail programSemesterDetail);

	public void updateProgramSemesterDetail(
			ProgramSemesterDetail programSemesterDetail);

	public List<ProgramSemesterDetail> listProgramSemesterDetails();

	public ProgramSemesterDetail getProgramSemesterDetailById(
			int programSemesterDetailId);

	public void deleteProgramSemesterDetail(int programSemesterDetailId);
	
	public void deleteProgramSemesterDetailsBySemester(int semesterId);
	
	public List<ProgramSemesterDetail> listProgramSemesterDetailsBySemester(
			int semesterId);
}
