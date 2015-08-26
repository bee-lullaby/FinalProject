package vn.edu.fpt.timetabling.auto.algorithms;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import localsearch.constraints.basic.IsEqual;
import localsearch.functions.basic.FuncPlus;
import localsearch.functions.basic.FuncVarConst;
import localsearch.functions.sum.Sum;
import localsearch.model.ConstraintSystem;
import localsearch.model.IFunction;
import localsearch.model.LocalSearchManager;
import localsearch.model.VarIntLS;
import vn.edu.fpt.timetabling.auto.entities.ClassCourse;
import vn.edu.fpt.timetabling.auto.entities.ClassFU;
import vn.edu.fpt.timetabling.auto.entities.Course;
import vn.edu.fpt.timetabling.auto.entities.DataCenter;
import vn.edu.fpt.timetabling.auto.entities.DaySlot;
import vn.edu.fpt.timetabling.auto.entities.Pair_CourseRoom;
import vn.edu.fpt.timetabling.auto.entities.Room;
import vn.edu.fpt.timetabling.auto.entities.SingleSolution;
import vn.edu.fpt.timetabling.auto.entities.Teacher;

public class AssignRoom {
	public DataCenter DA;
	public SingleSolution[] sol;

	public HashMap<Room, ArrayList<DaySlot>> mRoom2DaySlotList;
	public HashMap<ClassCourse, ArrayList<DaySlot>> mClassCourse2DaySlotList;
	public HashMap<Room, ArrayList<ClassCourse>> mRoom2ClassCourseList;
	public HashMap<ClassFU, HashSet<Room>> mClass2RoomList;
	// public ArrayList<CourseRoomPair> result;
	// public ArrayList<ClassCourse> notyetassignedClassCourse;
	// public boolean[] ccIsAssigned;
	// public int[] availableCapacity;

	final static int LIMIT_NBROOM_PERCOURSETYPE = 45;

	//////////////////////////////////////////////////////////////////////
	LocalSearchManager ls;
	ConstraintSystem S;
	VarIntLS[][] x;
	IFunction objfunc;
	IFunction[] fsumSameNBTeacher;

	/** Test room clustering. */
	@SuppressWarnings("unchecked")
	public void testRoomClustering() {
		DA = new DataCenter();
		AssignTeacher a = new AssignTeacher();
		a.DA = this.DA;

		String fn_data_course_class = "data_course_class_merged.txt";
		String fn_data_teacher = "data_teacher.txt";
		// String fn_data_room_building = "data_room_building.txt";
		String fn_data_mergedCases = "data_mergedCases.txt";
		// String fn_classTimeTableSolutions = "data_solutionWarehouses2.dat";
		String fn_conflictMatrix = "data_conflictMatrix.txt";
		String fn_courseScoreMatrix = "data_scoreMatrix.txt";
		String fn_beingUsedTimeTable = "data_beingusedTT.dat";
		// String fn_allClassTTB = "TTB.html";
		// String fn_allTeacherTTB1 = "teacherTimeTable1.html";
		// String fn_allTeacherTTB2 = "teacherTimeTable2.html";
		// String fn_allTeacherTTB = "teacherTimeTable.html";
		// String fn_allClass_TimetableFull = "TTB_AllClass_full.html";

		DA.loadData_Course_Class(fn_data_course_class); // for all phases
		DA.loadData_Teacher_UsingCode(fn_data_teacher); // for phase 3
		// DA.loadData_Room_Building(fn_data_room_building); //for phase 4
		DA.loadData_mergedCases(fn_data_mergedCases); // for phase 1

		// ----------------------------------------------------------
		// first, assigning teacher-cc
		a.assignTeacherUsingScore(fn_conflictMatrix, fn_beingUsedTimeTable, fn_courseScoreMatrix, DA.classCourses);

		// load data: nbRoomPercluster
		DA.nbRoomPerCluster = new int[DataCenter.NBCLUSTER];
		DA.aRoomList = new ArrayList[DataCenter.NBCLUSTER];
		for (int i = 0; i < DataCenter.NBCLUSTER; i++) {
			DA.aRoomList[i] = new ArrayList<>();
		}
		for (int i = 0; i < DataCenter.NBCLUSTER; i++) {
			DA.loadData_RoomCluster(DataCenter.FILENAME_PREFIX_ROOMCLUSTER, i);
		}
		DA.nbClassPerCluster = new int[DataCenter.NBCLUSTER];

		// find number of class per cluster.
		System.out.println("\nCluster: ");
		DA.nbClassPerCluster = findNumberOfClassPerCluster();
		for (int i = 0; i < DataCenter.NBCLUSTER; i++) {
			System.out.print("cluster[" + (i + 1) + "]: nbroom = " + DA.nbRoomPerCluster[i] + ", nbclass = "
					+ DA.nbClassPerCluster[i] + ":");
			for (Room r : DA.aRoomList[i]) {
				System.out.print(" " + r.code);
			}
			System.out.println();
		}

		// find elements needed
		SingleSolution[] sol = loadbeingUsedTimeTable(fn_beingUsedTimeTable);
		a.findScoreElement(sol);

		// build commonTeacherMatrix;
		buildCommonTeacherSameDayMatrix();

		// state model
		stateModel();

		// search
		search_LS();
		// search_OLS();

		// print result
		DA.result_classClustering = new ArrayList<>();
		for (int cter = 0; cter < DataCenter.NBCLUSTER; cter++) {

			System.out.print("Cluster[" + (cter + 1) + "]-<" + fsumSameNBTeacher[cter].getValue() + "> :");
			for (int cl = 0; cl < DA.nbClass; cl++) {
				if (x[cl][cter].getValue() == 1) {
					ClassFU cls = DA.classes[cl];
					System.out.print(" " + cls.code);
				}

			}
			System.out.println();
		}

		// ----------------------------------------------------------
	}

