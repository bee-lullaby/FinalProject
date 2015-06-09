package vn.edu.fpt.timetabling.service;

import java.util.List;

import vn.edu.fpt.timetabling.model.Student;

public interface StudentService {
	public void addStudent(Student student);

	public void updateStudent(Student student);

	public List<Student> listStudents();

	public Student getStudentById(int studentId);

	public Student getStudentByCode(String code);

	public void deleteStudent(int studentId);
}
