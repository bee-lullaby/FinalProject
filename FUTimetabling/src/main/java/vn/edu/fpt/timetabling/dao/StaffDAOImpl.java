package vn.edu.fpt.timetabling.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import vn.edu.fpt.timetabling.model.Staff;

@Repository
public class StaffDAOImpl implements StaffDAO {
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
	}

	@Override
	public void updateStaff(Staff staff) {
		getCurrentSession().update(staff);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Staff> listStaffs() {
		List<Staff> staff = (List<Staff>) getCurrentSession().createQuery("FROM vn.edu.fpt.timetabling.model.Staff")
				.list();
		return staff;
	}

	@Override
	public Staff getStaffById(int staffId) {
		Staff staff = (Staff) getCurrentSession().get(Staff.class, new Integer(staffId));
		return staff;
	}

	@Override
	public Staff getStaffByEmail(String email) {
		String hql = "FROM vn.edu.fpt.timetabling.model.Staff S WHERE lower(S.email) = lower(:email)";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("email", email);
		return (Staff) query.uniqueResult();
	}

	@Override
	public Staff getStaffByAccount(String account) {
		String hql = "FROM vn.edu.fpt.timetabling.model.Staff S WHERE lower(S.account) = lower(:account)";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("account", account);
		return (Staff) query.uniqueResult();
	}

	
	@Override
	public void deleteStaff(int staffId) {
		Staff staff = getStaffById(staffId);
		getCurrentSession().delete(staff);
	}
}
