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
import com.fpt.academic.model.Course;

@Controller
public class CourseManagementController {
	
	@Autowired
	private CourseDAO courseDAO;
	
	@RequestMapping(value="/course_management", method = RequestMethod.GET)
	public void courseManagement(Model model) {
		model.addAttribute("course", new Course());
		model.addAttribute("data", courseDAO.display());
		return;
	}
	
	@RequestMapping(value= "/course_management/addCourse", method = RequestMethod.POST)
	public ModelAndView  addCourse(@ModelAttribute Course course) {
		courseDAO.addCourse(course);
		return new ModelAndView("redirect:/course_management");
	}
	
	@RequestMapping(value= "/course_management/addfile", method = RequestMethod.POST)
	public ModelAndView  addCourseFromFile(@RequestParam("file") MultipartFile file) {
		if(!file.isEmpty()){
			File course = new File("D:\\FU\\Do an tot nghiep\\ServerData\\" +file.getOriginalFilename());
			try {
				file.transferTo(course);
				courseDAO.addListCourse(course);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return  new ModelAndView("redirect:/course_management");
	}
	
	@RequestMapping(value= "/course_management/updateCourse", method = RequestMethod.POST, params = { "save" })
	public ModelAndView saveCourse(@RequestParam String save, @ModelAttribute Course course) {
		courseDAO.updateCourse(course);
		return new ModelAndView("redirect:/course_management");
	}
	
	@RequestMapping(value= "/course_management/updateCourse", method = RequestMethod.POST, params = { "delete" })
	public ModelAndView delCourse(@RequestParam String delete, @ModelAttribute Course course) {
		courseDAO.deleteCourse(course);
		return new ModelAndView("redirect:/course_management");
	}
}
