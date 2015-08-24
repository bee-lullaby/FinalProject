package vn.edu.fpt.timetabling;

import java.util.HashSet;
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

import vn.edu.fpt.timetabling.exception.MaxStudentException;
import vn.edu.fpt.timetabling.model.ClassCourseSemester;
import vn.edu.fpt.timetabling.model.ClassCourseStudentSemester;
import vn.edu.fpt.timetabling.model.ClassFPT;
import vn.edu.fpt.timetabling.model.ClassSemester;
import vn.edu.fpt.timetabling.model.Course;
import vn.edu.fpt.timetabling.model.CourseSemester;
import vn.edu.fpt.timetabling.model.Semester;
import vn.edu.fpt.timetabling.model.Specialized;
import vn.edu.fpt.timetabling.model.Student;
import vn.edu.fpt.timetabling.service.ClassCourseSemesterService;
import vn.edu.fpt.timetabling.service.ClassCourseStudentSemesterService;
import vn.edu.fpt.timetabling.service.ClassSemesterService;
import vn.edu.fpt.timetabling.service.SemesterService;
import vn.edu.fpt.timetabling.service.StudentService;
import vn.edu.fpt.timetabling.utils.Const;

@Controller
public class ClassCourseStudentSemesterController extends GeneralController {
	private ClassCourseStudentSemesterService classCourseStudentSemesterService;
	private SemesterService semesterService;
	private StudentService studentService;
	private ClassSemesterService classSemesterService;
	private ClassCourseSemesterService classCourseSemesterService;

	@Autowired(required = true)
	@Qualifier(value = "classCourseStudentSemesterService")
	public void setClassCourseStudentSemesterService(
			ClassCourseStudentSemesterService classCourseStudentSemesterService) {
		this.classCourseStudentSemesterService = classCourseStudentSemesterService;
	}

	@Autowired(required = true)
	@Qualifier(value = "semesterService")
	public void setSemesterService(SemesterService semesterService) {
		this.semesterService = semesterService;
	}

	@Autowired(required = true)
	@Qualifier(value = "studentService")
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	@Autowired(required = true)
	@Qualifier(value = "classSemesterService")
	public void setClassSemesterService(ClassSemesterService classSemesterService) {
		this.classSemesterService = classSemesterService;
	}

	@Autowired(required = true)
	@Qualifier(value = "classCourseSemesterService")
	public void setClassCourseSemesterService(ClassCourseSemesterService classCourseSemesterService) {
		this.classCourseSemesterService = classCourseSemesterService;
	}

