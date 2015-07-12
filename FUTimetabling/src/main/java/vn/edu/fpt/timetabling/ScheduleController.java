package vn.edu.fpt.timetabling;

import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import vn.edu.fpt.timetabling.model.ClassCourseSemester;
import vn.edu.fpt.timetabling.model.ClassSemester;
import vn.edu.fpt.timetabling.model.DaySlot;
import vn.edu.fpt.timetabling.model.Semester;
import vn.edu.fpt.timetabling.service.ClassSemesterService;
import vn.edu.fpt.timetabling.service.ScheduleService;
import vn.edu.fpt.timetabling.service.SemesterService;

@Controller
public class ScheduleController {
	private static final Logger logger = LoggerFactory.getLogger(ScheduleController.class);
	private ScheduleService scheduleService;
	private SemesterService semesterService;
	private ClassSemesterService classSemesterService;

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

	@Autowired(required = true)
	@Qualifier(value = "classSemesterService")
	public void setClassSemesterService(ClassSemesterService classSemesterService) {
		this.classSemesterService = classSemesterService;
	}

	@RequestMapping(value = "/schedule", method = RequestMethod.GET)
	public String schedule(@RequestParam(value = "semesterId", required = true) int semesterId, Model model) {
		List<ClassSemester> list = scheduleService.listClassBySemester(semesterId);
		return "redirect:/schedule?semesterId=" + semesterId + "&classId=" + list.get(0).getClassFPT().getClassId()
				+ "&week=1";
	}

	@RequestMapping(value = "/schedule", method = RequestMethod.GET, params = { "semesterId", "classId", "week" })
	public void scheduleSemesterClass(@RequestParam int semesterId, @RequestParam int classId, @RequestParam int week,
			Model model) {
		// Get Semesters
		Semester semester = semesterService.getSemesterById(semesterId, true, false, false, false);

		ClassSemester classSemester = classSemesterService.getClassSemesterByClassSemester(semesterId, classId, true);

		List<ClassCourseSemester> classCourseSemesters = new ArrayList<ClassCourseSemester>();
		classCourseSemesters.addAll(classSemester.getClassCourseSemesters());
		// Get Start and End Date
		String startDate = new SimpleDateFormat("yyyy-MM-dd").format(semester.getStartDate());
		String endDate = new SimpleDateFormat("yyyy-MM-dd").format(semester.getEndDate());
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);

		//
		model.addAttribute("semesterId", semester.getSemesterId());
		model.addAttribute("semesterName", semester.getName());
		model.addAttribute("listClasses", semester.getClassSemesters());
		model.addAttribute("listClassCourses", classCourseSemesters);
		model.addAttribute("listTeacherCourseSemester",
				scheduleService.listTeacherByCourseSemester(classId, semesterId));

		ObjectMapper om = new ObjectMapper();
		StringWriter sw = new StringWriter();

		try {
			// if(generate == 0) {
			om.writeValue(sw, scheduleService.getListDaySlot(semesterId, classId, week));
			// } else if (generate == 1 && week > 1) {
			// om.writeValue(sw, scheduleService.getListDaySlot(semesterId,
			// classId, week - 1));
			// }
			model.addAttribute("JSONdata", sw);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// logger.info(startDate);
		// logger.info(endDate);
		return;
	}

	@RequestMapping(value = "/schedule/updateTimetable", method = RequestMethod.POST)
	public String updateTimetable(@RequestParam(value = "JSONToSubmit", required = true) String JSONToSubmit,
			@RequestParam(value = "JSONprev", required = true) String JSONprev, HttpServletRequest request) {
		logger.info(JSONToSubmit);

		ObjectMapper om = new ObjectMapper();
		TypeFactory typeFactory = om.getTypeFactory();
		List<DaySlot> daySlots;
		List<DaySlot> prevDaySlots;
		try {
			daySlots = om.readValue(JSONToSubmit, typeFactory.constructCollectionType(List.class, DaySlot.class));
			prevDaySlots = om.readValue(JSONprev, typeFactory.constructCollectionType(List.class, DaySlot.class));
			boolean result = scheduleService.saveTimetable(daySlots, prevDaySlots);
			if (result)
				logger.info("hameo");
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@RequestMapping(value = "/schedule/generateFromPreviousWeek", method = RequestMethod.POST, params = { "semesterId",
			"classId", "week" })
	public String generateFromPreviousWeek(@RequestParam int semesterId, @RequestParam int classId,
			@RequestParam int week, HttpServletRequest request) {
		scheduleService.generateFromPreviousWeek(semesterId, classId, week);
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}
}
