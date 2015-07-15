package vn.edu.fpt.timetabling.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import vn.edu.fpt.timetabling.model.Department;

public interface DepartmentService {
	public void addDepartment(Department department);

	public void addDepartmentFromFile(File departments) throws IOException;

	public void updateDepartment(Department department);

	public List<Department> listDepartments();

	public Department getDepartmentById(int departmentId);

	public Department getDepartmentByCode(String code);

	public void deleteDepartment(int departmentId);
}
