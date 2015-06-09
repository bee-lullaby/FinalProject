package vn.edu.fpt.timetabling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.edu.fpt.timetabling.model.Course;
import vn.edu.fpt.timetabling.model.Semester;
import vn.edu.fpt.timetabling.service.SemesterService;

@Controller
public class SemesterController {
	
	private SemesterService semesterService;

	@Autowired(required = true)
	@Qualifier(value = "semesterService")
	public void setSemesterService(SemesterService semesterService) {
		this.semesterService = semesterService;
	}

	@RequestMapping(value = "/semesters", method = RequestMethod.GET)
	public String listSemester(Model model) {
		model.addAttribute("semester", new Course());
		model.addAttribute("listSemesters", semesterService.listSemesters());
		return "semester";
	}

	// For add and update person both
	@RequestMapping(value = "/semester/add", method = RequestMethod.POST)
	public String addSemester(@ModelAttribute("semester") Semester semester) {
		if (semester.getSemesterId() == 0) {
			// new course, add it
			semesterService.addSemester(semester);
		} else {
			// existing course, call update
			semesterService.updateSemester(semester);
		}
		return "redirect:/semesters";
	}

	@RequestMapping("/semester/delete/{semesterId}")
	public String deleteSemester(@PathVariable("semesterId") int semesterId) {
		semesterService.deleteSemester(semesterId);
		return "redirect:/semesters";
	}

	@RequestMapping("/semester/edit/{semesterId}")
	public String editSemester(@PathVariable("semesterId") int semesterId, Model model) {
		model.addAttribute("semester", semesterService.getSemesterById(semesterId));
		model.addAttribute("listSemesters", semesterService.listSemesters());
		return "semester";
	}
}
