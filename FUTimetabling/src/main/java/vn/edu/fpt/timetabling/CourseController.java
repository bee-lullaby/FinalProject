package vn.edu.fpt.timetabling;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
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
import vn.edu.fpt.timetabling.model.Semester;
import vn.edu.fpt.timetabling.service.CourseSemesterService;
import vn.edu.fpt.timetabling.service.CourseService;
import vn.edu.fpt.timetabling.service.DepartmentService;
import vn.edu.fpt.timetabling.service.SemesterService;

@Controller
public class CourseController extends GeneralController {
	private CourseService courseService;
	private SemesterService semesterService;
	private DepartmentService departmentService;
	private CourseSemesterService courseSemesterService;

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
		int semesterId = semesterService.listSemesters(false, false, false, false).get(0).getSemesterId();
		return "redirect:/staff/courses?semesterId=" +semesterId;
	}

	@RequestMapping(value = "/staff/courses", method = RequestMethod.GET, params = { "semesterId" })
	public String course(@RequestParam int semesterId, HttpSession httpSession,
			Model model) {
		CourseSemester courseSemester = new CourseSemester();
		courseSemester.setCourse(new Course());
		courseSemester.setSemester(new Semester());
		model.addAttribute("courseSemester", courseSemester);
		model.addAttribute("listDepartments",
				departmentService.listDepartments());
		model.addAttribute("listCourses", courseService.listCourses());
		model.addAttribute("listSemesters",
				semesterService.listSemesters(false, false, false, false));
		model.addAttribute("listCourseSemesters",
				courseSemesterService.listCourseSemesterForView(semesterId));
		return "courses";
	}
	
	@RequestMapping(value = "/staff/courses/updateCourse", method = RequestMethod.POST, 
			params={"courseId", "courseSemesterId", "courseId", "code", "name", "slots", "semesterId", "departmentId", "courseConditionId"} )
	public String updateCourse(@RequestParam int courseId, @RequestParam int courseSemesterId, @RequestParam String code,
			@RequestParam String name, @RequestParam int slots, @RequestParam int semesterId,
			@RequestParam int departmentId, @RequestParam int courseConditionId, HttpServletRequest request) {
		
		Course course = new Course();
		course.setCode(code);
		course.setName(name);
		course.setDepartment(departmentService.getDepartmentById(departmentId));
		
		if(courseId == -1) {
			courseService.addCourse(course);
		} else {
			course.setCourseId(courseId);
			courseService.updateCourse(course);
		}
		
		CourseSemester courseSemester = new CourseSemester();
		courseSemester.setSlots(slots);
		courseSemester.setSemester(semesterService.getSemesterById(semesterId, false, false, false, false));
		courseSemester.setCourse(courseService.getCourseByCode(code));
		courseSemester.setCourseCondition(courseService.getCourseById(courseConditionId));
		
		if(courseSemesterId == -1) {
			courseSemesterService.addCourseSemester(courseSemester);
		} else {
			courseSemester.setCourseSemesterId(courseSemesterId);
			courseSemesterService.updateCourseSemester(courseSemester);
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}
	
	@RequestMapping(value = "/staff/courses/addFromFile", method = RequestMethod.POST)
	public String addCourseFromFile(@RequestParam("file") MultipartFile file, @RequestParam("semesterId") int semesterId, HttpServletRequest request) {
		if (!file.isEmpty()) {
			File courses = new File(
					"D:\\FU\\Do an tot nghiep\\Data\\ServerData\\"
							+ file.getOriginalFilename());
			try {
				file.transferTo(courses);
				courseSemesterService.addCourseSemesterFromFile(courses, semesterId);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@RequestMapping("/staff/course/delete/{courseId}")
	public String deleteCourse(@PathVariable("courseId") int courseId, HttpServletRequest request) {
		courseService.deleteCourse(courseId);
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@ExceptionHandler(Exception.class)
	public String handleException(HttpSession httpSession, Exception e, HttpServletRequest request) {
		httpSession.setAttribute("error", "Error, please try again.");
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}
}
