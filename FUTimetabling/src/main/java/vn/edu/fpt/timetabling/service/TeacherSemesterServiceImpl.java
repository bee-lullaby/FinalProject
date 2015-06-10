package vn.edu.fpt.timetabling.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.fpt.timetabling.dao.TeacherSemesterDAO;
import vn.edu.fpt.timetabling.model.TeacherSemester;

@Service
public class TeacherSemesterServiceImpl implements TeacherSemesterService {
	private TeacherSemesterDAO teacherSemesterDAO;

	public void setTeacherSemesterDAO(TeacherSemesterDAO teacherSemesterDAO) {
		this.teacherSemesterDAO = teacherSemesterDAO;
	}

	@Override
	@Transactional
	public void addTeacherSemester(TeacherSemester teacherSemester) {
		// TODO Auto-generated method stub
		teacherSemesterDAO.addTeacherSemester(teacherSemester);
	}

	@Override
	@Transactional
	public void updateTeacherSemester(TeacherSemester teacherSemester) {
		// TODO Auto-generated method stub
		teacherSemesterDAO.updateTeacherSemester(teacherSemester);
	}

	@Override
	@Transactional
	public List<TeacherSemester> listTeacherSemesters() {
		// TODO Auto-generated method stub
		return teacherSemesterDAO.listTeacherSemesters();
	}

	@Override
	@Transactional
	public TeacherSemester getTeacherSemesterById(int teacherSemesterId) {
		// TODO Auto-generated method stub
		return teacherSemesterDAO.getTeacherSemesterById(teacherSemesterId);
	}

	@Override
	@Transactional
	public void deleteTeacherSemester(int teacherSemesterId) {
		// TODO Auto-generated method stub
		teacherSemesterDAO.deleteTeacherSemester(teacherSemesterId);
	}

}
