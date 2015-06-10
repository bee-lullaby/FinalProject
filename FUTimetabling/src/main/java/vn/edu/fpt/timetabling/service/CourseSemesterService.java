package vn.edu.fpt.timetabling.service;

import java.io.File;
import java.util.List;

import vn.edu.fpt.timetabling.model.CourseSemester;

public interface CourseSemesterService {
	public void addCourseSemester(CourseSemester courseSemester);

	public void addCourseSemesterFromFile(File courseSemesters, int semesterId);

	public void updateCourseSemester(CourseSemester courseSemester);

	public List<CourseSemester> listCourseSemesters();

	public CourseSemester getCourseSemesterById(int courseSemesterId);

	public void deleteCourseSemester(int courseSemesterId);
}
