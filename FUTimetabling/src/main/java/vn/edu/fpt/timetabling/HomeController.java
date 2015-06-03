package vn.edu.fpt.timetabling;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.edu.fpt.timetabling.model.Staff;
import vn.edu.fpt.timetabling.service.StaffService;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Userinfoplus;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);
	private static final String CLIENT_ID = "938120756299-8u3vpmb12jptc4jn8h5bdqftlurbfll9.apps.googleusercontent.com";
	private static final String CLIENT_SECRET = "dj70o3tz4Qeu42qL0oDhV7Nm";
	private static final String REDIRECT_URI = "postmessage";
	private static final String FPT_DOMAIN = "fpt.edu.vn";

	private StaffService staffService;

	@Autowired(required = true)
	@Qualifier(value = "staffService")
	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String login(HttpSession session, @RequestBody String code)
			throws IOException {
		logger.info("code: " + code);
		HttpTransport netTransport = new NetHttpTransport();
		JacksonFactory jsonFactory = new JacksonFactory();
		GoogleTokenResponse token = new GoogleAuthorizationCodeTokenRequest(
				netTransport, jsonFactory, CLIENT_ID, CLIENT_SECRET, code,
				REDIRECT_URI).execute();
		System.out.println("Valid access token " + token.getAccessToken());
		GoogleCredential googleCredential = new GoogleCredential()
				.setAccessToken(token.getAccessToken());
		Oauth2 userInfoService = new Oauth2.Builder(netTransport, jsonFactory,
				googleCredential).setApplicationName("Oauth2").build();
		Userinfoplus userinfo = userInfoService.userinfo().get().execute();
		logger.info("id_token: " + token.getIdToken());
		logger.info("access_token: " + token.getAccessToken());
		String email = userinfo.getEmail();
		String hd = userinfo.getHd();
		if (hd != null && hd.equalsIgnoreCase(FPT_DOMAIN)) {
			// email fpt
			logger.info(email);
			Staff staff = staffService.getStaffByEmail(email);
			if (staff != null) {
				logger.info("staff");
				session.setAttribute("idToken", token.getIdToken());
				session.setAttribute("accessToken", token.getAccessToken());
				session.setAttribute("email", email);
				return "redirect:/staff";
			} else {
				return "";
			}
		} else {
			return "";
		}
	}

	@RequestMapping(value = "/fpt", method = RequestMethod.GET)
	public String getFPTPage() {
		logger.debug("Go to fpt page");

		// Do your work here. Whatever you like
		// i.e call a custom service to do your business
		// Prepare a model to be used by the JSP page

		// This will resolve to /WEB-INF/jsp/adminpage.jsp
		return "fpt";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String getAdminPage() {
		logger.debug("Received request to show admin page");

		// Do your work here. Whatever you like
		// i.e call a custom service to do your business
		// Prepare a model to be used by the JSP page

		// This will resolve to /WEB-INF/jsp/adminpage.jsp
		return "adminpage";
	}

}
