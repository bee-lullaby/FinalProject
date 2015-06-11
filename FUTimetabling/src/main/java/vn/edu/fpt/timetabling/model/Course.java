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
@Table(name = "courses")
public class Course {
	@Id
	@Column(name = "course_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int courseId;
	private String code;
	private String name;

	@ManyToOne
	@JoinColumn(name = "department_id")
	private Department department;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "course", orphanRemoval = true)
	List<ClassFPT> classes = new ArrayList<ClassFPT>();

	/**
	 * @return the classes
	 */
	public List<ClassFPT> getClasses() {
		return classes;
	}

	/**
	 * @param classes
	 *            the classes to set
	 */
	public void setClasses(List<ClassFPT> classes) {
		this.classes = classes;
	}

	/**
	 * 
	 */
	public Course() {
		super();
	}

	/**
	 * @param courseId
	 * @param code
	 * @param name
	 * @param department
	 */
	public Course(int courseId, String code, String name, Department department) {
		super();
		this.courseId = courseId;
		this.code = code;
		this.name = name;
		this.department = department;
	}

	/**
	 * @param code
	 * @param name
	 * @param department
	 */
	public Course(String code, String name, Department department) {
		super();
		this.code = code;
		this.name = name;
		this.department = department;
	}

	/**
	 * @return the courseId
	 */
	public int getCourseId() {
		return courseId;
	}

	/**
	 * @param courseId
	 *            the courseId to set
	 */
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * @param department
	 *            the department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", code=" + code + ", name="
				+ name + ", department=" + department + "]";
	}

}
