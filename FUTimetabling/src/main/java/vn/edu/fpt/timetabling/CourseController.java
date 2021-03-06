package vn.edu.fpt.timetabling;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import vn.edu.fpt.timetabling.exception.CourseExistedException;
import vn.edu.fpt.timetabling.model.Course;
import vn.edu.fpt.timetabling.model.CourseSemester;
import vn.edu.fpt.timetabling.model.Semester;
import vn.edu.fpt.timetabling.service.CourseSemesterService;
import vn.edu.fpt.timetabling.service.CourseService;
import vn.edu.fpt.timetabling.service.DepartmentService;
import vn.edu.fpt.timetabling.service.SemesterService;
import vn.edu.fpt.timetabling.service.TimetableService;

@Controller
public class CourseController extends GeneralController {
	private CourseService courseService;
	private SemesterService semesterService;
	private DepartmentService departmentService;
	private CourseSemesterService courseSemesterService;

	@Autowired
	private TimetableService timetableService;
	
	@Autowired(required = true)
	@Qualifier(value = "courseService")
	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}

	@Autowired(required = true)
	@Qualifier(value = "semesterService")
	public void setSemesterService(SemesterService semesterService) {
		this.semesterService = semesterService;
	}

	@Autowired(required = true)
	@Qualifier(value = "departmentService")
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	@Autowired(required = true)
	@Qualifier(value = "courseSemesterService")
	public void setCourseSemesterService(
			CourseSemesterService courseSemesterService) {
		this.courseSemesterService = courseSemesterService;
	}

	@RequestMapping(value = "/staff/courses", method = RequestMethod.GET)
	public String courseInit(HttpSession httpSession, Model model) {
		List<Semester> semesters = semesterService
				.listSemesters(false, false, false, false);
		int semesterId = 0;
		if(!semesters.isEmpty()) 
			semesterId = semesters.get(0).getSemesterId();
		return "redirect:/staff/courses?semesterId=" + semesterId;
	}

	@RequestMapping(value = "/staff/courses", method = RequestMethod.GET, params = { "semesterId" })
	public String course(@RequestParam int semesterId, HttpSession httpSession,
			Model model) {
		model.addAttribute("listDepartments",
				departmentService.listDepartments());
		model.addAttribute("listCourses", courseService.listCourses());
		model.addAttribute("listSemesters",
				semesterService.listSemesters(false, false, false, false));
		model.addAttribute("listCourseSemesters",
				courseSemesterService.listCourseSemesterForView(semesterId));
		checkError(httpSession, model);
		notifySuccess(httpSession, model);
		return "courses";
	}

	@RequestMapping(value = "/staff/courses/updateCourse", method = RequestMethod.POST, params = {
			"courseId", "courseSemesterId", "courseId", "code", "name",
			"slots", "semesterId", "departmentId"})
	public String updateCourse(@RequestParam int courseId,
			@RequestParam int courseSemesterId, @RequestParam String code,
			@RequestParam String name, @RequestParam int slots,
			@RequestParam int semesterId, @RequestParam int departmentId,
			@RequestParam(value="courseConditionId", required = false) int courseConditionId, HttpSession httpSession, HttpServletRequest request)
			throws CourseExistedException {

		Course course = courseService.getCourseByCode(code);
		CourseSemester courseSemester = courseSemesterService.getCourseSemesterByCourseCodeSemester(code, semesterId, false, false, false);
		if (courseId == -1 && courseSemester != null) {
			throw new CourseExistedException();
		} else {
			if (course == null) {
				course = new Course();
			}
		}

		course.setCode(code);
		course.setName(name);
		course.setDepartment(departmentService.getDepartmentById(departmentId));

		if (course.getCourseId() == -1) {
			courseService.addCourse(course);
		} else if (courseId != -1) {
			course.setCourseId(courseId);
			courseService.updateCourse(course);
		}

		if(courseSemester == null) {
			courseSemester = new CourseSemester();
		}
		courseSemester.setSlots(slots);
		courseSemester.setSemester(semesterService.getSemesterById(semesterId,
				false, false, false, false));
		courseSemester.setCourse(courseService.getCourseByCode(code));
		courseSemester.setCourseCondition(courseService
				.getCourseById(courseConditionId));

		if (courseSemesterId == -1) {
			courseSemesterService.addCourseSemester(courseSemester);
			httpSession.setAttribute("success", "Add Course Successful!");
		} else {
			courseSemester.setCourseSemesterId(courseSemesterId);
			courseSemesterService.updateCourseSemester(courseSemester);
			httpSession.setAttribute("success", "Edit Course Successful!");
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@RequestMapping(value = "/staff/courses/addFromFile", method = RequestMethod.POST)
	public String addCourseFromFile(@RequestParam("file") MultipartFile file, @RequestParam("semesterId") int semesterId, HttpSession httpSession,
			HttpServletRequest request) {
		if(timetableService.listTimetablesBySemester(semesterId).size() > 0) {
			httpSession.setAttribute("error", "Timetable existed ! Can't re-add!");
			String referer = request.getHeader("Referer");
			return "redirect:" + referer;
		}
		
		if (!file.isEmpty()) {
			File courses = new File("courses.xlxs");
			try {
				file.transferTo(courses);
				List<String> check = courseSemesterService.addCourseSemesterFromFile(courses, semesterId);
				if(check.isEmpty()) {
					httpSession.setAttribute("success", "Add Courses From File Successful!");
				} else {
					String text = "Department " +check.get(0);
					for(int i = 1; i < check.size(); i++) {
						text += ", " +check.get(i);
					}
					if(check.size() == 1) text += " is ";
					else text += " are ";
					text += "not existed! Please insert department first then try again!";
					httpSession.setAttribute("error", text);
					httpSession.setAttribute("success", "Others courses were added successful!");
				}
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@RequestMapping(value = "/staff/courses/deleteCourse", method = RequestMethod.GET, params = { "semesterId", "courseId" })
	public String deleteCourse(@RequestParam int semesterId, @RequestParam int courseId,
			HttpSession httpSession, HttpServletRequest request) {
		courseService.deleteCourse(semesterId, courseId);
		httpSession.setAttribute("success", "Delete Course Successful!");
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@ExceptionHandler(CourseExistedException.class)
	public String handleException(HttpSession httpSession, Exception e, HttpServletRequest request) {
		httpSession.setAttribute("error",
				"This Course's code existed! Please try again!");
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}
}
