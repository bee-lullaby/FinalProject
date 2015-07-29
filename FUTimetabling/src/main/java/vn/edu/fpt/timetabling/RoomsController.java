package vn.edu.fpt.timetabling;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import vn.edu.fpt.timetabling.model.Room;
import vn.edu.fpt.timetabling.service.CourseService;
import vn.edu.fpt.timetabling.service.RoomService;

@Controller
public class RoomsController extends GeneralController {
	private RoomService roomService;
	
	private CourseService courseService;
	
	@Autowired(required = true)
	@Qualifier(value = "roomService")
	public void setRoomService(RoomService roomService) {
		this.roomService = roomService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "courseService")
	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}
	@RequestMapping(value = "/staff/rooms", method = RequestMethod.GET)
	public String roomsInit(HttpSession httpSession, Model model) {
		model.addAttribute("listRooms", roomService.listRooms(false));
		model.addAttribute("listCourse", courseService.listCourses());
		checkError(httpSession, model);
		return "rooms";
	}
	
	@RequestMapping(value = "/staff/rooms/addFromFile", method = RequestMethod.POST)
	public String addRoomsFromFile(@RequestParam("file") MultipartFile file, 
				HttpSession httpSession, Model model, HttpServletRequest request) {
		if (!file.isEmpty()) {

			File rooms = new File("D:\\FU\\Do an tot nghiep\\Data\\ServerData\\" + file.getOriginalFilename());
			try {
				file.transferTo(rooms);
				roomService.addRoomFromFile(rooms);
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
	
	
	@RequestMapping(value="/staff/rooms/updateRoom", method = RequestMethod.POST, 
			params = {"roomId", "code", "capacity", "courses"})
	public String updateRooms(@RequestParam int roomId, @RequestParam String code, 
			@RequestParam int capacity, @RequestParam String[] courses, 
			HttpSession httpSession, Model model, HttpServletRequest request) {
		Room room = new Room();
		room.setCode(code);
		room.setCapacity(capacity);
		StringBuilder sb = new StringBuilder("");
		for(String course : courses) {
			String courseCode = courseService.getCourseById(Integer.parseInt(course)).getCode();
			sb.append(courseCode +", ");
		}
		if(sb.toString().length() > 1) {
			room.setCourses(sb.toString());
		}
		
		if(roomId == -1) {
			roomService.addRoom(room);
		} else {
			room.setRoomId(roomId);
			roomService.updateRoom(room);
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}
	
	@RequestMapping(value="/staff/rooms/delete/{roomId}")
	public String deleteRoom(@PathVariable("roomId") int roomId, HttpServletRequest request) {
		roomService.deleteRoom(roomId);
		
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}
}
