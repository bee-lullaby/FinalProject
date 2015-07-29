package vn.edu.fpt.timetabling;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.multipart.MultipartFile;

import vn.edu.fpt.timetabling.model.ClassCourseSemester;
import vn.edu.fpt.timetabling.model.ClassFPT;
import vn.edu.fpt.timetabling.model.ClassSemester;
import vn.edu.fpt.timetabling.service.ClassCourseSemesterService;
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
	@Qualifier(value = "classCourseSemesterService")
	public void setClassCourseSemesterService(ClassCourseSemesterService classCourseSemesterService) {
		this.classCourseSemesterService = classCourseSemesterService;
	}
	
	@RequestMapping(value = "/staff/classFPTs")
	public String classFPTinit(HttpSession httpSession, Model model) {
		int semesterId = semesterService.listSemesters(false, false, false, false).get(0).getSemesterId();
		return "redirect:/staff/classFPTs?semesterId=" +semesterId;
	}
	
	@RequestMapping(value = "/staff/classFPTs", method = RequestMethod.GET, params = {"semesterId"})
	public String classFPTs(@RequestParam int semesterId, HttpSession httpSession, Model model) {
		model.addAttribute("listSemesters", semesterService.listSemesters(false, false, false, false));
		model.addAttribute("listClassSemesters", classSemesterService.listClassSemesterForView(semesterId));
		model.addAttribute("listSpecializeds", specializedService.listSpecializeds(false, false));
		model.addAttribute("listCourseSemesters", courseSemesterService.listCourseSemesters(false, false, false));
		
		checkError(httpSession, model);
		return "classFPTs";
	}
	
	@RequestMapping(value = "/staff/classFPTs/updateClassFPTs", method = RequestMethod.POST, 
			params = {"classId", "classSemesterId", "semesterId", "batch", "batchChar", "type", "specializedId", "courses"})
	public String updateClassFPTs(@RequestParam int classId, @RequestParam int classSemesterId, 
			@RequestParam int semesterId, @RequestParam int batch, @RequestParam char batchChar, @RequestParam String type, 
			@RequestParam int specializedId, @RequestParam String[] courses, HttpSession httpSession, Model model, HttpServletRequest request) {
		if (type.equals(ClassType.SPECIALIZED)) {
			if (specializedId < 1 || batch < 1) {
			//	return "redirect:/staff/classes";
			}
		} else if (type.equals("Course")) {
			if (courses.length > 1) {
				//return "redirect:/staff/classes";
			}
		} else {
			//return "redirect:/staff/classes";
		}
		
		ClassFPT classFPT = classService.getClassById(classId);
		if (classFPT == null) {
			classFPT = new ClassFPT();
		}
		classFPT.setType(type);
		String classCodePrefix;
		String classCode = "";
		if (type.equals(ClassType.SPECIALIZED)) {
			classFPT.setSpecialized(specializedService.getSpecializedById(specializedId, false, false));
			classFPT.setBatch(batch);
			classFPT.setBatchChar(batchChar);
			classFPT.setCourse(null);
			classCodePrefix = classFPT.getSpecialized().getCode() + String.format("%02d", batch);
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
		if (classId == -1) {
			// new class, add it
			classService.addClass(classFPT);
		} else {
			// existing class, call update
			classFPT.setClassId(classId);
			classService.updateClass(classFPT);
		}
		
		ClassSemester classSemester = new ClassSemester();
		classSemester.setSemester(semesterService.getSemesterById(semesterId, false, false, false, false));
		classSemester.setClassFPT(classFPT);
		
		if(classSemesterId == -1) {
			classSemesterService.addClassSemester(classSemester);
		} else {
			classSemester.setClassSemesterId(classSemesterId);
			classSemesterService.updateClassSemester(classSemester);
		}
		
		
		if(classId != -1 && classSemesterId != -1) {
			Set<ClassCourseSemester> classCourseSemesters = classSemesterService.getClassSemesterById(classSemesterId, true).getClassCourseSemesters();
			for(ClassCourseSemester ccs : classCourseSemesters){
				classCourseSemesterService.deleteClassCourseSemester(ccs.getClassCourseSemesterId());
			}
		}
		
		for(String course : courses) {
			ClassCourseSemester ccs = new ClassCourseSemester();
			ccs.setCourseSemester(courseSemesterService.getCourseSemesterByCourseSemester(Integer.parseInt(course), semesterId, false, false, false));
			ccs.setClassSemester(classSemesterService.getClassSemesterByCode(classCode, semesterId, false));
			classCourseSemesterService.addClassCourseSemester(ccs);
		}
		
		checkError(httpSession, model);
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}
	
	@RequestMapping(value="/staff/classFPTs/addFromFile", method = RequestMethod.POST)
	public String addClassFromFile(@RequestParam("act") int act, @RequestParam("file") MultipartFile file, 
					@RequestParam("semesterId") int semesterId, HttpServletRequest request) {
		
		if (!file.isEmpty()) {
			File classSemesters = new File("D:\\FU\\Do an tot nghiep\\Data\\ServerData\\" + file.getOriginalFilename());
			try {
				file.transferTo(classSemesters);
				if(act == 0) {
					classSemesterService.addClassSemesterFromFile(classSemesters, semesterId);
				} else if (act == 1) {
					classCourseSemesterService.addClassCourseSemesterFromFile(classSemesters, semesterId);
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
	
	@RequestMapping(value="/staff/classFPTs/delete/{classId}")
	public String deleteClassFPT(@PathVariable("classId") int classId, HttpServletRequest request) {
		classService.deleteClass(classId);
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}
	
	@ExceptionHandler(Exception.class)
	public String handleException(HttpSession httpSession, HttpServletRequest request, Exception e) {
		httpSession.setAttribute("error", "Error, please try again.");
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}
	
}
