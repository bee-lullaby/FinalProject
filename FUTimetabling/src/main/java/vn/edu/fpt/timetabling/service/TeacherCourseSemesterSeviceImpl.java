package vn.edu.fpt.timetabling.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
import vn.edu.fpt.timetabling.model.TeacherCourseSemester;

@Service
@Transactional(rollbackFor = Exception.class)
public class TeacherCourseSemesterSeviceImpl implements TeacherCourseSemesterService {
	private TeacherCourseSemesterDAO teacherCourseSemesterDAO;
	@Autowired
	private CourseSemesterService courseSemesterService;
	@Autowired
	private TeacherSemesterService teacherSemesterService;

	public void setTeacherCourseSemesterDAO(TeacherCourseSemesterDAO teacherCourseSemesterDAO) {
		this.teacherCourseSemesterDAO = teacherCourseSemesterDAO;
	}

	@Override
	public void addTeacherCourseSemester(TeacherCourseSemester teacherCourseSemester) {
		teacherCourseSemesterDAO.addTeacherCourseSemester(teacherCourseSemester);
	}

	@Override
	public void addTeacherCourseSemesterFromFile(File teacherCourses) throws IOException {
		FileInputStream file = new FileInputStream(teacherCourses);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.iterator();
		rowIterator.next();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();
			String courseCode = "";
			if (cellIterator.hasNext()) {
				courseCode = cellIterator.next().getStringCellValue().trim();
				cellIterator.next();
			}
			while (cellIterator.hasNext()) {
				TeacherCourseSemester tcs = new TeacherCourseSemester();
				tcs.setCourseSemester(courseSemesterService.getCourseSemesterByCode(courseCode, false, false, false));

				String teacherAccount = cellIterator.next().getStringCellValue().trim();
				tcs.setTeacherSemester(
						teacherSemesterService.getTeacherSemesterByAccount(teacherAccount, false, false));
				teacherCourseSemesterDAO.addTeacherCourseSemester(tcs);
			}
		}
		workbook.close();
		file.close();
	}

	@Override
	public void updateTeacherCourseSemester(TeacherCourseSemester teacherCourseSemester) {
		teacherCourseSemesterDAO.updateTeacherCourseSemester(teacherCourseSemester);
	}

	@Override
	public List<TeacherCourseSemester> listTeacherCourseSemesters() {
		return teacherCourseSemesterDAO.listTeacherCourseSemesters();
	}

	@Override
	public TeacherCourseSemester getTeacherCourseSemesterById(int teacherCourseSemesterId) {
		return teacherCourseSemesterDAO.getTeacherCourseSemesterById(teacherCourseSemesterId);
	}

	@Override
	public void deleteTeacherCourseSemester(int teacherCourseSemesterId) {
		teacherCourseSemesterDAO.deleteTeacherCourseSemester(teacherCourseSemesterId);
	}
}
