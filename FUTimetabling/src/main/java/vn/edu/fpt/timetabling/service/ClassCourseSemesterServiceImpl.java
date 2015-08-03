package vn.edu.fpt.timetabling.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.fpt.timetabling.dao.ClassCourseSemesterDAO;
import vn.edu.fpt.timetabling.model.ClassCourseSemester;
import vn.edu.fpt.timetabling.model.ClassSemester;
import vn.edu.fpt.timetabling.model.CourseSemester;

@Service
@Transactional(rollbackFor = Exception.class)
public class ClassCourseSemesterServiceImpl implements ClassCourseSemesterService {
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

			ClassCourseSemester classCourseSemester = new ClassCourseSemester();

			ClassSemester classSemester = classSemesterService.getClassSemesterByCode(classCode, semesterId, true);
			if (classSemester == null) {
				continue;
			}
			classCourseSemester.setClassSemester(classSemester);
			String courseCode = row.getCell(1).getStringCellValue().trim();
			CourseSemester courseSemester = courseSemesterService.getCourseSemesterByCode(courseCode, false, false,
					false);
			if (courseSemester == null) {
				continue;
			}
			classCourseSemester.setCourseSemester(courseSemester);
			if (row.getCell(2) != null) {
				classCourseSemester.setSemesterLong(true);
			} else {
				classCourseSemester.setSemesterLong(false);
			}
			System.out.println(classCode + " " + courseCode);
			classCourseSemesterDAO.addClassCourseSemester(classCourseSemester);
		}
		workbook.close();
		file.close();
	}

	@Override
	public void updateClassCourseSemester(ClassCourseSemester classCourseSemester) {
		classCourseSemesterDAO.updateClassCourseSemester(classCourseSemester);
	}

	@Override
	public List<ClassCourseSemester> listClassCourseSemesters(boolean jointTimetable,
			boolean jointClassCourseStudentSemesters) {
		return classCourseSemesterDAO.listClassCourseSemesters(jointTimetable, jointClassCourseStudentSemesters);
	}

	@Override
	public ClassCourseSemester getClassCourseSemesterById(int classCourseSemesterId, boolean jointTimetable,
			boolean jointClassCourseStudentSemesters) {
		return classCourseSemesterDAO.getClassCourseSemesterById(classCourseSemesterId, jointTimetable,
				jointClassCourseStudentSemesters);
	}

	@Override
	public void deleteClassCourseSemester(int classCourseSemesterId) {
		classCourseSemesterDAO.deleteClassCourseSemester(classCourseSemesterId);
	}

	@Override
	public List<ClassCourseSemester> listClassCourseSemesterBySemester(int semesterId, boolean jointTimetable,
			boolean jointClassCourseStudentSemesters) {
		return classCourseSemesterDAO.listClassCourseSemesterBySemester(semesterId, jointTimetable,
				jointClassCourseStudentSemesters);
	}

	@Override
	public long getNumberOfStudents(int classCourseSemesterId) {
		return classCourseSemesterDAO.getNumberOfStudents(classCourseSemesterId);
	}

	@Override
	public List<ClassCourseSemester> listClassCourseSemesterByClass(int classSemesterId, boolean jointTimetable,
			boolean jointClassCourseStudentSemesters) {
		return classCourseSemesterDAO.listClassCourseSemesterByClass(classSemesterId, jointTimetable,
				jointClassCourseStudentSemesters);
	}

	@Override
	public List<ClassCourseSemester> listClassCourseSemesterByCourse(int courseSemesterId, boolean jointTimetable,
			boolean jointClassCourseStudentSemesters) {
		return classCourseSemesterDAO.listClassCourseSemesterByCourse(courseSemesterId, jointTimetable,
				jointClassCourseStudentSemesters);
	}

	@Override
	public List<ClassCourseSemester> listClassCourseSemesterByStudent(int semesterId, int studentId) {
		return classCourseSemesterDAO.listClassCourseSemesterByStudent(semesterId, studentId);
	}
}
