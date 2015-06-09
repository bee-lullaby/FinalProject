package vn.edu.fpt.timetabling.model;

import java.util.ArrayList;
import java.util.List;

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
import javax.persistence.Table;

@Entity
@Table(name = "class_semester")
public class ClassSemester {

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
	@Column(name = "students")
	private int noOfStudents;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "classSemester")
	List<Student> students = new ArrayList<Student>();

	/**
	 * 
	 */
	public ClassSemester() {
		super();
	}

	/**
	 * @return the students
	 */
	public List<Student> getStudents() {
		return students;
	}

	/**
	 * @param students
	 *            the students to set
	 */
	public void setStudents(List<Student> students) {
		this.students = students;
	}

	/**
	 * @param classSemesterId
	 * @param classFPT
	 * @param semester
	 * @param students
	 */
	public ClassSemester(int classSemesterId, ClassFPT classFPT,
			Semester semester, int students) {
		super();
		this.classSemesterId = classSemesterId;
		this.classFPT = classFPT;
		this.semester = semester;
		this.noOfStudents = students;
	}

	/**
	 * @param classFPT
	 * @param semester
	 * @param students
	 */
	public ClassSemester(ClassFPT classFPT, Semester semester, int students) {
		super();
		this.classFPT = classFPT;
		this.semester = semester;
		this.noOfStudents = students;
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

	/**
	 * @return the students
	 */
	public int getNoOfStudents() {
		return noOfStudents;
	}

	/**
	 * @param students
	 *            the students to set
	 */
	public void setNoOfStudents(int noOfStudents) {
		this.noOfStudents = noOfStudents;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ClassSemester [classSemesterId=" + classSemesterId
				+ ", classFPT=" + classFPT + ", semester=" + semester
				+ ", students=" + noOfStudents + "]";
	}

}
