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
	public List<ClassSemester> getListClassWasSetCourse(int semesterId) {

		List<ClassSemester> result = new ArrayList<ClassSemester>();
		List<ClassSemester> classSemesters = classSemesterService
				.listClassSemestersBySemester(semesterId, true);

		for (ClassSemester classSemester : classSemesters) {
			if (!classSemester.getClassCourseSemesters().isEmpty())
				result.add(classSemester);
		}

		return result;
	}

	// Get List From ClassSemester and check set timetable
	@Override
	public List<ClassSemester> getListClassWasSetTimetablesDone(int semesterId) {

		List<ClassSemester> result = new ArrayList<ClassSemester>();
		List<ClassSemester> classSemesters = classSemesterService
				.listClassSemestersBySemester(semesterId, true);

		for (ClassSemester classSemester : classSemesters) {
			boolean checkSetDone = true;
			for (ClassCourseSemester classCourseSemester : classSemester
					.getClassCourseSemesters()) {
				if (classCourseSemester.getTimetable().size() < classCourseSemester
						.getCourseSemester().getSlots()) {
					checkSetDone = false;
					break;
				}
			}

			if (checkSetDone)
				result.add(classSemester);
		}

		return result;
	}

	@Override
	public List<ClassSemester> getListClassWasSetRoomsDone(int semesterId) {
		// TODO Auto-generated method stub
		List<ClassSemester> result = new ArrayList<ClassSemester>();
		List<ClassSemester> classSemesters = classSemesterService
				.listClassSemestersBySemester(semesterId, true);

		for (ClassSemester classSemester : classSemesters) {
			boolean checkSetDone = true;
			for (ClassCourseSemester classCourseSemester : classSemester
					.getClassCourseSemesters()) {
				if (!classCourseSemester.getTimetable().isEmpty()) {
					if (classCourseSemester.getTimetable().iterator().next()
							.getRoom() == null) {
						checkSetDone = false;
						break;
					}
				} else {
					checkSetDone = false;
					break;
				}
			}

			if (checkSetDone)
				result.add(classSemester);
		}

		return result;
	}

	@Override
	public List<ClassSemester> getListClassWasSetTeachersDone(int semesterId) {
		// TODO Auto-generated method stub
		List<ClassSemester> result = new ArrayList<ClassSemester>();
		List<ClassSemester> classSemesters = classSemesterService
				.listClassSemestersBySemester(semesterId, true);

		for (ClassSemester classSemester : classSemesters) {
			boolean checkSetDone = true;
			for (ClassCourseSemester classCourseSemester : classSemester
					.getClassCourseSemesters()) {
				if (!classCourseSemester.getTimetable().isEmpty()) {
					if (classCourseSemester.getTimetable().iterator().next()
							.getTeacherSemester() == null) {
						checkSetDone = false;
						break;
					}
				} else {
					checkSetDone = false;
					break;
				}
			}

			if (checkSetDone)
				result.add(classSemester);
		}

		return result;
	}

}
