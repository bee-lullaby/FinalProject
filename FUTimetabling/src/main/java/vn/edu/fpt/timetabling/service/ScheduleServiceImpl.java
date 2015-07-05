package vn.edu.fpt.timetabling.service;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.edu.fpt.timetabling.dao.ClassSemesterDAO;
import vn.edu.fpt.timetabling.dao.CourseSemesterDAO;
import vn.edu.fpt.timetabling.dao.SemesterDAO;
import vn.edu.fpt.timetabling.model.ClassCourseSemester;
import vn.edu.fpt.timetabling.model.ClassSemester;
import vn.edu.fpt.timetabling.model.CourseSemester;
import vn.edu.fpt.timetabling.model.TeacherCourseSemester;

@Service
public class ScheduleServiceImpl implements ScheduleService {

	@Autowired
	private SemesterDAO semesterDAO;

	@Autowired
	private ClassSemesterDAO classSemesterDAO;

	@Autowired
	private CourseSemesterDAO courseSemesterDAO;

	@Transactional
	@Override
	public ClassSemester getClassSemesterByClassSemester(int semesterId, int classId) {
		return classSemesterDAO.getClassSemesterByClassSemester(semesterId, classId);
	}

	@Transactional
	@Override
	public Set<ClassSemester> listClassBySemester(int semesterId) {
		return semesterDAO.getSemesterById(semesterId, true, false, false, false).getClassSemesters();
	}

	@Transactional
	@Override
	public Set<ClassCourseSemester> listClassCourseSemesterByClassSemester(int classId, int semesterId) {
		ClassSemester classSemester = classSemesterDAO.getClassSemesterByClassSemester(semesterId, classId);
		return classSemester.getClassCourseSemester();
	}

	@Transactional
	@Override
	public Set<CourseSemester> listCourseSemesterByClass(int classId, int semesterId) {
		// TODO Auto-generated method stub
		ClassSemester classSemester = classSemesterDAO.getClassSemesterByClassSemester(semesterId, classId);
		Set<ClassCourseSemester> classCourseSemesters = classSemester.getClassCourseSemester();

		Set<CourseSemester> courseSemesters = new LinkedHashSet<CourseSemester>();
		for (ClassCourseSemester classCourseSemester : classCourseSemesters) {
			courseSemesters.add(courseSemesterDAO
					.getCourseSemesterById(classCourseSemester.getCourseSemester().getCourseSemesterId()));
		}

		return courseSemesters;
	}

	@Transactional
	@Override
	public Set<TeacherCourseSemester> listTeacherByCourseSemester(int classId, int semesterId) {
		// TODO Auto-generated method stub

		Set<CourseSemester> courseSemesters = listCourseSemesterByClass(classId, semesterId);
		Set<TeacherCourseSemester> teacherCourseSemesters = new LinkedHashSet<TeacherCourseSemester>();
		for (CourseSemester courseSemester : courseSemesters) {
			teacherCourseSemesters.addAll(courseSemester.getTeacherCourseSemesters());
		}
		return teacherCourseSemesters;
	}

}
