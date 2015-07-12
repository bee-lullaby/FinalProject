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
		teacherSemesterDAO.addTeacherSemester(teacherSemester);
	}

	@Override
	@Transactional
	public void updateTeacherSemester(TeacherSemester teacherSemester) {
		teacherSemesterDAO.updateTeacherSemester(teacherSemester);
	}

	@Override
	@Transactional
	public List<TeacherSemester> listTeacherSemesters() {
		return teacherSemesterDAO.listTeacherSemesters();
	}

	@Override
	@Transactional
	public TeacherSemester getTeacherSemesterById(int teacherSemesterId) {
		return teacherSemesterDAO.getTeacherSemesterById(teacherSemesterId);
	}

	@Override
	@Transactional
	public void deleteTeacherSemester(int teacherSemesterId) {
		teacherSemesterDAO.deleteTeacherSemester(teacherSemesterId);
	}

	@Override
	@Transactional
	public TeacherSemester getTeacherSemesterByAccount(String account) {
		return teacherSemesterDAO.getTeacherSemesterByAccount(account);
	}

}
