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
@Table(name = "students")
public class Student {
	@Id
	@Column(name = "student_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int studentId;
	@Column
	private String account;
	@Column
	private String name;
	@Column
	private String email;
	@Column(name = "student_code")
	private String studentCode;
	@ManyToOne
	@JoinColumn(name = "specialized_id")
	private Specialized specialized;
	@ManyToOne
	@JoinColumn(name = "detail_specialized_id")
	private Specialized detailSpecialized;
	@Column
	private String batch;
	@Column
	private int semester;

	public Specialized getDetailSpecialized() {
		return detailSpecialized;
	}

	public void setDetailSpecialized(Specialized detailSpecialized) {
		this.detailSpecialized = detailSpecialized;
	}

	/**
	 * 
	 */
	public Student() {
		super();
	}

	/**
	 * @param studentId
	 * @param account
	 * @param name
	 * @param email
	 * @param studentCode
	 * @param specialized
	 * @param batch
	 * @param semester
	 * @param classSemester
	 */
	public Student(int studentId, String account, String name, String email, String studentCode,
			Specialized specialized, String batch, int semester) {
		super();
		this.studentId = studentId;
		this.account = account;
		this.name = name;
		this.email = email;
		this.studentCode = studentCode;
		this.specialized = specialized;
		this.batch = batch;
		this.semester = semester;
	}

	/**
	 * @param account
	 * @param name
	 * @param email
	 * @param studentCode
	 * @param specialized
	 * @param batch
	 * @param semester
	 * @param classSemester
	 */
	public Student(String account, String name, String email, String studentCode, Specialized specialized, String batch,
			int semester) {
		super();
		this.account = account;
		this.name = name;
		this.email = email;
		this.studentCode = studentCode;
		this.specialized = specialized;
		this.batch = batch;
		this.semester = semester;
	}

	/**
	 * @return the studentId
	 */
	public int getStudentId() {
		return studentId;
	}

	/**
	 * @param studentId
	 *            the studentId to set
	 */
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	/**
	 * @return the account
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * @param account
	 *            the account to set
	 */
	public void setAccount(String account) {
		this.account = account;
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the studentCode
	 */
	public String getStudentCode() {
		return studentCode;
	}

	/**
	 * @param studentCode
	 *            the studentCode to set
	 */
	public void setStudentCode(String studentCode) {
		this.studentCode = studentCode;
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
	 * @return the batch
	 */
	public String getBatch() {
		return batch;
	}

	/**
	 * @param batch
	 *            the batch to set
	 */
	public void setBatch(String batch) {
		this.batch = batch;
	}

	/**
	 * @return the semester
	 */
	public int getSemester() {
		return semester;
	}

	/**
	 * @param semester
	 *            the semester to set
	 */
	public void setSemester(int semester) {
		this.semester = semester;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", account=" + account + ", name=" + name + ", email=" + email
				+ ", studentCode=" + studentCode + ", specialized=" + specialized + ", batch=" + batch + ", semester="
				+ semester + "]";
	}
}
