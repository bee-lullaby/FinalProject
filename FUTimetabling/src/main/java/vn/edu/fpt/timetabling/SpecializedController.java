package vn.edu.fpt.timetabling;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import vn.edu.fpt.timetabling.model.Specialized;
import vn.edu.fpt.timetabling.service.SpecializedService;

@Controller
public class SpecializedController {

	private SpecializedService specializedService;

	@Autowired(required = true)
	@Qualifier(value = "specializedService")
	public void setSpecializedService(SpecializedService specializedService) {
		this.specializedService = specializedService;
	}
	
	@RequestMapping(value = "/specializeds", method = RequestMethod.GET)
	public String listSpecialized(Model model) {
		model.addAttribute("specialized", new Specialized());
		model.addAttribute("listSpecializeds", specializedService.listSpecializeds());
		return "specialized";
	}

	// For add and update person both
	@RequestMapping(value = "/specialized/add", method = RequestMethod.POST)
	public String addDepartment(@ModelAttribute("specialized") Specialized specialized) {
		return "redirect:/specializeds";
	}

	// For add and update person both
	@RequestMapping(value = "/specialized/addFromFile", method = RequestMethod.POST)
	public String addDepartmentFromFile(@RequestParam("file") MultipartFile file) {
		if(!file.isEmpty()){
			File specializeds = new File("D:\\FU\\Do an tot nghiep\\Data\\ServerData\\" +file.getOriginalFilename());
			try {
				file.transferTo(specializeds);
				specializedService.addSpecializedFromFile(specializeds);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "redirect:/specializeds";
	}

	
	@RequestMapping("/specialized/delete/{specializedId}")
	public String deleteDepartment(@PathVariable("specializedId") int specializedId) {
		specializedService.deleteSpecialized(specializedId);
		return "redirect:/specializeds";
	}

	@RequestMapping("/specialized/edit/{specializedId}")
	public String editDepartment(@PathVariable("specializedId") int specializedId, Model model) {
		model.addAttribute("specialized", specializedService.getSpecializedById(specializedId));
		model.addAttribute("listSpecializeds", specializedService.listSpecializeds());
		return "department";
	}

}
