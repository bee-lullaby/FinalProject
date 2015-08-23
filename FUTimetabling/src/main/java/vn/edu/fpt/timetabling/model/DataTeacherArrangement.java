package vn.edu.fpt.timetabling.model;

import java.util.Set;

public class DataTeacherArrangement {
	private ClassCourseSemester classCourseSemester;
	private Set<ClassCourseSemester> conflictClasses;
	private Set<TeacherSemester> teacherAvailable;

	/**
	 * 
	 */
	public DataTeacherArrangement() {
		super();
	}

	/**
	 * @param classCourseSemester
	 * @param conflictClasses
	 * @param teacherAvailable
	 */
	public DataTeacherArrangement(ClassCourseSemester classCourseSemester,
			Set<ClassCourseSemester> conflictClasses,
			Set<TeacherSemester> teacherAvailable) {
		super();
		this.classCourseSemester = classCourseSemester;
		this.conflictClasses = conflictClasses;
		this.teacherAvailable = teacherAvailable;
	}

	/**
	 * @return the classCourseSemester
	 */
	public ClassCourseSemester getClassCourseSemester() {
		return classCourseSemester;
	}

	/**
	 * @param classCourseSemester the classCourseSemester to set
	 */
	public void setClassCourseSemester(ClassCourseSemester classCourseSemester) {
		this.classCourseSemester = classCourseSemester;
	}

	/**
	 * @return the conflictClasses
	 */
	public Set<ClassCourseSemester> getConflictClasses() {
		return conflictClasses;
	}

	/**
	 * @param conflictClasses the conflictClasses to set
	 */
	public void setConflictClasses(Set<ClassCourseSemester> conflictClasses) {
		this.conflictClasses = conflictClasses;
	}

	/**
	 * @return the teacherAvailable
	 */
	public Set<TeacherSemester> getTeacherAvailable() {
		return teacherAvailable;
	}

	/**
	 * @param teacherAvailable the teacherAvailable to set
	 */
	public void setTeacherAvailable(Set<TeacherSemester> teacherAvailable) {
		this.teacherAvailable = teacherAvailable;
	}

	
}
