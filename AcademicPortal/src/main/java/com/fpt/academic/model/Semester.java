package com.fpt.academic.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Semester {
	int semester_id;
	String code;
	String name;
	int semester_year;
	Date start_date;
	Date end_date;
	/**
	 * 
	 */
	public Semester() {
		super();
	}
	/**
	 * @param semester_id
	 * @param code
	 * @param name
	 * @param semester_year
	 * @param start_date
	 * @param end_date
	 */
	public Semester(int semester_id, String code, String name,
			int semester_year, Date start_date, Date end_date) {
		super();
		this.semester_id = semester_id;
		this.code = code;
		this.name = name;
		this.semester_year = semester_year;
		this.start_date = start_date;
		this.end_date = end_date;
	}
	
	public int getSemester_id() {
		return semester_id;
	}
	
	public void setSemester_id(int semester_id) {
		this.semester_id = semester_id;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getSemester_year() {
		return semester_year;
	}
	
	public void setSemester_year(int semester_year) {
		this.semester_year = semester_year;
	}
	
	public Date getStart_date() {
		return start_date;
	}
	
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	
	public Date getEnd_date() {
		return end_date;
	}
	
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	
	public static String selectList(List<Semester> semesters, int semester_id) {

		
		
		StringBuilder show = new StringBuilder();
		
		
		for(Semester semester: semesters) {
			if(semester.getSemester_id() == semester_id) {
				show.insert(0, "<option value=\"" +semester.getSemester_id() +"\">" +semester.getName() +"</option>");
			} else {
				show.append("<option value=\"" +semester.getSemester_id() +"\">" +semester.getName() +"</option>");
				
			}
		}	
		return show.toString();
	
	}
}
