package vn.edu.fpt.timetabling.dao;

import java.util.List;

import vn.edu.fpt.timetabling.model.CourseSemester;

public interface CourseSemesterDAO {
	public void addCourseSemester(CourseSemester courseSemester);

	public void updateCourseSemester(CourseSemester courseSemester);

	public List<CourseSemester> listCourseSemesters();

	public CourseSemester getCourseSemesterById(int courseSemesterId);
	
	public CourseSemester getCourseSemesterByCode(String code);
	
	public void deleteCourseSemester(int courseSemesterId);
}
