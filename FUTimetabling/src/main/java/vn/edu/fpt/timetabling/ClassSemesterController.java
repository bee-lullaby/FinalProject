package vn.edu.fpt.timetabling;

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

import vn.edu.fpt.timetabling.model.ClassSemester;
import vn.edu.fpt.timetabling.service.ClassSemesterService;
import vn.edu.fpt.timetabling.service.ClassService;
import vn.edu.fpt.timetabling.service.SemesterService;

@Controller
public class ClassSemesterController extends GeneralController {
	private ClassSemesterService classSemesterService;
	private SemesterService semesterService;
	private ClassService classService;

	@Autowired(required = true)
	@Qualifier(value = "classSemesterService")
	public void setClassSemesterService(ClassSemesterService classSemesterService) {
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
	public String listClassSemester(HttpSession httpSession, Model model) {
		model.addAttribute("classSemester", new ClassSemester());
		model.addAttribute("classSemesterService", classSemesterService);
		model.addAttribute("classSemesters", classSemesterService.listClassSemesters(false));
		model.addAttribute("semesters", semesterService.listSemesters(false, false, false, false));
		model.addAttribute("classes", classService.listClasses());
		checkError(httpSession, model);
		return "classSemester";
	}

	@RequestMapping(value = "/classSemester/add", method = RequestMethod.POST)
	public String addClassSemester(@RequestParam(value = "classSemesterId", required = true) int classSemesterId,
			@RequestParam(value = "classFPT", required = true) int classId,
			@RequestParam(value = "semester", required = true) int semesterId,
			@RequestParam(value = "semesterNumber", required = true) int semesterNumber) {
		ClassSemester classSemester = classSemesterService.getClassSemesterById(classSemesterId, false);
		if (classSemester == null) {
			classSemester = new ClassSemester();
		}
		classSemester.setClassFPT(classService.getClassById(classId));
		classSemester.setSemester(semesterService.getSemesterById(semesterId, false, false, false, false));
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
	public String deleteClassSemester(@PathVariable("classSemesterId") int classSemesterId) {
		classSemesterService.deleteClassSemester(classSemesterId);
		return "redirect:/classSemesters";
	}

	@RequestMapping("/classSemester/edit/{classSemesterId}")
	public String editClassSemester(@PathVariable("classSemesterId") int classSemesterId, Model model) {
		ClassSemester classSemester = classSemesterService.getClassSemesterById(classSemesterId, false);
		if (classSemester == null) {
			return "redirect:/classSemesters";
		}
		model.addAttribute("classSemester", classSemester);
		model.addAttribute("classSemesters", classSemesterService.listClassSemesters(false));
		model.addAttribute("classSemesterService", classSemesterService);
		model.addAttribute("semesters", semesterService.listSemesters(false, false, false, false));
		model.addAttribute("classes", classService.listClasses());
		model.addAttribute("semesterId", classSemester.getSemester().getSemesterId());
		model.addAttribute("classId", classSemester.getClassFPT().getClassId());
		return "classSemester";
	}

	@RequestMapping("/classSemester/autoClassSemester/{classSemesterId}")
	public String autoPutStudentsClassSemester(@PathVariable("classSemesterId") int classSemesterId) {
		classSemesterService.autoPutStudentsIntoClassSemester(classSemesterId);
		return "redirect:/classSemesters";
	}

	@RequestMapping("/classSemester/autoClassSemesters/{semesterId}")
	public String autoPutStudentsClassSemesters(@PathVariable("semesterId") int semesterId) {
		classSemesterService.autoPutStudentsIntoClassSemesters(semesterId);
		return "redirect:/classSemesters";
	}

	@ExceptionHandler(Exception.class)
	public String handleException(HttpSession httpSession, Exception e) {
		httpSession.setAttribute("error", "Error, please try again.");
		return "redirect:/classSemesters";
	}
}
