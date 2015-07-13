package vn.edu.fpt.timetabling.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

	@Column(name = "classes")
	private String classes;

	@Column(name = "capacity")
	private int capacity;

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
	public Room(int roomId, String code, String classes, int capacity) {
		super();
		this.roomId = roomId;
		this.code = code;
		this.classes = classes;
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
	public String getClasses() {
		return classes;
	}

	/**
	 * @param roomType
	 *            the roomType to sets
	 */
	public void setClasses(String classes) {
		this.classes = classes;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Room [roomId=" + roomId + ", code=" + code + ", classes="
				+ classes + ", capacity=" + capacity + "]";
	}
	
	
}
