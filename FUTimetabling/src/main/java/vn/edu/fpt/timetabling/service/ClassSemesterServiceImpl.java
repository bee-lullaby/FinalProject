package vn.edu.fpt.timetabling.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
public class ClassSemesterServiceImpl implements ClassSemesterService {

	private ClassSemesterDAO classSemesterDAO;

	@Autowired
	private SemesterService semesterService;

	@Autowired
	private StudentService studentService;

	@Autowired
	private ProgramSemesterService programSemesterService;

	@Autowired
	private ClassService classService;

	@Autowired
	private ClassSemesterService classSemesterService;

	@Autowired
	private ClassCourseSemesterService classCourseSemesterService;

	@Autowired
	private ClassCourseStudentSemesterService classCourseStudentSemesterService;

	public void setClassSemesterDAO(ClassSemesterDAO classSemesterDAO) {
		this.classSemesterDAO = classSemesterDAO;
	}

	@Override
	@Transactional
	public void addClassSemester(ClassSemester classSemester) {
		classSemesterDAO.addClassSemester(classSemester);
	}

	@Override
	@Transactional
	public void updateClassSemester(ClassSemester classSemester) {
		classSemesterDAO.updateClassSemester(classSemester);
	}

	@Override
	@Transactional
	public List<ClassSemester> listClassSemesters(boolean jointClassCourseSemester) {
		return classSemesterDAO.listClassSemesters(jointClassCourseSemester);
	}

	@Override
	@Transactional
	public ClassSemester getClassSemesterById(int classSemesterId, boolean jointClassCourseSemester) {
		return classSemesterDAO.getClassSemesterById(classSemesterId, jointClassCourseSemester);
	}

	@Override
	@Transactional
	public ClassSemester getClassSemesterByClassSemester(int semesterId, int classId,
			boolean jointClassCourseSemester) {
		return classSemesterDAO.getClassSemesterByClassSemester(semesterId, classId, jointClassCourseSemester);
	}

	@Override
	@Transactional
	public ClassSemester getClassSemesterByCode(String classCode, int semesterId, boolean jointClassCourseSemester) {
		return classSemesterDAO.getClassSemesterByCode(classCode, semesterId, jointClassCourseSemester);
	}

	@Override
	@Transactional
	public void deleteClassSemester(int classSemesterId) {
		classSemesterDAO.deleteClassSemester(classSemesterId);
	}

	@Override
	@Transactional
	public long getNumberOfStudents(int classSemesterId) {
		return classSemesterDAO.getNumberOfStudents(classSemesterId);
	}

	@Override
	@Transactional
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
		if (getNumberOfStudents(classSemesterId) < Const.MAX_NUMBER_OF_STUDENTS_IN_CLASS && students.size() > 0) {
			for (Student student : students) {
				for (ClassCourseSemester classCourseSemester : classCourseSemesters) {
					ClassCourseStudentSemester classCourseStudentSemester = new ClassCourseStudentSemester();
					classCourseStudentSemester.setClassCourseSemester(classCourseSemester);
					classCourseStudentSemester.setStudent(student);
					classCourseStudentSemesterService.addClassCourseStudentSemester(classCourseStudentSemester);
				}
				student.setClassSemester(classSemester);
				if (getNumberOfStudents(classSemesterId) == Const.MAX_NUMBER_OF_STUDENTS_IN_CLASS) {
					break;
				}
			}
		}
	}

	@Override
	@Transactional
	public List<ClassSemester> listClassSemestersBySpecializedSemester(int semesterId, int specializedId,
			int detailSpecializedId, int semesterNumber) {
		return classSemesterDAO.listClassSemestersBySpecializedSemester(semesterId, specializedId, detailSpecializedId,
				semesterNumber);
	}

	@Transactional
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
		classSemesterService.addClassSemester(classSemester);
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
	@Transactional
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
				if (getNumberOfStudents(classSemester.getClassSemesterId()) < Const.MAX_NUMBER_OF_STUDENTS_IN_CLASS) {
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
	@Transactional
	public boolean isStudentInClassSemester(int studentId, int classSemesterId) {
		return classSemesterDAO.isStudentInClassSemester(studentId, classSemesterId);
	}

}
