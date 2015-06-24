package vn.edu.fpt.timetabling;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.edu.fpt.timetabling.model.ProgramSemester;
import vn.edu.fpt.timetabling.service.ProgramSemesterService;
import vn.edu.fpt.timetabling.service.SemesterService;
import vn.edu.fpt.timetabling.service.SpecializedService;
import vn.edu.fpt.timetabling.utils.SessionUtils;

@Controller
public class ProgramSemesterController {

	private ProgramSemesterService programSemesterService;
	private SemesterService semesterService;
	private SpecializedService specializedService;

	@Autowired(required = true)
	@Qualifier(value = "programSemesterService")
	public void setProgramSemesterService(
			ProgramSemesterService programSemesterService) {
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

	@RequestMapping(value = "/programSemesters", method = RequestMethod.GET)
	public String listProgramSemester(HttpSession session, Model model) {
		if (!SessionUtils.isSessionValid(session)) {
			return "home";
		}
		model.addAttribute("programSemester", new ProgramSemester());
		model.addAttribute("programSemesters",
				programSemesterService.listProgramSemesters());
		model.addAttribute("semesters", semesterService.listSemesters());
		model.addAttribute("specializeds",
				specializedService.listSpecializeds());
		return "ProgramSemester";
	}
}
