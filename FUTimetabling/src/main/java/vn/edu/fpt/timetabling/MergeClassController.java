package vn.edu.fpt.timetabling;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import vn.edu.fpt.timetabling.exception.ClassCourseSemesterMergeExistedException;
import vn.edu.fpt.timetabling.model.ClassCourseSemester;
import vn.edu.fpt.timetabling.model.ClassCourseSemesterMerge;
import vn.edu.fpt.timetabling.model.Semester;
import vn.edu.fpt.timetabling.service.ClassCourseSemesterMergeService;
import vn.edu.fpt.timetabling.service.ClassCourseSemesterService;
import vn.edu.fpt.timetabling.service.CourseSemesterService;
import vn.edu.fpt.timetabling.service.SemesterService;

@Controller
public class MergeClassController extends GeneralController {

	@Autowired
	private SemesterService semesterService;

	@Autowired
	private CourseSemesterService courseSemesterService;

	@Autowired
	private ClassCourseSemesterService classCourseSemesterService;

	@Autowired
	private ClassCourseSemesterMergeService classCourseSemesterMergeService;
	
	
	@RequestMapping(value = "/staff/mergeClasses", method = RequestMethod.GET)
	public String mergeClassesInit(HttpSession httpSession, Model model) {
		List<Semester> semesters = semesterService.listSemesters(false, false,
				false, false);
		int semesterId = 0;
		if (!semesters.isEmpty())
			semesterId = semesters.get(0).getSemesterId();
		return "redirect:/staff/mergeClasses?semesterId=" + semesterId;
	}

	@RequestMapping(value = "/staff/mergeClasses", method = RequestMethod.GET, params = { "semesterId" })
	public String mergeClasses(@RequestParam int semesterId,
			HttpSession httpSession, Model model) {
		model.addAttribute("listSemesters",
				semesterService.listSemesters(false, false, false, false));
		model.addAttribute("listCourseSemesters", courseSemesterService
				.listCourseSemestersBySemester(semesterId, false, false, false));
		model.addAttribute("listClassCourseSemesters",
				classCourseSemesterService.listClassCourseSemesterBySemester(
						semesterId, false, false));
		model.addAttribute("listClassCourseSemesterMerges", classCourseSemesterMergeService.listClassCourseSemesterMerges(semesterId));
		checkError(httpSession, model);
		notifySuccess(httpSession, model);
		return "mergeClasses";
	}

	@RequestMapping(value = "/staff/mergeClasses/addMergeClass", method = RequestMethod.POST, params = {
			"classCourseSemesterId1", "classCourseSemesterId2" })
	public String addMergeClass(@RequestParam int classCourseSemesterId1,
			@RequestParam int classCourseSemesterId2, HttpSession httpSession, HttpServletRequest request) throws ClassCourseSemesterMergeExistedException {
		
		ClassCourseSemester ccs1 = classCourseSemesterService.getClassCourseSemesterById(classCourseSemesterId1, false, false);
		ClassCourseSemester ccs2 = classCourseSemesterService.getClassCourseSemesterById(classCourseSemesterId2, false, false);
		
		ClassCourseSemesterMerge ccsm = classCourseSemesterMergeService.getClassCourseSemesterMergeBy2CCS(classCourseSemesterId1, classCourseSemesterId2);
		
		if(ccsm != null) {
			throw new ClassCourseSemesterMergeExistedException();
		} else {
			ccsm = new ClassCourseSemesterMerge();
		}
		ccsm.setClassCourseSemester1(ccs1);
		ccsm.setClassCourseSemester2(ccs2);
		
		classCourseSemesterMergeService.addClassCourseSemesterMerge(ccsm);
		httpSession.setAttribute("success", "Add Merge-Class Successful!");
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}
	
	@RequestMapping(value = "/staff/mergeClasses/deleteMergeClass", method = RequestMethod.GET, params = { "mergeClassId" })
	public String deleteMergeClass(@RequestParam int mergeClassId,
			HttpSession httpSession, HttpServletRequest request) {
		classCourseSemesterMergeService.deleteClassCourseSemesterMerge(mergeClassId);
		httpSession.setAttribute("success", "Delete Course Successful!");
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}
	
	@RequestMapping(value = "/staff/mergeClasses/clear", method = RequestMethod.GET, params = { "semesterId" })
	public String clearMergeClasses(@RequestParam int semesterId,
			HttpSession httpSession, HttpServletRequest request) {
		classCourseSemesterMergeService.deleteClassCourseSemesterMerges(semesterId);
		httpSession.setAttribute("success", "Clear all Merge-Class Successful!");
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}
	
	@ExceptionHandler(ClassCourseSemesterMergeExistedException.class)
	public String handleException(HttpSession httpSession, Exception e, HttpServletRequest request) {
		httpSession.setAttribute("error",
				"This Merge-Classe existed! Please try again!");
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}
}
