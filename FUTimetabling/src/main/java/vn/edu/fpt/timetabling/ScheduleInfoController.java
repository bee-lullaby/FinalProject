package vn.edu.fpt.timetabling;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ScheduleInfoController {

	
	@RequestMapping(value="staff/scheduleInfo", method = RequestMethod.GET)
	public String scheduleInfo(HttpSession httpSession, Model model) {
		return "scheduleInfo";
	}
	
}
