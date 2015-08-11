package vn.edu.fpt.timetabling.auto.algorithms;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;

import com.mysql.fabric.xmlrpc.base.Array;

import choco.cp.model.CPModel;
import choco.cp.solver.CPSolver;
import localsearch.constraints.basic.Implicate;
import localsearch.constraints.basic.IsEqual;
import localsearch.constraints.basic.NotEqual;
import localsearch.model.ConstraintSystem;
import localsearch.model.IConstraint;
import localsearch.model.LocalSearchManager;
import localsearch.model.VarIntLS;
import vn.edu.fpt.timetabling.auto.entities.ClassCourse;
import vn.edu.fpt.timetabling.auto.entities.ClassFU;
import vn.edu.fpt.timetabling.auto.entities.CoupleCoursePlace;
import vn.edu.fpt.timetabling.auto.entities.Course;
import vn.edu.fpt.timetabling.auto.entities.DataCenter;
import vn.edu.fpt.timetabling.auto.entities.DataOneClass;
import vn.edu.fpt.timetabling.auto.entities.DaySlot;
import vn.edu.fpt.timetabling.auto.entities.Department;
import vn.edu.fpt.timetabling.auto.entities.Pair_ClassCourseClass;
import vn.edu.fpt.timetabling.auto.entities.Place;
import vn.edu.fpt.timetabling.auto.entities.Room;
import vn.edu.fpt.timetabling.auto.entities.SingleSolution;
import vn.edu.fpt.timetabling.auto.entities.Teacher;

public class TimeTableAllClass {
	public DataCenter DA;
	public TimeTableOneClass_SM[] timeTable_WareHouse;
	public SingleSolution[] beingUsedTimeTable;
	public int[] backUpTimeTable;
	int[] demand_OfACourse;
	int[] demand_Department;
	int[] supply_Department;
	int totalDemand;
	int totalSupply;
	int[] supplyTeacher4Optimization;
	int[] supplyTeacherFromData;
	int[] marginDemand_OfACourse;
	int totalNb_OfViolatedCourse;
	int[] nbOfViolatedCourse_OfAClass;
	int[] marginDemand_OfAClass;
	
	final static int SLOT_1 = 0;
	final static int SLOT_2 = 1;
	final static int SLOT_3 = 2;
	final static int SLOT_4 = 3;
	final static int SLOT_5 = 4;
	final static int SLOT_6 = 5;
	final static int EMPTY_SLOT = -1;
	final static int MORNING = 0;
	final static int AFTERNOON = 1;
	final static int BLOCK_1 = 0;
	final static int BLOCK_2 = 1;

	// the quality of outcome and time-consumming depend on this limit
	// should be 768 if template2
	final static int LIMIT_NBSOLUTION_PERCLASS = 2;
	final static int LIMIT_NBSOLUTION_PERCLASS_WHENOPTIMIZE = 768;
	final static int LIMIT_NBCLASS_INBANNEDLIST = 8;
	final static int LIMIT_ROOMALLOWANCE = 1;
	final static int LIMIT_SESSIONALLOWANCE = 1;
	final static int LIMIT_DEMANDTEACHER_PERCOURSE = 2;
	final static int NB_ITERATION = 1500;
	final static int MAX_NBCOURSEPERSEMESTER = 8;
	////////////////////////////////////////////////////////////////////////////
	final static int NB_FRAGMENT_PERCOURSE = DataCenter.NB_FRAGMENT_PERCOURSE_FULLMODEL;
	LocalSearchManager ls;
	ConstraintSystem S;
	VarIntLS[] x_day;
	VarIntLS[] x_slot;
	public HashMap<ClassCourse, ArrayList<VarIntLS>> mClassCourse2x_dayList;
	public HashMap<ClassCourse, ArrayList<VarIntLS>> mClassCourse2x_slotList;
	public HashMap<VarIntLS, ClassCourse> mXDay2ClassCourse;
	public HashMap<VarIntLS, ClassCourse> mXSlot2ClassCourse;
	public HashMap<VarIntLS, Integer> mXDay2Index;
	public HashMap<VarIntLS, Integer> mXSlot2Index;
	public HashMap<ClassCourse,Integer> mClassCourse2nbStudent;

	/**
	 * State model for all classcourses of all classes.
	 * 
	 */
	public void stateModelAllClass(String fn) {
		/*
		 * DA = new DataCenter();
		 * DA.loadData_Course_Class("data_all_sm_merged.txt");
		 * DA.loadData_mergedCases("data_mergedCases_sample.txt");
		 */
		DA.makeMustNotConflictClassCourseList();
		DA.makeMustNotConflictMatrix();
		genData_Fragment(fn);
		ls = new LocalSearchManager();
		int nbFC = DA.nbClassCourse * NB_FRAGMENT_PERCOURSE;

		x_day = new VarIntLS[DA.nbClassCourse * NB_FRAGMENT_PERCOURSE];
		x_slot = new VarIntLS[DA.nbClassCourse * NB_FRAGMENT_PERCOURSE];

		loadData_FragmentANDInitializeVariables(fn);
		S = new ConstraintSystem(ls);
		// ---------------------------------------------------------------------
		// constraints:
		// 1. nhung mon thuoc cung 1 lop se ko conflict voi nhau: cái này đã
		// được đảm bảo ở phase 1: OK

		// 2. lớp A(lớp chủ) có một số môn ghép với lớp B(lớp khách) thì các môn
		// của lớp B phải
		// được lên lịch sao cho không trùng với các môn còn lại của lớp A.
		for (int fci = 0; fci < nbFC - 1; fci++) {
			for (int fcj = fci + 1; fcj < nbFC; fcj++) {
				ClassCourse cc1 = mXDay2ClassCourse.get(x_day[fci]);
				ClassCourse cc2 = mXDay2ClassCourse.get(x_day[fcj]);
				ClassFU cls1 = DA.mClassCourse2Class.get(cc1);
				ClassFU cls2 = DA.mClassCourse2Class.get(cc2);
				int cc1Idx = DA.mClassCourse2Index.get(cc1);
				int cc2Idx = DA.mClassCourse2Index.get(cc2);
				// if (DA.mustNotConflictMatrix[cc1Idx][cc2Idx] == 1 || cls1 ==
				// cls2) {
				if (DA.mustNotConflictMatrix[cc1Idx][cc2Idx] == 1) {
					IConstraint ic1 = new IsEqual(x_day[fci], x_day[fcj]);
					IConstraint ic2 = new NotEqual(x_slot[fci], x_slot[fcj]);
					S.post(new Implicate(ic1, ic2));
				}
			}
		}

		// ---------------------------------------------------------------------
		ls.close();

		System.out.println("\nViolation after stating model: " + S.violations());
		// System.out.println("Class "+DA.classes[26].code+", violation =
		// "+calViolationOfAClass(26));
	}

	/**
	 * This model is for post improvement only. Don't use it in finding
	 * optimized timetable.
	 */
	public void stateModelAllClass2(String fn) {
		/*
		 * DA = new DataCenter();
		 * DA.loadData_Course_Class("data_all_sm_merged.txt");
		 * DA.loadData_mergedCases("data_mergedCases_sample.txt");
		 */
		DA.makeMustNotConflictClassCourseList();
		DA.makeMustNotConflictMatrix();
		genData_Fragment(fn);
		ls = new LocalSearchManager();
		int nbFC = DA.nbClassCourse * NB_FRAGMENT_PERCOURSE;

		x_day = new VarIntLS[DA.nbClassCourse * NB_FRAGMENT_PERCOURSE];
		x_slot = new VarIntLS[DA.nbClassCourse * NB_FRAGMENT_PERCOURSE];

		loadData_FragmentANDInitializeVariables(fn);
		S = new ConstraintSystem(ls);
		// ---------------------------------------------------------------------
		// constraints:
		// 1. nhung mon thuoc cung 1 lop se ko conflict voi nhau: cái này đã
		// được đảm bảo ở phase 1: OK

		// 2. lớp A(lớp chủ) có một số môn ghép với lớp B(lớp khách) thì các môn
		// của lớp B phải
		// được lên lịch sao cho không trùng với các môn còn lại của lớp A.
		for (int fci = 0; fci < nbFC - 1; fci++) {
			for (int fcj = fci + 1; fcj < nbFC; fcj++) {
				ClassCourse cc1 = mXDay2ClassCourse.get(x_day[fci]);
				ClassCourse cc2 = mXDay2ClassCourse.get(x_day[fcj]);
				ClassFU cls1 = DA.mClassCourse2Class.get(cc1);
				ClassFU cls2 = DA.mClassCourse2Class.get(cc2);
				int cc1Idx = DA.mClassCourse2Index.get(cc1);
				int cc2Idx = DA.mClassCourse2Index.get(cc2);
				if (DA.mustNotConflictMatrix[cc1Idx][cc2Idx] == 1 || cls1 == cls2) {
					// if (DA.mustNotConflictMatrix[cc1Idx][cc2Idx] == 1) {
					IConstraint ic1 = new IsEqual(x_day[fci], x_day[fcj]);
					IConstraint ic2 = new NotEqual(x_slot[fci], x_slot[fcj]);
					S.post(new Implicate(ic1, ic2));
				}
			}
		}

		// ---------------------------------------------------------------------
		ls.close();

		System.out.println("\nViolation after stating model: " + S.violations());
		// System.out.println("Class "+DA.classes[26].code+", violation =
		// "+calViolationOfAClass(26));
	}