	/** Room clustering */

	/**
	 * Find number of class per cluster. This function must be run after loading
	 * cluster data.
	 */
	public int[] findNumberOfClassPerCluster() {
		int[] nbClassEachCluster = new int[DataCenter.NBCLUSTER];

		// if there is only one cluster
		if (nbClassEachCluster.length == 1) {
			nbClassEachCluster[0] = DA.nbClass;
			return nbClassEachCluster;
		}

		// find min
		int min = DA.nbRoomPerCluster[0];
		int minIdx = 0;
		for (int i = 1; i < DA.nbRoomPerCluster.length; i++) {
			if (DA.nbRoomPerCluster[i] < min) {
				min = DA.nbRoomPerCluster[i];
				minIdx = i;
			}
		}
		// calculate rate between other nbRoomPerCluster[i]s and min
		// nbRoomPerCluster
		int[] rate = new int[DataCenter.NBCLUSTER];
		rate[minIdx] = 1;
		for (int i = 0; i < rate.length; i++) {
			if (i != minIdx) {
				rate[i] = (int) ((double) DA.nbRoomPerCluster[i] / min);
			}
		}

		// calculate number of class per cluster
		int sumRate = 0;
		for (int i = 0; i < rate.length; i++) {
			sumRate += rate[i];
		}

		int minNBClassPerCluster = (int) Math.ceil((double) DA.nbClass / sumRate);
		int cummulative = 0;
		for (int i = 0; i < nbClassEachCluster.length - 1; i++) {
			nbClassEachCluster[i] = rate[i] * minNBClassPerCluster;
			cummulative += nbClassEachCluster[i];
		}
		if (cummulative > DA.nbClass) {
			System.out.println("BUG in finding number of class per cluster.");
			System.exit(1);
		}
		nbClassEachCluster[nbClassEachCluster.length - 1] = DA.nbClass - cummulative;

		return nbClassEachCluster;
	}

