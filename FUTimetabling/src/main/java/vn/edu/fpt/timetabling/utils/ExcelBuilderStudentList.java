package vn.edu.fpt.timetabling.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import vn.edu.fpt.timetabling.model.ClassSemester;
import vn.edu.fpt.timetabling.model.Student;

public class ExcelBuilderStudentList extends AbstractExcelView {
	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HashMap<ClassSemester, Map<String, Student>> studentsMap = (HashMap<ClassSemester, Map<String, Student>>) model
				.get("studentsMap");

		CellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(HSSFColor.BLUE.index);
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		Font font = workbook.createFont();
		font.setFontName("Arial");
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setColor(HSSFColor.WHITE.index);
		style.setFont(font);

		for (Entry<ClassSemester, Map<String, Student>> entry : studentsMap.entrySet()) {
			HSSFSheet sheet = workbook.createSheet(entry.getKey().getClassFPT().getCode());
			sheet.setDefaultColumnWidth(30);

			HSSFRow header = sheet.createRow(0);
			header.createCell(0).setCellValue("ROLL NUMBER");
			header.getCell(0).setCellStyle(style);
			header.createCell(1).setCellValue("NAME");
			header.getCell(1).setCellStyle(style);
			header.createCell(2).setCellValue("SPECIALIZED");
			header.getCell(2).setCellStyle(style);
			header.createCell(3).setCellValue("DETAIL SPECIALIZED");
			header.getCell(3).setCellStyle(style);
			header.createCell(4).setCellValue("SEMESTER NUMBER");
			header.getCell(4).setCellStyle(style);

			int rowCount = 1;
			for (Student student : entry.getValue().values()) {
				HSSFRow row = sheet.createRow(rowCount++);
				row.createCell(0).setCellValue(student.getStudentCode());
				row.createCell(1).setCellValue(student.getName());
				row.createCell(2).setCellValue(student.getSpecialized().getCode());
				row.createCell(3).setCellValue(student.getDetailSpecialized().getCode());
				row.createCell(4).setCellValue(student.getSemester());
			}
		}
	}
}
