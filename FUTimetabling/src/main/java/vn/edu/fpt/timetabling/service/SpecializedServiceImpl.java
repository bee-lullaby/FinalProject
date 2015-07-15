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

import vn.edu.fpt.timetabling.dao.SpecializedDAO;
import vn.edu.fpt.timetabling.model.Specialized;

@Service
@Transactional(rollbackFor = Exception.class)
public class SpecializedServiceImpl implements SpecializedService {
	private SpecializedDAO specializedDAO;

	public void setSpecializedDAO(SpecializedDAO specializedDAO) {
		this.specializedDAO = specializedDAO;
	}

	@Override
	public void addSpecialized(Specialized specialized) {
		specializedDAO.addSpecialized(specialized);
	}

	@Override
	public void addSpecializedFromFile(File specializeds) throws IOException {
		FileInputStream file = new FileInputStream(specializeds);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.iterator();
		rowIterator.next();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			Specialized specialized = new Specialized();
			specialized.setCode(row.getCell(0).getStringCellValue().trim());
			specialized.setName(row.getCell(1).getStringCellValue().trim());
			specializedDAO.addSpecialized(specialized);
		}
		workbook.close();
		file.close();
	}

	@Override
	public void updateSpecialized(Specialized specialized) {
		specializedDAO.updateSpecialized(specialized);
	}

	@Override
	public List<Specialized> listSpecializeds(boolean jointClasses, boolean jointStudents) {
		return specializedDAO.listSpecializeds(jointClasses, jointStudents);
	}

	@Override
	public Specialized getSpecializedById(int specializedId, boolean jointClasses, boolean jointStudents) {
		return specializedDAO.getSpecializedById(specializedId, jointClasses, jointStudents);
	}

	@Override
	public Specialized getSpecializedByCode(String code, boolean jointClasses, boolean jointStudents) {
		return specializedDAO.getSpecializedByCode(code, jointClasses, jointStudents);
	}

	@Override
	public void deleteSpecialized(int specializedId) {
		specializedDAO.deleteSpecialized(specializedId);
	}

	@Override
	public List<Specialized> listDetailSpecializeds(boolean jointClasses, boolean jointStudents) {
		return specializedDAO.listDetailSpecializeds(jointClasses, jointStudents);
	}
}
