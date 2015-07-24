package vn.edu.fpt.timetabling.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.fpt.timetabling.dao.TeacherSemesterDAO;
import vn.edu.fpt.timetabling.model.TeacherSemester;

@Service
@Transactional(rollbackFor = Exception.class)
public class TeacherSemesterServiceImpl implements TeacherSemesterService {
	private TeacherSemesterDAO teacherSemesterDAO;

	public void setTeacherSemesterDAO(TeacherSemesterDAO teacherSemesterDAO) {
		this.teacherSemesterDAO = teacherSemesterDAO;
	}

	@Override
	public void addTeacherSemester(TeacherSemester teacherSemester) {
		teacherSemesterDAO.addTeacherSemester(teacherSemester);
	}

	@Override
	public void updateTeacherSemester(TeacherSemester teacherSemester) {
		teacherSemesterDAO.updateTeacherSemester(teacherSemester);
	}

	@Override
	public List<TeacherSemester> listTeacherSemesters(boolean jointTeacherCourseSemesters, boolean jointTimetables) {
		return teacherSemesterDAO.listTeacherSemesters(jointTeacherCourseSemesters, jointTimetables);
	}

	@Override
	public TeacherSemester getTeacherSemesterById(int teacherSemesterId, boolean jointTeacherCourseSemesters,
			boolean jointTimetables) {
		return teacherSemesterDAO.getTeacherSemesterById(teacherSemesterId, jointTeacherCourseSemesters,
				jointTimetables);
	}

	@Override
	public void deleteTeacherSemester(int teacherSemesterId) {
		teacherSemesterDAO.deleteTeacherSemester(teacherSemesterId);
	}

	@Override
	public TeacherSemester getTeacherSemesterByAccount(String account, boolean jointTeacherCourseSemesters,
			boolean jointTimetables) {
		return teacherSemesterDAO.getTeacherSemesterByAccount(account, jointTeacherCourseSemesters, jointTimetables);
	}
	
	@Override
	public TeacherSemester getTeacherSemesterByTeacherSemester(int teacherId, int semesterId,  boolean jointTeacherCourseSemesters,
			boolean jointTimetables) {
		return teacherSemesterDAO.getTeacherSemesterByTeacherSemester(teacherId, semesterId, jointTeacherCourseSemesters, jointTimetables);
	}
}

