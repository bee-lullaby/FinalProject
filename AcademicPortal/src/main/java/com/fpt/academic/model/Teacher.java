package com.fpt.academic.model;

import java.io.Serializable;



// 14-04-2015 - Create by hans
// Class teacher
// String account: account name of teachers. 
// String name: the real name of teachers.
// String email: gmail of teachers
// String account_type: define who is admin and who is not. ( 0 = not, 1 = admin )

public class Teacher {
	private int teacher_id;
	private String account;
	private String name;
	private String email;
	private String account_type;
	
	
	// Constructors

	public Teacher() {
	}

	public Teacher(int teacher_id, String account, String name, String email,
			String account_type) {
		this.teacher_id = teacher_id;
		this.account = account;
		this.name = name;
		this.email = email;
		this.account_type = account_type;
	}

	
	// Getter and Setter

	public int getTeacher_id() {
		return teacher_id;
	}

	public void setTeacher_id(int teacher_id) {
		this.teacher_id = teacher_id;
	}
	
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAccount_type() {
		return account_type;
	}

	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}
	
	
	
}
