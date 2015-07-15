package vn.edu.fpt.timetabling;

import java.io.File;
import java.io.IOException;
import java.util.List;

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
import org.springframework.web.multipart.MultipartFile;

import vn.edu.fpt.timetabling.model.Course;
import vn.edu.fpt.timetabling.model.CourseSemester;
import vn.edu.fpt.timetabling.service.CourseSemesterService;
import vn.edu.fpt.timetabling.service.CourseService;
import vn.edu.fpt.timetabling.service.SemesterService;

@Controller
public class CourseSemesterController extends GeneralController {
	private CourseSemesterService courseSemesterService;
	private SemesterService semesterService;
	private CourseService courseService;

	@Autowired(required = true)
	@Qualifier(value = "courseSemesterService")
	public void setCourseSemesterService(CourseSemesterService courseSemesterService) {
		this.courseSemesterService = courseSemesterService;
	}

	@Autowired(required = true)
	@Qualifier(value = "semesterService")
	public void setSemesterService(SemesterService semesterService) {
		this.semesterService = semesterService;
	}

	@Autowired(required = true)
	@Qualifier(value = "courseService")
	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}

	@RequestMapping(value = "/courseSemesters", method = RequestMethod.GET)
	public String listCourseSemesters(HttpSession httpSession, Model model) {
		model.addAttribute("courseSemester", new CourseSemester());
		model.addAttribute("listCourseSemesters", courseSemesterService.listCourseSemesters(false, false, false));
		model.addAttribute("semesters", semesterService.listSemesters(false, false, false, false));
		model.addAttribute("courses", courseService.listCourses());
		Course course = new Course();
		course.setCourseId(-1);
		course.setName("none");
		List<Course> courses = courseService.listCourses();
		courses.add(0, course);
		model.addAttribute("courseConditions", courses);
		checkError(httpSession, model);
		return "courseSemester";
	}

	// For add and update person both
	@RequestMapping(value = "/courseSemester/addFromFile", method = RequestMethod.POST)
	public String addCourseSemesterFromFile(@RequestParam("file") MultipartFile file,
			@RequestParam("semesterId") int semesterId) {
		if (!file.isEmpty()) {
			File courseSemesters = new File(
					"D:\\FU\\Do an tot nghiep\\Data\\ServerData\\" + file.getOriginalFilename());
			try {
				file.transferTo(courseSemesters);

				courseSemesterService.addCourseSemesterFromFile(courseSemesters, semesterId);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "redirect:/courseSemesters";
	}

	@RequestMapping(value = "/courseSemester/add", method = RequestMethod.POST)
	public String addCourseSemester(@RequestParam(value = "courseSemesterId", required = true) int courseSemesterId,
			@RequestParam(value = "course", required = true) int courseId,
			@RequestParam(value = "semester", required = true) int semesterId,
			@RequestParam(value = "slots", required = true) int slots,
			@RequestParam(value = "courseCondition", required = false) Integer courseConditionId) {
		if (courseId == courseConditionId) {
			return "redirect:/courseSemesters";
		}
		CourseSemester courseSemester = courseSemesterService.getCourseSemesterById(courseSemesterId, false, false,
				false);
		if (courseSemester == null) {
			courseSemester = new CourseSemester();
		}
		courseSemester.setCourse(courseService.getCourseById(courseId));
		courseSemester.setSemester(semesterService.getSemesterById(semesterId, false, false, false, false));
		courseSemester.setSlots(slots);
		if (courseConditionId != null) {
			courseSemester.setCourseCondition(courseService.getCourseById(courseConditionId));
		} else {
			courseSemester.setCourseCondition(null);
		}
		if (courseSemester.getCourseSemesterId() == 0) {
			// new class semester, add it
			courseSemesterService.addCourseSemester(courseSemester);
		} else {
			// existing class semester, call update
			courseSemesterService.updateCourseSemester(courseSemester);
		}
		return "redirect:/courseSemesters";
	}

	@RequestMapping("/courseSemester/delete/{courseSemesterId}")
	public String deleteCourseSemester(@PathVariable("courseSemesterId") int courseSemesterId) {
		courseSemesterService.deleteCourseSemester(courseSemesterId);
		return "redirect:/courseSemesters";
	}

	@RequestMapping("/courseSemester/edit/{courseSemesterId}")
	public String editCourseSemester(@PathVariable("courseSemesterId") int courseSemesterId, Model model) {
		CourseSemester courseSemester = courseSemesterService.getCourseSemesterById(courseSemesterId, false, false,
				false);
		if (courseSemester == null) {
			return "redirect:/courseSemesters";
		}
		model.addAttribute("courseSemester", courseSemester);
		model.addAttribute("courseSemesters", courseSemesterService.listCourseSemesters(false, false, false));
		model.addAttribute("semesters", semesterService.listSemesters(false, false, false, false));
		model.addAttribute("courses", courseService.listCourses());
		Course course = new Course();
		course.setCourseId(-1);
		course.setName("none");
		List<Course> courses = courseService.listCourses();
		courses.add(0, course);
		model.addAttribute("courseConditions", courses);
		model.addAttribute("courseId", courseSemester.getCourse().getCourseId());
		if (courseSemester.getCourseCondition() != null) {
			model.addAttribute("courseConditionId", courseSemester.getCourseCondition().getCourseId());
		}
		return "courseSemester";
	}

	@ExceptionHandler(Exception.class)
	public String handleException(HttpSession httpSession, Exception e) {
		httpSession.setAttribute("error", "Error, please try again.");
		return "redirect:/courseSemesters";
	}
}
