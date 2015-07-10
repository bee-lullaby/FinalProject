package vn.edu.fpt.timetabling.service;

import java.util.List;

import vn.edu.fpt.timetabling.model.ClassSemester;

public interface ClassSemesterService {
	public void addClassSemester(ClassSemester classSemester);

	public void updateClassSemester(ClassSemester classSemester);

	public List<ClassSemester> listClassSemesters(boolean jointClassCourseSemester);

	public ClassSemester getClassSemesterById(int classSemesterId, boolean jointClassCourseSemester);

	public ClassSemester getClassSemesterByClassSemester(int semesterId, int classId, boolean jointClassCourseSemester);

	public ClassSemester getClassSemesterByCode(String classCode, int semesterId, boolean jointClassCourseSemester);

	public void deleteClassSemester(int classSemesterId);

	public long getNumberOfStudents(int classSemesterId);

	public void autoPutStudentsIntoClassSemester(int classSemesterId);

	public List<ClassSemester> listClassSemestersBySpecializedSemester(int semesterId, int specializedId,
			int detailSpecializedId, int semesterNumber);

	public void autoPutStudentsIntoClassSemesters(int semesterId);

	public boolean isStudentInClassSemester(int studentId, int classSemesterId);
}