	/**
	 * Build CommonTeacherSameDayMatrix. This function must be run after
	 * assigning teacher and AssignTeacher.findScoreElement(SingleSolution[])
	 */
	public void buildCommonTeacherSameDayMatrix() {
		DA.commonTeacherSameDayMatrix = new int[DA.nbClass][DA.nbClass];
		// for each couple of classes
		for (int cl1 = 0; cl1 < DA.nbClass - 1; cl1++) {
			for (int cl2 = cl1 + 1; cl2 < DA.nbClass; cl2++) {
				ClassFU cls1 = DA.classes[cl1];
				ClassFU cls2 = DA.classes[cl2];

				ArrayList<ClassCourse> ccList1 = DA.mClass2ClassCourseList.get(cls1);
				ArrayList<ClassCourse> ccList2 = DA.mClass2ClassCourseList.get(cls2);

				int sum = 0;
				for (ClassCourse cc1 : ccList1) {
					Teacher tc1 = DA.mClassCourse2AssignedTeacher.get(cc1);
					for (ClassCourse cc2 : ccList2) {
						Teacher tc2 = DA.mClassCourse2AssignedTeacher.get(cc2);
						if (isInTheSameDay(cc1, cc2) && tc1 == tc2) {
							// if (tc1 == tc2) {
							sum++;
						}

					}
				}
				//
				DA.commonTeacherSameDayMatrix[cl1][cl2] = sum;
				DA.commonTeacherSameDayMatrix[cl2][cl1] = sum;
			}
		}
		//
		System.out.println("CommonTeacherSameDayMatrix: ");
		for (int i = 0; i < DA.nbClass; i++) {
			for (int j = 0; j < DA.nbClass; j++) {
				System.out.print(" " + DA.commonTeacherSameDayMatrix[i][j]);
			}
			System.out.println();
		}
	}

	/**
	 * Check if two classcourses are in the same day. This function must be run
	 * after finding elements
	 * (AssignTeacher.findScoreElement(SingleSolution[])).
	 */
	public boolean isInTheSameDay(ClassCourse clc1, ClassCourse clc2) {
		int cc1 = DA.mClassCourse2Index.get(clc1);
		int cc2 = DA.mClassCourse2Index.get(clc2);

		int b1 = DA.aClassCourse2Block[cc1];
		int b2 = DA.aClassCourse2Block[cc2];
		int ss1 = DA.aClassCourse2Session[cc1];
		int ss2 = DA.aClassCourse2Session[cc2];
		int d1 = DA.aClassCourse2Day[cc1];
		int d2 = DA.aClassCourse2Day[cc2];

		int sameblock = 0;
		if (clc1.stt == 3 || clc2.stt == 3) {
			sameblock = 1;
		} else if ((b1 + b2) % 2 == 0) {
			sameblock = 1;
		}

		int sameday = 0;
		if ((d1 + d2) % 2 == 0) {
			sameday = 1;
		}

		int samesession = 0;
		if ((ss1 + ss2) % 2 == 0) {
			samesession = 1;
		}

		if (sameblock * sameday * samesession == 1) {
			return true;
		} else {
			return false;
		}

	}

	public void stateModel() {
		ls = new LocalSearchManager();
		x = new VarIntLS[DA.nbClass][DataCenter.NBCLUSTER];
		VarIntLS[][] y = new VarIntLS[DA.nbClass][DataCenter.NBCLUSTER];

		for (int i = 0; i < DA.nbClass; i++) {
			for (int j = 0; j < DataCenter.NBCLUSTER; j++) {
				x[i][j] = new VarIntLS(ls, 0, 1);
				y[i][j] = new VarIntLS(ls, 0, 25); // 5cc * 5cc = 25
			}
		}
		S = new ConstraintSystem(ls);
		// ----------------------------------------------------------------
		for (int i = 0; i < DA.nbClass; i++) {
			S.post(new IsEqual(new Sum(x[i]), new FuncVarConst(ls, 1)));
		}

		IFunction[] fsum = new IFunction[DataCenter.NBCLUSTER];
		fsumSameNBTeacher = new IFunction[DataCenter.NBCLUSTER];
		for (int i = 0; i < fsum.length; i++) {
			fsum[i] = new FuncVarConst(ls, 0);
			fsumSameNBTeacher[i] = new FuncVarConst(ls, 0);
		}

		for (int i = 0; i < DataCenter.NBCLUSTER; i++) {
			for (int j = 0; j < DA.nbClass; j++) {
				fsum[i] = new FuncPlus(fsum[i], x[j][i]);
				// fsumSameNBTeacher[i] = new FuncPlus(fsumSameNBTeacher[i],
				// x[j][i]);
			}
			S.post(new IsEqual(fsum[i], new FuncVarConst(ls, DA.nbClassPerCluster[i])));
		}

		// optimization constraints

		/*
		 * objfunc = new FuncVarConst(ls, 0); for (int i = 0; i <
		 * DataCenter.NBCLUSTER; i++) { objfunc = new FuncPlus(objfunc,
		 * fsumSameNBTeacher[i]); }
		 * 
		 * objfunc = new FuncMinus(new FuncVarConst(ls, 0), objfunc);
		 */

		// ----------------------------------------------------------------
		ls.close();
	}

