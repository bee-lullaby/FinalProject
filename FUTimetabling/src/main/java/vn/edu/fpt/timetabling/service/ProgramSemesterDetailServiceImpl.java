package vn.edu.fpt.timetabling.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.fpt.timetabling.dao.ProgramSemesterDetailDAO;
import vn.edu.fpt.timetabling.model.ProgramSemesterDetail;

@Service
public class ProgramSemesterDetailServiceImpl implements
		ProgramSemesterDetailService {
	private ProgramSemesterDetailDAO programSemesterDetailDAO;

	public void setProgramSemesterDetailDAO(
			ProgramSemesterDetailDAO programSemesterDetailDAO) {
		this.programSemesterDetailDAO = programSemesterDetailDAO;
	}

	@Override
	@Transactional
	public void addProgramSemesterDetail(
			ProgramSemesterDetail programSemesterDetail) {
		programSemesterDetailDAO
				.addProgramSemesterDetail(programSemesterDetail);
	}

	@Override
	@Transactional
	public void updateProgramSemesterDetail(
			ProgramSemesterDetail programSemesterDetail) {
		programSemesterDetailDAO
				.updateProgramSemesterDetail(programSemesterDetail);
	}

	@Override
	@Transactional
	public List<ProgramSemesterDetail> listProgramSemesterDetails() {
		return programSemesterDetailDAO.listProgramSemesterDetails();
	}

	@Override
	@Transactional
	public ProgramSemesterDetail getProgramSemesterDetailById(
			int programSemesterDetailId) {
		return programSemesterDetailDAO
				.getProgramSemesterDetailById(programSemesterDetailId);
	}

	@Override
	@Transactional
	public void deleteProgramSemesterDetail(int programSemesterDetailId) {
		programSemesterDetailDAO
				.deleteProgramSemesterDetail(programSemesterDetailId);
	}

}
