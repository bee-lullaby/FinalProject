package com.fpt.academic.model;

public class TeacherSemester {
	int teacher_semester_id;
	int teacher_id;
	int semester_id;

	/**
	 * 
	 */
	public TeacherSemester() {
		super();
	}

	/**
	 * @param teacher_semester_id
	 * @param teacher_id
	 * @param semester_id
	 */
	public TeacherSemester(int teacher_semester_id, int teacher_id,
			int semester_id) {
		super();
		this.teacher_semester_id = teacher_semester_id;
		this.teacher_id = teacher_id;
		this.semester_id = semester_id;
	}

	public int getTeacher_semester_id() {
		return teacher_semester_id;
	}

	public void setTeacher_semester_id(int teacher_semester_id) {
		this.teacher_semester_id = teacher_semester_id;
	}

	public int getTeacher_id() {
		return teacher_id;
	}

	public void setTeacher_id(int teacher_id) {
		this.teacher_id = teacher_id;
	}

	public int getSemester_id() {
		return semester_id;
	}

	public void setSemester_id(int semester_id) {
		this.semester_id = semester_id;
	}

}
