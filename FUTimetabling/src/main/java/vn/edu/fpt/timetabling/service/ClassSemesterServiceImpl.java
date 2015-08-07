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

import vn.edu.fpt.timetabling.dao.ClassSemesterDAO;
import vn.edu.fpt.timetabling.model.ClassCourseSemester;
import vn.edu.fpt.timetabling.model.ClassCourseStudentSemester;
import vn.edu.fpt.timetabling.model.ClassFPT;
import vn.edu.fpt.timetabling.model.ClassSemester;
import vn.edu.fpt.timetabling.model.Course;
import vn.edu.fpt.timetabling.model.CourseSemester;
import vn.edu.fpt.timetabling.model.ProgramSemester;
import vn.edu.fpt.timetabling.model.ProgramSemesterDetail;
import vn.edu.fpt.timetabling.model.Semester;
import vn.edu.fpt.timetabling.model.Specialized;
import vn.edu.fpt.timetabling.model.Student;
import vn.edu.fpt.timetabling.utils.Const;
import vn.edu.fpt.timetabling.utils.Const.ClassType;

@Service
@Transactional(rollbackFor = Exception.class)
public class ClassSemesterServiceImpl implements ClassSemesterService {
	private ClassSemesterDAO classSemesterDAO;
	@Autowired
	private SemesterService semesterService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private ProgramSemesterService programSemesterService;
	@Autowired
	private ClassService classService;
	@Autowired
	private SpecializedService specializedService;
	@Autowired
	private ClassCourseSemesterService classCourseSemesterService;
	@Autowired
	private ClassCourseStudentSemesterService classCourseStudentSemesterService;

	public void setClassSemesterDAO(ClassSemesterDAO classSemesterDAO) {
		this.classSemesterDAO = classSemesterDAO;
	}

	@Override
	public void addClassSemester(ClassSemester classSemester) {
		classSemesterDAO.addClassSemester(classSemester);
	}

	@Override
	public void updateClassSemester(ClassSemester classSemester) {
		classSemesterDAO.updateClassSemester(classSemester);
	}

	@Override
	public List<ClassSemester> listClassSemesters(boolean jointClassCourseSemester) {
		return classSemesterDAO.listClassSemesters(jointClassCourseSemester);
	}

	@Override
	public List<ClassSemester> listClassSemestersBySemester(int semesterId, boolean jointClassCourseSemester) {
		return classSemesterDAO.listClassSemestersBySemester(semesterId, jointClassCourseSemester);
	}

	@Override
	public ClassSemester getClassSemesterById(int classSemesterId, boolean jointClassCourseSemester) {
		return classSemesterDAO.getClassSemesterById(classSemesterId, jointClassCourseSemester);
	}

	@Override
	public ClassSemester getClassSemesterByClassSemester(int semesterId, int classId,
			boolean jointClassCourseSemester) {
		return classSemesterDAO.getClassSemesterByClassSemester(semesterId, classId, jointClassCourseSemester);
	}

	@Override
	public ClassSemester getClassSemesterByCode(String classCode, int semesterId, boolean jointClassCourseSemester) {
		return classSemesterDAO.getClassSemesterByCode(classCode, semesterId, jointClassCourseSemester);
	}

	@Override
	public void deleteClassSemester(int classSemesterId) {
		classSemesterDAO.deleteClassSemester(classSemesterId);
	}

	@Override
	public long getNumberOfStudents(int classSemesterId) {
		return classSemesterDAO.getNumberOfStudents(classSemesterId);
	}

