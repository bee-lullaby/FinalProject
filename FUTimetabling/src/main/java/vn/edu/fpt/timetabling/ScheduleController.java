package vn.edu.fpt.timetabling;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import vn.edu.fpt.timetabling.model.ClassCourseSemester;
import vn.edu.fpt.timetabling.model.ClassSemester;
import vn.edu.fpt.timetabling.model.Semester;
import vn.edu.fpt.timetabling.service.ScheduleService;
import vn.edu.fpt.timetabling.service.SemesterService;

@Controller
public class ScheduleController {
//	private static final Logger logger = LoggerFactory
//			.getLogger(ScheduleController.class);
	private ScheduleService scheduleService;
	private SemesterService semesterService;

	@Autowired(required = true)
	@Qualifier(value = "scheduleService")
	public void setScheduleService(ScheduleService scheduleService) {
		this.scheduleService = scheduleService;
	}

	@Autowired(required = true)
	@Qualifier(value = "semesterService")
	public void setSemesterService(SemesterService semesterService) {
		this.semesterService = semesterService;
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
		//Get Semesters
		Semester semester = semesterService.getSemesterById(semesterId, true,
				false, false, false);

		ClassSemester classSemester = scheduleService
				.getClassSemesterByClassSemester(semesterId, classId);
		

		List<ClassCourseSemester> classCourseSemesters = new ArrayList<ClassCourseSemester>();
		classCourseSemesters.addAll(classSemester.getClassCourseSemester());
		// Get Start and End Date
		String startDate = new SimpleDateFormat("yyyy-MM-dd").format(semester
				.getStartDate());
		String endDate = new SimpleDateFormat("yyyy-MM-dd").format(semester
				.getEndDate());
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);

		//
		model.addAttribute("semesterId", semester.getSemesterId());
		model.addAttribute("semesterName", semester.getName());
		model.addAttribute("listClasses", semester.getClassSemesters());
		model.addAttribute("listClassCourses",
				classCourseSemesters);
		model.addAttribute("listCourses",
				scheduleService.listCourseSemesterByClass(classId, semesterId));
		model.addAttribute("listTeacherCourseSemester", scheduleService
				.listTeacherByCourseSemester(classId, semesterId));
		
//		ObjectMapper om = new ObjectMapper();
//		StringWriter sw = new StringWriter();
//		
		
		
//		logger.info(startDate);
//		logger.info(endDate);
		
		scheduleService.getListDaySlot(semesterId, classId, 1);
		return;
	}
}
