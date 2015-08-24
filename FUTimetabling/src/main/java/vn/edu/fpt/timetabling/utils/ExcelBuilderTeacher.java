package vn.edu.fpt.timetabling.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
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

import vn.edu.fpt.timetabling.model.Course;
import vn.edu.fpt.timetabling.model.Room;
import vn.edu.fpt.timetabling.model.TeacherSemester;
import vn.edu.fpt.timetabling.model.Timetable;

public class ExcelBuilderTeacher extends AbstractExcelView {
	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HashMap<TeacherSemester, List<Timetable>> timetablesMap = (HashMap<TeacherSemester, List<Timetable>>) model
				.get("timetablesMap");
		SimpleDateFormat dateFormat = new SimpleDateFormat("EEEEE");

		CellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(HSSFColor.BLUE.index);
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		Font font = workbook.createFont();
		font.setFontName("Arial");
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setColor(HSSFColor.WHITE.index);
		style.setFont(font);
		CellStyle styleFailed = workbook.createCellStyle();
		styleFailed.setFillForegroundColor(HSSFColor.RED.index);
		styleFailed.setFillPattern(CellStyle.SOLID_FOREGROUND);

		for (Entry<TeacherSemester, List<Timetable>> entry : timetablesMap.entrySet()) {
			HSSFSheet sheet = workbook.createSheet(entry.getKey().getTeacher().getAccount());
			sheet.setDefaultColumnWidth(30);

			HSSFRow header = sheet.createRow(0);
			header.createCell(0).setCellValue("DATE");
			header.getCell(0).setCellStyle(style);
			header.createCell(1).setCellValue("ROOM");
			header.getCell(1).setCellStyle(style);
			header.createCell(2).setCellValue("SUBJECT CODE");
			header.getCell(2).setCellStyle(style);
			header.createCell(3).setCellValue("SUBJECT");
			header.getCell(3).setCellStyle(style);
			header.createCell(4).setCellValue("CLASS");
			header.getCell(4).setCellStyle(style);
			header.createCell(5).setCellValue("SLOT, TIME");
			header.getCell(5).setCellStyle(style);

			int rowCount = 1;
			List<Timetable> timetables = entry.getValue();
			for (int i = 0; i < timetables.size(); i++) {
				Timetable timetable = timetables.get(i);
				HSSFRow row = sheet.createRow(rowCount++);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(timetable.getDate());
				Course course = timetable.getClassCourseSemester().getCourseSemester().getCourse();
				Room room = timetable.getRoom();
				String classString = timetable.getClassCourseSemester().getClassSemester().getClassFPT().getCode();
				if (i + 1 < timetables.size()) {
					Timetable nextTimetable = timetables.get(i + 1);
					if (nextTimetable.isSameTime(timetable)) {
						classString += ", "
								+ nextTimetable.getClassCourseSemester().getClassSemester().getClassFPT().getCode();
						i++;
					}
				}
				row.createCell(0)
						.setCellValue(dateFormat.format(calendar.getTime()) + ", " + calendar.get(Calendar.DAY_OF_MONTH)
								+ "/" + (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.YEAR));
				if (room != null) {
					row.createCell(1).setCellValue(room.getCode());
				} else {
					row.createCell(1).setCellValue("");
					row.getCell(1).setCellStyle(styleFailed);
				}
				row.createCell(2).setCellValue(course.getCode());
				row.createCell(3).setCellValue(course.getName());
				row.createCell(4).setCellValue(classString);
				int slot = timetable.getSlot();
				row.createCell(5).setCellValue("Slot" + slot + " ( from " + Const.Timetable.SLOT_TIME_MAP[slot - 1][0]
						+ ":" + Const.Timetable.SLOT_TIME_MAP[slot - 1][1] + ")");
			}
		}
	}
}
