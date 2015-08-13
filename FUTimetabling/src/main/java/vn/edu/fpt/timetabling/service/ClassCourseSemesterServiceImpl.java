package vn.edu.fpt.timetabling.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
import vn.edu.fpt.timetabling.model.ClassFPT;
import vn.edu.fpt.timetabling.model.ClassSemester;
import vn.edu.fpt.timetabling.model.Course;
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
	public HashMap<String, List<String>> addClassCourseSemesterFromFile(File classCourses, int semesterId)
			throws IOException {
		FileInputStream file = new FileInputStream(classCourses);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.iterator();
		rowIterator.next();

		HashMap<String, List<String>> mCheckClassAndCourseExisted = new HashMap<String, List<String>>();
		mCheckClassAndCourseExisted.put("class", new ArrayList<String>());
		mCheckClassAndCourseExisted.put("course", new ArrayList<String>());
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			String classCode = row.getCell(3).getStringCellValue().trim();

			ClassSemester classSemester = classSemesterService.getClassSemesterByCode(classCode, semesterId, true);
			if (classSemester == null) {
				mCheckClassAndCourseExisted.get("class").add(classCode);
				continue;
			}

			int count = 4;
			while (row.getCell(count) != null) {
				String courseCode = row.getCell(count).getStringCellValue().trim();
				CourseSemester courseSemester = courseSemesterService.getCourseSemesterByCourseCodeSemester(courseCode,
						semesterId, false, false, false);
				if (courseSemester == null) {
					mCheckClassAndCourseExisted.get("course").add(courseCode);
					continue;
				}

				ClassCourseSemester classCourseSemester = getClassCourseSemesterByClassAndCourseSemester(
						classSemester.getClassSemesterId(), courseSemester.getCourseSemesterId(), false, false);

				if (classCourseSemester == null) {
					classCourseSemester = new ClassCourseSemester();
				}

				classCourseSemester.setClassSemester(classSemester);
				classCourseSemester.setCourseSemester(courseSemester);
				// if (row.getCell(2) != null) {
				// System.out.println(row.getCell(2).getNumericCellValue());
				// if (row.getCell(2).getNumericCellValue() == 1) {
				// classCourseSemester.setSemesterLong(true);
				// } else {
				// classCourseSemester.setSemesterLong(false);
				// }
				// }
				classCourseSemester.setSemesterLong(false);
				if (classCourseSemester.getClassCourseSemesterId() == 0) {
					classCourseSemesterDAO.addClassCourseSemester(classCourseSemester);
				}
				count++;
			}
		}
		workbook.close();
		file.close();
		return mCheckClassAndCourseExisted;
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
	public ClassCourseSemester getClassCourseSemesterByClassAndCourseSemester(int classSemesterId, int courseSemesterId,
			boolean jointTimetable, boolean jointClassCourseStudentSemesters) {
		return classCourseSemesterDAO.getClassCourseSemesterByClassAndCourseSemester(classSemesterId, courseSemesterId,
				false, false);
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

	@Override
	public int deleteClassCourseSemesters(int semesterId) {
		return classCourseSemesterDAO.deleteClassCourseSemesters(semesterId);
	}
	
	@Override
	public ClassCourseSemester createNewCCS(ClassCourseSemester ccs) {
		ClassCourseSemester classCourseSemester = new ClassCourseSemester();
		classCourseSemester.setClassCourseSemesterId(ccs
				.getClassCourseSemesterId());

		ClassSemester classSemester = new ClassSemester();
		classSemester.setClassSemesterId(ccs.getClassSemester()
				.getClassSemesterId());
		ClassFPT classFPT = new ClassFPT();
		classFPT.setClassId(ccs.getClassSemester().getClassFPT()
				.getClassId());
		classFPT.setCode(ccs.getClassSemester().getClassFPT().getCode());
		classSemester.setClassFPT(classFPT);

		CourseSemester courseSemester = new CourseSemester();
		courseSemester.setCourseSemesterId(ccs.getCourseSemester()
				.getCourseSemesterId());
		Course course = new Course();
		course.setCourseId(ccs.getCourseSemester().getCourse()
				.getCourseId());
		course.setCode(ccs.getCourseSemester().getCourse().getCode());
		classSemester.setClassFPT(classFPT);
		courseSemester.setCourse(course);

		classCourseSemester.setClassSemester(classSemester);
		classCourseSemester.setCourseSemester(courseSemester);
		return classCourseSemester;
	}
}
