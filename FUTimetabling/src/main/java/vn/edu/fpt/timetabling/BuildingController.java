package vn.edu.fpt.timetabling;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.edu.fpt.timetabling.model.Building;
import vn.edu.fpt.timetabling.service.BuildingService;

@Controller
public class BuildingController extends GeneralController {
	private BuildingService buildingService;

	@Autowired(required = true)
	@Qualifier(value = "buildingService")
	public void setBuildingService(BuildingService buildingService) {
		this.buildingService = buildingService;
	}

	@RequestMapping(value = "/staff/buildings", method = RequestMethod.GET)
	public String listSpecialized(HttpSession httpSession, Model model) {
		model.addAttribute("building", new Building());
		model.addAttribute("buildings", buildingService.listBuildings());
		checkError(httpSession, model);
		return "building";
	}

	@RequestMapping(value = "/staff/building/add", method = RequestMethod.POST)
	public String addSpecialized(@ModelAttribute("building") Building building) {
		if (building.getBuildingId() == 0) {
			buildingService.addBuilding(building);
		} else {
			buildingService.updateBuilding(building);
		}
		return "redirect:/staff/buildings";
	}

	@RequestMapping("/staff/building/delete/{buildingId}")
	public String deleteSpecialized(@PathVariable("buildingId") int buildingId) {
		buildingService.deleteBuilding(buildingId);
		return "redirect:/staff/buildings";
	}

	@RequestMapping("/staff/building/edit/{buildingId}")
	public String editSpecialized(@PathVariable("buildingId") int buildingId, Model model) {
		model.addAttribute("building", buildingService.getBuildingById(buildingId));
		model.addAttribute("buildings", buildingService.listBuildings());
		return "building";
	}

	@ExceptionHandler(Exception.class)
	public String handleException(HttpSession httpSession, Exception e) {
		httpSession.setAttribute("error", "Error, please try again.");
		return "redirect:/staff/buildings";
	}
}
