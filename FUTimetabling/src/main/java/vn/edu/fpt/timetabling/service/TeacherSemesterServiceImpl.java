package vn.edu.fpt.timetabling.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.fpt.timetabling.dao.TeacherSemesterDAO;
import vn.edu.fpt.timetabling.model.Course;
import vn.edu.fpt.timetabling.model.CourseSemester;
import vn.edu.fpt.timetabling.model.Semester;
import vn.edu.fpt.timetabling.model.Teacher;
import vn.edu.fpt.timetabling.model.TeacherCourseSemester;
import vn.edu.fpt.timetabling.model.TeacherSemester;

@Service
@Transactional(rollbackFor = Exception.class)
public class TeacherSemesterServiceImpl implements TeacherSemesterService {
	private TeacherSemesterDAO teacherSemesterDAO;

	@Autowired
	private SemesterService semesterService;

	@Autowired
	private TeacherService teacherService;

	public void setTeacherSemesterDAO(TeacherSemesterDAO teacherSemesterDAO) {
		this.teacherSemesterDAO = teacherSemesterDAO;
	}

	@Override
	public void addTeacherSemester(TeacherSemester teacherSemester) {
		teacherSemesterDAO.addTeacherSemester(teacherSemester);
	}

	@Override
	public void addTeacherSemesterFromFile(File teacherSemesters, int semesterId) throws IOException {
		FileInputStream file = new FileInputStream(teacherSemesters);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIteratorFirst = sheet.iterator();
		rowIteratorFirst.next();
		while (rowIteratorFirst.hasNext()) {
			Row row = rowIteratorFirst.next();
			String account = row.getCell(0).getStringCellValue();
			String name = row.getCell(1).getStringCellValue();
			String email = row.getCell(2).getStringCellValue();
			if (teacherService.getTeacherByAccount(account) == null) {
				Teacher teacher = new Teacher();
				teacher.setAccount(account);
				teacher.setName(name);
				teacher.setEmail(email);
				teacherService.addTeacher(teacher);
			}

			if (getTeacherSemesterByAccount(semesterId, account, false, false) == null) {
				TeacherSemester teacherSemester = new TeacherSemester();
				teacherSemester.setSemester(semesterService.getSemesterById(semesterId, false, false, false, false));
				teacherSemester.setTeacher(teacherService.getTeacherByAccount(account));
				addTeacherSemester(teacherSemester);
			}
		}
		workbook.close();
		file.close();
	}

	@Override
	public void updateTeacherSemester(TeacherSemester teacherSemester) {
		teacherSemesterDAO.updateTeacherSemester(teacherSemester);
	}

	@Override
	public List<TeacherSemester> listTeacherSemesters(boolean jointTeacherCourseSemesters, boolean jointTimetables) {
		return teacherSemesterDAO.listTeacherSemesters(jointTeacherCourseSemesters, jointTimetables);
	}

	@Override
	public List<TeacherSemester> listTeacherSemestersBySemester(int semesterId, boolean jointTeacherCourseSemesters,
			boolean jointTimetables) {
		return teacherSemesterDAO.listTeacherSemestersBySemester(semesterId, jointTeacherCourseSemesters,
				jointTimetables);
	}

	@Override
	public TeacherSemester getTeacherSemesterById(int teacherSemesterId, boolean jointTeacherCourseSemesters,
			boolean jointTimetables) {
		return teacherSemesterDAO.getTeacherSemesterById(teacherSemesterId, jointTeacherCourseSemesters,
				jointTimetables);
	}

	@Override
	public void deleteTeacherSemester(int teacherSemesterId) {
		teacherSemesterDAO.deleteTeacherSemester(teacherSemesterId);
	}

	@Override
	public TeacherSemester getTeacherSemesterByAccount(int semesterId, String account,
			boolean jointTeacherCourseSemesters, boolean jointTimetables) {
		return teacherSemesterDAO.getTeacherSemesterByAccount(semesterId, account, jointTeacherCourseSemesters,
				jointTimetables);
	}

	@Override
	public TeacherSemester getTeacherSemesterByTeacherSemester(int teacherId, int semesterId,
			boolean jointTeacherCourseSemesters, boolean jointTimetables) {
		return teacherSemesterDAO.getTeacherSemesterByTeacherSemester(teacherId, semesterId,
				jointTeacherCourseSemesters, jointTimetables);
	}

	@Override
	public List<TeacherSemester> listTeacherSemestersForView(int semesterId) {
		List<TeacherSemester> result = new ArrayList<TeacherSemester>();

		for (TeacherSemester ts : listTeacherSemestersBySemester(semesterId, true, false)) {
			TeacherSemester teacherSemester = new TeacherSemester();

			teacherSemester.setTeacherSemesterId(ts.getTeacherSemesterId());

			Semester semester = new Semester();
			semester.setSemesterId(ts.getSemester().getSemesterId());
			semester.setName(ts.getSemester().getName());
			teacherSemester.setSemester(semester);

			Teacher teacher = new Teacher();
			teacher.setTeacherId(ts.getTeacher().getTeacherId());
			teacher.setAccount(ts.getTeacher().getAccount());
			teacher.setName(ts.getTeacher().getName());
			teacher.setEmail(ts.getTeacher().getEmail());
			teacherSemester.setTeacher(teacher);

			Set<TeacherCourseSemester> listTCS = new LinkedHashSet<TeacherCourseSemester>();
			for (TeacherCourseSemester tcs : ts.getTeacherCourseSemesters()) {
				TeacherCourseSemester teacherCourseSemester = new TeacherCourseSemester();
				teacherCourseSemester.setTeacherCourseSemesterId(tcs.getTeacherCourseSemesterId());

				CourseSemester courseSemester = new CourseSemester();
				courseSemester.setCourseSemesterId(tcs.getCourseSemester().getCourseSemesterId());

				Course course = new Course();
				course.setCourseId(tcs.getCourseSemester().getCourse().getCourseId());
				course.setCode(tcs.getCourseSemester().getCourse().getCode());
				courseSemester.setCourse(course);

				teacherCourseSemester.setCourseSemester(courseSemester);

				listTCS.add(teacherCourseSemester);
			}
			teacherSemester.setTeacherCourseSemesters(listTCS);

			result.add(teacherSemester);
		}
		return result;
	}

	@Override
	public List<TeacherSemester> listTeacherSemestersByCourse(int courseId) {
		return teacherSemesterDAO.listTeacherSemestersByCourse(courseId);
	}
}
