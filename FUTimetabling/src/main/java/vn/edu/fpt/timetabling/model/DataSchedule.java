package vn.edu.fpt.timetabling.model;

public class DataSchedule {
	private int remainSlots;

	private int numOfClasses;
	private int learnCourseInSlot;

	private int totalRooms;
	private int classesInSlot;

	private int numOfTeachers;

	/**
	 * 
	 */
	public DataSchedule() {
		super();
	}

	/**
	 * @param remainSlots
	 * @param numOfClasses
	 * @param classInSlot
	 * @param totalRooms
	 * @param remainRooms
	 * @param numOfTeachers
	 */
	public DataSchedule(int remainSlots, int numOfClasses, int learnCourseInSlot,
			int totalRooms, int classesInSlot, int numOfTeachers) {
		super();
		this.remainSlots = remainSlots;
		this.numOfClasses = numOfClasses;
		this.learnCourseInSlot = learnCourseInSlot;
		this.totalRooms = totalRooms;
		this.classesInSlot = classesInSlot;
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
	public int getLearnCourseInSlot() {
		return learnCourseInSlot;
	}

	/**
	 * @param classInSlot
	 *            the classInSlot to set
	 */
	public void setLearnCourseInSlot(int learnCourseInSlot) {
		this.learnCourseInSlot = learnCourseInSlot;
	}

	/**
	 * @return the remainRooms
	 */
	public int getClassesInSlot() {
		return classesInSlot;
	}

	/**
	 * @param remainRooms
	 *            the remainRooms to set
	 */
	public void setClassesInSlot(int classesInSlot) {
		this.classesInSlot = classesInSlot;
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

	/**
	 * @return the remainSlots
	 */
	public int getRemainSlots() {
		return remainSlots;
	}

	/**
	 * @param remainSlots
	 *            the remainSlots to set
	 */
	public void setRemainSlots(int remainSlots) {
		this.remainSlots = remainSlots;
	}

	/**
	 * @return the totalRooms
	 */
	public int getTotalRooms() {
		return totalRooms;
	}

	/**
	 * @param totalRooms
	 *            the totalRooms to set
	 */
	public void setTotalRooms(int totalRooms) {
		this.totalRooms = totalRooms;
	}

}
