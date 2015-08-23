package vn.edu.fpt.timetabling.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.fpt.timetabling.dao.TeacherCourseSemesterDAO;
import vn.edu.fpt.timetabling.model.CourseSemester;
import vn.edu.fpt.timetabling.model.TeacherCourseSemester;
import vn.edu.fpt.timetabling.model.TeacherSemester;

@Service
@Transactional(rollbackFor = Exception.class)
public class TeacherCourseSemesterSeviceImpl implements
		TeacherCourseSemesterService {
	private TeacherCourseSemesterDAO teacherCourseSemesterDAO;
	@Autowired
	private CourseSemesterService courseSemesterService;
	@Autowired
	private TeacherSemesterService teacherSemesterService;

	public void setTeacherCourseSemesterDAO(
			TeacherCourseSemesterDAO teacherCourseSemesterDAO) {
		this.teacherCourseSemesterDAO = teacherCourseSemesterDAO;
	}

	@Override
	public void addTeacherCourseSemester(
			TeacherCourseSemester teacherCourseSemester) {
		teacherCourseSemesterDAO
				.addTeacherCourseSemester(teacherCourseSemester);
	}

	@Override
	public List<String> addTeacherCourseSemesterFromFile(File teacherCourses,
			int semesterId) throws IOException {
		FileInputStream file = new FileInputStream(teacherCourses);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.iterator();
		rowIterator.next();

		List<String> checkCourseExisted = new ArrayList<String>();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();
			String courseCode = "";
			CourseSemester courseSemester = new CourseSemester();
			if (cellIterator.hasNext()) {
				courseCode = cellIterator.next().getStringCellValue().trim();
				courseSemester = courseSemesterService
						.getCourseSemesterByCourseCodeSemester(courseCode,
								semesterId, false, false, false);
				if (courseSemester == null) {
					checkCourseExisted.add(courseCode);
				} else {
					cellIterator.next();
					while (cellIterator.hasNext()) {
						TeacherCourseSemester tcs = new TeacherCourseSemester();

						tcs.setCourseSemester(courseSemester);

						String teacherAccount = cellIterator.next()
								.getStringCellValue().trim();
						
						TeacherSemester teacherSemester = teacherSemesterService
								.getTeacherSemesterByAccount(semesterId,
										teacherAccount, false, false);
						if (teacherSemester == null) {
							// Do Something //
						} else {
							if (getTeacherCourseSemesterByTeacherCourse(teacherSemester.getTeacherSemesterId(),
								courseCode) == null) {
								tcs.setTeacherSemester(teacherSemester);

								teacherCourseSemesterDAO
										.addTeacherCourseSemester(tcs);
							}
						}
						
						

					}
				}
			}
		}
		workbook.close();
		file.close();

		return checkCourseExisted;
	}

	@Override
	public void updateTeacherCourseSemester(
			TeacherCourseSemester teacherCourseSemester) {
		teacherCourseSemesterDAO
				.updateTeacherCourseSemester(teacherCourseSemester);
	}

	@Override
	public List<TeacherCourseSemester> listTeacherCourseSemesters() {
		return teacherCourseSemesterDAO.listTeacherCourseSemesters();
	}

	@Override
	public List<TeacherCourseSemester> listTeacherCourseSemestersByCourse(
			int courseSemesterId) {
		return teacherCourseSemesterDAO
				.listTeacherCourseSemestersByCourse(courseSemesterId);
	}

	@Override
	public List<TeacherCourseSemester> listTeacherCourseSemestersByTeacher(
			int teacherSemesterId) {
		return teacherCourseSemesterDAO
				.listTeacherCourseSemestersByTeacher(teacherSemesterId);
	}

	@Override
	public TeacherCourseSemester getTeacherCourseSemesterById(
			int teacherCourseSemesterId) {
		return teacherCourseSemesterDAO
				.getTeacherCourseSemesterById(teacherCourseSemesterId);
	}

	@Override
	public TeacherCourseSemester getTeacherCourseSemesterByTeacherCourse(
			int teacherSemesterId, String courseCode) {
		return teacherCourseSemesterDAO
				.getTeacherCourseSemesterByTeacherCourse(teacherSemesterId,
						courseCode);
	}

	@Override
	public void deleteTeacherCourseSemester(int teacherCourseSemesterId) {
		teacherCourseSemesterDAO
				.deleteTeacherCourseSemester(teacherCourseSemesterId);
	}

}
