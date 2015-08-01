package vn.edu.fpt.timetabling.model;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "rooms")
public class Room {

	@Id
	@Column(name = "room_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roomId;

	@Column(name = "code")
	private String code;

	@Column(name = "courses")
	private String courses;

	@Column(name = "capacity")
	private int capacity;

	@ManyToOne
	@JoinColumn(name = "building_id")
	private Building building;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "room", orphanRemoval = true)
	@OrderBy
	Set<Timetable> timetables = new LinkedHashSet<Timetable>();

	public Building getBuilding() {
		return building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	/**
	 * 
	 */
	public Room() {
		super();
	}

	/**
	 * @param roomId
	 * @param code
	 * @param roomType
	 * @param capacity
	 */
	public Room(int roomId, String code, String courses, int capacity) {
		super();
		this.roomId = roomId;
		this.code = code;
		this.courses = courses;
		this.capacity = capacity;
	}

	/**
	 * @return the roomId
	 */
	public int getRoomId() {
		return roomId;
	}

	/**
	 * @param roomId
	 *            the roomId to set
	 */
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the roomType
	 */
	public String getCourses() {
		return courses;
	}

	/**
	 * @param roomType
	 *            the roomType to sets
	 */
	public void setCourses(String courses) {
		this.courses = courses;
	}

	/**
	 * @return the capacity
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * @param capacity
	 *            the capacity to set
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	/**
	 * @return the timetables
	 */
	public Set<Timetable> getTimetables() {
		return timetables;
	}

	/**
	 * @param timetables
	 *            the timetables to set
	 */
	public void setTimetables(Set<Timetable> timetables) {
		this.timetables = timetables;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Room [roomId=" + roomId + ", code=" + code + ", classes=" + courses + ", capacity=" + capacity + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof Room) {
			return roomId == ((Room) obj).getRoomId();
		}
		return false;
	}

}
