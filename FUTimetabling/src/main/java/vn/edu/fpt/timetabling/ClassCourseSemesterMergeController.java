package vn.edu.fpt.timetabling;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import vn.edu.fpt.timetabling.model.ClassCourseSemester;
import vn.edu.fpt.timetabling.model.ClassCourseSemesterMerge;
import vn.edu.fpt.timetabling.model.ClassSemester;
import vn.edu.fpt.timetabling.model.CourseSemester;
import vn.edu.fpt.timetabling.model.Semester;
import vn.edu.fpt.timetabling.service.ClassCourseSemesterMergeService;
import vn.edu.fpt.timetabling.service.ClassCourseSemesterService;
import vn.edu.fpt.timetabling.service.CourseSemesterService;
import vn.edu.fpt.timetabling.service.SemesterService;

@Controller
public class ClassCourseSemesterMergeController extends GeneralController {
	private ClassCourseSemesterMergeService classCourseSemesterMergeService;
	private SemesterService semesterService;
	private ClassCourseSemesterService classCourseSemesterService;
	private CourseSemesterService courseSemesterService;

	@Autowired(required = true)
	@Qualifier(value = "classCourseSemesterMergeService")
	public void setClassCourseSemesterMergeService(ClassCourseSemesterMergeService classCourseSemesterMergeService) {
		this.classCourseSemesterMergeService = classCourseSemesterMergeService;
	}

	@Autowired(required = true)
	@Qualifier(value = "semesterService")
	public void setSemesterService(SemesterService semesterService) {
		this.semesterService = semesterService;
	}

	@Autowired(required = true)
	@Qualifier(value = "classCourseSemesterService")
	public void setClassCourseSemesterService(ClassCourseSemesterService classCourseSemesterService) {
		this.classCourseSemesterService = classCourseSemesterService;
	}

	@Autowired(required = true)
	@Qualifier(value = "courseSemesterService")
	public void setCourseSemesterService(CourseSemesterService courseSemesterService) {
		this.courseSemesterService = courseSemesterService;
	}

	private void processClassCourseSemesters(HttpSession session, Model model, Semester semester,
			Integer courseSemesterId) {
		Set<CourseSemester> courseSemesters = semester.getCourseSemesters();
		if (!courseSemesters.isEmpty()) {
			CourseSemester courseSemester = null;
			if (courseSemesterId != null || session.getAttribute("courseSemester") != null) {
				if (courseSemesterId == null) {
					courseSemester = (CourseSemester) session.getAttribute("courseSemester");
					boolean validCourseSemester = false;
					for (CourseSemester courseSemesterTemp : courseSemesters) {
						if (courseSemesterTemp.getCourseSemesterId() == courseSemester.getCourseSemesterId()) {
							validCourseSemester = true;
							break;
						}
					}
					if (!validCourseSemester) {
						courseSemester = courseSemesters.iterator().next();
					}
				} else {
					for (CourseSemester courseSemesterTemp : courseSemesters) {
						if (courseSemesterTemp.getCourseSemesterId() == courseSemesterId) {
							courseSemester = courseSemesterTemp;
						}
					}
				}
				if (courseSemester == null) {
					courseSemester = courseSemesters.iterator().next();
				}
			} else {
				courseSemester = courseSemesters.iterator().next();
			}
			courseSemesterId = courseSemester.getCourseSemesterId();
			courseSemester = courseSemesterService.getCourseSemesterById(courseSemesterId, false, false, false);
			session.setAttribute("courseSemester", courseSemester);
			model.addAttribute("courseSemester", courseSemester);
			model.addAttribute("courseSemesterId", courseSemesterId);
			model.addAttribute("classCourseSemesters",
					classCourseSemesterService.listClassCourseSemesterByCourse(courseSemesterId, false, false));
		}
		model.addAttribute("courseSemesters", courseSemesters);
	}

