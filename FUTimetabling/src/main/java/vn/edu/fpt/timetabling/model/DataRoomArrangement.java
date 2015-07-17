package vn.edu.fpt.timetabling.model;

import java.util.List;

public class DataRoomArrangement {
	private Room room;
	private List<Timetable> timetables;

	/**
	 * 
	 */
	public DataRoomArrangement() {
		super();
	}

	/**
	 * @param room
	 * @param timetables
	 */
	public DataRoomArrangement(Room room, List<Timetable> timetables) {
		super();
		this.room = room;
		this.timetables = timetables;
	}

	/**
	 * @return the room
	 */
	public Room getRoom() {
		return room;
	}

	/**
	 * @param room
	 *            the room to set
	 */
	public void setRoom(Room room) {
		this.room = room;
	}

	/**
	 * @return the timetables
	 */
	public List<Timetable> getTimetables() {
		return timetables;
	}

	/**
	 * @param timetables
	 *            the timetables to set
	 */
	public void setTimetables(List<Timetable> timetables) {
		this.timetables = timetables;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DataRoomArrangement [room=" + room + "]";
	}

}
