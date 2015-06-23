package vn.edu.fpt.timetabling.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.edu.fpt.timetabling.dao.ClassCourseSemesterDAO;
import vn.edu.fpt.timetabling.dao.ClassSemesterDAO;
import vn.edu.fpt.timetabling.dao.CourseSemesterDAO;
import vn.edu.fpt.timetabling.dao.SemesterDAO;
import vn.edu.fpt.timetabling.dao.TeacherCourseSemesterDAO;
import vn.edu.fpt.timetabling.model.ClassCourseSemester;
import vn.edu.fpt.timetabling.model.ClassSemester;
import vn.edu.fpt.timetabling.model.CourseSemester;
import vn.edu.fpt.timetabling.model.Semester;
import vn.edu.fpt.timetabling.model.TeacherCourseSemester;
import vn.edu.fpt.timetabling.model.TeacherSemester;

@Service
public class ScheduleServiceImpl implements ScheduleService {

	@Autowired
	private SemesterDAO semesterDAO;

	@Autowired
	private ClassSemesterDAO classSemesterDAO;

	@Autowired
	private ClassCourseSemesterDAO classCourseSemesterDAO;

	@Autowired
	private CourseSemesterDAO courseSemesterDAO;

	@Autowired
	private TeacherCourseSemesterDAO teacherCourseSemesterDAO;

	@Transactional
	@Override
	public Semester getSemesterById(int semesterId) {
		// TODO Auto-generated method stub
		return semesterDAO.getSemesterById(semesterId);
	}

	@Transactional
	@Override
	public List<ClassSemester> listClassBySemester(int semesterId) {
		// TODO Auto-generated method stub
		return classSemesterDAO.listClassSemesterBySemester(semesterId);
	}

	@Transactional
	@Override
	public List<CourseSemester> listCourseByClassSemester(int classId,
			int semesterId) {
		// TODO Auto-generated method stub
		int classSemesterId = classSemesterDAO
				.listClassSemesterByClassSemester(semesterId, classId)
				.getClassSemesterId();
		List<ClassCourseSemester> classCourseSemesters = classCourseSemesterDAO
				.listClassCourseSemesterByClass(classSemesterId);

		List<CourseSemester> courseSemesters = new ArrayList<CourseSemester>();
		for (ClassCourseSemester classCourseSemester : classCourseSemesters) {
			courseSemesters.add(classCourseSemester.getCourseSemester());
		}

		return courseSemesters;
	}

	@Override
	public List<TeacherSemester> listTeacherByCourseSemester(int courseId,
			int semesterId) {
		// TODO Auto-generated method stub
		int courseSemesterId = courseSemesterDAO
				.getCourseSemesterByCourseSemester(courseId, semesterId)
				.getCourseSemesterId();
		List<TeacherCourseSemester> teacherCourseSemesters = teacherCourseSemesterDAO
				.listTeacherCourseSemestersByCourse(courseSemesterId);

		List<TeacherSemester> teacherSemesters = new ArrayList<TeacherSemester>();
		for (TeacherCourseSemester teacherCourseSemester : teacherCourseSemesters) {
			teacherSemesters.add(teacherCourseSemester.getTeacherSemester());
		}
		return teacherSemesters;
	}

}
