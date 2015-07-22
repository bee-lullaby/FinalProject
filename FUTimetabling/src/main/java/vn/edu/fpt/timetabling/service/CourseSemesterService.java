package vn.edu.fpt.timetabling.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import vn.edu.fpt.timetabling.model.CourseSemester;

public interface CourseSemesterService {
	public void addCourseSemesterFromFile(File courseSemesters, int semesterId) throws IOException;

	public void addCourseSemester(CourseSemester courseSemester);

	public void updateCourseSemester(CourseSemester courseSemester);

	public List<CourseSemester> listCourseSemesters(boolean jointClassCourseSemester,
			boolean jointTeacherCourseSemester, boolean jointProgramSemesterDetails);
	
	public List<CourseSemester> listCourseSemesterForView(int semesterId);
	
	public CourseSemester getCourseSemesterById(int courseSemesterId, boolean jointClassCourseSemester,
			boolean jointTeacherCourseSemester, boolean jointProgramSemesterDetails);

	public CourseSemester getCourseSemesterByCode(String code, boolean jointClassCourseSemester,
			boolean jointTeacherCourseSemester, boolean jointProgramSemesterDetails);

	public CourseSemester getCourseSemesterByCourseSemester(int courseId, int semesterId,
			boolean jointClassCourseSemester, boolean jointTeacherCourseSemester, boolean jointProgramSemesterDetails);

	public void deleteCourseSemester(int courseSemesterId);

	public List<CourseSemester> listCourseSemestersByStudent(int studentId);
}
