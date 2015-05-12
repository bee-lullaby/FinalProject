package com.fpt.academic.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.sql.DataSource;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import sun.util.locale.StringTokenIterator;

import com.fpt.academic.model.Course;
import com.fpt.academic.model.CourseSemester;
import com.fpt.academic.model.Teacher;
import com.fpt.academic.model.TeacherCourse;
import com.fpt.academic.model.TeacherSemester;

public class TeacherCourseDAOImpl implements TeacherCourseDAO {

	private JdbcTemplate jdbc;
	
	public TeacherCourseDAOImpl(DataSource dataSource) {
		this.jdbc = new JdbcTemplate(dataSource);
	}
	
	@Autowired 
	private TeacherDAO teacherDAO;
	
	@Autowired 
	private CourseDAO courseDAO;
	
	@Autowired 
	private SemesterDAO semesterDAO;
	
	@Override
	public void addTeacherCourse(TeacherCourse teacher_course, int semester_id) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO teacher_course(teacher_semester_id, course_semester_id) VALUES (?, ?)";
		jdbc.update(sql, teacher_course.getTeacher_semester_id(), teacher_course.getCourse_semester_id());
	}

	@Override
	public void addListTeacherCourse(File teacher_course, int semester_id) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO teacher_course_semester(teacher_semester_id, course_semester_id) VALUES (?, ?)";
		
		
		List<Teacher> teachers = teacherDAO.list();
		List<String> teacherAccounts = new ArrayList<String>();
		for(Teacher teacher: teachers) {
			teacherAccounts.add(teacher.getAccount() );
			System.out.println(teacher.getAccount()+"a");
		}
		
		List<Course> courses = courseDAO.list();
		List<String> courseCodes = new ArrayList<String>();
		for(Course course: courses) {
			courseCodes.add(course.getCode());
		}
		
		List<TeacherSemester> teacher_semester = semesterDAO.getTeacherSemester(semester_id);
		List<Integer> teacherIDs = new ArrayList<Integer>();
		for(TeacherSemester ts: teacher_semester) {
			teacherIDs.add(ts.getTeacher_id());
		}
		
		
		List<CourseSemester> course_semester = semesterDAO.getCourseSemester(semester_id);
		List<Integer> courseIDs = new ArrayList<Integer>();
		for(CourseSemester cs: course_semester) {
			courseIDs.add(cs.getCourse_id());
		}
		
		
		try {
			FileInputStream file = new FileInputStream(teacher_course); 
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);
			
			Iterator<Row> rowIterator = sheet.iterator();
			
			rowIterator.next();
			while(rowIterator.hasNext()) {
				Row row = rowIterator.next();
				

				String course_code = row.getCell(0).getStringCellValue();
				String teacher_names = row.getCell(1).getStringCellValue();
				
				StringTokenizer st = new StringTokenizer(teacher_names);
				
				
				
				while(st.hasMoreTokens()) {
					String teacher_name = st.nextToken().toString();
					System.out.println(teacher_name);
					int index_name = teacherAccounts.indexOf(teacher_name);
					System.out.println(index_name);
					int teacher_id = teachers.get(index_name).getTeacher_id();
					int teacher_semester_id = teacher_semester.get(teacherIDs.indexOf(teacher_id)).getTeacher_semester_id();
					
					int course_id = courses.get(courseCodes.indexOf(course_code)).getCourse_id();
					int course_semester_id = course_semester.get(courseIDs.indexOf(course_id)).getCourse_semester_id();
					jdbc.update(sql, teacher_semester_id, course_semester_id);
				}
			}
			
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
