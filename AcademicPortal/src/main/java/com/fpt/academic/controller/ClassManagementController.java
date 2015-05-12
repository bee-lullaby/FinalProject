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

import com.fpt.academic.dao.ClassDAO;
import com.fpt.academic.model.ClassFPT;

@Controller
public class ClassManagementController {
	@Autowired
	private ClassDAO classDAO;
	
	@RequestMapping(value="/class_management", method = RequestMethod.GET)
	public void courseManagement(Model model) {
		model.addAttribute("classfpt", new ClassFPT());
		model.addAttribute("data", classDAO.display());
		return;
	}
	
	@RequestMapping(value= "/class_management/addClass", method = RequestMethod.POST)
	public ModelAndView  addCourse(@ModelAttribute ClassFPT classfpt) {
		classDAO.addClass(classfpt);
		return new ModelAndView("redirect:/class_management");
	}
	
	@RequestMapping(value= "/class_management/addfile", method = RequestMethod.POST)
	public ModelAndView  addCourseFromFile(@RequestParam("file") MultipartFile file) {
		if(!file.isEmpty()){
			File classfpt = new File("D:\\FU\\Do an tot nghiep\\ServerData\\" +file.getOriginalFilename());
			try {
				file.transferTo(classfpt);
				classDAO.addListClass(classfpt);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return  new ModelAndView("redirect:/class_management");
	}
	
	@RequestMapping(value= "/class_management/updateClass", method = RequestMethod.POST, params = { "save" })
	public ModelAndView saveCourse(@RequestParam String save, @ModelAttribute ClassFPT classfpt) {
		classDAO.updateClass(classfpt);
		return new ModelAndView("redirect:/class_management");
	}
	
	@RequestMapping(value= "/class_management/updateClass", method = RequestMethod.POST, params = { "delete" })
	public ModelAndView delCourse(@RequestParam String delete, @ModelAttribute ClassFPT classfpt) {
		classDAO.deleteClass(classfpt);
		return new ModelAndView("redirect:/class_management");
	}
}
