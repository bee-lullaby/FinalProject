package vn.edu.fpt.timetabling;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StaffController extends GeneralController {

	@RequestMapping(value = "/staff", method = RequestMethod.GET)
	public String staff() {
		return "staff";
	}
}
