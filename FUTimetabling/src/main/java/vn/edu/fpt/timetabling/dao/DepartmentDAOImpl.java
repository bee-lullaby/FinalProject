package vn.edu.fpt.timetabling.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import vn.edu.fpt.timetabling.model.Department;

@Repository
public class DepartmentDAOImpl implements DepartmentDAO {
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
	}

	@Override
	public void updateDepartment(Department department) {
		getCurrentSession().update(department);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Department> listDepartments() {
		String hql = "FROM vn.edu.fpt.timetabling.model.Department D" + " LEFT OUTER JOIN FETCH D.courses";
		Query query = getCurrentSession().createQuery(hql);
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Department> departments = (List<Department>) query.list();
		return departments;
	}

	@Override
	public Department getDepartmentById(int departmentId) {
		String hql = "FROM vn.edu.fpt.timetabling.model.Department D" + " LEFT OUTER JOIN FETCH D.courses"
				+ " WHERE D.departmentId = :departmentId";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("departmentId", departmentId);
		return (Department) query.uniqueResult();
	}

	@Override
	public Department getDepartmentByCode(String code) {
		String hql = "FROM vn.edu.fpt.timetabling.model.Department D" + " LEFT OUTER JOIN FETCH D.courses"
				+ " WHERE D.code = :code";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("code", code);
		return (Department) query.uniqueResult();
	}

	@Override
	public void deleteDepartment(int departmentId) {
		Department department = getDepartmentById(departmentId);
		getCurrentSession().delete(department);
	}
}
