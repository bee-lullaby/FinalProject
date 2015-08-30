package vn.edu.fpt.timetabling.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import vn.edu.fpt.timetabling.model.Specialized;
import vn.edu.fpt.timetabling.model.Student;

public interface StudentService {
	public void addStudent(Student student);

	public void addStudentsFromFile(File students) throws IOException;

	public void updateStudent(Student student);

	public List<Student> listStudents();

	public Student getStudentById(int studentId);

	public Student getStudentByCode(String code);

	public Student getStudentByEmail(String email);

	public void deleteStudent(int studentId);

	public String getNextStudentCode(Specialized specialized);

	public String getAccount(String name, String studentCode);

	public String getEmail(String account);

	public List<Student> listStudentsCanBeInClassCourseSemester(int classSemesterId, int specializedId,
			int detailspecializedId, int semesterNumber, int classCourseSemesterId);

	public List<Student> listStudentsInClassCourseSemester(int classSemesterId, int classCourseSemesterId);

	public List<Student> listStudentsInClasses(int semesterId);
	
	public List<Student> listStudentsWithoutClass(int semesterId);
}
