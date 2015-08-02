package vn.edu.fpt.timetabling.auto.entities;

public class Place {

	public int block;
	public int slot;
	public int session;

	public Place() {
		// TODO Auto-generated constructor stub
	}

	public Place(int block, int session, int slot) {
		super();
		this.block = block;
		this.slot = slot;
		this.session = session;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return block+"-"+session+"-"+slot;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
