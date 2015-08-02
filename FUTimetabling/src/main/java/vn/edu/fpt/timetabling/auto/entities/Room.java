package vn.edu.fpt.timetabling.auto.entities;

public class Room {
	public int ID;
	public String code;
	public int buildingID;
	public int supplySlotCapacity;
	public int availableSlot;
	public int capacity;
	
	
	public Room() {
		// TODO Auto-generated constructor stub
	}
	
	public Room(int iD, String code) {
		super();
		ID = iD;
		this.code = code;
	}

	public Room(int iD) {
		super();
		ID = iD;
	}

	public Room(String code) {
		super();
		this.code = code;
	}

	
	public Room(int iD, String code, int buildingID) {
		super();
		ID = iD;
		this.code = code;
		this.buildingID = buildingID;
	}

	public Room(int iD, String code, int buildingID, int capacity) {
		super();
		ID = iD;
		this.code = code;
		this.buildingID = buildingID;
		this.capacity = capacity;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