	private boolean isMaxStudent(Set<ClassCourseSemester> classCourseSemesters) {
		for (ClassCourseSemester classCourseSemester : classCourseSemesters) {
			if (classCourseSemesterService.getNumberOfStudents(classCourseSemester
					.getClassCourseSemesterId()) >= Const.StudentNumber.OPTIMAL_NUMBER_OF_STUDENTS_IN_CLASS) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void autoPutStudentsIntoClassSemester(int classSemesterId) {
		ClassSemester classSemester = getClassSemesterById(classSemesterId, true);
		if (classSemester == null) {
			return;
		}
		ClassFPT classFPT = classSemester.getClassFPT();
		int specializedId = classFPT.getSpecialized().getSpecializedId();
		int detailSpecializedId = 0;
		if (classFPT.getDetailSpecialized() != null) {
			detailSpecializedId = classFPT.getDetailSpecialized().getSpecializedId();
		}
		int semesterNumber = classSemester.getSemesterNumber();
		List<Student> students = studentService.listStudentsCanBeInClassCourseSemester(classSemesterId, specializedId,
				detailSpecializedId, semesterNumber, 0);
		Set<ClassCourseSemester> classCourseSemesters = classSemester.getClassCourseSemesters();
		if (!isMaxStudent(classCourseSemesters)) {
			if (students.size() > 0) {
				for (Student student : students) {
					for (ClassCourseSemester classCourseSemester : classCourseSemesters) {
						ClassCourseStudentSemester classCourseStudentSemester = new ClassCourseStudentSemester();
						classCourseStudentSemester.setClassCourseSemester(classCourseSemester);
						classCourseStudentSemester.setStudent(student);
						classCourseStudentSemesterService.addClassCourseStudentSemester(classCourseStudentSemester);
					}
					student.setClassSemester(classSemester);
					if (isMaxStudent(classCourseSemesters)) {
						break;
					}
				}
			}
		}
	}

	@Override
	public List<ClassSemester> listClassSemestersBySpecializedSemester(int semesterId, int specializedId,
			int detailSpecializedId, int semesterNumber) {
		return classSemesterDAO.listClassSemestersBySpecializedSemester(semesterId, specializedId, detailSpecializedId,
				semesterNumber);
	}

	private void createNewClass(Specialized specialized, Specialized detailSpecialized, Semester semester,
			int semesterNumber, Student student) {
		int specializedId = specialized.getSpecializedId();
		int detailSpecializedId = 0;
		int semesterId = semester.getSemesterId();
		if (detailSpecialized != null) {
			detailSpecializedId = detailSpecialized.getSpecializedId();
		}
		ClassFPT classFPT = new ClassFPT();
		classFPT.setType(ClassType.SPECIALIZED);
		classFPT.setSpecialized(specialized);
		classFPT.setDetailSpecialized(detailSpecialized);
		ProgramSemester programSemester = programSemesterService.getProgramSemesterBySpecializedSemester(semesterId,
				specializedId, detailSpecializedId, semesterNumber);
		int batch = programSemester.getBatch();
		classFPT.setBatch(batch);
		classFPT.setCourse(null);
		String classCodePrefix = classFPT.getSpecialized().getCode() + String.format("%02d", batch);
		classFPT.setNumber(classService.getNextClassNumber(classCodePrefix));
		classFPT.setCode(classCodePrefix + String.format("%02d", classFPT.getNumber()));
		classService.addClass(classFPT);
		ClassSemester classSemester = new ClassSemester();
		classSemester.setSemester(semester);
		classSemester.setClassFPT(classFPT);
		classSemester.setSemesterNumber(semesterNumber);
		addClassSemester(classSemester);
		Set<ProgramSemesterDetail> programSemesterDetails = programSemester.getProgramSemesterDetails();
		List<CourseSemester> courseSemesters = new ArrayList<CourseSemester>();
		CourseSemester semesterLong = null;
		for (ProgramSemesterDetail programSemesterDetail : programSemesterDetails) {
			CourseSemester courseSemester = programSemesterDetail.getCourseSemester();
			courseSemesters.add(courseSemester);
			if (programSemesterDetail.isSemesterLong()) {
				semesterLong = courseSemester;
			}
		}
		List<CourseSemester> allBlock = new ArrayList<CourseSemester>();
		List<CourseSemester> block1 = new ArrayList<CourseSemester>();
		List<CourseSemester> block2 = new ArrayList<CourseSemester>();
		for (int i = 0; i < courseSemesters.size(); i++) {
			CourseSemester courseSemester = courseSemesters.get(i);
			Course condition = courseSemester.getCourseCondition();
			boolean notCondition = true;
			for (int j = i + 1; j < courseSemesters.size(); j++) {
				CourseSemester courseSemesterTemp = courseSemesters.get(j);
				Course condition2 = courseSemesterTemp.getCourseCondition();
				if (condition != null && condition.getCourseId() == courseSemesterTemp.getCourse().getCourseId()) {
					block2.add(courseSemester);
					block1.add(courseSemesterTemp);
					courseSemesters.remove(i);
					courseSemesters.remove(j);
					notCondition = false;
					break;
				} else if (condition2 != null && courseSemester.getCourse().getCourseId() == condition2.getCourseId()) {
					block1.add(courseSemester);
					block2.add(courseSemesterTemp);
					courseSemesters.remove(i);
					courseSemesters.remove(j);
					notCondition = false;
					break;
				}
			}
			if (notCondition) {
				allBlock.add(courseSemester);
				courseSemesters.remove(i);
			}
			i--;
		}
		List<List<CourseSemester>> courseSemestersTemp = new ArrayList<List<CourseSemester>>();
		courseSemestersTemp.add(allBlock);
		courseSemestersTemp.add(block1);
		courseSemestersTemp.add(block2);
		for (List<CourseSemester> list : courseSemestersTemp) {
			for (CourseSemester courseSemester : list) {
				ClassCourseSemester classCourseSemester = new ClassCourseSemester();
				classCourseSemester.setClassSemester(classSemester);
				classCourseSemester.setCourseSemester(courseSemester);
				classCourseSemester.setBlockCondition(0);
				if (semesterLong != null
						&& courseSemester.getCourseSemesterId() == semesterLong.getCourseSemesterId()) {
					classCourseSemester.setSemesterLong(true);
				} else {
					classCourseSemester.setSemesterLong(false);
				}
				classCourseSemesterService.addClassCourseSemester(classCourseSemester);
				ClassCourseStudentSemester classCourseStudentSemester = new ClassCourseStudentSemester();
				classCourseStudentSemester.setClassCourseSemester(classCourseSemester);
				classCourseStudentSemester.setStudent(student);
				classCourseStudentSemesterService.addClassCourseStudentSemester(classCourseStudentSemester);
			}
		}
	}

	@Override
	public void autoPutStudentsIntoClassSemesters(int semesterId) {
		Semester semester = semesterService.getSemesterById(semesterId, false, false, false, false);
		if (semester == null) {
			return;
		}
		List<Student> students = studentService.listStudentsWithoutClass();
		for (Student student : students) {
			Specialized specialized = student.getSpecialized();
			Specialized detailSpecialized = student.getDetailSpecialized();
			int detailSpecializedId = 0;
			if (detailSpecialized != null) {
				detailSpecializedId = detailSpecialized.getSpecializedId();
			}
			int semesterNumber = student.getSemester();
			List<ClassSemester> classSemesters = listClassSemestersBySpecializedSemester(semesterId,
					specialized.getSpecializedId(), detailSpecializedId, semesterNumber);
			boolean put = false;
			for (ClassSemester classSemester : classSemesters) {
				Set<ClassCourseSemester> classCourseSemesters = classSemester.getClassCourseSemesters();
				if (!isMaxStudent(classCourseSemesters)) {
					for (ClassCourseSemester classCourseSemester : classCourseSemesters) {
						ClassCourseStudentSemester classCourseStudentSemester = new ClassCourseStudentSemester();
						classCourseStudentSemester.setClassCourseSemester(classCourseSemester);
						classCourseStudentSemester.setStudent(student);
						classCourseStudentSemesterService.addClassCourseStudentSemester(classCourseStudentSemester);
					}
					student.setClassSemester(classSemester);
					put = true;
					break;
				}
			}
			if (!put) {
				createNewClass(specialized, detailSpecialized, semester, semesterNumber, student);
			}
		}
	}

	@Override
	public boolean isStudentInClassSemester(int studentId, int classSemesterId) {
		return classSemesterDAO.isStudentInClassSemester(studentId, classSemesterId);
	}

	@Override
	public List<ClassSemester> listClassSemesterForView(int semesterId) {
		List<ClassSemester> result = new ArrayList<ClassSemester>();

		for (ClassSemester cs : listClassSemestersBySemester(semesterId, true)) {
			ClassSemester classSemester = new ClassSemester();
			classSemester.setClassSemesterId(cs.getClassSemesterId());

			Semester semester = new Semester();
			semester.setSemesterId(cs.getSemester().getSemesterId());
			semester.setName(cs.getSemester().getName());
			classSemester.setSemester(semester);
			ClassFPT classFPT = new ClassFPT();
			classFPT.setClassId(cs.getClassFPT().getClassId());
			classFPT.setCode(cs.getClassFPT().getCode());
			classFPT.setType(cs.getClassFPT().getType());

			if (cs.getClassFPT().getBatch() != null) {
				classFPT.setBatch(cs.getClassFPT().getBatch());
			}

			String testChar = "" + cs.getClassFPT().getBatchChar();
			if (testChar.compareTo("") != 0) {
				classFPT.setBatchChar(cs.getClassFPT().getBatchChar());
			}

			if (cs.getClassFPT().getSpecialized() != null) {
				Specialized specialized = new Specialized();
				specialized.setSpecializedId(cs.getClassFPT().getSpecialized().getSpecializedId());
				specialized.setName(cs.getClassFPT().getSpecialized().getName());
				classFPT.setSpecialized(specialized);
			}

			if (cs.getClassFPT().getDetailSpecialized() != null) {
				Specialized specialized = new Specialized();
				specialized.setSpecializedId(cs.getClassFPT().getDetailSpecialized().getSpecializedId());
				specialized.setName(cs.getClassFPT().getDetailSpecialized().getName());
				classFPT.setDetailSpecialized(specialized);
			}

			classSemester.setClassFPT(classFPT);
			classSemester.setSemesterNumber(cs.getSemesterNumber());
			Set<ClassCourseSemester> listCCS = new LinkedHashSet<ClassCourseSemester>();
			for (ClassCourseSemester ccs : cs.getClassCourseSemesters()) {
				ClassCourseSemester classCourseSemester = new ClassCourseSemester();
				classCourseSemester.setClassCourseSemesterId(ccs.getClassCourseSemesterId());

				CourseSemester courseSemester = new CourseSemester();
				courseSemester.setCourseSemesterId(ccs.getCourseSemester().getCourseSemesterId());

				Course course = new Course();
				course.setCourseId(ccs.getCourseSemester().getCourse().getCourseId());
				course.setCode(ccs.getCourseSemester().getCourse().getCode());
				courseSemester.setCourse(course);

				classCourseSemester.setCourseSemester(courseSemester);
				classCourseSemester.setBlockCondition(ccs.getBlockCondition());
				classCourseSemester.setSemesterLong(ccs.isSemesterLong());
				listCCS.add(classCourseSemester);
			}
			classSemester.setClassCourseSemesters(listCCS);
			result.add(classSemester);
		}

		return result;
	}

	@Override
	public void addClassSemesterFromFile(File classSemesters, int semesterId) throws IOException {
		classCourseStudentSemesterService.deleteClassCourseStudentSemesters(semesterId);
		classCourseSemesterService.deleteClassCourseSemesters(semesterId);
		classSemesterDAO.deleteClassSemesters(semesterId);
		FileInputStream file = new FileInputStream(classSemesters);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.iterator();
		rowIterator.next();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			String code = row.getCell(0).getStringCellValue().trim();
			ClassFPT classFPT = classService.getClassByCode(code);
			if (classFPT == null) {
				classFPT = new ClassFPT();
			}
			classFPT.setCode(code);
			String type = row.getCell(1).getStringCellValue().trim();
			classFPT.setType(type);
			if (row.getCell(2) != null) {
				classFPT.setSpecialized(specializedService
						.getSpecializedByCode(row.getCell(2).getStringCellValue().trim(), false, false));
			}
			if (row.getCell(3) != null) {
				classFPT.setDetailSpecialized(specializedService
						.getSpecializedByCode(row.getCell(3).getStringCellValue().trim(), false, false));
			}
			if (row.getCell(4) != null) {
				classFPT.setCourse(courseService.getCourseByCode(row.getCell(4).getStringCellValue().trim()));
			}
			if (row.getCell(5) != null) {
				classFPT.setBatch(Double.valueOf(row.getCell(5).getNumericCellValue()).intValue());
			}
			if (row.getCell(6) != null) {
				classFPT.setBatchChar(row.getCell(6).getStringCellValue().trim().charAt(0));
			}
			if (row.getCell(7) != null) {
				classFPT.setNumber(Double.valueOf(row.getCell(7).getNumericCellValue()).intValue());
			}
			if (classFPT.getClassId() == 0) {
				classService.addClass(classFPT);
			} else {
				classService.updateClass(classFPT);
			}
			ClassSemester classSemester = getClassSemesterByCode(code, semesterId, false);
			if (classSemester == null) {
				classSemester = new ClassSemester();
			}
			classSemester.setClassFPT(classFPT);
			classSemester.setSemester(semesterService.getSemesterById(semesterId, false, false, false, false));
			if (row.getCell(8) != null) {
				classSemester.setSemesterNumber(Double.valueOf(row.getCell(8).getNumericCellValue()).intValue());
			}
			if (classSemester.getClassSemesterId() == 0) {
				addClassSemester(classSemester);
			} else {
				updateClassSemester(classSemester);
			}
			int specializedId = classFPT.getSpecialized().getSpecializedId();
			int detailSpecializedId = 0;
			if (classFPT.getDetailSpecialized() != null) {
				detailSpecializedId = classFPT.getDetailSpecialized().getSpecializedId();
			}
			int semesterNumber = classSemester.getSemesterNumber();
			ProgramSemester programSemester = programSemesterService.getProgramSemesterBySpecializedSemester(semesterId,
					specializedId, detailSpecializedId, semesterNumber);
			Set<ProgramSemesterDetail> programSemesterDetails = programSemester.getProgramSemesterDetails();
			List<Course> courses1 = new ArrayList<Course>();
			List<Course> courses2 = new ArrayList<Course>();
			for (ProgramSemesterDetail programSemesterDetail : programSemesterDetails) {
				CourseSemester courseSemester = programSemesterDetail.getCourseSemester();
				Course course = courseSemester.getCourseCondition();
				if (course != null) {
					courses1.add(courseSemester.getCourse());
					courses2.add(course);
				}
			}
			for (ProgramSemesterDetail programSemesterDetail : programSemesterDetails) {
				CourseSemester courseSemester = programSemesterDetail.getCourseSemester();
				ClassCourseSemester classCourseSemester = new ClassCourseSemester();
				classCourseSemester.setClassSemester(classSemester);
				classCourseSemester.setCourseSemester(courseSemester);
				classCourseSemester.setSemesterLong(programSemesterDetail.isSemesterLong());
				if (!programSemesterDetail.isSemesterLong()) {
					classCourseSemester.setBlockCondition(0);
					for (Course course : courses1) {
						if (course.getCode().equals(courseSemester.getCourse().getCode())) {
							classCourseSemester.setBlockCondition(1);
						}
					}
					for (Course course : courses2) {
						if (course.getCode().equals(courseSemester.getCourse().getCode())) {
							classCourseSemester.setBlockCondition(2);
						}
					}
				}
				classCourseSemesterService.addClassCourseSemester(classCourseSemester);
			}
		}
		workbook.close();
		file.close();
	}

	@Override
	public int deleteClassSemesters(int semesterId) {
		return classSemesterDAO.deleteClassSemesters(semesterId);
	}
}
