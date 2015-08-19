package vn.edu.fpt.timetabling.model;

import java.util.Set;

public class DataTeacherArrangement {
	private ClassCourseSemester classCourseSemester;
	private Set<ClassCourseSemester> conflictClasses;

	/**
	 * 
	 */
	public DataTeacherArrangement() {
		super();
	}

	/**
	 * @param classCourseSemester
	 * @param conflictClasses
	 */
	public DataTeacherArrangement(ClassCourseSemester classCourseSemester,
			Set<ClassCourseSemester> conflictClasses) {
		super();
		this.classCourseSemester = classCourseSemester;
		this.conflictClasses = conflictClasses;
	}

	/**
	 * @return the classCourseSemester
	 */
	public ClassCourseSemester getClassCourseSemester() {
		return classCourseSemester;
	}

	/**
	 * @param classCourseSemester
	 *            the classCourseSemester to set
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
	 * @param conflictClasses
	 *            the conflictClasses to set
	 */
	public void setConflictClasses(Set<ClassCourseSemester> conflictClasses) {
		this.conflictClasses = conflictClasses;
	}
	
}
