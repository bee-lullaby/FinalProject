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
@Table(name = "teachers")
public class Teacher {

	@Id
	@Column(name = "teacher_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int teacherId;
	private String account;
	private String name;
	private String email;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "teacher", orphanRemoval = true)
	@OrderBy
	Set<TeacherSemester> teacherSemesters = new LinkedHashSet<TeacherSemester>();

	/**
	 * @return the teacherSemesters
	 */
	public Set<TeacherSemester> getTeacherSemesters() {
		return teacherSemesters;
	}

	/**
	 * @param teacherSemesters the teacherSemesters to set
	 */
	public void setTeacherSemesters(Set<TeacherSemester> teacherSemesters) {
		this.teacherSemesters = teacherSemesters;
	}

	/**
	 * 
	 */
	public Teacher() {
		super();
	}

	/**
	 * @param teacherId
	 * @param account
	 * @param name
	 * @param email
	 */
	public Teacher(int teacherId, String account, String name, String email) {
		super();
		this.teacherId = teacherId;
		this.account = account;
		this.name = name;
		this.email = email;
	}

	/**
	 * @param account
	 * @param name
	 * @param email
	 */
	public Teacher(String account, String name, String email) {
		super();
		this.account = account;
		this.name = name;
		this.email = email;
	}

	/**
	 * @return the teacherId
	 */
	public int getTeacherId() {
		return teacherId;
	}

	/**
	 * @param teacherId
	 *            the teacherId to set
	 */
	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
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

}
