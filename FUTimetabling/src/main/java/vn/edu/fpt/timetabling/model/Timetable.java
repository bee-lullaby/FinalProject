package vn.edu.fpt.timetabling.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "time_table")
public class Timetable {

	@Id
	@Column(name = "time_table_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int timeTableId;

	@Column(name = "date", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@Column(name = "slot")
	private int slot;

	@ManyToOne
	@JoinColumn(name = "room_id")
	private Room room;

	@ManyToOne
	@JoinColumn(name = "teacher_id")
	private Teacher teacher;

	/**
	 * 
	 */
	public Timetable() {
		super();
	}

	/**
	 * @param timeTableId
	 * @param date
	 * @param slot
	 * @param room
	 * @param teacher
	 */
	public Timetable(int timeTableId, Date date, int slot, Room room,
			Teacher teacher) {
		super();
		this.timeTableId = timeTableId;
		this.date = date;
		this.slot = slot;
		this.room = room;
		this.teacher = teacher;
	}

	/**
	 * @return the timeTableId
	 */
	public int getTimeTableId() {
		return timeTableId;
	}

	/**
	 * @param timeTableId
	 *            the timeTableId to set
	 */
	public void setTimeTableId(int timeTableId) {
		this.timeTableId = timeTableId;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
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
	 * @return the teacher
	 */
	public Teacher getTeacher() {
		return teacher;
	}

	/**
	 * @param teacher
	 *            the teacher to set
	 */
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TimeTable [timeTableId=" + timeTableId + ", date=" + date
				+ ", slot=" + slot + ", room=" + room + ", teacher=" + teacher
				+ "]";
	}

}
