package vn.edu.fpt.timetabling.service;

import java.util.List;

import vn.edu.fpt.timetabling.model.ClassSemester;

public interface RoomArrangementService {
	
	public List<ClassSemester> getListClassesCoursesOfSemester(int semesterId);
}
