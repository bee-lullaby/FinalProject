package vn.edu.fpt.timetabling.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.fpt.timetabling.dao.SemesterDAO;
import vn.edu.fpt.timetabling.model.Semester;

@Service
public class SemesterServiceImpl implements SemesterService {
	
	private SemesterDAO semesterDAO;

	/**
	 * @param semesterDAO the semesterDAO to set
	 */
	public void setSemesterDAO(SemesterDAO semesterDAO) {
		this.semesterDAO = semesterDAO;
	}

	@Override
	@Transactional
	public void addSemester(Semester semester) {
		// TODO Auto-generated method stub
		semesterDAO.addSemester(semester);
	}

	@Override
	@Transactional
	public void updateSemester(Semester semester) {
		// TODO Auto-generated method stub
		semesterDAO.updateSemester(semester);
	}

	@Override
	@Transactional
	public List<Semester> listSemesters() {
		// TODO Auto-generated method stub
		return semesterDAO.listSemesters();
	}

	@Override
	@Transactional
	public Semester getSemesterById(int semesterId) {
		// TODO Auto-generated method stub
		return semesterDAO.getSemesterById(semesterId);
	}

	@Override
	@Transactional
	public void deleteSemester(int semesterId) {
		// TODO Auto-generated method stub
		semesterDAO.deleteSemester(semesterId);
	}

}
