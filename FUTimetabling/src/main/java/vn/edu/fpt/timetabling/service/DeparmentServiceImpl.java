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
public class DeparmentServiceImpl implements DepartmentService {
	
	
	private DepartmentDAO departmentDAO;

	public void setDepartmentDAO(DepartmentDAO departmentDAO) {
		this.departmentDAO = departmentDAO;
	}
	
	@Override
	@Transactional
	public void addDepartment(Department department) {
		// TODO Auto-generated method stub
		departmentDAO.addDepartment(department);
	}

	@Override
	@Transactional
	public void addDepartmentFromFile(File departments) {
		try {
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	@Transactional
	public void updateDepartment(Department department) {
		// TODO Auto-generated method stub
		departmentDAO.updateDepartment(department);
	}

	@Override
	@Transactional
	public List<Department> listDepartments() {
		// TODO Auto-generated method stub
		return departmentDAO.listDepartments();
	}

	@Override
	@Transactional
	public Department getDepartmentById(int departmentId) {
		// TODO Auto-generated method stub
		return departmentDAO.getDepartmentById(departmentId);
	}

	@Override
	@Transactional
	public Department getDepartmentByCode(String code) {
		// TODO Auto-generated method stub
		return getDepartmentByCode(code);
	}

	@Override
	@Transactional
	public void deleteDepartment(int departmentId) {
		// TODO Auto-generated method stub
		departmentDAO.deleteDepartment(departmentId);
	}

}
