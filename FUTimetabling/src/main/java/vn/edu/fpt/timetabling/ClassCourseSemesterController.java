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

import vn.edu.fpt.timetabling.model.ClassCourseSemester;
import vn.edu.fpt.timetabling.model.ClassSemester;
import vn.edu.fpt.timetabling.model.CourseSemester;
import vn.edu.fpt.timetabling.model.Semester;
import vn.edu.fpt.timetabling.service.ClassCourseSemesterService;
import vn.edu.fpt.timetabling.service.ClassSemesterService;
import vn.edu.fpt.timetabling.service.CourseSemesterService;
import vn.edu.fpt.timetabling.service.SemesterService;
import vn.edu.fpt.timetabling.utils.SessionUtils;

@Controller
public class ClassCourseSemesterController {
	private ClassCourseSemesterService classCourseSemesterService;
	private SemesterService semesterService;
	private ClassSemesterService classSemesterService;
	private CourseSemesterService courseSemesterService;

	@Autowired(required = true)
	@Qualifier(value = "classCourseSemesterService")
	public void setClassCourseSemesterService(ClassCourseSemesterService classCourseSemesterService) {
		this.classCourseSemesterService = classCourseSemesterService;
	}

	@Autowired(required = true)
	@Qualifier(value = "semesterService")
	public void setSemesterService(SemesterService semesterService) {
		this.semesterService = semesterService;
	}

	@Autowired(required = true)
	@Qualifier(value = "classSemesterService")
	public void setClassSemesterService(ClassSemesterService classSemesterService) {
		this.classSemesterService = classSemesterService;
	}

	@Autowired(required = true)
	@Qualifier(value = "courseSemesterService")
	public void setCourseSemesterService(CourseSemesterService courseSemesterService) {
		this.courseSemesterService = courseSemesterService;
	}

	@RequestMapping(value = "/classCourseSemesters", method = RequestMethod.GET)
	public String listClassCourseSemester(HttpSession session, Model model,
			@RequestParam(value = "semesterId", required = false) Integer semesterId) {
		if (!SessionUtils.isSessionValid(session)) {
			return "home";
		}
		List<Semester> semesters = semesterService.listSemesters(true, true, false, false);
		Semester semester;
		if (semesterId != null || session.getAttribute("semester") != null) {
			if (semesterId == null) {
				semester = (Semester) session.getAttribute("semester");
			} else {
				semester = semesterService.getSemesterById(semesterId, true, true, false, false);
			}
			if (semester == null) {
				semester = semesters.get(0);
			}
		} else {
			semester = semesters.get(0);
		}
		semesterId = semester.getSemesterId();
		model.addAttribute("classCourseSemester", new ClassCourseSemester());
		model.addAttribute("classCourseSemesters",
				classCourseSemesterService.listClassCourseSemesterBySemester(semesterId));
		model.addAttribute("classSemesters", semester.getClassSemesters());
		model.addAttribute("courseSemesters", semester.getCourseSemesters());
		model.addAttribute("semesterId", semesterId);
		model.addAttribute("semesters", semesters);
		session.setAttribute("semester", semester);
		return "classCourse";
	}

	@RequestMapping(value = "/classCourseSemester/add", method = RequestMethod.POST)
	public String addClassCourseSemester(HttpSession session,
			@RequestParam(value = "classCourseSemesterId") int classCourseSemesterId,
			@RequestParam(value = "classSemester") int classSemesterId,
			@RequestParam(value = "courseSemester") int courseSemesterId,
			@RequestParam(value = "blockCondition", required = false) Integer blockCondition,
			@RequestParam(value = "semesterLong", required = false) Boolean semesterLong) {
		if (!SessionUtils.isSessionValid(session)) {
			return "home";
		}
		// change
		ClassSemester classSemester = classSemesterService.getClassSemesterById(classSemesterId, false);
		CourseSemester courseSemester = courseSemesterService.getCourseSemesterById(courseSemesterId, false, false,
				false);
		if (classSemester == null || courseSemester == null
				|| classSemester.getSemester().compareTo(courseSemester.getSemester()) != 0) {
			return "redirect:/classCourseSemesters";
		}
		ClassCourseSemester classCourseSemester = classCourseSemesterService
				.getClassCourseSemesterById(classCourseSemesterId);
		if (classCourseSemester == null) {
			classCourseSemester = new ClassCourseSemester();
		}
		classCourseSemester.setClassSemester(classSemester);
		classCourseSemester.setCourseSemester(courseSemester);
		if (semesterLong != null) {
			classCourseSemester.setSemesterLong(semesterLong);
			classCourseSemester.setBlockCondition(0);
		} else if (blockCondition != null) {
			classCourseSemester.setBlockCondition(blockCondition);
		}
		if (classCourseSemester.getClassCourseSemesterId() == 0) {
			// new class semester, add it
			classCourseSemesterService.addClassCourseSemester(classCourseSemester);
		} else {
			// existing class semester, call update
			classCourseSemesterService.updateClassCourseSemester(classCourseSemester);
		}
		return "redirect:/classCourseSemesters";
	}

	@RequestMapping("/classCourseSemester/delete/{classCourseSemesterId}")
	public String deleteCourseSemester(HttpSession session,
			@PathVariable("classCourseSemesterId") int classCourseSemesterId) {
		if (!SessionUtils.isSessionValid(session)) {
			return "home";
		}
		classCourseSemesterService.deleteClassCourseSemester(classCourseSemesterId);
		return "redirect:/classCourseSemesters";
	}

	@RequestMapping("/classCourseSemester/edit/{classCourseSemesterId}")
	public String editCourseSemester(HttpSession session,
			@PathVariable("classCourseSemesterId") int classCourseSemesterId, Model model) {
		if (!SessionUtils.isSessionValid(session)) {
			return "home";
		}
		ClassCourseSemester classCourseSemester = classCourseSemesterService
				.getClassCourseSemesterById(classCourseSemesterId);
		if (classCourseSemester == null) {
			return "redirect:/classCourseSemesters";
		}
		ClassSemester classSemester = classCourseSemester.getClassSemester();
		CourseSemester courseSemester = classCourseSemester.getCourseSemester();
		List<Semester> semesters = semesterService.listSemesters(true, true, false, false);
		Semester semester;
		int semesterId;
		if (session.getAttribute("semester") != null) {
			semester = (Semester) session.getAttribute("semester");
			if (semester == null) {
				semester = semesterService.getSemesterById(classSemester.getSemester().getSemesterId(), true, true,
						false, false);
			}
		} else {
			semester = semesterService.getSemesterById(classSemester.getSemester().getSemesterId(), true, true, false,
					false);
		}
		semesterId = semester.getSemesterId();
		model.addAttribute("classCourseSemester", classCourseSemester);
		model.addAttribute("classCourseSemesters",
				classCourseSemesterService.listClassCourseSemesterBySemester(semesterId));
		model.addAttribute("classSemesters", semester.getClassSemesters());
		model.addAttribute("courseSemesters", semester.getCourseSemesters());
		model.addAttribute("classSemesterId", classSemester.getClassSemesterId());
		model.addAttribute("courseSemesterId", courseSemester.getCourseSemesterId());
		model.addAttribute("semesters", semesters);
		model.addAttribute("semesterId", semesterId);
		session.setAttribute("semester", semester);
		return "classCourse";
	}
}
