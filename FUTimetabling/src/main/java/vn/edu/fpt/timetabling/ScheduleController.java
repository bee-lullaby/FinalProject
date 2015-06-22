package vn.edu.fpt.timetabling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import vn.edu.fpt.timetabling.service.ClassSemesterService;
import vn.edu.fpt.timetabling.service.SemesterService;

@Controller
public class ScheduleController {

	private SemesterService semesterService;
	private ClassSemesterService classSemesterService;
	
	@Autowired(required = true)
	@Qualifier(value = "semesterService")
	public void setSemesterService(SemesterService semesterService) {
		this.semesterService = semesterService;
	}
	
	
	@Autowired(required = true)
	@Qualifier(value = "classSemesterService")
	public void ClassSemesterService(ClassSemesterService classSemesterService) {
		this.classSemesterService = classSemesterService;
	}
	
	@RequestMapping(value="/schedule", method = RequestMethod.GET)
	public void schedule(@RequestParam(value = "semesterId", required = true) int semesterId, Model model) {
		model.addAttribute("semesterName", semesterService.getSemesterById(semesterId).getName());
		model.addAttribute("listClasses", classSemesterService.listClassSemesterBySemester(semesterId));
		return;
	}
}
