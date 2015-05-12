package com.fpt.academic.model;

public class ClassCourse {
	int class_semester_id;
	int course_semester_id;
	int class_course_id;

	/**
	 * 
	 */
	public ClassCourse() {
		super();
	}

	/**
	 * @param class_semester_id
	 * @param course_semester_id
	 * @param class_course_id
	 */
	public ClassCourse(int class_semester_id, int course_semester_id,
			int class_course_id) {
		super();
		this.class_semester_id = class_semester_id;
		this.course_semester_id = course_semester_id;
		this.class_course_id = class_course_id;
	}

	public int getClass_semester_id() {
		return class_semester_id;
	}

	public void setClass_semester_id(int class_semester_id) {
		this.class_semester_id = class_semester_id;
	}

	public int getCourse_semester_id() {
		return course_semester_id;
	}

	public void setCourse_semester_id(int course_semester_id) {
		this.course_semester_id = course_semester_id;
	}

	public int getClass_course_id() {
		return class_course_id;
	}

	public void setClass_course_id(int class_course_id) {
		this.class_course_id = class_course_id;
	}

}
