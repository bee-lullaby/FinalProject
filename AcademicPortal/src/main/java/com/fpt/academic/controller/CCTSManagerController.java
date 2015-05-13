package com.fpt.academic.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fpt.academic.dao.ClassCourseDAO;
import com.fpt.academic.dao.ClassCourseTeacherDAO;
import com.fpt.academic.dao.ClassCourseTeacherDAOImpl;
import com.fpt.academic.dao.ClassDAO;
import com.fpt.academic.dao.CourseDAO;
import com.fpt.academic.dao.SemesterDAO;
import com.fpt.academic.dao.TeacherCourseDAO;
import com.fpt.academic.dao.TeacherDAO;
import com.fpt.academic.model.Semester;

@Controller
public class CCTSManagerController {
	
	@Autowired
	private SemesterDAO semesterDAO;
	
	@Autowired
	private ClassDAO classDAO;
	
	@Autowired
	private CourseDAO courseDAO;
	
	@Autowired
	private TeacherDAO teacherDAO;
	
	@Autowired
	private TeacherCourseDAO teacher_courseDAO;
	
	@Autowired
	private ClassCourseDAO class_courseDAO;
	
	@Autowired
	private ClassCourseTeacherDAO class_course_teacherDAO;
	
	@RequestMapping(value="/add_data", method=RequestMethod.GET)
	public void addData(Model model) {
		List<Semester> semesters = semesterDAO.list();
		model.addAttribute("semester", Semester.selectList(semesters, -1));
		return;
	}
	
	@RequestMapping(value="/add_data/addTeacherCourse", method = RequestMethod.POST)
	public ModelAndView addTeacherCourse(@RequestParam("semester_id") int semester_id, @RequestParam("file") MultipartFile file) {
		try {
			File teacher_course = new File("D:\\FU\\Do an tot nghiep\\ServerData\\" +file.getOriginalFilename());
//			if(teacher_course.exists()) {
//				if(teacher_course.delete()) 
//					teacher_course = new File("D:\\FU\\Do an tot nghiep\\ServerData\\" +file.getOriginalFilename());
//			}
			file.transferTo(teacher_course);
			teacher_courseDAO.addListTeacherCourse(teacher_course, semester_id);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ModelAndView("redirect:/add_data");
	}
	
	@RequestMapping(value="/add_data/addAll", method=RequestMethod.POST)
	public ModelAndView addAll(@RequestParam("semester_id") int semester_id) {
		class_course_teacherDAO.createClassCourseTeacher(semester_id);
		return new ModelAndView("redirect:/add_data");
	}
	
	@RequestMapping(value="/add_data/addClassCourse", method = RequestMethod.POST)
	public ModelAndView addClassCourse(@RequestParam("semester_id") int semester_id, @RequestParam("file") MultipartFile file) {
		try {
			File class_course = new File("D:\\FU\\Do an tot nghiep\\ServerData\\" +file.getOriginalFilename());
//			if(teacher_course.exists()) {
//				if(teacher_course.delete()) 
//					teacher_course = new File("D:\\FU\\Do an tot nghiep\\ServerData\\" +file.getOriginalFilename());
//			}
			file.transferTo(class_course);
			class_courseDAO.addListClassCourse(class_course, semester_id);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ModelAndView("redirect:/add_data");
	}
	
	@RequestMapping(value="/add_data", method=RequestMethod.GET, params = { "semester_id" })
	public void addDataParams(@RequestParam int semester_id, Model model) {
		List<Semester> semesters = semesterDAO.list();
		model.addAttribute("semester", Semester.selectList(semesters, -1));
		return;
	}
	
	@RequestMapping(value="/ccts_management", method=RequestMethod.GET)
	public void ctsManagement(Model model) {
		List<Semester> semesters = semesterDAO.list();
		model.addAttribute("semester", Semester.selectList(semesters, -1));
		model.addAttribute("list_class", classDAO.displaySemester(semesters.get(0).getSemester_id()));
		return;
	}
	
	@RequestMapping(value="/ccts_management", method=RequestMethod.GET, params = { "semester_id" })
	public void ctsManagementSemester(@RequestParam int semester_id, Model model) {
		model.addAttribute("semester", Semester.selectList(semesterDAO.list(), semester_id));
		model.addAttribute("list_class", classDAO.displaySemester(semester_id));
		return;
	}

	@RequestMapping(value="/ccts_class_management", method=RequestMethod.GET, params={ "code" })
	public void ctsManagementClass(@RequestParam String code, Model model) {
		model.addAttribute("title", code);
		model.addAttribute("course1", courseDAO.displayCourseOfClass(code));
		model.addAttribute("teacher1", teacherDAO.displayTeachersOfCourse(code));
		model.addAttribute("course2", courseDAO.displayCourseOfClass(code));
		model.addAttribute("teacher2", teacherDAO.displayTeachersOfCourse(code));
		model.addAttribute("course3", courseDAO.displayCourseOfClass(code));
		model.addAttribute("teacher3", teacherDAO.displayTeachersOfCourse(code));
		return;
	}
}
