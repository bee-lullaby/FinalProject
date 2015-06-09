package vn.edu.fpt.timetabling.dao;

import java.util.List;

import vn.edu.fpt.timetabling.model.Staff;

public interface StaffDAO {
	public void addStaff(Staff staff);

	public void updateStaff(Staff staff);

	public List<Staff> listStaffs();

	public Staff getStaffById(int staffId);
	
	public Staff getStaffByEmail(String email);

	public void deleteStaff(int staffId);
}
