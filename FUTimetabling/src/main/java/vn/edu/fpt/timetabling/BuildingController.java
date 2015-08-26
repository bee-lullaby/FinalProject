package vn.edu.fpt.timetabling;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import vn.edu.fpt.timetabling.exception.BuildingExistedException;
import vn.edu.fpt.timetabling.model.Building;
import vn.edu.fpt.timetabling.service.BuildingService;

@Controller
public class BuildingController extends GeneralController {
	@Autowired
	private BuildingService buildingService;

	@RequestMapping(value = "/staff/building", method = RequestMethod.GET)
	public String buildingManagement(HttpSession httpSession, Model model) {
		model.addAttribute("listBuildings", buildingService.listBuildings());
		checkError(httpSession, model);
		notifySuccess(httpSession, model);
		return "building";
	}

	@RequestMapping(value = "/staff/building/updateBuilding", method = RequestMethod.POST, params = { "buildingId",
			"code" })
	public String updateBuilding(@RequestParam int buildingId, @RequestParam String code, HttpSession httpSession)
			throws BuildingExistedException {

		Building building = buildingService.getBuildingByCode(code);
		if (building != null) {
			throw new BuildingExistedException();
		}

		if (buildingId == -1)
			building = new Building();
		else
			building = buildingService.getBuildingById(buildingId);

		building.setCode(code);

		if (buildingId == -1) {
			buildingService.addBuilding(building);
			httpSession.setAttribute("success", "Add Building Successful!");
		} else {
			buildingService.addBuilding(building);
			httpSession.setAttribute("success", "Edit Building Successful!");
		}
		return "redirect:/staff/building";
	}

	@RequestMapping(value = "/staff/building/deleteBuilding", method = RequestMethod.GET, params = { "buildingId" })
	public String deleteBuilding(@RequestParam int buildingId, HttpSession httpSession, HttpServletRequest request) {
		buildingService.deleteBuilding(buildingId);
		httpSession.setAttribute("success", "Delete Building Successful!");
		return "redirect:/staff/building";
	}

	@ExceptionHandler(BuildingExistedException.class)
	public String handleException(HttpSession httpSession, Exception e) {
		httpSession.setAttribute("error", "This Building's code existed! Please try again!");
		return "redirect:/staff/building";
	}
}
