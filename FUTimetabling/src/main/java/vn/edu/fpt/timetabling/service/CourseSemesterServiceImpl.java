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

import vn.edu.fpt.timetabling.dao.CourseSemesterDAO;
import vn.edu.fpt.timetabling.model.CourseSemester;

@Service
public class CourseSemesterServiceImpl implements CourseSemesterService {

	private CourseSemesterDAO courseSemesterDAO;

	@Autowired
	private CourseService courseService;

	@Autowired
	private SemesterService semesterService;

	public void setCourseSemesterDAO(CourseSemesterDAO courseSemesterDAO) {
		this.courseSemesterDAO = courseSemesterDAO;
	}

	@Override
	@Transactional
	public void addCourseSemesterFromFile(File courseSemesters, int semesterId) {
		try {
			FileInputStream file = new FileInputStream(courseSemesters);
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);

			Iterator<Row> rowIterator = sheet.iterator();

			rowIterator.next();

			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				CourseSemester courseSemester = new CourseSemester();

				String codeCourse = row.getCell(1).getStringCellValue().trim();
				Double slots = row.getCell(3).getNumericCellValue();

				String codeConditionCourse = "";
				if (row.getCell(4) != null) {
					codeConditionCourse = row.getCell(4).getStringCellValue();
				}
				courseSemester.setCourse(courseService.getCourseByCode(codeCourse));
				courseSemester.setSemester(semesterService.getSemesterById(semesterId, false, false, false, false));
				courseSemester.setSlots(slots.intValue());
				if (codeConditionCourse.compareTo("") != 0) {
					courseSemester.setCourseCondition(courseService.getCourseByCode(codeConditionCourse));
				}
				courseSemesterDAO.addCourseSemester(courseSemester);

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
	public void addCourseSemester(CourseSemester courseSemester) {
		courseSemesterDAO.addCourseSemester(courseSemester);
	}

	@Override
	@Transactional
	public void updateCourseSemester(CourseSemester courseSemester) {
		courseSemesterDAO.updateCourseSemester(courseSemester);
	}

	@Override
	@Transactional
	public List<CourseSemester> listCourseSemesters(boolean jointClassCourseSemester,
			boolean jointTeacherCourseSemester, boolean jointProgramSemesterDetails) {
		return courseSemesterDAO.listCourseSemesters(jointClassCourseSemester, jointTeacherCourseSemester,
				jointProgramSemesterDetails);
	}

	@Override
	@Transactional
	public CourseSemester getCourseSemesterById(int courseSemesterId, boolean jointClassCourseSemester,
			boolean jointTeacherCourseSemester, boolean jointProgramSemesterDetails) {
		return courseSemesterDAO.getCourseSemesterById(courseSemesterId, jointClassCourseSemester,
				jointTeacherCourseSemester, jointProgramSemesterDetails);
	}

	@Override
	@Transactional
	public CourseSemester getCourseSemesterByCode(String code, boolean jointClassCourseSemester,
			boolean jointTeacherCourseSemester, boolean jointProgramSemesterDetails) {
		return courseSemesterDAO.getCourseSemesterByCode(code, jointClassCourseSemester, jointTeacherCourseSemester,
				jointProgramSemesterDetails);
	}

	@Override
	@Transactional
	public CourseSemester getCourseSemesterByCourseSemester(int courseId, int semesterId,
			boolean jointClassCourseSemester, boolean jointTeacherCourseSemester, boolean jointProgramSemesterDetails) {
		return courseSemesterDAO.getCourseSemesterByCourseSemester(courseId, semesterId, jointClassCourseSemester,
				jointTeacherCourseSemester, jointProgramSemesterDetails);
	}

	@Override
	@Transactional
	public void deleteCourseSemester(int courseSemesterId) {
		courseSemesterDAO.deleteCourseSemester(courseSemesterId);
	}

	@Override
	@Transactional
	public List<CourseSemester> listCourseSemestersByStudent(int studentId) {
		return courseSemesterDAO.listCourseSemestersByStudent(studentId);
	}

}
