package vn.edu.fpt.timetabling;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import vn.edu.fpt.timetabling.model.ClassSemester;
import vn.edu.fpt.timetabling.model.Semester;
import vn.edu.fpt.timetabling.model.Timetable;
import vn.edu.fpt.timetabling.service.ClassCourseSemesterService;
import vn.edu.fpt.timetabling.service.ClassSemesterService;
import vn.edu.fpt.timetabling.service.RoomService;
import vn.edu.fpt.timetabling.service.ScheduleInfoService;
import vn.edu.fpt.timetabling.service.ScheduleService;
import vn.edu.fpt.timetabling.service.SemesterService;
import vn.edu.fpt.timetabling.service.TimetableService;

@Controller
public class AutomaticTimetablingController extends GeneralController {
	@Autowired
	private SemesterService semesterService;
	@Autowired
	private ClassSemesterService classSemesterService;
	@Autowired
	private ClassCourseSemesterService classCourseSemesterService;
	@Autowired
	private RoomService roomService;
	@Autowired
	private ScheduleService scheduleService;
	@Autowired
	private ScheduleInfoService scheduleInfoService;
	@Autowired
	private TimetableService timetableService;

	@RequestMapping(value = "/staff/automaticTimetabling", params = { "semesterId" })
	public String automaticSchedule(@RequestParam int semesterId, HttpSession httpSession, Model model) {

		Semester semester = semesterService.getSemesterById(semesterId, true, false, false, true);
		Set<ClassSemester> classSemesters = semester.getClassSemesters();

		String semesterName = semester.getName();
		int totalClasses = classSemesters.size();
		int totalTeachers = semester.getTeacherSemesters().size();
		int totalRooms = roomService.listRooms(false).size();
		int totalClassCourses = classCourseSemesterService.listClassCourseSemesterBySemester(semesterId, false, false)
				.size();
		model.addAttribute("semesterId", semester.getSemesterId());
		model.addAttribute("semesterName", semesterName);
		model.addAttribute("totalClasses", totalClasses);
		model.addAttribute("totalTeachers", totalTeachers);
		model.addAttribute("totalRooms", totalRooms);
		model.addAttribute("totalClassCourses", totalClassCourses);
		checkError(httpSession, model);
		notifySuccess(httpSession, model);
		return "automaticTimetabling";
	}

	@RequestMapping(value = "/staff/automaticTimetabling/auto", method = RequestMethod.GET)
	public String autoSchedule(@RequestParam(value = "semesterId", required = true) int semesterId, Model model,
			HttpSession httpSession) {
		scheduleService.autoSchedule(semesterId);
		httpSession.setAttribute("success", "Auto timetabling successful!!!");
		return "redirect:/staff/automaticTimetablingResult?semesterId=" + semesterId;
	}

	@RequestMapping(value = "/staff/automaticTimetabling/clear", method = RequestMethod.GET)
	public String clearSchedule(@RequestParam(value = "semesterId", required = true) int semesterId, Model model,
			HttpSession httpSession, HttpServletRequest request) {
		timetableService.deleteTimetablesBySemester(semesterId);
		httpSession.setAttribute("success", "Delete all timetable of semester successful!!!");
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
		// return "redirect:/staff/schedule?semesterId=" + semesterId;
	}

	@RequestMapping(value = "/staff/automaticTimetablingResult", method = RequestMethod.GET)
	public String autoScheduleResult(@RequestParam(value = "semesterId", required = true) int semesterId, Model model,
			HttpSession httpSession, HttpServletRequest request) {

		Semester semester = semesterService.getSemesterById(semesterId, false, false, false, false);
		model.addAttribute("semesterName", semester.getName());
		model.addAttribute("listClassWasSetTimetablesDone",
				scheduleInfoService.getListClassWasSetTimetablesDone(semesterId));
		model.addAttribute("listClassWasSetRoomsDone", scheduleInfoService.getListClassWasSetRoomsDone(semesterId));
		model.addAttribute("listClassWasSetTeachersDone",
				scheduleInfoService.getListClassWasSetTeachersDone(semesterId));
		checkError(httpSession, model);
		notifySuccess(httpSession, model);
		return "automaticTimetablingResult";
		// return "redirect:/staff/schedule?semesterId=" + semesterId;
	}

	@RequestMapping(value = "/staff/downloadTimetable", method = RequestMethod.GET)
	public ModelAndView downloadTimetable(@RequestParam(value = "semesterId", required = true) int semesterId,
			Model model, HttpSession httpSession, HttpServletResponse response) throws IOException {
		List<ClassSemester> classSemesters = classSemesterService.listClassSemestersBySemester(semesterId, false);
		HashMap<ClassSemester, List<Timetable>> timetablesMap = new HashMap<>();
		for (ClassSemester classSemester : classSemesters) {
			List<Timetable> timetables = timetableService.listTimetableByClass(classSemester.getClassSemesterId());
			timetablesMap.put(classSemester, timetables);
		}
		return new ModelAndView("excelView", "timetablesMap", timetablesMap);
	}
}
