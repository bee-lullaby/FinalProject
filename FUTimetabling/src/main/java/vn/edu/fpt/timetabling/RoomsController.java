package vn.edu.fpt.timetabling;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import vn.edu.fpt.timetabling.exception.RoomExistedException;
import vn.edu.fpt.timetabling.model.Room;
import vn.edu.fpt.timetabling.service.BuildingService;
import vn.edu.fpt.timetabling.service.CourseService;
import vn.edu.fpt.timetabling.service.RoomService;

@Controller
public class RoomsController extends GeneralController {
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private BuildingService buildingService;
//	
//	@Autowired(required = true)
//	@Qualifier(value = "roomService")
//	public void setRoomService(RoomService roomService) {
//		this.roomService = roomService;
//	}
//	
//	@Autowired(required = true)
//	@Qualifier(value = "courseService")
//	public void setCourseService(CourseService courseService) {
//		this.courseService = courseService;
//	}
	
	@RequestMapping(value = "/staff/rooms", method = RequestMethod.GET)
	public String roomsInit(HttpSession httpSession, Model model) {
		model.addAttribute("listRooms", roomService.listRooms(false));
		model.addAttribute("listCourses", courseService.listCourses());
		model.addAttribute("listBuildings", buildingService.listBuildings());
		checkError(httpSession, model);
		notifySuccess(httpSession, model);
		return "rooms";
	}
	
	@RequestMapping(value = "/staff/rooms/addFromFile", method = RequestMethod.POST)
	public String addRoomsFromFile(@RequestParam("file") MultipartFile file, 
				HttpSession httpSession, Model model) {
		if (!file.isEmpty()) {

			File rooms = new File("rooms.xlxs");
			try {
				file.transferTo(rooms);
				// handle building not existed.
				List<String> check = roomService.addRoomFromFile(rooms);
				if(check.isEmpty()) {
					httpSession.setAttribute("success", "Add List Of Rooms Successful!");
				} else {
					String text = "Building " +check.get(0);
					for(int i = 1; i < check.size(); i++) {
						text += ", " +check.get(i);
					}
					if(check.size() == 1) text += " is ";
					else text += " are ";
					text += "not existed! Please insert building first then try again!";
					httpSession.setAttribute("error", text);
				}
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "redirect:/staff/rooms";
	}
	
	
	@RequestMapping(value="/staff/rooms/updateRoom", method = RequestMethod.POST, 
			params = {"roomId", "code", "capacity", "buildingId"})
	public String updateRooms(@RequestParam int roomId, @RequestParam String code, 
			@RequestParam int capacity, @RequestParam int buildingId, 
			@RequestParam(value = "courses", required = false) String[] courses, 
			HttpSession httpSession, Model model) throws RoomExistedException {
		
		Room room = roomService.getRoomByCode(code, false);
		if((roomId == -1  && room != null) 
				|| (room != null && roomId != -1 && roomId != room.getRoomId())) {
			throw new RoomExistedException();
		}
			
		if(roomId == -1) {
			room = new Room();
		}
		
		room.setCode(code);
		room.setCapacity(capacity);
		room.setBuilding(buildingService.getBuildingById(buildingId));
		StringBuilder sb = new StringBuilder("");
		if(courses != null) {
			for(String course : courses) {
				String courseCode = courseService.getCourseById(Integer.parseInt(course)).getCode();
				sb.append(courseCode +", ");
			}
		}
		if(sb.toString().length() > 1) {
			room.setCourses(sb.toString());
		}
		
		if(roomId == -1) {
			roomService.addRoom(room);
			httpSession.setAttribute("success", "Add Room Successful!");
		} else {
			room.setRoomId(roomId);
			roomService.updateRoom(room);
			httpSession.setAttribute("success", "Edit Room Successful!");
		}
		return "redirect:/staff/rooms";
	}
	
	@RequestMapping(value="/staff/rooms/deleteRoom", method = RequestMethod.GET, params = {"roomId"})
	public String deleteRoom(@RequestParam int roomId, HttpSession httpSession) {
		roomService.deleteRoom(roomId);
		httpSession.setAttribute("success", "Delete Room Successful!");
		return "redirect:/staff/rooms";
	}
	
	@ExceptionHandler(RoomExistedException.class)
	public String handleRoomExistedException(HttpSession httpSession, Exception e) {
		httpSession.setAttribute("error",
				"This Room's code existed! Please try again!");
		return "redirect:/staff/rooms";
	}
}
