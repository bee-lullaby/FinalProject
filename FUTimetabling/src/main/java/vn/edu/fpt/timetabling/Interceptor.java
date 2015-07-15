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
		HttpSession httpSession = request.getSession();
		if (SessionUtils.isSessionValid(httpSession) || request.getRequestURL().toString().equals(Const.URL)) {
			return true;
		} else {
			response.sendRedirect(Const.URL);
			return false;
		}
	}
}
