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

import vn.edu.fpt.timetabling.model.Department;
import vn.edu.fpt.timetabling.service.CourseService;
import vn.edu.fpt.timetabling.service.DepartmentService;

@Controller
public class DepartmentController extends GeneralController {
	private DepartmentService departmentService;
	private CourseService courseService;

	@Autowired(required = true)
	@Qualifier(value = "departmentService")
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "courseService")
	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}

	@RequestMapping(value = "/staff/departments", method = RequestMethod.GET)
	public String departmentInit(HttpSession httpSession, Model model) {
		model.addAttribute("listDepartments", departmentService.listDepartments());
		model.addAttribute("listCourses", courseService.listCourses());
		return "departments";
	}
	
	@RequestMapping(value = "/staff/departments/updateDepartment", method = RequestMethod.POST,
			params={"departmentId", "code", "name"})
	public String updateDepartment(@RequestParam int departmentId, @RequestParam String code,
			@RequestParam String name, HttpServletRequest request) {
		Department d = new Department();
		d.setCode(code); d.setName(name);
		if (departmentId < 0) {
			departmentService.addDepartment(d);
		} else {
			System.out.println("1");
			d.setDepartmentId(departmentId);
			departmentService.updateDepartment(d);
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}
	
	@RequestMapping(value = "/staff/departments/addFromFile", method = RequestMethod.POST)
	public String addDepartmentsFromFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		System.out.println("1");
		if (!file.isEmpty()) {
			File departments = new File("D:\\FU\\Do an tot nghiep\\Data\\ServerData\\" + file.getOriginalFilename());
			try {
				file.transferTo(departments);
				departmentService.addDepartmentFromFile(departments);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@RequestMapping("/staff/departments/delete/{departmentId}")
	public String deleteDepartment(@PathVariable("departmentId") int departmentId, HttpServletRequest request) {
		departmentService.deleteDepartment(departmentId);
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
