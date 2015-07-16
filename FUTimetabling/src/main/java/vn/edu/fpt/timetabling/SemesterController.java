package vn.edu.fpt.timetabling;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.edu.fpt.timetabling.model.Course;
import vn.edu.fpt.timetabling.model.Semester;
import vn.edu.fpt.timetabling.service.SemesterService;

@Controller
public class SemesterController extends GeneralController {
	private SemesterService semesterService;

	@Autowired(required = true)
	@Qualifier(value = "semesterService")
	public void setSemesterService(SemesterService semesterService) {
		this.semesterService = semesterService;
	}

	@RequestMapping(value = "/staff/semesters", method = RequestMethod.GET)
	public String listSemester(HttpSession httpSession, Model model) {
		model.addAttribute("semester", new Course());
		model.addAttribute("listSemesters", semesterService.listSemesters(false, false, false, false));
		checkError(httpSession, model);
		return "semester";
	}

	// For add and update person both
	@RequestMapping(value = "/staff/semester/add", method = RequestMethod.POST)
	public String addSemester(@ModelAttribute("semester") Semester semester) {
		if (semester.getSemesterId() == 0) {
			// new course, add it
			semesterService.addSemester(semester);
		} else {
			// existing course, call update
			semesterService.updateSemester(semester);
		}
		return "redirect:/staff/semesters";
	}

	@RequestMapping("/staff/semester/delete/{semesterId}")
	public String deleteSemester(@PathVariable("semesterId") int semesterId) {
		semesterService.deleteSemester(semesterId);
		return "redirect:/staff/semesters";
	}

	@RequestMapping("/staff/semester/edit/{semesterId}")
	public String editSemester(@PathVariable("semesterId") int semesterId, Model model) {
		model.addAttribute("semester", semesterService.getSemesterById(semesterId, false, false, false, false));
		model.addAttribute("listSemesters", semesterService.listSemesters(false, false, false, false));
		return "semester";
	}

	@ExceptionHandler(Exception.class)
	public String handleException(HttpSession httpSession, Exception e) {
		httpSession.setAttribute("error", "Error, please try again.");
		return "redirect:/staff/semesters";
	}
}
