package vn.edu.fpt.timetabling.auto.algorithms;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
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
import vn.edu.fpt.timetabling.auto.entities.Course;
import vn.edu.fpt.timetabling.auto.entities.DataCenter;
import vn.edu.fpt.timetabling.auto.entities.DataOneClass;
import vn.edu.fpt.timetabling.auto.entities.DaySlot;
import vn.edu.fpt.timetabling.auto.entities.SingleSolution;

public class TimeTableAllClass {
	public DataCenter DA;
	public TimeTableOneClass_SM[] timeTable_WareHouse;
	public SingleSolution[] beingUsedTimeTable;
	public int[] backUpTimeTable;
	int[] demand_OfACourse;
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

	// the quality of outcome and time-consumming depend on this limit
	final static int LIMIT_NBSOLUTION_PERCLASS = 768; // should be 768
	final static int LIMIT_NBCLASS_INBANNEDLIST = 5;
	final static int LIMIT_ROOMALLOWANCE = 1;
	final static int LIMIT_DEMANDTEACHER_PERCOURSE = 2;
	final static int NB_ITERATION = 1000;

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
			mClassCourse2x_dayList.put(DA.classCourses[i], new ArrayList<VarIntLS>());
			mClassCourse2x_slotList.put(DA.classCourses[i], new ArrayList<VarIntLS>());
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

