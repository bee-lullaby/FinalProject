package vn.edu.fpt.timetabling.dao;

import java.util.List;

import vn.edu.fpt.timetabling.model.ClassSemester;

public interface ClassSemesterDAO {
	public void addClassSemester(ClassSemester classSemester);

	public void updateClassSemester(ClassSemester classSemester);

	public List<ClassSemester> listClassSemesters(boolean jointClassCourseSemester);

	public List<ClassSemester> listClassSemestersBySemester(int semesterId, boolean jointClassCourseSemester);

	public ClassSemester getClassSemesterById(int classSemesterId, boolean jointClassCourseSemester);

	public ClassSemester getClassSemesterByClassSemester(int semesterId, int classId, boolean jointClassCourseSemester);

	public ClassSemester getClassSemesterByCode(String classCode, int semesterId, boolean jointClassCourseSemester);

	public void deleteClassSemester(int classSemesterId);

	public long getNumberOfStudents(int classSemesterId);

	public List<ClassSemester> listClassSemestersBySpecializedSemester(int semesterId, int specializedId,
			int detailSpecializedId, int semesterNumber);

	public boolean isStudentInClassSemester(int studentId, int classSemesterId);
}
