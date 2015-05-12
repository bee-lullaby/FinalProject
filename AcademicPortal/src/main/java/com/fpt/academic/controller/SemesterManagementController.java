package com.fpt.academic.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fpt.academic.dao.CourseDAO;
import com.fpt.academic.dao.SemesterDAO;
import com.fpt.academic.model.Course;
import com.fpt.academic.model.Semester;

@Controller
public class SemesterManagementController {
	
	@Autowired
	private SemesterDAO semesterDAO;
	
	@RequestMapping(value="/semester_management", method = RequestMethod.GET)
	public void semesterManagement(Model model) {
		model.addAttribute("semester", new Semester());
		model.addAttribute("data", semesterDAO.display());
		return;
	}
	
	@RequestMapping(value= "/semester_management/addSemester", method = RequestMethod.POST)
	public ModelAndView  addCourse(@ModelAttribute Semester semester) {
		semesterDAO.addSemester(semester);
		return new ModelAndView("redirect:/semester_management");
	}
	
	@RequestMapping(value= "/semester_management/updateSemester", method = RequestMethod.POST, params = { "save" })
	public ModelAndView saveCourse(@RequestParam String save, @ModelAttribute Semester semester) {
		semesterDAO.updateSemester(semester);
		return new ModelAndView("redirect:/course_management");
	}
	
	@RequestMapping(value= "/semester_management/updateSemester", method = RequestMethod.POST, params = { "delete" })
	public ModelAndView delCourse(@RequestParam String delete, @ModelAttribute Semester semester) {
		semesterDAO.deleteSemester(semester);
		return new ModelAndView("redirect:/course_management");
	}
}
