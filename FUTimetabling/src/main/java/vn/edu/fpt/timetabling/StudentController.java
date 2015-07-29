package vn.edu.fpt.timetabling;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
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
import vn.edu.fpt.timetabling.service.ClassSemesterService;
import vn.edu.fpt.timetabling.service.SpecializedService;
import vn.edu.fpt.timetabling.service.StudentService;

@Controller
public class StudentController extends GeneralController {
	private StudentService studentService;
	private SpecializedService specializedService;
	private ClassSemesterService classSemesterService;

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

	@Autowired(required = true)
	@Qualifier(value = "classSemesterService")
	public void setClassSemesterService(
			ClassSemesterService classSemesterService) {
		this.classSemesterService = classSemesterService;
	}

	@RequestMapping(value = "/staff/students", method = RequestMethod.GET)
	public String students(Model model) {
		model.addAttribute("listStudents", studentService.listStudents());
		model.addAttribute("listClassSemesters",
				classSemesterService.listClassSemesters(false));
		model.addAttribute("listSpecializeds",
				specializedService.listSpecializeds(false, false));
		model.addAttribute("listDetailSpecializeds",
				specializedService.listDetailSpecializeds(false, false));
		return "students";
	}

	@RequestMapping(value = "/staff/students/addStudent", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public String addStudent(
			@RequestParam(value = "studentId", required = true) int studentId,
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "specializedId", required = true) int specializedId,
			@RequestParam(value = "batch", required = true) String batch,
			@RequestParam(value = "semester", required = true) int semester,
			HttpServletRequest request) {
		Student student = studentService.getStudentById(studentId);
		boolean update = true;
		if (student == null) {
			student = new Student();
			update = false;
		}
		student.setName(name);
		Specialized specialized = specializedService.getSpecializedById(
				specializedId, false, true);
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
			studentService.addStudent(student);
		} else {
			studentService.updateStudent(student);
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@RequestMapping(value = "/staff/students/editStudent", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public String editStudent(
			@RequestParam(value = "studentId", required = true) int studentId,
			@RequestParam(value = "code", required = true) String code,
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "classSemesterId", required = true) int classSemesterId,
			@RequestParam(value = "specializedId", required = true) int specializedId,
			@RequestParam(value = "dsId", required = true) int dsId,
			@RequestParam(value = "batch", required = true) String batch,
			@RequestParam(value = "semester", required = true) int semester,
			HttpServletRequest request) {
		Student student = studentService.getStudentById(studentId);

		if (student != null) {
			student.setStudentCode(code);
			student.setName(name);
			student.setEmail(email);
			student.setClassSemester(classSemesterService.getClassSemesterById(
					classSemesterId, false));
			if (specializedId != -1)
				student.setSpecialized(specializedService.getSpecializedById(
						specializedId, false, false));
			else
				student.setSpecialized(null);
			if(dsId != -1) 
				student.setDetailSpecialized(specializedService.getSpecializedById(dsId, false, false));
			else
				student.setDetailSpecialized(null);
			student.setBatch(batch);
			student.setSemester(semester);
			studentService.updateStudent(student);
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@RequestMapping(value = "/staff/students/addFromFile", method = RequestMethod.POST)
	public String addStudentFromFile(@RequestParam("file") MultipartFile file,
			@RequestParam("semesterId") int semesterId, HttpServletRequest request) {
		if (!file.isEmpty()) {
			File students = new File(
					"D:\\FU\\Do an tot nghiep\\Data\\ServerData\\"
							+ file.getOriginalFilename());
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
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@RequestMapping("/staff/students/delete/{studentId}")
	public String deleteStudent(@PathVariable("studentId") int studentId, HttpServletRequest request) {
		studentService.deleteStudent(studentId);
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@ExceptionHandler(Exception.class)
	public String handleException(HttpSession httpSession, Exception e, HttpServletRequest request) {
		httpSession.setAttribute("error", "Error, please try again.");
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}
}
