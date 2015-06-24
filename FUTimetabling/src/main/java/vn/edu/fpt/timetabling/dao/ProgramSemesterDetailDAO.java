package vn.edu.fpt.timetabling.dao;

import java.util.List;

import vn.edu.fpt.timetabling.model.ProgramSemesterDetail;

public interface ProgramSemesterDetailDAO {
	public void addProgramSemesterDetail(
			ProgramSemesterDetail programSemesterDetail);

	public void updateProgramSemesterDetail(
			ProgramSemesterDetail programSemesterDetail);

	public List<ProgramSemesterDetail> listProgramSemesterDetails();

	public ProgramSemesterDetail getProgramSemesterDetailById(
			int programSemesterDetailId);

	public void deleteProgramSemesterDetail(int programSemesterDetailId);
}
