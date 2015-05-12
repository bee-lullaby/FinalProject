package com.fpt.academic.model;

public class TeacherCourse {
	int teacher_course_id;
	int teacher_semester_id;
	int course_semester_id;

	/**
	 * 
	 */
	public TeacherCourse() {
		super();
	}

	/**
	 * @param teacher_course_id
	 * @param teacher_semester_id
	 * @param course_
	 */
	public TeacherCourse(int teacher_course_id, int teacher_semester_id,
			int course_semester_id) {
		super();
		this.teacher_course_id = teacher_course_id;
		this.teacher_semester_id = teacher_semester_id;
		this.course_semester_id = course_semester_id;
	}

	public int getTeacher_course_id() {
		return teacher_course_id;
	}

	public void setTeacher_course_id(int teacher_course_id) {
		this.teacher_course_id = teacher_course_id;
	}

	public int getTeacher_semester_id() {
		return teacher_semester_id;
	}

	public void setTeacher_semester_id(int teacher_semester_id) {
		this.teacher_semester_id = teacher_semester_id;
	}

	public int getCourse_semester_id() {
		return course_semester_id;
	}

	public void setCourse_semester_id(int course_semester_id) {
		this.course_semester_id = course_semester_id;
	}

}
