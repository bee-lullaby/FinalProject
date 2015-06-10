package vn.edu.fpt.timetabling;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import vn.edu.fpt.timetabling.model.CourseSemester;
import vn.edu.fpt.timetabling.service.CourseSemesterService;

@Controller
public class CourseSemesterController {
	private CourseSemesterService courseSemesterService;

	@Autowired(required = true)
	@Qualifier(value = "courseSemesterService")
	public void setCourseSemesterService(
			CourseSemesterService courseSemesterService) {
		this.courseSemesterService = courseSemesterService;
	}

	@RequestMapping(value = "/courseSemesters", method = RequestMethod.GET)
	public String listCourse(Model model) {
		model.addAttribute("courseSemester", new CourseSemester());
		model.addAttribute("listCourseSemesters",
				courseSemesterService.listCourseSemesters());
		return "courseSemester";
	}

	// For add and update person both
	@RequestMapping(value = "/courseSemester/addFromFile", method = RequestMethod.POST)
	public String addCourseFromFile(@RequestParam("file") MultipartFile file,
			@RequestParam("semesterId") int semesterId) {
		if (!file.isEmpty()) {
			File courseSemesters = new File(
					"D:\\FU\\Do an tot nghiep\\Data\\ServerData\\"
							+ file.getOriginalFilename());
			try {
				file.transferTo(courseSemesters);

				courseSemesterService.addCourseSemesterFromFile(
						courseSemesters, semesterId);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "redirect:/courseSemesters";
	}
}
