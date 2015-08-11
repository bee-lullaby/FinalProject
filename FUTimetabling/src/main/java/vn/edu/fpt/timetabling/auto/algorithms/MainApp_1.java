package vn.edu.fpt.timetabling.auto.algorithms;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;

import vn.edu.fpt.timetabling.auto.entities.ClassCourse;
import vn.edu.fpt.timetabling.auto.entities.ClassFU;
import vn.edu.fpt.timetabling.auto.entities.Course;
import vn.edu.fpt.timetabling.auto.entities.DataCenter;
import vn.edu.fpt.timetabling.auto.entities.Department;
import vn.edu.fpt.timetabling.auto.entities.Room;
import vn.edu.fpt.timetabling.auto.entities.SingleSolution;
import vn.edu.fpt.timetabling.auto.entities.Teacher;

public class MainApp_1 {

	DataCenter DA;
	TimeTableAllClass TA;
	AssignTeacher teacherAssigner1;
	AssignTeacher2 teacherAssigner2;
	AssignRoom roomAssigner;
	final static int LIMIT_NBSOLUTION_PERCLASS = TimeTableAllClass.LIMIT_NBSOLUTION_PERCLASS;

	public void timetabling_Temp2() {
		long startTime = System.currentTimeMillis();
		// =================================================================
		DA = new DataCenter();
		TA = new TimeTableAllClass();
		TA.DA = this.DA;
		teacherAssigner1 = new AssignTeacher();
		teacherAssigner1.DA = this.DA;
		teacherAssigner2 = new AssignTeacher2();
		teacherAssigner2.DA = this.DA;
		roomAssigner = new AssignRoom();
		roomAssigner.DA = this.DA;

		String fn_data_class = "datasm/data_class_sm.txt";
		String fn_data_course = "datasm/data_course_sm.txt";
		String fn_data_classcourse = "datasm/data_classcourse_sm.txt";
		String fn_data_room = "datasm/data_room_sm.txt";
		String fn_data_department = "datasm/data_department_sm.txt";
		String fn_data_building = "datasm/data_building_sm.txt";
		String fn_data_mergedCases = "datasm/data_mergedcases_sm.txt";
		String fn_classTimeTableSolutions_Temp2 = "datasm/data_solutionWarehouses_Temp2.dat";
		String fn_beingUsedTimeTable_Temp2 = "datasm/data_beingusedTT_Temp2.dat";
		String fn_conflictMatrix_Temp2 = "datasm/data_conflictMatrix_Temp2.txt";
		String fn_TTB = "datasm/optimizedTTB_sm.html";
		String fn_allTeacherTTB2 = "datasm/teacher_TTB.html";
		String fn_courseScoreMatrix_Temp2 = "datasm/data_scoreMatrix_Temp2.txt";

		TA.DA.loadData_Class_v2(fn_data_class);
		TA.DA.loadData_Department_v2(fn_data_department);
		TA.DA.loadData_Course_v2(fn_data_course);
		TA.DA.loadData_ClassCourse_v2(fn_data_classcourse);
		TA.DA.loadData_mergedCases(fn_data_mergedCases);
		TA.DA.loadData_Building_v2(fn_data_building);
		TA.DA.loadData_Room_v2(fn_data_room);
		if (!TA.DA.isDataValidForTemplate2()) {
			System.out.println("Not feasible to make timetable.");
			System.exit(1);
		}

//		DA.makeMustNotConflictClassCourseList();
		// ---------------------------------------------------------------------------
		// PHASE 1:
		long startTime_phase1 = System.currentTimeMillis();
		TA.makeSolutionWarehouse_Template2(fn_classTimeTableSolutions_Temp2);
		long estimatedTime_phase1 = System.currentTimeMillis() - startTime_phase1;

		// ---------------------------------------------------------------------------
		// PHASE 2:
		long startTime_phase2 = System.currentTimeMillis();
		TA.findOptimalTimeTable_v2_2(fn_classTimeTableSolutions_Temp2);
		TA.savebeingUsedTimeTable(fn_beingUsedTimeTable_Temp2);
		TA.writeMatrix2File(fn_conflictMatrix_Temp2, TA.beingUsedTimeTable);

		boolean ok = TA.isTimeTableCorrect(fn_conflictMatrix_Temp2);
		if (ok) {
			System.out.println("Timetable is correct.");
			// System.exit(1);
		} else {
			System.out.println("Timetable is not correct.");
			System.exit(1);
		}

		SingleSolution[] sol = TA.loadbeingUsedTimeTable(fn_beingUsedTimeTable_Temp2);
		TA.beingUsedTimeTable = sol;
		// TA.printTimeTableAllClass(fn_allClassTTB,sol);
		
		long estimatedTime_phase2 = System.currentTimeMillis() - startTime_phase2;
		
		// ---------------------------------------------------------------------------
		// PHASE 3:
		long startTime_phase3 = System.currentTimeMillis();
		teacherAssigner1.assignTeacherUsingScore(fn_conflictMatrix_Temp2, fn_beingUsedTimeTable_Temp2,
				fn_courseScoreMatrix_Temp2, TA.DA.classCourses);
		// teacherAssigner1.printAllTeacherHTML(fn_allTeacherTTB1, sol);
		// teacherAssigner2.assignTeacherUsingLS(fn_conflictMatrix_Temp2);
		// teacherAssigner2.completeMapClassCourse2AssignedTeacher();
		// teacherAssigner2.printAllTeacherHTML(fn_allTeacherTTB2, sol);

		long estimatedTime_phase3 = System.currentTimeMillis() - startTime_phase3;

		// ---------------------------------------------------------------------------
		// PHASE 4:
		long startTime_phase4 = System.currentTimeMillis();
		roomAssigner.assignRoomUsingFor(fn_beingUsedTimeTable_Temp2);
		System.out.println("\nRoom demand of every slot:");
		// TA.calRoomDemandEverySlot(sol);
		System.out.println();
		long estimatedTime_phase4 = System.currentTimeMillis() - startTime_phase4;
		
		// ==================================================================
		printTimeTableHTML_AllClass(fn_TTB);
//		PoiWriteExcelFile(TA, "timetable.xls", sol);
		System.out.println("Done printing timetable all class to " + fn_TTB);
		printTimeTable_AllTeacher(fn_allTeacherTTB2);
		System.out.println("Done printing timetable all teacher to " + fn_allTeacherTTB2);
		// ==================================================================
		long estimatedTime = System.currentTimeMillis() - startTime;
		System.out.println("\nTime-consuming phase 1: " + (double) estimatedTime_phase1 / 1000 + " secs");
		System.out.println("Time-consuming phase 2: " + (double) estimatedTime_phase2 / 1000 + " secs");
		System.out.println("Time-consuming phase 3: " + (double) estimatedTime_phase3 / 1000 + " secs");
		System.out.println("Time-consuming phase 4: " + (double) estimatedTime_phase4 / 1000 + " secs");
		System.out.println("Time-consuming all phases and loading data: " + (double) estimatedTime / 1000 + " secs");
	}

