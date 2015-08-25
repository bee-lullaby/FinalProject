package vn.edu.fpt.timetabling.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import vn.edu.fpt.timetabling.model.CourseSemester;

public interface CourseSemesterService {
	public List<String> addCourseSemesterFromFile(File courseSemesters, int semesterId)
			throws IOException;

	public void addCourseSemester(CourseSemester courseSemester);

	public void updateCourseSemester(CourseSemester courseSemester);

	public List<CourseSemester> listCourseSemesters(
			boolean jointClassCourseSemester,
			boolean jointTeacherCourseSemester,
			boolean jointProgramSemesterDetails);
	
	public List<CourseSemester> listCourseSemestersBySemester(
			int semesterId,
			boolean jointClassCourseSemester,
			boolean jointTeacherCourseSemester,
			boolean jointProgramSemesterDetails);
	
	public List<CourseSemester> listCourseSemesterForView(int semesterId);
	
	public CourseSemester getCourseSemesterById(int courseSemesterId,
			boolean jointClassCourseSemester,
			boolean jointTeacherCourseSemester,
			boolean jointProgramSemesterDetails);

	public CourseSemester getCourseSemesterByCourseSemester(int courseId,
			int semesterId, boolean jointClassCourseSemester,
			boolean jointTeacherCourseSemester,
			boolean jointProgramSemesterDetails);

	public CourseSemester getCourseSemesterByCourseCodeSemester(String code,
			int semesterId, boolean jointClassCourseSemester,
			boolean jointTeacherCourseSemester,
			boolean jointProgramSemesterDetails);

	public void deleteCourseSemester(int courseSemesterId);

	public List<CourseSemester> listCourseSemestersByStudent(int studentId);
	
	public List<CourseSemester> listCourseSemestersByDepartment(
			int semesterId,
			int departmentId,
			boolean jointClassCourseSemester,
			boolean jointTeacherCourseSemester,
			boolean jointProgramSemesterDetails);
	
	public List<CourseSemester> listCourseSemestersByCourseId(int courseId,
			boolean jointClassCourseSemester, boolean jointTeacherCourseSemester, boolean jointProgramSemesterDetails);
}
