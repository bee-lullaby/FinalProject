package vn.edu.fpt.timetabling;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import vn.edu.fpt.timetabling.model.ClassFPT;
import vn.edu.fpt.timetabling.model.Course;
import vn.edu.fpt.timetabling.model.Specialized;
import vn.edu.fpt.timetabling.service.ClassService;
import vn.edu.fpt.timetabling.service.CourseService;
import vn.edu.fpt.timetabling.service.SpecializedService;

@Controller
public class ClassController {
	private ClassService classService;
	private SpecializedService specializedService;
	private CourseService courseService;

	@Autowired(required = true)
	@Qualifier(value = "classService")
	public void setClassService(ClassService classService) {
		this.classService = classService;
	}

	@Autowired(required = true)
	@Qualifier(value = "specializedService")
	public void setSpecializedService(SpecializedService specializedService) {
		this.specializedService = specializedService;
	}

	@Autowired(required = true)
	@Qualifier(value = "courseService")
	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}

	@RequestMapping(value = "/classes", method = RequestMethod.GET)
	public String listCourse(HttpSession session, Model model) {
		Object idToken = session.getAttribute("idToken");
		Object accessToken = session.getAttribute("accessToken");
		Object email = session.getAttribute("email");
		if (idToken == null || accessToken == null || email == null) {
			return "home";
		}
		model.addAttribute("classFPT", new ClassFPT());
		model.addAttribute("classes", classService.listClasses());
		List<Specialized> specializeds = specializedService.listSpecializeds();
		List<Course> courses = courseService.listCourses();
		HashMap<Integer, String> specializedMap = new HashMap<Integer, String>();
		HashMap<Integer, String> courseMap = new HashMap<Integer, String>();
		for (Specialized specialized : specializeds) {
			specializedMap.put(specialized.getSpecializedId(),
					specialized.getName());
		}
		for (Course course : courses) {
			courseMap.put(course.getCourseId(), course.getName());
		}
		model.addAttribute("specializeds", specializedMap);
		model.addAttribute("courses", courseMap);
		return "classManagement";
	}

	// For add and update person both
	@RequestMapping(value = "/class/add", method = RequestMethod.POST)
	public String addClass(
			@RequestParam(value = "classId", required = true) Integer classId,
			@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "specialized", required = false) Integer specializedId,
			@RequestParam(value = "batch", required = false) Integer batch,
			@RequestParam(value = "course", required = false) Integer courseId) {
		if (type.equals("Specialized")) {
			if (specializedId == null || batch == null) {
				return "redirect:/classes";
			}
		} else if (type.equals("Course")) {
			if (courseId == null) {
				return "redirect:/classes";
			}
		} else {
			return "redirect:/classes";
		}
		ClassFPT classFPT = new ClassFPT();
		classFPT.setType(type);
		String classCodePrefix;
		if (type.equals("Specialized")) {
			classFPT.setSpecialized(specializedService
					.getSpecializedById(specializedId));
			classFPT.setBatch(batch);
			classFPT.setCourse(null);
			classCodePrefix = classFPT.getSpecialized().getCode()
					+ String.format("%02d", batch);
		} else {
			classFPT.setBatch(null);
			classFPT.setSpecialized(null);
			classFPT.setCourse(courseService.getCourseById(courseId));
			classCodePrefix = classFPT.getCourse().getCode();
		}
		classFPT.setNumber(classService.getNextClassNumber(classCodePrefix));
		if (type.equals("Specialized")) {
			classFPT.setCode(classCodePrefix
					+ String.format("%02d", classFPT.getNumber()));
		} else {
			classFPT.setCode(classCodePrefix + "." + classFPT.getNumber());
		}
		if (classFPT.getClassId() == 0) {
			// new course, add it
			classService.addClass(classFPT);
		} else {
			// existing course, call update
			classService.updateClass(classFPT);
		}
		return "redirect:/classes";
	}
}
