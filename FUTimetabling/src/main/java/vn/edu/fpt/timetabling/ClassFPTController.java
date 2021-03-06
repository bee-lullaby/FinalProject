package vn.edu.fpt.timetabling;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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

import vn.edu.fpt.timetabling.model.ClassCourseSemester;
import vn.edu.fpt.timetabling.model.ClassCourseStudentSemester;
import vn.edu.fpt.timetabling.model.ClassFPT;
import vn.edu.fpt.timetabling.model.ClassSemester;
import vn.edu.fpt.timetabling.model.CourseSemester;
import vn.edu.fpt.timetabling.model.Semester;
import vn.edu.fpt.timetabling.service.ClassCourseSemesterService;
import vn.edu.fpt.timetabling.service.ClassCourseStudentSemesterService;
import vn.edu.fpt.timetabling.service.ClassSemesterService;
import vn.edu.fpt.timetabling.service.ClassService;
import vn.edu.fpt.timetabling.service.CourseSemesterService;
import vn.edu.fpt.timetabling.service.CourseService;
import vn.edu.fpt.timetabling.service.SemesterService;
import vn.edu.fpt.timetabling.service.SpecializedService;
import vn.edu.fpt.timetabling.utils.Const.ClassType;

@Controller
public class ClassFPTController extends GeneralController {
	private ClassService classService;
	private SpecializedService specializedService;
	private CourseService courseService;
	private SemesterService semesterService;
	private ClassSemesterService classSemesterService;
	private CourseSemesterService courseSemesterService;
	private ClassCourseSemesterService classCourseSemesterService;
	private ClassCourseStudentSemesterService classCourseStudentSemesterService;
	
	@Autowired(required = true)
	@Qualifier(value = "classService")
	public void setClassService(ClassService classService) {
		this.classService = classService;
	}

	@Autowired(required = true)
	@Qualifier(value = "specializedService")
	public void setSpecializedService(SpecializedService specializedService) {
		this.specializedService = specializedService;
	}

	@Autowired(required = true)
	@Qualifier(value = "courseService")
	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
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

	@Autowired(required = true)
	@Qualifier(value = "classCourseStudentSemesterService")
	public void setClassCourseStudentSemesterService(
			ClassCourseStudentSemesterService classCourseStudentSemesterService) {
		this.classCourseStudentSemesterService = classCourseStudentSemesterService;
	}

	@Autowired(required = true)
	@Qualifier(value = "classCourseSemesterService")
	public void setClassCourseSemesterService(ClassCourseSemesterService classCourseSemesterService) {
		this.classCourseSemesterService = classCourseSemesterService;
	}
	
	@RequestMapping(value = "/staff/classFPTs")
	public String classFPTinit(HttpSession httpSession, Model model) {
		List<Semester> semesters = semesterService.listSemesters(false, false, false, false);
		int semesterId = semesters.get(0).getSemesterId();
		if (!semesters.isEmpty())
			semesterId = semesters.get(0).getSemesterId();
		return "redirect:/staff/classFPTs?semesterId=" + semesterId;
	}

	@RequestMapping(value = "/staff/classFPTs", method = RequestMethod.GET, params = { "semesterId" })
	public String classFPTs(@RequestParam int semesterId, HttpSession httpSession, Model model) {
		model.addAttribute("listSemesters", semesterService.listSemesters(false, false, false, false));
		model.addAttribute("listClassSemesters", classSemesterService.listClassSemesterForView(semesterId));
		model.addAttribute("listSpecializeds", specializedService.listSpecializeds(false, false));
		model.addAttribute("listDetailSpecializeds", specializedService.listDetailSpecializeds(false, false));
		model.addAttribute("listCourseSemesters", courseSemesterService.listCourseSemesters(false, false, false));
		httpSession.setAttribute("semesterId", semesterId);
		checkError(httpSession, model);
		notifySuccess(httpSession, model);
		return "classFPTs";
	}

