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

import com.fpt.academic.dao.TeacherDAO;
import com.fpt.academic.model.Teacher;


@Controller
public class TeacherManagementController {
	

	@Autowired
	private TeacherDAO teacherDAO;
	
	@RequestMapping(value="/teacher_management", method = RequestMethod.GET)
	public void teacherManagement(Model model) {
		model.addAttribute("teacher", new Teacher());
		model.addAttribute("data", teacherDAO.display());
		return;
	}
	
	@RequestMapping(value= "/teacher_management/addTeacher", method = RequestMethod.POST)
	public ModelAndView  addTeacher(@ModelAttribute Teacher teacher) {
		teacherDAO.addTeacher(teacher);
		return new ModelAndView("redirect:/teacher_management");
	}
	
	@RequestMapping(value= "/teacher_management/addfile", method = RequestMethod.POST)
	public ModelAndView  addTeacherFromFile(@RequestParam("file") MultipartFile file) {
		if(!file.isEmpty()){
			File teachers = new File("D:\\" +file.getOriginalFilename());
			try {
				file.transferTo(teachers);
				teacherDAO.addListTeacher(teachers);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return  new ModelAndView("redirect:/teacher_management");
	}
	
	@RequestMapping(value= "/teacher_management/updateTeacher", method = RequestMethod.POST, params = { "save" })
	public ModelAndView saveTeacher(@RequestParam String save, @ModelAttribute Teacher teacher) {
		teacherDAO.updateTeacher(teacher);
		return new ModelAndView("redirect:/teacher_management");
	}
	
	@RequestMapping(value= "/teacher_management/updateTeacher", method = RequestMethod.POST, params = { "delete" })
	public ModelAndView delTeacher(@RequestParam String delete, @ModelAttribute Teacher teacher) {
		teacherDAO.deleteTeacher(teacher);
		return new ModelAndView("redirect:/teacher_management");
	}
}