	private void processClassCourseSemesters(HttpSession session, Model model, ClassSemester classSemester,
			Integer classCourseSemesterId) {
		Set<ClassCourseSemester> classCourseSemesters = classSemester.getClassCourseSemesters();
		if (!classCourseSemesters.isEmpty()) {
			ClassCourseSemester classCourseSemester = null;
			Course course = new Course();
			course.setName("ALL");
			CourseSemester courseSemester = new CourseSemester();
			courseSemester.setCourse(course);
			ClassCourseSemester classCourseSemesterAll = new ClassCourseSemester();
			classCourseSemesterAll.setClassCourseSemesterId(0);
			classCourseSemesterAll.setCourseSemester(courseSemester);
			classCourseSemesters.add(classCourseSemesterAll);
			if (classCourseSemesterId != null || session.getAttribute("classCourseSemester") != null) {
				if (classCourseSemesterId == null) {
					classCourseSemester = (ClassCourseSemester) session.getAttribute("classCourseSemester");
					boolean validClassCourseSemester = false;
					for (ClassCourseSemester classCourseSemesterTemp : classCourseSemesters) {
						if (classCourseSemesterTemp.getClassCourseSemesterId() == classCourseSemester
								.getClassCourseSemesterId()) {
							validClassCourseSemester = true;
							break;
						}
					}
					if (!validClassCourseSemester) {
						classCourseSemester = classCourseSemesterAll;
					}
				} else {
					for (ClassCourseSemester classCourseSemesterTemp : classCourseSemesters) {
						if (classCourseSemesterTemp.getClassCourseSemesterId() == classCourseSemesterId) {
							classCourseSemester = classCourseSemesterTemp;
						}
					}
				}
				if (classCourseSemester == null) {
					classCourseSemester = classCourseSemesterAll;
				}
			} else {
				classCourseSemester = classCourseSemesterAll;
			}
			classCourseSemesterId = classCourseSemester.getClassCourseSemesterId();
			Specialized specialized = classSemester.getClassFPT().getSpecialized();
			Specialized detailSpecialized = classSemester.getClassFPT().getDetailSpecialized();
			int detailspecializedId = -1;
			if (detailSpecialized != null) {
				detailspecializedId = detailSpecialized.getSpecializedId();
			}
			int semesterNumber = classSemester.getSemesterNumber();
			session.setAttribute("classCourseSemester", classCourseSemester);
			model.addAttribute("classCourseSemester", classCourseSemester);
			model.addAttribute("classCourseSemesterId", classCourseSemesterId);
			int classSemesterId = classSemester.getClassSemesterId();
			model.addAttribute("freeStudents", studentService.listStudentsCanBeInClassCourseSemester(classSemesterId,
					specialized.getSpecializedId(), detailspecializedId, semesterNumber, classCourseSemesterId));
			model.addAttribute("busyStudents",
					studentService.listStudentsInClassCourseSemester(classSemesterId, classCourseSemesterId));
		}
		model.addAttribute("classCourseSemesters", classCourseSemesters);
	}

	private void processClassSemesters(HttpSession session, Model model, Semester semester, Integer classSemesterId,
			Integer classCourseSemesterId) {
		Set<ClassSemester> classSemesters = semester.getClassSemesters();
		if (!classSemesters.isEmpty()) {
			ClassSemester classSemester = null;
			ClassFPT classFPT = new ClassFPT();
			classFPT.setCode("ALL");
			ClassSemester classSemesterAll = new ClassSemester();
			classSemesterAll.setClassSemesterId(0);
			classSemesterAll.setClassFPT(classFPT);
			classSemesterAll.setSemester(semester);
			classSemesters.add(classSemesterAll);
			if (classSemesterId != null || session.getAttribute("classSemester") != null) {
				if (classSemesterId == null) {
					classSemester = (ClassSemester) session.getAttribute("classSemester");
					boolean validClassSemester = false;
					for (ClassSemester classSemesterTemp : classSemesters) {
						if (classSemesterTemp.getClassSemesterId() == classSemester.getClassSemesterId()) {
							validClassSemester = true;
							break;
						}
					}
					if (!validClassSemester) {
						classSemester = classSemesterAll;
					}
				} else {
					for (ClassSemester classSemesterTemp : classSemesters) {
						if (classSemesterTemp.getClassSemesterId() == classSemesterId) {
							classSemester = classSemesterTemp;
						}
					}
				}
				if (classSemester == null) {
					classSemester = classSemesterAll;
				}
			} else {
				classSemester = classSemesterAll;
			}
			classSemesterId = classSemester.getClassSemesterId();
			if (classSemesterId == 0) {
				session.setAttribute("classSemester", classSemester);
				model.addAttribute("classSemester", classSemester);
				model.addAttribute("classSemesterId", classSemesterId);
				model.addAttribute("freeStudents", studentService.listStudentsWithoutClass(semester.getSemesterId()));
				model.addAttribute("busyStudents", studentService.listStudentsInClassCourseSemester(0, 0));
			} else {
				classSemester = classSemesterService.getClassSemesterById(classSemesterId, true);
				session.setAttribute("classSemester", classSemester);
				model.addAttribute("classSemester", classSemester);
				model.addAttribute("classSemesterId", classSemesterId);
				processClassCourseSemesters(session, model, classSemester, classCourseSemesterId);
			}
		}
		model.addAttribute("classSemesters", classSemesters);
	}

