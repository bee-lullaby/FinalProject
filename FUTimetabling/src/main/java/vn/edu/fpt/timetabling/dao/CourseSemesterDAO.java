package vn.edu.fpt.timetabling.dao;

import java.util.List;

import vn.edu.fpt.timetabling.model.CourseSemester;

public interface CourseSemesterDAO {
	public void addCourseSemester(CourseSemester courseSemester);

	public void updateCourseSemester(CourseSemester courseSemester);

	public List<CourseSemester> listCourseSemesters(
			boolean jointClassCourseSemester,
			boolean jointTeacherCourseSemester,
			boolean jointProgramSemesterDetails);

	public CourseSemester getCourseSemesterById(int courseSemesterId,
			boolean jointClassCourseSemester,
			boolean jointTeacherCourseSemester,
			boolean jointProgramSemesterDetails);

	public CourseSemester getCourseSemesterByCode(String code,
			boolean jointClassCourseSemester,
			boolean jointTeacherCourseSemester,
			boolean jointProgramSemesterDetails);

	public CourseSemester getCourseSemesterByCourseSemester(int courseId,
			int semesterId, boolean jointClassCourseSemester,
			boolean jointTeacherCourseSemester,
			boolean jointProgramSemesterDetails);

	public void deleteCourseSemester(int courseSemesterId);

	public List<CourseSemester> listCourseSemestersByStudent(int studentId);

	public List<CourseSemester> listCourseSemestersBySemester(int semesterId,
			boolean jointClassCourseSemester,
			boolean jointTeacherCourseSemester,
			boolean jointProgramSemesterDetails);
}
