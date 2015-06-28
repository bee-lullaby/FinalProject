package vn.edu.fpt.timetabling;

import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import vn.edu.fpt.timetabling.model.ClassSemester;
import vn.edu.fpt.timetabling.model.Semester;
import vn.edu.fpt.timetabling.service.ScheduleService;

@Controller
public class ScheduleController {
	private static final Logger logger = LoggerFactory
			.getLogger(ScheduleController.class);
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
		Set<ClassSemester> list = scheduleService.listClassBySemester(semesterId);
		Iterator<ClassSemester> i = list.iterator();
		return "redirect:/schedule?semesterId=" + semesterId + "&classId="
				+ i.next().getClassFPT().getClassId();
	}

	@RequestMapping(value = "/schedule", method = RequestMethod.GET, params = {
			"semesterId", "classId" })
	public void scheduleSemesterClass(@RequestParam int semesterId,
			@RequestParam int classId, Model model) {
		
		Semester semester = scheduleService.getSemesterById(semesterId);
		
		
		ClassSemester classSemester = scheduleService
				.getClassSemesterByClassSemester(semesterId, classId);
		
		// Get Start and End Date
		String startDate = new SimpleDateFormat("yyyy-MM-dd").format(semester.getStartDate());
		String endDate = new SimpleDateFormat("yyyy-MM-dd").format(semester.getEndDate());
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
		
		//
		model.addAttribute("semesterId", semester.getSemesterId());
		model.addAttribute("semesterName", semester.getName());
		model.addAttribute("listClasses", semester.getClassSemesters());
		model.addAttribute("listClassCourses",
				classSemester.getClassCourseSemester());
		model.addAttribute("listCourses",
				scheduleService.listCourseSemesterByClass(classId, semesterId));
		model.addAttribute("listTeacherCourseSemester", scheduleService
				.listTeacherByCourseSemester(classId, semesterId));
		
		logger.info(startDate);
		logger.info(endDate);
		return;
	}
}
