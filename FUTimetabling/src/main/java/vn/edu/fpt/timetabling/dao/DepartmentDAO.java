package vn.edu.fpt.timetabling.dao;

import java.util.List;

import vn.edu.fpt.timetabling.model.Department;

public interface DepartmentDAO {
	public void addDepartment(Department department);

	public void updateDepartment(Department department);

	public List<Department> listDepartments();

	public Department getDepartmentById(int departmentId);

	public Department getDepartmentByCode(String code);

	public void deleteDepartment(int departmentId);
}
