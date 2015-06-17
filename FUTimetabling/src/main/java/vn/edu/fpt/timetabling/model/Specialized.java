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
import javax.persistence.OneToMany;
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
	List<Student> students = new ArrayList<Student>();
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "specialized", orphanRemoval = true)
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
		List<ClassFPT> classes = getClasses();

		if (classes.size() > 0) {
			sb.append(classes.get(0).getCode());
			for (int i = 1; i < classes.size(); i++) {
				sb.append(", " + classes.get(i).getCode());
			}
		}
		return sb.toString();
	}
	
}
