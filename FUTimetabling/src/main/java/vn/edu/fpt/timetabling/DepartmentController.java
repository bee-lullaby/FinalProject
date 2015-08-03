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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import vn.edu.fpt.timetabling.exception.DepartmentExistedException;
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
		model.addAttribute("listDepartments",
				departmentService.listDepartments());
		model.addAttribute("listCourses", courseService.listCourses());
		checkError(httpSession, model);
		notifySuccess(httpSession, model);
		return "departments";
	}

	@RequestMapping(value = "/staff/departments/updateDepartment", method = RequestMethod.POST, 
			params = { "code", "name" })
	public String updateDepartment(@RequestParam String code, @RequestParam String name,
			HttpSession httpSession) throws DepartmentExistedException {
		Department d = departmentService.getDepartmentByCode(code);
		if (d != null)
			throw new DepartmentExistedException();
		else
			d = new Department();
		d.setCode(code);
		d.setName(name);
		departmentService.addDepartment(d);
		httpSession.setAttribute("success", "Add Department Successful!");
		return "redirect:/staff/departments";
	}

	@RequestMapping(value = "/staff/departments/addFromFile", method = RequestMethod.POST)
	public String addDepartmentsFromFile(
			@RequestParam("file") MultipartFile file, HttpSession httpSession) {
		if (!file.isEmpty()) {
			File departments = new File(
					"D:\\FU\\Do an tot nghiep\\Data\\ServerData\\"
							+ file.getOriginalFilename());
			try {
				file.transferTo(departments);
				departmentService.addDepartmentFromFile(departments);
				httpSession.setAttribute("success",
						"Add Department From File Successful!");
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "redirect:/staff/departments";
	}

	@RequestMapping(value = "/staff/departments/deleteDepartment", method = RequestMethod.GET, params = { "departmentId" })
	public String deleteDepartment(@RequestParam int departmentId,
			HttpSession httpSession) {
		departmentService.deleteDepartment(departmentId);
		httpSession.setAttribute("success", "Delete Department Successful!");
		return "redirect:/staff/departments";
	}

	@ExceptionHandler(DepartmentExistedException.class)
	public String handleException(HttpSession httpSession, Exception e,
			HttpServletRequest request) {
		httpSession.setAttribute("error",
				"This Department's code existed! Please try again!");
		return "redirect:/staff/departments";
	}
}
