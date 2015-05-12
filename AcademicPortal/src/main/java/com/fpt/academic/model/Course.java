package com.fpt.academic.model;

public class Course {
	int course_id;
	String code;
	String name;
	int slots;
	String teachers;
	
	public Course() {
		super();
	}

	/**
	 * @param course_id
	 * @param code
	 * @param name
	 * @param slots
	 * @param teachers
	 */
	public Course(int course_id, String code, String name, int slots,
			String teachers) {
		super();
		this.course_id = course_id;
		this.code = code;
		this.name = name;
		this.slots = slots;
		this.teachers = teachers;
	}

	public int getCourse_id() {
		return course_id;
	}

	public void setCourse_id(int course_id) {
		this.course_id = course_id;
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

	public int getSlots() {
		return slots;
	}

	public void setSlots(int slots) {
		this.slots = slots;
	}

	public String getTeachers() {
		return teachers;
	}

	public void setTeachers(String teachers) {
		this.teachers = teachers;
	}
	
	
	
	
}
