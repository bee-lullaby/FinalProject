package vn.edu.fpt.timetabling.auto.entities;

public class Course {
	public int ID;
	public String code;

	public Course(int iD, String code) {
		super();
		ID = iD;
		this.code = code;
	}

	public Course() {
		// TODO Auto-generated constructor stub
	}

	public Course(int ID) {
		// TODO Auto-generated constructor stub
		this.ID = ID;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
