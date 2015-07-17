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

import vn.edu.fpt.timetabling.dao.ClassCourseSemesterDAOImpl;
import vn.edu.fpt.timetabling.model.ClassSemester;
import vn.edu.fpt.timetabling.model.Room;
import vn.edu.fpt.timetabling.service.RoomArrangementService;
import vn.edu.fpt.timetabling.service.ScheduleService;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

@Controller
public class RoomArrangementController {
	private static final Logger logger = LoggerFactory.getLogger(ClassCourseSemesterDAOImpl.class);
	private RoomArrangementService roomArrangementService;
	private ScheduleService scheduleService;
	
	
	@Autowired(required = true)
	@Qualifier(value = "roomArrangementService")
	public void setRoomArrangementService(
			RoomArrangementService roomArrangementService) {
		this.roomArrangementService = roomArrangementService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "scheduleService")
	public void setScheduleService(
			ScheduleService scheduleService) {
		this.scheduleService = scheduleService;
	}
	
	@RequestMapping(value = "/staff/roomArrangement", method = RequestMethod.GET)
	public String room(@RequestParam(value = "semesterId", required = true) int semesterId, Model model) {
		List<ClassSemester> list = scheduleService.listClassBySemester(semesterId);
		return "redirect:/staff/roomArrangement?semesterId=" + semesterId + "&classId="
				+ list.get(0).getClassFPT().getClassId();
	}

	@RequestMapping(value = "/staff/roomArrangement", method = RequestMethod.GET, params = { "semesterId", "classId"})
	public String roomArrangement(@RequestParam int semesterId, @RequestParam int classId,Model model) {
		List<ClassSemester> JSONccs = roomArrangementService.getListClassesCoursesOfSemester(semesterId, classId);
		List<Room> JSONdataRoomArrangement = roomArrangementService.getListRoomsOfSemester(semesterId);
		
		ObjectMapper om = new ObjectMapper();
		StringWriter classesCoursesJSON = new StringWriter();
		StringWriter roomsJSON = new StringWriter();
		try {
			om.writeValue(classesCoursesJSON, JSONccs);
			om.writeValue(roomsJSON, JSONdataRoomArrangement);
			
			model.addAttribute("classesCourses", classesCoursesJSON);
			model.addAttribute("rooms", roomsJSON);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "roomArrangement";
	}
	
	@RequestMapping(value = "/staff/roomArrangement/updateTimetable", method = RequestMethod.POST)
	public String updateTimetable(@RequestParam(value = "dataToSet", required = true) String dataToSet, HttpServletRequest request) {

		ObjectMapper om = new ObjectMapper();
		TypeFactory typeFactory = om.getTypeFactory();
		List<ClassSemester> data;
		try {
			data = om.readValue(dataToSet, typeFactory.constructCollectionType(List.class, ClassSemester.class));
			roomArrangementService.saveTimetables(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
//		return "";
	}

}
