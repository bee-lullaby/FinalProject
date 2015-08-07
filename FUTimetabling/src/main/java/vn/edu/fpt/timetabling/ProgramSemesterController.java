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

import vn.edu.fpt.timetabling.model.Semester;
import vn.edu.fpt.timetabling.service.CourseSemesterService;
import vn.edu.fpt.timetabling.service.ProgramSemesterService;
import vn.edu.fpt.timetabling.service.SemesterService;
import vn.edu.fpt.timetabling.service.SpecializedService;

@Controller
public class ProgramSemesterController extends GeneralController {
	private ProgramSemesterService programSemesterService;
	private SemesterService semesterService;
	private SpecializedService specializedService;
	private CourseSemesterService courseSemesterService;

	@Autowired(required = true)
	@Qualifier(value = "programSemesterService")
	public void setProgramSemesterService(ProgramSemesterService programSemesterService) {
		this.programSemesterService = programSemesterService;
	}

	@Autowired(required = true)
	@Qualifier(value = "semesterService")
	public void setSemesterService(SemesterService semesterService) {
		this.semesterService = semesterService;
	}

	@Autowired(required = true)
	@Qualifier(value = "specializedService")
	public void setSpecializedService(SpecializedService specializedService) {
		this.specializedService = specializedService;
	}

	@Autowired(required = true)
	@Qualifier(value = "courseSemesterService")
	public void setCourseSemesterService(CourseSemesterService courseSemesterService) {
		this.courseSemesterService = courseSemesterService;
	}

	@RequestMapping(value = "/staff/programs", method = RequestMethod.GET)
	public String programSemestersInit(HttpSession httpSession, Model model) {
		List<Semester> semesters = semesterService.listSemesters(false, false, false, false);
		int semesterId = 0;
		if (!semesters.isEmpty())
			semesterId = semesters.get(0).getSemesterId();
		return "redirect:/staff/programs?semesterId=" + semesterId;
	}

	@RequestMapping(value = "/staff/programs", method = RequestMethod.GET, params = { "semesterId" })
	public String programSemesters(@RequestParam int semesterId, HttpSession httpSession, Model model) {
		model.addAttribute("listSemesters", semesterService.listSemesters(false, false, false, false));
		model.addAttribute("listProgramSemesters", programSemesterService.listProgramSemestersBySemester(semesterId));
		model.addAttribute("listSpecializeds", specializedService.listSpecializeds(false, false));
		model.addAttribute("listDetailSpecializeds", specializedService.listDetailSpecializeds(false, false));
		model.addAttribute("listCourseSemesters",
				courseSemesterService.listCourseSemestersBySemester(semesterId, false, false, false));
		checkError(httpSession, model);
		notifySuccess(httpSession, model);
		return "programs";
	}

	@RequestMapping(value = "/staff/programs/addFromFile", method = RequestMethod.POST)
	public String addDepartmentsFromFile(@RequestParam("semesterId") int semesterId,
			@RequestParam("file") MultipartFile file, HttpSession httpSession) {
		if (!file.isEmpty()) {
			File programSemesters = new File("programSemesters.xlxs");
			try {
				file.transferTo(programSemesters);
				programSemesterService.addProgramSemesterFromFile(semesterId, programSemesters);
				httpSession.setAttribute("success", "Add Programs From File Successful!");
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "redirect:/staff/programs";
	}

	@ExceptionHandler(Exception.class)
	public String handleException(HttpSession httpSession, Exception e) {
		httpSession.setAttribute("error", "Error, please try again.");
		return "redirect:/staff/programs";
	}
}
