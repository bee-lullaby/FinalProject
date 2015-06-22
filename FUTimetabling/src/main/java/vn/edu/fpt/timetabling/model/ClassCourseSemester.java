package vn.edu.fpt.timetabling.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "class_course_semester")
public class ClassCourseSemester {
	@Id
	@Column(name = "class_course_semester_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int classCourseSemesterId;

	@ManyToOne
	@JoinColumn(name = "class_semester_id")
	private ClassSemester classSemester;

	@ManyToOne
	@JoinColumn(name = "course_semester_id")
	private CourseSemester courseSemester;

	@Column(name = "block_condition")
	private int blockCondition;

	@Column(name = "semester_long", columnDefinition = "TINYINT")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean semesterLong;

	/**
	 * 
	 */
	public ClassCourseSemester() {
		super();
	}

	/**
	 * @return the classCourseSemesterId
	 */
	public int getClassCourseSemesterId() {
		return classCourseSemesterId;
	}

	/**
	 * @param classCourseSemesterId
	 *            the classCourseSemesterId to set
	 */
	public void setClassCourseSemesterId(int classCourseSemesterId) {
		this.classCourseSemesterId = classCourseSemesterId;
	}

	/**
	 * @return the classSemester
	 */
	public ClassSemester getClassSemester() {
		return classSemester;
	}

	/**
	 * @param classSemester
	 *            the classSemester to set
	 */
	public void setClassSemester(ClassSemester classSemester) {
		this.classSemester = classSemester;
	}

	/**
	 * @return the courseSemester
	 */
	public CourseSemester getCourseSemester() {
		return courseSemester;
	}

	/**
	 * @param courseSemester
	 *            the courseSemester to set
	 */
	public void setCourseSemester(CourseSemester courseSemester) {
		this.courseSemester = courseSemester;
	}

	/**
	 * @return the blockCondition
	 */
	public int getBlockCondition() {
		return blockCondition;
	}

	/**
	 * @param blockCondition
	 *            the blockCondition to set
	 */
	public void setBlockCondition(int blockCondition) {
		this.blockCondition = blockCondition;
	}

	/**
	 * @return the semesterLong
	 */
	public boolean isSemesterLong() {
		return semesterLong;
	}

	/**
	 * @param semesterLong
	 *            the semesterLong to set
	 */
	public void setSemesterLong(boolean semesterLong) {
		this.semesterLong = semesterLong;
	}

	/**
	 * @param classCourseSemeterId
	 * @param classSemester
	 * @param courseSemester
	 * @param blockCondition
	 * @param semesterLong
	 */
	public ClassCourseSemester(int classCourseSemeterId,
			ClassSemester classSemester, CourseSemester courseSemester,
			int blockCondition, boolean semesterLong) {
		super();
		this.classCourseSemesterId = classCourseSemeterId;
		this.classSemester = classSemester;
		this.courseSemester = courseSemester;
		this.blockCondition = blockCondition;
		this.semesterLong = semesterLong;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ClassCourseSemester [classCourseSemeterId="
				+ classCourseSemesterId + ", classSemester=" + classSemester
				+ ", courseSemester=" + courseSemester + ", blockCondition="
				+ blockCondition + ", semesterLong=" + semesterLong + "]";
	}

}
