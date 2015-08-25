package vn.edu.fpt.timetabling;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import vn.edu.fpt.timetabling.exception.TeacherExistedException;
import vn.edu.fpt.timetabling.model.Semester;
import vn.edu.fpt.timetabling.model.Teacher;
import vn.edu.fpt.timetabling.model.TeacherCourseSemester;
import vn.edu.fpt.timetabling.model.TeacherSemester;
import vn.edu.fpt.timetabling.service.CourseSemesterService;
import vn.edu.fpt.timetabling.service.SemesterService;
import vn.edu.fpt.timetabling.service.TeacherCourseSemesterService;
import vn.edu.fpt.timetabling.service.TeacherSemesterService;
import vn.edu.fpt.timetabling.service.TeacherService;

@Controller
public class TeacherController extends GeneralController {
	private TeacherService teacherService;
	private SemesterService semesterService;
	private TeacherSemesterService teacherSemesterService;
	private CourseSemesterService courseSemesterService;
	private TeacherCourseSemesterService teacherCourseSemesterService;
	@Autowired(required = true)
	@Qualifier(value = "teacherService")
	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	@Autowired(required = true)
	@Qualifier(value = "semesterService")
	public void setSemesterService(SemesterService semesterService) {
		this.semesterService = semesterService;
	}

	@Autowired(required = true)
	@Qualifier(value = "teacherSemesterService")
	public void setTeacherSemesterService(
			TeacherSemesterService teacherSemesterService) {
		this.teacherSemesterService = teacherSemesterService;
	}

	@Autowired(required = true)
	@Qualifier(value = "courseSemesterService")
	public void setCourseSemesterService(
			CourseSemesterService courseSemesterService) {
		this.courseSemesterService = courseSemesterService;
	}

	@Autowired(required = true)
	@Qualifier(value = "teacherCourseSemesterService")
	public void setTeacherCourseSemesterService(
			TeacherCourseSemesterService teacherCourseSemesterService) {
		this.teacherCourseSemesterService = teacherCourseSemesterService;
	}
	
	@RequestMapping(value = "/staff/teachers", method = RequestMethod.GET)
	public String teacherInit(HttpSession httpSession, Model model) {
		List<Semester> semesters = semesterService
				.listSemesters(false, false, false, false);
		int semesterId = 0;
		if(!semesters.isEmpty()) 
			semesterId = semesters.get(0).getSemesterId();
		return "redirect:/staff/teachers?semesterId=" + semesterId;
	}

	@RequestMapping(value = "/staff/teachers", method = RequestMethod.GET, params = { "semesterId" })
	public String teachers(@RequestParam int semesterId,
			HttpSession httpSession, Model model) {

		model.addAttribute("listSemesters",
				semesterService.listSemesters(false, false, false, false));
		model.addAttribute("listCourseSemesters",
				courseSemesterService.listCourseSemestersBySemester(semesterId, false, false, false));
		model.addAttribute("listTeacherSemesters",
				teacherSemesterService.listTeacherSemestersForView(semesterId));
		checkError(httpSession, model);
		notifySuccess(httpSession, model);
		return "teachers";
	}

