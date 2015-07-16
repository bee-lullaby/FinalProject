package vn.edu.fpt.timetabling;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import vn.edu.fpt.timetabling.model.Teacher;
import vn.edu.fpt.timetabling.service.TeacherCourseSemesterService;
import vn.edu.fpt.timetabling.service.TeacherService;

@Controller
public class TeacherController extends GeneralController {
	private TeacherService teacherService;

	@Autowired
	private TeacherCourseSemesterService teacherCourseSemesterService;

	@Autowired(required = true)
	@Qualifier(value = "teacherService")
	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	@RequestMapping(value = "/staff/teachers", method = RequestMethod.GET)
	public String listCourse(HttpSession httpSession, Model model) {
		model.addAttribute("teacher", new Teacher());
		model.addAttribute("listTeachers", teacherService.listTeachers());
		checkError(httpSession, model);
		return "teacher";
	}

	// For add and update person both
	@RequestMapping(value = "/staff/teacher/add", method = RequestMethod.POST)
	public String addCourse(@ModelAttribute("teacher") Teacher teacher) {
		if (teacher.getTeacherId() == 0) {
			// new course, add it
			teacherService.addTeacher(teacher);
		} else {
			// existing course, call update
			teacherService.updateTeacher(teacher);
		}
		return "redirect:/staff/teachers";
	}

	@RequestMapping("/staff/teacher/delete/{teacherId}")
	public String deleteCourse(@PathVariable("teacherId") int teacherId) {
		teacherService.deleteTeacher(teacherId);
		return "redirect:/staff/teachers";
	}

	@RequestMapping("/staff/teacher/edit/{teacherId}")
	public String editCourse(@PathVariable("teacherId") int teacherId, Model model) {
		model.addAttribute("teacher", teacherService.getTeacherById(teacherId));
		model.addAttribute("listTeachers", teacherService.listTeachers());
		return "teacher";
	}

	@RequestMapping("/staff/teacherCourse/addFromFile")
	public String addTeacherCourseFromFile(@RequestParam("file") MultipartFile file) {
		if (!file.isEmpty()) {
			File teacherCourses = new File("D:\\FU\\Do an tot nghiep\\Data\\ServerData\\" + file.getOriginalFilename());
			try {
				file.transferTo(teacherCourses);
				teacherCourseSemesterService.addTeacherCourseSemesterFromFile(teacherCourses);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "redirect:/staff/teachers";
	}

	@ExceptionHandler(Exception.class)
	public String handleException(HttpSession httpSession, Exception e) {
		httpSession.setAttribute("error", "Error, please try again.");
		return "redirect:/staff/teachers";
	}
}
