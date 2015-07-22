package algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;

import entities.ClassCourse;
import entities.ClassFU;
import entities.Course;
import entities.DataCenter;
import entities.Room;
import entities.SingleSolution;
import entities.Teacher;

public class MainApp {

	DataCenter DA;
	TimeTableAllClass TA;

	AssignTeacher2 teacherAssigner2;
	AssignRoom roomAssigner;
	final static int LIMIT_NBSOLUTION_PERCLASS = TimeTableAllClass.LIMIT_NBSOLUTION_PERCLASS;

	public void timetabling() {
		long startTime = System.currentTimeMillis();
		// =================================================================
		DA = new DataCenter();
		TA = new TimeTableAllClass();
		TA.DA = this.DA;
		teacherAssigner2 = new AssignTeacher2();
		teacherAssigner2.DA = this.DA;
		roomAssigner = new AssignRoom();
		roomAssigner.DA = this.DA;

		String fn_data_course_class = "data/data_course_class_merged.txt";
		String fn_data_teacher = "data/data_teacher.txt";
		String fn_data_room_building = "data/data_room_building.txt";
		String fn_data_mergedCases = "data/data_mergedCases.txt";
		String fn_classTimeTableSolutions = "data/data_solutionWarehouses.dat";
		String fn_conflictMatrix = "data/data_conflictMatrix.txt";
		String fn_beingUsedTimeTable = "data/data_beingusedTT.dat";
		String fn_allTeacherTTB = "teacherTimeTable.html";
		String fn_allClass_TimetableFull = "allClassTimeTable.html";

		DA.loadData_Course_Class(fn_data_course_class); // for all phases
		DA.loadData_Teacher_UsingCode(fn_data_teacher); // for phase 3
		DA.loadData_Room_Building(fn_data_room_building); // for phase 4
		DA.loadData_mergedCases(fn_data_mergedCases); // for phase 1

		// ---------------------------------------------------------------------------
		// PHASE 1:
		long startTime_phase1 = System.currentTimeMillis();
		// //TA.makeSolutionWarehouse(fn_classTimeTableSolutions);
		long estimatedTime_phase1 = System.currentTimeMillis()
				- startTime_phase1;

		// ---------------------------------------------------------------------------
		// PHASE 2:
		long startTime_phase2 = System.currentTimeMillis();
		TA.findOptimalTimeTable_v2_2(fn_classTimeTableSolutions);
		TA.savebeingUsedTimeTable(fn_beingUsedTimeTable);
		TA.writeMatrix2File(fn_conflictMatrix, TA.beingUsedTimeTable);

		SingleSolution[] sol = TA.loadbeingUsedTimeTable(fn_beingUsedTimeTable);
		// TA.printTimeTableAllClass(fn_allClassTTB,sol);
		

		long estimatedTime_phase2 = System.currentTimeMillis()
				- startTime_phase2;

		// ---------------------------------------------------------------------------
		// PHASE 3:
		long startTime_phase3 = System.currentTimeMillis();
		teacherAssigner2.assignTeacherUsingLS(fn_conflictMatrix);
		// teacherAssigner2.printAllTeacherHTML(fn_allTeacherTTB2, sol);

		long estimatedTime_phase3 = System.currentTimeMillis()
				- startTime_phase3;

		// ---------------------------------------------------------------------------
		// PHASE 4:
		long startTime_phase4 = System.currentTimeMillis();
		roomAssigner.assignRoomUsingFor(fn_beingUsedTimeTable);
		System.out.println("\nRoom demand of every slot:");
		TA.calRoomDemandEverySlot(sol);
		System.out.println();
		long estimatedTime_phase4 = System.currentTimeMillis()
				- startTime_phase4;

		// ==================================================================
		printTimeTableHTML_AllClass(fn_allClass_TimetableFull, sol);
		printTimeTable_AllTeacher(fn_allTeacherTTB, sol);
		PoiWriteExcelFile(TA, "timetable.xls", sol);
		// ==================================================================
		long estimatedTime = System.currentTimeMillis() - startTime;
		System.out.println("\nTime-consuming phase 1: "
				+ (double) estimatedTime_phase1 / 1000 + " secs");
		System.out.println("Time-consuming phase 2: "
				+ (double) estimatedTime_phase2 / 1000 + " secs");
		System.out.println("Time-consuming phase 3: "
				+ (double) estimatedTime_phase3 / 1000 + " secs");
		System.out.println("Time-consuming phase 4: "
				+ (double) estimatedTime_phase4 / 1000 + " secs");
		System.out.println("Time-consuming all phases and loading data: "
				+ (double) estimatedTime / 1000 + " secs");
	}

