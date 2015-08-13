package vn.edu.fpt.timetabling.auto.entities;

public class ClassFU {

	/**
	 * @param args
	 */
	public int ID;
	public String code;
	public int nbStudent;

	public ClassFU(int iD, String code) {
		super();
		ID = iD;
		this.code = code;
	}
	public ClassFU(int iD, String code,int nbS) {
		super();
		ID = iD;
		this.code = code;
		this.nbStudent=nbS;
	}

	public ClassFU() {
		// TODO Auto-generated constructor stub
	}

	public ClassFU(int iD) {
		super();
		ID = iD;
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
