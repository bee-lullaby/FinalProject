package vn.edu.fpt.timetabling.model;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "class_semester")
public class ClassSemester implements Comparable<ClassSemester> {

	@Id
	@Column(name = "class_semester_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int classSemesterId;

	@ManyToOne
	@JoinColumn(name = "class_id")
	private ClassFPT classFPT;

	@ManyToOne
	@JoinColumn(name = "semester_id")
	private Semester semester;

	@Column(name = "semester")
	private int semesterNumber = 0;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "classSemester", orphanRemoval = true)
	@OrderBy
	Set<Student> students = new LinkedHashSet<Student>();

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "classSemester", orphanRemoval = true)
	@OrderBy
	Set<ClassCourseSemester> classCourseSemester = new LinkedHashSet<ClassCourseSemester>();

	/**
	 * @return the classCourseSemester
	 */
	public Set<ClassCourseSemester> getClassCourseSemester() {
		return classCourseSemester;
	}

	/**
	 * @param classCourseSemester
	 *            the classCourseSemester to set
	 */
	public void setClassCourseSemester(Set<ClassCourseSemester> classCourseSemester) {
		this.classCourseSemester = classCourseSemester;
	}

	/**
	 * @return the semesterNumber
	 */
	public int getSemesterNumber() {
		return semesterNumber;
	}

	/**
	 * @param semesterNumber
	 *            the semesterNumber to set
	 */
	public void setSemesterNumber(int semesterNumber) {
		this.semesterNumber = semesterNumber;
	}

	/**
	 * 
	 */
	public ClassSemester() {
		super();
	}

	/**
	 * @return the students
	 */
	public Set<Student> getStudents() {
		return students;
	}

	/**
	 * @param students
	 *            the students to set
	 */
	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	/**
	 * @param classSemesterId
	 * @param classFPT
	 * @param semester
	 * @param students
	 */
	public ClassSemester(int classSemesterId, ClassFPT classFPT, Semester semester) {
		super();
		this.classSemesterId = classSemesterId;
		this.classFPT = classFPT;
		this.semester = semester;
	}

	/**
	 * @param classFPT
	 * @param semester
	 * @param students
	 */
	public ClassSemester(ClassFPT classFPT, Semester semester) {
		super();
		this.classFPT = classFPT;
		this.semester = semester;
	}

	/**
	 * @return the classSemesterId
	 */
	public int getClassSemesterId() {
		return classSemesterId;
	}

	/**
	 * @param classSemesterId
	 *            the classSemesterId to set
	 */
	public void setClassSemesterId(int classSemesterId) {
		this.classSemesterId = classSemesterId;
	}

	/**
	 * @return the classFPT
	 */
	public ClassFPT getClassFPT() {
		return classFPT;
	}

	/**
	 * @param classFPT
	 *            the classFPT to set
	 */
	public void setClassFPT(ClassFPT classFPT) {
		this.classFPT = classFPT;
	}

	/**
	 * @return the semester
	 */
	public Semester getSemester() {
		return semester;
	}

	/**
	 * @param semester
	 *            the semester to set
	 */
	public void setSemester(Semester semester) {
		this.semester = semester;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ClassSemester [classSemesterId=" + classSemesterId + ", classFPT=" + classFPT + ", semester=" + semester
				+ "]";
	}

	@Override
	public int compareTo(ClassSemester classSemester) {
		if (this.semester.compareTo(classSemester.getSemester()) == 0) {
			return this.classFPT.getCode().compareTo(classSemester.getClassFPT().getCode());
		} else if (this.semester.compareTo(classSemester.getSemester()) > 0) {
			return 1;
		} else {
			return -1;
		}
	}

}
