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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.fpt.timetabling.dao.CourseSemesterDAO;
import vn.edu.fpt.timetabling.dao.TeacherCourseSemesterDAO;
import vn.edu.fpt.timetabling.dao.TeacherSemesterDAO;
import vn.edu.fpt.timetabling.model.TeacherCourseSemester;

@Service
public class TeacherCourseSemesterSeviceImpl implements
		TeacherCourseSemesterService {

	private TeacherCourseSemesterDAO teacherCourseSemesterDAO;
	

	private static final Logger logger = LoggerFactory
			.getLogger(ClassCourseSemesterServiceImpl.class);
	
	@Autowired
	private CourseSemesterDAO courseSemesterDAO;
	
	@Autowired
	private TeacherSemesterDAO teacherSemesterDAO;
	
	public void setTeacherCourseSemesterDAO(
			TeacherCourseSemesterDAO teacherCourseSemesterDAO) {
		this.teacherCourseSemesterDAO = teacherCourseSemesterDAO;
	}

	@Override
	@Transactional
	public void addTeacherCourseSemester(
			TeacherCourseSemester teacherCourseSemester) {
		// TODO Auto-generated method stub
		teacherCourseSemesterDAO
				.addTeacherCourseSemester(teacherCourseSemester);
	}

	@Override
	@Transactional
	public void addTeacherCourseSemesterFromFile(File teacherCourses) {
		try {
			FileInputStream file = new FileInputStream(teacherCourses);
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);

			Iterator<Row> rowIterator = sheet.iterator();

			rowIterator.next();

			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				String courseCode = "";
				if(cellIterator.hasNext()) {
					courseCode = cellIterator.next().getStringCellValue().trim();
					cellIterator.next();
				}
				while(cellIterator.hasNext()) {
					TeacherCourseSemester tcs = new TeacherCourseSemester();
					tcs.setCourseSemester(courseSemesterDAO.getCourseSemesterByCode(courseCode, false, false, false));
					
					String teacherAccount = cellIterator.next().getStringCellValue().trim();
					logger.info(courseCode +" " +teacherAccount);
					tcs.setTeacherSemester(teacherSemesterDAO.getTeacherSemesterByAccount(teacherAccount));
					teacherCourseSemesterDAO.addTeacherCourseSemester(tcs);
				}
				
			}

			workbook.close();
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	@Transactional
	public void updateTeacherCourseSemester(
			TeacherCourseSemester teacherCourseSemester) {
		// TODO Auto-generated method stub
		teacherCourseSemesterDAO
				.updateTeacherCourseSemester(teacherCourseSemester);
	}

	@Override
	@Transactional
	public List<TeacherCourseSemester> listTeacherCourseSemesters() {
		// TODO Auto-generated method stub
		return teacherCourseSemesterDAO.listTeacherCourseSemesters();
	}

	@Override
	@Transactional
	public TeacherCourseSemester getTeacherCourseSemesterById(
			int teacherCourseSemesterId) {
		// TODO Auto-generated method stub
		return teacherCourseSemesterDAO
				.getTeacherCourseSemesterById(teacherCourseSemesterId);
	}

	@Override
	@Transactional
	public void deleteTeacherCourseSemester(int teacherCourseSemesterId) {
		// TODO Auto-generated method stub
		teacherCourseSemesterDAO
				.deleteTeacherCourseSemester(teacherCourseSemesterId);
	}

}
