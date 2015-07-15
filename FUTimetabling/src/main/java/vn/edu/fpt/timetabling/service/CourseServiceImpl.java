package vn.edu.fpt.timetabling.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.fpt.timetabling.dao.CourseDAO;
import vn.edu.fpt.timetabling.model.Course;

@Service
@Transactional(rollbackFor = Exception.class)
public class CourseServiceImpl implements CourseService {
	private CourseDAO courseDAO;
	@Autowired
	private DepartmentService departmentService;

	public void setCourseDAO(CourseDAO courseDAO) {
		this.courseDAO = courseDAO;
	}

	@Override
	public void addCourse(Course course) {
		courseDAO.addCourse(course);
	}

	@Override
	public void addCourseFromFile(File courses) throws IOException {
		FileInputStream file = new FileInputStream(courses);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.iterator();
		rowIterator.next();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			Course course = new Course();
			String code = row.getCell(0).getStringCellValue().trim();
			course.setDepartment(departmentService.getDepartmentByCode(code));
			course.setCode(row.getCell(1).getStringCellValue().trim());
			course.setName(row.getCell(2).getStringCellValue().trim());
			courseDAO.addCourse(course);
		}
		workbook.close();
		file.close();
	}

	@Override
	public void updateCourse(Course course) {
		courseDAO.updateCourse(course);
	}

	@Override
	public List<Course> listCourses() {
		return courseDAO.listCourses();
	}

	@Override
	public Course getCourseById(int courseId) {
		return courseDAO.getCourseById(courseId);
	}

	@Override
	public Course getCourseByCode(String code) {
		return courseDAO.getCourseByCode(code);
	}

	@Override
	public void deleteCourse(int courseId) {
		courseDAO.deleteCourse(courseId);
	}
}
