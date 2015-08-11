package vn.edu.fpt.timetabling.auto.entities;

public class Teacher {

	/**
	 * @param args
	 */
	public int ID;
	public String code;
	public Department department;

	public Teacher(int iD) {
		super();
		ID = iD;
	}
	
	public Teacher(int iD, String code) {
		super();
		ID = iD;
		this.code = code;
	}

	public Teacher() {
		// TODO Auto-generated constructor stub
	}

	public Teacher(int iD, String code, Department department) {
		super();
		ID = iD;
		this.code = code;
		this.department = department;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
