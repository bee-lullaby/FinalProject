package vn.edu.fpt.timetabling.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.edu.fpt.timetabling.dao.ClassSemesterDAO;
import vn.edu.fpt.timetabling.dao.CourseSemesterDAO;
import vn.edu.fpt.timetabling.dao.SemesterDAO;
import vn.edu.fpt.timetabling.dao.TimetableDAO;
import vn.edu.fpt.timetabling.model.ClassCourseSemester;
import vn.edu.fpt.timetabling.model.ClassSemester;
import vn.edu.fpt.timetabling.model.CourseSemester;
import vn.edu.fpt.timetabling.model.DataSchedule;
import vn.edu.fpt.timetabling.model.DaySlot;
import vn.edu.fpt.timetabling.model.Semester;
import vn.edu.fpt.timetabling.model.TeacherCourseSemester;
import vn.edu.fpt.timetabling.model.Timetable;
import vn.edu.fpt.timetabling.utils.TimetableUtils;

@Service
public class ScheduleServiceImpl implements ScheduleService {

	private static final Logger logger = LoggerFactory
			.getLogger(ScheduleServiceImpl.class);

	@Autowired
	private SemesterDAO semesterDAO;

	@Autowired
	private ClassSemesterDAO classSemesterDAO;

	@Autowired
	private CourseSemesterDAO courseSemesterDAO;

	@Autowired
	private TimetableDAO timetableDAO;

	@Transactional
	@Override
	public ClassSemester getClassSemesterByClassSemester(int semesterId,
			int classId) {
		return classSemesterDAO.getClassSemesterByClassSemester(semesterId,
				classId, true);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<ClassSemester> listClassBySemester(int semesterId) {
		return (List<ClassSemester>) semesterDAO.getSemesterById(semesterId,
				true, false, false, false).getClassSemesters();
	}

	@Transactional
	@Override
	public List<ClassCourseSemester> listClassCourseSemesterByClassSemester(
			int classId, int semesterId) {
		ClassSemester classSemester = classSemesterDAO
				.getClassSemesterByClassSemester(semesterId, classId, true);
		List<ClassCourseSemester> classCourseSemesters = new ArrayList<ClassCourseSemester>();
		classCourseSemesters.addAll(classSemester.getClassCourseSemester());
		return classCourseSemesters;
	}

	@Transactional
	@Override
	public List<CourseSemester> listCourseSemesterByClass(int classId,
			int semesterId) {
		// TODO Auto-generated method stub
		ClassSemester classSemester = classSemesterDAO
				.getClassSemesterByClassSemester(semesterId, classId, true);
		Set<ClassCourseSemester> classCourseSemesters = classSemester
				.getClassCourseSemester();

		List<CourseSemester> courseSemesters = new ArrayList<CourseSemester>();
		for (ClassCourseSemester classCourseSemester : classCourseSemesters) {
			courseSemesters.add(courseSemesterDAO.getCourseSemesterById(
					classCourseSemester.getCourseSemester()
							.getCourseSemesterId(), true, true, false));
		}

		return courseSemesters;
	}

	@Transactional
	@Override
	public List<TeacherCourseSemester> listTeacherByCourseSemester(int classId,
			int semesterId) {
		// TODO Auto-generated method stub

		List<CourseSemester> courseSemesters = listCourseSemesterByClass(
				classId, semesterId);
		List<TeacherCourseSemester> teacherCourseSemesters = new ArrayList<TeacherCourseSemester>();
		for (CourseSemester courseSemester : courseSemesters) {
			teacherCourseSemesters.addAll(courseSemester
					.getTeacherCourseSemesters());
		}
		return teacherCourseSemesters;
	}

	@Transactional
	@Override
	public List<Timetable> listTimetableOfClassCourseSemester(
			List<ClassCourseSemester> classCourseSemesters) {
		// TODO Auto-generated method stub
		return timetableDAO.listTimetablesByCCSId(classCourseSemesters);
	}

	@Transactional
	@Override
	public List<DaySlot> getListDaySlot(int semesterId, int classId, int week) {

		Semester semester = semesterDAO.getSemesterById(semesterId, false,
				false, false, false);
		Date date = semester.getStartDate();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		cal.set(Calendar.DATE, cal.get(Calendar.DATE) - 1 + 7 * (week - 1));
		logger.info(cal.get(Calendar.DATE) + " " + cal.get(Calendar.MONTH));

		// ClassSemester classSemester =
		// getClassSemesterByClassSemester(semesterId, classId);

		// Get List Class Course Semester based on Class//
		List<ClassCourseSemester> classCourseSemesters = listClassCourseSemesterByClassSemester(
				classId, semesterId);
		// Get List Timetable based on Class //
		List<Timetable> timetables = listTimetableOfClassCourseSemester(classCourseSemesters);

		// Create List Course Semester of Class //
		List<CourseSemester> courseSemesters = new ArrayList<CourseSemester>();
		for (ClassCourseSemester classCourseSemester : classCourseSemesters) {
			courseSemesters.add(courseSemesterDAO.getCourseSemesterById(
					classCourseSemester.getCourseSemester()
							.getCourseSemesterId(), true, true, false));
		}

		// Create Map between course and timetable //
		HashMap<CourseSemester, List<Timetable>> mapCourseTimetable = new HashMap<CourseSemester, List<Timetable>>();
		for (CourseSemester courseSemester : courseSemesters) {
			List<ClassCourseSemester> list = new ArrayList<ClassCourseSemester>();
			list.addAll(courseSemester.getClassCourseSemesters());
			mapCourseTimetable.put(courseSemester,
					timetableDAO.listTimetablesByCCSId(list));
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<DaySlot> list = new ArrayList<DaySlot>();
		
		
		for (int i = 0; i < 7; i++) {
			for (int j = 1; j < 7; j++) {
				
				DaySlot ds = new DaySlot();
				HashMap<String, DataSchedule> dataSchedule = new HashMap<String, DataSchedule>();
				
				ds.setDate(sdf.format(cal.getTime()));
				ds.setSlot(j);
				Object o = TimetableUtils.containsTimetable(timetables,
						cal.getTime(), j);
				if (o != null) {
					Timetable t = (Timetable) o;
					ds.setSetCourseSlot(t.getClassCourseSemester()
							.getClassCourseSemesterId());
				} else {
					ds.setSetCourseSlot(-1);
				}
				
				for(CourseSemester cs : courseSemesters) {
					DataSchedule dataS = new DataSchedule();
					dataS.setNumOfClasses(cs.getClassCourseSemesters().size());
					dataS.setClassInSlot(TimetableUtils.findNumberSameDaySlot(mapCourseTimetable.get(cs), cal.getTime(), j));
					dataS.setNumOfTeachers(cs.getTeacherCourseSemesters().size());
					dataSchedule.put(cs.getCourse().getCode(), dataS);
				}
				ds.setDataSchedule(dataSchedule);
				list.add(ds);
			}
			cal.set(Calendar.DATE, cal.get(Calendar.DATE) + 1);
		}
		return list;
	}
}