	public void search_LS() {
		localsearch.search.TabuSearch ts = new localsearch.search.TabuSearch();
		ts.search(S, 50, 3000, 400, 30);

	}

	public void search_OLS() {
		localsearch.search.TabuSearch ts = new localsearch.search.TabuSearch();
		ts.searchMaintainConstraints(objfunc, S, 100, 3000, 1000, 30);
	}
	//////////////////////////////////////////////////////////////////////

	public void init(String fn_beingUsedTimeTable) {
		// load data
		// DA = new DataCenter();
		// DA.loadData_Course_Class("data_all_sm_merged.txt");
		// DA.loadData_Teacher_UsingCode("teacherDataCode.txt");
		// DA.loadData_Room_Building("roomdata.txt");
		sol = new SingleSolution[DA.nbClass];
		sol = loadbeingUsedTimeTable(fn_beingUsedTimeTable);
		// DA.loadData_SoftSkill("data_softskill.txt");

		// calculate supply
		init_roomSupply();

		// calculate demand
		init_roomDemand();

		/*
		 * System.out.println(); Room r = DA.rooms[0]; for (int cc = 0; cc < 20;
		 * cc++) { ClassCourse clc = DA.classCourses[cc]; //
		 * System.out.println(r.availableSlot
		 * +"-"+currentDemandOfAClassCourse(clc)); if
		 * (isAvailableAsRequired(clc,r)) {
		 * markDaySlotOfRoom_AsAssigned(clc,DA.rooms[0]); } ClassFU cls =
		 * DA.mClassCourse2Class.get(clc); ArrayList<DaySlot> L =
		 * mClassCourse2DaySlotList.get(clc); System.out.print(clc.ID+ ", cc = "
		 * +cls.code+"-"+clc.code+", current demand = "
		 * +currentDemandOfAClassCourse(clc)+": "); for (DaySlot daySlot : L) {
		 * System.out.print(" <"
		 * +daySlot.day+"-"+daySlot.slot+"-"+daySlot.isAssigned +">"); }
		 * System.out.println(""); }
		 * 
		 * System.out.println("Room = "+r.code+", capacity = "+r.capacity+
		 * ", available = "+r.availableSlot+": "); ArrayList<DaySlot> L =
		 * mRoom2DaySlotList.get(r); for (DaySlot daySlot : L) {
		 * System.out.print(" <"
		 * +daySlot.day+"-"+daySlot.slot+"-"+daySlot.isAssigned +">"); }
		 * System.out.println("");
		 */
	}

