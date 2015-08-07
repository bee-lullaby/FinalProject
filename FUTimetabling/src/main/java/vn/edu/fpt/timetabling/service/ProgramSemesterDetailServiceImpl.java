package vn.edu.fpt.timetabling.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.fpt.timetabling.dao.ProgramSemesterDetailDAO;
import vn.edu.fpt.timetabling.model.ProgramSemesterDetail;

@Service
@Transactional(rollbackFor = Exception.class)
public class ProgramSemesterDetailServiceImpl implements ProgramSemesterDetailService {
	private ProgramSemesterDetailDAO programSemesterDetailDAO;

	public void setProgramSemesterDetailDAO(ProgramSemesterDetailDAO programSemesterDetailDAO) {
		this.programSemesterDetailDAO = programSemesterDetailDAO;
	}

	@Override
	public void addProgramSemesterDetail(ProgramSemesterDetail programSemesterDetail) {
		programSemesterDetailDAO.addProgramSemesterDetail(programSemesterDetail);
	}

	@Override
	public void updateProgramSemesterDetail(ProgramSemesterDetail programSemesterDetail) {
		programSemesterDetailDAO.updateProgramSemesterDetail(programSemesterDetail);
	}

	@Override
	public List<ProgramSemesterDetail> listProgramSemesterDetails() {
		return programSemesterDetailDAO.listProgramSemesterDetails();
	}

	@Override
	public ProgramSemesterDetail getProgramSemesterDetailById(int programSemesterDetailId) {
		return programSemesterDetailDAO.getProgramSemesterDetailById(programSemesterDetailId);
	}

	@Override
	public void deleteProgramSemesterDetail(int programSemesterDetailId) {
		programSemesterDetailDAO.deleteProgramSemesterDetail(programSemesterDetailId);
	}
	
	@Override
	public void deleteProgramSemesterDetailsBySemester(int semesterId) {
		programSemesterDetailDAO.deleteProgramSemesterDetailsBySemester(semesterId);
	}
	
	@Override
	public List<ProgramSemesterDetail> listProgramSemesterDetailsBySemester(int semesterId) {
		return programSemesterDetailDAO.listProgramSemesterDetailsBySemester(semesterId);
	}
}
