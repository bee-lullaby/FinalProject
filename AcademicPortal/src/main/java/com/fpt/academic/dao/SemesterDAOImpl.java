package com.fpt.academic.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.fpt.academic.model.ClassFPT;
import com.fpt.academic.model.ClassSemester;
import com.fpt.academic.model.Course;
import com.fpt.academic.model.CourseSemester;
import com.fpt.academic.model.Semester;
import com.fpt.academic.model.Teacher;
import com.fpt.academic.model.TeacherSemester;

public class SemesterDAOImpl implements SemesterDAO {

	private JdbcTemplate jdbc;

	@Autowired
	private CourseDAO courseDAO;

	@Autowired
	private TeacherDAO teacherDAO;

	@Autowired
	private ClassDAO classDAO;

	public SemesterDAOImpl(DataSource dataSource) {
		this.jdbc = new JdbcTemplate(dataSource);
	}

	@Override
	public void addSemester(Semester semester) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO semesters(code, name, semester_year, start_date, end_date) VALUES (?, ?, ?, ?, ?)";

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String start_date = sdf.format(semester.getStart_date());
		String end_date = sdf.format(semester.getEnd_date());
		jdbc.update(sql, semester.getCode(), semester.getName(),
				semester.getSemester_year(), start_date, end_date);

		String get_semester_id = "SELECT semester_id FROM semesters WHERE code = ?";
		int semester_id = jdbc.queryForInt(get_semester_id, semester.getCode());

		String add_course_semester = "INSERT INTO course_semester(course_id, semester_id) VALUES (?, ?)";
		List<Course> courses = courseDAO.list();
		for (Course course : courses) {
			jdbc.update(add_course_semester, course.getCourse_id(), semester_id);
		}

		String add_teacher_semester = "INSERT INTO teacher_semester(teacher_id, semester_id) VALUES (?, ?)";
		List<Teacher> teachers = teacherDAO.list();
		for (Teacher teacher : teachers) {
			jdbc.update(add_teacher_semester, teacher.getTeacher_id(),
					semester_id);
		}

		String add_class_semester = "INSERT INTO class_semester(class_id, semester_id, students) VALUES (?, ?, ?)";
		List<ClassFPT> classes = classDAO.list();
		for (ClassFPT classFPT : classes) {
			jdbc.update(add_class_semester, classFPT.getClass_id(),
					semester_id, "30");
		}
	}

	@Override
	public void updateSemester(Semester semester) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteSemester(Semester semester) {
		// TODO Auto-generated method stub
		String get_semester_id = "SELECT semester_id FROM semesters WHERE code = ?";
		int semester_id = jdbc.queryForInt(get_semester_id, semester.getCode());

		String del_course_semester = "DELETE FROM course_semester WHERE semester_id=?";
		jdbc.update(del_course_semester, semester_id);
		String del_teacher_semester = "DELETE FROM teacher_semester WHERE semester_id=?";
		jdbc.update(del_teacher_semester, semester_id);
		String del_class_semester = "DELETE FROM class_semester WHERE semester_id=?";
		jdbc.update(del_class_semester, semester_id);

		String del_semester = "DELETE FROM semesters WHERE semester_id=?";
		jdbc.update(del_semester, semester_id);

	}

	@Override
	public List<Semester> list() {
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM semesters ORDER BY start_date DESC";
		List<Semester> list = new ArrayList<Semester>();
		List<Map<String, Object>> rows = jdbc.queryForList(sql);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		for (Map row : rows) {
			Semester semester = new Semester();
			semester.setSemester_id((Integer) row.get("semester_id"));
			semester.setCode((String) row.get("code"));
			semester.setName((String) row.get("name"));
			semester.setSemester_year((Integer) row.get("semester_year"));

			java.sql.Date sqlStartDate = (java.sql.Date) row.get("start_date");
			java.sql.Date sqlEndDate = (java.sql.Date) row.get("end_date");

			try {
				semester.setStart_date(sdf.parse(sdf.format(sqlStartDate)));
				semester.setEnd_date(sdf.parse(sdf.format(sqlEndDate)));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			list.add(semester);
		}
		return list;
	}

	@Override
	public String display() {
		// TODO Auto-generated method stub
		StringBuilder listToShow = new StringBuilder("");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		int count = 1;
		for (Semester semester : list()) {
			listToShow.append("<tr> " + "<td class=\"text-left\">" + count++
					+ "</td> " + "<td class=\"text-left\">"
					+ semester.getCode() + "</td>" + "<td class=\"text-left\">"
					+ semester.getName() + "</td>" + "<td class=\"text-left\">"
					+ semester.getSemester_year() + "</td>"
					+ "<td class=\"text-left\">"
					+ sdf.format(semester.getStart_date()) + "</td>"
					+ "<td class=\"text-left\">"
					+ sdf.format(semester.getEnd_date()) + "</td>" + "</tr>");
		}

		return listToShow.toString();
	}

	@Override
	public List<ClassSemester> getClassSemester(int semester_id) {
		// TODO Auto-generated method stub

		List<ClassSemester> list = new ArrayList<ClassSemester>();

		String sql = "SELECT * FROM class_semester WHERE semester_id=?";

		List<Map<String, Object>> rows = jdbc.queryForList(sql, semester_id);
		for (Map row : rows) {
			ClassSemester class_semester = new ClassSemester();
			class_semester.setClass_semester_id((Integer) row.get("class_semester_id"));
			class_semester.setClass_id((Integer) row.get("class_id"));
			class_semester.setSemester_id((Integer) row.get("semester_id"));
			list.add(class_semester);
		}

		return list;
	}

	@Override
	public List<CourseSemester> getCourseSemester(int semester_id) {
		// TODO Auto-generated method stub
		List<CourseSemester> list = new ArrayList<CourseSemester>();

		String sql = "SELECT * FROM course_semester WHERE semester_id=?";

		List<Map<String, Object>> rows = jdbc.queryForList(sql, semester_id);
		for (Map row : rows) {
			CourseSemester course_semester = new CourseSemester();
			course_semester.setCourse_semester_id((Integer) row.get("course_semester_id"));
			course_semester.setCourse_id((Integer) row.get("course_id"));
			course_semester.setSemester_id((Integer) row.get("semester_id"));
			list.add(course_semester);
		}

		return list;
	}

	@Override
	public List<TeacherSemester> getTeacherSemester(int semester_id) {
		// TODO Auto-generated method stub
		
		
		List<TeacherSemester> list = new ArrayList<TeacherSemester>();

		String sql = "SELECT * FROM teacher_semester WHERE semester_id=?";

		List<Map<String, Object>> rows = jdbc.queryForList(sql, semester_id);
		for (Map row : rows) {
			TeacherSemester teacher_semester = new TeacherSemester();
			teacher_semester.setTeacher_semester_id((Integer) row.get("teacher_semester_id"));
			teacher_semester.setTeacher_id((Integer) row.get("teacher_id"));
			teacher_semester.setSemester_id((Integer) row.get("semester_id"));
			list.add(teacher_semester);
		}

		return list;
	}

}
