package vn.edu.fpt.timetabling.model;

import java.util.ArrayList;
import java.util.Date;
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
@Table(name = "semesters")
public class Semester {

	@Id
	@Column(name = "semester_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int semesterId;
	private String code;
	private String name;

	@Column(name = "semester_year")
	private int semesterYear;
	@Column(name = "start_date")
	private Date startDate;
	@Column(name = "end_date")
	private Date endDate;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "semester", orphanRemoval = true)
	List<ClassSemester> classSemesters = new ArrayList<ClassSemester>();

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
	 * 
	 */
	public Semester() {
		super();
	}

	/**
	 * @param semesterId
	 * @param code
	 * @param name
	 * @param semesterYear
	 * @param startDate
	 * @param endDate
	 */
	public Semester(int semesterId, String code, String name, int semesterYear,
			Date startDate, Date endDate) {
		super();
		this.semesterId = semesterId;
		this.code = code;
		this.name = name;
		this.semesterYear = semesterYear;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	/**
	 * @param code
	 * @param name
	 * @param semesterYear
	 * @param startDate
	 * @param endDate
	 */
	public Semester(String code, String name, int semesterYear, Date startDate,
			Date endDate) {
		super();
		this.code = code;
		this.name = name;
		this.semesterYear = semesterYear;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	/**
	 * @return the semesterId
	 */
	public int getSemesterId() {
		return semesterId;
	}

	/**
	 * @param semesterId
	 *            the semesterId to set
	 */
	public void setSemesterId(int semesterId) {
		this.semesterId = semesterId;
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
	 * @return the semesterYear
	 */
	public int getSemesterYear() {
		return semesterYear;
	}

	/**
	 * @param semesterYear
	 *            the semesterYear to set
	 */
	public void setSemesterYear(int semesterYear) {
		this.semesterYear = semesterYear;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Semester [semesterId=" + semesterId + ", code=" + code
				+ ", name=" + name + ", semesterYear=" + semesterYear
				+ ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}

}