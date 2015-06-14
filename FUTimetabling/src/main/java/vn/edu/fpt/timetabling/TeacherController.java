package vn.edu.fpt.timetabling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.edu.fpt.timetabling.model.Teacher;
import vn.edu.fpt.timetabling.service.TeacherService;

@Controller
public class TeacherController {
	private TeacherService teacherService;

	@Autowired(required = true)
	@Qualifier(value = "teacherService")
	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	@RequestMapping(value = "/teachers", method = RequestMethod.GET)
	public String listCourse(Model model) {
		model.addAttribute("teacher", new Teacher());
		model.addAttribute("listTeachers", teacherService.listTeachers());
		return "teacher";
	}

	// For add and update person both
	@RequestMapping(value = "/teacher/add", method = RequestMethod.POST)
	public String addCourse(@ModelAttribute("teacher") Teacher teacher) {
		if (teacher.getTeacherId() == 0) {
			// new course, add it
			teacherService.addTeacher(teacher);
		} else {
			// existing course, call update
			teacherService.updateTeacher(teacher);
		}
		return "redirect:/teachers";
	}

	@RequestMapping("/teacher/delete/{teacherId}")
	public String deleteCourse(@PathVariable("teacherId") int teacherId) {
		teacherService.deleteTeacher(teacherId);
		return "redirect:/teachers";
	}

	@RequestMapping("/teacher/edit/{teacherId}")
	public String editCourse(@PathVariable("teacherId") int teacherId, Model model) {
		model.addAttribute("teacher", teacherService.getTeacherById(teacherId));
		model.addAttribute("listTeachers", teacherService.listTeachers());
		return "teacher";
	}
}