	public void assignRoom3For() {
		init(null);

		mClass2RoomList = new HashMap<ClassFU, HashSet<Room>>();
		for (ClassFU cls : DA.classes) {
			mClass2RoomList.put(cls, new HashSet<Room>());
		}
		DA.result = new ArrayList<Pair_CourseRoom>();
		mRoom2ClassCourseList = new HashMap<Room, ArrayList<ClassCourse>>();
		for (int r = 0; r < DA.nbRoom; r++) {
			Room ro = DA.rooms[r];
			mRoom2ClassCourseList.put(ro, new ArrayList<ClassCourse>());
		}

		DA.ccIsAssigned = new boolean[DA.nbClassCourse]; // mark if cc is
															// assigned
		DA.notyetassignedClassCourse = new ArrayList<ClassCourse>(); // nhung cc
																		// nao
																		// ko
																		// tim
																		// dc
																		// phong
		// ============================================
		for (int cl = 0; cl < DA.nbClass; cl++) {
			ArrayList<ClassCourse> ccList = DA.mClass2ClassCourseList.get(DA.classes[cl]);
			boolean hasFullRoom = false;
			int fullRoomIdx = -1;

			// cal demand of ccList
			int totalDemand = 0;
			for (ClassCourse cc : ccList) {
				totalDemand += currentDemandOfAClassCourse(cc);
			}

			// find full room
			for (int ri = 0; ri < DA.nbRoom; ri++) {
				Room ro = DA.rooms[ri];
				// System.out.println("demand = "+totalDemand+", sp =
				// "+ro.availableSlot);
				// if (totalDemand <= ro.availableSlot) { //neu tong so
				// available slot >= demand moi xet tiep
				if (totalDemand <= DA.availableCapacity[ri]) { // neu tong so
																// available
																// slot >=
																// demand moi
																// xet tiep
					boolean ok = true;
					for (ClassCourse cc : ccList) {
						if (!isAvailableAsRequired(cc, ro)) {
							ok = false;
						}
					}
					if (ok) {
						hasFullRoom = true;
						fullRoomIdx = ri;
						break;
					}
				}
			}

			// if found -> gan het
			if (hasFullRoom) {
				Room r = DA.rooms[fullRoomIdx];
				for (ClassCourse cc : ccList) {
					if (isAvailableAsRequired(cc, r)) {
						assignCC2Room(cc, r);
						// ccIsAssigned[DA.mClassCourse2Index.get(cc)] = true;
					}
				}
			} else { // gan tuan tu tung cc cho tung r
				for (ClassCourse cc : ccList) {
					boolean thereIsARoom = false;
					for (int ri = 0; ri < DA.nbRoom; ri++) {
						Room r = DA.rooms[ri];
						if (isAvailableAsRequired(cc, r)) {
							assignCC2Room(cc, r);
							thereIsARoom = true;
							// ccIsAssigned[DA.mClassCourse2Index.get(cc)] =
							// true;
						}
					}
					if (thereIsARoom == false) {
						DA.notyetassignedClassCourse.add(cc);
					}
				}
				//
			}
		}
		// ============================================
		System.out.println("\nResult: ");
		for (Pair_CourseRoom p : DA.result) {
			ClassCourse cc = p.cc;
			ClassFU cls = DA.mClassCourse2Class.get(cc);
			Room r = p.r;
			System.out.println("cc = " + cc.code + "-" + cls.code + ": " + r.code);
		}

		System.out.println("\nNot yet assigned class-course: ");
		int count = 0;
		for (int i = 0; i < DA.ccIsAssigned.length; i++) {
			if (DA.ccIsAssigned[i] == false) {
				count++;
				ClassCourse cc = DA.classCourses[i];
				ClassFU cls = DA.mClassCourse2Class.get(cc);
				System.out.println(count + ", " + cc.code + "-" + cls.code);
			}
		}
		System.out.println("Total " + count + " not yet assigned.\n");

		for (int ri = 0; ri < DA.nbRoom; ri++) {
			Room r = DA.rooms[ri];
			ArrayList<ClassCourse> ccList = mRoom2ClassCourseList.get(r);
			// System.out.println("mRoom2ClassCourseList size =
			// "+mRoom2ClassCourseList.size());
			System.out.print("room = " + r.code + ":<" + DA.availableCapacity[ri] + ">" + "-<" + ccList.size() + ">: ");
			for (ClassCourse cc : ccList) {
				ClassFU cls = DA.mClassCourse2Class.get(cc);
				System.out.print(" " + cls.code + "-" + cc.code);
			}
			System.out.println();
		}

	}

