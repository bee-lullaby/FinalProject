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
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "specialized")
public class Specialized {
	@Id
	@Column(name = "specialized_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int specializedId;
	@Column
	private String code;
	@Column
	private String name;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "specialized", orphanRemoval = true)
	@OrderBy
	Set<Student> students = new LinkedHashSet<Student>();
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "specialized", orphanRemoval = true)
	@OrderBy
	Set<ClassFPT> classes = new LinkedHashSet<ClassFPT>();

	/**
	 * @return the classes
	 */
	public Set<ClassFPT> getClasses() {
		return classes;
	}

	/**
	 * @param classes
	 *            the classes to set
	 */
	public void setClasses(Set<ClassFPT> classes) {
		this.classes = classes;
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
	 * 
	 */
	public Specialized() {
		super();
	}

	/**
	 * @param specializedId
	 * @param code
	 * @param name
	 */
	public Specialized(int specializedId, String code, String name) {
		super();
		this.specializedId = specializedId;
		this.code = code;
		this.name = name;
	}

	/**
	 * @param code
	 * @param name
	 */
	public Specialized(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}

	/**
	 * @return the specializedId
	 */
	public int getSpecializedId() {
		return specializedId;
	}

	/**
	 * @param specializedId
	 *            the specializedId to set
	 */
	public void setSpecializedId(int specializedId) {
		this.specializedId = specializedId;
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
		return "Specialized [specializedId=" + specializedId + ", code=" + code
				+ ", name=" + name + "]";
	}

	public String classesToString() {
		StringBuilder sb = new StringBuilder();
		Set<ClassFPT> classes = getClasses();

		if (classes.size() > 0) {
			for (ClassFPT classFPT : classes) {
				if (sb.length() == 0) {
					sb.append(classFPT.getCode());
				} else {
					sb.append(", " + classFPT.getCode());
				}
			}
		}
		return sb.toString();
	}

}
