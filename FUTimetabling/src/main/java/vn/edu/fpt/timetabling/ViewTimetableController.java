package vn.edu.fpt.timetabling;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import vn.edu.fpt.timetabling.model.ClassSemester;
import vn.edu.fpt.timetabling.model.Semester;
import vn.edu.fpt.timetabling.model.Student;
import vn.edu.fpt.timetabling.model.TeacherSemester;
import vn.edu.fpt.timetabling.service.ClassSemesterService;
import vn.edu.fpt.timetabling.service.SemesterService;
import vn.edu.fpt.timetabling.service.StudentService;
import vn.edu.fpt.timetabling.service.TeacherSemesterService;
import vn.edu.fpt.timetabling.service.TimetableService;

@Controller
public class ViewTimetableController {

	@Autowired
	private SemesterService semesterService;
	@Autowired
	private TeacherSemesterService teacherSemesterService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private ClassSemesterService classSemesterService;
	@Autowired
	private TimetableService timetableService;

	@RequestMapping(value = "/staff/viewTimetable", method = RequestMethod.GET)
	public String viewTimetableStaffInit(Model model, HttpSession httpSession) {
		List<Semester> semesters = semesterService.listSemesters(false, false,
				false, false);
		int semesterId = 0;
		if (!semesters.isEmpty())
			semesterId = semesters.get(0).getSemesterId();
		return "redirect:/staff/viewTimetable?semesterId=" + semesterId
				+ "&classSemesterId=0";
	}

	@RequestMapping(value = "/staff/viewTimetable", method = RequestMethod.GET, params = { "semesterId" })
	public String viewTimetableStaff(
			@RequestParam int semesterId,
			@RequestParam(value = "studentId", required = false) Integer studentId,
			@RequestParam(value = "teacherSemesterId", required = false) Integer teacherSemesterId,
			@RequestParam(value = "classSemesterId", required = false) Integer classSemesterId,
			Model model, HttpSession httpSession) {
		if (studentId == null && teacherSemesterId == null
				&& classSemesterId == null) {
			return "redirect:/staff/viewTimetable";
		} else if (studentId != null) {
			if (studentId != 0) {
				model.addAttribute("accountType", 1);
				model.addAttribute("listSemesters", semesterService
						.listSemesters(false, false, false, false));
				Semester s = semesterService.getSemesterById(semesterId, false,
						false, false, false);
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				model.addAttribute("startDate", sdf.format(s.getStartDate()));
				model.addAttribute("endDate", sdf.format(s.getEndDate()));
				model.addAttribute("listStudents",
						studentService.listStudents());
				// timetable for student
				model.addAttribute("listTimetables", timetableService
						.listTimetableByStudent(semesterId, studentId));
			} else {

				List<Student> list = studentService.listStudents();
				if (list != null && !list.isEmpty() && list.get(0) != null) {
					return "redirect:/staff/viewTimetable?semesterId="
							+ semesterId + "&studentId="
							+ list.get(0).getStudentId();
				} else {
					model.addAttribute("accountType", 1);
					model.addAttribute("listSemesters", semesterService
							.listSemesters(false, false, false, false));
					Semester s = semesterService.getSemesterById(semesterId,
							false, false, false, false);
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					model.addAttribute("startDate",
							sdf.format(s.getStartDate()));
					model.addAttribute("endDate", sdf.format(s.getEndDate()));
					model.addAttribute("listTeacherSemesters", list);
					model.addAttribute("listStudents",
							studentService.listStudents());
				}
			}
		} else if (teacherSemesterId != null) {
			if (teacherSemesterId != 0) {
				model.addAttribute("accountType", 1);
				model.addAttribute("listSemesters", semesterService
						.listSemesters(false, false, false, false));
				Semester s = semesterService.getSemesterById(semesterId, false,
						false, false, false);
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				model.addAttribute("startDate", sdf.format(s.getStartDate()));
				model.addAttribute("endDate", sdf.format(s.getEndDate()));

				model.addAttribute("listTeacherSemesters",
						teacherSemesterService
								.listTeacherSemestersForView(semesterId));
				model.addAttribute("listTimetables", timetableService
						.listTimetableByTeacher(teacherSemesterId));
			} else {

				List<TeacherSemester> list = teacherSemesterService
						.listTeacherSemestersForView(semesterId);
				if (list != null && !list.isEmpty() && list.get(0) != null) {
					return "redirect:/staff/viewTimetable?semesterId="
							+ semesterId + "&teacherSemesterId="
							+ list.get(0).getTeacherSemesterId();
				} else {
					model.addAttribute("accountType", 1);
					model.addAttribute("listSemesters", semesterService
							.listSemesters(false, false, false, false));
					Semester s = semesterService.getSemesterById(semesterId,
							false, false, false, false);
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					model.addAttribute("startDate",
							sdf.format(s.getStartDate()));
					model.addAttribute("endDate", sdf.format(s.getEndDate()));
					model.addAttribute("listTeacherSemesters", list);
				}
			}
		} else if (classSemesterId != null) {
			if (classSemesterId != 0) {
				model.addAttribute("accountType", 1);
				model.addAttribute("listSemesters", semesterService
						.listSemesters(false, false, false, false));
				Semester s = semesterService.getSemesterById(semesterId, false,
						false, false, false);
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				model.addAttribute("startDate", sdf.format(s.getStartDate()));
				model.addAttribute("endDate", sdf.format(s.getEndDate()));

				ClassSemester cs = classSemesterService.getClassSemesterById(
						classSemesterId, true);
				model.addAttribute("listClassSemesters", classSemesterService
						.listClassSemesterForView(semesterId));
				model.addAttribute("listTimetables",
						timetableService.listTimetableByClass(classSemesterId));
			} else {

				List<ClassSemester> list = classSemesterService
						.listClassSemesterForView(semesterId);
				if (list != null && !list.isEmpty() && list.get(0) != null) {
					return "redirect:/staff/viewTimetable?semesterId="
							+ semesterId + "&classSemesterId="
							+ list.get(0).getClassSemesterId();
				} else {
					model.addAttribute("accountType", 1);
					model.addAttribute("listSemesters", semesterService
							.listSemesters(false, false, false, false));
					Semester s = semesterService.getSemesterById(semesterId,
							false, false, false, false);
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					model.addAttribute("startDate",
							sdf.format(s.getStartDate()));
					model.addAttribute("endDate", sdf.format(s.getEndDate()));
					model.addAttribute("listClassSemesters", list);
				}
			}
		}

		return "viewTimetable";

	}

