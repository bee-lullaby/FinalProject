package vn.edu.fpt.timetabling;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

public class GeneralController {
	protected void checkError(HttpSession httpSession, Model model) {
		if (httpSession.getAttribute("error") != null) {
			model.addAttribute("error", httpSession.getAttribute("error"));
			httpSession.removeAttribute("error");
		}
	}
	
	protected void notifySuccess(HttpSession httpSession, Model model) {
		if (httpSession.getAttribute("success") != null) {
			model.addAttribute("success", httpSession.getAttribute("success"));
			httpSession.removeAttribute("success");
		}
	}
}
