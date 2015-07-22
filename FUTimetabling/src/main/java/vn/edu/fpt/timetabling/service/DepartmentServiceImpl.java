package vn.edu.fpt.timetabling.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.fpt.timetabling.dao.DepartmentDAO;
import vn.edu.fpt.timetabling.model.Department;

@Service
@Transactional(rollbackFor = Exception.class)
public class DepartmentServiceImpl implements DepartmentService {
	private DepartmentDAO departmentDAO;

	public void setDepartmentDAO(DepartmentDAO departmentDAO) {
		this.departmentDAO = departmentDAO;
	}

	@Override
	public void addDepartment(Department department) {
		departmentDAO.addDepartment(department);
	}

	@Override
	public void addDepartmentFromFile(File departments) throws IOException {
		FileInputStream file = new FileInputStream(departments);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.iterator();
		rowIterator.next();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			Department department = new Department();
			department.setCode(row.getCell(0).getStringCellValue().trim());
			department.setName(row.getCell(1).getStringCellValue().trim());
			departmentDAO.addDepartment(department);
		}
		workbook.close();
		file.close();
	}

	@Override
	public void updateDepartment(Department department) {
		departmentDAO.updateDepartment(department);
	}

	@Override
	public List<Department> listDepartments() {
		return departmentDAO.listDepartments();
	}

	@Override
	public Department getDepartmentById(int departmentId) {
		return departmentDAO.getDepartmentById(departmentId);
	}

	@Override
	public Department getDepartmentByCode(String code) {
		return departmentDAO.getDepartmentByCode(code);
	}

	@Override
	public void deleteDepartment(int departmentId) {
		departmentDAO.deleteDepartment(departmentId);
	}
}
