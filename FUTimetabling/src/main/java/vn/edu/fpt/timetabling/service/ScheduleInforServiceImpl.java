package vn.edu.fpt.timetabling.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import vn.edu.fpt.timetabling.model.ClassCourseSemester;
import vn.edu.fpt.timetabling.model.ClassSemester;

public class ScheduleInforServiceImpl implements ScheduleInforService {
	@Autowired
	private ClassSemesterService classSemesterService;

	// Get List From ClassSemester and check set timetable
	@Override
	public List<ClassSemester> getListWasNotSetTimetablesDone(int semesterId) {

		List<ClassSemester> result = new ArrayList<ClassSemester>();
		List<ClassSemester> classSemesters = classSemesterService.listClassSemestersBySemester(semesterId, true);

		for (ClassSemester classSemester : classSemesters) {
			for (ClassCourseSemester classCourseSemester : classSemester.getClassCourseSemesters()) {
				if (classCourseSemester.getTimetable().size() < classCourseSemester.getCourseSemester().getSlots()) {
					result.add(classSemester);
					break;
				}
			}
		}

		return result;
	}

	@Override
	public List<ClassSemester> getListWasNotSetRoomsDone(int semesterId) {
		return null;
	}

	@Override
	public List<ClassSemester> getListWasNoteSetTeachersDone(int semesterId) {
		// TODO Auto-generated method stub
		return null;
	}

}
