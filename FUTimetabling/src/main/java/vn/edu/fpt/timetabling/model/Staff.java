package vn.edu.fpt.timetabling.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Staff")
public class Staff {

	@Id
	@Column(name = "course_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int staffId;
	private String account;
	private String name;
	private String email;
	private String accountType;

	/**
	 * 
	 */
	public Staff() {
		super();
	}

	/**
	 * @param staffId
	 * @param account
	 * @param name
	 * @param email
	 * @param accountType
	 */
	public Staff(int staffId, String account, String name, String email,
			String accountType) {
		super();
		this.staffId = staffId;
		this.account = account;
		this.name = name;
		this.email = email;
		this.accountType = accountType;
	}

	/**
	 * @return the staffId
	 */
	public int getStaffId() {
		return staffId;
	}

	/**
	 * @param staffId
	 *            the staffId to set
	 */
	public void setStaffId(int staffId) {
		this.staffId = staffId;
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
	 * @return the accountType
	 */
	public String getAccountType() {
		return accountType;
	}

	/**
	 * @param accountType
	 *            the accountType to set
	 */
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	@Override
	public String toString() {
		return "{staffId = " + staffId + ", account = " + account + ", name = "
				+ name + ", email = " + email + ", accountType = "
				+ accountType + "}";
	}
}
