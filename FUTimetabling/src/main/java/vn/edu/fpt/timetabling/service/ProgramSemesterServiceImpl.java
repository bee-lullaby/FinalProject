package vn.edu.fpt.timetabling.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.fpt.timetabling.dao.ProgramSemesterDAO;
import vn.edu.fpt.timetabling.model.ProgramSemester;

@Service
public class ProgramSemesterServiceImpl implements ProgramSemesterService {
	private ProgramSemesterDAO programSemesterDAO;

	public void setProgramSemesterDAO(ProgramSemesterDAO programSemesterDAO) {
		this.programSemesterDAO = programSemesterDAO;
	}

	@Override
	@Transactional
	public void addProgramSemester(ProgramSemester programSemester) {
		programSemesterDAO.addProgramSemester(programSemester);
	}

	@Override
	@Transactional
	public void updateProgramSemester(ProgramSemester programSemester) {
		programSemesterDAO.updateProgramSemester(programSemester);
	}

	@Override
	@Transactional
	public List<ProgramSemester> listProgramSemesters() {
		return programSemesterDAO.listProgramSemesters();
	}

	@Override
	@Transactional
	public ProgramSemester getProgramSemesterById(int programSemesterId) {
		return programSemesterDAO.getProgramSemesterById(programSemesterId);
	}

	@Override
	@Transactional
	public void deleteProgramSemester(int programSemesterId) {
		programSemesterDAO.deleteProgramSemester(programSemesterId);
	}

	@Override
	@Transactional
	public ProgramSemester getProgramSemesterBySpecializedSemester(int semesterId, int specializedId,
			int detailSpecializedId, int semesterNumber) {
		return programSemesterDAO.getProgramSemesterBySpecializedSemester(semesterId, specializedId,
				detailSpecializedId, semesterNumber);
	}
}
