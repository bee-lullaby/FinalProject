package vn.edu.fpt.timetabling;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import vn.edu.fpt.timetabling.model.Specialized;
import vn.edu.fpt.timetabling.model.Student;
import vn.edu.fpt.timetabling.service.SpecializedService;
import vn.edu.fpt.timetabling.service.StudentService;

@Controller
public class StudentController extends GeneralController {
	private StudentService studentService;
	private SpecializedService specializedService;

	@Autowired(required = true)
	@Qualifier(value = "studentService")
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	@Autowired(required = true)
	@Qualifier(value = "specializedService")
	public void setSpecializedService(SpecializedService specializedService) {
		this.specializedService = specializedService;
	}

	@RequestMapping(value = "/staff/students", method = RequestMethod.GET)
	public String listStudents(HttpSession httpSession, Model model) {
		model.addAttribute("student", new Student());
		model.addAttribute("students", studentService.listStudents());
		model.addAttribute("specializeds", specializedService.listSpecializeds(false, false));
		checkError(httpSession, model);
		return "student";
	}

	@RequestMapping(value = "/staff/student/add", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public String addStudent(@RequestParam(value = "studentId", required = true) int studentId,
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "specialized", required = true) int specializedId,
			@RequestParam(value = "batch", required = true) String batch,
			@RequestParam(value = "semester", required = true) int semester) {
		Student student = studentService.getStudentById(studentId);
		boolean update = true;
		if (student == null) {
			student = new Student();
			update = false;
		}
		student.setName(name);
		Specialized specialized = specializedService.getSpecializedById(specializedId, false, true);
		student.setSpecialized(specialized);
		String studentCode;
		if (update) {
			studentCode = student.getStudentCode();
		} else {
			studentCode = studentService.getNextStudentCode(specialized);
		}
		student.setStudentCode(studentCode);
		String account = studentService.getAccount(name, studentCode);
		student.setAccount(account);
		String email = studentService.getEmail(account);
		student.setEmail(email);
		student.setBatch(batch);
		student.setSemester(semester);
		if (student.getStudentId() == 0) {
			// new class semester, add it
			studentService.addStudent(student);
		} else {
			// existing class semester, call update
			studentService.updateStudent(student);
		}
		return "redirect:/staff/students";
	}

	// For add and update person both
	@RequestMapping(value = "/staff/student/addFromFile", method = RequestMethod.POST)
	public String addStudentFromFile(@RequestParam("file") MultipartFile file,
			@RequestParam("semesterId") int semesterId) {
		if (!file.isEmpty()) {
			File students = new File("D:\\FU\\Do an tot nghiep\\Data\\ServerData\\" + file.getOriginalFilename());
			try {
				file.transferTo(students);
				studentService.addStudentsFromFile(semesterId, students);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "redirect:/staff/students";
	}

	@RequestMapping("/staff/student/delete/{studentId}")
	public String deleteStudent(@PathVariable("studentId") int studentId) {
		studentService.deleteStudent(studentId);
		return "redirect:/staff/students";
	}

	@RequestMapping("/staff/student/edit/{studentId}")
	public String editStudent(@PathVariable("studentId") int studentId, Model model) {
		Student student = studentService.getStudentById(studentId);
		if (student == null) {
			return "home";
		}
		model.addAttribute("student", student);
		model.addAttribute("students", studentService.listStudents());
		model.addAttribute("specializeds", specializedService.listSpecializeds(false, false));
		return "student";
	}

	@ExceptionHandler(Exception.class)
	public String handleException(HttpSession httpSession, Exception e) {
		httpSession.setAttribute("error", "Error, please try again.");
		return "redirect:/staff/students";
	}
}
