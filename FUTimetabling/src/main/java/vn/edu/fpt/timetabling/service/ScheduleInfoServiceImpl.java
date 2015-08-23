package vn.edu.fpt.timetabling.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.fpt.timetabling.model.ClassCourseSemester;
import vn.edu.fpt.timetabling.model.ClassSemester;

@Service
@Transactional(rollbackFor = Exception.class)
public class ScheduleInfoServiceImpl implements ScheduleInfoService {
	@Autowired
	private ClassSemesterService classSemesterService;

	// Get List Class Was Set Course
	@Override
	public List<ClassCourseSemester> getListClassWasSetCourse(int semesterId) {

		List<ClassCourseSemester> result = new ArrayList<ClassCourseSemester>();
		List<ClassSemester> classSemesters = classSemesterService
				.listClassSemestersBySemester(semesterId, true);

		for (ClassSemester classSemester : classSemesters) {
			if (!classSemester.getClassCourseSemesters().isEmpty())
				result.addAll(classSemester.getClassCourseSemesters());
		}

		return result;
	}

	// Get List From ClassSemester and check set timetable
	@Override
	public List<ClassCourseSemester> getListClassWasSetTimetablesDone(int semesterId) {

		List<ClassCourseSemester> result = new ArrayList<ClassCourseSemester>();
		List<ClassSemester> classSemesters = classSemesterService
				.listClassSemestersBySemester(semesterId, true);

		for (ClassSemester classSemester : classSemesters) {
			
			for (ClassCourseSemester classCourseSemester : classSemester
					.getClassCourseSemesters()) {
				boolean checkSetDone = true;
				
				if (classCourseSemester.getTimetable().size() < classCourseSemester
						.getCourseSemester().getSlots()) {
					checkSetDone = false;
				}
				
				if (checkSetDone) {
					result.add(classCourseSemester);
				}
			}
			

		}

		return result;
	}

	@Override
	public List<ClassCourseSemester> getListClassWasSetRoomsDone(int semesterId) {
		// TODO Auto-generated method stub
		List<ClassCourseSemester> result = new ArrayList<ClassCourseSemester>();
		List<ClassSemester> classSemesters = classSemesterService
				.listClassSemestersBySemester(semesterId, true);

		for (ClassSemester classSemester : classSemesters) {
			for (ClassCourseSemester classCourseSemester : classSemester
					.getClassCourseSemesters()) {
				boolean checkSetDone = true;
				if (!classCourseSemester.getTimetable().isEmpty()) {
					if (classCourseSemester.getTimetable().iterator().next()
							.getRoom() == null) {
						checkSetDone = false;
					}
				} else {
					checkSetDone = false;
				}
				if (checkSetDone) {
					result.add(classCourseSemester);
				}
			}
		}

		return result;
	}

	@Override
	public List<ClassCourseSemester> getListClassWasSetTeachersDone(int semesterId) {
		// TODO Auto-generated method stub
		List<ClassCourseSemester> result = new ArrayList<ClassCourseSemester>();
		List<ClassSemester> classSemesters = classSemesterService
				.listClassSemestersBySemester(semesterId, true);

		for (ClassSemester classSemester : classSemesters) {
			
			for (ClassCourseSemester classCourseSemester : classSemester
					.getClassCourseSemesters()) {
				boolean checkSetDone = true;
				if (!classCourseSemester.getTimetable().isEmpty()) {
					if (classCourseSemester.getTimetable().iterator().next()
							.getTeacherSemester() == null) {
						checkSetDone = false;
					}
				} else {
					checkSetDone = false;
				}

				if (checkSetDone) {
					result.add(classCourseSemester);
				}
			}

		}

		return result;
	}

}
