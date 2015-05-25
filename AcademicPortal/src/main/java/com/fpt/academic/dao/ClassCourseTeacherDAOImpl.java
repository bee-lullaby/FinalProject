package com.fpt.academic.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.fpt.academic.model.ClassCourse;
import com.fpt.academic.model.ClassCourseTeacher;
import com.fpt.academic.model.ClassSemester;
import com.fpt.academic.model.Course;
import com.fpt.academic.model.Teacher;
import com.fpt.academic.model.TeacherCourse;
import com.fpt.academic.model.TeacherSemester;

public class ClassCourseTeacherDAOImpl implements ClassCourseTeacherDAO {

	@Autowired
	private SemesterDAO semesterDAO;

	private JdbcTemplate jdbc;

	public ClassCourseTeacherDAOImpl(DataSource dataSource) {
		this.jdbc = new JdbcTemplate(dataSource);
	}

	@Override
	public void createClassCourseTeacher(int semester_id) {
		// TODO Auto-generated method stub
		List<ClassCourse> class_course = getClassCourse();
		List<TeacherCourse> teacher_course = getTeacherCourse();

		List<ClassSemester> class_semester = semesterDAO
				.getClassSemester(semester_id);
		List<TeacherSemester> teacher_semester = semesterDAO
				.getTeacherSemester(semester_id);

		List<Integer> course_semester_ids = new ArrayList<Integer>();
		List<Integer> teacher_semester_ids = new ArrayList<Integer>();

		Map<String, List<Integer>> teacher_course_list = new HashMap();

		for (TeacherCourse tc : teacher_course) {
			course_semester_ids.add(tc.getCourse_semester_id());
			teacher_semester_ids.add(tc.getTeacher_semester_id());
		}
		int count = 0;
		for (int course_semester_id : course_semester_ids) {
			if (teacher_course_list.containsKey(course_semester_id)) {
				List<Integer> list = teacher_course_list
						.get(course_semester_id);
				list.add(teacher_course.get(count++).getTeacher_semester_id());
				teacher_course_list.remove("course_semester_id");
				teacher_course_list.put(String.valueOf(course_semester_id),
						list);

			} else {
				List<Integer> list = new ArrayList<Integer>();
				list.add(teacher_course.get(count++).getTeacher_semester_id());
				teacher_course_list.put(String.valueOf(course_semester_id),
						list);
			}
		}

		String sql = "INSERT INTO class_course_teacher_semester(class_semester_id, course_semester_id, teacher_semester_id) VALUES (?, ?, ?)";

		for (ClassCourse cc : class_course) {
			List<Integer> teacher_semester_id = teacher_course_list.get(String
					.valueOf(cc.getCourse_semester_id()));
			Random rd = new Random();
			jdbc.update(sql, cc.getClass_semester_id(), cc
					.getCourse_semester_id(), teacher_semester_id.get(rd
					.nextInt(teacher_semester_id.size())));
		}
	}

	@Override
	public List<ClassCourse> getClassCourse() {
		// TODO Auto-generated method stub
		String sql = "select * from class_course_semester";
		List<ClassCourse> class_course = new ArrayList<ClassCourse>();
		List<Map<String, Object>> rows = jdbc.queryForList(sql);

		for (Map row : rows) {
			ClassCourse cc = new ClassCourse();
			cc.setClass_course_id((Integer) row.get("class_course_semester_id"));
			cc.setClass_semester_id((Integer) row.get("class_semester_id"));
			cc.setCourse_semester_id((Integer) row.get("course_semester_id"));
			class_course.add(cc);
		}
		return class_course;
	}

	@Override
	public List<TeacherCourse> getTeacherCourse() {
		// TODO Auto-generated method stub
		String sql = "select * from teacher_course_semester";
		List<TeacherCourse> teacher_course = new ArrayList<TeacherCourse>();
		List<Map<String, Object>> rows = jdbc.queryForList(sql);

		for (Map row : rows) {
			TeacherCourse tc = new TeacherCourse();
			tc.setTeacher_course_id((Integer) row
					.get("course_teacher_semester_id"));
			tc.setTeacher_semester_id((Integer) row.get("teacher_semester_id"));
			tc.setCourse_semester_id((Integer) row.get("course_semester_id"));
			teacher_course.add(tc);
		}
		return teacher_course;
	}

	@Override
	public List<ClassCourseTeacher> getClassCourseTeacherByClassSemester(int class_semester_id) {
		// TODO Auto-generated method stub
		String sql = "select * from class_course_teacher_semester where class_semester_id=?";
		List<ClassCourseTeacher> list = new ArrayList<ClassCourseTeacher>();
		List<Map<String, Object>> rows = jdbc.queryForList(sql, class_semester_id);

		for (Map row : rows) {
			ClassCourseTeacher cct = new ClassCourseTeacher();
			cct.setClass_course_teacher_id((Integer) row
					.get("class_course_teacher_semester_id"));
			cct.setClass_semester_id((Integer) row.get("class_semester_id"));
			cct.setTeacher_semester_id((Integer) row.get("teacher_semester_id"));
			cct.setCourse_semester_id((Integer) row.get("course_semester_id"));
			list.add(cct);
		}

		return list;
	}
	
	
	
}
