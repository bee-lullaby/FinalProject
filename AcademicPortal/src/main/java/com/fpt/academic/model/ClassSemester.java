package com.fpt.academic.model;

public class ClassSemester {
	int class_semester_id;
	int class_id;
	int semester_id;

	/**
	 * 
	 */
	public ClassSemester() {
		super();
	}

	/**
	 * @param class_semester_id
	 * @param class_id
	 * @param semester_id
	 */
	public ClassSemester(int class_semester_id, int class_id, int semester_id) {
		super();
		this.class_semester_id = class_semester_id;
		this.class_id = class_id;
		this.semester_id = semester_id;
	}

	public int getClass_semester_id() {
		return class_semester_id;
	}

	public void setClass_semester_id(int class_semester_id) {
		this.class_semester_id = class_semester_id;
	}

	public int getClass_id() {
		return class_id;
	}

	public void setClass_id(int class_id) {
		this.class_id = class_id;
	}

	public int getSemester_id() {
		return semester_id;
	}

	public void setSemester_id(int semester_id) {
		this.semester_id = semester_id;
	}

}