	public void assignRoomUsingFor(String fn_beingUsedTimeTable) {
		init(fn_beingUsedTimeTable);
		DA.result = new ArrayList<Pair_CourseRoom>();
		mRoom2ClassCourseList = new HashMap<Room, ArrayList<ClassCourse>>();
		DA.mClassCourse2AssignedRoom = new HashMap<>();

		for (int r = 0; r < DA.nbRoom; r++) {
			Room ro = DA.rooms[r];
			mRoom2ClassCourseList.put(ro, new ArrayList<ClassCourse>());
		}
		DA.ccIsAssigned = new boolean[DA.nbClassCourse]; // mark if cc is
															// assigned
		DA.notyetassignedClassCourse = new ArrayList<ClassCourse>(); // nhung cc
																		// nao
																		// ko
																		// tim
																		// dc
																		// phong
		mClass2RoomList = new HashMap<ClassFU, HashSet<Room>>();
		for (ClassFU cls : DA.classes) {
			mClass2RoomList.put(cls, new HashSet<Room>());
		}
		// ====================================================
		/*
		 * ArrayList<Course> cList = DA.lSoftSkillCourse; ArrayList<ClassCourse>
		 * classcourseList1 = new ArrayList<ClassCourse>(); System.out.println(
		 * "\nSoft skills class-courses:"); for (Course course : cList) {
		 * ArrayList<ClassCourse> L = DA.mCourse2ClassCourseList.get(course);
		 * classcourseList1.addAll(L);
		 * System.out.print(course.code+"-<"+L.size()+">: "); for (ClassCourse
		 * cc : L) { ClassFU cls = DA.mClassCourse2Class.get(cc);
		 * System.out.print(cc.code+"-"+cls.code+" "); } System.out.println(); }
		 * System.out.println();
		 */
		// ====================================================
		// DA.lClassifiedCourse = new ArrayList[DA.NB_COURSETYPE];
		// DA.lCorrespondClassifiedRoom = new ArrayList[DA.NB_COURSETYPE];
		for (int type = 0; type < DA.nbRoomTypes; type++) {
			// DA.lClassifiedCourse[type] = new ArrayList<Course>();
			// DA.lCorrespondClassifiedRoom[type] = new ArrayList<Room>();
			// DA.loadData_CourseRoom("datafall/type"+(type+1)+".txt",type);

			ArrayList<ClassCourse> classcourseList = new ArrayList<ClassCourse>();
			for (Course c : DA.lClassifiedCourse[type]) {
				ArrayList<ClassCourse> L = DA.mCourse2ClassCourseList.get(c);
				classcourseList.addAll(L);
			}

			ArrayList<Room> roomList = new ArrayList<Room>();
			for (int i = 0; i < DA.lCorrespondClassifiedRoom[type].size(); i++) {
				if (i >= LIMIT_NBROOM_PERCOURSETYPE) {
					break;
				}
				roomList.add(i, DA.lCorrespondClassifiedRoom[type].get(i));
			}

			System.out.println("RoomList size = " + roomList.size());
			assignCC2Room(classcourseList, roomList);
		}

		for (int type = 0; type < DA.nbRoomTypes; type++) {
			System.out.println("\nType no." + (type + 1) + ": ");
			System.out.print("Course:" + "<" + DA.lClassifiedCourse[type].size() + ">: ");
			for (Course c : DA.lClassifiedCourse[type]) {
				System.out.print(" " + c.code);
			}
			System.out.print("\nRoom: " + "<" + DA.lCorrespondClassifiedRoom[type].size() + ">: ");
			for (Room r : DA.lCorrespondClassifiedRoom[type]) {
				System.out.print(" " + r.code);
			}
			System.out.println();
		}

		// ====================================================
		System.out.println("\nResult: ");
		for (Pair_CourseRoom p : DA.result) {
			ClassCourse cc = p.cc;
			ClassFU cls = DA.mClassCourse2Class.get(cc);
			Room r = p.r;
			System.out.println("cc = " + cc.code + "-" + cls.code + ": " + r.code);
		}

		System.out.println("\nNot yet assigned class-course: ");
		int count = 0;
		for (int i = 0; i < DA.ccIsAssigned.length; i++) {
			if (DA.ccIsAssigned[i] == false) {
				count++;
				ClassCourse cc = DA.classCourses[i];
				ClassFU cls = DA.mClassCourse2Class.get(cc);
				System.out.println(count + ", " + cc.code + "-" + cls.code);
			}
		}
		System.out.println("Total " + count + " not yet assigned.\n");

		for (int ri = 0; ri < DA.nbRoom; ri++) {
			Room r = DA.rooms[ri];
			ArrayList<ClassCourse> ccList = mRoom2ClassCourseList.get(r);
			// System.out.println("mRoom2ClassCourseList size =
			// "+mRoom2ClassCourseList.size());
			System.out.print("room = " + r.code + ":<" + DA.availableCapacity[ri] + ">" + "-<" + ccList.size() + ">: ");
			for (ClassCourse cc : ccList) {
				ClassFU cls = DA.mClassCourse2Class.get(cc);
				System.out.print(" " + cls.code + "-" + cc.code);
			}
			System.out.println();
		}
	}

