package vn.edu.fpt.timetabling.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.fpt.timetabling.dao.TeacherDAO;
import vn.edu.fpt.timetabling.model.Teacher;

@Service
@Transactional(rollbackFor = Exception.class)
public class TeacherServiceImpl implements TeacherService {
	private TeacherDAO teacherDAO;
	
	public void setTeacherDAO(TeacherDAO teacherDAO) {
		this.teacherDAO = teacherDAO;
	}

	
	@Autowired
	private TeacherSemesterService teacherSemesterService;
	
	@Override
	public void addTeacher(Teacher teacher) {
		teacherDAO.addTeacher(teacher);
	}

	@Override
	public void updateTeacher(Teacher teacher) {
		teacherDAO.updateTeacher(teacher);
	}

	@Override
	public List<Teacher> listTeachers() {
		return teacherDAO.listTeachers();
	}

	@Override
	public Teacher getTeacherById(int teacherId) {
		return teacherDAO.getTeacherById(teacherId);
	}

	@Override
	public Teacher getTeacherByEmail(String email) {
		return teacherDAO.getTeacherByEmail(email);
	}
	
	@Override
	public Teacher getTeacherByAccount(String account) {
		return teacherDAO.getTeacherByAccount(account);
	}
	@Override
	public void deleteTeacher(int semesterId, int teacherId) {
		if(teacherSemesterService.listTeacherSemestersByTeacherId(teacherId, false, false).size() > 1) {
			teacherSemesterService.deleteTeacherSemester(teacherSemesterService.getTeacherSemesterByTeacherSemester(teacherId, semesterId, false, false).getTeacherSemesterId());
		} else {
			teacherDAO.deleteTeacher(teacherId);
		}
	}
	
	
}
