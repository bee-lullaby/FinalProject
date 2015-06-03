package vn.edu.fpt.timetabling.dao;

import java.util.List;

import vn.edu.fpt.timetabling.model.Staff;

public interface StaffDAO {
	public void addStaff(Staff staff);

	public void updateStaff(Staff staff);

	public List<Staff> listStaff();

	public Staff getStaffById(int staffId);

	public void deleteStaff(int staffId);
}
