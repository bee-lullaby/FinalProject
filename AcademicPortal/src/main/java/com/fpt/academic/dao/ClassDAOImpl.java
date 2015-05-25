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

public class ClassDAOImpl implements ClassDAO{
	
	private JdbcTemplate jdbc;
	
	public ClassDAOImpl(DataSource dataSource) {
		this.jdbc = new JdbcTemplate(dataSource);
	}
	
	
	@Override
	public void addClass(ClassFPT classfpt) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO classes(code) VALUES (?)";
		jdbc.update(sql, classfpt.getCode());
	}

	@Override
	public void addListClass(File classfpts) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO classes(code) VALUES (?)";
		try {
			FileInputStream file = new FileInputStream(classfpts); 
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);
			
			Iterator<Row> rowIterator = sheet.iterator();
			
			rowIterator.next();
			while(rowIterator.hasNext()) {
				Row row = rowIterator.next();
				jdbc.update(sql,  row.getCell(1).getStringCellValue().trim());
			}
			
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updateClass(ClassFPT classfpt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteClass(ClassFPT classfpt) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM classes WHERE code=?";
		jdbc.update(sql, classfpt.getCode());
	}

	@Override
	public List<ClassFPT> list() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM classes";
		List<ClassFPT> list = new ArrayList<ClassFPT>();
		List<Map<String,Object>> rows = jdbc.queryForList(sql);
		
		for(Map row: rows) {
			ClassFPT classfpt = new ClassFPT();
			classfpt.setClass_id((Integer)row.get("class_id"));
			classfpt.setCode((String)row.get("code"));
			list.add(classfpt);
		}
		
		
		return list;
	}

	@Override
	public String display() {
		// TODO Auto-generated method stub
		StringBuilder listToShow = new StringBuilder("");
		
		int count = 1;
		for (ClassFPT classfpt: list()) {
			listToShow.append("<tr> "
				+ "<td class=\"text-left\">" +count++ +"</td> "
				+ "<td class=\"text-left\">" +classfpt.getCode() +"</td>"
				+ "<td class=\"text-left\">" +classfpt.getCode() +"</td>"
			    + "</tr>");
		}
		
		return listToShow.toString();
	}
	

	@Override
	public List<ClassFPT> listClassSemester(int semester_id) {
		// TODO Auto-generated method stub
		String sql = "select t1.class_id as class_id, t1.code as code from classes as t1, "
					 		 +     "class_semester as t2, "
							 +	   "semesters as t3 "
							 +" where t1.class_id = t2.class_id "
							 +"   AND t2.semester_id = t3.semester_id"
							 +"   AND t2.semester_id = ?";
		
		
		List<ClassFPT> list = new ArrayList<ClassFPT>();
		List<Map<String,Object>> rows = jdbc.queryForList(sql, semester_id);
		for(Map row: rows) {
			ClassFPT classfpt = new ClassFPT();
			classfpt.setClass_id((Integer)row.get("class_id"));
			classfpt.setCode((String)row.get("code"));
			list.add(classfpt);
		}
		
		
		return list;
	}


	@Override
	public String displaySemester(int semester_id) {
		StringBuilder show = new StringBuilder();
		
		int count = 0;
		for(ClassFPT classfpt: listClassSemester(semester_id)) {
			if(count % 7 == 0) {
				show.append("<tr>");
				count = 0;
			} 
			show.append("<td><a class=\"list_class_semester\" href=\"\\academic\\ccts_class_management?code=" +classfpt.getCode() +"\">" +classfpt.getCode() +"</a></td>");
			count++;
			if(count == 7) {
				show.append("</tr>");
			}
		}
		
		return show.toString();
	}


	@Override
	public ClassFPT getClassID(String class_code) {
		// TODO Auto-generated method stub
		
		String sql = "SELECT * FROM classes WHERE code=?";
		List<ClassFPT> list = new ArrayList<ClassFPT>();
		List<Map<String,Object>> rows = jdbc.queryForList(sql, class_code);
		
		for(Map row: rows) {
			ClassFPT classfpt = new ClassFPT();
			classfpt.setClass_id((Integer)row.get("class_id"));
			classfpt.setCode((String)row.get("code"));
			list.add(classfpt);
		}
		
		
		return list.get(0);
	}
	
	
	
}