	@RequestMapping(value = "/staff/classFPTs/createFileData", method = RequestMethod.GET)
	public String createFileData(HttpSession httpSession, Model model) throws Exception {
		int semesterId = semesterService.listSemesters(false, false, false, false).get(0).getSemesterId();
		if (httpSession.getAttribute("semesterId") != null) {
			semesterId = (int) httpSession.getAttribute("semesterId");
		}
		FileOutputStream courseClassStatusNumberStudent = new FileOutputStream(
				"D:\\courseClassStatusNumberStudent.txt");
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(courseClassStatusNumberStudent);
		List<ClassCourseSemester> classCourseSemesters = classCourseSemesterService
				.listClassCourseSemesterBySemester(semesterId, false, false);
		for (ClassCourseSemester classCourseSemester : classCourseSemesters) {
			outputStreamWriter.write(classCourseSemester.getCourseSemester().getCourse().getCode());
			outputStreamWriter.write(" " + classCourseSemester.getClassSemester().getClassFPT().getCode());
			if (classCourseSemester.isSemesterLong()) {
				outputStreamWriter.write(" 3");
			} else {
				outputStreamWriter.write(" " + classCourseSemester.getBlockCondition());
			}
			outputStreamWriter.write(" "
					+ classCourseSemesterService.getNumberOfStudents(classCourseSemester.getClassCourseSemesterId()));
			outputStreamWriter.write("\n");
		}
		outputStreamWriter.close();
		courseClassStatusNumberStudent.close();
		FileOutputStream courseClassStudent = new FileOutputStream("D:\\courseClassStudent.txt");
		OutputStreamWriter outputStreamWriter2 = new OutputStreamWriter(courseClassStudent);
		List<ClassCourseStudentSemester> classCourseStudentSemesters = classCourseStudentSemesterService
				.listClassCourseStudentSemesters();
		for (ClassCourseStudentSemester classCourseStudentSemester : classCourseStudentSemesters) {
			outputStreamWriter2.write(
					classCourseStudentSemester.getClassCourseSemester().getCourseSemester().getCourse().getCode());
			outputStreamWriter2.write(" "
					+ classCourseStudentSemester.getClassCourseSemester().getClassSemester().getClassFPT().getCode());
			outputStreamWriter2.write(" " + classCourseStudentSemester.getStudent().getStudentCode());
			outputStreamWriter2.write("\n");
		}
		outputStreamWriter2.close();
		courseClassStudent.close();
		return "redirect:/staff/classFPTs?semesterId=" + semesterId;
	}

