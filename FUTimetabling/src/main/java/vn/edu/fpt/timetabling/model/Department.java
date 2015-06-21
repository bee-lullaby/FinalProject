package vn.edu.fpt.timetabling.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "department")
public class Department {
	@Id
	@Column(name = "department_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int departmentId;

	private String code;
	private String name;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "department", orphanRemoval = true)
	Set<Course> courses = new HashSet<Course>();

	/**
	 * 
	 */
	public Department() {
		super();
	}

	/**
	 * @param departmentId
	 * @param code
	 * @param name
	 */
	public Department(int departmentId, String code, String name) {
		super();
		this.departmentId = departmentId;
		this.code = code;
		this.name = name;
	}

	/**
	 * @param code
	 * @param name
	 */
	public Department(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}

	/**
	 * @return the departmentId
	 */
	public int getDepartmentId() {
		return departmentId;
	}

	/**
	 * @param departmentId
	 *            the departmentId to set
	 */
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Department [departmentId=" + departmentId + ", code=" + code
				+ ", name=" + name + "]";
	}

	/**
	 * @return the courses
	 */
	public Set<Course> getCourses() {
		return courses;
	}

	/**
	 * @param courses
	 *            the courses to set
	 */
	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	public String coursesToString() {
		StringBuilder sb = new StringBuilder();

		Set<Course> courses = getCourses();

		if (courses.size() > 0) {
			for (Course course : courses) {
				if (sb.length() == 0) {
					sb.append(course.getCode());
				} else {
					sb.append(", " + course.getCode());
				}
			}
		}
		return sb.toString();
	}
}
