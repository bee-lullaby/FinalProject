package vn.edu.fpt.timetabling;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import vn.edu.fpt.timetabling.model.Room;
import vn.edu.fpt.timetabling.model.Timetable;
import vn.edu.fpt.timetabling.service.RoomPerDayService;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
public class RoomPerDayController {
	private RoomPerDayService roomPerDayService;
	
	@Autowired(required = true)
	@Qualifier(value = "roomPerDayService")
	public void setRoomPerDayService(
			RoomPerDayService roomPerDayService) {
		this.roomPerDayService = roomPerDayService;
	}
	
	@RequestMapping(value = "/staff/roomPerDay", method = RequestMethod.GET)
	public String roomPerDay() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		String d = cal.get(Calendar.YEAR) +"-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE);
		return "redirect:/staff/roomPerDay?date=" +d;
	}
	
	
	@RequestMapping(value = "/staff/roomPerDay", method = RequestMethod.GET, params = { "date" })
	public String roomPerDayByDay(@RequestParam String date, Model model) {
		List<Timetable> timetableData = roomPerDayService.getListTimetableOfDay(date);
		HashMap<String, List<Room>> roomsData = roomPerDayService.getListRoom();
		
		ObjectMapper om = new ObjectMapper();
		StringWriter timetableJSON = new StringWriter();
		StringWriter roomsJSON = new StringWriter();
		
		try {
			om.writeValue(timetableJSON, timetableData);
			om.writeValue(roomsJSON, roomsData);
			
			model.addAttribute("timetableData", timetableJSON);
			model.addAttribute("roomsData", roomsJSON);
			
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
		return "roomPerDay";
	}
	
//	@RequestMapping(value = "/staff/roomPerDay/setRoom", method = RequestMethod.POST)
//	public String test(Model model, HttpServletRequest request) {
//		String referer = request.getHeader("Referer");
//		return "redirect:" + referer;
//	}
	
	@RequestMapping(value = "/staff/roomPerDay/setRoom", method = RequestMethod.POST, params = { "timetableId", "roomId"})
	public String roomPerDaySetRoom(@RequestParam int timetableId, @RequestParam int roomId, HttpServletRequest request) {
		roomPerDayService.saveRoom(timetableId, roomId);
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}
}
