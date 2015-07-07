package vn.edu.fpt.timetabling;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import vn.edu.fpt.timetabling.model.ClassCourseSemester;
import vn.edu.fpt.timetabling.model.ClassCourseStudentSemester;
import vn.edu.fpt.timetabling.model.ClassSemester;
import vn.edu.fpt.timetabling.model.Semester;
import vn.edu.fpt.timetabling.model.Specialized;
import vn.edu.fpt.timetabling.model.Student;
import vn.edu.fpt.timetabling.service.ClassCourseSemesterService;
import vn.edu.fpt.timetabling.service.ClassCourseStudentSemesterService;
import vn.edu.fpt.timetabling.service.SemesterService;
import vn.edu.fpt.timetabling.service.StudentService;
import vn.edu.fpt.timetabling.utils.SessionUtils;

@Controller
public class ClassCourseStudentSemesterController {

	private ClassCourseStudentSemesterService classCourseStudentSemesterService;
	private SemesterService semesterService;
	private ClassCourseSemesterService classCourseSemesterService;
	private StudentService studentService;

	@Autowired(required = true)
	@Qualifier(value = "classCourseStudentSemesterService")
	public void setClassCourseStudentSemesterService(
			ClassCourseStudentSemesterService classCourseStudentSemesterService) {
		this.classCourseStudentSemesterService = classCourseStudentSemesterService;
	}

	@Autowired(required = true)
	@Qualifier(value = "semesterService")
	public void setSemesterService(SemesterService semesterService) {
		this.semesterService = semesterService;
	}

	@Autowired(required = true)
	@Qualifier(value = "classCourseSemesterService")
	public void setClassCourseSemesterService(ClassCourseSemesterService classCourseSemesterService) {
		this.classCourseSemesterService = classCourseSemesterService;
	}

	@Autowired(required = true)
	@Qualifier(value = "studentService")
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	@RequestMapping(value = "/classCourseStudentSemesters", method = RequestMethod.GET)
	public String listClassCourseSemester(HttpSession session, Model model,
			@RequestParam(value = "semesterId", required = false) Integer semesterId,
			@RequestParam(value = "classCourseSemesterId", required = false) Integer classCourseSemesterId) {
		if (!SessionUtils.isSessionValid(session)) {
			return "home";
		}
		List<Semester> semesters = semesterService.listSemesters(false, false, false, false);
		Semester semester;
		if (semesterId != null || session.getAttribute("semester") != null) {
			if (semesterId == null) {
				semester = (Semester) session.getAttribute("semester");
			} else {
				semester = semesterService.getSemesterById(semesterId, false, false, false, false);
			}
			if (semester == null) {
				semester = semesters.get(0);
			}
		} else {
			semester = semesters.get(0);
		}
		semesterId = semester.getSemesterId();
		List<ClassCourseSemester> classCourseSemesters = classCourseSemesterService
				.listClassCourseSemesterBySemester(semesterId);
		if (!classCourseSemesters.isEmpty()) {
			ClassCourseSemester classCourseSemester = null;
			if (classCourseSemesterId != null || session.getAttribute("classCourseSemester") != null) {
				if (classCourseSemesterId == null) {
					classCourseSemester = (ClassCourseSemester) session.getAttribute("classCourseSemester");
				} else {
					for (ClassCourseSemester classCourseSemesterTemp : classCourseSemesters) {
						if (classCourseSemesterTemp.getClassCourseSemesterId() == classCourseSemesterId) {
							classCourseSemester = classCourseSemesterTemp;
						}
					}
				}
				if (classCourseSemester == null) {
					classCourseSemester = classCourseSemesters.get(0);
				}
			} else {
				classCourseSemester = classCourseSemesters.get(0);
			}
			classCourseSemesterId = classCourseSemester.getClassCourseSemesterId();
			ClassSemester classSemester = classCourseSemester.getClassSemester();
			Specialized specialized = classSemester.getClassFPT().getSpecialized();
			Specialized detailSpecialized = classSemester.getClassFPT().getDetailSpecialized();
			int detailspecializedId = -1;
			if (detailSpecialized != null) {
				detailspecializedId = detailSpecialized.getSpecializedId();
			}
			int semesterNumber = classSemester.getSemesterNumber();
			session.setAttribute("classCourseSemester", classCourseSemester);
			model.addAttribute("classCourseSemester", classCourseSemester);
			model.addAttribute("classCourseSemesterId", classCourseSemesterId);
			model.addAttribute("students", studentService.listStudentsBySpecializedSemester(
					specialized.getSpecializedId(), detailspecializedId, semesterNumber, classCourseSemesterId));
		}
		model.addAttribute("classCourseSemesters", classCourseSemesters);
		model.addAttribute("semesters", semesters);
		model.addAttribute("semesterId", semesterId);
		session.setAttribute("semester", semester);
		return "classCourseStudentSemester";
	}

	@RequestMapping("/classCourseStudentSemester/add/{studentId}")
	public String addClassCourseStudentSemester(HttpSession session, @PathVariable("studentId") int studentId,
			Model model) {
		if (!SessionUtils.isSessionValid(session)) {
			return "home";
		}
		Student student = studentService.getStudentById(studentId);
		if (student != null && session.getAttribute("classCourseSemester") != null) {
			ClassCourseSemester classCourseSemester = (ClassCourseSemester) session.getAttribute("classCourseSemester");
			ClassCourseStudentSemester classCourseStudentSemester = new ClassCourseStudentSemester();
			classCourseStudentSemester.setClassCourseSemester(classCourseSemester);
			classCourseStudentSemester.setStudent(student);
			classCourseStudentSemesterService.addClassCourseStudentSemester(classCourseStudentSemester);
		}
		return "redirect:/classCourseStudentSemesters";
	}
}
