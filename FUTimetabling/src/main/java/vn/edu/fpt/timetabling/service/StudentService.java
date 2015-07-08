package vn.edu.fpt.timetabling.service;

import java.util.List;

import vn.edu.fpt.timetabling.model.Specialized;
import vn.edu.fpt.timetabling.model.Student;

public interface StudentService {
	public void addStudent(Student student);

	public void updateStudent(Student student);

	public List<Student> listStudents();

	public Student getStudentById(int studentId);

	public Student getStudentByCode(String code);

	public void deleteStudent(int studentId);

	public String getNextStudentCode(Specialized specialized);

	public String getAccount(String name, String studentCode);

	public String getEmail(String account);

	public List<Student> listStudentsCanBeInClassCourseSemester(int classSemesterId, int specializedId,
			int detailspecializedId, int semesterNumber, int classCourseSemesterId);

	public List<Student> listStudentsInClassCourseSemester(int classSemesterId, int classCourseSemesterId);
}
