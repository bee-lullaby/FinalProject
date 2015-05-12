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

import com.fpt.academic.model.ClassCourse;
import com.fpt.academic.model.ClassFPT;
import com.fpt.academic.model.ClassSemester;
import com.fpt.academic.model.Course;
import com.fpt.academic.model.CourseSemester;

public class ClassCourseDAOImpl implements ClassCourseDAO {
	private JdbcTemplate jdbc;

	public ClassCourseDAOImpl(DataSource dataSource) {
		this.jdbc = new JdbcTemplate(dataSource);
	}

	@Autowired
	private ClassDAO classDAO;

	@Autowired
	private CourseDAO courseDAO;

	@Autowired
	private SemesterDAO semesterDAO;

	@Override
	public void addClassCourse(ClassCourse class_course, int semester_id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addListClassCourse(File class_course, int semester_id) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO class_course_semester(class_semester_id, course_semester_id) VALUES (?, ?)";

		List<ClassFPT> classes = classDAO.list();
		List<String> classCodes = new ArrayList<String>();
		for (ClassFPT classFPT : classes) {
			classCodes.add(classFPT.getCode());
			System.out.println(classFPT.getCode());
		}

		List<Course> courses = courseDAO.list();
		List<String> courseCodes = new ArrayList<String>();
		for (Course course : courses) {
			courseCodes.add(course.getCode());
		}

		List<ClassSemester> class_semester = semesterDAO
				.getClassSemester(semester_id);
		List<Integer> classIDs = new ArrayList<Integer>();
		for (ClassSemester cs : class_semester) {
			classIDs.add(cs.getClass_id());
		}

		List<CourseSemester> course_semester = semesterDAO
				.getCourseSemester(semester_id);
		List<Integer> courseIDs = new ArrayList<Integer>();
		for (CourseSemester cs : course_semester) {
			courseIDs.add(cs.getCourse_id());
		}

		try {
			FileInputStream file = new FileInputStream(class_course);
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);

			Iterator<Row> rowIterator = sheet.iterator();

			rowIterator.next();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();

				String class_codes = row.getCell(0).getStringCellValue();
				String course_codes = row.getCell(1).getStringCellValue();
				
				if (!course_codes.isEmpty() || course_codes.compareTo("") != 0) {
					StringTokenizer st = new StringTokenizer(course_codes);
					while (st.hasMoreTokens()) {
						
						String code = st.nextToken().toString().trim();
						System.out.print(code +" ");
						int index_name = classCodes.indexOf(code);
						System.out.print(index_name +" ");
						int class_id = classes.get(index_name).getClass_id();
						System.out.print(class_id +" ");
						int class_semester_id = class_semester.get(
								classIDs.indexOf(class_id))
								.getClass_semester_id();
						System.out.print(class_semester_id +" ");
						System.out.println();
						
						
						int course_id = courses.get(
								courseCodes.indexOf(class_codes))
								.getCourse_id();
						int course_semester_id = course_semester.get(
								courseIDs.indexOf(course_id))
								.getCourse_semester_id();
						jdbc.update(sql, class_semester_id, course_semester_id);
					}
				}
			}

			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
