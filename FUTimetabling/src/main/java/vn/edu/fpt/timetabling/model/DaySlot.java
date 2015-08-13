package vn.edu.fpt.timetabling.model;

import java.util.HashMap;

public class DaySlot {
	private String date;
	private int slot;
	private int setCourseSlot;

	// map between Course Code and DataSchedule
	private HashMap<String, DataSchedule> dataSchedule;

	/**
	 * 
	 */
	public DaySlot() {
		super();
	}

	/**
	 * @param date
	 * @param slot
	 * @param setCourseSlot
	 * @param dataSchedule
	 */
	public DaySlot(String date, int slot, int setCourseSlot,
			HashMap<String, DataSchedule> dataSchedule) {
		super();
		this.date = date;
		this.slot = slot;
		this.setCourseSlot = setCourseSlot;
		this.dataSchedule = dataSchedule;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the slot
	 */
	public int getSlot() {
		return slot;
	}

	/**
	 * @param slot
	 *            the slot to set
	 */
	public void setSlot(int slot) {
		this.slot = slot;
	}

	/**
	 * @return the setCourseSlot
	 */
	public int getSetCourseSlot() {
		return setCourseSlot;
	}

	/**
	 * @param setCourseSlot
	 *            the setCourseSlot to set
	 */
	public void setSetCourseSlot(int setCourseSlot) {
		this.setCourseSlot = setCourseSlot;
	}

	/**
	 * @return the dataSchedule
	 */
	public HashMap<String, DataSchedule> getDataSchedule() {
		return dataSchedule;
	}

	/**
	 * @param dataSchedule
	 *            the dataSchedule to set
	 */
	public void setDataSchedule(HashMap<String, DataSchedule> dataSchedule) {
		this.dataSchedule = dataSchedule;
	}

}
