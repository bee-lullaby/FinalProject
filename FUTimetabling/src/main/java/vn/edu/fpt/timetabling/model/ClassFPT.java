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
@Table(name = "classes")
public class ClassFPT {
	@Id
	@Column(name = "class_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int classId;
	@Column
	private String code;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "classFPT")
	List<ClassSemester> classSemesters = new ArrayList<ClassSemester>();

	/**
	 * 
	 */
	public ClassFPT() {
		super();
	}

	/**
	 * @return the classSemesters
	 */
	public List<ClassSemester> getClassSemesters() {
		return classSemesters;
	}

	/**
	 * @param classSemesters
	 *            the classSemesters to set
	 */
	public void setClassSemesters(List<ClassSemester> classSemesters) {
		this.classSemesters = classSemesters;
	}

	/**
	 * @param classId
	 * @param code
	 */
	public ClassFPT(int classId, String code) {
		super();
		this.classId = classId;
		this.code = code;
	}

	/**
	 * @return the classId
	 */
	public int getClassId() {
		return classId;
	}

	/**
	 * @param classId
	 *            the classId to set
	 */
	public void setClassId(int classId) {
		this.classId = classId;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ClassFPT [classId=" + classId + ", code=" + code + "]";
	}

}
