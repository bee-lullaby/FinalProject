package com.fpt.academic.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.jdbc.core.JdbcTemplate;

import com.fpt.academic.model.ClassFPT;
import com.fpt.academic.model.Course;
import com.fpt.academic.model.Teacher;

public class CourseDAOImpl implements CourseDAO {
	
	private JdbcTemplate jdbc;
	
	public CourseDAOImpl(DataSource dataSource) {
		this.jdbc = new JdbcTemplate(dataSource);
	}
	
	
	@Override
	public void addCourse(Course course) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO courses(code, name, slots) VALUES (?, ?, ?)";
		jdbc.update(sql, course.getCode(), course.getName(), course.getSlots());
	}

	@Override
	public void addListCourse(File courses) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO courses(code, name, slots) VALUES (?, ?, ?)";
		try {
			FileInputStream file = new FileInputStream(courses); 
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);
			
			Iterator<Row> rowIterator = sheet.iterator();
			
			
			rowIterator.next();
			while(rowIterator.hasNext()) {
				Row row = rowIterator.next();
				jdbc.update(sql, row.getCell(0).getStringCellValue().trim(), row.getCell(1).getStringCellValue().trim(), 
						row.getCell(2).getNumericCellValue());
			}
			
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updateCourse(Course course) {
		// TODO Auto-generated method stub
//		String sql = "UPDATE courses SET code=?, name=?, account_type=? WHERE course_id=? ";
//		jdbc.update(sql, course.getCode(), course.getName(), course.getCourse_id());
//	
	}

	@Override
	public void deleteCourse(Course course) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM courses WHERE code=?";
		jdbc.update(sql, course.getCode());
	}
	
	@Override
	public List<Course> list() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM courses";
		List<Course> list = new ArrayList<Course>();
		List<Map<String,Object>> rows = jdbc.queryForList(sql);
		
		for(Map row: rows) {
			Course course = new Course();
			course.setCourse_id((Integer) row.get("course_id"));
			course.setCode((String) row.get("code"));
			course.setName((String) row.get("name"));
			course.setSlots((Integer) row.get("slots"));
			list.add(course);
		}
		
		
		return list;
	}

	@Override
	public String display() {
		// TODO Auto-generated method stub
		
		StringBuilder listToShow = new StringBuilder("");
		
		int count = 1;
		for (Course course: list()) {
			listToShow.append("<tr> "
				+ "<td class=\"text-left\">" +count++ +"</td> "
				+ "<td class=\"text-left\">" +course.getCode() +"</td>"
				+ "<td class=\"text-left\">" +course.getName() +"</td>"
				+ "<td class=\"text-left\">" +course.getSlots() +"</td>"
				+ "<td class=\"text-left\">" +course.getSlots() +"</td>"
			    + "</tr>");
		}
		
		return listToShow.toString();
	}
	
	
	@Override
	public List<Course> listCourseOfClass(String code) {
		// TODO Auto-generated method stub
		String sql = "select t1.course_id as course_id, t1.code as code, t1.name as name "
								+  "from courses as t1, "
								+       "course_semester as t2, "
								+       "semesters as t3 "
							    +  "where t1.course_id = t2.course_id "
							    +"   AND  t2.semester_id = t3.semester_id";
		
		
		List<Course> list = new ArrayList<Course>();
		List<Map<String,Object>> rows = jdbc.queryForList(sql);
		for(Map row: rows) {
			Course course = new Course();
			course.setCourse_id((Integer)row.get("course_id"));
			course.setCode((String)row.get("code"));
			course.setName((String)row.get("name"));
			list.add(course);
		}
		
		
		return list;
	}

	@Override
	public String displayCourseOfClass(String code) {
		StringBuilder show = new StringBuilder();
		
		for(Course course: listCourseOfClass(code)) {
			show.append("<option>" +course.getCode() +"</option>");
		}
		
		return show.toString();
	}
}
