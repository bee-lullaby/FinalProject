package vn.edu.fpt.timetabling;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import vn.edu.fpt.timetabling.model.Semester;
import vn.edu.fpt.timetabling.service.ClassSemesterService;
import vn.edu.fpt.timetabling.service.RoomService;
import vn.edu.fpt.timetabling.service.ScheduleInfoService;
import vn.edu.fpt.timetabling.service.SemesterService;

@Controller
public class ScheduleInfoController {
	@Autowired
	private SemesterService semesterService;
	@Autowired
	private ClassSemesterService classSemesterService;
	@Autowired
	private RoomService roomService;
	@Autowired
	private ScheduleInfoService scheduleInfoService;

	@RequestMapping(value = "staff/scheduleInfo", method = RequestMethod.GET)
	public String scheduleInfoInit(HttpSession httpSession, Model model) {
		List<Semester> semesters = semesterService.listSemesters(false, false,
				false, false);
		int semesterId = 0;
		if (!semesters.isEmpty())
			semesterId = semesters.get(0).getSemesterId();
		return "redirect:/staff/scheduleInfo?semesterId=" + semesterId;
	}

	@RequestMapping(value = "staff/scheduleInfo", method = RequestMethod.GET, params = { "semesterId" })
	public String scheduleInfo(@RequestParam int semesterId,
			HttpSession httpSession, Model model) {
		if (semesterId != 0) {
			model.addAttribute("listSemesters",
					semesterService.listSemesters(false, false, false, false));
			model.addAttribute("totalClasses", classSemesterService
					.listClassSemestersBySemester(semesterId, false).size());
			model.addAttribute("totalRooms", roomService.listRooms(false)
					.size());
			model.addAttribute("listClassWasSetTimetablesDone",
					scheduleInfoService
							.getListClassWasSetTimetablesDone(semesterId));
			model.addAttribute("listClassWasSetCourse",
					scheduleInfoService.getListClassWasSetCourse(semesterId));
			model.addAttribute("listClassWasSetRoomsDone",
					scheduleInfoService.getListClassWasSetRoomsDone(semesterId));
			model.addAttribute("listClassWasSetTeachersDone",
					scheduleInfoService
							.getListClassWasSetTeachersDone(semesterId));
		} else {
			model.addAttribute("totalClasses", "N/A");
			model.addAttribute("totalRooms", "N/A");
			model.addAttribute("listClassWasSetTimetablesDone",
					"N/A");
			model.addAttribute("listClassWasSetCourse",
					"N/A");
			model.addAttribute("listClassWasSetRoomsDone",
					"N/A");
			model.addAttribute("listClassWasSetTeachersDone",
					"N/A");
		}
		return "scheduleInfo";
	}

}
