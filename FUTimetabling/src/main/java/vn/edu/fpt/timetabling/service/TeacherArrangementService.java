package vn.edu.fpt.timetabling.service;

import java.util.List;
import java.util.Set;

import vn.edu.fpt.timetabling.model.ClassCourseSemester;
import vn.edu.fpt.timetabling.model.Course;
import vn.edu.fpt.timetabling.model.CourseSemester;
import vn.edu.fpt.timetabling.model.DataTeacherArrangement;
import vn.edu.fpt.timetabling.model.Department;
import vn.edu.fpt.timetabling.model.Timetable;

public interface TeacherArrangementService {
	
	public List<Department> getListDepartment();
	
	public List<Course> getListCourse(int departmentId);
	
	public CourseSemester getCourseSemester(int semesterId, int courseId);
	
	public List<DataTeacherArrangement> getDataTeacherArrangement(int semesterId, int courseId);
	
	public Set<ClassCourseSemester> getListClassConflicts(ClassCourseSemester classCourseSemester, Set<ClassCourseSemester> classCourseSemesters);

	public boolean updateTimetable(List<Timetable> timetables);
}
