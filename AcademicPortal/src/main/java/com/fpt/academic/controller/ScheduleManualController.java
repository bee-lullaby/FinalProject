package com.fpt.academic.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fpt.academic.dao.ClassCourseTeacherDAO;
import com.fpt.academic.dao.ClassDAO;
import com.fpt.academic.dao.CourseDAO;
import com.fpt.academic.dao.SemesterDAO;
import com.fpt.academic.dao.TeacherDAO;
import com.fpt.academic.model.ClassCourseTeacher;
import com.fpt.academic.model.ClassFPT;
import com.fpt.academic.model.ClassSemester;
import com.fpt.academic.model.Course;

@Controller
public class ScheduleManualController {

	List<ClassFPT> list;
	
	
	int position = 0;

	@Autowired
	private ClassDAO classDAO;
	
	@Autowired
	private CourseDAO courseDAO;
	
	@Autowired
	private TeacherDAO teacherDAO;
	
	@Autowired
	private SemesterDAO semesterDAO;
	
	@Autowired
	private ClassCourseTeacherDAO cctDAO;
	
	@RequestMapping(value = "/schedule_manual", method = RequestMethod.GET, params = { "semester_id" })
	public ModelAndView scheduleManual(@RequestParam int semester_id, Model model) {
		position = 0;
		if (list == null)
			list = classDAO.list();
		return new ModelAndView("redirect:/schedule_manual?semester_id=" +semester_id +"&classcode="
				+ list.get(position).getCode());
	}

	@RequestMapping(value = "/schedule_manual", method = RequestMethod.GET, params = { "semester_id", "classcode" })
	public void scheduleManual(@RequestParam int semester_id, @RequestParam String classcode, Model model) {
		if (list == null)
			list = classDAO.list();
		model.addAttribute("classname", classcode);
		model.addAttribute("courses", setDisplayForClass(semester_id, classcode));
		return;
	}

	@RequestMapping(value = "/schedule_manual", method = RequestMethod.GET, params = { "semester_id", "action" })
	public ModelAndView actionNextPrevious(@RequestParam int semester_id, @RequestParam String action,
			Model model) {
		if (list == null)
			list = classDAO.list();
		if (action.compareTo("next") == 0) {
			position++;
			if (position == list.size())
				position = 0;
			return new ModelAndView("redirect:/schedule_manual?semester_id=" +semester_id +"&classcode="
					+ list.get(position).getCode());
		}
		if (action.compareTo("previous") == 0) {
			position--;
			if (position == -1)
				position = list.size() - 1;
			return new ModelAndView("redirect:/schedule_manual?semester_id=" +semester_id +"&classcode="
					+ list.get(position).getCode());
		}
		return null;
	}
	
	
	public String setDisplayForClass(int semester_id, String class_code) {
		
		List<Course> courses = new ArrayList<Course>();
		
		ClassFPT classFPT = classDAO.getClassID(class_code);
		ClassSemester class_semester = semesterDAO.getClassSemesterID(semester_id, classFPT.getClass_id());
		
		List<ClassCourseTeacher> cct_List = cctDAO.getClassCourseTeacherByClassSemester(class_semester.getClass_semester_id());
		
		
		StringBuilder result = new StringBuilder("");
		int count = 1;
		for(ClassCourseTeacher cct: cct_List) {
			int course_semester_id = cct.getCourse_semester_id();
			int teacher_semester_id = cct.getTeacher_semester_id();
			
			int course_id = semesterDAO.getCourseID(course_semester_id);
			int teacher_id = semesterDAO.getTeacherID(teacher_semester_id);
			
			String course_code = courseDAO.getCourse(course_id).getCode();
			String teacher_name = teacherDAO.getTeacher(teacher_id).getName();
			
			
			result.append("<button id='button-course-" +
				    + count++ +"-" +course_code +
				    "' class='button-not-clicked'>" + course_code +
				    "<small>Teacher:" +teacher_name +"</small>" +
				    "<small>Remain slots: 30</small>" +
				    "</button>");
			
		}
		
		return result.toString();
	}
}
