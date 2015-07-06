package vn.edu.fpt.timetabling.service;

import java.util.List;

import vn.edu.fpt.timetabling.model.ClassSemester;

public interface ClassSemesterService {
	public void addClassSemester(ClassSemester classSemester);

	public void updateClassSemester(ClassSemester classSemester);

	public List<ClassSemester> listClassSemesters(
			boolean jointClassCourseSemester);

	public ClassSemester getClassSemesterById(int classSemesterId,
			boolean jointClassCourseSemester);

	public ClassSemester getClassSemesterByClassSemester(int semesterId,
			int classId, boolean jointClassCourseSemester);

	public ClassSemester getClassSemesterByCode(String classCode,
			int semesterId, boolean jointClassCourseSemester);

	public void deleteClassSemester(int classSemesterId);
}
