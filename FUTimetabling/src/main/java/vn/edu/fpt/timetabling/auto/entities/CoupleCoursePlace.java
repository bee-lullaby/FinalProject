package vn.edu.fpt.timetabling.auto.entities;

public class CoupleCoursePlace {

	public Course c;
	public Place p;

	public CoupleCoursePlace() {
		// TODO Auto-generated constructor stub
	}

	public CoupleCoursePlace(Course c, Place p) {
		super();
		this.c = c;
		this.p = p;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return c.code+"-"+p.toString();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
