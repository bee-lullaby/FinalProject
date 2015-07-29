package vn.edu.fpt.timetabling;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
	public void setTeacherSemesterService(TeacherSemesterService teacherSemesterService) {
		this.teacherSemesterService = teacherSemesterService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "courseSemesterService")
	public void setCourseSemesterService(CourseSemesterService courseSemesterService) {
		this.courseSemesterService = courseSemesterService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "teacherCourseSemesterService")
	public void setTeacherCourseSemesterService(TeacherCourseSemesterService teacherCourseSemesterService) {
		this.teacherCourseSemesterService = teacherCourseSemesterService;
	}
	
	@RequestMapping(value = "/staff/teachers", method = RequestMethod.GET)
	public String teacherInit(HttpSession httpSession, Model model) {
		int semesterId = semesterService.listSemesters(false, false, false, false).get(0).getSemesterId();
		return "redirect:/staff/teachers?semesterId=" +semesterId;
	}
	
	@RequestMapping(value = "/staff/teachers", method = RequestMethod.GET, params = {"semesterId"})
	public String teachers(@RequestParam int semesterId, HttpSession httpSession, Model model) {

		model.addAttribute("listSemesters",
				semesterService.listSemesters(false, false, false, false));
		model.addAttribute("listCourseSemesters", courseSemesterService.listCourseSemesters(false, false, false));
		model.addAttribute("listTeacherSemesters", teacherSemesterService.listTeacherSemestersForView(semesterId));
		checkError(httpSession, model);
		return "teachers";
	}
	
	@RequestMapping(value = "/staff/teachers/updateTeacher", method = RequestMethod.POST, 
			params={"teacherId", "teacherSemesterId", "name", "account", "email", "semesterId", "courses"} )
	public String updateTeacher(@RequestParam int teacherId, @RequestParam int teacherSemesterId, @RequestParam String name,
			@RequestParam String account, @RequestParam String email, @RequestParam int semesterId,
			@RequestParam String[] courses, HttpServletRequest request) {
		
		Teacher teacher = new Teacher();
		teacher.setName(name);
		teacher.setAccount(account);
		teacher.setEmail(email);
		if(teacherId == -1) {
			teacherService.addTeacher(teacher);
		} else {
			teacher.setTeacherId(teacherId);
			teacherService.updateTeacher(teacher);
		}
		
		TeacherSemester teacherSemester = new TeacherSemester();
		teacherSemester.setSemester(semesterService.getSemesterById(semesterId, false, false, false, false));
		teacherSemester.setTeacher(teacher);
		
		if(teacherSemesterId == -1) {
			teacherSemesterService.addTeacherSemester(teacherSemester);
		} else {
			teacherSemester.setTeacherSemesterId(teacherSemesterId);
			teacherSemesterService.updateTeacherSemester(teacherSemester);
		}
		
		
		if(teacherSemesterId != -1 && teacherId != -1) {
			List<TeacherCourseSemester> teacherCourseSemesters = teacherCourseSemesterService.listTeacherCourseSemestersByTeacher(teacherSemesterId);
			for(TeacherCourseSemester tcs : teacherCourseSemesters){
				teacherCourseSemesterService.deleteTeacherCourseSemester(tcs.getTeacherCourseSemesterId());
			}
		}
		
		for(String course : courses) {
			System.out.println(course);
			TeacherCourseSemester tcs = new TeacherCourseSemester();
			tcs.setCourseSemester(courseSemesterService.getCourseSemesterByCourseSemester(Integer.parseInt(course), semesterId, false, false, false));
			int getTeacherId = teacherService.getTeacherByAccount(account).getTeacherId();
			tcs.setTeacherSemester(teacherSemesterService.getTeacherSemesterByTeacherSemester(getTeacherId, semesterId, false, false));
			teacherCourseSemesterService.addTeacherCourseSemester(tcs);
		}
		
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@RequestMapping(value="/staff/teachers/addFromFile", method = RequestMethod.POST)
	public String addTeachersFromFile(@RequestParam("act") int act, @RequestParam("file") MultipartFile file, 
			@RequestParam("semesterId") int semesterId, HttpServletRequest request) {
		if (!file.isEmpty()) {
			File teachers = new File("D:\\FU\\Do an tot nghiep\\Data\\ServerData\\" + file.getOriginalFilename());
			try {
				file.transferTo(teachers);
				if(act == 0) {
					teacherSemesterService.addTeacherSemesterFromFile(teachers, semesterId);
				} else if (act == 1) {
					teacherCourseSemesterService.addTeacherCourseSemesterFromFile(teachers, semesterId);
				}
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}
	
	@RequestMapping("/staff/teacher/delete/{teacherId}")
	public String deleteTeacher(@PathVariable("teacherId") int teacherId, HttpServletRequest request) {
		teacherService.deleteTeacher(teacherId);
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}
}
