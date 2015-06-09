package vn.edu.fpt.timetabling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.edu.fpt.timetabling.model.ClassFPT;
import vn.edu.fpt.timetabling.service.ClassService;

public class ClassController {
	private ClassService classService;
	
	@Autowired(required = true)
	@Qualifier(value = "classService")
	public void setClassService(ClassService classService) {
		this.classService = classService;
	}
	
	@RequestMapping(value = "/classes", method = RequestMethod.GET)
	public String listCourse(Model model) {
		model.addAttribute("class", new ClassFPT());
		model.addAttribute("listClasses", classService.listClasses());
		return "course";
	}
}
