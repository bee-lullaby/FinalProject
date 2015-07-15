package vn.edu.fpt.timetabling.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.fpt.timetabling.dao.SemesterDAO;
import vn.edu.fpt.timetabling.model.Semester;

@Service
@Transactional(rollbackFor = Exception.class)
public class SemesterServiceImpl implements SemesterService {
	private SemesterDAO semesterDAO;

	public void setSemesterDAO(SemesterDAO semesterDAO) {
		this.semesterDAO = semesterDAO;
	}

	@Override
	public void addSemester(Semester semester) {
		semesterDAO.addSemester(semester);
	}

	@Override
	public void updateSemester(Semester semester) {
		semesterDAO.updateSemester(semester);
	}

	@Override
	public List<Semester> listSemesters(boolean jointClassSemester, boolean jointCourseSemester,
			boolean jointProgramSemester, boolean jointTeacherSemester) {
		return semesterDAO.listSemesters(jointClassSemester, jointCourseSemester, jointProgramSemester,
				jointTeacherSemester);
	}

	@Override
	public Semester getSemesterById(int semesterId, boolean jointClassSemester, boolean jointCourseSemester,
			boolean jointProgramSemester, boolean jointTeacherSemester) {
		return semesterDAO.getSemesterById(semesterId, jointClassSemester, jointCourseSemester, jointProgramSemester,
				jointTeacherSemester);
	}

	@Override
	public void deleteSemester(int semesterId) {
		semesterDAO.deleteSemester(semesterId);
	}
}