	public void printOneClass(TimeTableAllClass TA, SingleSolution sol,
			HSSFSheet sheet, int clIdx, int r) {

		for (int i = 0; i < 6; i++) {
			sheet.createRow(r + i);
		}
		HSSFRow row = sheet.getRow(r);
		HSSFCell cell = row.createCell(2);
		cell.setCellValue(TA.DA.classes[clIdx].code);
		CellStyle style = sheet.getWorkbook().createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		cell.setCellStyle(style);
		int ss = -1;
		sheet.addMergedRegion(new CellRangeAddress(r, r + 5, 2, 2));
		sheet.addMergedRegion(new CellRangeAddress(r, r + 5, 3, 3));
		cell = row.createCell(4);
		cell.setCellValue("Block 1");
		cell.setCellStyle(style);
		cell = sheet.getRow(r + 3).createCell(4);
		cell.setCellValue("Block 2");
		cell.setCellStyle(style);
		sheet.addMergedRegion(new CellRangeAddress(r, r + 2, 4, 4));
		sheet.addMergedRegion(new CellRangeAddress(r + 3, r + 5, 4, 4));

		for (int i = 0; i < 2 * TA.DA.nbSlotsPerHalfDay; i++) {
			int x = 0;
			x = i;
			if ((i == 3) || (i == 4) || (i == 5)) {
				x = x - 3;
			}
			for (int j = 0; j < TA.DA.nbDays_20; j++) {
				int y = 5;
				int x1 = x;
				String str = "";
				String rc = "No room";
				if (j < 10) {
					y = y + j;
				}
				if (j > 9) {
					y = y + j - 10;
					x1 = x1 + 3;
				}

				if (sol.T[i][j] >= 0) {
					if (i < 3 && ss == -1) {
						ss = 0;
					}
					if (i > 2 && ss == -1)
						ss = 1;
					ClassCourse cc = DA.mID2ClassCourse_all.get(sol.T[i][j]);
					Course c = DA.mClassCourse2Course.get(cc);
/*					if (DA.mClassCourse2AssignedTeacher.get(cc) != null) {
						Teacher tc = DA.mClassCourse2AssignedTeacher.get(cc);
						tcc = tc.code;
					}*/
					if (DA.mClassCourse2AssignedRoom.get(cc) != null) {
						Room room = DA.mClassCourse2AssignedRoom.get(cc);
						rc = room.code;
					}
					str = c.code;
					row = sheet.getRow(r + x1);
					cell = row.createCell(y);
					cell.setCellValue(str+" "+rc);
					style.setWrapText(true);
					cell.setCellStyle(style);
				}
			}
			row = sheet.getRow(r);
			cell = row.createCell(3);
			if (ss == 0) {
				cell.setCellValue("Sang");
				cell.setCellStyle(style);
			}
			if (ss == 1)
				cell.setCellValue("Chieu");
				cell.setCellStyle(style);
		}
	}

	public void PoiWriteExcelFile(TimeTableAllClass TA, String fn,
			SingleSolution[] sol) {
		try {
			FileOutputStream fileOut = new FileOutputStream(fn);
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("Timetable");
			HSSFRow row1 = sheet.createRow(0);
			HSSFCell cell = row1.createCell(0);
			cell.setCellValue("Timetable FU HL");
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 14));
			CellStyle Title = workbook.createCellStyle();
			Font font = workbook.createFont();
			font.setBold(true);
			font.setFontHeightInPoints((short) 12);
			Title.setFont(font);
			Title.setAlignment(CellStyle.ALIGN_CENTER);
			cell.setCellStyle(Title);
			
			
			CellStyle st2 = workbook.createCellStyle();
			font.setFontHeightInPoints((short) 9);
			st2.setFont(font);
			st2.setAlignment(CellStyle.ALIGN_CENTER);