	public void timetabling_Temp1() {
		long startTime = System.currentTimeMillis();
		// =================================================================
		DA = new DataCenter();
		TA = new TimeTableAllClass();
		TA.DA = this.DA;
		teacherAssigner1 = new AssignTeacher();
		teacherAssigner1.DA = this.DA;
		teacherAssigner2 = new AssignTeacher2();
		teacherAssigner2.DA = this.DA;
		roomAssigner = new AssignRoom();
		roomAssigner.DA = this.DA;

		String fn_data_course_class = "data_course_class_merged.txt";
		String fn_data_teacher = "data_teacher.txt";
		String fn_data_room_building = "data_room_building.txt";
		String fn_data_mergedCases = "data_mergedCases.txt";
		String fn_classTimeTableSolutions_Temp2 = "data_solutionWarehouses_Temp2.dat";
		String fn_classTimeTableSolutions_Temp1 = "data_solutionWarehouses_Temp1.dat";
		String fn_conflictMatrix_Temp2 = "data_conflictMatrix_Temp2.txt";
		String fn_conflictMatrix_Temp1 = "data_conflictMatrix_Temp1.txt";
		String fn_courseScoreMatrix_Temp2 = "data_scoreMatrix_Temp2.txt";
		String fn_courseScoreMatrix_Temp1 = "data_scoreMatrix_Temp1.txt";
		String fn_beingUsedTimeTable_Temp2 = "data_beingusedTT_Temp2.dat";
		String fn_beingUsedTimeTable_Temp1 = "data_beingusedTT_Temp1.dat";
		String fn_allClassTTB = "TTB.html";
		String fn_allTeacherTTB1 = "teacherTimeTable1.html";
		String fn_allTeacherTTB2 = "teacherTimeTable2.html";
		String fn_allTeacherTTB = "teacherTimeTable.html";
		String fn_allClass_TimetableFull = "TTB_AllClass_Temp1.html";

		DA.loadData_Course_Class(fn_data_course_class); // for all phases
		DA.loadData_Teacher_UsingCode(fn_data_teacher); // for phase 3
		DA.loadData_Room_Building(fn_data_room_building); // for phase 4
		DA.loadData_mergedCases(fn_data_mergedCases); // for phase 1

		// ---------------------------------------------------------------------------
		// PHASE 1:
		long startTime_phase1 = System.currentTimeMillis();
		// ///TA.makeSolutionWarehouse_Template1(fn_classTimeTableSolutions_Temp1);
		long estimatedTime_phase1 = System.currentTimeMillis() - startTime_phase1;

		// ---------------------------------------------------------------------------
		// PHASE 2:
		long startTime_phase2 = System.currentTimeMillis();
		TA.findOptimalTimeTable_v2_3(fn_classTimeTableSolutions_Temp1);
		TA.savebeingUsedTimeTable(fn_beingUsedTimeTable_Temp1);
		TA.writeMatrix2File(fn_conflictMatrix_Temp1, TA.beingUsedTimeTable);

		boolean ok = TA.isTimeTableCorrect(fn_conflictMatrix_Temp1);
		if (ok) {
			System.out.println("Timetable is correct.");
			System.exit(1);
		} else {
			System.out.println("Timetable is not correct.");
			System.exit(1);
		}

		SingleSolution[] sol = TA.loadbeingUsedTimeTable(fn_beingUsedTimeTable_Temp1);
		TA.beingUsedTimeTable = sol;
		// TA.printTimeTableAllClass(fn_allClassTTB,sol);

		long estimatedTime_phase2 = System.currentTimeMillis() - startTime_phase2;

		// ---------------------------------------------------------------------------
		// PHASE 3:
		long startTime_phase3 = System.currentTimeMillis();
		teacherAssigner1.assignTeacherUsingScore(fn_conflictMatrix_Temp1, fn_beingUsedTimeTable_Temp1,
				fn_courseScoreMatrix_Temp1, TA.DA.classCourses);
		// teacherAssigner1.printAllTeacherHTML(fn_allTeacherTTB1, sol);
		// teacherAssigner2.assignTeacherUsingLS(fn_conflictMatrix_Temp1);
		// teacherAssigner2.completeMapClassCourse2AssignedTeacher();
		// teacherAssigner2.printAllTeacherHTML(fn_allTeacherTTB2, sol);

		long estimatedTime_phase3 = System.currentTimeMillis() - startTime_phase3;

		// ---------------------------------------------------------------------------
		// PHASE 4:
		long startTime_phase4 = System.currentTimeMillis();
		roomAssigner.assignRoomUsingFor(fn_beingUsedTimeTable_Temp1);
		System.out.println("\nRoom demand of every slot:");
		// TA.calRoomDemandEverySlot(sol);
		System.out.println();
		long estimatedTime_phase4 = System.currentTimeMillis() - startTime_phase4;

		// ==================================================================
		// this.TA.beingUsedTimeTable =
		// TA.loadbeingUsedTimeTable(fn_beingUsedTimeTable_Temp1);
		printTimeTableHTML_AllClass(fn_allClass_TimetableFull);
		System.out.println("Done printing timetable all class to " + fn_allClass_TimetableFull);
		printTimeTable_AllTeacher(fn_allTeacherTTB1);
		System.out.println("Done printing timetable all teacher to " + fn_allTeacherTTB1);
		// ==================================================================
		long estimatedTime = System.currentTimeMillis() - startTime;
		System.out.println("\nTime-consuming phase 1: " + (double) estimatedTime_phase1 / 1000 + " secs");
		System.out.println("Time-consuming phase 2: " + (double) estimatedTime_phase2 / 1000 + " secs");
		System.out.println("Time-consuming phase 3: " + (double) estimatedTime_phase3 / 1000 + " secs");
		System.out.println("Time-consuming phase 4: " + (double) estimatedTime_phase4 / 1000 + " secs");
		System.out.println("Time-consuming all phases and loading data: " + (double) estimatedTime / 1000 + " secs");

	}

