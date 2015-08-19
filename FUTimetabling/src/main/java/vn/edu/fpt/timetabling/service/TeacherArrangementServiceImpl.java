package vn.edu.fpt.timetabling.service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.fpt.timetabling.model.ClassCourseSemester;
import vn.edu.fpt.timetabling.model.ClassFPT;
import vn.edu.fpt.timetabling.model.ClassSemester;
import vn.edu.fpt.timetabling.model.Course;
import vn.edu.fpt.timetabling.model.CourseSemester;
import vn.edu.fpt.timetabling.model.DataTeacherArrangement;
import vn.edu.fpt.timetabling.model.Department;
import vn.edu.fpt.timetabling.model.Teacher;
import vn.edu.fpt.timetabling.model.TeacherCourseSemester;
import vn.edu.fpt.timetabling.model.TeacherSemester;
import vn.edu.fpt.timetabling.model.Timetable;

@Service
@Transactional(rollbackFor = Exception.class)
public class TeacherArrangementServiceImpl implements TeacherArrangementService {
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private CourseSemesterService courseSemesterService;
	@Autowired
	private ClassCourseSemesterService classCourseSemesterService;
	@Autowired
	private TimetableService timetableService;

	@Override
	public List<Department> getListDepartment() {
		List<Department> result = new ArrayList<Department>();

		for (Department d : departmentService.listDepartments()) {
			Department department = new Department();
			department.setDepartmentId(d.getDepartmentId());
			department.setName(d.getName());

			result.add(department);
		}

		return result;
	}

	@Override
	public List<Course> getListCourse(int departmentId) {
		List<Course> results = new ArrayList<Course>();
		Department department = departmentService.getDepartmentById(departmentId);
		for (Course course : department.getCourses()) {
			Course newCourse = new Course();
			newCourse.setCourseId(course.getCourseId());
			newCourse.setCode(course.getCode());
			results.add(newCourse);
		}

		return results;
	}

	@Override
	public CourseSemester getCourseSemester(int courseSemesterId) {

		CourseSemester courseSemester = new CourseSemester();

		CourseSemester cs = courseSemesterService.getCourseSemesterById(courseSemesterId, true, true, false);

		// set CourseSemeter ID
		courseSemester.setCourseSemesterId(cs.getCourseSemesterId());

		// set Course
		Course newCourse = new Course();
		newCourse.setCourseId(cs.getCourse().getCourseId());
		newCourse.setCode(cs.getCourse().getCode());

		courseSemester.setCourse(newCourse);

		// Set ClassCourseSemester
		Set<ClassCourseSemester> classCourseSemesters = new LinkedHashSet<ClassCourseSemester>();
		for (ClassCourseSemester classCourseSemester : cs.getClassCourseSemesters()) {
			ClassCourseSemester newCCS = new ClassCourseSemester();
			newCCS.setClassCourseSemesterId(classCourseSemester.getClassCourseSemesterId());

			ClassSemester newClassSemester = new ClassSemester();
			ClassFPT classFPT = new ClassFPT();
			classFPT.setClassId(classCourseSemester.getClassSemester().getClassFPT().getClassId());
			classFPT.setCode(classCourseSemester.getClassSemester().getClassFPT().getCode());
			newClassSemester.setClassSemesterId(classCourseSemester.getClassSemester().getClassSemesterId());
			newClassSemester.setClassFPT(classFPT);

			newCCS.setClassSemester(newClassSemester);

			Set<Timetable> newTimetable = new LinkedHashSet<Timetable>();
			for (Timetable t : classCourseSemesterService
					.getClassCourseSemesterById(classCourseSemester.getClassCourseSemesterId(), true, false)
					.getTimetable()) {

				Timetable newT = new Timetable();
				newT.setTimeTableId(t.getTimeTableId());
				newT.setDate(t.getDate());
				newT.setSlot(t.getSlot());

				TeacherSemester ts = new TeacherSemester();
				if (t.getTeacherSemester() != null) {
					ts.setTeacherSemesterId(t.getTeacherSemester().getTeacherSemesterId());
					Teacher teacher = new Teacher();
					teacher.setTeacherId(t.getTeacherSemester().getTeacher().getTeacherId());
					teacher.setAccount(t.getTeacherSemester().getTeacher().getAccount());
					teacher.setName(t.getTeacherSemester().getTeacher().getName());
					ts.setTeacher(teacher);
				}
				newT.setTeacherSemester(ts);
				newTimetable.add(newT);
			}
			newCCS.setTimetable(newTimetable);

			classCourseSemesters.add(newCCS);
		}
		courseSemester.setClassCourseSemesters(classCourseSemesters);

		// Set TeacherCourseSemester
		Set<TeacherCourseSemester> teacherCourseSemesters = new LinkedHashSet<TeacherCourseSemester>();
		for (TeacherCourseSemester tcs : cs.getTeacherCourseSemesters()) {
			TeacherCourseSemester newTCS = new TeacherCourseSemester();

			newTCS.setTeacherCourseSemesterId(tcs.getTeacherCourseSemesterId());

			TeacherSemester ts = new TeacherSemester();
			ts.setTeacherSemesterId(tcs.getTeacherSemester().getTeacherSemesterId());
			Teacher teacher = new Teacher();
			teacher.setTeacherId(tcs.getTeacherSemester().getTeacher().getTeacherId());
			teacher.setAccount(tcs.getTeacherSemester().getTeacher().getAccount());
			ts.setTeacher(teacher);

			newTCS.setTeacherSemester(ts);

			teacherCourseSemesters.add(newTCS);
		}
		courseSemester.setTeacherCourseSemesters(teacherCourseSemesters);
		return courseSemester;
	}

