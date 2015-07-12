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
public class SpecializedServiceImpl implements SpecializedService {

	private SpecializedDAO specializedDAO;

	public void setSpecializedDAO(SpecializedDAO specializedDAO) {
		this.specializedDAO = specializedDAO;
	}

	@Override
	@Transactional
	public void addSpecialized(Specialized specialized) {
		specializedDAO.addSpecialized(specialized);
	}

	@Override
	@Transactional
	public void addSpecializedFromFile(File specializeds) {
		try {
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	@Transactional
	public void updateSpecialized(Specialized specialized) {
		specializedDAO.updateSpecialized(specialized);
	}

	@Override
	@Transactional
	public List<Specialized> listSpecializeds() {
		return specializedDAO.listSpecializeds();
	}

	@Override
	@Transactional
	public Specialized getSpecializedById(int specializedId) {
		return specializedDAO.getSpecializedById(specializedId);
	}

	@Override
	@Transactional
	public Specialized getSpecializedByCode(String code) {
		return specializedDAO.getSpecializedByCode(code);
	}

	@Override
	@Transactional
	public void deleteSpecialized(int specializedId) {
		specializedDAO.deleteSpecialized(specializedId);
	}

	@Override
	@Transactional
	public List<Specialized> listDetailSpecializeds() {
		return specializedDAO.listDetailSpecializeds();
	}

}
