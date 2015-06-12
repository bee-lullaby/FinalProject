package vn.edu.fpt.timetabling;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import vn.edu.fpt.timetabling.model.ClassSemester;
import vn.edu.fpt.timetabling.service.ClassSemesterService;
import vn.edu.fpt.timetabling.service.ClassService;
import vn.edu.fpt.timetabling.service.SemesterService;
import vn.edu.fpt.timetabling.utils.SessionUtils;

@Controller
public class ClassSemesterController {
	private ClassSemesterService classSemesterService;
	private SemesterService semesterService;
	private ClassService classService;

	@Autowired(required = true)
	@Qualifier(value = "classSemesterService")
	public void setClassSemesterService(
			ClassSemesterService classSemesterService) {
		this.classSemesterService = classSemesterService;
	}

	@Autowired(required = true)
	@Qualifier(value = "semesterService")
	public void setSemesterService(SemesterService semesterService) {
		this.semesterService = semesterService;
	}

	@Autowired(required = true)
	@Qualifier(value = "classService")
	public void setClassService(ClassService classService) {
		this.classService = classService;
	}

	@RequestMapping(value = "/classSemesters", method = RequestMethod.GET)
	public String listClassSemester(HttpSession session, Model model) {
		if (!SessionUtils.isSessionValid(session)) {
			return "home";
		}
		model.addAttribute("classSemester", new ClassSemester());
		model.addAttribute("classSemesters",
				classSemesterService.listClassSemesters());
		model.addAttribute("semesters", semesterService.listSemesters());
		model.addAttribute("classes", classService.listClasses());
		return "classSemester";
	}

	@RequestMapping(value = "/classSemester/add", method = RequestMethod.POST)
	public String addClassSemester(
			HttpSession session,
			@RequestParam(value = "classSemesterId", required = true) int classSemesterId,
			@RequestParam(value = "classFPT", required = true) int classId,
			@RequestParam(value = "semester", required = true) int semesterId,
			@RequestParam(value = "semesterNumber", required = true) int semesterNumber) {
		if (!SessionUtils.isSessionValid(session)) {
			return "home";
		}
		ClassSemester classSemester = classSemesterService
				.getClassSemesterById(classSemesterId);
		if (classSemester == null) {
			classSemester = new ClassSemester();
		}
		classSemester.setClassFPT(classService.getClassById(classId));
		classSemester.setSemester(semesterService.getSemesterById(semesterId));
		classSemester.setSemesterNumber(semesterNumber);
		if (classSemester.getClassSemesterId() == 0) {
			// new class semester, add it
			classSemesterService.addClassSemester(classSemester);
		} else {
			// existing class semester, call update
			classSemesterService.updateClassSemester(classSemester);
		}
		return "redirect:/classSemesters";
	}

	@RequestMapping("/classSemester/delete/{classSemesterId}")
	public String deleteClassSemester(HttpSession session,
			@PathVariable("classSemesterId") int classSemesterId) {
		if (!SessionUtils.isSessionValid(session)) {
			return "home";
		}
		classSemesterService.deleteClassSemester(classSemesterId);
		return "redirect:/classSemesters";
	}

	@RequestMapping("/classSemester/edit/{classSemesterId}")
	public String editClassSemester(HttpSession session,
			@PathVariable("classSemesterId") int classSemesterId, Model model) {
		if (!SessionUtils.isSessionValid(session)) {
			return "home";
		}
		ClassSemester classSemester = classSemesterService
				.getClassSemesterById(classSemesterId);
		if (classSemester == null) {
			return "redirect:/classSemesters";
		}
		model.addAttribute("classSemester", classSemester);
		model.addAttribute("classSemesters",
				classSemesterService.listClassSemesters());
		model.addAttribute("semesters", semesterService.listSemesters());
		model.addAttribute("classes", classService.listClasses());
		model.addAttribute("semesterId", classSemester.getSemester()
				.getSemesterId());
		model.addAttribute("classId", classSemester.getClassFPT().getClassId());
		return "classSemester";
	}
}
