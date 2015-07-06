package vn.edu.fpt.timetabling.model;

public class DataSchedule {
	private int numOfClasses;
	private int classInSlot;
	private int numOfTeachers;

	/**
	 * 
	 */
	public DataSchedule() {
		super();
	}

	/**
	 * @param numOfClasses
	 * @param classInSlot
	 * @param numOfTeachers
	 */
	public DataSchedule(int numOfClasses, int classInSlot, int numOfTeachers) {
		super();
		this.numOfClasses = numOfClasses;
		this.classInSlot = classInSlot;
		this.numOfTeachers = numOfTeachers;
	}

	/**
	 * @return the numOfClasses
	 */
	public int getNumOfClasses() {
		return numOfClasses;
	}

	/**
	 * @param numOfClasses
	 *            the numOfClasses to set
	 */
	public void setNumOfClasses(int numOfClasses) {
		this.numOfClasses = numOfClasses;
	}

	/**
	 * @return the classInSlot
	 */
	public int getClassInSlot() {
		return classInSlot;
	}

	/**
	 * @param classInSlot
	 *            the classInSlot to set
	 */
	public void setClassInSlot(int classInSlot) {
		this.classInSlot = classInSlot;
	}

	/**
	 * @return the numOfTeachers
	 */
	public int getNumOfTeachers() {
		return numOfTeachers;
	}

	/**
	 * @param numOfTeachers
	 *            the numOfTeachers to set
	 */
	public void setNumOfTeachers(int numOfTeachers) {
		this.numOfTeachers = numOfTeachers;
	}

}
