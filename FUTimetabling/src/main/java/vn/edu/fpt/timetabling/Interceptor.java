package vn.edu.fpt.timetabling;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import vn.edu.fpt.timetabling.utils.Const;
import vn.edu.fpt.timetabling.utils.SessionUtils;

public class Interceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
//		return true;
		HttpSession httpSession = request.getSession();
		String url = request.getRequestURL().toString();
		if (url.equals(Const.URL) || url.contains("logout") || url.equals("http://localhost:8080/Timetabling/back")) {
			return true;
		} else if (SessionUtils.isSessionValid(httpSession)) {
			if (url.contains("/staff") && SessionUtils.isStaff(httpSession)) {
				return true;
			} else if (url.contains("/studentPage") && SessionUtils.isStudent(httpSession)) {
				return true;
			} else if (url.contains("/teacherPage") && SessionUtils.isTeacher(httpSession)) {
				return true;
			} else {
				response.sendRedirect(Const.URL);
				return false;
			}
		} else {
			response.sendRedirect(Const.URL);
			return false;
		}
	}
}
