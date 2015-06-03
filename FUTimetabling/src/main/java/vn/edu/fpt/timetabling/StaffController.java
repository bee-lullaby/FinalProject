package vn.edu.fpt.timetabling;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StaffController {

	private static final Logger logger = LoggerFactory
			.getLogger(StaffController.class);

	@RequestMapping(value = "/staff", method = RequestMethod.GET)
	public String home(HttpSession session, Locale locale, Model model) {
		Object idToken = session.getAttribute("idToken");
		Object accessToken = session.getAttribute("accessToken");
		Object email = session.getAttribute("email");
		if (idToken == null || accessToken == null || email == null) {
			return "home";
		}
		logger.info("Staff page");
		model.addAttribute("idToken", session.getAttribute("idToken"));
		model.addAttribute("accessToken", session.getAttribute("accessToken"));
		model.addAttribute("email", session.getAttribute("email"));
		return "staff";
	}
}
