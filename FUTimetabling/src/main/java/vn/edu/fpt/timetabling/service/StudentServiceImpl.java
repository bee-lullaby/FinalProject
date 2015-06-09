package vn.edu.fpt.timetabling.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import vn.edu.fpt.timetabling.dao.StudentDAO;
import vn.edu.fpt.timetabling.model.Student;

public class StudentServiceImpl implements StudentService {

	private StudentDAO studentDAO;

	public void setStudentDAO(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}

	@Override
	@Transactional
	public void addStudent(Student student) {
		studentDAO.addStudent(student);
	}

	@Override
	@Transactional
	public void updateStudent(Student student) {
		studentDAO.updateStudent(student);
	}

	@Override
	@Transactional
	public List<Student> listStudents() {
		return studentDAO.listStudents();
	}

	@Override
	@Transactional
	public Student getStudentById(int studentId) {
		return studentDAO.getStudentById(studentId);
	}

	@Override
	@Transactional
	public Student getStudentByCode(String code) {
		return studentDAO.getStudentByCode(code);
	}

	@Override
	@Transactional
	public void deleteStudent(int studentId) {
		studentDAO.deleteStudent(studentId);
	}

}
