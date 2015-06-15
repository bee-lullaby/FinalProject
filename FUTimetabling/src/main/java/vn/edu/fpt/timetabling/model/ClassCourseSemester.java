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
@Table(name = "class_course_semester")
public class ClassCourseSemester {
	@Id
	@Column(name = "class_course_semester_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int classCourseSemeterId;

	@ManyToOne
	@JoinColumn(name = "class_semester_id")
	private ClassSemester classSemester;

	@ManyToOne
	@JoinColumn(name = "course_semester_id")
	private CourseSemester courseSemester;

	private int block_condition;

	private int semester_long;

	/**
	 * 
	 */
	public ClassCourseSemester() {
		super();
	}

	/**
	 * @param classCourseSemeterId
	 * @param classSemester
	 * @param courseSemester
	 * @param block_condition
	 * @param semester_long
	 */
	public ClassCourseSemester(int classCourseSemeterId,
			ClassSemester classSemester, CourseSemester courseSemester,
			int block_condition, int semester_long) {
		super();
		this.classCourseSemeterId = classCourseSemeterId;
		this.classSemester = classSemester;
		this.courseSemester = courseSemester;
		this.block_condition = block_condition;
		this.semester_long = semester_long;
	}

	/**
	 * @return the classCourseSemeterId
	 */
	public int getClassCourseSemeterId() {
		return classCourseSemeterId;
	}

	/**
	 * @param classCourseSemeterId
	 *            the classCourseSemeterId to set
	 */
	public void setClassCourseSemeterId(int classCourseSemeterId) {
		this.classCourseSemeterId = classCourseSemeterId;
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
	 * @return the block_condition
	 */
	public int getBlock_condition() {
		return block_condition;
	}

	/**
	 * @param block_condition
	 *            the block_condition to set
	 */
	public void setBlock_condition(int block_condition) {
		this.block_condition = block_condition;
	}

	/**
	 * @return the semester_long
	 */
	public int getSemester_long() {
		return semester_long;
	}

	/**
	 * @param semester_long
	 *            the semester_long to set
	 */
	public void setSemester_long(int semester_long) {
		this.semester_long = semester_long;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ClassCourseSemester [classCourseSemeterId="
				+ classCourseSemeterId + ", classSemester=" + classSemester
				+ ", courseSemester=" + courseSemester + ", block_condition="
				+ block_condition + ", semester_long=" + semester_long + "]";
	}

}
