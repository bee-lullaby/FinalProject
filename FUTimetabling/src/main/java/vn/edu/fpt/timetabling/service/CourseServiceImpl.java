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
import vn.edu.fpt.timetabling.dao.DepartmentDAO;
import vn.edu.fpt.timetabling.model.Course;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseDAO courseDAO;
	
	@Autowired
	private DepartmentDAO departmentDAO;
	

	public void setDepartmentDAO(DepartmentDAO departmentDAO) {
		this.departmentDAO = departmentDAO;
	}
	
	public void setCourseDAO(CourseDAO courseDAO) {
		this.courseDAO = courseDAO;
	}
	
	@Override
	@Transactional
	public void addCourse(Course course) {
		courseDAO.addCourse(course);
	}
	
	@Override
	@Transactional
	public void addCourseFromFile(File courses) {
		try {
			FileInputStream file = new FileInputStream(courses);
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);

			Iterator<Row> rowIterator = sheet.iterator();

			rowIterator.next();

			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Course course = new Course();

				String code = row.getCell(0).getStringCellValue().trim();
				course.setDepartment(departmentDAO.getDepartmentByCode(code));
				course.setCode(row.getCell(1).getStringCellValue().trim());
				course.setName(row.getCell(2).getStringCellValue().trim());
				courseDAO.addCourse(course);

			}

			workbook.close();
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	@Transactional
	public void updateCourse(Course course) {
		courseDAO.updateCourse(course);
	}

	@Override
	@Transactional
	public List<Course> listCourses() {
		return courseDAO.listCourses();
	}

	@Override
	@Transactional
	public Course getCourseById(int courseId) {
		return courseDAO.getCourseById(courseId);
	}
	
	@Override
	@Transactional
	public Course getCourseByCode(String code) {
		return courseDAO.getCourseByCode(code);
	}

	
	@Override
	@Transactional
	public void deleteCourse(int courseId) {
		courseDAO.deleteCourse(courseId);
	}


}