	/** Assign some class-courses to some rooms. */
	public void assignCC2Room(ArrayList<ClassCourse> classcourseList, ArrayList<Room> roomList) {
		for (int cl = 0; cl < DA.nbClass; cl++) {
			// find class-courses have the same class
			ClassFU cls = DA.classes[cl];
			ArrayList<ClassCourse> sameClass_CCList = new ArrayList<ClassCourse>();
			for (ClassCourse cc : classcourseList) {
				ClassFU clsi = DA.mClassCourse2Class.get(cc);
				int ccIdx = DA.mClassCourse2Index.get(cc);
				if (cls == clsi && DA.ccIsAssigned[ccIdx] == false) {
					sameClass_CCList.add(cc);
				}
			}

			// cal demand of ccList
			int totalDemand = 0;
			for (ClassCourse cc : sameClass_CCList) {
				totalDemand += currentDemandOfAClassCourse(cc);
			}

			// find full room
			boolean hasFullRoom = false;
			int fullRoomIdx = -1;
			for (int ri = 0; ri < roomList.size(); ri++) {
				Room ro = roomList.get(ri);
				if (totalDemand <= DA.availableCapacity[ri]) { // neu tong so
																// available
																// slot >=
																// demand moi
																// xet tiep
					boolean ok = true;
					for (ClassCourse cc : sameClass_CCList) {
						if (!isAvailableAsRequired(cc, ro)) {
							ok = false;
						}
					}
					if (ok) {
						hasFullRoom = true;
						fullRoomIdx = ri;
						break;
					}
				}
			}

			// if found -> gan het
			if (hasFullRoom) {
				Room r = roomList.get(fullRoomIdx);
				for (ClassCourse cc : sameClass_CCList) {
					if (isAvailableAsRequired(cc, r)) {
						assignCC2Room(cc, r);
						/*
						 * int ccIdx = DA.mClassCourse2Index.get(cc);
						 * ccIsAssigned[ccIdx] = true;
						 */
					}
				}
			} else { // gan tuan tu tung cc cho tung r
				for (ClassCourse cc : sameClass_CCList) {
					boolean thereIsARoom = false;
					for (int ri = 0; ri < roomList.size(); ri++) {
						Room r = roomList.get(ri);
						if (isAvailableAsRequired(cc, r)) {
							assignCC2Room(cc, r);
							thereIsARoom = true;
							break;
							/*
							 * int ccIdx = DA.mClassCourse2Index.get(cc);
							 * ccIsAssigned[ccIdx] = true;
							 */
						}
					}
					if (thereIsARoom == false) {
						DA.notyetassignedClassCourse.add(cc);
					}
				}
				//
			}
			//
		}

	}

