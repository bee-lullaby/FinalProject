package vn.edu.fpt.timetabling.auto.entities;

public class DaySlot{
	public int day;
	public int slot;
	public boolean isAssigned;
	public DaySlot() {
		// TODO Auto-generated constructor stub
	}
	
	public DaySlot(int d, int s){
		this.day = d; this.slot = s;
	}

	public DaySlot(int d, int s, boolean isAssigned) {
		super();
		this.day = d;
		this.slot = s;
		this.isAssigned = isAssigned;
	}
	
	public boolean compareAllElement(DaySlot ds){
		if (this.day == ds.day && this.slot == ds.slot && this.isAssigned == ds.isAssigned) {
			return true;
		}
		return false;
	}
	
}
