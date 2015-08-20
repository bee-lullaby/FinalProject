package vn.edu.fpt.timetabling;

import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vn.edu.fpt.timetabling.model.ClassSemester;
import vn.edu.fpt.timetabling.model.Semester;
import vn.edu.fpt.timetabling.service.ClassCourseSemesterService;
import vn.edu.fpt.timetabling.service.ClassSemesterService;
import vn.edu.fpt.timetabling.service.RoomService;
import vn.edu.fpt.timetabling.service.SemesterService;

@Controller
public class AutomaticTimetablingController {
	@Autowired
	private SemesterService semesterService;
	@Autowired
	private ClassSemesterService classSemesterService;
	@Autowired
	private ClassCourseSemesterService classCourseSemesterService;
	@Autowired
	private RoomService roomService;
		
	@RequestMapping(value = "/staff/automaticTimetabling", params={"semesterId"})
	public String automaticSchedule(@RequestParam int semesterId, HttpSession httpSession, Model model) {
		
		Semester semester = semesterService.getSemesterById(semesterId, true, false, false, true);
		Set<ClassSemester> classSemesters = semester.getClassSemesters();
		
		String semesterName = semester.getName();
		int totalClasses = classSemesters.size();
		int totalTeachers = semester.getTeacherSemesters().size();
		int totalRooms = roomService.listRooms(false).size();
		int totalClassCourses = classCourseSemesterService.listClassCourseSemesterBySemester(semesterId, false, false).size();
		
		model.addAttribute("semesterName", semesterName);
		model.addAttribute("totalClasses", totalClasses);
		model.addAttribute("totalTeachers", totalTeachers);
		model.addAttribute("totalRooms", totalRooms);
		model.addAttribute("totalClassCourses", totalClassCourses);
		return "automaticTimetabling";
	}

}
