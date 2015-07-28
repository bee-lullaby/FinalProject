package vn.edu.fpt.timetabling.utils;

import javax.servlet.http.HttpSession;

public class SessionUtils {
	public static boolean isSessionValid(HttpSession session) {
		if (session == null) {
			return false;
		}
		Object idToken = session.getAttribute("idToken");
		Object accessToken = session.getAttribute("accessToken");
		Object email = session.getAttribute("email");
		Object roll = session.getAttribute("roll");
		if (idToken == null || accessToken == null || email == null || roll == null) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean isStaff(HttpSession session) {
		if (session == null) {
			return false;
		}
		if (session.getAttribute("roll") != null && session.getAttribute("roll").toString().equals("staff")) {
			return true;
		}
		return false;
	}

	public static boolean isStudent(HttpSession session) {
		if (session == null) {
			return false;
		}
		if (session.getAttribute("roll") != null && session.getAttribute("roll").toString().equals("student")) {
			return true;
		}
		return false;
	}

	public static boolean isTeacher(HttpSession session) {
		if (session == null) {
			return false;
		}
		if (session.getAttribute("roll") != null && session.getAttribute("roll").toString().equals("teacher")) {
			return true;
		}
		return false;
	}
}