	public void timetabling_Temp1_full() {
		long startTime = System.currentTimeMillis();
		// =================================================================
		DA = new DataCenter();
		TA = new TimeTableAllClass();
		TA.DA = this.DA;
		teacherAssigner1 = new AssignTeacher();
		teacherAssigner1.DA = this.DA;
		teacherAssigner2 = new AssignTeacher2();
		teacherAssigner2.DA = this.DA;
		roomAssigner = new AssignRoom();
		roomAssigner.DA = this.DA;

		/*
		 * String fn_data_course_class = "data_course_class_merged.txt"; String
		 * fn_data_teacher = "data_teacher.txt"; String fn_data_room_building =
		 * "data_room_building.txt"; String fn_data_mergedCases =
		 * "data_mergedCases.txt"; String fn_classTimeTableSolutions_Temp2 =
		 * "data_solutionWarehouses_Temp2.dat"; String
		 * fn_classTimeTableSolutions_Temp1 =
		 * "data_solutionWarehouses_Temp1.dat"; String fn_conflictMatrix_Temp2 =
		 * "data_conflictMatrix_Temp2.txt"; String fn_conflictMatrix_Temp1 =
		 * "data_conflictMatrix_Temp1.txt"; String fn_courseScoreMatrix_Temp2 =
		 * "data_scoreMatrix_Temp2.txt"; String fn_courseScoreMatrix_Temp1 =
		 * "data_scoreMatrix_Temp1.txt"; String fn_beingUsedTimeTable_Temp2 =
		 * "data_beingusedTT_Temp2.dat"; String fn_beingUsedTimeTable_Temp1 =
		 * "data_beingusedTT_Temp1.dat"; String fn_allClassTTB = "TTB.html";
		 * String fn_allTeacherTTB1 = "teacherTimeTable1.html"; String
		 * fn_allTeacherTTB2 = "teacherTimeTable2.html"; String fn_allTeacherTTB
		 * = "teacherTimeTable.html"; String fn_allClass_TimetableFull =
		 * "TTB_AllClass_Temp1.html";
		 * 
		 * DA.loadData_Course_Class(fn_data_course_class); // for all phases
		 * DA.loadData_Teacher_UsingCode(fn_data_teacher); // for phase 3
		 * DA.loadData_Room_Building(fn_data_room_building); // for phase 4
		 * DA.loadData_mergedCases(fn_data_mergedCases); // for phase 1
		 */
		String fn_output = "class_course_de_session_room_block_slot.txt";
		String fn_data_class = "data_class_v3.txt";
		String fn_data_department = "data_department_v3.txt";
		String fn_data_course = "data_course_v3.txt";
		String fn_data_classcourse = "data_classcourse_v3.txt";
		String fn_data_mergedCases = "data_mergedCases.txt";
		String fn_data_building = "data_building_v2.txt";
		String fn_data_room = "data_room_v2.txt";
		// String fn_classTimeTableSolutions_Temp2 =
		// "data_solutionWarehouses_Temp2.dat";
		String fn_classTimeTableSolutions_Temp1 = "data_solutionWarehouses_Temp1_full.dat";
		// String fn_conflictMatrix_Temp2 = "data_conflictMatrix_Temp2.txt";
		String fn_conflictMatrix_Temp1 = "data_conflictMatrix_Temp1.txt";
		// String fn_courseScoreMatrix_Temp2 = "data_scoreMatrix_Temp2.txt";
		String fn_courseScoreMatrix_Temp1 = "data_scoreMatrix_Temp1.txt";
		// String fn_beingUsedTimeTable_Temp2 = "data_beingusedTT_Temp2.dat";
		String fn_beingUsedTimeTable_Temp1 = "data_beingusedTT_Temp1_full.dat";
		String fn_allClassTTB = "TTB.html";
		String fn_allTeacherTTB1 = "teacherTimeTable1.html";
		// String fn_allTeacherTTB2 = "teacherTimeTable2.html";
		// String fn_allTeacherTTB = "teacherTimeTable.html";
		String fn_allClass_TimetableFull = "TTB_AllClass_Temp1.html";

		DA.loadData_Class_v2(fn_data_class);
		DA.loadData_Department_v2(fn_data_department);
		DA.loadData_Course_v2(fn_data_course);
		DA.loadData_ClassCourse_v2(fn_data_classcourse);
		DA.loadData_mergedCases(fn_data_mergedCases);
		DA.loadData_Building_v2(fn_data_building);
		DA.loadData_Room_v2(fn_data_room);

		// ---------------------------------------------------------------------------
		// PHASE 1:
		long startTime_phase1 = System.currentTimeMillis();
		// ///TA.makeSolutionWarehouse_Template1(fn_classTimeTableSolutions_Temp1);
		long estimatedTime_phase1 = System.currentTimeMillis() - startTime_phase1;

		// ---------------------------------------------------------------------------
		// PHASE 2:
		long startTime_phase2 = System.currentTimeMillis();
		TA.findOptimalTimeTable_v2_3(fn_classTimeTableSolutions_Temp1);
		TA.savebeingUsedTimeTable(fn_beingUsedTimeTable_Temp1);
		TA.writeMatrix2File(fn_conflictMatrix_Temp1, TA.beingUsedTimeTable);

		boolean ok = TA.isTimeTableCorrect(fn_conflictMatrix_Temp1);
		if (ok) {
			System.out.println("Timetable is correct.");
			System.exit(1);
		} else {
			System.out.println("Timetable is not correct.");
			System.exit(1);
		}

		SingleSolution[] sol = TA.loadbeingUsedTimeTable(fn_beingUsedTimeTable_Temp1);
		TA.beingUsedTimeTable = sol;
		TA.printTimeTableAllClass(fn_allClassTTB, sol);

		long estimatedTime_phase2 = System.currentTimeMillis() - startTime_phase2;

		// ---------------------------------------------------------------------------
		// PHASE 3:
		long startTime_phase3 = System.currentTimeMillis();
		/*
		 * teacherAssigner1.assignTeacherUsingScore(fn_conflictMatrix_Temp1,
		 * fn_beingUsedTimeTable_Temp1, fn_courseScoreMatrix_Temp1,
		 * TA.DA.classCourses);
		 */
		// teacherAssigner1.printAllTeacherHTML(fn_allTeacherTTB1, sol);
		// teacherAssigner2.assignTeacherUsingLS(fn_conflictMatrix_Temp1);
		// teacherAssigner2.completeMapClassCourse2AssignedTeacher();
		// teacherAssigner2.printAllTeacherHTML(fn_allTeacherTTB2, sol);

		long estimatedTime_phase3 = System.currentTimeMillis() - startTime_phase3;

		// ---------------------------------------------------------------------------
		// PHASE 4:
		long startTime_phase4 = System.currentTimeMillis();
		roomAssigner.assignRoomUsingFor(fn_beingUsedTimeTable_Temp1);
		System.out.println("\nRoom demand of every slot:");
		// TA.calRoomDemandEverySlot(sol);
		System.out.println();
		long estimatedTime_phase4 = System.currentTimeMillis() - startTime_phase4;

		// ==================================================================
		// this.TA.beingUsedTimeTable =
		// TA.loadbeingUsedTimeTable(fn_beingUsedTimeTable_Temp1);
		/*
		 * printTimeTableHTML_AllClass(fn_allClass_TimetableFull);
		 * System.out.println("Done printing timetable all class to " +
		 * fn_allClass_TimetableFull);
		 * printTimeTable_AllTeacher(fn_allTeacherTTB1); System.out.println(
		 * "Done printing timetable all teacher to " + fn_allTeacherTTB1);
		 */
		// ==================================================================
		
		long estimatedTime = System.currentTimeMillis() - startTime;
		System.out.println("\nTime-consuming phase 1: " + (double) estimatedTime_phase1 / 1000 + " secs");
		System.out.println("Time-consuming phase 2: " + (double) estimatedTime_phase2 / 1000 + " secs");
		System.out.println("Time-consuming phase 3: " + (double) estimatedTime_phase3 / 1000 + " secs");
		System.out.println("Time-consuming phase 4: " + (double) estimatedTime_phase4 / 1000 + " secs");
		System.out.println("Time-consuming all phases and loading data: " + (double) estimatedTime / 1000 + " secs");

		this.printResultListFormat(fn_output, sol);

	}

