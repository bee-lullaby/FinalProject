package vn.edu.fpt.timetabling.service;

import java.util.List;

import vn.edu.fpt.timetabling.model.Staff;

public interface StaffService {
	public void addStaff(Staff staff);

	public void updateStaff(Staff staff);

	public List<Staff> listStaff();

	public Staff getStaffById(int staffId);

	public Staff getStaffByEmail(String email);

	public void deleteStaff(int staffId);
}
