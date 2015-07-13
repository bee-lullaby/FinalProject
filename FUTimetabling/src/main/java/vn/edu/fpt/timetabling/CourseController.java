package vn.edu.fpt.timetabling;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import vn.edu.fpt.timetabling.model.Course;
import vn.edu.fpt.timetabling.service.ClassCourseSemesterService;
import vn.edu.fpt.timetabling.service.CourseService;

@Controller
public class CourseController {

	private CourseService courseService;
	private ClassCourseSemesterService classCourseSemesterService;

	@Autowired(required = true)
	@Qualifier(value = "courseService")
	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}

	@Autowired(required = true)
	@Qualifier(value = "classCourseSemesterService")
	public void setClassCourseSemesterService(
			ClassCourseSemesterService classCourseSemesterService) {
		this.classCourseSemesterService = classCourseSemesterService;
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

	
	@RequestMapping(value = "/courses/addFromFile", method = RequestMethod.POST)
	public String addCourseFromFile(@RequestParam("file") MultipartFile file) {
		if (!file.isEmpty()) {

			File courses = new File(
					"D:\\FU\\Do an tot nghiep\\Data\\ServerData\\"
							+ file.getOriginalFilename());
			try {
				file.transferTo(courses);
				courseService.addCourseFromFile(courses);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "redirect:/courses";
	}

	@RequestMapping(value = "/classCourse/addFromFile", method = RequestMethod.POST)
	public String addClassCourseFromFile(
			@RequestParam("file") MultipartFile file, @RequestParam("semesterId") int semesterId) {
		if (!file.isEmpty()) {
			File classCourses = new File(
					"D:\\FU\\Do an tot nghiep\\Data\\ServerData\\"
							+ file.getOriginalFilename());
			try {
				file.transferTo(classCourses);
				classCourseSemesterService.addClassCourseSemesterFromFile(classCourses, semesterId);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "redirect:/courses";
	}

	@RequestMapping("/course/delete/{courseId}")
	public String deleteCourse(@PathVariable("courseId") int courseId) {
		courseService.deleteCourse(courseId);
		return "redirect:/courses";
	}

	@RequestMapping("/course/edit/{courseId}")
	public String editCourse(@PathVariable("courseId") int courseId, Model model) {
		model.addAttribute("course", courseService.getCourseById(courseId));
		model.addAttribute("listCourses", courseService.listCourses());
		return "course";
	}
}