	@RequestMapping(value = "/staff/addStudentToClass", method = RequestMethod.GET)
	public String listClassCourseSemester(HttpSession session, Model model,
			@RequestParam(value = "semesterId", required = false) Integer semesterId,
			@RequestParam(value = "classSemesterId", required = false) Integer classSemesterId,
			@RequestParam(value = "classCourseSemesterId", required = false) Integer classCourseSemesterId) {
		List<Semester> semesters = semesterService.listSemesters(true, false, false, false);
		Semester semester;
		if (semesterId != null || session.getAttribute("semester") != null) {
			if (semesterId == null) {
				semester = (Semester) session.getAttribute("semester");
			} else {
				semester = semesterService.getSemesterById(semesterId, true, false, false, false);
			}
			if (semester == null) {
				semester = semesters.get(0);
			}
		} else {
			semester = semesters.get(0);
		}
		semesterId = semester.getSemesterId();
		semester = semesterService.getSemesterById(semesterId, true, false, false, false);
		processClassSemesters(session, model, semester, classSemesterId, classCourseSemesterId);
		model.addAttribute("semesters", semesters);
		model.addAttribute("semesterId", semesterId);
		session.setAttribute("semester", semester);
		checkError(session, model);
		notifySuccess(session, model);
		return "addStudentToClass";
	}

	@RequestMapping("/staff/addStudentToClass/add/{studentId}")
	public String addClassCourseStudentSemester(HttpSession session, @PathVariable("studentId") int studentId,
			Model model) throws MaxStudentException {
		Student student = studentService.getStudentById(studentId);
		if (student != null && (session.getAttribute("classCourseSemester") != null
				|| session.getAttribute("classSemester") != null)) {
			Set<ClassCourseSemester> classCourseSemesters;
			ClassSemester classSemester;
			if (session.getAttribute("classCourseSemester") != null
					&& ((ClassCourseSemester) session.getAttribute("classCourseSemester"))
							.getClassCourseSemesterId() != 0) {
				classCourseSemesters = new HashSet<ClassCourseSemester>();
				ClassCourseSemester classCourseSemester = (ClassCourseSemester) session
						.getAttribute("classCourseSemester");
				classSemester = classCourseSemester.getClassSemester();
				classCourseSemesters.add(classCourseSemester);
			} else {
				classSemester = (ClassSemester) session.getAttribute("classSemester");
				classCourseSemesters = classSemester.getClassCourseSemesters();
			}
			boolean maxStudent = false;
			for (ClassCourseSemester classCourseSemester : classCourseSemesters) {
				if (classCourseSemesterService.getNumberOfStudents(classCourseSemester
						.getClassCourseSemesterId()) >= Const.StudentNumber.MAX_NUMBER_OF_STUDENTS_IN_CLASS) {
					maxStudent = true;
					break;
				}
			}
			if (!maxStudent) {
				for (ClassCourseSemester classCourseSemester : classCourseSemesters) {
					if (classCourseSemester.getClassCourseSemesterId() == 0) {
						continue;
					}
					ClassCourseStudentSemester classCourseStudentSemester = new ClassCourseStudentSemester();
					classCourseStudentSemester.setClassCourseSemester(classCourseSemester);
					classCourseStudentSemester.setStudent(student);
					classCourseStudentSemesterService.addClassCourseStudentSemester(classCourseStudentSemester);
				}
			} else {
				throw new MaxStudentException();
			}
		}
		return "redirect:/staff/addStudentToClass";
	}