	/**
	 * Print timetable of all classes with: course, time(day,slot), room,
	 * professor.
	 */
	

	public void printTimeTableHTML_AllClass(String fn) {
		try {
			File f = new File(fn);
			PrintWriter out = new PrintWriter(f);
			SingleSolution[] sol = this.TA.beingUsedTimeTable;
			for (int icl = 0; icl < DA.nbClass; icl++) {
				printTimeTableHTML_OneClass(sol[icl], out, icl);
			}

			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** Print timetable of one class. */
	public void printTimeTableHTML_OneClass(SingleSolution sol, PrintWriter out, int clIdx) {
		out.println("solution position = " + sol.position);
		out.println(", class = " + DA.classes[clIdx].code);
		out.println("<table border = 1>");
		for (int i = 0; i < 2 * DA.nbSlotsPerHalfDay; i++) {
			out.print("<tr>");
			for (int j = 0; j < DA.nbDays_20; j++) {
				out.print("<td height = 80 width = 80 ");
				String str = "";
				String tcc = "no teacher";
				String rc = "no room";
				if (sol.T[i][j] >= 0) {
					ClassCourse cc = DA.mID2ClassCourse.get(sol.T[i][j]);
					Course c = DA.mClassCourse2Course.get(cc);
					if (DA.mClassCourse2AssignedTeacher == null) {
						System.out.println("mClassCourse2AssignedTeacher is null");
						System.exit(1);
					}
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
	public void printTimeTable_AllTeacher(String fn) {
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
			// System.out.println();
			ArrayList<ClassCourse> L = DA.mTeacher2AssignedClassCourse.get(t);
			for (ClassCourse cc : L) {
				ClassFU cls = DA.mClassCourse2Class.get(cc);
				int clIdx = DA.mClass2Index.get(cls);
				SingleSolution ss = this.TA.beingUsedTimeTable[clIdx];
				for (int d = 0; d < DA.nbDays_20; d++) {
					for (int sl = 0; sl < DA.nbSlotsPerHalfDay * 2; sl++) {
						if (ss.T[sl][d] == cc.ID) {
							teacherSol[sl][d] = cc.ID;
							// System.out.println("tc = "+t.code+", d = "+d+",
							// sl = "+sl+", cc = "+cc.code+", cls = "+cls.code);
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
	}

	/** Print timetable of one teacher. */
	public void printOneTeacherHTML(int[][] T, PrintWriter out, int tIdx) {
		out.println("Teacher = " + DA.teachers[tIdx].code);
		out.println("<table border = 1>");
		for (int i = 0; i < 2 * DA.nbSlotsPerHalfDay; i++) {
			out.print("<tr>");
			for (int j = 0; j < DA.nbDays_20; j++) {
				out.print("<td height = 80 width = 80 ");
				if (T[i][j] >= 0) {
					ClassCourse cc = DA.mID2ClassCourse.get(T[i][j]);
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

	public void printResultListFormat(String fn, SingleSolution[] sol) {
		System.out.println("reached");
		TA.findBlockSlotSessionOfClassCourse(sol);

		// =========

		// System.out.println("reached");
		// ==========
		try {
			System.out.println("reached");
			File file = new File(fn);
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);

			int idx = -1;
			for (ClassCourse cc : DA.classCourses) {
				// System.out.println("reached");
				idx++;
				ClassFU cls = DA.mClassCourse2Class.get(cc);
				Course c = DA.mClassCourse2Course.get(cc);
				Room r = DA.mClassCourse2AssignedRoom.get(cc);
				String roomCode = new String();
				if (r == null) {
					roomCode = "noroom";
				} else {
					roomCode = r.code;
				}
				Department d = DA.mClassCourse2Department.get(cc);
				int block = DA.aClassCourse2Block[idx];
				int slot = DA.aClassCourse2Slot[idx] + 1;
				int session = DA.aClassCourse2Session[idx];
				String ss = new String();
				if (session == 0) {
					ss = "Sang";
				} else {
					ss = "Chieu";
				}

				// classcourse@class@course@Deparrtment@section@room@slot(block1)@slot(block2)
				if (cc.stt == 3) {
					bw.write(cls.code + c.code + " @ " + cls.code + " @ " + c.code + " @ " + d.code + " @ " + ss + " @ "
							+ roomCode + " @ " + slot + " @ " + slot + "@\n");
					/*
					 * System.out.println(cls.code+c.code +" @ "+cls.code +" @ "
					 * +c.code +" @ "+d.code +" @ "+ss +" @ "+roomCode +" @ "
					 * +slot+"("+(TimeTableAllClass.BLOCK_1+1)+")" +" @ "
					 * +slot+"("+(TimeTableAllClass.BLOCK_2+1)+")@" );
					 */
				} else {
					if (block == TimeTableAllClass.BLOCK_1) {
						bw.write(cls.code + c.code + " @ " + cls.code + " @ " + c.code + " @ " + d.code + " @ " + ss
								+ " @ " + roomCode + " @ " + slot + " @  @\n");
						/*
						 * System.out.println(cls.code+c.code +" @ "+cls.code +
						 * " @ "+c.code +" @ "+d.code +" @ "+ss +" @ "+roomCode
						 * +" @ "+slot+"("+(TimeTableAllClass.BLOCK_1+1)+")"
						 * +"@@" );
						 */
					} else {
						bw.write(cls.code + c.code + " @ " + cls.code + " @ " + c.code + " @ " + d.code + " @ " + ss
								+ " @ " + roomCode + " @  @ " + slot + " @\n");
						/*
						 * System.out.println(cls.code+c.code +" @ "+cls.code +
						 * " @ "+c.code +" @ "+d.code +" @ "+ss +" @ "+roomCode
						 * +" @ "+slot+"("+(TimeTableAllClass.BLOCK_1+1)+")@@"
						 * );
						 */
					}
					//
				}
			}
			bw.write("-1\n");
			bw.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void TTB_temp2() {
		long startTime = System.currentTimeMillis();
		// =================================================================
		DA = new DataCenter();
		TA = new TimeTableAllClass();
		TA.DA = this.DA;
		teacherAssigner1 = new AssignTeacher();
		teacherAssigner1.DA = this.DA;
		teacherAssigner2 = new AssignTeacher2();
		teacherAssigner2.DA = this.DA;
		roomAssigner = new AssignRoom();
		roomAssigner.DA = this.DA;

		/*
		 * String fn_data_course_class = "data_course_class_merged.txt"; String
		 * fn_data_teacher = "data_teacher.txt"; String fn_data_room_building =
		 * "data_room_building.txt"; String fn_data_mergedCases =
		 * "data_mergedCases.txt"; String fn_classTimeTableSolutions_Temp2 =
		 * "data_solutionWarehouses_Temp2.dat"; String
		 * fn_classTimeTableSolutions_Temp1 =
		 * "data_solutionWarehouses_Temp1.dat"; String fn_conflictMatrix_Temp2 =
		 * "data_conflictMatrix_Temp2.txt"; String fn_conflictMatrix_Temp1 =
		 * "data_conflictMatrix_Temp1.txt"; String fn_courseScoreMatrix_Temp2 =
		 * "data_scoreMatrix_Temp2.txt"; String fn_courseScoreMatrix_Temp1 =
		 * "data_scoreMatrix_Temp1.txt"; String fn_beingUsedTimeTable_Temp2 =
		 * "data_beingusedTT_Temp2.dat"; String fn_beingUsedTimeTable_Temp1 =
		 * "data_beingusedTT_Temp1.dat"; String fn_allClassTTB = "TTB.html";
		 * String fn_allTeacherTTB1 = "teacherTimeTable1.html"; String
		 * fn_allTeacherTTB2 = "teacherTimeTable2.html"; String
		 * fn_allTeacherTTB_Temp2 = "teacherTimeTable_Temp2.html"; String
		 * fn_allClass_TimetableFull_Temp2 = "TTB_AllClass_Temp2.html";
		 * 
		 * DA.loadData_Course_Class(fn_data_course_class); // for all phases
		 * DA.loadData_Teacher_UsingCode(fn_data_teacher); // for phase 3
		 * DA.loadData_Room_Building(fn_data_room_building); // for phase 4
		 * DA.loadData_mergedCases(fn_data_mergedCases); // for phase 1
		 */

		// ----------------------------------------------------------------------------
		String fn_data_class = "datasm/data_class_sm.txt";
		String fn_data_course = "datasm/data_course_sm.txt";
		String fn_data_classcourse = "datasm/data_classcourse_sm.txt";
		String fn_data_room = "datasm/data_room_sm.txt";
		String fn_data_department = "datasm/data_department_sm.txt";
		String fn_data_building = "datasm/data_building_sm.txt";
		String fn_data_mergedCases = "datasm/data_mergedcases_sm.txt";
		String fn_classTimeTableSolutions_Temp2 = "data_solutionWarehouses_Temp2.dat";
		String fn_beingUsedTimeTable_Temp2 = "data_beingusedTT_Temp2.dat";
		String fn_conflictMatrix_Temp2 = "data_conflictMatrix_Temp2.txt";
		String fn_courseScoreMatrix_Temp2 = "data_scoreMatrix_Temp2.txt";

		DA.loadData_Class_v2(fn_data_class);
		DA.loadData_Department_v2(fn_data_department);
		DA.loadData_Course_v2(fn_data_course);
		DA.loadData_ClassCourse_v2(fn_data_classcourse);
		DA.loadData_mergedCases(fn_data_mergedCases);
		DA.loadData_Building_v2(fn_data_building);
		DA.loadData_Room_v2(fn_data_room);
		// ---------------------------------------------------------------------------
		// PHASE 1:
		long startTime_phase1 = System.currentTimeMillis();
		// //TA.makeSolutionWarehouse_Template2(fn_classTimeTableSolutions_Temp2);
		long estimatedTime_phase1 = System.currentTimeMillis() - startTime_phase1;

		// ---------------------------------------------------------------------------
		// PHASE 2:
		long startTime_phase2 = System.currentTimeMillis();
		TA.findOptimalTimeTable_v2_2(fn_classTimeTableSolutions_Temp2);
		TA.savebeingUsedTimeTable(fn_beingUsedTimeTable_Temp2);
		TA.writeMatrix2File(fn_conflictMatrix_Temp2, TA.beingUsedTimeTable);

		boolean ok = TA.isTimeTableCorrect(fn_conflictMatrix_Temp2);
		if (ok) {
			System.out.println("Timetable is correct.");
			// System.exit(1);
		} else {
			System.out.println("Timetable is not correct.");
			System.exit(1);
		}

		SingleSolution[] sol = TA.loadbeingUsedTimeTable(fn_beingUsedTimeTable_Temp2);
		TA.beingUsedTimeTable = sol;
		// TA.printTimeTableAllClass(fn_allClassTTB,sol);

		long estimatedTime_phase2 = System.currentTimeMillis() - startTime_phase2;

		// ---------------------------------------------------------------------------
		// PHASE 3:
		long startTime_phase3 = System.currentTimeMillis();
		/*
		 * teacherAssigner1.assignTeacherUsingScore(fn_conflictMatrix_Temp2,
		 * fn_beingUsedTimeTable_Temp2, fn_courseScoreMatrix_Temp2,
		 * TA.DA.classCourses);
		 */
		// teacherAssigner1.printAllTeacherHTML(fn_allTeacherTTB1, sol);
		// teacherAssigner2.assignTeacherUsingLS(fn_conflictMatrix_Temp2);
		// teacherAssigner2.completeMapClassCourse2AssignedTeacher();
		// teacherAssigner2.printAllTeacherHTML(fn_allTeacherTTB2, sol);

		long estimatedTime_phase3 = System.currentTimeMillis() - startTime_phase3;

		// ---------------------------------------------------------------------------
		// PHASE 4:
		long startTime_phase4 = System.currentTimeMillis();
		/*
		 * roomAssigner.assignRoomUsingFor(fn_beingUsedTimeTable_Temp2);
		 * System.out.println("\nRoom demand of every slot:"); //
		 * TA.calRoomDemandEverySlot(sol); System.out.println();
		 */
		long estimatedTime_phase4 = System.currentTimeMillis() - startTime_phase4;

		/*
		 * // ==================================================================
		 * printTimeTableHTML_AllClass(fn_allClass_TimetableFull_Temp2);
		 * System.out.println("Done printing timetable all class to " +
		 * fn_allClass_TimetableFull_Temp2);
		 * printTimeTable_AllTeacher(fn_allTeacherTTB2); System.out.println(
		 * "Done printing timetable all teacher to " + fn_allTeacherTTB2);
		 */
		// ==================================================================
		long estimatedTime = System.currentTimeMillis() - startTime;
		System.out.println("\nTime-consuming phase 1: " + (double) estimatedTime_phase1 / 1000 + " secs");
		System.out.println("Time-consuming phase 2: " + (double) estimatedTime_phase2 / 1000 + " secs");
		System.out.println("Time-consuming phase 3: " + (double) estimatedTime_phase3 / 1000 + " secs");
		System.out.println("Time-consuming phase 4: " + (double) estimatedTime_phase4 / 1000 + " secs");
		System.out.println("Time-consuming all phases and loading data: " + (double) estimatedTime / 1000 + " secs");
	}

	public static void main(String[] args) {
		MainApp_1 app = new MainApp_1();
		app.timetabling_Temp2();
		// app.timetabling_Temp1_full();
	}

}
