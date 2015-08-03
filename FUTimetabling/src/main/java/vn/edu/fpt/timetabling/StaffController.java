package vn.edu.fpt.timetabling;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import vn.edu.fpt.timetabling.exception.StaffExistedException;
import vn.edu.fpt.timetabling.model.Staff;
import vn.edu.fpt.timetabling.service.StaffService;

@Controller
public class StaffController extends GeneralController {

	@Autowired
	private StaffService staffService;

	@RequestMapping(value = "/staff", method = RequestMethod.GET)
	public String staff() {
		return "staff";
	}

	@RequestMapping(value = "/staff/staffManagement", method = RequestMethod.GET)
	public String staffManagement(HttpSession httpSession, Model model) {
		model.addAttribute("listStaffs", staffService.listStaff());
		checkError(httpSession, model);
		notifySuccess(httpSession, model);
		return "staffManagement";
	}

	@RequestMapping(value = "/staff/staffManagement/updateStaff", method = RequestMethod.POST, params = {
			"staffId", "account", "name", "email" })
	public String updateStaff(@RequestParam int staffId,
			@RequestParam String account, @RequestParam String name,
			@RequestParam String email, HttpSession httpSession)
			throws StaffExistedException {
		Staff staff = staffService.getStaffByAccount(account);

		if ((staffId == -1 && staff != null)  
				|| (staff != null && staffId != -1 && staffId != staff.getStaffId())) {
			throw new StaffExistedException();
		}
		
		if (staffId == -1) {
			staff = new Staff();
		} else {
			staff = staffService.getStaffById(staffId);
		}
		
		staff.setAccount(account);
		staff.setName(name);
		staff.setEmail(email);
		staff.setAccountType("staff");

		if (staffId == -1) {
			staffService.addStaff(staff);
			httpSession.setAttribute("success", "Add Staff successful!");
		} else {
			staffService.updateStaff(staff);
			httpSession.setAttribute("success", "Edit Staff successful!");
		}
		return "redirect:/staff/staffManagement";
	}

	@RequestMapping(value = "/staff/staffManagement/deleteStaff", method = RequestMethod.GET, params = { "staffId" })
	public String deleteStaff(@RequestParam int staffId,
			HttpServletRequest request, HttpSession httpSession) {
		staffService.deleteStaff(staffId);
		httpSession.setAttribute("success", "Delete Staff Successful!");
		return "redirect:/staff/staffManagement";
	}
	
	@ExceptionHandler(StaffExistedException.class)
	public String handleException(HttpSession httpSession, Exception e) {
		httpSession.setAttribute("error",
				"This Staff existed! Please try again!");
		return "redirect:/staff/staffManagement";
	}
}