	public void assignCC2Room(ClassCourse cc, Room r) {
		DA.result.add(new Pair_CourseRoom(cc, r));
		DA.mClassCourse2AssignedRoom.put(cc, r);
		//
		mRoom2ClassCourseList.get(r).add(cc);

		//
		markDaySlot_AsAssigned(cc, r);

		// mark as assigned
		int ccIdx = DA.mClassCourse2Index.get(cc);
		DA.ccIsAssigned[ccIdx] = true;

		//
		ClassFU cls = DA.mClassCourse2Class.get(cc);
		// System.out.println(cc.code);
		// System.out.println(cls.code);
		mClass2RoomList.get(cls).add(r);
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

	public void init_roomDemand() {
		mClassCourse2DaySlotList = new HashMap<ClassCourse, ArrayList<DaySlot>>();
		for (int cc = 0; cc < DA.nbClassCourse; cc++) {
			ClassCourse clc = DA.classCourses[cc];
			mClassCourse2DaySlotList.put(clc, new ArrayList<DaySlot>());
		}

		for (int cl = 0; cl < DA.nbClass; cl++) {
			// ClassFU cls = DA.classes[cl];
			SingleSolution ssol = sol[cl];
			// ArrayList<ClassCourse> ccList = DA.mClass2ClCourseList.get(cls);
			for (int day = 0; day < DA.nbDays_20; day++) {
				for (int slot = 0; slot < DA.nbSlotsPerHalfDay * 2; slot++) {
					int ccID = ssol.T[slot][day];
					if (ccID >= 0) { // neu co mon dang hoc
						DaySlot ds = new DaySlot(day, slot, false);
						ClassCourse cc = DA.mID2ClassCourse.get(ccID);
						mClassCourse2DaySlotList.get(cc).add(ds);
					}
				}
			}
		}
		/*
		 * for (int cc = 0; cc < 5; cc++) { ClassCourse clc =
		 * DA.classCourses[cc]; ClassFU cls = DA.mClassCourse2Class.get(clc);
		 * ArrayList<DaySlot> L = mClassCourse2DaySlotList.get(clc);
		 * System.out.print(clc.ID+ ", cc = "+cls.code+"-"+clc.code+
		 * ", current demand = "+currentDemandOfAClassCourse(clc)+": "); for
		 * (DaySlot daySlot : L) { System.out.print(" <"
		 * +daySlot.day+"-"+daySlot.slot+"-"+daySlot.isAssigned +">"); }
		 * System.out.println(""); }
		 */
	}

	public void init_roomSupply() {
		DA.availableCapacity = new int[DA.nbRoom];
		mRoom2DaySlotList = new HashMap<Room, ArrayList<DaySlot>>();
		for (int r = 0; r < DA.nbRoom; r++) {
			mRoom2DaySlotList.put(DA.rooms[r], new ArrayList<DaySlot>());
		}
		for (int r = 0; r < DA.nbRoom; r++) {
			Room room = DA.rooms[r];
			ArrayList<DaySlot> supplyList = mRoom2DaySlotList.get(room);
			for (int day = 0; day < DA.nbDays_20; day++) {
				for (int slot = 0; slot < DA.nbSlotsPerHalfDay * 2; slot++) {
					DaySlot ds = new DaySlot(day, slot, false);
					supplyList.add(ds);
				}
			}
			room.supplySlotCapacity = supplyList.size();
			// room.availableSlot = room.capacity;
			DA.availableCapacity[r] = room.supplySlotCapacity;
			// System.out.println("room = "+room.code+", capacity =
			// "+room.capacity+", available-slot = "+room.availableSlot);
			System.out.println("room = " + room.code + ", capacity = " + room.supplySlotCapacity
					+ ", available capacity = " + DA.availableCapacity[r]);
		}

	}

	/** Calculate demand of a set of cc */
	public HashMap<ClassCourse, ArrayList<DaySlot>> init_roomDemand(ArrayList<ClassCourse> ccList) {

		return null;
	}

	/** Calculate supply of a set of room */
	public HashMap<Room, ArrayList<DaySlot>> init_roomSupply(ArrayList<Room> rList) {

		return null;
	}

	/**
	 * Return current demand of a class course: number of slots that have not
	 * yet assigned to anyroom.
	 */
	public int currentDemandOfAClassCourse(ClassCourse cc) {
		int demand = 0;
		ArrayList<DaySlot> L = mClassCourse2DaySlotList.get(cc);
		for (DaySlot ds : L) {
			if (ds.isAssigned == false) {
				demand++;
			}
		}
		return demand;
	}

	public boolean isAvailableAsRequired(ClassCourse cc, Room r) {
		boolean asRequired = true;
		ArrayList<DaySlot> demandList = mClassCourse2DaySlotList.get(cc);
		ArrayList<DaySlot> supplyList = mRoom2DaySlotList.get(r);

		for (DaySlot dds : demandList) {
			if (dds.isAssigned == false) { // neu chua assign
				boolean assignable = false;
				for (DaySlot sds : supplyList) {
					if (dds.compareAllElement(sds)) {
						assignable = true;
					}
				}
				if (assignable == false) {
					return false;
				}
			}
		}
		return asRequired;
	}

	/** Chi mark as assigned, chua assign. */
	public void markDaySlot_AsAssigned(ClassCourse cc, Room r) {
		ArrayList<DaySlot> demandList = mClassCourse2DaySlotList.get(cc);
		ArrayList<DaySlot> supplyList = mRoom2DaySlotList.get(r);
		int rIdx = DA.mRoom2Index.get(r);

		int count = 0;
		for (DaySlot dds : demandList) {
			if (dds.isAssigned == false) { // neu chua assign
				for (DaySlot sds : supplyList) {
					if (dds.compareAllElement(sds) == true) {
						count++;
						dds.isAssigned = true;
						sds.isAssigned = true;
					}
				}
			}
		}
		// r.availableSlot -= count; //available slot of room r is reduced by
		// demand of cc.
		DA.availableCapacity[rIdx] -= count;
	}

	//////////////////////////////////////////////////////////////////////
	public static void main(String[] args) {
		AssignRoom ar = new AssignRoom();
		// ar.assignRoom3For();
		ar.assignRoomUsingFor("beingusedTT2.dat");
		// ar.testRoomClustering();

	}
}
