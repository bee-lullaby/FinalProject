package vn.edu.fpt.timetabling;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import vn.edu.fpt.timetabling.model.ClassFPT;
import vn.edu.fpt.timetabling.service.ClassService;
import vn.edu.fpt.timetabling.service.CourseService;
import vn.edu.fpt.timetabling.service.SpecializedService;
import vn.edu.fpt.timetabling.utils.Const.ClassType;

@Controller
public class ClassController extends GeneralController {
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

	@RequestMapping(value = "/staff/classes", method = RequestMethod.GET)
	public String listClasses(HttpSession httpSession, Model model) {
		model.addAttribute("classFPT", new ClassFPT());
		model.addAttribute("classes", classService.listClasses());
		model.addAttribute("specializeds", specializedService.listSpecializeds(false, false));
		model.addAttribute("courses", courseService.listCourses());
		checkError(httpSession, model);
		return "classManagement";
	}

	// For add and update person both
	@RequestMapping(value = "/staff/class/add", method = RequestMethod.POST)
	public String addClass(@RequestParam(value = "classId", required = true) Integer classId,
			@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "specialized", required = false) Integer specializedId,
			@RequestParam(value = "batch", required = false) Integer batch,
			@RequestParam(value = "course", required = false) Integer courseId) {
		if (type.equals(ClassType.SPECIALIZED)) {
			if (specializedId == null || batch == null) {
				return "redirect:/staff/classes";
			}
		} else if (type.equals("Course")) {
			if (courseId == null) {
				return "redirect:/staff/classes";
			}
		} else {
			return "redirect:/staff/classes";
		}
		ClassFPT classFPT = classService.getClassById(classId);
		if (classFPT == null) {
			classFPT = new ClassFPT();
		}
		classFPT.setType(type);
		String classCodePrefix;
		if (type.equals(ClassType.SPECIALIZED)) {
			classFPT.setSpecialized(specializedService.getSpecializedById(specializedId, false, false));
			classFPT.setBatch(batch);
			classFPT.setCourse(null);
			classCodePrefix = classFPT.getSpecialized().getCode() + String.format("%02d", batch);
		} else {
			classFPT.setBatch(null);
			classFPT.setSpecialized(null);
			classFPT.setCourse(courseService.getCourseById(courseId));
			classCodePrefix = classFPT.getCourse().getCode();
		}
		classFPT.setNumber(classService.getNextClassNumber(classCodePrefix));
		if (type.equals(ClassType.SPECIALIZED)) {
			classFPT.setCode(classCodePrefix + String.format("%02d", classFPT.getNumber()));
		} else {
			classFPT.setCode(classCodePrefix + "." + classFPT.getNumber());
		}
		if (classFPT.getClassId() == 0) {
			// new class, add it
			classService.addClass(classFPT);
		} else {
			// existing class, call update
			classService.updateClass(classFPT);
		}
		return "redirect:/staff/classes";
	}

	@RequestMapping("/staff/class/delete/{classId}")
	public String deleteClass(@PathVariable("classId") int classId) {
		classService.deleteClass(classId);
		return "redirect:/staff/classes";
	}

	@RequestMapping("/staff/class/edit/{classId}")
	public String editClass(@PathVariable("classId") int classId, Model model) {
		ClassFPT classFPT = classService.getClassById(classId);
		if (classFPT == null) {
			return "redirect:/staff/classes";
		}
		model.addAttribute("classFPT", classFPT);
		model.addAttribute("classes", classService.listClasses());
		model.addAttribute("specializeds", specializedService.listSpecializeds(false, false));
		model.addAttribute("courses", courseService.listCourses());
		if (classFPT.getType().equals(ClassType.SPECIALIZED)) {
			model.addAttribute("specialized", classFPT.getSpecialized().getSpecializedId());
		} else {
			model.addAttribute("course", classFPT.getCourse().getCourseId());
		}
		return "classManagement";
	}

	@ExceptionHandler(Exception.class)
	public String handleException(HttpSession httpSession, Exception e) {
		httpSession.setAttribute("error", "Error, please try again.");
		return "redirect:/staff/classes";
	}
}
