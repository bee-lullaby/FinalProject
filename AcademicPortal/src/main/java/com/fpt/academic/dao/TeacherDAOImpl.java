package com.fpt.academic.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.fpt.academic.model.Course;
import com.fpt.academic.model.Teacher;

public class TeacherDAOImpl implements TeacherDAO {

	private JdbcTemplate jdbc;
	
	public TeacherDAOImpl(DataSource dataSource) {
		this.jdbc = new JdbcTemplate(dataSource);
	}

	@Override
	public void addTeacher(Teacher teacher) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO teachers(account, name, email, account_type) VALUES (?, ?, ?, ?)";
		jdbc.update(sql, teacher.getAccount(), teacher.getName(), teacher.getEmail(), "admin");
	}

	@Override
	public void addListTeacher(File teachers) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO teachers(account, name, email, account_type) VALUES (?, ?, ?, ?)";
		try {
			FileInputStream file = new FileInputStream(teachers); 
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);
			
			Iterator<Row> rowIterator = sheet.iterator();
			
			rowIterator.next();
			while(rowIterator.hasNext()) {
				Row row = rowIterator.next();
				
				jdbc.update(sql, row.getCell(0).getStringCellValue().trim(), row.getCell(1).getStringCellValue().trim(), 
						row.getCell(2).getStringCellValue().trim(), row.getCell(3).getStringCellValue().trim());
			}
			
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updateTeacher(Teacher teacher) {
		// TODO Auto-generated method stub
		String sql = "UPDATE teachers SET name=?, email=?, account_type=? WHERE account=? ";
		jdbc.update(sql, teacher.getName().trim(), teacher.getEmail().trim(), teacher.getAccount_type().trim(), teacher.getAccount().trim());
	}

	@Override
	public void deleteTeacher(Teacher teacher) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM teachers WHERE account=?";
		jdbc.update(sql, teacher.getAccount());
	}

	@Override
	public List<Teacher> list() {
		// TODO Auto-generated method stub
		
		String sql = "SELECT * FROM teachers";
		List<Teacher> list = new ArrayList<Teacher>();
		List<Map<String, Object>> rows = jdbc.queryForList(sql);
		
		for(Map row: rows) {
			Teacher teacher = new Teacher();
			teacher.setTeacher_id((Integer) row.get("teacher_id"));
			teacher.setAccount((String) row.get("account"));
			teacher.setName((String) row.get("name"));
			teacher.setEmail((String) row.get("email"));
			teacher.setAccount_type((String) row.get("account_type"));
			list.add(teacher);
		}
		return list;
	}

	@Override
	public String display() {
		// TODO Auto-generated method stub
		StringBuilder listToShow = new StringBuilder("");
		
		int count = 1;
		for (Teacher teacher: list()) {
			listToShow.append("<tr> "
				+ "<td class=\"text-left\">" +count++ +"</th> "
				+ "<td class=\"text-left\">" +teacher.getAccount() +"</th>"
				+ "<td class=\"text-left\">" +teacher.getName() +"</th>"
				+ "<td class=\"text-left\">" +teacher.getEmail() +"</th>"
			    + "</tr>");
		}
		
		return listToShow.toString();
	}

	@Override
	public List<Teacher> listTeachersOfCourse(String code) {
		// TODO Auto-generated method stub
		
		String sql = "select t1.teacher_id as teacher_id,"
				+  "         t1.account as account,"
				+  "         t1.name as name,"
				+  "         t1.email as email,"
				+  "		 t1.account_type as account_type"
				+  "from teachers as t1,"
				+  "teacher_semester as t2,"
				+  "semesters as t3"
			    +  "where t1.teacher_id = t2.teacher_id"
			    +  "AND t2.semester_id = t3.semester_id";


		List<Teacher> list = new ArrayList<Teacher>();
		List<Map<String,Object>> rows = jdbc.queryForList(sql);
		
		for(Map row: rows) {
			Teacher teacher = new Teacher();
			teacher.setTeacher_id((Integer)row.get("teacher_id"));
			teacher.setAccount((String)row.get("account"));
			teacher.setName((String)row.get("name"));
			teacher.setEmail((String)row.get("email"));
			teacher.setAccount_type((String)row.get("account_type"));
			list.add(teacher);
		}
		
		
		return list;
	}

	@Override
	public String displayTeachersOfCourse(String code) {
		// TODO Auto-generated method stub
		
		StringBuilder show = new StringBuilder();
		
		for(Teacher teacher: list()) {
			show.append("<option>" +teacher.getAccount() +"</option>");
		}
		
		return show.toString();
	}

	@Override
	public Teacher getTeacher(int teacher_id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM teachers WHERE teacher_id=?";
		List<Teacher> list = new ArrayList<Teacher>();
		List<Map<String,Object>> rows = jdbc.queryForList(sql, teacher_id);
		
		for(Map row: rows) {
			Teacher teacher = new Teacher();
			teacher.setTeacher_id((Integer)row.get("teacher_id"));
			teacher.setAccount((String)row.get("account"));
			teacher.setName((String)row.get("name"));
			teacher.setEmail((String)row.get("email"));
			teacher.setAccount_type((String)row.get("account_type"));
			list.add(teacher);
		}
		
		return list.get(0);
	}
}