	@Override
	public List<DataTeacherArrangement> getDataTeacherArrangement(int semesterId, int courseId) {
		List<DataTeacherArrangement> dta = new ArrayList<DataTeacherArrangement>();

		CourseSemester cs = courseSemesterService.getCourseSemesterByCourseSemester(courseId, semesterId, true, true,
				false);

		Set<ClassCourseSemester> classCourseSemesters = cs.getClassCourseSemesters();

		Set<ClassCourseSemester> reCreate = new LinkedHashSet<ClassCourseSemester>();
		for (ClassCourseSemester ccs : classCourseSemesters) {
			reCreate.add(
					classCourseSemesterService.getClassCourseSemesterById(ccs.getClassCourseSemesterId(), true, false));
		}

		for (ClassCourseSemester classCourseSemester : reCreate) {
			DataTeacherArrangement dtaObj = new DataTeacherArrangement();

			ClassCourseSemester newCCS = new ClassCourseSemester();
			newCCS.setClassCourseSemesterId(classCourseSemester.getClassCourseSemesterId());

			ClassSemester classSemester = new ClassSemester();
			classSemester.setClassSemesterId(classCourseSemester.getClassSemester().getClassSemesterId());
			ClassFPT classFPT = new ClassFPT();
			classFPT.setClassId(classCourseSemester.getClassSemester().getClassFPT().getClassId());
			classFPT.setCode(classCourseSemester.getClassSemester().getClassFPT().getCode());
			newCCS.setClassSemester(classSemester);

			CourseSemester courseSemester = new CourseSemester();
			courseSemester.setCourseSemesterId(classCourseSemester.getCourseSemester().getCourseSemesterId());
			Course course = new Course();
			course.setCourseId(classCourseSemester.getCourseSemester().getCourse().getCourseId());
			course.setCode(classCourseSemester.getCourseSemester().getCourse().getCode());
			courseSemester.setCourse(course);
			newCCS.setCourseSemester(courseSemester);

			dtaObj.setClassCourseSemester(newCCS);

			Set<ClassCourseSemester> listConflict = getListClassConflicts(classCourseSemester, reCreate);
			if (listConflict != null && !listConflict.isEmpty()) {
				dtaObj.setConflictClasses(listConflict);
			}

			dta.add(dtaObj);
		}
		return dta;
	}

	public Set<ClassCourseSemester> getListClassConflicts(ClassCourseSemester classCourseSemester,
			Set<ClassCourseSemester> classCourseSemesters) {
		Set<ClassCourseSemester> result = new LinkedHashSet<ClassCourseSemester>();

		for (ClassCourseSemester ccs : classCourseSemesters) {
			if (ccs.getClassCourseSemesterId() == classCourseSemester.getClassCourseSemesterId()) {
				continue;
			}
			boolean check = false;
			for (Timetable timetableToCheck : classCourseSemester.getTimetable()) {
				for (Timetable timetableOfClass : ccs.getTimetable()) {
					if (timetableToCheck.getDate().equals(timetableOfClass.getDate())
							&& timetableToCheck.getSlot() == timetableOfClass.getSlot()) {
						ClassCourseSemester newCCS = new ClassCourseSemester();
						newCCS.setClassCourseSemesterId(ccs.getClassCourseSemesterId());

						ClassSemester classSemester = new ClassSemester();
						classSemester.setClassSemesterId(ccs.getClassSemester().getClassSemesterId());
						ClassFPT classFPT = new ClassFPT();
						classFPT.setClassId(ccs.getClassSemester().getClassFPT().getClassId());
						classFPT.setCode(ccs.getClassSemester().getClassFPT().getCode());
						classSemester.setClassFPT(classFPT);
						newCCS.setClassSemester(classSemester);

						CourseSemester courseSemester = new CourseSemester();
						courseSemester.setCourseSemesterId(ccs.getCourseSemester().getCourseSemesterId());
						Course course = new Course();
						course.setCourseId(ccs.getCourseSemester().getCourse().getCourseId());
						course.setCode(ccs.getCourseSemester().getCourse().getCode());
						courseSemester.setCourse(course);
						newCCS.setCourseSemester(courseSemester);

						result.add(newCCS);
						check = true;
						break;
					}
				}
				if (check)
					break;
			}
		}

		return result;
	}

	public boolean updateTimetable(List<Timetable> timetables) {
		for (Timetable timetable : timetables) {
			Timetable t = timetableService.getTimetableById(timetable.getTimeTableId());

			if (timetable.getTeacherSemester() != null) {
				t.setTeacherSemester(timetable.getTeacherSemester());
			} else {
				t.setTeacherSemester(null);
			}
			timetableService.updateTimetable(t);
		}
		return true;
	}
}
