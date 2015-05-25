package com.fpt.academic.dao;

import java.util.List;

import com.fpt.academic.model.ClassFPT;
import com.fpt.academic.model.ClassSemester;
import com.fpt.academic.model.Course;
import com.fpt.academic.model.CourseSemester;
import com.fpt.academic.model.Semester;
import com.fpt.academic.model.Teacher;
import com.fpt.academic.model.TeacherSemester;

public interface SemesterDAO {
	public void addSemester(Semester semester);
//	void addListSemester(File semester);
	public void updateSemester(Semester semester);
	public void deleteSemester(Semester semester);
	public List<Semester> list();
	public String display();
	
	
// Get method
	 public List<ClassSemester> getClassSemester(int semester_id);
	 public List<CourseSemester> getCourseSemester(int semester_id);
	 public List<TeacherSemester> getTeacherSemester(int semester_id);
	 
	 public int getClassID(int class_semester_id);
	 public int getCourseID(int course_semester_id);
	 public int getTeacherID(int teacher_semester_id);
	 
	 public ClassSemester getClassSemesterID (int semester_id, int class_code);
	 
}
