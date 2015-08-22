package vn.edu.fpt.timetabling.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.fpt.timetabling.dao.ClassCourseStudentSemesterDAO;
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
public class ClassCourseStudentSemesterServiceImpl implements ClassCourseStudentSemesterService {
	private ClassCourseStudentSemesterDAO classCourseStudentSemesterDAO;
	@Autowired
	private SemesterService semesterService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private ClassSemesterService classSemesterService;
	@Autowired
	private ProgramSemesterService programSemesterService;
	@Autowired
	private ClassService classService;
	@Autowired
	private ClassCourseSemesterService classCourseSemesterService;

	public void setClassCourseStudentSemesterDAO(ClassCourseStudentSemesterDAO classCourseStudentSemesterDAO) {
		this.classCourseStudentSemesterDAO = classCourseStudentSemesterDAO;
	}

	@Override
	public void addClassCourseStudentSemester(ClassCourseStudentSemester classCourseStudentSemester) {
		classCourseStudentSemesterDAO.addClassCourseStudentSemester(classCourseStudentSemester);
	}

	@Override
	public void updateClassCourseStudentSemester(ClassCourseStudentSemester classCourseStudentSemester) {
		classCourseStudentSemesterDAO.updateClassCourseStudentSemester(classCourseStudentSemester);
	}

	@Override
	public List<ClassCourseStudentSemester> listClassCourseStudentSemesters() {
		return classCourseStudentSemesterDAO.listClassCourseStudentSemesters();
	}

	@Override
	public ClassCourseStudentSemester getClassCourseStudentSemesterById(int classCourseStudentSemesterId) {
		return classCourseStudentSemesterDAO.getClassCourseStudentSemesterById(classCourseStudentSemesterId);
	}

	@Override
	public void deleteClassCourseStudentSemester(int classCourseStudentSemesterId) {
		classCourseStudentSemesterDAO.deleteClassCourseStudentSemester(classCourseStudentSemesterId);
	}

	@Override
	public int removeStudentFromClassCourseSemester(int studentId, int classCourseSemesterId) {
		return classCourseStudentSemesterDAO.removeStudentFromClassCourseSemester(studentId, classCourseSemesterId);
	}

	@Override
	public int deleteClassCourseStudentSemesters(int semesterId) {
		return classCourseStudentSemesterDAO.deleteClassCourseStudentSemesters(semesterId);
	}

	@Override
	public int deleteClassCourseStudentSemesterByClass(int classSemesterId) {
		return classCourseStudentSemesterDAO.deleteClassCourseStudentSemesterByClass(classSemesterId);
	}

