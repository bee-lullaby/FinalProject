package com.fpt.academic.model;

public class CourseSemester {
	int course_semester_id;
	int course_id;
	int semester_id;

	/**
	 * 
	 */
	public CourseSemester() {
		super();
	}

	/**
	 * @param course_semester_id
	 * @param course_id
	 * @param semester_id
	 */
	public CourseSemester(int course_semester_id, int course_id, int semester_id) {
		super();
		this.course_semester_id = course_semester_id;
		this.course_id = course_id;
		this.semester_id = semester_id;
	}

	public int getCourse_semester_id() {
		return course_semester_id;
	}

	public void setCourse_semester_id(int course_semester_id) {
		this.course_semester_id = course_semester_id;
	}

	public int getCourse_id() {
		return course_id;
	}

	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}

	public int getSemester_id() {
		return semester_id;
	}

	public void setSemester_id(int semester_id) {
		this.semester_id = semester_id;
	}

}
