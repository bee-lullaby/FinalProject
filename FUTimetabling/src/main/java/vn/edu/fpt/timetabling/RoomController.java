package vn.edu.fpt.timetabling;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import vn.edu.fpt.timetabling.model.Room;
import vn.edu.fpt.timetabling.service.RoomService;

@Controller
public class RoomController extends GeneralController {
	private RoomService roomService;

	@Autowired(required = true)
	@Qualifier(value = "roomService")
	public void setRoomService(RoomService roomService) {
		this.roomService = roomService;
	}

	@RequestMapping(value = "/rooms", method = RequestMethod.GET)
	public String listRoom(HttpSession httpSession, Model model) {
		model.addAttribute("room", new Room());
		model.addAttribute("listRooms", roomService.listRooms(true));
		checkError(httpSession, model);
		return "room";
	}

	@RequestMapping(value = "/rooms/addFromFile", method = RequestMethod.POST)
	public String addCourseFromFile(@RequestParam("file") MultipartFile file) {
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
		return "redirect:/rooms";
	}

	@ExceptionHandler(Exception.class)
	public String handleException(HttpSession httpSession, Exception e) {
		httpSession.setAttribute("error", "Error, please try again.");
		return "redirect:/rooms";
	}
}
