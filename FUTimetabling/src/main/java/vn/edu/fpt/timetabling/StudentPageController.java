package vn.edu.fpt.timetabling;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StudentPageController extends GeneralController {

	@RequestMapping(value = "/studentPage", method = RequestMethod.GET)
	public String studentPage() {
		return "studentPage";
	}
}