	@RequestMapping(value = "/staff/classFPTs/updateClassCourse", method = RequestMethod.POST, params = { "semesterId",
			"classId", "courseId", "blockCondition", "semesterLong" })
	public String updateClassCourse(@RequestParam int semesterId, @RequestParam int classId,
			@RequestParam String[] courseId, @RequestParam String[] blockCondition, @RequestParam String[] semesterLong,
			HttpSession httpSession, HttpServletRequest request) {
		ClassSemester classSemester = classSemesterService.getClassSemesterByClassSemester(semesterId, classId, true);
		for (ClassCourseSemester classCourseSemester : classSemester.getClassCourseSemesters()) {
			classCourseSemesterService.deleteClassCourseSemester(classCourseSemester.getClassCourseSemesterId());
		}
		for (int i = 0; i < courseId.length; i++) {
			if (Integer.parseInt(courseId[i]) != -1) {
				CourseSemester courseSemester = courseSemesterService.getCourseSemesterByCourseSemester(
						Integer.parseInt(courseId[i]), semesterId, false, false, false);
				ClassCourseSemester classCourseSemester = new ClassCourseSemester();
				classCourseSemester.setClassSemester(classSemester);
				classCourseSemester.setCourseSemester(courseSemester);
				classCourseSemester.setBlockCondition(Integer.parseInt(blockCondition[i]));
				int semesterLongInt = Integer.parseInt(semesterLong[i]);
				if (semesterLongInt == 1) {
					classCourseSemester.setSemesterLong(true);
				} else if (semesterLongInt == 0) {
					classCourseSemester.setSemesterLong(false);
				}
				classCourseSemesterService.addClassCourseSemester(classCourseSemester);
			}
		}
		httpSession.setAttribute("success", "Courses of Class were updated successful!");
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@RequestMapping(value = "/staff/classFPTs/updateClassFPTs", method = RequestMethod.POST, params = {"semesterId", "batch", "batchChar", "type", "specializedId" })
	public String updateClassFPTs(
			@RequestParam int semesterId, @RequestParam int batch, @RequestParam char batchChar,
			@RequestParam String type, @RequestParam int specializedId,
			@RequestParam(value = "detailSepecializedId", required = false) int detailSepecializedId,
			@RequestParam(value = "courses", required = false) String[] courses, HttpSession httpSession, Model model,
			HttpServletRequest request) {
		if (type.equals(ClassType.SPECIALIZED)) {
			if (specializedId < 1 || batch < 1) {
				// return "redirect:/staff/classes";
			}
		} else if (type.equals("Course")) {
			if (courses.length > 1) {
				// return "redirect:/staff/classes";
			}
		} else {
			// return "redirect:/staff/classes";
		}

		ClassFPT classFPT = new ClassFPT();
		classFPT.setType(type);
		String classCodePrefix;
		String classCode = "";
		if (type.equals(ClassType.SPECIALIZED)) {
			classFPT.setSpecialized(specializedService.getSpecializedById(specializedId, false, false));
			classFPT.setBatch(batch);
			classFPT.setBatchChar(batchChar);
			classFPT.setCourse(null);
			if (detailSepecializedId != -1) {
				classFPT.setDetailSpecialized(specializedService.getSpecializedById(detailSepecializedId, false, false));
			}
			classCodePrefix = classFPT.getDetailSpecialized().getCode() + String.format("%02d", batch);
		} else {
			classFPT.setBatch(null);
			classFPT.setSpecialized(null);
			classFPT.setCourse(courseService.getCourseById(Integer.parseInt(courses[0])));
			classCodePrefix = classFPT.getCourse().getCode();
		}
		classFPT.setNumber(classService.getNextClassNumber(classCodePrefix));
		
		if (type.equals(ClassType.SPECIALIZED)) {
			classCode = classCodePrefix + String.format("%02d", classFPT.getNumber());
			classFPT.setCode(classCode);
		} else {
			classCode = classCodePrefix + "." + classFPT.getNumber();
			classFPT.setCode(classCode);
		}

		classService.addClass(classFPT);

		ClassSemester classSemester = new ClassSemester();
		classSemester.setSemester(semesterService.getSemesterById(semesterId, false, false, false, false));
		classSemester.setClassFPT(classFPT);

			classSemesterService.addClassSemester(classSemester);
		

		if (courses != null) {
			for (String course : courses) {
				ClassCourseSemester ccs = new ClassCourseSemester();
				ccs.setCourseSemester(courseSemesterService.getCourseSemesterByCourseSemester(Integer.parseInt(course),
						semesterId, false, false, false));
				ccs.setClassSemester(classSemesterService.getClassSemesterByCode(classCode, semesterId, false));
				classCourseSemesterService.addClassCourseSemester(ccs);
			}
		}
		httpSession.setAttribute("success", "Add Class Successful!");
		return "redirect:/staff/classFPTs";
	}

	@RequestMapping(value = "/staff/classFPTs/addFromFile", method = RequestMethod.POST)
	public String addClassFromFile(@RequestParam("act") int act, @RequestParam("file") MultipartFile file,
			@RequestParam("semesterId") int semesterId, HttpSession httpSession, HttpServletRequest request) {

		if (!file.isEmpty()) {
			File classSemesters = new File("classSemesters.xlxs");
			try {
				file.transferTo(classSemesters);
//				if (act == 0) {
				classSemesterService.addClassSemesterFromFile(classSemesters, semesterId);
				httpSession.setAttribute("success", "Add classes successful!");
//				} else if (act == 1) {
//					HashMap<String, List<String>> check = classCourseSemesterService
//							.addClassCourseSemesterFromFile(classSemesters, semesterId);
//
//					if (!check.get("class").isEmpty()) {
//						String text = "Class " + check.get("class").get(0);
//						for (int i = 1; i < check.get("class").size(); i++) {
//							text += ", " + check.get("class").get(i);
//						}
//						text += " of this Semester";
//						if (check.get("class").size() == 1)
//							text += " is ";
//						else
//							text += " are ";
//						text += " not existed! Please insert class first then try again!";
//						httpSession.setAttribute("error", text);
//					}
//					if (!check.get("course").isEmpty()) {
//						String text = "Course " + check.get("course").get(0);
//						for (int i = 1; i < check.get("course").size(); i++) {
//							text += ", " + check.get("course").get(i);
//						}
//						text += " of this Semester";
//						if (check.get("course").size() == 1)
//							text += " is ";
//						else
//							text += " are ";
//						text += " not existed! Please insert course first then try again!";
//						httpSession.setAttribute("error", text);
//					}
//					if (check.get("course").isEmpty() && check.get("class").isEmpty()) {
//						httpSession.setAttribute("success", "Add Courses For Classes Successful!");
//					}
//				}
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@RequestMapping(value = "/staff/classFPTs/deleteClassFPT", method = RequestMethod.GET, params = { "classId" })
	public String deleteClassFPT(@RequestParam int classId, HttpSession httpSession,HttpServletRequest request) {	
		classService.deleteClass(classId);
		httpSession.setAttribute("success", "Delete Class Successful!");
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}
	
	 @ExceptionHandler(Exception.class)
	 public String handleException(HttpSession httpSession, Exception e) {
	 httpSession.setAttribute("error",
	 "Error!");
	 return "redirect:/staff/classFPTs";
	 }

}
