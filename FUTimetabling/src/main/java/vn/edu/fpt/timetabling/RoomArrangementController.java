package vn.edu.fpt.timetabling;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import vn.edu.fpt.timetabling.model.ClassSemester;
import vn.edu.fpt.timetabling.model.DataRoomArrangement;
import vn.edu.fpt.timetabling.model.Room;
import vn.edu.fpt.timetabling.service.ClassSemesterService;
import vn.edu.fpt.timetabling.service.RoomArrangementService;
import vn.edu.fpt.timetabling.service.ScheduleService;
import vn.edu.fpt.timetabling.service.SemesterService;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

@Controller
public class RoomArrangementController {
	@Autowired
	private RoomArrangementService roomArrangementService;
	@Autowired
	private ScheduleService scheduleService;
	@Autowired
	private SemesterService semesterService;
	@Autowired
	private ClassSemesterService classSemesterService;

	@RequestMapping(value = "/staff/roomArrangement", method = RequestMethod.GET, params = { "semesterId" })
	public String roomInit(@RequestParam int semesterId, Model model) {
		List<ClassSemester> list = scheduleService
				.listClassBySemester(semesterId);
		return "redirect:/staff/roomArrangement?semesterId=" + semesterId
				+ "&classSemesterId=" + list.get(0).getClassSemesterId();
	}

	@RequestMapping(value = "/staff/roomArrangement", method = RequestMethod.GET, params = {
			"semesterId", "classSemesterId" })
	public String roomArrangement(@RequestParam int semesterId,
			@RequestParam int classSemesterId, Model model) {
		List<ClassSemester> JSONccs = roomArrangementService
				.getListClassesCoursesOfSemester(semesterId, classSemesterId);
		List<Room> JSONdataRoomArrangement = roomArrangementService
				.getListRoomsOfSemester(semesterId);
		List<DataRoomArrangement> JSONDras = roomArrangementService
				.getDataRoomArrangement(semesterId);

		ObjectMapper om = new ObjectMapper();
		StringWriter classesCoursesJSON = new StringWriter();
		StringWriter roomsJSON = new StringWriter();
		StringWriter drasJSON = new StringWriter();
		try {
			// Write with JSON style
			om.writeValue(classesCoursesJSON, JSONccs);
			om.writeValue(roomsJSON, JSONdataRoomArrangement);
			om.writeValue(drasJSON, JSONDras);

			// Add to View
			model.addAttribute("classesCourses", classesCoursesJSON);
			model.addAttribute("rooms", roomsJSON);
			model.addAttribute("dataRoomArrangements", drasJSON);
			model.addAttribute("listSemesters",
					semesterService.listSemesters(false, false, false, false));
			model.addAttribute("listClassSemesters", classSemesterService
					.listClassSemestersBySemester(semesterId, false));
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
	public String updateTimetableRoom(
			@RequestParam(value = "dataToSet", required = true) String dataToSet,
			HttpServletRequest request) {

		ObjectMapper om = new ObjectMapper();
		TypeFactory typeFactory = om.getTypeFactory();
		List<ClassSemester> data;
		try {
			data = om.readValue(dataToSet, typeFactory.constructCollectionType(
					List.class, ClassSemester.class));
			roomArrangementService.updateTimetable(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
		// return "";
	}

}
