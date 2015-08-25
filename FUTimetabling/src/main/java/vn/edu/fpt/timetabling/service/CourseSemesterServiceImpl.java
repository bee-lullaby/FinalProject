package vn.edu.fpt.timetabling.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.fpt.timetabling.CourseController;
import vn.edu.fpt.timetabling.dao.CourseSemesterDAO;
import vn.edu.fpt.timetabling.model.Course;
import vn.edu.fpt.timetabling.model.CourseSemester;
import vn.edu.fpt.timetabling.model.Department;

@Service
@Transactional(rollbackFor = Exception.class)
public class CourseSemesterServiceImpl implements CourseSemesterService {

	private static final Logger logger = LoggerFactory
			.getLogger(CourseController.class);

	private CourseSemesterDAO courseSemesterDAO;
	@Autowired
	private CourseService courseService;
	@Autowired
	private SemesterService semesterService;
	@Autowired
	private DepartmentService departmentService;

	public void setCourseSemesterDAO(CourseSemesterDAO courseSemesterDAO) {
		this.courseSemesterDAO = courseSemesterDAO;
	}

	@Override
	public List<String> addCourseSemesterFromFile(File courseSemesters,
			int semesterId) throws IOException {
		FileInputStream file = new FileInputStream(courseSemesters);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIteratorFirst = sheet.iterator();
		rowIteratorFirst.next();
		List<String> checkDepartmentExisted = new ArrayList<String>();

		while (rowIteratorFirst.hasNext()) {
			Row row = rowIteratorFirst.next();
			CourseSemester courseSemester = new CourseSemester();
			String codeCourse = row.getCell(1).getStringCellValue().trim();
			Double slots = row.getCell(3).getNumericCellValue();
			String codeConditionCourse = "";
			if (row.getCell(4) != null) {
				codeConditionCourse = row.getCell(4).getStringCellValue();
			}

			if (courseService.getCourseByCode(codeCourse) == null) {
				Course course = new Course();
				String departmentCode = row.getCell(0).getStringCellValue()
						.trim();

				Department department = departmentService
						.getDepartmentByCode(departmentCode);
				if (department == null) {
					checkDepartmentExisted.add(departmentCode);
					continue;
				}
				course.setDepartment(department);
				course.setCode(codeCourse);
				course.setName(row.getCell(2).getStringCellValue().trim());
				courseService.addCourse(course);
			}

			if (getCourseSemesterByCourseCodeSemester(codeCourse, semesterId,
					false, false, false) == null) {
				courseSemester.setCourse(courseService
						.getCourseByCode(codeCourse));
				logger.info(Integer.toString(semesterId));
				courseSemester.setSemester(semesterService.getSemesterById(
						semesterId, false, false, false, false));
				courseSemester.setSlots(slots.intValue());
				if (codeConditionCourse.compareTo("") != 0) {
					courseSemester.setCourseCondition(courseService
							.getCourseByCode(codeConditionCourse));
				}
				courseSemesterDAO.addCourseSemester(courseSemester);
			}

		}
		workbook.close();
		file.close();
		return checkDepartmentExisted;
	}

	@Override
	public void addCourseSemester(CourseSemester courseSemester) {
		courseSemesterDAO.addCourseSemester(courseSemester);
	}

	@Override
	public void updateCourseSemester(CourseSemester courseSemester) {
		courseSemesterDAO.updateCourseSemester(courseSemester);
	}

	@Override
	public List<CourseSemester> listCourseSemesters(
			boolean jointClassCourseSemester,
			boolean jointTeacherCourseSemester,
			boolean jointProgramSemesterDetails) {
		return courseSemesterDAO.listCourseSemesters(jointClassCourseSemester,
				jointTeacherCourseSemester, jointProgramSemesterDetails);
	}

	@Override
	public List<CourseSemester> listCourseSemestersBySemester(int semesterId,
			boolean jointClassCourseSemester,
			boolean jointTeacherCourseSemester,
			boolean jointProgramSemesterDetails) {
		return courseSemesterDAO.listCourseSemestersBySemester(semesterId,
				false, false, false);
	}

	@Override
	public List<CourseSemester> listCourseSemesterForView(int semesterId) {
		// TODO Auto-generated method stub

		List<CourseSemester> result = new ArrayList<CourseSemester>();
		result.addAll(courseSemesterDAO.listCourseSemestersBySemester(
				semesterId, false, false, false));

		return result;
	}

	@Override
	public CourseSemester getCourseSemesterById(int courseSemesterId,
			boolean jointClassCourseSemester,
			boolean jointTeacherCourseSemester,
			boolean jointProgramSemesterDetails) {
		return courseSemesterDAO.getCourseSemesterById(courseSemesterId,
				jointClassCourseSemester, jointTeacherCourseSemester,
				jointProgramSemesterDetails);
	}

	@Override
	public CourseSemester getCourseSemesterByCourseSemester(int courseId,
			int semesterId, boolean jointClassCourseSemester,
			boolean jointTeacherCourseSemester,
			boolean jointProgramSemesterDetails) {
		return courseSemesterDAO.getCourseSemesterByCourseSemester(courseId,
				semesterId, jointClassCourseSemester,
				jointTeacherCourseSemester, jointProgramSemesterDetails);
	}

	@Override
	public CourseSemester getCourseSemesterByCourseCodeSemester(String code,
			int semesterId, boolean jointClassCourseSemester,
			boolean jointTeacherCourseSemester,
			boolean jointProgramSemesterDetails) {
		return courseSemesterDAO.getCourseSemesterByCourseCodeSemester(code,
				semesterId, jointClassCourseSemester,
				jointTeacherCourseSemester, jointProgramSemesterDetails);
	}

	@Override
	public void deleteCourseSemester(int courseSemesterId) {
		courseSemesterDAO.deleteCourseSemester(courseSemesterId);
	}

	@Override
	public List<CourseSemester> listCourseSemestersByStudent(int studentId) {
		return courseSemesterDAO.listCourseSemestersByStudent(studentId);
	}

	@Override
	public List<CourseSemester> listCourseSemestersByDepartment(int semesterId,
			int departmentId, boolean jointClassCourseSemester,
			boolean jointTeacherCourseSemester,
			boolean jointProgramSemesterDetails) {
		return courseSemesterDAO.listCourseSemestersByDepartment(semesterId,
				departmentId, false, false, false);
	}
	
	@Override
	public List<CourseSemester> listCourseSemestersByCourseId(int courseId,
			boolean jointClassCourseSemester, boolean jointTeacherCourseSemester, boolean jointProgramSemesterDetails) {
		return courseSemesterDAO.listCourseSemestersByCourseId(courseId, jointClassCourseSemester, jointTeacherCourseSemester, jointProgramSemesterDetails);
	}

}