	@RequestMapping(value = "/staff/teachers/updateTeacher", method = RequestMethod.POST, params = {
			"teacherId", "teacherSemesterId", "name", "account", "email",
			"semesterId" })
	public String updateTeacher(
			@RequestParam int teacherId,
			@RequestParam int teacherSemesterId,
			@RequestParam String name,
			@RequestParam String account,
			@RequestParam String email,
			@RequestParam int semesterId,
			@RequestParam(value = "courses", required = false) String[] courses,
			HttpSession httpSession) throws TeacherExistedException {

		Teacher teacher = teacherService.getTeacherByAccount(account);
		TeacherSemester teacherSemester = teacherSemesterService.getTeacherSemesterByAccount(semesterId, account, false, false);
		if (teacherSemester != null) {
			throw new TeacherExistedException();
		} else {
			if(teacher == null) {
				teacher = new Teacher();
			}
		}

		teacher.setName(name);
		teacher.setAccount(account);
		teacher.setEmail(email);
		if (teacher.getTeacherId() == -1) {
			teacherService.addTeacher(teacher);
			httpSession.setAttribute("success", "Add Teacher Successful!");
		} else {
			teacher.setTeacherId(teacherId);
			teacherService.updateTeacher(teacher);
			httpSession.setAttribute("success", "Edit Teacher Successful!");
		}

		teacherSemester = new TeacherSemester();
		teacherSemester.setSemester(semesterService.getSemesterById(semesterId,
				false, false, false, false));
		teacherSemester.setTeacher(teacher);

		if (teacherSemesterId == -1) {
			teacherSemesterService.addTeacherSemester(teacherSemester);
		} else {
			teacherSemester.setTeacherSemesterId(teacherSemesterId);
			teacherSemesterService.updateTeacherSemester(teacherSemester);
		}

		if (teacherSemesterId != -1 && teacherId != -1) {
			List<TeacherCourseSemester> teacherCourseSemesters = teacherCourseSemesterService
					.listTeacherCourseSemestersByTeacher(teacherSemesterId);
			for (TeacherCourseSemester tcs : teacherCourseSemesters) {
				teacherCourseSemesterService.deleteTeacherCourseSemester(tcs
						.getTeacherCourseSemesterId());
			}
		}
		if (courses != null) {
			for (String course : courses) {
				TeacherCourseSemester tcs = new TeacherCourseSemester();
				tcs.setCourseSemester(courseSemesterService
						.getCourseSemesterByCourseSemester(
								Integer.parseInt(course), semesterId, false,
								false, false));
				int getTeacherId = teacherService.getTeacherByAccount(account)
						.getTeacherId();
				tcs.setTeacherSemester(teacherSemesterService
						.getTeacherSemesterByTeacherSemester(getTeacherId,
								semesterId, false, false));
				teacherCourseSemesterService.addTeacherCourseSemester(tcs);
			}
		}
		return "redirect:/staff/teachers";
	}

	@RequestMapping(value = "/staff/teachers/addFromFile", method = RequestMethod.POST)
	public String addTeachersFromFile(@RequestParam("act") int act,
			@RequestParam("file") MultipartFile file,
			@RequestParam("semesterId") int semesterId, HttpSession httpSession) {
		if (!file.isEmpty()) {
			File teachers = new File("teachers.xlxs");
			try {
				file.transferTo(teachers);
				if (act == 0) {
					teacherSemesterService.addTeacherSemesterFromFile(teachers,
							semesterId);
					httpSession.setAttribute("success",
							"Add Teachers from File successful!");
				} else if (act == 1) {
					List<String> check = teacherCourseSemesterService
							.addTeacherCourseSemesterFromFile(teachers,
									semesterId);
					if(check.isEmpty()) {
						httpSession.setAttribute("success",
								"Add Teachers Courses from File successful!");
					} else {
						String text = "Course " +check.get(0);
						for(int i = 1; i < check.size(); i++) {
							text += ", " +check.get(i);
						}
						if(check.size() == 1) text += " is ";
						else text += " are ";
						text += "not existed! Please insert course first then try again!";
						httpSession.setAttribute("error", text);
						httpSession.setAttribute("success", "Others teachers were added successful!");
					}
				}
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "redirect:/staff/teachers";
	}

	@RequestMapping(value = "/staff/teachers/deleteTeacher", method = RequestMethod.GET, params = { "teacherId" })
	public String deleteTeacher(@RequestParam int teacherId,
			HttpSession httpSession) {
		teacherService.deleteTeacher(teacherId);
		httpSession.setAttribute("success",
				"Delete Teachers Semester successful!");
		return "redirect:/staff/teachers";
	}
	

	@ExceptionHandler(TeacherExistedException.class)
	public String handleException(HttpSession httpSession, Exception e) {
		httpSession.setAttribute("error",
				"This Teacher's account existed! Please try again!");
		return "redirect:/staff/teachers";
	}
}
