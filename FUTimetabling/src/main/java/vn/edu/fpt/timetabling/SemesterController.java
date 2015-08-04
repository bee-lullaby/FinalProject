package vn.edu.fpt.timetabling;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import vn.edu.fpt.timetabling.exception.SemesterExistedException;
import vn.edu.fpt.timetabling.model.Semester;
import vn.edu.fpt.timetabling.service.SemesterService;

@Controller
public class SemesterController extends GeneralController {
	private SemesterService semesterService;

	@Autowired(required = true)
	@Qualifier(value = "semesterService")
	public void setSemesterService(SemesterService semesterService) {
		this.semesterService = semesterService;
	}

	@RequestMapping(value = "/staff/semesters", method = RequestMethod.GET)
	public String listSemester(HttpSession httpSession, Model model) {
		model.addAttribute("listSemesters",
				semesterService.listSemesters(false, false, false, false));
		checkError(httpSession, model);
		notifySuccess(httpSession, model);
		return "semesters";
	}

	// For add and update person both
	@RequestMapping(value = "/staff/semesters/updateSemester", method = RequestMethod.POST, params = {
			"code", "name", "startDate", "endDate" })
	public String updateSemester(@RequestParam String code,
			@RequestParam String name, @RequestParam String startDate,
			@RequestParam String endDate, HttpSession httpSession) throws SemesterExistedException, ParseException {
		
		Semester semester = semesterService.getSemesterByCode(code, false, false, false, false);
		if(semester != null) {
			throw new SemesterExistedException();
		} else {
			semester = new Semester();
		}
		semester.setCode(code);
		semester.setName(name);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date start = sdf.parse(startDate);
		Date end = sdf.parse(endDate);
		
		semester.setStartDate(start);
		semester.setEndDate(end);
		
		semesterService.addSemester(semester);
		httpSession.setAttribute("success", "Add Semester Successful!");
		return "redirect:/staff/semesters";
	}

	@RequestMapping(value = "/staff/semesters/deleteSemester", method = RequestMethod.GET, params = {"semesterId"})
	public String deleteSemester(@RequestParam int semesterId, HttpSession httpSession) {
		semesterService.deleteSemester(semesterId);
		httpSession.setAttribute("success", "Delete Semester Successful!");
		return "redirect:/staff/semesters";
	}

	@ExceptionHandler(SemesterExistedException.class)
	public String handleException(HttpSession httpSession, Exception e) {
		httpSession.setAttribute("error", "The Semester's code existed! Please try again!");
		return "redirect:/staff/semesters";
	}
}
