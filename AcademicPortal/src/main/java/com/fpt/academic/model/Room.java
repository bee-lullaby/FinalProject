package com.fpt.academic.model;

public class Room {
	
	int room_id;
	String code;
	String room_type;
	int capacity;
	
	
	public Room() {
		super();
	}


	public Room(int room_id, String code, String room_type, int capacity) {
		super();
		this.room_id = room_id;
		this.code = code;
		this.room_type = room_type;
		this.capacity = capacity;
	}


	public int getRoom_id() {
		return room_id;
	}


	public void setRoom_id(int room_id) {
		this.room_id = room_id;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getRoom_type() {
		return room_type;
	}


	public void setRoom_type(String room_type) {
		this.room_type = room_type;
	}


	public int getCapacity() {
		return capacity;
	}


	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	
	
	
}