			HSSFRow row2 = sheet.createRow(1);
			cell = row2.createCell(0);
			cell.setCellValue("Ky");
			cell.setCellStyle(st2);
			cell = row2.createCell(1);
			cell.setCellValue("Khoa");
			cell.setCellStyle(st2);
			cell = row2.createCell(2);
			cell.setCellValue("Lop");
			cell.setCellStyle(st2);
			cell = row2.createCell(3);
			cell.setCellValue("Ca hoc");
			cell.setCellStyle(st2);
			cell = row2.createCell(4);
			cell.setCellValue("Block");
			cell.setCellStyle(st2);
			cell = row2.createCell(5);
			cell.setCellValue("3 Tuan dau");
			cell.setCellStyle(st2);
			cell = row2.createCell(10);
			cell.setCellValue("3 Tuan sau");
			cell.setCellStyle(st2);
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 5, 9));
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 10, 14));
			int r = 2;

			for (int icl = 0; icl < TA.DA.nbClass; icl++) {
				printOneClass(TA, sol[icl], sheet, icl, r);
				r = r + 6;
			}

			workbook.write(fileOut);
			fileOut.flush();
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Print timetable of all classes with: course, time(day,slot), room,
	 * professor.
	 */
	public void printTimeTableHTML_AllClass(String fn, SingleSolution[] sol) {
		try {
			File f = new File(fn);
			PrintWriter out = new PrintWriter(f);
			// = this.TA.beingUsedTimeTable;
			for (int icl = 0; icl < DA.nbClass; icl++) {
				printTimeTableHTML_OneClass(sol[icl], out, icl);
			}

			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Done printing timetable all class to " + fn);
	}

	/** Print timetable of one class. */
	public void printTimeTableHTML_OneClass(SingleSolution sol,
			PrintWriter out, int clIdx) {
		out.println("solution position = " + sol.position);
		out.println(", class = " + DA.classes[clIdx].code);
		out.println("<table border = 1>");
		for (int i = 0; i < 2 * DA.nbSlotsPerHalfDay; i++) {
			out.print("<tr>");
			for (int j = 0; j < DA.nbDays_20; j++) {
				out.print("<td height = 20 width = 80 ");
				String str = "";
				String tcc = "no teacher";
				String rc = "no room";
				if (sol.T[i][j] >= 0) {
					ClassCourse cc = DA.mID2ClassCourse_all.get(sol.T[i][j]);
					Course c = DA.mClassCourse2Course.get(cc);
					// System.out.println(cc.code);
					if (DA.mClassCourse2AssignedTeacher.get(cc) != null) {
						Teacher tc = DA.mClassCourse2AssignedTeacher.get(cc);
						tcc = tc.code;
					}
					if (DA.mClassCourse2AssignedRoom.get(cc) != null) {
						Room r = DA.mClassCourse2AssignedRoom.get(cc);
						rc = r.code;
					}
					str = c.code;
					int stt = cc.stt;
					if (stt == 3) {
						out.println("bgcolor='yellow'> ");
					} else {
						// out.println("bgcolor='green'> ");
						// if (session == 0) {
						out.println("bgcolor='green'> ");
						// } else {
						// out.println("bgcolor='blue'> ");
						// }
					}

					out.println(str);
					out.println(tcc);
					out.println(rc);
				}
				out.println("</td>");
			}
			out.println("</tr>");
		}
		out.println("</table>");
		out.println();
	}

	/**
	 * Print timetable of all teacher with: course, class, time(day,slot), room.
	 */
	public void printTimeTable_AllTeacher(String fn, SingleSolution[] sol) {
		PrintWriter out = null;
		try {
			out = new PrintWriter(new File(fn));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for (int tc = 0; tc < DA.nbTeacher; tc++) {
			int[][] teacherSol = new int[DA.nbSlotsPerHalfDay * 2][DA.nbDays_20];
			for (int i = 0; i < DA.nbSlotsPerHalfDay * 2; i++) {
				for (int j = 0; j < DA.nbDays_20; j++) {
					teacherSol[i][j] = -1;
				}
			}
			Teacher t = DA.teachers[tc];
			int tIdx = DA.mTeacher2Index.get(t);
			// System.out.println(t.code);
			ArrayList<ClassCourse> L = DA.mTeacher2AssignedClassCourse.get(t);
			for (ClassCourse cc : L) {
				ClassFU cls = DA.mClassCourse2Class.get(cc);
				int clIdx = DA.mClass2Index.get(cls);
				SingleSolution ss = sol[clIdx];
				for (int d = 0; d < DA.nbDays_20; d++) {
					for (int sl = 0; sl < DA.nbSlotsPerHalfDay * 2; sl++) {
						if (ss.T[sl][d] == cc.ID) {
							teacherSol[sl][d] = cc.ID;
							// System.out.println("tc = "+t.code+", d = "+d+", sl = "+sl+", cc = "+cc.code+", cls = "+cls.code);
						}
					}
				}
			}
			try {

				printOneTeacherHTML(teacherSol, out, tIdx);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		out.close();
		System.out.println("Done printing timetable all teacher to " + fn);
	}

	/** Print timetable of one teacher. */
	public void printOneTeacherHTML(int[][] T, PrintWriter out, int tIdx) {
		out.println("Teacher = " + DA.teachers[tIdx].code);
		out.println("<table border = 1>");
		for (int i = 0; i < 2 * DA.nbSlotsPerHalfDay; i++) {
			out.print("<tr>");
			for (int j = 0; j < DA.nbDays_20; j++) {
				out.print("<td height = 40 width = 80 ");
				if (T[i][j] >= 0) {
					ClassCourse cc = DA.mID2ClassCourse_all.get(T[i][j]);
					ClassFU cls = DA.mClassCourse2Class.get(cc);
					Course c = DA.mClassCourse2Course.get(cc);
					String roomCode = "no room";
					if (DA.mClassCourse2AssignedRoom.get(cc) != null) {
						Room r = DA.mClassCourse2AssignedRoom.get(cc);
						roomCode = r.code;
					}

					int stt = cc.stt;
					if (stt == 3) {
						out.println("bgcolor='yellow'> ");
					} else {
						out.println("bgcolor='green'> ");
					}
					out.println(c.code);
					out.println(cls.code);
					out.println(roomCode);
				}
				out.println("</td>");
			}
			out.println("</tr>");
		}
		out.println("</table>");
		out.println();
	}

	public static void main(String[] args) {
		MainApp app = new MainApp();
		app.timetabling();
	}

}
