package vn.edu.fpt.timetabling;

import java.io.File;
import java.io.IOException;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import vn.edu.fpt.timetabling.model.Specialized;
import vn.edu.fpt.timetabling.service.SpecializedService;

@Controller
public class SpecializedController extends GeneralController {
	private SpecializedService specializedService;

	@Autowired(required = true)
	@Qualifier(value = "specializedService")
	public void setSpecializedService(SpecializedService specializedService) {
		this.specializedService = specializedService;
	}

	@RequestMapping(value = "/staff/specializeds", method = RequestMethod.GET)
	public String listSpecialized(HttpSession httpSession, Model model) {
		model.addAttribute("specialized", new Specialized());
		model.addAttribute("listSpecializeds", specializedService.listSpecializeds(true, false));
		checkError(httpSession, model);
		return "specialized";
	}

	// For add and update person both
	@RequestMapping(value = "/staff/specialized/add", method = RequestMethod.POST)
	public String addSpecialized(@ModelAttribute("specialized") Specialized specialized) {
		if (specialized.getSpecializedId() == 0) {
			specializedService.addSpecialized(specialized);
		} else {
			specializedService.updateSpecialized(specialized);
		}
		return "redirect:/staff/specializeds";
	}

	// For add and update person both
	@RequestMapping(value = "/staff/specialized/addFromFile", method = RequestMethod.POST)
	public String addSpecializedFromFile(@RequestParam("file") MultipartFile file) {
		if (!file.isEmpty()) {
			File specializeds = new File("D:\\FU\\Do an tot nghiep\\Data\\ServerData\\" + file.getOriginalFilename());
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
		return "redirect:/staff/specializeds";
	}

	@RequestMapping("/staff/specialized/delete/{specializedId}")
	public String deleteSpecialized(@PathVariable("specializedId") int specializedId) {
		specializedService.deleteSpecialized(specializedId);
		return "redirect:/staff/specializeds";
	}

	@RequestMapping("/staff/specialized/edit/{specializedId}")
	public String editSpecialized(@PathVariable("specializedId") int specializedId, Model model) {
		model.addAttribute("specialized", specializedService.getSpecializedById(specializedId, true, false));
		model.addAttribute("listSpecializeds", specializedService.listSpecializeds(true, false));
		return "specialized";
	}

	@ExceptionHandler(Exception.class)
	public String handleException(HttpSession httpSession, Exception e) {
		httpSession.setAttribute("error", "Error, please try again.");
		return "redirect:/staff/specializeds";
	}
}