	/** Generate fragment courses and write to file. File format: fc-cc */
	public void genData_Fragment(String fn) {
		File file = new File(fn);
		try {
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			// bw.write("#nbFragment\n");
			int nbFragmentCourse = DA.nbClassCourse * NB_FRAGMENT_PERCOURSE;

			// bw.write(DA.nbClassCourse*NBFRAGMENTPERCOURSE);
			bw.write("#fragmentIndex #classCourseID\n");

			int ccIdx = -1;
			int ccID = -1;
			for (int fc_i = 0; fc_i < nbFragmentCourse; fc_i++) {
				if (fc_i % NB_FRAGMENT_PERCOURSE == 0) {
					ccIdx++;
					ClassCourse cc = DA.classCourses[ccIdx];
					ccID = cc.ID;
				}
				bw.write(fc_i + " " + ccID + "\n");
			}
			bw.write("-1\n");
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Load fragment courses from file. Build map<ClassCourse,x_day/x_slot>,
	 * map<ClassCourse,ArrayList<x_day/x_slot>>. Initialise x_day and x_slot.
	 */
	public void loadData_FragmentANDInitializeVariables(String fn) {
		int nbFC = DA.nbClassCourse * NB_FRAGMENT_PERCOURSE; // neu fc per
																// course ko
																// bang nhau thi
																// problem
		/*
		 * ls = new LocalSearchManager(); x_day = new VarIntLS[nbFC]; x_slot =
		 * new VarIntLS[nbFC];
		 */
		mClassCourse2x_dayList = new HashMap<>();
		mClassCourse2x_slotList = new HashMap<>();
		mXDay2ClassCourse = new HashMap<>();
		mXSlot2ClassCourse = new HashMap<>();
		mXDay2Index = new HashMap<>();
		mXSlot2Index = new HashMap<>();

		for (int i = 0; i < DA.nbClassCourse; i++) {
			mClassCourse2x_dayList.put(DA.classCourses[i], new ArrayList<>());
			mClassCourse2x_slotList.put(DA.classCourses[i], new ArrayList<>());
		}
		File f = new File(fn);
		try {
			Scanner in = new Scanner(f);
			String line = in.nextLine();
			// System.out.println(line);
			int idx = -1;
			while (true) {
				idx++;
				int fcIdx = in.nextInt();
				if (fcIdx == -1 || idx >= nbFC) {
					break;
				}
				int ccID = in.nextInt();

				if (fcIdx < nbFC && DA.mID2ClassCourse.get(ccID) != null) {
					// System.out.println("fcIdx = "+fcIdx+", ccID = "+ccID);
					ClassCourse cc = DA.mID2ClassCourse.get(ccID);
					VarIntLS day = new VarIntLS(ls, 0, DA.nbDays_20 - 1);
					VarIntLS slot = new VarIntLS(ls, 0, DA.nbSlotsPerHalfDay * 2 - 1);
					x_day[fcIdx] = day;
					x_slot[fcIdx] = slot;
					ArrayList<VarIntLS> xdayList = mClassCourse2x_dayList.get(cc);
					ArrayList<VarIntLS> xslotList = mClassCourse2x_slotList.get(cc);
					xdayList.add(x_day[fcIdx]);
					xslotList.add(x_slot[fcIdx]);
					mXDay2ClassCourse.put(day, cc);
					mXSlot2ClassCourse.put(slot, cc);
					mXDay2Index.put(x_day[fcIdx], fcIdx);
					mXSlot2Index.put(x_slot[fcIdx], fcIdx);
				}
			}

			/*
			 * for (int i = 0; i < nbFC; i++) { VarIntLS day = x_day[i];
			 * VarIntLS slot = x_slot[i];
			 * 
			 * ClassCourse cc1 = mXDay2ClassCourse.get(day); ClassCourse cc2 =
			 * mXSlot2ClassCourse.get(slot); if (cc1 != cc2) {
			 * System.out.println(
			 * "Problem in loadData_FragmentANDInitializeVariables. Not match."
			 * ); System.exit(1); } System.out.println(i+" "+cc1.ID);
			 * 
			 * }
			 */

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/** Check if two solutions are conflict with each other. */
	public boolean isConflict(SingleSolution sol1, SingleSolution sol2) {
		return true;
	}

	/***/
	public int getAssignDelta(int pickedClass, SingleSolution sol) {
		int sumAssignDelta = 0;
		ClassFU cls = DA.classes[pickedClass];
		ArrayList<ClassCourse> ccList = DA.mClass2ClassCourseList.get(cls);
		for (ClassCourse cc : ccList) {
			ArrayList<VarIntLS> xdayList = mClassCourse2x_dayList.get(cc);
			ArrayList<VarIntLS> xslotList = mClassCourse2x_slotList.get(cc);
			ArrayList<DaySlot> dsList = new ArrayList<>();

			// tim day, slot of classcourse cc in sol
			for (int d = 0; d < DA.nbDays_20; d++) {
				for (int sl = 0; sl < DA.nbSlotsPerHalfDay * 2; sl++) {
					if (sol.T[sl][d] >= 0 && sol.T[sl][d] == cc.ID) {
						dsList.add(new DaySlot(d, sl));
					}
				}
			}

			if (xdayList.size() != dsList.size() || xslotList.size() != dsList.size()) {
				System.out.println(xdayList.size());
				System.out.println("Problem in public int getAssignDelta(int pickedClass, SingleSolution sol).");
				System.exit(1);
			}
			int size = dsList.size();
			for (int i = 0; i < size; i++) {
				DaySlot ds = dsList.get(i);
				VarIntLS xday = xdayList.get(i);
				VarIntLS xslot = xslotList.get(i);
				int s1 = S.getAssignDelta(xday, ds.day);
				int s2 = S.getAssignDelta(xslot, ds.slot);
				sumAssignDelta += s1;
				sumAssignDelta += s2;
			}

		}

		return sumAssignDelta;
	}

	public void setValuePropagage(int pickedClass, SingleSolution sol) {
		ClassFU cls = DA.classes[pickedClass];
		ArrayList<ClassCourse> ccList = DA.mClass2ClassCourseList.get(cls);
		for (ClassCourse cc : ccList) {
			ArrayList<VarIntLS> xdayList = mClassCourse2x_dayList.get(cc);
			ArrayList<VarIntLS> xslotList = mClassCourse2x_slotList.get(cc);
			ArrayList<DaySlot> dsList = new ArrayList<>();

			// tim day, slot of classcourse cc in sol
			for (int d = 0; d < DA.nbDays_20; d++) {
				for (int sl = 0; sl < DA.nbSlotsPerHalfDay * 2; sl++) {
					if (sol.T[sl][d] >= 0 && sol.T[sl][d] == cc.ID) {
						dsList.add(new DaySlot(d, sl));
					}
				}
			}

			if (xdayList.size() != dsList.size() || xslotList.size() != dsList.size()) {
				System.out.println(
						xdayList.size() + " vs " + dsList.size() + " , " + xslotList.size() + " vs " + dsList.size());
				System.out.println("Problem in public int setValuePropagage(int pickedClass, SingleSolution sol).");
				System.exit(1);
			}
			int size = dsList.size();
			for (int i = 0; i < size; i++) {
				DaySlot ds = dsList.get(i);
				VarIntLS xday = xdayList.get(i);
				VarIntLS xslot = xslotList.get(i);
				// int dIdx = mXDay2Index.get(xday);
				// int sIdx = mXSlot2Index.get(xslot);
				xday.setValuePropagate(ds.day);
				xslot.setValuePropagate(ds.slot);
			}

		}

	}
	////////////////////////////////////////////////////////////////////////////

	/**
	 * KHOI TAO: load data, make all solutions for a class, create a timetable
	 * for all class, backup current timetable, calculate supplied teacher
	 */

	public SingleSolution[] makeSolutionWarehouse_Template2_4EmptyClass() {
		SingleSolution[] sol = new SingleSolution[1];
		SingleSolution ss = new SingleSolution();
		ss.T = new int[DA.nbSlotsPerHalfDay * 2][DA.nbDays_20];
		for (int d = 0; d < DA.nbDays_20; d++) {
			for (int sl = 0; sl < DA.nbSlotsPerHalfDay * 2; sl++) {
				ss.T[sl][d] = -1;
			}
		}
		ss.position = 0;
		sol[0] = ss;
		return sol;
	}

	public void makeSolutionWarehouse_Template2(String targetFn) {
		// load data file
		// make solution warehouses
		// save to public TimeTableOneClass_SM[] timeTable_WareHouse;
		// DA = new DataCenter();
		long startTime = System.currentTimeMillis();
		// DA.loadData_Course_Class(sourceFn);
		DA.blockClassifyANDGenOneClassData();

		timeTable_WareHouse = new TimeTableOneClass_SM[DA.nbClass];
		for (int cl = 0; cl < DA.nbClass; cl++) {
			timeTable_WareHouse[cl] = new TimeTableOneClass_SM();
			timeTable_WareHouse[cl].D = new DataOneClass();
			timeTable_WareHouse[cl].D.loadData_SM(DataCenter.FILENAME_PREFIX_DATAONECLASS + cl + ".txt");
			System.out.println("Done class " + cl);
		}

		for (int cl = 0; cl < DA.nbClass; cl++) {
			ClassFU cls = DA.classes[cl];
			ArrayList<ClassCourse> L = DA.mClass2ClassCourseList.get(cls);
			TimeTableOneClass_SM tt1c = timeTable_WareHouse[cl];
			if (L.size() <= 0) {
				System.out.println("no course in this class " + cls.code);
				tt1c.sol = makeSolutionWarehouse_Template2_4EmptyClass();
				// tt1c.printHTML("class_" + cl + ".html");
				// System.exit(1);
			} else {
				tt1c.model = new CPModel();
				tt1c.solver = new CPSolver();
				tt1c.stateModel_Template2();
				tt1c.solver.read(tt1c.model);
				tt1c.findSolution_Template2(DataCenter.FILENAME_PREFIX_DATAONECLASS + cl + ".html",
						TimeTableAllClass.LIMIT_NBSOLUTION_PERCLASS);
				// tt1c.printHTML("class_" + cl + ".html");
			}
		}

		long estimatedTime = System.currentTimeMillis() - startTime;
		System.out.println("Making solution warehouses in " + estimatedTime / 1000 + " secs");

		saveSolutionWarehouse(targetFn);
	}

	public void makeSolutionWarehouse_Template1(String targetFn) {
		// load data file
		// make solution warehouses
		// save to public TimeTableOneClass_SM[] timeTable_WareHouse;
		// DA = new DataCenter();
		long startTime = System.currentTimeMillis();
		// DA.loadData_Course_Class(sourceFn);
		DA.blockClassifyANDGenOneClassData();

		timeTable_WareHouse = new TimeTableOneClass_SM[DA.nbClass];
		for (int cl = 0; cl < DA.nbClass; cl++) {
			timeTable_WareHouse[cl] = new TimeTableOneClass_SM();
			timeTable_WareHouse[cl].D = new DataOneClass();
			timeTable_WareHouse[cl].D.loadData_SM(DataCenter.FILENAME_PREFIX_DATAONECLASS + cl + ".txt");
			System.out.println("Done class " + cl);
		}

		for (int cl = 0; cl < DA.nbClass; cl++) {
			TimeTableOneClass_SM tt1c = timeTable_WareHouse[cl];
			tt1c.model = new CPModel();
			tt1c.solver = new CPSolver();
			tt1c.stateModel_Template1();
			tt1c.solver.read(tt1c.model);
			tt1c.findSolution_Template1(DataCenter.FILENAME_PREFIX_DATAONECLASS + cl + ".html",
					TimeTableAllClass.LIMIT_NBSOLUTION_PERCLASS);
			// tt1c.printHTML_all("class_" + cl +
			// ".html",limitNbSolutionPerClass);
		}

		long estimatedTime = System.currentTimeMillis() - startTime;
		System.out.println("Making solution warehouses in " + estimatedTime / 1000 + " secs");

		saveSolutionWarehouse(targetFn);
	}

	public void saveSolutionWarehouse(String fn) {
		// save public TimeTableOneClass_SM[] timeTable_WareHouse to file
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(fn);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			for (int i = 0; i < DA.nbClass; i++) {
				oos.writeObject(timeTable_WareHouse[i].sol);
			}
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void savebeingUsedTimeTable(String fn) {
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(fn);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			for (int i = 0; i < DA.nbClass; i++) {
				oos.writeObject(beingUsedTimeTable[i]);
			}
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public SingleSolution[] loadbeingUsedTimeTable(String fn) {
		SingleSolution[] sol = new SingleSolution[DA.nbClass];
		FileInputStream fis;
		try {
			fis = new FileInputStream(fn);
			ObjectInputStream ois = new ObjectInputStream(fis);
			for (int i = 0; i < DA.nbClass; i++) {
				sol[i] = (SingleSolution) ois.readObject();
			}
			ois.close();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sol;
	}

	public void loadSolutionWarehouse(String fn) {
		// load data to public TimeTableOneClass_SM[] timeTable_WareHouse
		FileInputStream fis;
		try {
			fis = new FileInputStream(fn);
			ObjectInputStream ois = new ObjectInputStream(fis);
			for (int i = 0; i < DA.nbClass; i++) {
				timeTable_WareHouse[i].sol = (SingleSolution[]) ois.readObject();
			}
			ois.close();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void init(String fn_classTimeTableSolutions, String fn_data_teacherSupply) {
		// DA = new DataCenter();
		// DA.loadData_Course_Class(fn_data_course_class);
		// DA.(fn_data_mergedCases);
		stateModelAllClass("fc.txt");

		timeTable_WareHouse = new TimeTableOneClass_SM[DA.nbClass];
		for (int cl = 0; cl < DA.nbClass; cl++) {
			timeTable_WareHouse[cl] = new TimeTableOneClass_SM();
			timeTable_WareHouse[cl].D = new DataOneClass();
			timeTable_WareHouse[cl].D.loadData_SM(DataCenter.FILENAME_PREFIX_DATAONECLASS + cl + ".txt");
			System.out.println("Done class " + cl);
		}

		loadSolutionWarehouse(fn_classTimeTableSolutions);

		beingUsedTimeTable = new SingleSolution[DA.nbClass];
		backUpTimeTable = new int[DA.nbClass];
		Random r = new Random();
		for (int cl = 0; cl < DA.nbClass; cl++) {
			TimeTableOneClass_SM tt1c = timeTable_WareHouse[cl];
			beingUsedTimeTable[cl] = new SingleSolution();

			int upperBound = 0;
			if (tt1c.sol.length < TimeTableAllClass.LIMIT_NBSOLUTION_PERCLASS) {
				upperBound = tt1c.sol.length;
			} else {
				upperBound = TimeTableAllClass.LIMIT_NBSOLUTION_PERCLASS;
			}
			if (upperBound < 1) {
				System.out.println("number of solution of class " + DA.classes[cl].code + " < 1");
				System.exit(1);
			}
			int p = r.nextInt((upperBound - 1 - 0) + 1) + 0;
			// int p = 0;
			beingUsedTimeTable[cl] = tt1c.sol[p];
			setValuePropagage(cl, beingUsedTimeTable[cl]);
			backUpTimeTable[cl] = tt1c.sol[p].position;
		}

		// this.loadData_supply_Teacher(fn_data_teacherSupply);
		supplyTeacher4Optimization = calculateSupplyTeacher();
		demand_OfACourse = calculateTeacherDemand(beingUsedTimeTable);
		supply_Department = calculateDepartmentSupplyTeacher();
		demand_Department = calculateDepartmentTeacherDemand(beingUsedTimeTable);
		
		totalDemand = sumOfAnArray(demand_OfACourse);
		totalSupply = sumOfAnArray(supplyTeacher4Optimization);
		System.out.println("total demand 0 = " + totalDemand);
		System.out.println("total supply 0 = " + totalSupply);
	}

	public void initialize(int limitNbSolutionPerClass) {
		DA = new DataCenter();
		long startTime = System.currentTimeMillis();
		DA.loadData_Course_Class("data_all_sm.txt");
		long estimatedTime = System.currentTimeMillis() - startTime;
		System.out.println("Loading data in " + estimatedTime / 1000 + " secs");

		timeTable_WareHouse = new TimeTableOneClass_SM[DA.nbClass];
		for (int cl = 0; cl < DA.nbClass; cl++) {
			timeTable_WareHouse[cl] = new TimeTableOneClass_SM();
			timeTable_WareHouse[cl].D = new DataOneClass();
			timeTable_WareHouse[cl].D.loadData_SM("cls" + (cl + 1) + ".txt");
			System.out.println("Done class " + cl);
		}

		beingUsedTimeTable = new SingleSolution[DA.nbClass];
		backUpTimeTable = new int[DA.nbClass];
		Random r = new Random();
		for (int cl = 0; cl < DA.nbClass; cl++) {
			TimeTableOneClass_SM tt1c = timeTable_WareHouse[cl];
			tt1c.model = new CPModel();
			tt1c.solver = new CPSolver();
			tt1c.stateModel_Template2();
			tt1c.solver.read(tt1c.model);
			tt1c.findSolution_Template2("class_" + cl + ".html", limitNbSolutionPerClass);
			beingUsedTimeTable[cl] = new SingleSolution();
			int p = r.nextInt((tt1c.sol.length - 1 - 0) + 1) + 0;
			// int p = 0;
			beingUsedTimeTable[cl] = tt1c.sol[p];
			backUpTimeTable[cl] = tt1c.sol[p].position;
		}

		// this.loadData_supply_Teacher("teacher_supply.txt");
		this.supplyTeacher4Optimization = this.calculateSupplyTeacher();
		this.demand_OfACourse = this.calculateTeacherDemand(beingUsedTimeTable);
		totalDemand = sumOfAnArray(demand_OfACourse);
		totalSupply = sumOfAnArray(supplyTeacher4Optimization);
		System.out.println("total demand 0 = " + totalDemand);
		System.out.println("total supply 0 = " + totalSupply);
	}

	public int[] calculateTeacherDemand(SingleSolution[] pickedSolutions) {
		/*
		 * CALCULATE DEMAND OF ALL COURSES input: timetable of all class supply
		 * teacher of each course output: demand of each course
		 */
		int[] maxDemand = new int[DA.nbCourse];
		int[] slotDemand = new int[DA.nbCourse];
		for (int c = 0; c < DA.nbCourse; c++) {
			maxDemand[c] = 0;
			slotDemand[c] = 0;
		}

		for (int day = 0; day < DA.nbDays_20; day++) {
			for (int slot = 0; slot < DA.nbSlotsPerHalfDay * 2; slot++) {
				for (int c = 0; c < DA.nbCourse; c++) {
					slotDemand[c] = 0;
				}
				for (int cl = 0; cl < DA.nbClass; cl++) {
					int ccID = pickedSolutions[cl].T[slot][day];
					if (ccID != -1) {
						ClassCourse cc = DA.mID2ClassCourse.get(ccID);
						if (cc != null) {
							Course c = DA.mClassCourse2Course.get(cc);
							// System.out.println(c.ID);
							if (c != null) {
								int cIdx = DA.mCourse2Index.get(c);
								slotDemand[cIdx]++;
							}
						}
					}

				}
				for (int c = 0; c < DA.nbCourse; c++) {
					if (slotDemand[c] > maxDemand[c]) {
						maxDemand[c] = slotDemand[c];
					}
					// System.out.println("demand c["+c+"] = "+maxDemand[c]);
				}
			}
		}
		return maxDemand;
	}

	public void testDepartMentDemand(SingleSolution[] ttb){
		int[] demand = calculateDepartmentTeacherDemand(ttb);
		for (Department d : DA.departments) {
			int idx = DA.mDepartment2Index.get(d);
			System.out.println(d.code+", demand = "+demand[idx]);
		}
	}
	public int[] calculateDepartmentTeacherDemand(SingleSolution[] pickedSolutions) {
		/*
		 * CALCULATE DEMAND OF ALL COURSES input: timetable of all class supply
		 * teacher of each course output: demand of each course
		 */
		int[] maxDemand = new int[DA.nbDepartment];
		int[] slotDemand = new int[DA.nbDepartment];
		for (int c = 0; c < DA.nbDepartment; c++) {
			maxDemand[c] = 0;
			slotDemand[c] = 0;
		}

		for (int day = 0; day < DA.nbDays_20; day++) {
			for (int slot = 0; slot < DA.nbSlotsPerHalfDay * 2; slot++) {
				for (int dep = 0; dep < DA.nbDepartment; dep++) {
					slotDemand[dep] = 0;
				}
				for (int cl = 0; cl < DA.nbClass; cl++) {
					int ccID = pickedSolutions[cl].T[slot][day];
					if (ccID != -1) {
						ClassCourse cc = DA.mID2ClassCourse.get(ccID);
						if (cc != null) {
							Course c = DA.mClassCourse2Course.get(cc);
							// System.out.println(c.ID);
							if (c != null) {
//								int cIdx = DA.mCourse2Index.get(c);
//								slotDemand[cIdx]++;
								Department d = DA.mCourse2Department.get(c);
								if (d != null) {
									int dIdx = DA.mDepartment2Index.get(d);
									slotDemand[dIdx] = slotDemand[dIdx]+1;
								}else{
									System.out.println("ko co dep");
									System.exit(1);
								}
							}
						}
					}

				}
				for (int dep = 0; dep < DA.nbDepartment; dep++) {
					if (slotDemand[dep] > maxDemand[dep]) {
						maxDemand[dep] = slotDemand[dep];
					}
				}
			}
		}
		return maxDemand;
	}
	
	public int[] calculateDepartmentTeacherSupply(){
		supply_Department = new int[DA.nbDepartment];
		for (Department department : DA.departments) {
			ArrayList<Teacher> L = DA.mDepartment2TeacherList.get(department);	
			int depIdx = DA.mDepartment2Index.get(department);
			supply_Department[depIdx] = L.size();
		}
		return supply_Department;
	}
	
	public int sumOfAnArray(int[] array) {
		int len = array.length;
		int sum = 0;
		for (int c = 0; c < len; c++) {
			sum += array[c];
		}
		return sum;
	}

	public SingleSolution changeSession(SingleSolution singleSol) {
		/*
		 * CHANGE SESSION OF A SOLUTION input: a single solution ouput: a single
		 * solution with different session
		 */
		SingleSolution tempSol = new SingleSolution();
		tempSol.T = new int[DA.nbSlotsPerHalfDay * 2][DA.nbDays_20];
		tempSol.position = singleSol.position;
		for (int d = 0; d < DA.nbDays_20; d++) {
			for (int sl = 0; sl < DA.nbSlotsPerHalfDay * 2; sl++) {
				tempSol.T[sl][d] = EMPTY_SLOT;
			}
		}

		int oldSession = 0;
		int day = 0;
		boolean done = false;
		for (int d = 0; d < DA.nbDays_20; d++) {
			if (done) {
				// phai chay qua tat ca cac ngay moi chac chan duoc session bang
				// bao nhieu
				// vi co the co ngay 0 lop do ko hoc
				break;
			}
			for (int slot = 0; slot < DA.nbSlotsPerHalfDay * 2; slot++) {
				if (singleSol.T[slot][day] > 2) {
					if (slot > SLOT_3) {
						oldSession = 1;
					} else {
						oldSession = 0;
					}
					done = true;
					break;
				}
			}
		}

		int newSession = 0;
		if (oldSession == 0) {
			newSession = 1;
		}

		if (newSession == 0) {
			for (int d = 0; d < DA.nbDays_20; d++) {
				for (int sl = SLOT_4; sl < DA.nbSlotsPerHalfDay * 2; sl++) {
					// System.out.println("day = "+d+" sl = "+sl);
					if (singleSol.T[sl][d] != -1) {
						tempSol.T[sl - DA.nbSlotsPerHalfDay][d] = singleSol.T[sl][d];
					} else {
						tempSol.T[sl - DA.nbSlotsPerHalfDay][d] = EMPTY_SLOT;
					}
				}
			}
		} else {
			for (int d = 0; d < DA.nbDays_20; d++) {
				for (int sl = SLOT_1; sl < DA.nbSlotsPerHalfDay; sl++) {
					// System.out.println("day = "+d+" sl = "+sl);
					if (singleSol.T[sl][d] != -1) {
						tempSol.T[sl + DA.nbSlotsPerHalfDay][d] = singleSol.T[sl][d];
					} else {
						tempSol.T[sl + DA.nbSlotsPerHalfDay][d] = EMPTY_SLOT;
					}
				}
			}
		}

		return tempSol;
	}

	public int[] calculateSupplyTeacher() {
		int[] supply = new int[DA.nbCourse];
		for (int i = 0; i < DA.nbCourse; i++) {
			Course c = DA.courses[i];
			ArrayList<ClassCourse> L = DA.mCourse2ClassCourseList.get(c);
			int s = (int) Math.ceil((double) L.size() / MAX_NBCOURSEPERSEMESTER);
			int roomLimit = DA.aCourse2RoomLimit[i];
			supply[i] = Math.min(s, roomLimit);
		}

		return supply;
	}
	
	public int[] calculateDepartmentSupplyTeacher() {
		int[] supply = new int[DA.nbDepartment];
		for (int i = 0; i < DA.nbDepartment; i++) {
			Department d = DA.departments[i];
			System.out.println(d.code);
			ArrayList<Teacher> teacherList = DA.mDepartment2TeacherList.get(d);
			if (teacherList == null) {
				System.out.println("null: "+d.code);
				System.exit(1);
			}
			ArrayList<Course> courseList = DA.mDepartment2CourseList.get(d);
			int minRoomSupply = DA.nbRoom;
			for (Course course : courseList) {
				int cIdx = DA.mCourse2Index.get(course);
				int roomLimit = DA.aCourse2RoomLimit[cIdx];
				if (roomLimit < minRoomSupply) {
					minRoomSupply = roomLimit;
				}
			}			
			supply[i] = Math.min(minRoomSupply, 
					teacherList.size());
		}
		return supply;
	}

	
	public int marginOfAClass(int[] demand, int[] supply, int classIdx) {
		int margin = 0;
		ArrayList<Course> L = new ArrayList<Course>();
		ClassFU cls = DA.classes[classIdx];
		L = DA.mClass2CourseList.get(cls);
		for (Course course : L) {
			int cIdx = DA.mCourse2Index.get(course);
			int m = demand[cIdx] - supply[cIdx];
			margin += m;
		}
		return margin;
	}

	/**
	 * PICK MOST VIOLATED CLASS: input: supply teacher of each class,
	 * demand_OfACourse; output: index of picked class
	 */
	public int pickMostViolatedClass(int[] demand, int[] supply, HashSet<Integer> banned) {

		// supplyTeacher = new int[DA.nbCourse];
		// supplyTeacher = calculateSupplyTeacher();
		marginDemand_OfACourse = new int[DA.nbCourse];

		for (int c = 0; c < DA.nbCourse; c++) {
			marginDemand_OfACourse[c] = demand[c] - supply[c];
		}

		marginDemand_OfAClass = new int[DA.nbClass];
		nbOfViolatedCourse_OfAClass = new int[DA.nbClass];
		for (int cl = 0; cl < DA.nbClass; cl++) {
			ArrayList<Course> L = new ArrayList<Course>();
			ClassFU cls = DA.classes[cl];
			L = DA.mClass2CourseList.get(cls);
			for (Course course : L) {
				int cIdx = DA.mCourse2Index.get(course);
				marginDemand_OfAClass[cl] += marginDemand_OfACourse[cIdx];
				if (marginDemand_OfACourse[cIdx] > 0) {
					nbOfViolatedCourse_OfAClass[cl]++;
				}
			}
			System.out.println("cl = " + cl + ", margin = " + marginDemand_OfAClass[cl] + ", nbViolated = "
					+ nbOfViolatedCourse_OfAClass[cl]);
		}

		int pickedClass = -1;
		int maxNbOfViolated = 0;
		int maxMargin = 0;
		for (int cl = 0; cl < DA.nbClass; cl++) { // make sure nbClass > 1
			if (maxMargin < marginDemand_OfAClass[cl] && banned.contains(cl) == false) {
				// change
				pickedClass = cl;
				maxMargin = marginDemand_OfAClass[cl];
				maxNbOfViolated = nbOfViolatedCourse_OfAClass[cl];
			} else if (maxMargin == marginDemand_OfAClass[cl] && banned.contains(cl) == false) {
				if (maxNbOfViolated < nbOfViolatedCourse_OfAClass[cl]) {
					// change
					pickedClass = cl;
					maxMargin = marginDemand_OfAClass[cl];
					maxNbOfViolated = nbOfViolatedCourse_OfAClass[cl];
				}
			}
		}

		// -------------------------
		// pick a class randomly among the most violiated classes
		ArrayList<Integer> mostViolatedClassIndexList;
		mostViolatedClassIndexList = new ArrayList<>();
		for (int cl = 0; cl < DA.nbClass; cl++) { // make sure nbClass > 1
			if (banned.contains(cl) == false && maxMargin == marginDemand_OfAClass[cl]
					&& maxNbOfViolated == nbOfViolatedCourse_OfAClass[cl]) {
				mostViolatedClassIndexList.add(cl);
			}
		}

		if (mostViolatedClassIndexList.size() > 0) {
			Random r = new Random();
			int rIdx = r.nextInt(mostViolatedClassIndexList.size());
			pickedClass = mostViolatedClassIndexList.get(rIdx);
			System.out.print("Picked class = " + pickedClass + ", candidates:");
			for (int idx = 0; idx < mostViolatedClassIndexList.size(); idx++) {
				System.out.print(" " + mostViolatedClassIndexList.get(idx));
			}
			System.out.println();
		}
		
		// -------------------------

		return pickedClass;
	}

	/** Calculate constraints violation of a class. */
	/*
	 * public int calViolationOfAClass(int classIdx){ int sumViolation = 0;
	 * ClassFU cls = DA.classes[classIdx]; ArrayList<ClassCourse> ccList =
	 * DA.mClass2ClassCourseList.get(cls); for (ClassCourse cc : ccList) {
	 * ArrayList<VarIntLS> xdayList = mClassCourse2x_dayList.get(cc);
	 * ArrayList<VarIntLS> xslotList = mClassCourse2x_slotList.get(cc); int size
	 * = xdayList.size(); for (int i = 0; i < size; i++) { VarIntLS xday =
	 * xdayList.get(i); VarIntLS xslot = xslotList.get(i); sumViolation +=
	 * S.violations(xday); sumViolation += S.violations(xslot); } } return
	 * sumViolation; }
	 */
	public int calViolationOfAClass(int classIdx) {
		int sumViolation = 0;
		ClassFU cls = DA.classes[classIdx];
		ArrayList<ClassCourse> ccList = DA.mClass2ClassCourseList.get(cls);
		for (ClassCourse cc : ccList) {
			ArrayList<VarIntLS> xdayList = mClassCourse2x_dayList.get(cc);
			ArrayList<VarIntLS> xslotList = mClassCourse2x_slotList.get(cc);
			int size = xdayList.size();
			for (int i = 0; i < size; i++) {
				VarIntLS xday = xdayList.get(i);
				VarIntLS xslot = xslotList.get(i);
				sumViolation += S.violations(xday);
				sumViolation += S.violations(xslot);
			}
		}
		return sumViolation;
	}

	public void findOptimalTimeTable_v2_3(String fn_classTimeTableSolutions) {
		init(fn_classTimeTableSolutions, "");

		int iter = 0;
		HashSet<Integer> bannedClass = new HashSet<Integer>();
		System.out.println("Starting violation = " + S.violations());
		do {
			if (bannedClass.size() == DA.nbClass && S.violations() > 0) {
				// System.out.println("All classes banned...");
				// break;
				bannedClass.clear();
			}
			System.out.println();
			System.out.println("iteration = " + iter);

			// pick a class
			int pickedClass = -1;
			int maxViolation = 0;
			for (int cls = 0; cls < DA.nbClass; cls++) {
				int clsViolation = calViolationOfAClass(cls);
				if (clsViolation > maxViolation) {
					maxViolation = clsViolation;
					pickedClass = cls;
				}
			}
			System.out.println("picked class = " + pickedClass);
			if (pickedClass == -1) {
				System.out.println("cannot pick class anymore ....");
				break;
			}

			// backing up current solution of picked class
			backUpTimeTable[pickedClass] = beingUsedTimeTable[pickedClass].position;
			int positionOfBest = backUpTimeTable[pickedClass];
			int newLimit = timeTable_WareHouse[pickedClass].sol.length;
			int next = -1;
			int currentViolation = S.violations();
			int diff = 0;
			next = 0;

			int bestReduce = 0;
			// int oldPosition = backUpTimeTable[pickedClass];
			int currentDelta = getAssignDelta(pickedClass,
					timeTable_WareHouse[pickedClass].sol[backUpTimeTable[pickedClass]]);
			do { // find best solution for picked class
				if (next != backUpTimeTable[pickedClass]) { // ko duyet solution
															// da co
					// lay solution tai next
					beingUsedTimeTable[pickedClass] = timeTable_WareHouse[pickedClass].sol[next];

					// tinh new violation, so voi best violation.
					int newdelta = getAssignDelta(pickedClass, timeTable_WareHouse[pickedClass].sol[next]);
					setValuePropagage(pickedClass, timeTable_WareHouse[pickedClass].sol[next]);
					int newViolation = S.violations();
					int newReduce = newViolation - currentViolation;
					// if(newdelta < currentDelta){
					if (newReduce < bestReduce) {
						currentDelta = newdelta;
						bestReduce = newReduce;

						positionOfBest = next;
					}
				}
				next++;
			} while (next < newLimit);

			// neu position best == backup -> ban this class
			// else: unban all
			if (positionOfBest == backUpTimeTable[pickedClass]) { // better
																	// solution
																	// not
																	// found.

				bannedClass.add(pickedClass);
				System.out.println("class " + pickedClass + " has been banned");
				System.out.println("total " + bannedClass.size() + " classes banned...");

				int oldPosition = backUpTimeTable[pickedClass];
				beingUsedTimeTable[pickedClass] = timeTable_WareHouse[pickedClass].sol[oldPosition];
				setValuePropagage(pickedClass, timeTable_WareHouse[pickedClass].sol[oldPosition]);

				System.out.println("best violation = " + S.violations());

			} else { // better solution found.
				// System.out.println("Better violation =
				// "+getAssignDelta(pickedClass,
				// timeTable_WareHouse[pickedClass].sol[positionOfBest]));
				setValuePropagage(pickedClass, timeTable_WareHouse[pickedClass].sol[positionOfBest]);
				System.out.println("Better violation = " + calViolationOfAClass(pickedClass));
				beingUsedTimeTable[pickedClass] = timeTable_WareHouse[pickedClass].sol[positionOfBest];
				System.out.println("best violation = " + S.violations());
				bannedClass.add(pickedClass);
				if (bannedClass.size() > LIMIT_NBCLASS_INBANNEDLIST) {
					bannedClass.clear();
					System.out.println("Unbanned all...");
				}
			}
			iter++;
		} while (iter < NB_ITERATION && S.violations() > 0);
		System.out.println("Final violation = " + S.violations());
		// print result
		System.out.println("Done finding...");
		System.out.println("\nResult:.....");

		demand_OfACourse = calculateTeacherDemand(beingUsedTimeTable);
		totalDemand = sumOfAnArray(demand_OfACourse);
		System.out.println("total demand = " + totalDemand);
		System.out.println("total supply = " + sumOfAnArray(supplyTeacher4Optimization));
		for (int c = 0; c < DA.nbCourse; c++) {
			System.out.println("ID = " + DA.courses[c].ID + ", code = " + DA.courses[c].code + ", demand = "
					+ demand_OfACourse[c] + ", supply = " + supplyTeacher4Optimization[c] + ", margin = "
					+ (demand_OfACourse[c] - supplyTeacher4Optimization[c]));
		}

		int nbRoom = calRoomDemand(beingUsedTimeTable);
		System.out.println("nbroom needed = " + nbRoom);

		// writeMatrix2File("conflict_matrix.txt", beingUsedTimeTable);
		// ==================================================================================================================
		// now find better feasible timetable
		// OPTIMIZING
		iter = 0;
		bannedClass = new HashSet<Integer>();
		int base_violation = S.violations();
		System.out.println("Violation after making feasible solution: " + base_violation);
		// do {
		while (iter < NB_ITERATION && totalDemand > totalSupply) {
			if (bannedClass.size() == DA.nbClass) {
				System.out.println("All classes banned...");
				break;
			}
			System.out.println();
			System.out.println("iteration = " + iter);
			int pickedClass = -1;

			pickedClass = this.pickMostViolatedClass(demand_OfACourse, supplyTeacher4Optimization, bannedClass);
			System.out.println("picked class = " + pickedClass);
			if (pickedClass == -1) {
				System.out.println("cannot pick class anymore ....");
				break;
			}

			// backing up current solution of picked class
			backUpTimeTable[pickedClass] = beingUsedTimeTable[pickedClass].position;
			int positionOfBest = backUpTimeTable[pickedClass];
			int bestRoomDemand = calRoomDemand(beingUsedTimeTable);
			int newLimit = timeTable_WareHouse[pickedClass].sol.length;
			// if (newLimit > LIMIT_NBSOLUTION_PERCLASS_WHENOPTIMIZE) {
			// newLimit = LIMIT_NBSOLUTION_PERCLASS_WHENOPTIMIZE;
			// }
			int[] newDemand = new int[DA.nbCourse];
			int bestTotalDemand = totalDemand;
			demand_OfACourse = calculateTeacherDemand(beingUsedTimeTable);
			totalDemand = sumOfAnArray(demand_OfACourse);

			int next = -1;
			int diff = 0;
			do {
				int newTotalDemand = totalDemand;
				int currentRoomDemand = 0;
				next = 0;
				do {
					if (next != backUpTimeTable[pickedClass]) { // ko duyet
																// solution da
																// co

						// lay solution tai next
						beingUsedTimeTable[pickedClass] = timeTable_WareHouse[pickedClass].sol[next];
						setValuePropagage(pickedClass, timeTable_WareHouse[pickedClass].sol[next]);
						int newViolation = S.violations();

						// check if feasible
						if (newViolation <= base_violation) {
							// tinh new demand, new total demand
							newDemand = calculateTeacherDemand(beingUsedTimeTable);
							newTotalDemand = sumOfAnArray(newDemand);

							// tinh lai room demand, neu vuot qua limit thi ko
							// lay this solution
							currentRoomDemand = calRoomDemand(beingUsedTimeTable);

							if (newTotalDemand < bestTotalDemand && currentRoomDemand < bestRoomDemand + diff) {
								base_violation = newViolation;
								positionOfBest = next;
								bestTotalDemand = newTotalDemand;
								bestRoomDemand = currentRoomDemand;
								System.out.println(diff + "th chance");
								System.out.println("room demand = " + currentRoomDemand);
							}

						}
					}
					next++;
				} while (next < newLimit);
				diff++;
			} while (diff <= LIMIT_ROOMALLOWANCE && positionOfBest == backUpTimeTable[pickedClass]);

			// neu position best == backup -> ban this class
			// else: unban all
			if (positionOfBest == backUpTimeTable[pickedClass]) { // better
																	// solution
																	// not
																	// found.

				bannedClass.add(pickedClass);
				System.out.println("class " + pickedClass + " has been banned");
				System.out.println("total " + bannedClass.size() + " classes banned...");

				int oldPosition = backUpTimeTable[pickedClass];
				beingUsedTimeTable[pickedClass] = timeTable_WareHouse[pickedClass].sol[oldPosition];
				setValuePropagage(pickedClass, timeTable_WareHouse[pickedClass].sol[oldPosition]);

				System.out.println("best violation = " + S.violations());

			} else { // better solution found.
				System.out.println("Better violation = "
						+ getAssignDelta(pickedClass, timeTable_WareHouse[pickedClass].sol[positionOfBest]));
				setValuePropagage(pickedClass, timeTable_WareHouse[pickedClass].sol[positionOfBest]);
				beingUsedTimeTable[pickedClass] = timeTable_WareHouse[pickedClass].sol[positionOfBest];
				System.out.println("best violation = " + S.violations());
				bannedClass.add(pickedClass);
				if (bannedClass.size() > LIMIT_NBCLASS_INBANNEDLIST) {
					bannedClass.clear();
					System.out.println("Unbanned all...");
				}
			}

			// ------------------------------------------------------------------------
			iter++;
			// } while (iter < NB_ITERATION && totalDemand > totalSupply);
		}
		System.out.println("Final violation = " + S.violations());
		System.out.println("Done finding...");
		System.out.println("\nResult:.....");

		demand_OfACourse = calculateTeacherDemand(beingUsedTimeTable);
		totalDemand = sumOfAnArray(demand_OfACourse);
		System.out.println("total demand = " + totalDemand);
		System.out.println("total supply = " + sumOfAnArray(supplyTeacher4Optimization));
		for (int c = 0; c < DA.nbCourse; c++) {
			System.out.println("ID = " + DA.courses[c].ID + ", code = " + DA.courses[c].code + ", demand = "
					+ demand_OfACourse[c] + ", supply = " + supplyTeacher4Optimization[c] + ", margin = "
					+ (demand_OfACourse[c] - supplyTeacher4Optimization[c]));
		}

		nbRoom = calRoomDemand(beingUsedTimeTable);
		System.out.println("nbroom needed = " + nbRoom);
	}

	public void findOptimalTimeTable_v2(String commonData, String solutionWarehouse, int limit) {
		// initialize(NB_OF_SOLUTION_PERCLASS_LIMIT);
		init(solutionWarehouse, null);

		int iter = 0;
		HashSet<Integer> bannedClass = new HashSet<Integer>();
		do {
			if (bannedClass.size() == DA.nbClass) {
				System.out.println("All classes banned...");
				break;
			}
			System.out.println();
			System.out.println("iteration = " + iter);
			int pickedClass = -1;

			pickedClass = this.pickMostViolatedClass(demand_OfACourse, supplyTeacher4Optimization, bannedClass);
			System.out.println("picked class = " + pickedClass);
			if (pickedClass == -1) {
				System.out.println("cannot pick class anymore ....");
				break;
			}

			// backing up current solution of picked class
			backUpTimeTable[pickedClass] = beingUsedTimeTable[pickedClass].position;
			int positionOfBest = backUpTimeTable[pickedClass];
			int bestRoomDemand = calRoomDemand(beingUsedTimeTable);
			int newLimit = timeTable_WareHouse[pickedClass].sol.length;
			int[] newDemand = new int[DA.nbCourse];
			int bestTotalDemand = totalDemand;
			demand_OfACourse = calculateTeacherDemand(beingUsedTimeTable);
			totalDemand = sumOfAnArray(demand_OfACourse);

			int next = -1;
			// next = 0;

			int diff = 0;
			do {
				int newTotalDemand = totalDemand;
				int currentRoomDemand = 0;
				int bestScore = 0;
				int current_score = 0;
				next = 0;
				do {
					// System.out.println("class = "+pickedClass+", position =
					// "+next);
					if (next != backUpTimeTable[pickedClass]) { // ko duyet
																// solution da
																// co
						// lay solution tai next
						// int nextsol =
						// next%timeTable_AllClass[pickedClass].sol.length;
						beingUsedTimeTable[pickedClass] = timeTable_WareHouse[pickedClass].sol[next];

						// tinh new demand, new total demand
						newDemand = calculateTeacherDemand(beingUsedTimeTable);
						newTotalDemand = sumOfAnArray(newDemand);

						// tinh lai room demand, neu vuot qua limit thi ko lay
						// this solution
						currentRoomDemand = calRoomDemand(beingUsedTimeTable);

						// tinh current score
						// current_score = 2*(newTotalDemand-bestTotalDemand) +
						// 1*(currentRoomDemand-bestRoomDemand);

						// so sanh: neu new total demand < min total demand AND
						// room demand < roomLimit
						// thi doi min. luu lai position best.
						// boolean condition1 = newTotalDemand < bestTotalDemand
						// && currentRoomDemand < bestRoomDemand+diff;
						// boolean condition2 = newTotalDemand ==
						// bestTotalDemand && currentRoomDemand <
						// bestRoomDemand;
						if (newTotalDemand < bestTotalDemand && currentRoomDemand < bestRoomDemand + diff) {
							// if (condition1 || condition2) {
							positionOfBest = next;
							bestTotalDemand = newTotalDemand;
							bestRoomDemand = currentRoomDemand;
							bestScore = current_score;
							System.out.println(diff + "th chance");
							System.out.println("room demand = " + currentRoomDemand);
						}
					}
					next++;
				} while (next < newLimit);
				diff++;
			} while (diff <= LIMIT_ROOMALLOWANCE && positionOfBest == backUpTimeTable[pickedClass]);

			// neu position best == backup -> ban this class
			// else: unban all
			if (positionOfBest == backUpTimeTable[pickedClass]) {
				bannedClass.add(pickedClass);
				System.out.println("class " + pickedClass + " has been banned");
				System.out.println("total " + bannedClass.size() + " classes banned...");

				int oldPosition = backUpTimeTable[pickedClass];
				beingUsedTimeTable[pickedClass] = timeTable_WareHouse[pickedClass].sol[oldPosition];
				System.out.println("total demand = " + totalDemand);
				System.out.println("total supply = " + totalSupply);

			} else {
				beingUsedTimeTable[pickedClass] = timeTable_WareHouse[pickedClass].sol[positionOfBest];
				totalDemand = bestTotalDemand;
				demand_OfACourse = calculateTeacherDemand(beingUsedTimeTable);
				System.out.println("total demand = " + totalDemand);
				System.out.println("total supply = " + totalSupply);
				bannedClass.add(pickedClass);
				if (bannedClass.size() > LIMIT_NBCLASS_INBANNEDLIST) {
					bannedClass.clear();
					System.out.println("Unbanned all...");
				}

			}

			iter++;
		} while (iter < NB_ITERATION && totalDemand > totalSupply);

		System.out.println("Done finding...");
		System.out.println("\nResult:.....");

		demand_OfACourse = calculateTeacherDemand(beingUsedTimeTable);
		totalDemand = sumOfAnArray(demand_OfACourse);
		System.out.println("total demand = " + totalDemand);
		System.out.println("total supply = " + sumOfAnArray(supplyTeacher4Optimization));
		for (int c = 0; c < DA.nbCourse; c++) {
			System.out.println("ID = " + DA.courses[c].ID + ", code = " + DA.courses[c].code + ", demand = "
					+ demand_OfACourse[c] + ", supply = " + supplyTeacher4Optimization[c] + ", margin = "
					+ (demand_OfACourse[c] - supplyTeacher4Optimization[c]));
		}

		int nbRoom = calRoomDemand(beingUsedTimeTable);
		System.out.println("nbroom needed = " + nbRoom);

		// writeMatrix2File("conflict_matrix.txt", beingUsedTimeTable);
	}

	public void findOptimalTimeTable_v2_2(String fn_classTimeTableSolutions) {
//		init(fn_classTimeTableSolutions, "");

		int iter = 0;
		HashSet<Integer> bannedClass = new HashSet<Integer>();
		System.out.println("Starting violation = " + S.violations());
		do {
			if (bannedClass.size() == DA.nbClass && S.violations() > 0) {
				// System.out.println("All classes banned...");
				// break;
				bannedClass.clear();
			}
			System.out.println();
			System.out.println("iteration = " + iter);

			// pick a class
			int pickedClass = -1;
			int maxViolation = 0;
			for (int cls = 0; cls < DA.nbClass; cls++) {
				int clsViolation = calViolationOfAClass(cls);
				if (clsViolation > maxViolation) {
					maxViolation = clsViolation;
					pickedClass = cls;
				}
			}
			System.out.println("picked class = " + pickedClass);
			if (pickedClass == -1) {
				System.out.println("cannot pick class anymore ....");
				break;
			}

			// backing up current solution of picked class
			backUpTimeTable[pickedClass] = beingUsedTimeTable[pickedClass].position;
			int positionOfBest = backUpTimeTable[pickedClass];
			int newLimit = timeTable_WareHouse[pickedClass].sol.length;
			int next = -1;
			int currentViolation = S.violations();
			int diff = 0;
			next = 0;

			int bestReduce = 0;
			// int oldPosition = backUpTimeTable[pickedClass];
			// int currentDelta = getAssignDelta(pickedClass,
			// timeTable_WareHouse[pickedClass].sol[backUpTimeTable[pickedClass]]);
			do { // find best solution for picked class
				if (next != backUpTimeTable[pickedClass]) { // ko duyet solution
															// da co
					// lay solution tai next
					beingUsedTimeTable[pickedClass] = timeTable_WareHouse[pickedClass].sol[next];

					// tinh new violation, so voi best violation.
					// int newdelta = getAssignDelta(pickedClass,
					// timeTable_WareHouse[pickedClass].sol[next]);
					setValuePropagage(pickedClass, timeTable_WareHouse[pickedClass].sol[next]);
					int newViolation = S.violations();
					int newReduce = newViolation - currentViolation;
					// if(newdelta < currentDelta){
					if (newReduce < bestReduce) {
						// currentDelta = newdelta;
						bestReduce = newReduce;

						positionOfBest = next;
					}
				}
				next++;
			} while (next < newLimit);

			// neu position best == backup -> ban this class
			// else: unban all
			if (positionOfBest == backUpTimeTable[pickedClass]) { // better
																	// solution
																	// not
																	// found.

				bannedClass.add(pickedClass);
				System.out.println("class " + pickedClass + " has been banned");
				System.out.println("total " + bannedClass.size() + " classes banned...");

				int oldPosition = backUpTimeTable[pickedClass];
				beingUsedTimeTable[pickedClass] = timeTable_WareHouse[pickedClass].sol[oldPosition];
				setValuePropagage(pickedClass, timeTable_WareHouse[pickedClass].sol[oldPosition]);

				System.out.println("best violation = " + S.violations());

			} else { // better solution found.
				// System.out.println("Better violation =
				// "+getAssignDelta(pickedClass,
				// timeTable_WareHouse[pickedClass].sol[positionOfBest]));
				setValuePropagage(pickedClass, timeTable_WareHouse[pickedClass].sol[positionOfBest]);
				System.out.println("Better violation = " + calViolationOfAClass(pickedClass));
				beingUsedTimeTable[pickedClass] = timeTable_WareHouse[pickedClass].sol[positionOfBest];
				System.out.println("best violation = " + S.violations());
				bannedClass.add(pickedClass);
				if (bannedClass.size() > LIMIT_NBCLASS_INBANNEDLIST) {
					bannedClass.clear();
					System.out.println("Unbanned all...");
				}
			}
			iter++;
		} while (iter < NB_ITERATION && S.violations() > 0);
		System.out.println("Final violation = " + S.violations());
		// print result
		System.out.println("Done finding...");
		System.out.println("\nResult:.....");

		demand_OfACourse = calculateTeacherDemand(beingUsedTimeTable);
		totalDemand = sumOfAnArray(demand_OfACourse);
		System.out.println("total demand = " + totalDemand);
		System.out.println("total supply = " + sumOfAnArray(supplyTeacher4Optimization));
		for (int c = 0; c < DA.nbCourse; c++) {
			System.out.println("ID = " + DA.courses[c].ID + ", code = " + DA.courses[c].code + ", demand = "
					+ demand_OfACourse[c] + ", supply = " + supplyTeacher4Optimization[c] + ", margin = "
					+ (demand_OfACourse[c] - supplyTeacher4Optimization[c]));
		}

		int nbRoom = calRoomDemand(beingUsedTimeTable);
		System.out.println("nbroom needed = " + nbRoom);

		// writeMatrix2File("conflict_matrix.txt", beingUsedTimeTable);
		// ==================================================================================================================
		// now find better feasible timetable
		// OPTIMIZING
		iter = 0;
		bannedClass = new HashSet<Integer>();
		int base_violation = S.violations();
		demand_OfACourse = calculateTeacherDemand(beingUsedTimeTable);
		int nbViolatedCourse = DA.nbCourse;
		int nBViolatedDepartment = DA.nbDepartment;
		System.out.println("Violation after making feasible solution: " + base_violation);
		do {
			if (bannedClass.size() == DA.nbClass-1) {
				bannedClass.clear();
//				System.out.println("All classes banned...");
//				break;
			}
			System.out.println();
			System.out.println("iteration = " + iter);
			int pickedClass = -1;

			pickedClass = this.pickMostViolatedClass(demand_OfACourse, supplyTeacher4Optimization, bannedClass);
			System.out.println("picked class = " + pickedClass);
			if (pickedClass == -1) {
				System.out.println("cannot pick class anymore ....");
				break;
			}

			// backing up current solution of picked class
			backUpTimeTable[pickedClass] = beingUsedTimeTable[pickedClass].position;
			int bestPosition = backUpTimeTable[pickedClass];
//			int bestRoomDemand = calRoomDemand(beingUsedTimeTable);
			int newLimit = timeTable_WareHouse[pickedClass].sol.length;
			int[] newDemand = new int[DA.nbCourse];
//			int bestTotalDemand = totalDemand;
			demand_OfACourse = calculateTeacherDemand(beingUsedTimeTable);
			totalDemand = sumOfAnArray(demand_OfACourse);
			int[] ss = findSessionAllClass(beingUsedTimeTable);
			demand_Department = calculateDepartmentTeacherDemand(beingUsedTimeTable);
//			int oldTotalDepartmentDemand = sumOfAnArray(demand_Department);
			int next = -1;
			int diff = 0;
			do {
//				int newTotalDemand = totalDemand;
//				int currentRoomDemand = 0;
				int bestDiff = Math.abs(ss[0]-ss[1]);
				nBViolatedDepartment = nbViolatedElement(demand_Department, supply_Department);
				
				next = 0;
				do {
					if (next != backUpTimeTable[pickedClass]) {
						// lay solution tai next
						beingUsedTimeTable[pickedClass] = timeTable_WareHouse[pickedClass].sol[next];
						setValuePropagage(pickedClass, timeTable_WareHouse[pickedClass].sol[next]);
						// getAssignDelta(pickedClass,
						// timeTable_WareHouse[pickedClass].sol[next]);
						int newViolation = S.violations();

						// check if feasible
						if (newViolation <= base_violation) {
							// tinh new demand, new total demand
							newDemand = calculateTeacherDemand(beingUsedTimeTable);
							int[] newDepartmentDemand = calculateDepartmentTeacherDemand(beingUsedTimeTable);
//							newTotalDemand = sumOfAnArray(newDemand);
							int newNBViolatedCourse = nbViolatedElement(newDemand, supplyTeacher4Optimization);
							int newNBViolatedDepartment = nbViolatedElement(newDepartmentDemand, supply_Department);
//							int[] newDepartmentDemand = calculateDepartmentTeacherDemand(beingUsedTimeTable);
//							int newTotalDepartmentDemand = sumOfAnArray(newDepartmentDemand);
							// tinh lai room demand, neu vuot qua limit thi ko
							// lay this solution
//							currentRoomDemand = calRoomDemand(beingUsedTimeTable);
							int[] ss1 = findSessionAllClass(beingUsedTimeTable);
							int currentDiff = Math.abs(ss1[0]-ss1[1]);

							if (
									newNBViolatedCourse < nbViolatedCourse
									&& newNBViolatedDepartment < nBViolatedDepartment
									//&& newTotalDepartmentDemand <= oldTotalDepartmentDemand
//									&& newTotalDemand < bestTotalDemand 
//									&& currentRoomDemand < bestRoomDemand + diff
									&& currentDiff < bestDiff + diff
									) {
								nbViolatedCourse = newNBViolatedCourse;
								nBViolatedDepartment = newNBViolatedDepartment;
//								oldTotalDepartmentDemand = newTotalDepartmentDemand;
								base_violation = newViolation;
								bestPosition = next;
//								bestTotalDemand = newTotalDemand;
								bestDiff = currentDiff;
//								bestRoomDemand = currentRoomDemand;
//								System.out.println(diff + "th chance");
//								System.out.println("room demand = " + currentRoomDemand);
							}

						}
					}
					next++;
				} while (next < newLimit);
				diff++;
			} while (diff <= LIMIT_SESSIONALLOWANCE 
					&& bestPosition == backUpTimeTable[pickedClass]);

			// neu position best == backup -> ban this class
			// else: unban all
			if (bestPosition == backUpTimeTable[pickedClass]) { // better
																	// solution
																	// not
																	// found.

				bannedClass.add(pickedClass);
				System.out.println("class " + pickedClass + " has been banned");
				System.out.println("total " + bannedClass.size() + " classes banned...");

				int oldPosition = backUpTimeTable[pickedClass];
				beingUsedTimeTable[pickedClass] = timeTable_WareHouse[pickedClass].sol[oldPosition];
				setValuePropagage(pickedClass, timeTable_WareHouse[pickedClass].sol[oldPosition]);

				System.out.println("best violation = " + S.violations());

			} else { // better solution found.
				// System.out.println("Better violation =
				// "+getAssignDelta(pickedClass,
				// timeTable_WareHouse[pickedClass].sol[positionOfBest]));
				System.out.println("Better violation =" + S.violations());
				setValuePropagage(pickedClass, timeTable_WareHouse[pickedClass].sol[bestPosition]);
				beingUsedTimeTable[pickedClass] = timeTable_WareHouse[pickedClass].sol[bestPosition];
				backUpTimeTable[pickedClass] = bestPosition;
				System.out.println("best violation = " + S.violations());
				bannedClass.add(pickedClass);
				if (bannedClass.size() > LIMIT_NBCLASS_INBANNEDLIST) {
					bannedClass.clear();
					System.out.println("Unbanned all...");
				}
			}

			// ------------------------------------------------------------------------
			demand_OfACourse = calculateTeacherDemand(beingUsedTimeTable);
			nbViolatedCourse = nbViolatedElement(demand_OfACourse, supplyTeacher4Optimization);
			iter++;
		} while (iter < NB_ITERATION 
//				&& totalDemand > totalSupply
				&& nbViolatedCourse > 0
				&& nBViolatedDepartment > 0
				);
		System.out.println("Final violation = " + S.violations());
		System.out.println("Done finding...");
		System.out.println("\nResult:.....");

		demand_OfACourse = calculateTeacherDemand(beingUsedTimeTable);
		totalDemand = sumOfAnArray(demand_OfACourse);
		System.out.println("total demand = " + totalDemand);
		System.out.println("total supply = " + sumOfAnArray(supplyTeacher4Optimization));
		for (int c = 0; c < DA.nbCourse; c++) {
			System.out.println("ID = " + DA.courses[c].ID + ", code = " + DA.courses[c].code + ", demand = "
					+ demand_OfACourse[c] + ", supply = " + supplyTeacher4Optimization[c] + ", margin = "
					+ (demand_OfACourse[c] - supplyTeacher4Optimization[c]));
		}

		nbRoom = calRoomDemand(beingUsedTimeTable);
		System.out.println("nbroom needed = " + nbRoom);
	}

	/**Find number of violated course, i.e. courses have demand > supply.*/
	public int nbViolatedElement(int[] demand, int[] supply){
		int sum = 0;
		for (int i = 0; i < supply.length; i++) {
			if (demand[i]-supply[i] > 0) {
				sum++;
			}
		}		
		return sum;
	}
	
	//session balance
		/**Find session. Return 1 if afternoon, 0 if morning and -1 as defaut.*/
		public int findSession(SingleSolution sol){
			int session = -1;
			for (int day = 0; day < DA.nbDays_20; day++) {
				for (int slot = 0; slot < DA.nbSlotsPerHalfDay*2; slot++) {
					if (sol.T[slot][day] >= 0) {
						if (slot <= SLOT_3) {
							return 0;
						}else{
							return 1;
						}					
					}
				}
			}		
			return session;
		}
		
		public int[] findSessionAllClass(SingleSolution[] timetable){
			int[] session = new int[2];
			int sang = 0, chieu = 0;
			boolean[] ok = new boolean[DA.nbClass];
			for (int cls = 0; cls < DA.nbClass; cls++) {
				for (int day = 0; day < DA.nbDays_20; day++) {
					if (ok[cls]) {
						break;
					}
					for (int slot = 0; slot < DA.nbSlotsPerHalfDay*2; slot++) {
						if (timetable[cls].T[slot][day] >= 0) {
							if (slot <= SLOT_3) {
								sang++;
							}else{
								chieu++;
							}		
							ok[cls] = true;
							break;
						}
					}
				}	
			}
			session[0] = sang;
			session[1] = chieu;
			return session;
		}
	
	public void printTimeTableAllClass(String fn, SingleSolution[] sol) {
		PrintWriter out = null;
		try {
			out = new PrintWriter(fn);
			for (int icl = 0; icl < DA.nbClass; icl++) {
				printOneHTML(sol[icl], out, icl);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.close();
	}

	public void printSingleSolutionHTML(SingleSolution sol, String fn) {
		PrintWriter out = null;
		try {
			out = new PrintWriter(fn);
			out.println("position = " + sol.position);
			out.println("<table border = 1>");
			for (int i = 0; i < 2 * DA.nbSlotsPerHalfDay; i++) {
				out.print("<tr>");
				for (int j = 0; j < DA.nbDays_20; j++) {
					out.print("<td height = 20 width = 20 ");
					String str = "";
					/*
					 * if (s[i][j] >= 0){ str = "M" + s[i][j]; ClassCourse c =
					 * D.mID2Course.get(s[i][j]);
					 */
					if (sol.T[i][j] >= 0) {
						str = "M" + sol.T[i][j];
						ClassCourse cc = DA.mID2ClassCourse.get(sol.T[i][j]);
						Course c = DA.mClassCourse2Course.get(cc);
						int idx = c.ID;
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
					}
					out.println("</td>");
					// out.print("<td height = 20 width = 20 bgcolor='green'>" +
					// str + "</td>\n");
				}
				out.println("</tr>");
			}
			out.println("</table>");
			out.println("-----------------------\n");
			out.println();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		out.close();
	}

	public void printOneHTML(SingleSolution sol, PrintWriter out, int clIdx) {

		// try {
		out.println("solution position = " + sol.position);
		// out.println("class index = " + clIdx);
		out.println(", class = " + DA.classes[clIdx].code);
		out.println("<table border = 1>");
		for (int i = 0; i < 2 * DA.nbSlotsPerHalfDay; i++) {
			out.print("<tr>");
			for (int j = 0; j < DA.nbDays_20; j++) {
				out.print("<td height = 20 width = 80 ");
				String str = "";
				/*
				 * if (s[i][j] >= 0){ str = "M" + s[i][j]; ClassCourse c =
				 * D.mID2Course.get(s[i][j]);
				 */
				if (sol.T[i][j] >= 0) {
					// str = "M" + sol.T[i][j];
					// str = DA.mID2Code.get() sol.T[i][j];
					// ClassCourse c = DA.mID2ClassCourse_all.get(sol.T[i][j]);
					// Course c
					ClassCourse cc = DA.mID2ClassCourse.get(sol.T[i][j]);
					Course c = DA.mClassCourse2Course.get(cc);
					// int id = c.ID;
					// str = DA.mClassID2Code.get(c.ID);
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
				}
				out.println("</td>");
				// out.print("<td height = 20 width = 20 bgcolor='green'>" +
				// str + "</td>\n");
			}
			out.println("</tr>");
		}
		out.println("</table>");
		// out.println("-----------------------\n");
		out.println();

		// } catch (Exception ex) {
		// ex.printStackTrace();
		// }
		//
		// out.close();
	}

	public void loadData_supply_Teacher(String fn) {
		try {
			Scanner in = new Scanner(new File(fn));
			String line = "";
			line = in.nextLine();

			int nbcourse = in.nextInt();
			System.out.println(line + ": " + nbcourse);
			line = in.nextLine();
			line = in.nextLine();

			if (nbcourse != DA.nbCourse) {
				System.out.println("BUB in supply teacher.........");
				System.out.println("nbCourse in supply = " + nbcourse);
				System.out.println("nbCourse in demand = " + DA.nbCourse);
			} else {
				supplyTeacher4Optimization = new int[DA.nbCourse];
				int idx = 0;
				while (true) {
					int cID = in.nextInt();
					if (cID == -1 || idx > DA.nbCourse) {
						break;
					}
					int sup = in.nextInt();
					System.out.println("course = " + cID + ", supply = " + sup);
					Course c = DA.mID2Course.get(cID);
					int cIdx = DA.mCourse2Index.get(c);
					if (sup > LIMIT_DEMANDTEACHER_PERCOURSE) {
						supplyTeacher4Optimization[cIdx] = LIMIT_DEMANDTEACHER_PERCOURSE;
					} else {
						supplyTeacher4Optimization[cIdx] = sup;
					}

					idx++;
				}
			}

			in.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("Done loading teacher supply data!");
	}

	public int[][] buildConflictMatrix(SingleSolution[] sol) {
		int[][] conflict = new int[DA.nbClassCourse][DA.nbClassCourse];

		for (int slot = 0; slot < DA.nbSlotsPerHalfDay * 2; slot++) {
			for (int day = 0; day < DA.nbDays_20; day++) {
				ArrayList<Integer> ccList = new ArrayList<Integer>();
				for (int cls = 0; cls < DA.nbClass; cls++) {
					int classCourseID = sol[cls].T[slot][day];
					if (classCourseID != -1) {
						ClassCourse cc = DA.mID2ClassCourse.get(classCourseID);
						int ccIdx = DA.mClassCourse2Index.get(cc);
						ccList.add(ccIdx);
					}
				}
				for (int i = 0; i < ccList.size() - 1; i++) {
					for (int j = i + 1; j < ccList.size(); j++) {
						int c1 = ccList.get(i);
						int c2 = ccList.get(j);
						conflict[c1][c2] = 1;
						conflict[c2][c1] = 1;
					}
				} // class
			} // slot
		} // day

		// print conflict pairs
		/*
		 * System.out.println("\nConflict list: "); for (int c1 = 0; c1 <
		 * DA.nbClassCourse-1; c1++) { for (int c2 = c1+1; c2 <
		 * DA.nbClassCourse; c2++) { ClassCourse cc1 = DA.classCourses[c1];
		 * ClassCourse cc2 = DA.classCourses[c2]; ClassFU cls1 =
		 * DA.mClassCourse2Class.get(cc1); ClassFU cls2 =
		 * DA.mClassCourse2Class.get(cc2); if (conflict[c1][c2]==1) { //
		 * System.out.println("conflict: <"
		 * +c1+"-"+cc1.code+"-"+cls1.code+","+c2+"-"+cc2.code+"-"+cls2.code+">")
		 * ; System.out.println("conflict: <"
		 * +c1+"-"+c2+","+cc1.code+"-"+cc2.code+","+cls1.code+"-"+cls2.code+">")
		 * ; } } }
		 */

		return conflict;
	}

	public void writeMatrix2File(String fn, SingleSolution[] sol) {
		int[][] conflict = new int[DA.nbClassCourse][DA.nbClassCourse];
		conflict = buildConflictMatrix(sol);
		try {
			File file = new File(fn);

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			for (int i = 0; i < DA.nbClassCourse; i++) {
				for (int j = 0; j < DA.nbClassCourse; j++) {
					bw.write(conflict[i][j] + " ");
				}
				bw.write("\n");
			}

			bw.close();

			System.out.println("Done writing conflict matrix to file!");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int calRoomDemand(SingleSolution[] sol) {
		int maxDemand = 0;
		// int maxAtSlot = -1;
		// int maxAtDay = -1;
		for (int day = 0; day < DA.nbDays_20; day++) {
			for (int slot = 0; slot < DA.nbSlotsPerHalfDay * 2; slot++) {
				int demand = 0;
				for (int cl = 0; cl < DA.nbClass; cl++) {
					int ccID = sol[cl].T[slot][day];
					if (ccID != -1) {
						demand++;
					}
				}
				//
				if (demand > maxDemand) {
					maxDemand = demand;
					// maxAtSlot = slot;
					// maxAtDay = day;
				}
			}
			//
		}
		return maxDemand;
	}

	public void calRoomDemandEverySlot(SingleSolution[] sol) {
		for (int day = 0; day < DA.nbDays_20; day++) {
			for (int slot = 0; slot < DA.nbSlotsPerHalfDay * 2; slot++) {
				int demand = 0;
				for (int cl = 0; cl < DA.nbClass; cl++) {
					int ccID = sol[cl].T[slot][day];
					if (ccID != -1) {
						demand++;
					}
				}
				//
				// System.out.println("d = "+day+", s = "+slot+": "+demand);
				System.out.println(demand);
			}
			//
		}
	}

	public boolean isTimeTableCorrect(String fn_conflictMatrix) {
		int[][] conflict = new int[DA.nbClassCourse][DA.nbClassCourse];
		conflict = loadConflictMatrix(fn_conflictMatrix);

		for (int c1 = 0; c1 < DA.nbClassCourse; c1++) {
			for (int c2 = 0; c2 < DA.nbClassCourse; c2++) {
				if (DA.mustNotConflictMatrix == null) {
					System.out.println("mustNotConflictMatrix is null");
					System.exit(1);
				}
				ClassCourse cc1 = DA.classCourses[c1];
				ClassCourse cc2 = DA.classCourses[c2];
				if (cc1 != cc2 && DA.mustNotConflictMatrix[c1][c2] == 1 && conflict[c1][c2] == 1) {
					ClassFU cls1 = DA.mClassCourse2Class.get(cc1);
					ClassFU cls2 = DA.mClassCourse2Class.get(cc2);

					System.out.println(cc1.code + "-" + cls1.code + ", " + cc2.code + "-" + cls2.code);
					return false;
				}
			}
		}

		return true;
	}

	public int[][] loadConflictMatrix(String fn) {
		int nbc = DA.nbClassCourse;
		int[][] conflict1 = new int[nbc][nbc];
		try {
			Scanner in = new Scanner(new File(fn));
			// String line = in.nextLine();
			// System.out.println(line);
			int stt = -1;
			for (int i = 0; i < nbc; i++) {
				for (int j = 0; j < nbc; j++) {
					stt = in.nextInt();
					conflict1[i][j] = stt;
				}
			}

			/*
			 * for (int i = 0; i < nbc; i++) { for (int j = 0; j < nbc; j++) {
			 * System.out.print(" "+conflict[i][j]); if (conflict[i][j]==-1) {
			 * System.out.println("BUG"); System.exit(1);; } }
			 * System.out.println(); }
			 */
			in.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("Done loading conflict matrix!");
		return conflict1;
	}

	/** find some data (day, block, session) to calculate scoress */
	public void findBlockSlotSessionOfClassCourse(SingleSolution[] sol) {
		// tìm block, session, ngày chẵn/lẽ của cc
		DA.aClassCourse2Block = new int[DA.nbClassCourse];
		DA.aClassCourse2Slot = new int[DA.nbClassCourse];
		// DA.aClassCourse2Day = new int[DA.nbClassCourse];
		DA.aClassCourse2Session = new int[DA.nbClassCourse];
		// DA.aClassCourse2Noon = new int[DA.nbClassCourse];
		// DA.aClassCourse2BeginEndDay = new int[DA.nbClassCourse];

		for (int cl = 0; cl < DA.nbClass; cl++) {
			ClassFU cls = DA.classes[cl];
			ArrayList<ClassCourse> L = DA.mClass2ClassCourseList.get(cls);
			// tìm set day cho các class-course:
			// tìm session + noon cho từng class-course.
			// tim block, ngày chẵn/lẻ cho tung class-course:
			for (int d = 0; d < DA.nbDays_20; d++) {
				for (int sl = 0; sl < DA.nbSlotsPerHalfDay * 2; sl++) {
					if (sol[cl].T[sl][d] >= 0) {
						for (ClassCourse cc : L) {
							if (sol[cl].T[sl][d] == cc.ID) {
								int ccIdx = DA.mClassCourse2Index.get(cc);
								DA.aClassCourse2Slot[ccIdx] = sl;
								if (sl > SLOT_3) {
									DA.aClassCourse2Session[ccIdx] = AFTERNOON;
								} else {
									DA.aClassCourse2Session[ccIdx] = MORNING;
								}
								switch (d) {
								case 0:
									// DA.aClassCourse2Day[ccIdx] = 0;
									if (cc.stt == 3) {
										DA.aClassCourse2Block[ccIdx] = BLOCK_2;
									} else {
										DA.aClassCourse2Block[ccIdx] = BLOCK_1;
									}

									break;
								case 1:
									// DA.aClassCourse2Day[ccIdx] = 1;
									if (cc.stt == 3) {
										DA.aClassCourse2Block[ccIdx] = BLOCK_2;
									} else {
										DA.aClassCourse2Block[ccIdx] = BLOCK_1;
									}
									break;
								case 10:
									// DA.aClassCourse2Day[ccIdx] = 0;
									DA.aClassCourse2Block[ccIdx] = BLOCK_2;
									break;
								case 11:
									// DA.aClassCourse2Day[ccIdx] = 1;
									DA.aClassCourse2Block[ccIdx] = BLOCK_2;
									break;
								}
								// chi lay ngay dau tien
								break;
							}
						}
					}
				}
			}

		}
		//
		int idx = -1;
		for (ClassCourse cc : DA.classCourses) {
			idx++;
			System.out.println(cc.code + ": block = " + DA.aClassCourse2Block[idx] + ", slot = "
					+ DA.aClassCourse2Slot[idx] + ", session = " + DA.aClassCourse2Session[idx]);
		}

	}

	////////////////////////////////////////////////////////////////////////////////
	// POST IMPROVEMENT
	public void initPostImprovement() {
		DA.makePlace();
		DA.buildMapPlace2DaySlotList();
		DA.makeCoupleCoursePlace();
		DA.mCouple2ClassCourseList = new HashMap<>(); // L
		for (CoupleCoursePlace cp : DA.lcoupleCoursePlaceList) {
			DA.mCouple2ClassCourseList.put(cp, new ArrayList<>());
		}
		DA.mCourse2LStar = new HashMap<>(); // LStar
		for (Course c : DA.courses) {
			DA.mCourse2LStar.put(c, new ArrayList<>());
		}

		DA.mCouple2FEachPlace = new HashMap<>(); // F
		for (CoupleCoursePlace cp : DA.lcoupleCoursePlaceList) {
			DA.mCouple2FEachPlace.put(cp, 0);
		}

		DA.mCourse2FStar = new HashMap<>(); // fstar
		for (Course c : DA.courses) {
			DA.mCourse2FStar.put(c, 0);
		}
		DA.mClassCourse2Place = new HashMap<>();

		DA.mCourse2AllLStar = new HashMap<>();
		for (Course c : DA.courses) {
			DA.mCourse2AllLStar.put(c, new ArrayList<>());
		}

	}

	@SuppressWarnings("unchecked")
	public void make_L_LStar_F_FStar(SingleSolution[] timetable) {
		for (CoupleCoursePlace cp : DA.lcoupleCoursePlaceList) {
			if (cp == null) {
				System.out.println("cp");
				System.exit(1);
			}
			Course c = cp.c;
			Place p = cp.p;
			int block = p.block;
			// int session = p.session;
			int slot = p.slot;

			ArrayList<ClassCourse> L = DA.mCourse2ClassCourseList.get(c);
			boolean ok = true;
			for (ClassCourse cc : L) {
				if (cc.stt == 3) {
					ok = false;
				}
			}
			if (ok) {
				for (int cl = 0; cl < DA.nbClass; cl++) {
					SingleSolution sol = timetable[cl];
					int day = block * 10;
					if (sol == null) {
						System.out.println("not ok");
						System.exit(1);
					}
					int ccID = sol.T[slot][day];
					ClassCourse cc = DA.mID2ClassCourse.get(ccID);
					if (L.contains(cc)) {
						DA.mCouple2ClassCourseList.get(cp).add(cc);
						DA.mClassCourse2Place.put(cc, p);
					}
				}
			}
			//
		}
		int demand = 0;
		for (Course c : DA.courses) {
			ArrayList<CoupleCoursePlace> L = DA.mCourse2CoupleCPList.get(c);
			ArrayList<ClassCourse> ccL = DA.mCourse2ClassCourseList.get(c);
			if (L != null) {
				int count = 0;
				int max = -1;
				ArrayList<ClassCourse> lStarList = null;
				System.out.print(c.code + ": ");
				for (CoupleCoursePlace cp : L) {
					ArrayList<ClassCourse> LL = DA.mCouple2ClassCourseList.get(cp);
					System.out.print(" " + LL.size());
					count += LL.size();
					if (LL.size() > max) {
						max = LL.size();
						lStarList = LL;// (ArrayList<ClassCourse>) LL.clone();
					}
					DA.mCouple2FEachPlace.replace(cp, LL.size());
				}
				// all classcourse in all L*
				for (CoupleCoursePlace cp : L) {
					ArrayList<ClassCourse> LL = DA.mCouple2ClassCourseList.get(cp);
					if (LL.size() == max) {
						DA.mCourse2AllLStar.get(c).addAll(LL);
					}
				}

				DA.mCourse2LStar.replace(c, lStarList);
				DA.mCourse2FStar.replace(c, max);
				System.out.println(", " + (ccL.size() == count) + ", " + count + ", fstar = " + max + "-"
						+ DA.mCourse2FStar.get(c) + ", nbCC in all L* = " + DA.mCourse2AllLStar.get(c).size());
				demand += max;

			}
		}
		System.out.println("demand = " + demand);

		for (Course c : DA.courses) {
			System.out.print(c.code + ": ");
			ArrayList<ClassCourse> L = DA.mCourse2LStar.get(c);
			int fstar = DA.mCourse2FStar.get(c);
			System.out.print(fstar);
			for (ClassCourse cc : L) {
				ClassFU cls = DA.mClassCourse2Class.get(cc);
				System.out.print(" " + cc.code + "-" + cls.code);
			}
			System.out.println();
			// System.out.println();
		}
	}

	public void update_L_LStar_F_FStar(Course course, SingleSolution[] timetable) {
		// reset L* vs F*
		DA.mCourse2LStar.replace(course, new ArrayList<>());
		DA.mCourse2FStar.put(course, 0);

		// update
		ArrayList<CoupleCoursePlace> CPList = DA.mCourse2CoupleCPList.get(course);
		if (CPList == null) {
			return;
		}
		for (CoupleCoursePlace cp : CPList) {
			// reset F vs L
			DA.mCouple2ClassCourseList.replace(cp, new ArrayList<>());
			DA.mCouple2FEachPlace.replace(cp, 0);

			if (cp == null) {
				System.out.println("cp null");
				System.exit(1);
			}
			// Course c = cp.c;
			Place p = cp.p;
			int block = p.block;
			// int session = p.session;
			int slot = p.slot;

			ArrayList<ClassCourse> L = DA.mCourse2ClassCourseList.get(course);
			// check if course c doesnt contain a cc has stt = 3
			boolean ok = true;
			for (ClassCourse cc : L) {
				if (cc.stt == 3) {
					ok = false;
				}
			}
			if (ok) {
				for (int cl = 0; cl < DA.nbClass; cl++) {
					SingleSolution sol = timetable[cl];
					int day = block * 10;
					if (sol == null) {
						System.out.println("not ok");
						System.exit(1);
					}
					int ccID = sol.T[slot][day];
					ClassCourse cc = DA.mID2ClassCourse.get(ccID);
					if (L.contains(cc)) {
						DA.mCouple2ClassCourseList.get(cp).add(cc);
						DA.mClassCourse2Place.put(cc, p); // or replace
					}
				}
			}
			//
		}
		int demand = 0;
		// for (Course c : DA.courses) {
		ArrayList<CoupleCoursePlace> L = DA.mCourse2CoupleCPList.get(course);
		ArrayList<ClassCourse> ccL = DA.mCourse2ClassCourseList.get(course);
		if (L != null) {
			int count = 0;
			int max = -1;
			ArrayList<ClassCourse> lStarList = null;
			System.out.print(course.code + ": ");
			for (CoupleCoursePlace cp : L) {
				ArrayList<ClassCourse> LL = DA.mCouple2ClassCourseList.get(cp);
				System.out.print(" " + LL.size());
				count += LL.size();
				if (LL.size() > max) {
					max = LL.size();
					lStarList = LL;// (ArrayList<ClassCourse>) LL.clone();
				}
				DA.mCouple2FEachPlace.replace(cp, LL.size());
			}
			// all classcourse in all L*
			DA.mCourse2AllLStar.get(course).clear(); // remove old place
			for (CoupleCoursePlace cp : L) {
				ArrayList<ClassCourse> LL = DA.mCouple2ClassCourseList.get(cp);
				if (LL.size() == max) {
					DA.mCourse2AllLStar.get(course).addAll(LL);
				}
			}

			DA.mCourse2LStar.replace(course, lStarList);
			DA.mCourse2FStar.replace(course, max);
			System.out.println(", " + ccL.size() + "-" + (ccL.size() == count) + ", " + count + ", fstar = " + max + "-"
					+ DA.mCourse2FStar.get(course) + ", nbCC in all L* = " + DA.mCourse2AllLStar.get(course).size());
			demand += max;
		}
		// }
		// System.out.println("demand = "+demand);

		// for (Course c : DA.courses) {
		System.out.print(course.code + ": ");
		ArrayList<ClassCourse> cccL = DA.mCourse2LStar.get(course);
		int fstar = DA.mCourse2FStar.get(course);
		System.out.print(fstar);
		for (ClassCourse cc : cccL) {
			ClassFU cls = DA.mClassCourse2Class.get(cc);
			System.out.print(" " + cc.code + "-" + cls.code);
		}
		System.out.println();
		// System.out.println();
		// }
	}

	public void testMove() {
		String fn = "data_beingusedTT_Temp1_full_200.dat";
		// TA.savebeingUsedTimeTable(fn);
		SingleSolution[] ttb = loadbeingUsedTimeTable(fn);
		if (ttb[0] == null) {
			System.exit(1);
		}
		beingUsedTimeTable = ttb;
		printTimeTableAllClass("optimizedTTB_full2.html", ttb);
		// TA.writeMatrix2File("conflictm_full2.txt", TA.beingUsedTimeTable);
		// TA.findBlockSlotSessionOfClassCourse(sol);

		initPostImprovement();
		stateModelAllClass("fc.txt");
		for (int cl = 0; cl < DA.nbClass; cl++) {
			setValuePropagage(cl, ttb[cl]);
		}
		System.out.println("start violation = " + S.violations());
		// long startTime = System.currentTimeMillis();
		make_L_LStar_F_FStar(ttb);
		Course c = DA.mCode2Course.get("CSI101");
		System.out.println();
		update_L_LStar_F_FStar(c, ttb);
		ArrayList<ClassCourse> ccL = DA.mCourse2LStar.get(c);
		ArrayList<CoupleCoursePlace> CPList = DA.mCourse2CoupleCPList.get(c);
		ClassCourse cc = ccL.get(1);
		System.out.println();
		ClassFU cls = DA.mClassCourse2Class.get(cc);
		System.out.println(cc.code + "-" + cls.code);
		// System.out.println();
		Place p0 = DA.mClassCourse2Place.get(cc);
		Place p = DA.findPlace(0, 0, 1);
		Place p1 = DA.findPlace(1, 0, 2);
		Place p2 = DA.findPlace(0, 1, 3);
		Place p3 = DA.findPlace(1, 1, 4);
		System.out.println(isFeasibleToMove(cc, p1, ttb));
		System.out.println("ss = " + p0.session + ", bl = " + p0.block + ", slot = " + p0.slot);
		move(cc, p0, p1, ttb);
		Place pp = DA.mClassCourse2Place.get(cc);
		System.out.println("ss = " + pp.session + ", bl = " + pp.block + ", slot = " + pp.slot);

		System.out.println("first move violation = " + S.violations());
		update_L_LStar_F_FStar(c, ttb);

		/*
		 * ccL = DA.mCourse2LStar.get(DA.courses[40]); CPList =
		 * DA.mCourse2CoupleCPList.get(DA.courses[40]);
		 * 
		 * cc = ccL.get(2);
		 * System.out.println(DA.mClassCourse2Class.get(cc).code); p0 =
		 * DA.mClassCourse2Place.get(cc); //remember to update p0
		 * System.out.println(isFeasibleToMove(cc, p3, ttb));
		 * System.out.println("ss = "+p0.session+", bl = "+p0.block+", slot = "
		 * +p0.slot); move(cc, p0, p3, ttb); pp = DA.mClassCourse2Place.get(cc);
		 * System.out.println("ss = "+pp.session+", bl = "+pp.block+", slot = "
		 * +pp.slot); update_L_LStar_F_FStar(DA.courses[40], ttb);
		 */

		ccL = DA.mCourse2LStar.get(c);
		CPList = DA.mCourse2CoupleCPList.get(c);
		cc = ccL.get(1);
		pp = DA.mClassCourse2Place.get(cc);
		Place p4 = DA.findPlace(1, 0, 1);
		// Place p5 = DA.findPlace(0, 1, 3);
		System.out.println(isFeasibleToMove(cc, p4, ttb));
		System.out.println("ss = " + pp.session + ", bl = " + pp.block + ", slot = " + pp.slot);
		move(cc, pp, p4, ttb);
		System.out.println("ss = " + p4.session + ", bl = " + p4.block + ", slot = " + p4.slot);
		System.out.println("first move violation = " + S.violations());
		update_L_LStar_F_FStar(c, ttb);

		System.out.println("second move violation = " + S.violations());
		printTimeTableAllClass("optimizedTTB_full2_moved.html", ttb);

		// System.out.println(p4 == p5);

		// long estimatedTime = System.currentTimeMillis() - startTime;
		// System.out.println("Time-consuming: "+estimatedTime/1000+" secs");
	}

	public void move(ClassCourse cc, Place p0, Place p, SingleSolution[] timetable) {
		ArrayList<DaySlot> dsList0 = DA.mPlace2DaySlotList.get(p0);
		ArrayList<DaySlot> dsList1 = DA.mPlace2DaySlotList.get(p);
		ArrayList<VarIntLS> xdayList = mClassCourse2x_dayList.get(cc);
		ArrayList<VarIntLS> xslotList = mClassCourse2x_slotList.get(cc);

		ClassFU cls = DA.mClassCourse2Class.get(cc);
		int clsIdx = DA.mClass2Index.get(cls);
		SingleSolution sol = timetable[clsIdx];

		// move
		int idx = -1;
		for (DaySlot ds : dsList1) {
			idx++;
			int day = ds.day;
			int slot = ds.slot;
			sol.T[slot][day] = cc.ID;
			xdayList.get(idx).setValuePropagate(day);
			xslotList.get(idx).setValuePropagate(slot);
		}
		// erase
		idx = -1;
		for (DaySlot ds : dsList0) {
			idx++;
			int day = ds.day;
			int slot = ds.slot;
			sol.T[slot][day] = -1;
		}
		DA.mClassCourse2Place.put(cc, p);

	}

	public boolean isFeasibleToMove(ClassCourse cc, Place p, SingleSolution[] timetable) {
		// ArrayList<DaySlot> dsList0 = DA.mPlace2DaySlotList.get(p0);
		ArrayList<DaySlot> dsList1 = DA.mPlace2DaySlotList.get(p);
		ArrayList<VarIntLS> xdayList = mClassCourse2x_dayList.get(cc);
		ArrayList<VarIntLS> xslotList = mClassCourse2x_slotList.get(cc);

		for (DaySlot ds : dsList1) {
			int index = dsList1.indexOf(ds);
			int day = ds.day;
			int slot = ds.slot;
			VarIntLS xday = xdayList.get(index);
			VarIntLS xslot = xslotList.get(index);
			xday.setValuePropagate(day);
			xslot.setValuePropagate(slot);
		}
		if (S.violations() > 0) {
			return false;
		} else {
			for (VarIntLS x : xslotList) {
				x.setValuePropagate(x.getOldValue());
			}
			for (VarIntLS x : xdayList) {
				x.setValuePropagate(x.getOldValue());
			}
		}

		return true;
	}

	public void postImprovement(String fn_timetable) {
		long startTime = System.currentTimeMillis();
		// String fn = "data_beingusedTT_Temp1_full.dat";
		SingleSolution[] ttb = loadbeingUsedTimeTable(fn_timetable);
		if (ttb[0] == null) {
			System.exit(1);
		}
		beingUsedTimeTable = ttb;
		printTimeTableAllClass("optimizedTTB_full2.html", ttb);

		initPostImprovement();
		stateModelAllClass2("fc.txt");
		for (int cl = 0; cl < DA.nbClass; cl++) {
			setValuePropagage(cl, ttb[cl]);
		}
		System.out.println("start violation = " + S.violations());
		make_L_LStar_F_FStar(ttb);

		// -----------------------------------------------------------
		System.out.println();
		for (Course c : DA.courses) {
			int fStar = DA.mCourse2FStar.get(c);
			if (fStar > 1) {
				int iter = -1;
				do {
					iter++;
					// get all L*
					ArrayList<ClassCourse> allLStar = DA.mCourse2AllLStar.get(c);
					/*
					 * System.out.print(c.code+": F* = "+fStar+
					 * ", all cc in L*: "); for (ClassCourse cc : allLStar) {
					 * ClassFU cls = DA.mClassCourse2Class.get(cc);
					 * System.out.print(" "+cls.code);
					 * 
					 * } System.out.println();
					 */
					// improve
					ArrayList<ClassCourse> allLStar2 = (ArrayList<ClassCourse>) allLStar.clone();
					for (ClassCourse cc : allLStar2) {
						ClassFU cls = DA.mClassCourse2Class.get(cc);
						// System.out.println("picked cc =
						// "+cc.code+"-"+cls.code);

						Place p0 = DA.mClassCourse2Place.get(cc);
						if (S.violations() > 0) {
							System.out.println(cls.code + "-" + c.code + "-" + p0);
							System.exit(1);
						}
						int oldFStar = DA.mCourse2FStar.get(c);
						// find a new place to move
						boolean ok = false;
						for (Place p : DA.places) {
							CoupleCoursePlace cp = DA.findCouple(c, p);
							int fp = DA.mCouple2FEachPlace.get(cp);
							if (p != p0 && p0.session == p.session && fp < oldFStar - 1
									&& isFeasibleToMove(cc, p, ttb)) {
								System.out.println(cls.code + "-" + cc.code + ": " + oldFStar + " vs " + fp);
								if (p0.session != p.session) {
									System.out.println("khac session");
								}
								// move
								move(cc, p0, p, ttb);
								// update all
								update_L_LStar_F_FStar(c, ttb);
								// int newFStar = DA.mCourse2FStar.get(c);
								// System.out.println(", new f* = "+newFStar);
								ok = true;
								break;
							} else {
								// System.out.println("new place not found.");
							}
						}
						//
						if (ok) {
							break;
						}
					}
				} while (iter < 20);
			}
			//

		}

		// -----------------------------------------------------------
		// String fn = "data_beingusedTT_Temp1_full.dat";
		SingleSolution[] timetable = loadbeingUsedTimeTable(fn_timetable);
		System.out.println();
		int[] demand = calculateTeacherDemand(ttb);
		int[] odemand = calculateTeacherDemand(timetable);
		int sum = 0;
		for (Course c : DA.courses) {
			int idx = DA.mCourse2Index.get(c);
			System.out
					.println(c.code + ": " + odemand[idx] + " vs " + demand[idx] + ", " + (odemand[idx] - demand[idx]));

		}

		int ssum = sumOfAnArray(demand);
		sum = sumOfAnArray(odemand);
		System.out.println("total demand = " + ssum + "-" + sum);
		printTimeTableAllClass("optimizedTTB_full2_moved.html", ttb);
		System.out.println("final violation = " + S.violations());
		long estimatedTime = System.currentTimeMillis() - startTime;
		System.out.println("Time-consuming: " + estimatedTime / 1000 + " secs");
	}

	public void modelling_ttb_manual() {
		DA.makeMustNotConflictClassCourseList();
		DA.makeMustNotConflictMatrix();
		String fn = "datasm/variable.txt";
		genData_Fragment(fn);
		ls = new LocalSearchManager();
		int nbFC = DA.nbClassCourse * NB_FRAGMENT_PERCOURSE;

		x_day = new VarIntLS[DA.nbClassCourse * NB_FRAGMENT_PERCOURSE];
		x_slot = new VarIntLS[DA.nbClassCourse * NB_FRAGMENT_PERCOURSE];

		loadData_FragmentANDInitializeVariables(fn);
		S = new ConstraintSystem(ls);

		// state model
		for (int fci = 0; fci < nbFC - 1; fci++) {
			for (int fcj = fci + 1; fcj < nbFC; fcj++) {
				ClassCourse cc1 = mXDay2ClassCourse.get(x_day[fci]);
				ClassCourse cc2 = mXDay2ClassCourse.get(x_day[fcj]);
				ClassFU cls1 = DA.mClassCourse2Class.get(cc1);
				ClassFU cls2 = DA.mClassCourse2Class.get(cc2);
				int cc1Idx = DA.mClassCourse2Index.get(cc1);
				int cc2Idx = DA.mClassCourse2Index.get(cc2);
				if (DA.mustNotConflictMatrix[cc1Idx][cc2Idx] == 1 || cls1 == cls2) {
					// if (DA.mustNotConflictMatrix[cc1Idx][cc2Idx] == 1) {
					// if(cls1 == cls2){
					IConstraint ic1 = new IsEqual(x_day[fci], x_day[fcj]);
					IConstraint ic2 = new NotEqual(x_slot[fci], x_slot[fcj]);
					S.post(new Implicate(ic1, ic2));
				}
			}
		}

		// ---------------------------------------------------------------------
		ls.close();

		System.out.println("\nViolation after stating model: " + S.violations());

		// set value
		DA.loadData_ClassCourse_DaySlot_temp2("datasm/data_ttb_sm.txt");
		for (ClassCourse cc : DA.classCourses) {
			ArrayList<VarIntLS> dayList = mClassCourse2x_dayList.get(cc);
			ArrayList<VarIntLS> slotList = mClassCourse2x_slotList.get(cc);
			ArrayList<DaySlot> dsList = DA.mClassCourse2DaySlotList_manual.get(cc);
			if (dayList.size() != dsList.size() || slotList.size() != dsList.size()) {
				System.out.println("Problem in modeling manual");
				System.exit(1);
			}
			int idx = -1;
			for (DaySlot daySlot : dsList) {
				idx++;
				int d = daySlot.day;
				int s = daySlot.slot;
				dayList.get(idx).setValuePropagate(d);
				slotList.get(idx).setValuePropagate(s);
			}
		}

		// validation
		System.out.println("Violation after modeling manual_ttb: " + S.violations());
		int count = 0;
		for (VarIntLS xday : x_day) {
			ClassCourse cc = mXDay2ClassCourse.get(xday);
			ClassFU cls = DA.mClassCourse2Class.get(cc);
			if (S.violations(xday) > 0) {
				System.out.println("Violated day: " + cls.code + "-" + cc.code + ", d = " + xday.getValue());
				count++;
			}
		}
		for (VarIntLS xslot : x_slot) {
			ClassCourse cc = mXSlot2ClassCourse.get(xslot);
			ClassFU cls = DA.mClassCourse2Class.get(cc);
			if (S.violations(xslot) > 0) {
				System.out.println("Violated slot: " + cls.code + "-" + cc.code + ", s = " + xslot.getValue());
				count++;
			}
		}
		System.out.println("total " + count + " violated variables.");
	}

	public void buildTTB_manual() {
		DA.classTTB_manual = new SingleSolution[DA.nbClass];
		for (ClassFU cls : DA.classes) {
			int clIdx = DA.mClass2Index.get(cls);
			DA.classTTB_manual[clIdx] = new SingleSolution();
			DA.classTTB_manual[clIdx].T = new int[DA.nbSlotsPerHalfDay * 2][DA.nbDays_20];
			for (int slot = 0; slot < DA.nbSlotsPerHalfDay * 2; slot++) {
				for (int day = 0; day < DA.nbDays_20; day++) {
					DA.classTTB_manual[clIdx].T[slot][day] = -1;
				}
			}
			//
			ArrayList<ClassCourse> L = DA.mClass2ClassCourseList.get(cls);
			for (ClassCourse cc : L) {
				ArrayList<DaySlot> dsList = DA.mClassCourse2DaySlotList_manual.get(cc);
				for (DaySlot ds : dsList) {
					int day = ds.day;
					int slot = ds.slot;
					DA.classTTB_manual[clIdx].T[slot][day] = cc.ID;
				}
			}
		}
		//

		int[] demandEachCourse = calculateTeacherDemand(DA.classTTB_manual);
		for (Course c : DA.courses) {
			int cIdx = DA.mCourse2Index.get(c);
			int dm = demandEachCourse[cIdx];
			System.out.println(c.code + ", demand = " + dm);
		}
		int demand = sumOfAnArray(demandEachCourse);
		System.out.println("total demand (manual): " + demand);
	}

	public void calculateDemandAllCourse_manual() {

	}
	public void printOneClass(TimeTableAllClass TA, SingleSolution sol,
			HSSFSheet BL1,HSSFSheet BL2, int clIdx, int r,int c) {
		int c2=c;
		HSSFRow rowbl1 = BL1.createRow(r);
		HSSFRow rowbl12 = BL1.createRow(r+1);
		HSSFRow rowbl13 = BL1.createRow(r+2);
		HSSFRow rowbl14 = BL1.createRow(r+3);
		HSSFCell cell = rowbl1.createCell(c);	
		cell.setCellValue(TA.DA.classes[clIdx].code);
		c++;
		HSSFCell ses = rowbl1.createCell(c);
		HSSFCell ses2 = rowbl13.createCell(c);
		
		for(int i =0; i< 10;i++){
			String rc = "No room";
			int p=0;
			for (int j = 0; j < 2 * TA.DA.nbSlotsPerHalfDay; j++){
				String str = "";
				if (sol.T[j][i] >= 0) {
					
					ClassCourse cc = DA.mID2ClassCourse.get(sol.T[j][i]);
					Course course = DA.mClassCourse2Course.get(cc);
					if (DA.mClassCourse2AssignedRoom.get(cc) != null) {
						Room room = DA.mClassCourse2AssignedRoom.get(cc);
						/*if(room.code.equals("P201")||room.code.equals("P202")||room.code.equals("P203")){
							
						}else*/
						rc = room.code;
					}
					str = course.code;
					if (j<3) {
						p=c+3*i+j+1; 
						HSSFCell slot = rowbl1.createCell(p);
						HSSFCell rm = rowbl12.createCell(p);
						slot.setCellValue(str);
						rm.setCellValue(rc);
					}else {
						p=c+3*i+j+1-3;
						HSSFCell slot = rowbl13.createCell(p);
						HSSFCell rm = rowbl14.createCell(p);
						slot.setCellValue(str);
						rm.setCellValue(rc);
					}
					
					
				}
			}	
		}
			ses.setCellValue("Sang");
			ses2.setCellValue("Chieu");

		
		HSSFRow rowbl2 = BL2.createRow(r);
		HSSFRow rowbl22 = BL2.createRow(r+1);
		HSSFRow rowbl23= BL2.createRow(r+2);
		HSSFRow rowbl24 = BL2.createRow(r+3);
		cell = rowbl2.createCell(c2);	
		cell.setCellValue(TA.DA.classes[clIdx].code);
		c2++;
		int ss2=-1;
		ses = rowbl2.createCell(c2);
		ses2= rowbl23.createCell(c2);
		
		for(int i =10; i< 20;i++){
			String rc = "No room";
			int p=0;
			for (int j = 0; j < 2 * TA.DA.nbSlotsPerHalfDay; j++){
				String str = "";
				if (sol.T[j][i] >= 0) {

					ClassCourse cc = DA.mID2ClassCourse.get(sol.T[j][i]);
					Course course = DA.mClassCourse2Course.get(cc);
					if (DA.mClassCourse2AssignedRoom.get(cc) != null) {
						Room room = DA.mClassCourse2AssignedRoom.get(cc);
						rc = room.code;
					}
					str = course.code;
					if (j<3){
						 p=c2+3*(i-10)+j+1;
						HSSFCell slot = rowbl2.createCell(p);
						HSSFCell rm = rowbl22.createCell(p);
						slot.setCellValue(str);
						rm.setCellValue(rc);
					}else {
						p=c2+3*(i-10)+j+1-3;
						HSSFCell slot = rowbl23.createCell(p);
						HSSFCell rm = rowbl24.createCell(p);
						slot.setCellValue(str);
						rm.setCellValue(rc);
					}
					
				}
			}	
		}
			ses.setCellValue("Sang");
			ses2.setCellValue("Chieu");
	}

	public void PoiWriteExcelFile(TimeTableAllClass TA, String fn,
			SingleSolution[] sol) {
		try {
			FileOutputStream fileOut = new FileOutputStream(fn);
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet BL1 = workbook.createSheet("Block 1");
			HSSFSheet BL2 = workbook.createSheet("Block 2");
			
			int r = 8;
			int c =2;
			

			for (int icl = 0; icl < DA.nbClass; icl++) {
				printOneClass(TA, sol[icl], BL1,BL2, icl, r,c);
				r = r+4;
			}
			
			HSSFSheet dm = workbook.createSheet("Demand teacher");
			HSSFRow row = dm.createRow(0);
			HSSFCell cell = row.createCell(0);
			cell.setCellValue("No.");
			cell = row.createCell(1);
			cell.setCellValue("Course");
			cell = row.createCell(2);
//			demand_OfACourse = new int[DA.nbCourse];
//			demand_OfACourse = calculateTeacherDemand(beingUsedTimeTable);
			cell.setCellValue("So giao vien toi thieu");
			for (int i = 0; i < DA.nbCourse; i++) {
				Course course = DA.courses[i];
				int demand = demand_OfACourse[i];
				String code = course.code;
				row= dm.createRow(i+1);
				cell=row.createCell(0);
				cell.setCellValue(i+1);
				cell=row.createCell(1);
				cell.setCellValue(code);
				cell=row.createCell(2);
				cell.setCellValue(demand);
			}

			//
			HSSFSheet viewByCourse = workbook.createSheet("View by Course");
			for(int i=1;i<=DA.nbCourse*6;i++){
				HSSFRow row1 = viewByCourse.createRow(i);
				for (int j=0;j<=20;j++){
					HSSFCell cell1 = row1.createCell(j);
				}
			}
			
			for(int i =0;i<DA.nbCourse;i++ ){
				HSSFRow row1=viewByCourse.getRow(1+6*i);
				HSSFCell cell1 = row1.getCell(0);
				Course course = DA.courses[i];
				cell1.setCellValue(course.code);
				viewByCourse.addMergedRegion(new CellRangeAddress(1+6*i, 6*i+6, 0, 0));
				ArrayList<ClassCourse> cclist = DA.mCourse2ClassCourseList.get(course);
				
				for (int j = 0; j < cclist.size(); j++) {
					ClassFU cls = DA.mClassCourse2Class.get(cclist.get(j));
					SingleSolution s = sol[DA.mClass2Index.get(cls)];
					for(int a=0;a<6;a++){
						for (int b=0;b<20;b++){
							if (s.T[a][b] == cclist.get(j).ID){
								row1= viewByCourse.getRow(1+6*i+a);
								cell1= row1.getCell(b+1);
								String str = cell1.getStringCellValue();
								str=str+","+cls.code;
								cell1.setCellValue(str);
							}
						}
					}
				}	
			}
			
			
			//
			HSSFSheet viewByRoom = workbook.createSheet("View by Room");
			for(int i=1;i<=DA.nbRoom;i++){
				HSSFRow row1 = viewByRoom.createRow(i);
				
				for (int j=0;j<=6*20;j++){
					HSSFCell cell1 = row1.createCell(j);
				}
				HSSFCell cell1 = row1.getCell(0);
				cell1.setCellValue(DA.mID2Room.get(i-1).code);
			}
			
			for (int icl = 0; icl < DA.nbClass; icl++) {
				SingleSolution s = sol[icl];
				String code = DA.classes[icl].code;
				for (int slot =0;slot<6;slot++){
					for(int day=0;day<20;day++){
						if (s.T[slot][day]>0){
							ClassCourse cc = DA.mID2ClassCourse.get(s.T[slot][day]);
							if (DA.mClassCourse2AssignedRoom.get(cc) != null) {
								Room room = DA.mClassCourse2AssignedRoom.get(cc);
								int idxr =room.ID;
								HSSFRow row1 = viewByRoom.getRow(idxr+1);
								HSSFCell cell1 = row1.getCell(1+day*6+slot);
								String str = cell1.getStringCellValue();
								str=str+","+ code;
								cell1.setCellValue(str);
							}
						}
					}
				}
			}
			
			// View delete list
			
			HSSFSheet CourseCantBeOpened = workbook.createSheet("Lop khong duoc mo");
			int i=0;
			for (Pair_ClassCourseClass pair : DA.lneedToBeDeletedClassCourse) {
				HSSFRow rowrow = CourseCantBeOpened.createRow(i);
				HSSFCell cellcc = rowrow.createCell(0);
				HSSFCell cellcl = rowrow.createCell(1);
				HSSFCell cellnbs = rowrow.createCell(2);
				cellcc.setCellValue(pair.classCourse.code);
				cellcl.setCellValue(pair.classCourse.classFU.code);
				cellnbs.setCellValue(DA.mClassCourse2nbStudent.get(pair.classCourse));
				i++;
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

	//////////////////////////////////////////////////////////////////////////////
	public void recoverTimeTableMergedCase(SingleSolution[] timetable){
//		public ArrayList<Pair_ClassCourseClass> lClassCourseGuestClassPair;
		for (Pair_ClassCourseClass pair : DA.lClassCourseGuestClassPair) {
			ClassCourse classCourse = pair.classCourse;
			ClassFU guestClass = pair.guestClass;
			ClassFU hostClass = DA.mClassCourse2Class.get(classCourse);
			int hostIndex = DA.mClass2Index.get(hostClass);
			int guestIndex = DA.mClass2Index.get(guestClass);
			for (int slot = 0; slot < DA.nbSlotsPerHalfDay*2; slot++) {
				for (int day = 0; day < DA.nbDays_20; day++) {
					if (timetable[hostIndex].T[slot][day] == classCourse.ID) {
						timetable[guestIndex].T[slot][day] = classCourse.ID;
					}					
				}
			}
			//
		}
	}
	
	
	
	
	///////////////////////////////////////////////////////////////////////////////
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TimeTableAllClass TA = new TimeTableAllClass();
		TA.DA = new DataCenter();
		/*
		 * TA.DA.loadData_Class_v2("data_class_v3.txt");
		 * TA.DA.loadData_Department_v2("data_department_v3.txt");
		 * TA.DA.loadData_Course_v2("data_course_v3.txt"); //
		 * TA.DA.loadData_Teacher_v2("data_teacher_v2.txt"); //
		 * DA.loadData_Course_Teacher_v2("data_course_teacher_v2.txt"); //
		 * TA.DA.loadData_Building_v2("data_building_v2.txt"); //
		 * TA.DA.loadData_Room_v2("data_room_v2.txt");
		 * TA.DA.loadData_ClassCourse_v2("data_classcourse_v4.txt");
		 * 
		 * TA.DA.loadData_mergedCases("data_mergedCases.txt"); //
		 * DA.loadData_mergedCases("data_mergedCases_sample.txt"); //
		 * TA.DA.makeMustNotConflictClassCourseList(); //
		 * TA.DA.makeMustNotConflictMatrix();
		 * 
		 * 
		 * 
		 * // ////TA.makeSolutionWarehouse_Template1(
		 * "data_solutionWarehouses_Temp1_full2.dat"); long startTime =
		 * System.currentTimeMillis(); /////TA.makeSolutionWarehouse_Template1(
		 * "data_solutionWarehouses_Temp1_full3.dat");
		 * 
		 * //
		 * TA.findOptimalTimeTable_v2_2("data_solutionWarehouses_Temp1_full.dat"
		 * ); String fn = "data_beingusedTT_Temp1_full.dat"; //
		 * TA.savebeingUsedTimeTable(fn); // TA.beingUsedTimeTable =
		 * TA.loadbeingUsedTimeTable(fn); // SingleSolution[] ttb =
		 * TA.loadbeingUsedTimeTable(fn); TA.beingUsedTimeTable = ttb;
		 * TA.printTimeTableAllClass("optimizedTTB_full2.html",ttb); //
		 * TA.writeMatrix2File("conflictm_full2.txt", TA.beingUsedTimeTable); //
		 * TA.findBlockSlotSessionOfClassCourse(sol);
		 * 
		 * TA.initPostImprovement();
		 * 
		 * TA.make_L_LStar_F_FStar(ttb);
		 * TA.update_L_LStar_F_FStar(TA.DA.courses[40],ttb); //
		 * TA.testMove(ttb); TA.update_L_LStar_F_FStar(TA.DA.courses[40], ttb);
		 * TA.printTimeTableAllClass("optimizedTTB_full2_moved.html",ttb);
		 * 
		 * 
		 * 
		 * 
		 * // TA.testMove(); // String fn = "data_beingusedTT_Temp1_full.dat";
		 * TA.postImprovement(fn); long estimatedTime =
		 * System.currentTimeMillis() - startTime; System.out.println(
		 * "Time-consuming: "+estimatedTime/1000+" secs");
		 */

		long startTime = System.currentTimeMillis();
		/*String fn_data_class = "datasm/data_class_sm.txt";
		String fn_data_course = "datasm/data_course_sm.txt";
		String fn_data_classcourse = "datasm/data_classcourse_sm.txt";
		String fn_data_room = "datasm/data_room_sm.txt";
		String fn_data_department = "datasm/data_department_sm.txt";
		String fn_data_building = "datasm/data_building_sm.txt";
		String fn_data_mergedCases = "datasm/data_mergedcases_sm.txt";
		String fn_classTimeTableSolutions_Temp2 = "datasm/data_solutionWarehouses_Temp2.dat";
		String fn_beingUsedTimeTable_Temp2 = "datasm/data_beingusedTT_Temp2.dat";
		String fn_conflictMatrix_Temp2 = "datasm/data_conflictMatrix_Temp2.txt";
		String fn_TTB = "datasm/optimizedTTB_sm.html";*/
		
		
		String fn_data_class = "datafall/data_class_fall.txt";
		String fn_data_course = "datafall/data_course_fall.txt";
		String fn_data_classcourse = "datafall/data_classcourse_fall.txt";
		String fn_data_room = "datafall/data_room_fall.txt";
		String fn_data_department = "datafall/data_department_fall.txt";
		String fn_data_building = "datafall/data_building_fall.txt";
		String fn_data_teacher = "datafall/data_teacher_fall.txt";
		String fn_data_course_teacher = "datafall/data_course_teacher_fall.txt";
		String fn_classTimeTableSolutions_Temp2 = "datafall/data_solutionWarehouses_Temp2.dat";
		String fn_beingUsedTimeTable_Temp2 = "datafall/data_beingusedTT_Temp2.dat";
		String fn_conflictMatrix_Temp2 = "datafall/data_conflictMatrix_Temp2.txt";
		String fn_TTB = "datafall/optimizedTTB_fall.html";
		String fn_savedata_ClassCourse = "datafall/data_saveClassCourse.txt";
		
		
		// String fn_courseScoreMatrix_Temp2 =
		// "datasm/data_scoreMatrix_Temp2.txt";

		//TA.DA.loadData_Class_v2(fn_data_class);
		TA.DA.loadData_Class_v2_hasStudent(fn_data_class);
		TA.DA.loadData_Department_v2(fn_data_department);
		TA.DA.loadData_Course_v2(fn_data_course);
		TA.DA.loadData_ClassCourse_v2(fn_data_classcourse);
		//TA.DA.loadData_mergedCases(fn_data_mergedCases);
		TA.DA.mergeClassCourse();
		TA.DA.reloadData_ClassCourse_v2(fn_data_classcourse);
		
		TA.DA.loadData_Building_v2(fn_data_building);
		TA.DA.loadData_Room_v2(fn_data_room);
		TA.DA.loadData_CourseRoom();
		TA.DA.loadData_Teacher_v2(fn_data_teacher);
		TA.DA.loadData_Course_Teacher_v2(fn_data_course_teacher);
		if (!TA.DA.isDataValidForTemplate2()) {
			System.out.println("Not feasible to make timetable.");
			System.exit(1);
		}
		TA.makeSolutionWarehouse_Template2(fn_classTimeTableSolutions_Temp2);
		//
		TA.init(fn_classTimeTableSolutions_Temp2, "");
		  TA.findOptimalTimeTable_v2_2(fn_classTimeTableSolutions_Temp2);
		  TA.savebeingUsedTimeTable(fn_beingUsedTimeTable_Temp2);
		  SingleSolution[] ttb =  TA.loadbeingUsedTimeTable(fn_beingUsedTimeTable_Temp2);
		  TA.beingUsedTimeTable = ttb;
		  TA.writeMatrix2File(fn_conflictMatrix_Temp2, TA.beingUsedTimeTable);
		  if (TA.isTimeTableCorrect(fn_conflictMatrix_Temp2)) {
			  System.out.println("Timetable is correct.");
			  TA.printTimeTableAllClass(fn_TTB,TA.beingUsedTimeTable);
			  AssignRoom roomAssigner;
			  roomAssigner = new AssignRoom();
			  roomAssigner.DA = TA.DA;
			  roomAssigner.assignRoomUsingFor(fn_beingUsedTimeTable_Temp2);
			  TA.calRoomDemandEverySlot(TA.beingUsedTimeTable); System.out.println();
			  TA.demand_OfACourse = new int[TA.DA.nbCourse];
			  TA.demand_OfACourse = TA.calculateTeacherDemand(ttb);
			  int[] ss = TA.findSessionAllClass(ttb);
			  System.out.println("sang = "+ss[0]);
			  System.out.println("chieu = "+ss[1]);
			  TA.testDepartMentDemand(ttb);
			  TA.recoverTimeTableMergedCase(ttb);
			  TA.PoiWriteExcelFile(TA, "ttb.xls", TA.beingUsedTimeTable);
		  }else{
			  System.out.println("Timetable is incorrect.");
		  }
		  
		  
		  
		//TA.modelling_ttb_manual();
		// TA.buildTTB_manual();
		long estimatedTime = System.currentTimeMillis() - startTime;
		System.out.println("Time-consuming: " + estimatedTime / 1000 + " secs");
	}

}
