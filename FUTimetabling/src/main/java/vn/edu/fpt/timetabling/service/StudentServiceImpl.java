package vn.edu.fpt.timetabling.service;

import java.text.Normalizer;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.fpt.timetabling.dao.StudentDAO;
import vn.edu.fpt.timetabling.model.Specialized;
import vn.edu.fpt.timetabling.model.Student;

@Service
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

	@Override
	@Transactional
	public String getNextStudentCode(Specialized specialized) {
		Set<Student> students = specialized.getStudents();
		System.out.println(students.size());
		String code = specialized.getCode();
		if (students.isEmpty()) {
			code += "00000";
		} else {
			String studentCode = "";
			for (Student student : students) {
				if (studentCode.isEmpty()) {
					studentCode = student.getStudentCode();
				} else {
					String temp = student.getStudentCode();
					if (temp.compareTo(studentCode) > 0) {
						studentCode = temp;
					}
				}
			}
			Integer number = Integer.parseInt(studentCode.substring(2));
			code += String.format("%05d", number + 1);
		}
		return code;
	}

	@Override
	@Transactional
	public String getAccount(String name, String studentCode) {
		String temp = Normalizer.normalize(name, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		StringTokenizer stringTokenizer = new StringTokenizer(
				pattern.matcher(temp).replaceAll("").replaceAll("Đ", "D").replaceAll("đ", "d"));
		String account = "";
		if (stringTokenizer.hasMoreTokens()) {
			while (true) {
				temp = stringTokenizer.nextToken();
				if (stringTokenizer.hasMoreTokens()) {
					account += Character.toUpperCase(temp.charAt(0));
				} else {
					account = temp + account;
					break;
				}
			}
		}
		account += studentCode;
		return account;
	}

	@Override
	@Transactional
	public String getEmail(String account) {
		String email = account + "@fpt.edu.vn";
		return email;
	}

	@Override
	@Transactional
	public List<Student> listStudentsBySpecializedSemester(int specializedId, int detailspecializedId,
			int semesterNumber, int classCourseSemesterId) {
		return studentDAO.listStudentsBySpecializedSemester(specializedId, detailspecializedId, semesterNumber, classCourseSemesterId);
	}

}
