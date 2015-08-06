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

import vn.edu.fpt.timetabling.exception.SpecializedExistedException;
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

	@RequestMapping(value = "/staff/specialized", method = RequestMethod.GET)
	public String listSpecialized(HttpSession httpSession, Model model) {
		model.addAttribute("listSpecializeds", specializedService.listSpecializeds(false, false));
		model.addAttribute("listDetailSpecializeds", specializedService.listDetailSpecializeds(false, false));
		checkError(httpSession, model);
		notifySuccess(httpSession, model);
		return "specialized";
	}

	@RequestMapping(value = "/staff/specialized/updateSpecialized", method = RequestMethod.POST,
			params={"code", "name", "isDetail"})
	public String addSpecialized(@RequestParam String code, @RequestParam String name,
			@RequestParam int isDetail, HttpSession httpSession) throws SpecializedExistedException {
		Specialized specialized = specializedService.getSpecializedByCode(code, false, false);
		if(specialized != null) {
			throw new SpecializedExistedException();
		} else {
			specialized = new Specialized();
		}
		
		specialized.setCode(code);
		specialized.setName(name);
		if(isDetail == 1) {
			specialized.setDetailSpecialized(true);
		} else {
			specialized.setDetailSpecialized(false);
		}
		specializedService.addSpecialized(specialized);
		httpSession.setAttribute("success", "Add Specialized Successful!");
		return "redirect:/staff/specialized";
	}

	@RequestMapping(value = "/staff/specialized/addFromFile", method = RequestMethod.POST)
	public String addSpecializedFromFile(@RequestParam("file") MultipartFile file) {
		if (!file.isEmpty()) {
			File specializeds = new File("D:\\FU\\Do an tot nghiep\\Data\\ServerData\\" + file.getOriginalFilename());
			try {
				file.transferTo(specializeds);
				specializedService.addSpecializedFromFile(specializeds);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "redirect:/staff/specialized";
	}

	@RequestMapping(value = "/staff/specialized/deleteSpecialized", method = RequestMethod.GET, params={"specializedId"} )
	public String deleteSpecialized(@RequestParam int specializedId, HttpSession httpSession) {
		specializedService.deleteSpecialized(specializedId);
		httpSession.setAttribute("success", "Delete Specialized Successful!");
		return "redirect:/staff/specialized";
	}

	@ExceptionHandler(SpecializedExistedException.class)
	public String handleException(HttpSession httpSession, Exception e) {
		httpSession.setAttribute("error", "This Specialized's code existed! Please Try Again!");
		return "redirect:/staff/specialized";
	}
}
