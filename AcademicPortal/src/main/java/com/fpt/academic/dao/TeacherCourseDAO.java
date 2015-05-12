package com.fpt.academic.dao;

import java.io.File;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.fpt.academic.model.ClassFPT;
import com.fpt.academic.model.TeacherCourse;

public interface TeacherCourseDAO {
	public void addTeacherCourse(TeacherCourse teacher_course, int semester_id);
	public void addListTeacherCourse(File teacher_course, int semester_id);
}
