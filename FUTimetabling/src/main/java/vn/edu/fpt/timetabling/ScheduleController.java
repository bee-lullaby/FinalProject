package vn.edu.fpt.timetabling;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import vn.edu.fpt.timetabling.model.ClassSemester;
import vn.edu.fpt.timetabling.service.ScheduleService;

@Controller
public class ScheduleController {

	private ScheduleService scheduleService;

	@Autowired(required = true)
	@Qualifier(value = "scheduleService")
	public void setScheduleService(ScheduleService scheduleService) {
		this.scheduleService = scheduleService;
	}

	@RequestMapping(value = "/schedule", method = RequestMethod.GET)
	public String schedule(
			@RequestParam(value = "semesterId", required = true) int semesterId,
			Model model) {
		List<ClassSemester> list = scheduleService
				.listClassBySemester(semesterId);
		return "redirect:/schedule?semesterId=" + semesterId + "&classId="
				+ list.get(0).getClassFPT().getClassId();
	}

	@RequestMapping(value = "/schedule", method = RequestMethod.GET, params = {
			"semesterId", "classId" })
	public void scheduleSemesterClass(@RequestParam int semesterId,
			@RequestParam int classId, Model model) {
		model.addAttribute("semesterId", semesterId);
		model.addAttribute("semesterName",
				scheduleService.getSemesterById(semesterId).getName());
		model.addAttribute("listClasses",
				scheduleService.listClassBySemester(semesterId));
		model.addAttribute("listClassCourses", scheduleService
				.listClassCourseSemesterByClassSemester(classId, semesterId));
		model.addAttribute("listCourses",
				scheduleService.listCourseSemesterByClass(classId, semesterId));
		model.addAttribute("listTeacherCourseSemester", scheduleService
				.listTeacherByCourseSemester(classId, semesterId));
		return;
	}
}