	@RequestMapping(value = "/teacherPage/viewTimetable", method = RequestMethod.GET)
	public String viewTimetableTeacherInit(Model model, HttpSession httpSession) {
		List<Semester> semesters = semesterService.listSemesters(false, false,
				false, false);
		int semesterId = 0;
		if (!semesters.isEmpty())
			semesterId = semesters.get(0).getSemesterId();
		int id = (Integer) httpSession.getAttribute("id");
		TeacherSemester ts = teacherSemesterService
				.getTeacherSemesterByTeacherSemester(id, semesterId, false,
						false);
		if (ts != null) {
			return "redirect:/teacherPage/viewTimetable?semesterId="
					+ semesterId + "&teacherSemesterId="
					+ ts.getTeacherSemesterId();
		} else {
			return "redirect:/teacherPage/viewTimetable?semesterId="
					+ semesterId + "&teacherSemesterId=0";
		}
	}

	@RequestMapping(value = "/teacherPage/viewTimetable", method = RequestMethod.GET, params = { "semesterId" })
	public String viewTimetableTeacher(
			@RequestParam int semesterId,
			@RequestParam(value = "teacherSemesterId", required = false) Integer teacherSemesterId,
			Model model, HttpSession httpSession) {
		if (teacherSemesterId == null) {
			return "redirect:/teacherPage/viewTimetable";
		} else {
			model.addAttribute("accountType", 2);
			model.addAttribute("listSemesters",
					semesterService.listSemesters(false, false, false, false));
			Semester s = semesterService.getSemesterById(semesterId, false,
					false, false, false);
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			model.addAttribute("startDate", sdf.format(s.getStartDate()));
			model.addAttribute("endDate", sdf.format(s.getEndDate()));
			model.addAttribute("listTimetables",
					timetableService.listTimetableByTeacher(teacherSemesterId));
		}
		return "viewTimetable";
	}

	@RequestMapping(value = "/studentPage/viewTimetable", method = RequestMethod.GET)
	public String viewTimetableStudentInit(Model model, HttpSession httpSession) {
		List<Semester> semesters = semesterService.listSemesters(false, false,
				false, false);
		int semesterId = 0;
		if (!semesters.isEmpty())
			semesterId = semesters.get(0).getSemesterId();
		int id = (Integer) httpSession.getAttribute("id");
		Student s = studentService.getStudentById(id);
		if (s != null) {
			return "redirect:/studentPage/viewTimetable?semesterId="
					+ semesterId + "&studentId=" + s.getStudentId();
		} else {
			return "redirect:/studentPage/viewTimetable?semesterId="
					+ semesterId + "&studentId=0";
		}
	}

	@RequestMapping(value = "/studentPage/viewTimetable", method = RequestMethod.GET, params = { "semesterId" })
	public String viewTimetableStudent(
			@RequestParam int semesterId,
			@RequestParam(value = "studentId", required = false) Integer studentId,
			Model model, HttpSession httpSession) {
		if (studentId == null) {
			return "redirect:/studentPage/viewTimetable";
		} else {
			model.addAttribute("accountType", 3);
			model.addAttribute("listSemesters",
					semesterService.listSemesters(false, false, false, false));
			Semester s = semesterService.getSemesterById(semesterId, false,
					false, false, false);
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			model.addAttribute("startDate", sdf.format(s.getStartDate()));
			model.addAttribute("endDate", sdf.format(s.getEndDate()));
		}

		return "viewTimetable";
	}

}
