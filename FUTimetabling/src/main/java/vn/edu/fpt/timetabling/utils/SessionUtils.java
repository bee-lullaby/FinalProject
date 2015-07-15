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
		if (idToken == null || accessToken == null || email == null) {
			return false;
		} else {
			return true;
		}
	}
}
