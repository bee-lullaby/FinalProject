package vn.edu.fpt.timetabling.dao;

import java.util.List;

import vn.edu.fpt.timetabling.model.Student;

public interface StudentDAO {
	public void addStudent(Student student);

	public void updateStudent(Student student);

	public List<Student> listStudents();

	public Student getStudentById(int studentId);

	public Student getStudentByCode(String code);

	public Student getStudentByEmail(String email);

	public void deleteStudent(int studentId);

	public List<Student> listStudentsCanBeInClassCourseSemester(int classSemesterId, int specializedId,
			int detailspecializedId, int semesterNumber, int classCourseSemesterId);

	public List<Student> listStudentsInClassCourseSemester(int classSemesterId, int classCourseSemesterId);

	public List<Student> listStudentsWithoutClass(int semesterId);

	public List<Student> listStudentsInClasses(int semesterId);
	
	public Student getLastStudent(int specializedId);
}
