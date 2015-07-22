package vn.edu.fpt.timetabling;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

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
import vn.edu.fpt.timetabling.service.TeacherArrangementService;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class TeacherArrangementController {
	private TeacherArrangementService teacherArrangementService;

	@Autowired(required = true)
	@Qualifier(value = "teacherArrangementService")
	public void setRoomService(
			TeacherArrangementService teacherArrangementService) {
		this.teacherArrangementService = teacherArrangementService;
	}

	@RequestMapping(value = "/staff/teacherArrangement", method = RequestMethod.GET)
	public String teacherArrangement(Model model) {
		return "redirect:/staff/teacherArrangement?semesterId=1&departmentId=1&courseId=1";
	}

	@RequestMapping(value = "/staff/teacherArrangement", method = RequestMethod.GET, params = {
			"semesterId", "departmentId" })
	public String teacherArrangementSemesterDepartment(
			@RequestParam int semesterId, @RequestParam int departmentId,
			Model model) {
		List<Course> coursesData = teacherArrangementService
				.getListCourse(departmentId);
		return "redirect:/staff/teacherArrangement?semesterId=" + semesterId
				+ "&departmentId=" + departmentId + "&courseId="
				+ coursesData.get(0).getCourseId();
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
}