	private ClassSemester createNewClass(Specialized specialized, Specialized detailSpecialized, Semester semester,
			int semesterNumber) {
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
		System.out.println("Create new class: " + specialized.getCode() + "/" + detailSpecialized.getCode() + "/"
				+ semesterNumber);
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
		Set<ClassCourseSemester> classCourseSemesters = new HashSet<>();
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
				classCourseSemesters.add(classCourseSemester);
			}
		}
		classSemester.setClassCourseSemesters(classCourseSemesters);
		return classSemester;
	}

	public void addClassCourseStudentSemesters(ClassSemester classSemester, Student student) {
		System.out.println("Put student: " + student.getAccount() + " in " + classSemester.getClassFPT().getCode());
		for (ClassCourseSemester classCourseSemester : classSemester.getClassCourseSemesters()) {
			ClassCourseStudentSemester classCourseStudentSemester = new ClassCourseStudentSemester();
			classCourseStudentSemester.setClassCourseSemester(classCourseSemester);
			classCourseStudentSemester.setStudent(student);
			addClassCourseStudentSemester(classCourseStudentSemester);
		}
	}

	@Override
	public void autoPutStudentsIntoClassSemesters(int semesterId) {
		deleteClassCourseStudentSemesters(semesterId);
		Semester semester = semesterService.getSemesterById(semesterId, true, false, false, false);
		if (semester == null) {
			return;
		}
		List<Student> students = studentService.listStudentsWithoutClass();
		HashMap<String, List<Student>> studentsMap = new HashMap<>();
		for (Student student : students) {
			String key = student.getSpecialized().getCode() + "/" + student.getDetailSpecialized().getCode() + "/"
					+ student.getSemester();
			List<Student> studentsGroup;
			if (studentsMap.containsKey(key)) {
				studentsGroup = studentsMap.get(key);
			} else {
				studentsGroup = new ArrayList<>();
				studentsMap.put(key, studentsGroup);
			}
			studentsGroup.add(student);
		}
		for (Entry<String, List<Student>> entry : studentsMap.entrySet()) {
			System.out.println("Group student: " + entry.getKey());
			List<Student> studentsGroup = entry.getValue();
			Student temp = studentsGroup.get(0);
			Specialized specialized = temp.getSpecialized();
			Specialized detailSpecialized = temp.getDetailSpecialized();
			int semesterNumber = temp.getSemester();
			List<ClassSemester> classSemesters = classSemesterService.listClassSemestersBySpecializedSemester(
					semesterId, specialized.getSpecializedId(), detailSpecialized.getSpecializedId(), semesterNumber);
			int size = studentsGroup.size();
			if (size > 2 * Const.StudentNumber.MAX_NUMBER_OF_STUDENTS_IN_CLASS) {
				int numberClassFull = size / Const.StudentNumber.MAX_NUMBER_OF_STUDENTS_IN_CLASS - 1;
				int numberOldClassUsed = Math.min(numberClassFull, classSemesters.size());
				for (int i = 0; i < numberOldClassUsed; i++) {
					ClassSemester classSemester = classSemesters.remove(0);
					for (int j = 0; j < Const.StudentNumber.MAX_NUMBER_OF_STUDENTS_IN_CLASS; j++) {
						addClassCourseStudentSemesters(classSemester, studentsGroup.remove(0));
					}
				}
				int numberNewFullClasses = numberClassFull - numberOldClassUsed;
				for (int i = 0; i < numberNewFullClasses; i++) {
					ClassSemester classSemester = createNewClass(specialized, detailSpecialized, semester,
							semesterNumber);
					for (int j = 0; j < Const.StudentNumber.MAX_NUMBER_OF_STUDENTS_IN_CLASS; j++) {
						addClassCourseStudentSemesters(classSemester, studentsGroup.remove(0));
					}
				}
			}
			size = studentsGroup.size();
			if (2 * Const.StudentNumber.MAX_NUMBER_OF_STUDENTS_IN_CLASS >= size
					&& size > Const.StudentNumber.MAX_NUMBER_OF_STUDENTS_IN_CLASS) {
				int sizeOne = (int) Math.ceil(size / 2.0);
				ClassSemester classSemester;
				if (classSemesters.size() > 0) {
					classSemester = classSemesters.remove(0);
				} else {
					classSemester = createNewClass(specialized, detailSpecialized, semester, semesterNumber);
				}
				for (int i = 0; i < sizeOne; i++) {
					addClassCourseStudentSemesters(classSemester, studentsGroup.remove(0));
				}
			}
			if (Const.StudentNumber.MAX_NUMBER_OF_STUDENTS_IN_CLASS >= studentsGroup.size()) {
				ClassSemester classSemester;
				if (classSemesters.size() > 0) {
					classSemester = classSemesters.remove(0);
				} else {
					classSemester = createNewClass(specialized, detailSpecialized, semester, semesterNumber);

				}
				for (Student studentTemp : studentsGroup) {
					addClassCourseStudentSemesters(classSemester, studentTemp);
				}
			}
		}
		for (ClassSemester classSemester : semester.getClassSemesters()) {
			System.out.println("Number of student in class " + classSemester.getClassFPT().getCode() + ": "
					+ classSemesterService.getNumberOfStudents(classSemester.getClassSemesterId()));
		}
	}

	@Override
	public void autoPutStudentsIntoClassSemester(int classSemesterId) {
		ClassSemester classSemester = classSemesterService.getClassSemesterById(classSemesterId, true);
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
		while (classSemesterService
				.getNumberOfStudents(classSemesterId) < Const.StudentNumber.MAX_NUMBER_OF_STUDENTS_IN_CLASS
				&& !students.isEmpty()) {
			addClassCourseStudentSemesters(classSemester, students.remove(0));
		}
	}
}
