package vn.edu.fpt.timetabling;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import vn.edu.fpt.timetabling.model.ClassSemester;
import vn.edu.fpt.timetabling.model.Room;
import vn.edu.fpt.timetabling.model.Timetable;
import vn.edu.fpt.timetabling.service.RoomArrangementService;
import vn.edu.fpt.timetabling.service.RoomService;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class RoomArrangementController {

	private RoomArrangementService roomArrangementService;
	
	private RoomService roomService;

	@Autowired(required = true)
	@Qualifier(value = "roomArrangementService")
	public void setRoomArrangementService(
			RoomArrangementService roomArrangementService) {
		this.roomArrangementService = roomArrangementService;
	}

	@Autowired(required = true)
	@Qualifier(value = "roomService")
	public void setRoomService(
			RoomService roomService) {
		this.roomService = roomService;
	}

	@RequestMapping(value = "/roomArrangement", method = RequestMethod.GET)
	public void roomArrangement(@RequestParam(value = "semesterId", required = true) int semesterId, Model model) {
		List<ClassSemester> JSONccs = roomArrangementService.getListClassesCoursesOfSemester(semesterId);
		HashMap<Room, List<Timetable>> JSONmapRoomTimetable = roomArrangementService.getListRoomsOfSemester(semesterId);
		
		ObjectMapper om = new ObjectMapper();
		StringWriter classesCoursesJSON = new StringWriter();
		StringWriter roomsJSON = new StringWriter();
		try {
			om.writeValue(classesCoursesJSON, JSONccs);
//			om.writeValue(roomsJSON, roomService.listRooms(false));
			om.writeValue(roomsJSON, JSONmapRoomTimetable);
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
		
		return;
	}
}