	@RequestMapping(value = "/staff/addStudentToClass/clearStudentClass", method = RequestMethod.GET, params = {
			"classSemesterId" })
	public String clearStudentClass(@RequestParam int classSemesterId, HttpSession httpSession, Model model) {
		classCourseStudentSemesterService.deleteClassCourseStudentSemesterByClass(classSemesterId);
		httpSession.setAttribute("success", "Clear students' class successful!");
		int semesterId = semesterService.listSemesters(false, false, false, false).get(0).getSemesterId();
		if (httpSession.getAttribute("semesterId") != null) {
			semesterId = (int) httpSession.getAttribute("semesterId");
		}
		return "redirect:/staff/addStudentToClass?semesterId=" + semesterId;
	}

	@RequestMapping(value = "/staff/addStudentToClass/clearStudentClasses", method = RequestMethod.GET, params = {
			"semesterId" })
	public String clearStudentClasses(@RequestParam int semesterId, HttpSession httpSession, Model model) {
		classCourseStudentSemesterService.deleteClassCourseStudentSemesters(semesterId);
		httpSession.setAttribute("success", "Clear students' classes successful!");
		return "redirect:/staff/addStudentToClass?semesterId=" + semesterId;
	}

	@RequestMapping(value = "/staff/addStudentToClass/autoStudentClass", method = RequestMethod.GET, params = {
			"classSemesterId" })
	public String autoStudentClass(@RequestParam int classSemesterId, HttpSession httpSession, Model model) {
		ClassSemester classSemester = classSemesterService.getClassSemesterById(classSemesterId, false);
		if (classSemester != null) {
			classCourseStudentSemesterService.autoPutStudentsIntoClassSemester(classSemesterId);
			httpSession.setAttribute("success", "Auto put student into class successful!");
		}
		return "redirect:/staff/addStudentToClass?semesterId=" + classSemester.getSemester().getSemesterId();
	}

	@RequestMapping(value = "/staff/addStudentToClass/autoStudentClasses", method = RequestMethod.GET, params = {
			"semesterId" })
	public String autoStudentClasses(@RequestParam int semesterId, HttpSession httpSession, Model model) {
		classCourseStudentSemesterService.autoPutStudentsIntoClassSemesters(semesterId);
		httpSession.setAttribute("success", "Auto put student into classes successful!");
		return "redirect:/staff/addStudentToClass?semesterId=" + semesterId;
	}

	@RequestMapping("/staff/addStudentToClass/remove/{studentId}")
	public String removeClassCourseStudentSemester(HttpSession session, @PathVariable("studentId") int studentId,
			Model model) {
		Student student = studentService.getStudentById(studentId);
		if (student != null && (session.getAttribute("classCourseSemester") != null
				|| session.getAttribute("classSemester") != null)) {
			Set<ClassCourseSemester> classCourseSemesters;
			ClassSemester classSemester;
			if (session.getAttribute("classCourseSemester") != null
					&& ((ClassCourseSemester) session.getAttribute("classCourseSemester"))
							.getClassCourseSemesterId() != 0) {
				classCourseSemesters = new HashSet<ClassCourseSemester>();
				ClassCourseSemester classCourseSemester = (ClassCourseSemester) session
						.getAttribute("classCourseSemester");
				classCourseSemesters.add(classCourseSemester);
				classSemester = classCourseSemester.getClassSemester();
			} else {
				classSemester = (ClassSemester) session.getAttribute("classSemester");
				classCourseSemesters = classSemester.getClassCourseSemesters();
			}
			for (ClassCourseSemester classCourseSemester : classCourseSemesters) {
				classCourseStudentSemesterService.removeStudentFromClassCourseSemester(studentId,
						classCourseSemester.getClassCourseSemesterId());
			}
		}
		return "redirect:/staff/addStudentToClass";
	}

	@ExceptionHandler(Exception.class)
	public String handleException(HttpSession httpSession, Exception e) {
		if (e instanceof MaxStudentException) {
			httpSession.setAttribute("error",
					"Student number exceeds " + Const.StudentNumber.MAX_NUMBER_OF_STUDENTS_IN_CLASS);
		} else {
			httpSession.setAttribute("error", "Error, please try again.");
		}
		return "redirect:/staff/addStudentToClass";
	}
}
