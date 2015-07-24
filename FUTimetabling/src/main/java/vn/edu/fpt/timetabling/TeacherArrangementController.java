package vn.edu.fpt.timetabling;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import vn.edu.fpt.timetabling.model.Course;
import vn.edu.fpt.timetabling.model.CourseSemester;
import vn.edu.fpt.timetabling.model.DataTeacherArrangement;
import vn.edu.fpt.timetabling.model.Department;
import vn.edu.fpt.timetabling.model.Timetable;
import vn.edu.fpt.timetabling.service.CourseSemesterService;
import vn.edu.fpt.timetabling.service.DepartmentService;
import vn.edu.fpt.timetabling.service.SemesterService;
import vn.edu.fpt.timetabling.service.TeacherArrangementService;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

@Controller
public class TeacherArrangementController {
	
	private SemesterService semesterService;
	private DepartmentService departmentService;
	private CourseSemesterService courseSemesterService;
	private TeacherArrangementService teacherArrangementService;
	
	private static final Logger logger = LoggerFactory.getLogger(TeacherArrangementController.class);
	
	
	@Autowired(required = true)
	@Qualifier(value = "semesterService")
	public void setSemesterService(
			SemesterService semesterService) {
		this.semesterService = semesterService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "departmentService")
	public void setDepartmentService(
			DepartmentService departmentService) {
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

	@RequestMapping(value = "/staff/teacherArrangement", method = RequestMethod.GET)
	public String teacherArrangement(Model model) {
		int semesterId = semesterService.listSemesters(false, false, false,	false).get(0).getSemesterId();
		int departmentId = departmentService.listDepartments().get(0).getDepartmentId();
		int courseId = courseSemesterService.listCourseSemestersByDepartment(semesterId, departmentId, false, false, false).get(0).getCourse().getCourseId();
		return "redirect:/staff/teacherArrangement?semesterId="+semesterId +"&departmentId=" +departmentId +"&courseId=" +courseId;

	}
	
	@RequestMapping(value = "/staff/teacherArrangement", method = RequestMethod.GET, params = {
			"semesterId"})
	public String teacherArrangementSemesterDepartment(
			@RequestParam int semesterId,
			Model model) {
		int departmentId = departmentService.listDepartments().get(0).getDepartmentId();
		int courseId = courseSemesterService.listCourseSemestersByDepartment(semesterId, departmentId, false, false, false).get(0).getCourse().getCourseId();
		return "redirect:/staff/teacherArrangement?semesterId="+semesterId +"&departmentId=" +departmentId +"&courseId=" +courseId;

	}

	@RequestMapping(value = "/staff/teacherArrangement", method = RequestMethod.GET, params = {
			"semesterId", "departmentId" })
	public String teacherArrangementSemesterDepartment(
			@RequestParam int semesterId, @RequestParam int departmentId,
			Model model) {
		int courseId = courseSemesterService.listCourseSemestersByDepartment(semesterId, departmentId, false, false, false).get(0).getCourse().getCourseId();
		return "redirect:/staff/teacherArrangement?semesterId="+semesterId +"&departmentId=" +departmentId +"&courseId=" +courseId;
	}

	@RequestMapping(value = "/staff/teacherArrangement", method = RequestMethod.GET, params = {
			"semesterId", "departmentId", "courseId" })
	public String teacherArrangementSemesterDepartmentCourse(
			@RequestParam int semesterId, @RequestParam int departmentId,
			@RequestParam int courseId, Model model) {
		List<Department> departmentsData = teacherArrangementService
				.getListDepartment();
		List<Course> coursesData = teacherArrangementService
				.getListCourse(departmentId);
		CourseSemester courseSemesterData = teacherArrangementService
				.getCourseSemester(semesterId, courseId);
		List<DataTeacherArrangement> dtaData = teacherArrangementService
				.getDataTeacherArrangement(semesterId, courseId);

		ObjectMapper om = new ObjectMapper();
		StringWriter departmentsJSON = new StringWriter();
		StringWriter coursesJSON = new StringWriter();
		StringWriter courseSemesterJSON = new StringWriter();
		StringWriter dtaJSON = new StringWriter();

		try {
			om.writeValue(departmentsJSON, departmentsData);
			om.writeValue(coursesJSON, coursesData);
			om.writeValue(courseSemesterJSON, courseSemesterData);
			om.writeValue(dtaJSON, dtaData);
			
			model.addAttribute("listSemesters", semesterService.listSemesters(false, false, false, false));
			model.addAttribute("departmentsData", departmentsJSON);
			model.addAttribute("coursesData", coursesJSON);
			model.addAttribute("courseSemesterData", courseSemesterJSON);
			model.addAttribute("dtaData", dtaJSON);
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
	public String updateTimetable(@RequestParam(value = "dataToSet", required = true) String dataToSet, HttpServletRequest request) {
		ObjectMapper om = new ObjectMapper();
		TypeFactory typeFactory = om.getTypeFactory();
		List<Timetable> data;
		try {
			data = om.readValue(dataToSet,	typeFactory.constructCollectionType(List.class, Timetable.class));
			logger.info(dataToSet);
			boolean result = teacherArrangementService.saveTimetables(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}
}
