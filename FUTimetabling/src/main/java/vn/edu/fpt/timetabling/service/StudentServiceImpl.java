package vn.edu.fpt.timetabling.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.Normalizer;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.fpt.timetabling.dao.StudentDAO;
import vn.edu.fpt.timetabling.model.Specialized;
import vn.edu.fpt.timetabling.model.Student;

@Service
@Transactional(rollbackFor = Exception.class)
public class StudentServiceImpl implements StudentService {
	private StudentDAO studentDAO;

	@Autowired
	private SpecializedService specializedService;

	public void setStudentDAO(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}

	@Override
	public void addStudent(Student student) {
		studentDAO.addStudent(student);
	}

	@Override
	public void addStudentsFromFile(File students) throws IOException {
		FileInputStream file = new FileInputStream(students);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.iterator();
		rowIterator.next();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			String studentCode = row.getCell(0).getStringCellValue().trim();
			String name = row.getCell(1).getStringCellValue().trim();
			String specializedCode = row.getCell(2).getStringCellValue().trim();
			String account = getAccount(name, studentCode);
			Double currentSemester = row.getCell(3).getNumericCellValue();
			Specialized specialized = specializedService.getSpecializedByCode(specializedCode, false, false);
			Student s = studentDAO.getStudentByCode(studentCode);
			if (s == null) {
				s = new Student();
			}
			System.out.println(studentCode);
			s.setStudentCode(studentCode);
			s.setName(name);
			s.setAccount(account);
			s.setEmail(account += "@fpt.edu.vn");
			s.setBatch("hameo");
			s.setSpecialized(specialized);
			s.setSemester(currentSemester.intValue());
			if (s.getStudentId() == 0)
				studentDAO.addStudent(s);
			else
				studentDAO.updateStudent(s);
		}
		workbook.close();
		file.close();
	}

	@Override
	public void updateStudent(Student student) {
		studentDAO.updateStudent(student);
	}

	@Override
	public List<Student> listStudents() {
		return studentDAO.listStudents();
	}

	@Override
	public Student getStudentById(int studentId) {
		return studentDAO.getStudentById(studentId);
	}

	@Override
	public Student getStudentByCode(String code) {
		return studentDAO.getStudentByCode(code);
	}

	@Override
	public void deleteStudent(int studentId) {
		studentDAO.deleteStudent(studentId);
	}

	@Override
	public String getNextStudentCode(Specialized specialized) {
		String code = specialized.getCode();
		Student lastStudent = studentDAO.getLastStudent(specialized.getSpecializedId());
		if (lastStudent == null) {
			code += "00000";
		} else {
			String studentCode = lastStudent.getStudentCode();
			Integer number = Integer.parseInt(studentCode.substring(2));
			code += String.format("%05d", number + 1);
		}
		return code;
	}

	@Override
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
	public String getEmail(String account) {
		String email = account + "@fpt.edu.vn";
		return email;
	}

	@Override
	public List<Student> listStudentsCanBeInClassCourseSemester(int classSemesterId, int specializedId,
			int detailspecializedId, int semesterNumber, int classCourseSemesterId) {
		return studentDAO.listStudentsCanBeInClassCourseSemester(classSemesterId, specializedId, detailspecializedId,
				semesterNumber, classCourseSemesterId);
	}

	@Override
	public List<Student> listStudentsInClassCourseSemester(int classSemesterId, int classCourseSemesterId) {
		return studentDAO.listStudentsInClassCourseSemester(classSemesterId, classCourseSemesterId);
	}

	@Override
	public List<Student> listStudentsWithoutClass() {
		return studentDAO.listStudentsWithoutClass();
	}

	@Override
	public Student getStudentByEmail(String email) {
		return studentDAO.getStudentByEmail(email);
	}
}
