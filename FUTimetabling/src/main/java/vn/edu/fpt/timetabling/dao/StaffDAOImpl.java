package vn.edu.fpt.timetabling.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import vn.edu.fpt.timetabling.model.Staff;

@Repository
public class StaffDAOImpl implements StaffDAO {

	private static final Logger logger = LoggerFactory
			.getLogger(StaffDAOImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addStaff(Staff staff) {
		getCurrentSession().persist(staff);
		logger.info("Staff saved successfully, staff details=" + staff);
	}

	@Override
	public void updateStaff(Staff staff) {
		getCurrentSession().update(staff);
		logger.info("Staff updated successfully, staff details=" + staff);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Staff> listStaff() {
		List<Staff> staff = (List<Staff>) getCurrentSession().createQuery(
				"FROM vn.edu.fpt.timetabling.model.Staff").list();
		for (Staff staffTemp : staff) {
			logger.info("Staff list::" + staffTemp);
		}
		return staff;
	}

	@Override
	public Staff getStaffById(int staffId) {
		Staff staff = (Staff) getCurrentSession().load(Staff.class,
				new Integer(staffId));
		logger.info("Staff loaded successfully, staff details=" + staff);
		return staff;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Staff getStaffByEmail(String email) {
		String hql = "FROM vn.edu.fpt.timetabling.model.Staff S WHERE S.email = :email";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("email", email);
		List<Staff> staff = (List<Staff>) query.list();
		if (staff != null && !staff.isEmpty()) {
			return staff.get(0);
		} else {
			return null;
		}
	}

	@Override
	public void deleteStaff(int staffId) {
		Staff staff = getStaffById(staffId);
		if (staff != null) {
			getCurrentSession().delete(staff);
		}
		logger.info("Staff deleted successfully, staff details=" + staff);
	}
}
