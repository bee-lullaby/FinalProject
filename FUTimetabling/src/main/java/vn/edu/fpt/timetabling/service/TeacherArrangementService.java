package vn.edu.fpt.timetabling.service;

import java.util.List;
import java.util.Set;

import vn.edu.fpt.timetabling.model.ClassCourseSemester;
import vn.edu.fpt.timetabling.model.CourseSemester;
import vn.edu.fpt.timetabling.model.DataTeacherArrangement;
import vn.edu.fpt.timetabling.model.Department;
import vn.edu.fpt.timetabling.model.TeacherSemester;
import vn.edu.fpt.timetabling.model.Timetable;

public interface TeacherArrangementService {
	
	public List<Department> getListDepartment();

	public List<CourseSemester> getListCourseSemester(int semesterId, int departmentId);
	
	public CourseSemester getCourseSemester(int courseSemesterId);
	
	public List<DataTeacherArrangement> getDataTeacherArrangement(int courseSemesterId);
	
	public Set<ClassCourseSemester> getListClassConflicts(ClassCourseSemester classCourseSemester, Set<ClassCourseSemester> classCourseSemesters);
	
	public Set<TeacherSemester> getListTeacherAvailableForClassCourse(ClassCourseSemester classCourseSemester);
		
	public boolean updateTimetable(List<Timetable> timetables);
}
