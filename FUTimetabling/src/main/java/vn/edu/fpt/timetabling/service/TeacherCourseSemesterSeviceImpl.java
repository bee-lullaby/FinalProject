package vn.edu.fpt.timetabling.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.fpt.timetabling.dao.TeacherCourseSemesterDAO;
import vn.edu.fpt.timetabling.model.TeacherCourseSemester;

@Service
public class TeacherCourseSemesterSeviceImpl implements TeacherCourseSemesterService {

	private TeacherCourseSemesterDAO teacherCourseSemesterDAO;
	
	public void setTeacherCourseSemesterDAO(TeacherCourseSemesterDAO teacherCourseSemesterDAO) {
		this.teacherCourseSemesterDAO = teacherCourseSemesterDAO;
	}
	
	@Override
	@Transactional
	public void addTeacherCourseSemester(
			TeacherCourseSemester teacherCourseSemester) {
		// TODO Auto-generated method stub
		teacherCourseSemesterDAO.addTeacherCourseSemester(teacherCourseSemester);
	}

	@Override
	@Transactional
	public void updateTeacherCourseSemester(
			TeacherCourseSemester teacherCourseSemester) {
		// TODO Auto-generated method stub
		teacherCourseSemesterDAO.updateTeacherCourseSemester(teacherCourseSemester);
	}

	@Override
	@Transactional
	public List<TeacherCourseSemester> listTeacherCourseSemesters() {
		// TODO Auto-generated method stub
		return teacherCourseSemesterDAO.listTeacherCourseSemesters();
	}

	@Override
	@Transactional
	public TeacherCourseSemester getTeacherCourseSemesterById(
			int teacherCourseSemesterId) {
		// TODO Auto-generated method stub
		return teacherCourseSemesterDAO.getTeacherCourseSemesterById(teacherCourseSemesterId);
	}

	@Override
	@Transactional
	public void deleteTeacherCourseSemester(int teacherCourseSemesterId) {
		// TODO Auto-generated method stub
		teacherCourseSemesterDAO.deleteTeacherCourseSemester(teacherCourseSemesterId);
	}

}
