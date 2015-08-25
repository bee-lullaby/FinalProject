package vn.edu.fpt.timetabling;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import vn.edu.fpt.timetabling.model.CourseSemester;
import vn.edu.fpt.timetabling.model.DataTeacherArrangement;
import vn.edu.fpt.timetabling.model.Timetable;
import vn.edu.fpt.timetabling.service.ClassCourseSemesterMergeService;
import vn.edu.fpt.timetabling.service.CourseSemesterService;
import vn.edu.fpt.timetabling.service.DepartmentService;
import vn.edu.fpt.timetabling.service.SemesterService;
import vn.edu.fpt.timetabling.service.TeacherArrangementService;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

@Controller
public class TeacherArrangementController extends GeneralController {

	private SemesterService semesterService;
	private DepartmentService departmentService;
	private CourseSemesterService courseSemesterService;
	private TeacherArrangementService teacherArrangementService;
	private ClassCourseSemesterMergeService classCourseSemesterMergeService;

	@Autowired(required = true)
	@Qualifier(value = "semesterService")
	public void setSemesterService(SemesterService semesterService) {
		this.semesterService = semesterService;
	}

	@Autowired(required = true)
	@Qualifier(value = "departmentService")
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	@Autowired(required = true)
	@Qualifier(value = "courseSemesterService")
	public void setCourseSemesterService(
			CourseSemesterService courseSemesterService) {
		this.courseSemesterService = courseSemesterService;
	}

	@Autowired(required = true)
	@Qualifier(value = "teacherArrangementService")
	public void setTeacherArrangementService(
			TeacherArrangementService teacherArrangementService) {
		this.teacherArrangementService = teacherArrangementService;
	}

	@Autowired(required = true)
	@Qualifier(value = "classCourseSemesterMergeService")
	public void setClassCourseSemesterService(
			ClassCourseSemesterMergeService classCourseSemesterMergeService) {
		this.classCourseSemesterMergeService = classCourseSemesterMergeService;
	}
	
	@RequestMapping(value = "/staff/teacherArrangement", method = RequestMethod.GET)
	public String teacherArrangement(Model model) {
		int semesterId = semesterService
				.listSemesters(false, false, false, false).get(0)
				.getSemesterId();
		int departmentId = departmentService.listDepartments().get(0)
				.getDepartmentId();

		List<CourseSemester> courseSemesters = courseSemesterService
				.listCourseSemestersByDepartment(semesterId, departmentId,
						false, false, false);
		int courseSemesterId = 0;
		if (courseSemesters.size() != 0) {
			courseSemesterId = courseSemesters.get(0).getCourseSemesterId();
		}
		return "redirect:/staff/teacherArrangement?semesterId=" + semesterId
				+ "&departmentId=" + departmentId + "&courseSemesterId=" + courseSemesterId;
	}

	@RequestMapping(value = "/staff/teacherArrangement", method = RequestMethod.GET, params = { "semesterId" })
	public String teacherArrangementSemesterDepartment(
			@RequestParam int semesterId, Model model) {
		int departmentId = departmentService.listDepartments().get(0)
				.getDepartmentId();
		List<CourseSemester> courseSemesters = courseSemesterService
				.listCourseSemestersByDepartment(semesterId, departmentId,
						false, false, false);
		int courseSemesterId = 0;
		if (courseSemesters.size() != 0) {
			courseSemesterId = courseSemesters.get(0).getCourseSemesterId();
		}
		return "redirect:/staff/teacherArrangement?semesterId=" + semesterId
				+ "&departmentId=" + departmentId + "&courseSemesterId=" + courseSemesterId;

	}

	@RequestMapping(value = "/staff/teacherArrangement", method = RequestMethod.GET, params = {
			"semesterId", "departmentId" })
	public String teacherArrangementSemesterDepartment(
			@RequestParam int semesterId, @RequestParam int departmentId,
			Model model) {
		List<CourseSemester> courseSemesters = courseSemesterService
				.listCourseSemestersByDepartment(semesterId, departmentId,
						false, false, false);
		int courseSemesterId = 0;
		if (courseSemesters.size() != 0) {
			courseSemesterId = courseSemesters.get(0).getCourseSemesterId();
		}
		return "redirect:/staff/teacherArrangement?semesterId=" + semesterId
				+ "&departmentId=" + departmentId + "&courseSemesterId=" + courseSemesterId;
	}

	@RequestMapping(value = "/staff/teacherArrangement", method = RequestMethod.GET, params = {
			"semesterId", "departmentId", "courseSemesterId" })
	public String teacherArrangementSemesterDepartmentCourse(
			@RequestParam int semesterId, @RequestParam int departmentId,
			@RequestParam int courseSemesterId, Model model, HttpSession httpSession) {
		List<CourseSemester> coursesData =  teacherArrangementService.getListCourseSemester(semesterId, departmentId);
		CourseSemester courseSemesterData;
		List<DataTeacherArrangement> dtaData;
		HashMap<String, Set<Integer>> mMergerClassData = classCourseSemesterMergeService.getMapCourseWithMergeClassInSemester(semesterId);
		if (courseSemesterId != 0) {
			courseSemesterData = teacherArrangementService.getCourseSemester(courseSemesterId);
			dtaData = teacherArrangementService.getDataTeacherArrangement(courseSemesterId);
		} else {
			courseSemesterData = new CourseSemester();
			dtaData = new ArrayList<DataTeacherArrangement>();
		}
		ObjectMapper om = new ObjectMapper();
		StringWriter courseSemesterJSON = new StringWriter();
		StringWriter dtaJSON = new StringWriter();
		StringWriter mMergeClassJSON = new StringWriter();
		try {
			if (courseSemesterId != 0) {
				om.writeValue(courseSemesterJSON, courseSemesterData);
				om.writeValue(dtaJSON, dtaData);
				om.writeValue(mMergeClassJSON, mMergerClassData);
			}
			model.addAttribute("listSemesters",
					semesterService.listSemesters(false, false, false, false));
			model.addAttribute("listDepartments", teacherArrangementService.getListDepartment());
			if (courseSemesterId != 0) {
				model.addAttribute("semesterId", semesterId);
				model.addAttribute("departmentId", departmentId);
				model.addAttribute("coursesData", coursesData);
				model.addAttribute("courseSemesterData", courseSemesterJSON);
				model.addAttribute("dtaData", dtaJSON);
				model.addAttribute("mMergeClassData", mMergeClassJSON); 
			}
			checkError(httpSession, model);
			notifySuccess(httpSession, model);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "teacherArrangement";
	}

	@RequestMapping(value = "/staff/teacherArrangement/updateTimetable", method = RequestMethod.POST)
	public String updateTimetableTeacher(
			@RequestParam(value = "dataToSet", required = true) String dataToSet,
			HttpServletRequest request, HttpSession httpSession) {
		ObjectMapper om = new ObjectMapper();
		TypeFactory typeFactory = om.getTypeFactory();
		List<Timetable> data;
		try {
			data = om.readValue(dataToSet, typeFactory.constructCollectionType(
					List.class, Timetable.class));
			boolean result = teacherArrangementService.updateTimetable(data);
			if (result) 
				httpSession.setAttribute("success", "Update Teacher for Timetable successful!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}
}
