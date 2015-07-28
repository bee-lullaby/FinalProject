package vn.edu.fpt.timetabling;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TeacherPageController extends GeneralController {

	@RequestMapping(value = "/teacherPage", method = RequestMethod.GET)
	public String teacherPage() {
		return "teacherPage";
	}
}
