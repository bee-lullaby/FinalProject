package vn.edu.fpt.timetabling;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Userinfoplus;

import vn.edu.fpt.timetabling.model.Staff;
import vn.edu.fpt.timetabling.model.Student;
import vn.edu.fpt.timetabling.model.Teacher;
import vn.edu.fpt.timetabling.service.StaffService;
import vn.edu.fpt.timetabling.service.StudentService;
import vn.edu.fpt.timetabling.service.TeacherService;
import vn.edu.fpt.timetabling.utils.Const;
import vn.edu.fpt.timetabling.utils.SessionUtils;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	private static final Logger logger = Logger.getLogger(HomeController.class);
	private StaffService staffService;
	private StudentService studentService;
	private TeacherService teacherService;

	@Autowired(required = true)
	@Qualifier(value = "staffService")
	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}

	@Autowired(required = true)
	@Qualifier(value = "studentService")
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	@Autowired(required = true)
	@Qualifier(value = "teacherService")
	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpSession session) {
		if (SessionUtils.isStaff(session)) {
			return "redirect:/staff/";
		} else if (SessionUtils.isStudent(session)) {
			return "redirect:/studentPage/";
		} else if (SessionUtils.isTeacher(session)) {
			return "redirect:/teacherPage/";
		}
		logger.info("Welcome home! The client locale is " + locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate);
		return "home";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("idToken");
		session.removeAttribute("accessToken");
		session.removeAttribute("id");
		session.removeAttribute("email");
		session.removeAttribute("name");
		session.removeAttribute("account");
		session.removeAttribute("accountType");
		session.removeAttribute("roll");
		return "home";
	}

	@RequestMapping(value = "/back", method = RequestMethod.GET)
	public String back(HttpSession session) {
		if (SessionUtils.isStaff(session)) {
			return "redirect:/staff/";
		} else if (SessionUtils.isStudent(session)) {
			return "redirect:/studentPage/";
		} else if (SessionUtils.isTeacher(session)) {
			return "redirect:/teacherPage/";
		}
		return "home";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseBody
	public String login(HttpSession session, @RequestBody String code) throws IOException {
		HttpTransport netTransport = new NetHttpTransport();
		JacksonFactory jsonFactory = new JacksonFactory();
		GoogleTokenResponse token = new GoogleAuthorizationCodeTokenRequest(netTransport, jsonFactory, Const.CLIENT_ID,
				Const.CLIENT_SECRET, code, Const.REDIRECT_URI).execute();
		System.out.println("Valid access token " + token.getAccessToken());
		GoogleCredential googleCredential = new GoogleCredential().setAccessToken(token.getAccessToken());
		Oauth2 userInfoService = new Oauth2.Builder(netTransport, jsonFactory, googleCredential)
				.setApplicationName("Oauth2").build();
		Userinfoplus userinfo = userInfoService.userinfo().get().execute();
		String email = userinfo.getEmail();
		String hd = userinfo.getHd();
		if (hd != null && hd.equalsIgnoreCase(Const.FPT_DOMAIN)) {
			// email fpt
			Staff staff = staffService.getStaffByEmail(email);
			Student student = studentService.getStudentByEmail(email);
			Teacher teacher = teacherService.getTeacherByEmail(email);
			if (staff != null) {
				session.setAttribute("idToken", token.getIdToken());
				session.setAttribute("accessToken", token.getAccessToken());
				session.setAttribute("id", staff.getStaffId());
				session.setAttribute("email", email);
				session.setAttribute("name", staff.getName());
				session.setAttribute("account", staff.getAccount());
				session.setAttribute("accountType", staff.getAccountType());
				session.setAttribute("roll", "staff");
				return "staff";
			} else if (teacher != null) {
				session.setAttribute("idToken", token.getIdToken());
				session.setAttribute("accessToken", token.getAccessToken());
				session.setAttribute("id", teacher.getTeacherId());
				session.setAttribute("email", email);
				session.setAttribute("name", teacher.getName());
				session.setAttribute("account", teacher.getAccount());
				session.setAttribute("accountType", teacher.getAccountType());
				session.setAttribute("roll", "teacher");
				return "teacherPage";
			} else if (student != null) {
				session.setAttribute("idToken", token.getIdToken());
				session.setAttribute("accessToken", token.getAccessToken());
				session.setAttribute("id", student.getStudentId());
				session.setAttribute("email", email);
				session.setAttribute("name", student.getName());
				session.setAttribute("account", student.getAccount());
				session.setAttribute("accountType", "student");
				session.setAttribute("roll", "student");
				return "studentPage";
			} else {
				return "";
			}
		} else {
			return "";
		}
	}
}
