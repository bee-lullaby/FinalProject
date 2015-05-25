package com.fpt.academic.model;

public class ClassCourseTeacher {
	int class_course_teacher_id;
	int class_semester_id;
	int course_semester_id;
	int teacher_semester_id;

	/**
	 * 
	 */
	public ClassCourseTeacher() {
		super();
	}

	/**
	 * @param class_course_teacher_id
	 * @param class_semester_id
	 * @param course_semester_id
	 * @param teacher_semester_id
	 */
	public ClassCourseTeacher(int class_course_teacher_id,
			int class_semester_id, int course_semester_id,
			int teacher_semester_id) {
		super();
		this.class_course_teacher_id = class_course_teacher_id;
		this.class_semester_id = class_semester_id;
		this.course_semester_id = course_semester_id;
		this.teacher_semester_id = teacher_semester_id;
	}

	public int getClass_course_teacher_id() {
		return class_course_teacher_id;
	}

	public void setClass_course_teacher_id(int class_course_teacher_id) {
		this.class_course_teacher_id = class_course_teacher_id;
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

	public int getTeacher_semester_id() {
		return teacher_semester_id;
	}

	public void setTeacher_semester_id(int teacher_semester_id) {
		this.teacher_semester_id = teacher_semester_id;
	}

}
