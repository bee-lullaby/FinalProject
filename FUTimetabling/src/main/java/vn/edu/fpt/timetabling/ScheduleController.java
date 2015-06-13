package vn.edu.fpt.timetabling;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ScheduleController {
	
	@RequestMapping(value="/schedule", method = RequestMethod.GET)
	public void schedule(Model model) {
		model.addAttribute("course1", "1");
		model.addAttribute("course2", "2");
		model.addAttribute("course3", "3");
		model.addAttribute("course4", "4");
		model.addAttribute("course5", "5");
		return;
	}
	
}