	@RequestMapping(value = "/staff/classCourseSemesterMerges", method = RequestMethod.GET)
	public String listClassCourseSemesterMerge(HttpSession session, Model model,
			@RequestParam(value = "semesterId", required = false) Integer semesterId,
			@RequestParam(value = "courseSemesterId", required = false) Integer courseSemesterId) {
		List<Semester> semesters = semesterService.listSemesters(false, true, false, false);
		Semester semester;
		if (semesterId != null || session.getAttribute("semester") != null) {
			if (semesterId == null) {
				semester = (Semester) session.getAttribute("semester");
			} else {
				semester = semesterService.getSemesterById(semesterId, false, true, false, false);
			}
			if (semester == null) {
				semester = semesters.get(0);
			}
		} else {
			semester = semesters.get(0);
		}
		semesterId = semester.getSemesterId();
		semester = semesterService.getSemesterById(semesterId, false, true, false, false);
		model.addAttribute("classCourseSemesterMerge", new ClassCourseSemesterMerge());
		model.addAttribute("classCourseSemesterMerges",
				classCourseSemesterMergeService.listClassCourseSemesterMerges(semesterId));
		model.addAttribute("courseSemesters", semester.getCourseSemesters());
		processClassCourseSemesters(session, model, semester, courseSemesterId);
		model.addAttribute("semesterId", semesterId);
		model.addAttribute("semesters", semesters);
		session.setAttribute("semester", semester);
		checkError(session, model);
		return "classCourseSemesterMerge";
	}

	@RequestMapping(value = "/staff/classCourseSemesterMerge/add", method = RequestMethod.POST)
	public String addClassCourseSemesterMerge(
			@RequestParam(value = "classCourseSemesterMergeId") int classCourseSemesterMergeId,
			@RequestParam(value = "classCourseSemester1") int classCourseSemester1Id,
			@RequestParam(value = "classCourseSemester2") int classCourseSemester2Id) {
		ClassCourseSemester classCourseSemester1 = classCourseSemesterService
				.getClassCourseSemesterById(classCourseSemester1Id, false, false);
		ClassCourseSemester classCourseSemester2 = classCourseSemesterService
				.getClassCourseSemesterById(classCourseSemester2Id, false, false);
		if (classCourseSemester1 == null || classCourseSemester2 == null) {
			return "redirect:/staff/classCourseSemesterMerges";
		}
		ClassSemester classSemester1 = classCourseSemester1.getClassSemester();
		ClassSemester classSemester2 = classCourseSemester2.getClassSemester();
		if (classSemester1.getSemester().compareTo(classSemester2.getSemester()) != 0
				|| classSemester1.getClassFPT().getCode().equals(classSemester2.getClassFPT().getCode())
				|| !classCourseSemester1.getCourseSemester().getCourse().getCode()
						.equals(classCourseSemester2.getCourseSemester().getCourse().getCode())) {
			return "redirect:/staff/classCourseSemesterMerges";
		}
		ClassCourseSemesterMerge classCourseSemesterMerge = classCourseSemesterMergeService
				.getClassCourseSemesterMergeById(classCourseSemesterMergeId);
		if (classCourseSemesterMerge == null) {
			classCourseSemesterMerge = new ClassCourseSemesterMerge();
		}
		classCourseSemesterMerge.setClassCourseSemester1(classCourseSemester1);
		classCourseSemesterMerge.setClassCourseSemester2(classCourseSemester2);
		if (classCourseSemesterMerge.getClassCourseSemesterMergeId() == 0) {
			classCourseSemesterMergeService.addClassCourseSemesterMerge(classCourseSemesterMerge);
		} else {
			classCourseSemesterMergeService.updateClassCourseSemesterMerge(classCourseSemesterMerge);
		}
		return "redirect:/staff/classCourseSemesterMerges";
	}

	@RequestMapping("/staff/classCourseSemesterMerge/delete/{classCourseSemesterMergeId}")
	public String deleteCourseSemesterMerge(
			@PathVariable("classCourseSemesterMergeId") int classCourseSemesterMergeId) {
		classCourseSemesterMergeService.deleteClassCourseSemesterMerge(classCourseSemesterMergeId);
		return "redirect:/staff/classCourseSemesterMerges";
	}

	@ExceptionHandler(Exception.class)
	public String handleException(HttpSession httpSession, Exception e) {
		httpSession.setAttribute("error", "Error, please try again.");
		return "redirect:/staff/classCourseSemesterMerges";
	}
}
