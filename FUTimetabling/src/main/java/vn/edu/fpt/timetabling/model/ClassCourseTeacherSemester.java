package vn.edu.fpt.timetabling.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "class_course_teacher_semester")
public class ClassCourseTeacherSemester {

	@Id
	@Column(name = "class_course_teacher_semester_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int classCourseTeacherSemesterId;
	
	@ManyToOne
	@JoinColumn(name = "class_semester_id")
	private ClassSemester classSemester;
	
	@ManyToOne
	@JoinColumn(name = "course_semester_id")
	private ClassSemester courseSemester;
	
	@ManyToOne
	@JoinColumn(name = "teacher_semester_id")
	private ClassSemester teacherSemester;

	/**
	 * 
	 */
	public ClassCourseTeacherSemester() {
		super();
	}

	/**
	 * @param classCourseTeacherSemesterId
	 * @param classSemester
	 * @param courseSemester
	 * @param teacherSemester
	 */
	public ClassCourseTeacherSemester(int classCourseTeacherSemesterId,
			ClassSemester classSemester, ClassSemester courseSemester,
			ClassSemester teacherSemester) {
		super();
		this.classCourseTeacherSemesterId = classCourseTeacherSemesterId;
		this.classSemester = classSemester;
		this.courseSemester = courseSemester;
		this.teacherSemester = teacherSemester;
	}

	/**
	 * @return the classCourseTeacherSemesterId
	 */
	public int getClassCourseTeacherSemesterId() {
		return classCourseTeacherSemesterId;
	}

	/**
	 * @param classCourseTeacherSemesterId the classCourseTeacherSemesterId to set
	 */
	public void setClassCourseTeacherSemesterId(int classCourseTeacherSemesterId) {
		this.classCourseTeacherSemesterId = classCourseTeacherSemesterId;
	}

	/**
	 * @return the classSemester
	 */
	public ClassSemester getClassSemester() {
		return classSemester;
	}

	/**
	 * @param classSemester the classSemester to set
	 */
	public void setClassSemester(ClassSemester classSemester) {
		this.classSemester = classSemester;
	}

	/**
	 * @return the courseSemester
	 */
	public ClassSemester getCourseSemester() {
		return courseSemester;
	}

	/**
	 * @param courseSemester the courseSemester to set
	 */
	public void setCourseSemester(ClassSemester courseSemester) {
		this.courseSemester = courseSemester;
	}

	/**
	 * @return the teacherSemester
	 */
	public ClassSemester getTeacherSemester() {
		return teacherSemester;
	}

	/**
	 * @param teacherSemester the teacherSemester to set
	 */
	public void setTeacherSemester(ClassSemester teacherSemester) {
		this.teacherSemester = teacherSemester;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ClassCourseTeacherSemester [classCourseTeacherSemesterId="
				+ classCourseTeacherSemesterId + ", classSemester="
				+ classSemester + ", courseSemester=" + courseSemester
				+ ", teacherSemester=" + teacherSemester + "]";
	}
	
	
}