				if (fcIdx < nbFC && DA.mID2ClassCourse_all.get(ccID) != null) {
					// System.out.println("fcIdx = "+fcIdx+", ccID = "+ccID);
					ClassCourse cc = DA.mID2ClassCourse_all.get(ccID);
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
			in.close();
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
				System.out.println("Problem in public int getAssignDelta(int pickedClass, SingleSolution sol).");
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

	public void makeSolutionWarehouse(String targetFn) {
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
			timeTable_WareHouse[cl].D
					.loadData_SM(DataCenter.DATAFOLDER + "/" + DataCenter.FILENAME_PREFIX_DATAONECLASS + cl + ".txt");
			System.out.println("Done class " + cl);
		}

		for (int cl = 0; cl < DA.nbClass; cl++) {
			TimeTableOneClass_SM tt1c = timeTable_WareHouse[cl];
			tt1c.model = new CPModel();
			tt1c.solver = new CPSolver();
			tt1c.stateModel();
			tt1c.solver.read(tt1c.model);
			tt1c.findSolution(DataCenter.FILENAME_PREFIX_DATAONECLASS + cl + ".html",
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
		stateModelAllClass(DataCenter.DATAFOLDER + "/" + "fc.txt");

		timeTable_WareHouse = new TimeTableOneClass_SM[DA.nbClass];
		for (int cl = 0; cl < DA.nbClass; cl++) {
			timeTable_WareHouse[cl] = new TimeTableOneClass_SM();
			timeTable_WareHouse[cl].D = new DataOneClass();
			timeTable_WareHouse[cl].D
					.loadData_SM(DataCenter.DATAFOLDER + "/" + DataCenter.FILENAME_PREFIX_DATAONECLASS + cl + ".txt");
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

			int p = r.nextInt((upperBound - 1 - 0) + 1) + 0;
			// int p = 0;
			beingUsedTimeTable[cl] = tt1c.sol[p];
			setValuePropagage(cl, beingUsedTimeTable[cl]);
			backUpTimeTable[cl] = tt1c.sol[p].position;
		}

		// this.loadData_supply_Teacher(fn_data_teacherSupply);
		this.supplyTeacher4Optimization = this.calculateSupplyTeacher();
		this.demand_OfACourse = this.calTeacherDemand(beingUsedTimeTable);
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
			tt1c.stateModel();
			tt1c.solver.read(tt1c.model);
			tt1c.findSolution("class_" + cl + ".html", limitNbSolutionPerClass);
			beingUsedTimeTable[cl] = new SingleSolution();
			int p = r.nextInt((tt1c.sol.length - 1 - 0) + 1) + 0;
			// int p = 0;
			beingUsedTimeTable[cl] = tt1c.sol[p];
			backUpTimeTable[cl] = tt1c.sol[p].position;
		}

		// this.loadData_supply_Teacher("teacher_supply.txt");
		this.supplyTeacher4Optimization = this.calculateSupplyTeacher();
		this.demand_OfACourse = this.calTeacherDemand(beingUsedTimeTable);
		totalDemand = sumOfAnArray(demand_OfACourse);
		totalSupply = sumOfAnArray(supplyTeacher4Optimization);
		System.out.println("total demand 0 = " + totalDemand);
		System.out.println("total supply 0 = " + totalSupply);
	}

	public int[] calTeacherDemand(SingleSolution[] pickedSolutions) {
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
						ClassCourse cc = DA.mID2ClassCourse_all.get(ccID);
						Course c = DA.mClassCourse2Course.get(cc);
						// System.out.println(c.ID);
						int cIdx = DA.mCourse2Index.get(c);
						slotDemand[cIdx]++;
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
		/*
		 * Random r = new Random(); for (int c = 0; c < DA.nbCourse; c++) { //
		 * int nbTCperCourse = r.nextInt((2-1)+1)+1; int nbTCperCourse = 1;
		 * supply[c] = nbTCperCourse; System.out.println("c = "+c+", supply = "
		 * +nbTCperCourse); }
		 */

		for (int i = 0; i < DA.nbCourse; i++) {
			Course c = DA.courses[i];
			ArrayList<ClassCourse> L = DA.mCourse2ClassCourseList.get(c);
			int s = (int) Math.ceil((double) L.size() / 8);
			supply[i] = s;
		}

		return supply;
	}

	public int marginOfAClass(int[] demand, int[] supply, int classIdx) {
		int margin = 0;
		ArrayList<Course> L = new ArrayList<Course>();
		ClassFU cls = DA.classes[classIdx];
		L = DA.mClass2_CourseList.get(cls);
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
			L = DA.mClass2_CourseList.get(cls);
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

		return pickedClass;
	}

	/** Calculate constraints violation of a class. */
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

	public void findOptimalTimeTable_v2_2(String fn_classTimeTableSolutions) {
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

		demand_OfACourse = calTeacherDemand(beingUsedTimeTable);
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
			demand_OfACourse = calTeacherDemand(beingUsedTimeTable);
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
							newDemand = calTeacherDemand(beingUsedTimeTable);
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
		} while (iter < NB_ITERATION && totalDemand > totalSupply);
		System.out.println("Final violation = " + S.violations());
		System.out.println("Done finding...");
		System.out.println("\nResult:.....");

		demand_OfACourse = calTeacherDemand(beingUsedTimeTable);
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
			demand_OfACourse = calTeacherDemand(beingUsedTimeTable);
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
						newDemand = calTeacherDemand(beingUsedTimeTable);
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
				demand_OfACourse = calTeacherDemand(beingUsedTimeTable);
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

		demand_OfACourse = calTeacherDemand(beingUsedTimeTable);
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
						ClassCourse cc = DA.mID2ClassCourse_all.get(sol.T[i][j]);
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
					ClassCourse cc = DA.mID2ClassCourse_all.get(sol.T[i][j]);
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
						ClassCourse cc = DA.mID2ClassCourse_all.get(classCourseID);
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TimeTableAllClass TA = new TimeTableAllClass();
		TA.DA = new DataCenter();
		TA.DA.loadData_Course_Class("data_all_sm_merged.txt");
		TA.DA.loadData_mergedCases("data_mergedCases_sample.txt");
		TA.makeSolutionWarehouse("solutionWarehouses_improved.dat");

		/*
		 * TA.DA = new DataCenter();
		 * TA.DA.loadData_Course_Class("data_all_sm_merged.txt");
		 * DA.("data_mergedCases_sample.txt");
		 */

		long startTime = System.currentTimeMillis();

		TA.findOptimalTimeTable_v2_2("solutionWarehouses_improved.dat");
		String fn = "beingusedTT2.dat";
		TA.savebeingUsedTimeTable(fn);
		SingleSolution[] sol = TA.loadbeingUsedTimeTable(fn);
		TA.printTimeTableAllClass("optimizedTTB.html", sol);
		TA.writeMatrix2File("conflictm.txt", TA.beingUsedTimeTable);
		long estimatedTime = System.currentTimeMillis() - startTime;
		System.out.println("Time-consuming: " + estimatedTime / 1000 + " secs");

		/*
		 * TA.DA = new DataCenter();
		 * TA.DA.loadData_Course_Class("data_all_sm_merged.txt");
		 */
		/*
		 * String fn = "beingusedTT.dat"; SingleSolution[] sol =
		 * TA.loadbeingUsedTimeTable(fn); TA.calRoomDemandEverySlot(sol);
		 */
		// TA.genData_Fragment("fc.txt");
		// TA.loadData_Fragment("fc.txt");

		// TA.DA.("data_mergedCases_sample.txt");
		// TA.stateModelAllClass("fc.txt");
		// TA.init("data_all_sm_merged.txt", "solutionWarehouses_improved.dat",
		// TimeTableAllClass.NB_OF_SOLUTION_PERCLASS_LIMIT);
	}

}
