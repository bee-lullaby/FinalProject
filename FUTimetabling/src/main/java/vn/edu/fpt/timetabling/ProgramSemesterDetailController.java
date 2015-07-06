package vn.edu.fpt.timetabling;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import vn.edu.fpt.timetabling.model.CourseSemester;
import vn.edu.fpt.timetabling.model.ProgramSemester;
import vn.edu.fpt.timetabling.model.ProgramSemesterDetail;
import vn.edu.fpt.timetabling.model.Semester;
import vn.edu.fpt.timetabling.service.CourseSemesterService;
import vn.edu.fpt.timetabling.service.ProgramSemesterDetailService;
import vn.edu.fpt.timetabling.service.ProgramSemesterService;
import vn.edu.fpt.timetabling.service.SemesterService;
import vn.edu.fpt.timetabling.utils.SessionUtils;

@Controller
public class ProgramSemesterDetailController {

	private ProgramSemesterDetailService programSemesterDetailService;
	private ProgramSemesterService programSemesterService;
	private CourseSemesterService courseSemesterService;
	private SemesterService semesterService;

	@Autowired(required = true)
	@Qualifier(value = "programSemesterDetailService")
	public void setProgramSemesterDetailService(ProgramSemesterDetailService programSemesterDetailService) {
		this.programSemesterDetailService = programSemesterDetailService;
	}

	@Autowired(required = true)
	@Qualifier(value = "programSemesterService")
	public void setProgramSemesterService(ProgramSemesterService programSemesterService) {
		this.programSemesterService = programSemesterService;
	}

	@Autowired(required = true)
	@Qualifier(value = "courseSemesterService")
	public void setCourseSemesterService(CourseSemesterService courseSemesterService) {
		this.courseSemesterService = courseSemesterService;
	}

	@Autowired(required = true)
	@Qualifier(value = "semesterService")
	public void setSemesterService(SemesterService semesterService) {
		this.semesterService = semesterService;
	}

	@RequestMapping(value = "/programSemesterDetails", method = RequestMethod.GET)
	public String listProgramSemesterDetail(HttpSession session, Model model,
			@RequestParam(value = "semesterId", required = false) Integer semesterId) {
		if (!SessionUtils.isSessionValid(session)) {
			return "home";
		}
		Semester semester;
		if (semesterId != null || session.getAttribute("semesterId") != null) {
			if (semesterId == null) {
				semesterId = (Integer) session.getAttribute("semesterId");
			}
			semester = semesterService.getSemesterById(semesterId, false, true, true, false);
			if (semester == null) {
				semester = semesterService.listSemesters(false, true, true, false).get(0);
			}
		} else {
			semester = semesterService.listSemesters(false, true, true, false).get(0);
		}
		model.addAttribute("programSemesterDetail", new ProgramSemesterDetail());
		model.addAttribute("programSemesterDetails", programSemesterDetailService.listProgramSemesterDetails());
		model.addAttribute("programSemesters", semester.getProgramSemesters());
		model.addAttribute("courseSemesters", semester.getCourseSemesters());
		model.addAttribute("semesters", semesterService.listSemesters(false, false, false, false));
		return "programSemesterDetail";
	}

	@RequestMapping(value = "/programSemesterDetail/add", method = RequestMethod.POST)
	public String addProgramSemesterDetail(HttpSession session,
			@RequestParam(value = "programSemesterDetailId", required = true) int programSemesterDetailId,
			@RequestParam(value = "programSemester", required = true) int programSemesterId,
			@RequestParam(value = "courseSemester", required = true) int courseSemesterId) {
		if (!SessionUtils.isSessionValid(session)) {
			return "home";
		}
		ProgramSemesterDetail programSemesterDetail = programSemesterDetailService
				.getProgramSemesterDetailById(programSemesterDetailId);
		if (programSemesterDetail == null) {
			programSemesterDetail = new ProgramSemesterDetail();
		}
		ProgramSemester programSemester = programSemesterService.getProgramSemesterById(programSemesterId);
		CourseSemester courseSemester = courseSemesterService.getCourseSemesterById(courseSemesterId, false, false, false);
		if (programSemester == null || courseSemester == null) {
			return "redirect:/programSemesterDetails";
		}
		programSemesterDetail.setProgramSemester(programSemester);
		programSemesterDetail.setCourseSemester(courseSemester);
		if (programSemesterDetail.getProgramSemesterDetailId() == 0) {
			// new program semester, add it
			programSemesterDetailService.addProgramSemesterDetail(programSemesterDetail);
		} else {
			// existing program semester, call update
			programSemesterDetailService.updateProgramSemesterDetail(programSemesterDetail);
		}
		return "redirect:/programSemesterDetails";
	}

	@RequestMapping("/programSemesterDetail/delete/{programSemesterDetailId}")
	public String deleteProgramSemesterDetail(HttpSession session,
			@PathVariable("programSemesterDetailId") int programSemesterDetailId) {
		if (!SessionUtils.isSessionValid(session)) {
			return "home";
		}
		programSemesterDetailService.deleteProgramSemesterDetail(programSemesterDetailId);
		return "redirect:/programSemesterDetails";
	}

	@RequestMapping("/programSemesterDetail/edit/{programSemesterDetailId}")
	public String editProgramSemester(HttpSession session,
			@PathVariable("programSemesterDetailId") int programSemesterDetailId, Model model) {
		if (!SessionUtils.isSessionValid(session)) {
			return "home";
		}
		ProgramSemesterDetail programSemesterDetail = programSemesterDetailService
				.getProgramSemesterDetailById(programSemesterDetailId);
		if (programSemesterDetail == null) {
			return "redirect:/programSemesterDetails";
		}
		Semester semester;
		if (session.getAttribute("semesterId") != null) {
			int semesterId = (Integer) session.getAttribute("semesterId");
			semester = semesterService.getSemesterById(semesterId, false, true, true, false);
			if (semester == null) {
				semester = semesterService.listSemesters(false, true, true, false).get(0);
			}
		} else {
			semester = semesterService.listSemesters(false, true, true, false).get(0);
		}
		model.addAttribute("programSemesterDetail", programSemesterDetail);
		model.addAttribute("programSemesterDetails", programSemesterDetailService.listProgramSemesterDetails());
		model.addAttribute("programSemesters", semester.getProgramSemesters());
		model.addAttribute("courseSemesters", semester.getCourseSemesters());
		model.addAttribute("semesters", semesterService.listSemesters(false, false, false, false));
		model.addAttribute("programSemesterId", programSemesterDetail.getProgramSemester().getProgramSemesterId());
		model.addAttribute("courseSemesterId", programSemesterDetail.getCourseSemester().getCourseSemesterId());
		return "programSemesterDetail";
	}
}
