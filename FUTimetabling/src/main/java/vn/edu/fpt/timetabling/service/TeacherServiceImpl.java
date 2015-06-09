package vn.edu.fpt.timetabling.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.fpt.timetabling.dao.TeacherDAO;
import vn.edu.fpt.timetabling.model.Teacher;

@Service
public class TeacherServiceImpl implements TeacherService {
	
	private TeacherDAO teacherDAO;

	public void setTeacherDAO(TeacherDAO teacherDAO) {
		this.teacherDAO = teacherDAO;
	}

	@Override
	@Transactional
	public void addTeacher(Teacher teacher) {
		teacherDAO.addTeacher(teacher);
	}

	@Override
	@Transactional
	public void updateTeacher(Teacher teacher) {
		teacherDAO.updateTeacher(teacher);
	}

	@Override
	@Transactional
	public List<Teacher> listTeachers() {
		return teacherDAO.listTeachers();
	}

	@Override
	@Transactional
	public Teacher getTeacherById(int teacherId) {
		return teacherDAO.getTeacherById(teacherId);
	}

	@Override
	@Transactional
	public Teacher getTeacherByEmail(String email) {
		return teacherDAO.getTeacherByEmail(email);
	}

	@Override
	@Transactional
	public void deleteTeacher(int teacherId) {
		teacherDAO.deleteTeacher(teacherId);
	}

}
