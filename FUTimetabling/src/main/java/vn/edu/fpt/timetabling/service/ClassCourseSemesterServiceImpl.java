package vn.edu.fpt.timetabling.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.fpt.timetabling.dao.ClassCourseSemesterDAO;
import vn.edu.fpt.timetabling.model.ClassCourseSemester;
import vn.edu.fpt.timetabling.model.ClassSemester;

@Service
@Transactional(rollbackFor = Exception.class)
public class ClassCourseSemesterServiceImpl implements ClassCourseSemesterService {
	private static final Logger logger = Logger.getLogger(ClassCourseSemesterServiceImpl.class);
	private ClassCourseSemesterDAO classCourseSemesterDAO;
	@Autowired
	private ClassSemesterService classSemesterService;
	@Autowired
	private CourseSemesterService courseSemesterService;

	public void setClassCourseSemesterDAO(ClassCourseSemesterDAO classCourseSemesterDAO) {
		this.classCourseSemesterDAO = classCourseSemesterDAO;
	}

	@Override
	public void addClassCourseSemester(ClassCourseSemester classCourseSemester) {
		classCourseSemesterDAO.addClassCourseSemester(classCourseSemester);
	}

	@Override
	public void addClassCourseSemesterFromFile(File classCourses, int semesterId) throws IOException {
		FileInputStream file = new FileInputStream(classCourses);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.iterator();
		rowIterator.next();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			String classCode = row.getCell(0).getStringCellValue().trim();
			int i = 0;
			while (i <= 4) {
				ClassCourseSemester ccs = new ClassCourseSemester();
				ClassSemester cs = classSemesterService.getClassSemesterByCode(classCode, semesterId, true);
				ccs.setClassSemester(cs);
				logger.info("CLASS: " + String.valueOf(ccs.getClassSemester().getClassSemesterId()));
				String courseCode = row.getCell(i + 1).getStringCellValue().trim();
				ccs.setCourseSemester(courseSemesterService.getCourseSemesterByCode(courseCode, false, false, false));
				logger.info("COURSE: " + String.valueOf(ccs.getCourseSemester().getCourseSemesterId()));
				if (i == 4) {
					ccs.setSemesterLong(true);
					i++;
				} else {
					ccs.setSemesterLong(false);
					i++;
				}
				classCourseSemesterDAO.addClassCourseSemester(ccs);
			}
		}
		workbook.close();
		file.close();
	}

	@Override
	public void updateClassCourseSemester(ClassCourseSemester classCourseSemester) {
		classCourseSemesterDAO.updateClassCourseSemester(classCourseSemester);
	}

	@Override
	public List<ClassCourseSemester> listClassCourseSemesters() {
		return classCourseSemesterDAO.listClassCourseSemesters();
	}

	@Override
	public ClassCourseSemester getClassCourseSemesterById(int classCourseSemesterId) {
		return classCourseSemesterDAO.getClassCourseSemesterById(classCourseSemesterId);
	}

	@Override
	public void deleteClassCourseSemester(int classCourseSemesterId) {
		classCourseSemesterDAO.deleteClassCourseSemester(classCourseSemesterId);
	}

	@Override
	public List<ClassCourseSemester> listClassCourseSemesterBySemester(int semesterId) {
		return classCourseSemesterDAO.listClassCourseSemesterBySemester(semesterId);
	}

	@Override
	public long getNumberOfStudents(int classCourseSemesterId) {
		return classCourseSemesterDAO.getNumberOfStudents(classCourseSemesterId);
	}
}
