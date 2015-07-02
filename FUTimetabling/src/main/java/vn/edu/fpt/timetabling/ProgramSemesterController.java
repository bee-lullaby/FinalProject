package vn.edu.fpt.timetabling;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import vn.edu.fpt.timetabling.model.ProgramSemester;
import vn.edu.fpt.timetabling.model.Semester;
import vn.edu.fpt.timetabling.model.Specialized;
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
		List<Specialized> detailSpecializeds = specializedService
				.listDetailSpecializeds();
		detailSpecializeds.add(0, new Specialized());
		model.addAttribute("detailSpecializeds", detailSpecializeds);
		return "programSemester";
	}

	@RequestMapping(value = "/programSemester/add", method = RequestMethod.POST)
	public String addProgramSemester(
			HttpSession session,
			@RequestParam(value = "programSemesterId", required = true) int programSemesterId,
			@RequestParam(value = "semester", required = true) int semesterId,
			@RequestParam(value = "currentSemester", required = true) int currentSemester,
			@RequestParam(value = "specialized", required = true) int specializedId,
			@RequestParam(value = "detailSpecialized", required = false) Integer detailSpecializedId) {
		if (!SessionUtils.isSessionValid(session)) {
			return "home";
		}
		ProgramSemester programSemester = programSemesterService
				.getProgramSemesterById(programSemesterId);
		if (programSemester == null) {
			programSemester = new ProgramSemester();
		}
		Semester semester = semesterService.getSemesterById(semesterId);
		Specialized specialized = specializedService
				.getSpecializedById(specializedId);
		if (semester == null || specialized == null) {
			return "redirect:/programSemesters";
		}
		if (detailSpecializedId != null) {
			Specialized detailSpecialized = specializedService
					.getSpecializedById(detailSpecializedId);
			if (detailSpecialized != null) {
				programSemester.setDetailSpecialized(detailSpecialized);
			}
		}
		programSemester.setSemester(semester);
		programSemester.setCurrentSemester(currentSemester);
		programSemester.setSpecialized(specialized);
		if (programSemester.getProgramSemesterId() == 0) {
			// new program semester, add it
			programSemesterService.addProgramSemester(programSemester);
		} else {
			// existing program semester, call update
			programSemesterService.updateProgramSemester(programSemester);
		}
		return "redirect:/programSemesters";
	}

	@RequestMapping("/programSemester/delete/{programSemesterId}")
	public String deleteProgramSemester(HttpSession session,
			@PathVariable("programSemesterId") int programSemesterId) {
		if (!SessionUtils.isSessionValid(session)) {
			return "home";
		}
		programSemesterService.deleteProgramSemester(programSemesterId);
		return "redirect:/programSemesters";
	}

	@RequestMapping("/programSemester/edit/{programSemesterId}")
	public String editProgramSemester(HttpSession session,
			@PathVariable("programSemesterId") int programSemesterId,
			Model model) {
		if (!SessionUtils.isSessionValid(session)) {
			return "home";
		}
		ProgramSemester programSemester = programSemesterService
				.getProgramSemesterById(programSemesterId);
		if (programSemester == null) {
			return "redirect:/programSemesters";
		}
		model.addAttribute("programSemester", programSemester);
		model.addAttribute("programSemesters",
				programSemesterService.listProgramSemesters());
		model.addAttribute("semesters", semesterService.listSemesters());
		model.addAttribute("specializeds",
				specializedService.listSpecializeds());
		List<Specialized> detailSpecializeds = specializedService
				.listDetailSpecializeds();
		detailSpecializeds.add(0, new Specialized());
		model.addAttribute("detailSpecializeds", detailSpecializeds);
		model.addAttribute("semesterId", programSemester.getSemester()
				.getSemesterId());
		model.addAttribute("specializedId", programSemester.getSpecialized()
				.getSpecializedId());
		model.addAttribute("detailSpecializedId", programSemester
				.getDetailSpecialized().getSpecializedId());
		return "programSemester";
	}
}
