package vn.edu.fpt.timetabling.dao;

import java.util.List;

import vn.edu.fpt.timetabling.model.CourseSemester;

public interface CourseSemesterDAO {
	public void addCourseSemester(CourseSemester courseSemester);

	public void updateCourseSemester(CourseSemester courseSemester);

	public List<CourseSemester> listCourseSemesters(boolean jointClassCourseSemester,
			boolean jointTeacherCourseSemester, boolean jointProgramSemesterDetails);

	public CourseSemester getCourseSemesterById(int courseSemesterId, boolean jointClassCourseSemester,
			boolean jointTeacherCourseSemester, boolean jointProgramSemesterDetails);

	public CourseSemester getCourseSemesterByCourseSemester(int courseId, int semesterId,
			boolean jointClassCourseSemester, boolean jointTeacherCourseSemester, boolean jointProgramSemesterDetails);

	public CourseSemester getCourseSemesterByCourseCodeSemester(String code, int semesterId,
			boolean jointClassCourseSemester, boolean jointTeacherCourseSemester, boolean jointProgramSemesterDetails);

	public void deleteCourseSemester(int courseSemesterId);

	public List<CourseSemester> listCourseSemestersByStudent(int studentId);

	public List<CourseSemester> listCourseSemestersBySemester(int semesterId, boolean jointClassCourseSemester,
			boolean jointTeacherCourseSemester, boolean jointProgramSemesterDetails);

	public List<CourseSemester> listCourseSemestersByDepartment(int semesterId, int departmentId,
			boolean jointClassCourseSemester, boolean jointTeacherCourseSemester, boolean jointProgramSemesterDetails);
	
	public List<CourseSemester> listCourseSemestersByCourseId(int courseId,
			boolean jointClassCourseSemester, boolean jointTeacherCourseSemester, boolean jointProgramSemesterDetails);
	
	public int deleteCourseSemesters(int semesterId);
}
