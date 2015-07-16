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

import vn.edu.fpt.timetabling.model.Department;
import vn.edu.fpt.timetabling.service.DepartmentService;

@Controller
public class DepartmentController extends GeneralController {
	private DepartmentService departmentService;

	@Autowired(required = true)
	@Qualifier(value = "departmentService")
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	@RequestMapping(value = "/staff/departments", method = RequestMethod.GET)
	public String listDepartment(HttpSession httpSession, Model model) {
		model.addAttribute("department", new Department());
		model.addAttribute("listDepartments", departmentService.listDepartments());
		checkError(httpSession, model);
		return "department";
	}

	// For add and update person both
	@RequestMapping(value = "/staff/department/add", method = RequestMethod.POST)
	public String addDepartment(@ModelAttribute("department") Department department) {
		if (department.getDepartmentId() == 0) {
			// new course, add it
			departmentService.addDepartment(department);
		} else {
			// existing course, call update
			departmentService.updateDepartment(department);
		}
		return "redirect:/staff/departments";
	}

	// For add and update person both
	@RequestMapping(value = "/staff/department/addFromFile", method = RequestMethod.POST)
	public String addDepartmentFromFile(@RequestParam("file") MultipartFile file) {
		if (!file.isEmpty()) {
			File departments = new File("D:\\FU\\Do an tot nghiep\\Data\\ServerData\\" + file.getOriginalFilename());
			try {
				file.transferTo(departments);
				departmentService.addDepartmentFromFile(departments);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "redirect:/staff/departments";
	}

	@RequestMapping("/staff/department/delete/{departmentId}")
	public String deleteDepartment(@PathVariable("departmentId") int departmentId) {
		departmentService.deleteDepartment(departmentId);
		return "redirect:/staff/departments";
	}

	@RequestMapping("/staff/department/edit/{departmentId}")
	public String editDepartment(@PathVariable("departmentId") int departmentId, Model model) {
		model.addAttribute("department", departmentService.getDepartmentById(departmentId));
		model.addAttribute("listDepartments", departmentService.listDepartments());
		return "department";
	}

	@ExceptionHandler(Exception.class)
	public String handleException(HttpSession httpSession, Exception e) {
		httpSession.setAttribute("error", "Error, please try again.");
		return "redirect:/staff/departments";
	}
}
