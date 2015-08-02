package vn.edu.fpt.timetabling.auto.entities;

public class ClassCourse {

	/**
	 * @stt: only class-course has status
	 */
	public int ID;
	public String code;
	public int stt;

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

	public int getStt() {
		return stt;
	}

	public void setStt(int stt) {
		this.stt = stt;
	}

	public ClassCourse() {
		// TODO Auto-generated constructor stub
	}

	public ClassCourse(int ID) {
		this.ID = ID;
	}

	public ClassCourse(int ID, int stt) {
		this.ID = ID;
		this.stt = stt;
	}

	public ClassCourse(int iD, String code, int stt) {
		super();
		ID = iD;
		this.code = code;
		this.stt = stt;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
