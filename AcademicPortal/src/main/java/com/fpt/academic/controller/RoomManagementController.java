package com.fpt.academic.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fpt.academic.dao.RoomDAO;
import com.fpt.academic.model.Room;


@Controller
public class RoomManagementController {
	

	@Autowired
	private RoomDAO roomDAO;
	
	@RequestMapping(value="/room_management", method = RequestMethod.GET)
	public void roomManagement(Model model) {
		model.addAttribute("room", new Room());
		model.addAttribute("data", roomDAO.display());
		return;
	}
	
	@RequestMapping(value= "/room_management/addRoom", method = RequestMethod.POST)
	public ModelAndView  addRoom(@ModelAttribute Room room) {
		roomDAO.addRoom(room);
		return new ModelAndView("redirect:/room_management");
	}
	
	@RequestMapping(value= "/room_management/addfile", method = RequestMethod.POST)
	public ModelAndView  addRoomFromFile(@RequestParam("file") MultipartFile file) {
		if(!file.isEmpty()){
			File rooms = new File("D:\\FU\\Do an tot nghiep\\ServerData\\" +file.getOriginalFilename());
			try {
				file.transferTo(rooms);
				roomDAO.addListRoom(rooms);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return  new ModelAndView("redirect:/room_management");
	}
	
	@RequestMapping(value= "/room_management/updateRoom", method = RequestMethod.POST, params = { "save" })
	public ModelAndView saveRoom(@RequestParam String save, @ModelAttribute Room room) {
		roomDAO.updateRoom(room);
		return new ModelAndView("redirect:/room_management");
	}
	
	@RequestMapping(value= "/room_management/updateRoom", method = RequestMethod.POST, params = { "delete" })
	public ModelAndView delRoom(@RequestParam String delete, @ModelAttribute Room room) {
		roomDAO.deleteRoom(room);
		return new ModelAndView("redirect:/room_management");
	}
}
