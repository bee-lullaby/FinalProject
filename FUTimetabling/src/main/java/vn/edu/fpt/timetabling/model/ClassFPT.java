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
@Table(name = "classes")
public class ClassFPT {
	@Id
	@Column(name = "class_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int classId;
	@Column
	private String code;
	@Column
	private String type = "Specialized";
	@ManyToOne
	@JoinColumn(name = "specialized_id")
	private Specialized specialized;
	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;
	@Column
	private Integer batch;
	@Column
	private int number;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "classFPT")
	List<ClassSemester> classSemesters = new ArrayList<ClassSemester>();

	/**
	 * @param batch
	 *            the batch to set
	 */
	public void setBatch(Integer batch) {
		this.batch = batch;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

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
	 * @return the specialized
	 */
	public Specialized getSpecialized() {
		return specialized;
	}

	/**
	 * @param specialized
	 *            the specialized to set
	 */
	public void setSpecialized(Specialized specialized) {
		this.specialized = specialized;
	}

	/**
	 * @return the course
	 */
	public Course getCourse() {
		return course;
	}

	/**
	 * @param course
	 *            the course to set
	 */
	public void setCourse(Course course) {
		this.course = course;
	}

	/**
	 * @return the batch
	 */
	public Integer getBatch() {
		return batch;
	}

	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * @param number
	 *            the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * @param classId
	 * @param code
	 * @param specialized
	 * @param course
	 * @param batch
	 * @param number
	 */
	public ClassFPT(int classId, String code, Specialized specialized,
			Course course, int batch, int number) {
		super();
		this.classId = classId;
		this.code = code;
		this.specialized = specialized;
		this.course = course;
		this.batch = batch;
		this.number = number;
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
