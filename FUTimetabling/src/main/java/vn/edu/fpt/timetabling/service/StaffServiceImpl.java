package vn.edu.fpt.timetabling.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.fpt.timetabling.dao.StaffDAO;
import vn.edu.fpt.timetabling.model.Staff;

@Service
public class StaffServiceImpl implements StaffService {

	private StaffDAO staffDAO;

	public void setStaffDAO(StaffDAO staffDAO) {
		this.staffDAO = staffDAO;
	}

	@Override
	@Transactional
	public void addStaff(Staff staff) {
		staffDAO.addStaff(staff);
	}

	@Override
	@Transactional
	public void updateStaff(Staff staff) {
		staffDAO.updateStaff(staff);
	}

	@Override
	@Transactional
	public List<Staff> listStaff() {
		return staffDAO.listStaffs();
	}

	@Override
	@Transactional
	public Staff getStaffById(int staffId) {
		return staffDAO.getStaffById(staffId);
	}

	@Override
	@Transactional
	public Staff getStaffByEmail(String email) {
		return staffDAO.getStaffByEmail(email);
	}

	@Override
	@Transactional
	public void deleteStaff(int staffId) {
		staffDAO.deleteStaff(staffId);
	}
}
