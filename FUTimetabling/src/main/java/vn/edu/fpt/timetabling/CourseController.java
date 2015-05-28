package vn.edu.fpt.timetabling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.edu.fpt.timetabling.model.Course;
import vn.edu.fpt.timetabling.service.CourseService;

@Controller
public class CourseController {

	private CourseService courseService;

	@Autowired(required = true)
	@Qualifier(value = "courseService")
	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}

	@RequestMapping(value = "/courses", method = RequestMethod.GET)
	public String listCourse(Model model) {
		model.addAttribute("course", new Course());
		model.addAttribute("listCourses", courseService.listCourses());
		return "course";
	}

	// For add and update person both
	@RequestMapping(value = "/course/add", method = RequestMethod.POST)
	public String addCourse(@ModelAttribute("course") Course course) {
		if (course.getCourseId() == 0) {
			// new course, add it
			courseService.addCourse(course);
		} else {
			// existing course, call update
			courseService.updateCourse(course);
		}
		return "redirect:/courses";
	}

	@RequestMapping("/delete/{courseId}")
	public String deleteCourse(@PathVariable("courseId") int courseId) {
		courseService.deleteCourse(courseId);
		return "redirect:/courses";
	}

	@RequestMapping("/edit/{courseId}")
	public String editCourse(@PathVariable("courseId") int courseId, Model model) {
		model.addAttribute("course", courseService.getCourseById(courseId));
		model.addAttribute("listCourses", courseService.listCourses());
		return "course";
	}
}
