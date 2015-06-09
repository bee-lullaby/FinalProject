package vn.edu.fpt.timetabling.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import vn.edu.fpt.timetabling.model.Department;

@Repository
public class DepartmentDAOImpl implements DepartmentDAO {

	private static final Logger logger = LoggerFactory
			.getLogger(DepartmentDAOImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addDepartment(Department department) {
		getCurrentSession().persist(department);
		logger.info("Department was saved successfully, course departments="
				+ department);
	}

	@Override
	public void updateDepartment(Department department) {
		getCurrentSession().update(department);
		logger.info("Department was updated successfully, course departments="
				+ department);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Department> listDepartments() {
		List<Department> departments = (List<Department>) getCurrentSession()
				.createQuery("from vn.edu.fpt.timetabling.model.Department")
				.list();
		for (Department department : departments) {
			logger.info("Department list:" + department);
		}
		return departments;
	}

	@Override
	public Department getDepartmentById(int departmentId) {
		Department department = (Department) getCurrentSession().load(
				Department.class, new Integer(departmentId));
		logger.info("Department was loaded successfully, course departments="
				+ department);
		return department;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Department getDepartmentByCode(String code) {
		String hql = "FROM vn.edu.fpt.timetabling.model.Department D WHERE D.code = :code";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("code", code);
		List<Department> department = (List<Department>) query.list();
		logger.info("Department was loaded successfully, department details="
				+ department.get(0));
		return department.get(0);
	}

	@Override
	public void deleteDepartment(int departmentId) {
		Department department = getDepartmentById(departmentId);
		if (department != null) {
			getCurrentSession().delete(department);
			logger.info("Department was deleted successfully, course departments="
					+ department);
		}
	}

}
