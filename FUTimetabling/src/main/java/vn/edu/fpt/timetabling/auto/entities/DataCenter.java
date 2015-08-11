package vn.edu.fpt.timetabling.auto.entities;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.apache.poi.util.SystemOutLogger;

import com.mysql.fabric.xmlrpc.base.Array;

import localsearch.model.VarIntLS;
import vn.edu.fpt.timetabling.auto.algorithms.MultiKnapsack;

public class DataCenter {

	public static final String FILENAME_PREFIX_DATAONECLASS = "datasm/class";
	// public static final String FILENAME_PREFIX_DATAONECLASS =
	// "datafall/class";
	public static final String FILENAME_PREFIX_ROOMCLUSTER = "data_room_cluster";
	public static final int NB_FRAGMENT_PERCOURSE_SHORTMODEL = 2;
	public static final int NB_FRAGMENT_PERCOURSE_FULLMODEL = 10;
	public static final int LIMIT_NBROOM_PERCOURSE = 12;
	public static final int NB_COURSETYPE = 5;
	public static final boolean BLOCKCLASSIFYING = false;

	public int nbDays_20 = 20;
	public int nbSlotsPerHalfDay = 3;

	public int nbClass;
	public ClassFU[] classes;
	public HashMap<Integer, ClassFU> mID2Class;
	public HashMap<ClassFU, Integer> mClass2Index;
	public HashMap<Integer, String> mClassID2Code;
	public HashMap<String, Integer> mClassCode2ID;
	public HashMap<ClassFU, CourseCounter> mClass2Counter;
	public HashMap<String, ClassFU> mCode2Class;

	public int nbDepartment;
	public Department[] departments;
	public HashMap<Integer, Department> mID2Department;
	public HashMap<String, Department> mCode2Department;
	public HashMap<Integer, String> mDepartmentID2Code;
	public HashMap<String, Integer> mDepartmentCode2ID;
	public HashMap<Department, Integer> mDepartment2Index;

	public int nbCourse;
	public Course[] courses;
	public HashMap<Integer, Course> mID2Course;
	public HashMap<Course, Integer> mCourse2Index;
	public HashMap<Integer, String> mCourseID2Code;
	public HashMap<String, Integer> mCourseCode2ID;
	public HashMap<String, Course> mCode2Course;
	public HashMap<Course, Department> mCourse2Department;
	public HashMap<Department, ArrayList<Course>> mDepartment2CourseList;

	public int nbClassCourse;
	public ClassCourse[] classCourses;
	public HashMap<Integer, ClassCourse> mID2ClassCourse;
	public HashMap<ClassCourse, Integer> mClassCourse2Index;
	public HashMap<Course, ArrayList<ClassCourse>> mCourse2ClassCourseList;
	public HashMap<ClassCourse, Course> mClassCourse2Course;
	public HashMap<ClassCourse, ClassFU> mClassCourse2Class;
	public HashMap<ClassFU, ArrayList<ClassCourse>> mClass2ClassCourseList;
	public HashMap<ClassFU, ArrayList<Course>> mClass2CourseList;
	public HashMap<Department, ArrayList<ClassCourse>> mDepartment2ClassCourseList;
	public HashMap<ClassCourse, Department> mClassCourse2Department;
	public HashMap<String, ClassCourse> mCode2ClassCourse;

	public int nbTeacher;
	public Teacher[] teachers;
	public HashMap<Course, ArrayList<Teacher>> mCourse2TeacherList;
	public HashMap<Teacher, ArrayList<Course>> mTeacher2CourseList;
	public HashMap<Department, ArrayList<Teacher>> mDepartment2TeacherList;
	public HashMap<Integer, Teacher> mID2Teacher;
	public HashMap<Teacher, Integer> mTeacher2Index;
	public HashMap<Integer, String> mTeacherID2Code;
	public HashMap<String, Integer> mTeacherCode2ID;
	public HashMap<String, Teacher> mCode2Teacher;
	public int[] lowerBoundnbClassCourseOfATeacher;
	public int[] teacherSupplied;

	public int nbBuilding;
	public Building[] buildings;
	public HashMap<Building, Integer> mBuidling2Index;
	public HashMap<Integer, Building> mID2Building;
	public HashMap<String, Integer> mBuildingCode2ID;
	public HashMap<Integer, String> mBuidlingID2Code;
	public HashMap<Building, ArrayList<Room>> mBuidling2RoomList;
	public HashMap<String, Building> mCode2Building;

	public int nbRoom;
	public Room[] rooms;
	public HashMap<Room, Integer> mRoom2Index;
	public HashMap<Integer, Room> mID2Room;
	public HashMap<String, Integer> mRoomCode2ID;
	public HashMap<Integer, String> mRoomID2Code;
	public HashMap<Room, ArrayList<DaySlot>> mRoom2DaySlotList;
	public HashMap<String, Room> mCode2Room;

	public int nbSoftSkillCourse;
	public int nbSoftSkillRoom;
	public ArrayList<Room> lSoftSkillRoom;
	public ArrayList<Course> lSoftSkillCourse;
	public HashMap<Course, Integer> mCourse2RoomLimit;
	public int[] aCourse2RoomLimit;

	public int nbCourseType;
	public ArrayList<Course>[] lClassifiedCourse;
	public ArrayList<Room>[] lCorrespondClassifiedRoom;

	////////////////////////////////////////////////////////////////////////
	// Merging
	public HashMap<ClassFU, Integer> mClass2NBStudent;
	public HashMap<ClassCourse, Integer> mClassCourse2nbStudent;
	public ArrayList<ClassCourse> lMergedClassCourse;
	public ArrayList<Pair_ClassCourseClass> lClassCourseGuestClass_Temp;
	public ArrayList<Pair_ClassCourseClass> lClassCourseGuestClassPair;
	public ArrayList<Pair_ClassCourse> lMustNotConflictClassCourse;
	// public ArrayList<ClassCourse> lneedToBeDeletedClassCourse;
	public ArrayList<Pair_ClassCourseClass> lneedToBeDeletedClassCourse;
	public int[][] mustNotConflictMatrix;

	/////////////////////////////////////////////////////////////////////////
	// POST IMPROVEMENT DATA
	public int nbPlace;
	public Place[] places;
	public int nbCoupleCoursePlaces;
	// public CoupleCoursePlace[] coupleCoursePlaces;
	public ArrayList<CoupleCoursePlace> lcoupleCoursePlaceList;
	public HashMap<Course, ArrayList<CoupleCoursePlace>> mCourse2CoupleCPList;
	public HashMap<Place, ArrayList<DaySlot>> mPlace2DaySlotList;
	public HashMap<CoupleCoursePlace, ArrayList<ClassCourse>> mCouple2ClassCourseList;
	public HashMap<CoupleCoursePlace, Integer> mCouple2FEachPlace;
	public HashMap<Course, Integer> mCourse2FStar;
	public HashMap<Course, ArrayList<ClassCourse>> mCourse2LStar;
	public HashMap<ClassCourse, Place> mClassCourse2Place;
	public HashMap<Course, ArrayList<ClassCourse>> mCourse2AllLStar;

	/////////////////////////////////////////////////////////////////////////
	// manualData template 1
	public HashMap<Course, Integer> mCourse2DemandMannual;
	public ArrayList<ClassCourse> lClassCourseMannual;
	public HashMap<ClassCourse, Integer> mClassCourse2SlotMannual;
	public HashMap<ClassCourse, Integer> mClassCourse2SessionMannual;
	public HashMap<ClassCourse, String> mClassCourse2RoomMannual;
	public HashMap<ClassCourse, Integer> mClassCourse2BlockMannual;
	public HashMap<ClassCourse, Course> mClassCourse2CourseMannual;
	public HashMap<ClassCourse, ClassFU> mClassCourse2ClassMannual;

	/////////////////////////////////////////////////////////////////////////
	// manualData Template 2
	public HashMap<ClassCourse, ArrayList<DaySlot>> mClassCourse2DaySlotList_manual;
	public SingleSolution[] classTTB_manual;
	public HashMap<Course, Integer> mCourse2Demand_manual;
	public HashMap<ClassCourse, ArrayList<VarIntLS>> mClassCourse2VarList;
	public HashMap<VarIntLS, ClassCourse> mVar2ClassCourse;

	/////////////////////////////////////////////////////////////////////////
	// assign teacher data
	public HashMap<Teacher, ArrayList<ClassCourse>> mTeacher2AssignedClassCourse;
	public HashMap<Course, ArrayList<Teacher>> mCourse2AssignedTeacher;
	public HashMap<ClassCourse, Teacher> mClassCourse2AssignedTeacher;

	public int[] aClassCourse2Session;
	public int[] aClassCourse2Slot;
	public int[] aClassCourse2Day;
	public int[] aClassCourse2Block;
	public int[] aClassCourse2Noon;
	public int[] aClassCourse2BeginEndDay;

	// room-class cluster
	public final static int NBCLUSTER = 3;
	public int[] nbRoomPerCluster;
	public int[] nbClassPerCluster;
	public ArrayList<Room>[] aRoomList;
	public ArrayList<Pair_RoomList_ClassList> result_classClustering;

	/**
	 * commonTeacherMatrix[i][j] = number of common teacher of two classes i and
	 * j.
	 */
	public int[][] commonTeacherMatrix;
	/**
	 * commonTeacherMatrix[i][j] = number of common teacher in the same day of
	 * two classes i and j.
	 */
	public int[][] commonTeacherSameDayMatrix;

	// assign room data
	public HashMap<ClassCourse, Room> mClassCourse2AssignedRoom;
	public ArrayList<Pair_CourseRoom> result;
	public ArrayList<ClassCourse> notyetassignedClassCourse;
	public int[] availableCapacity;
	public boolean[] ccIsAssigned;

	/**
	 * From merged cases, construct list of pair<cc1,cc2> where ClassCourse cc1
	 * must not conflict ClassCourse cc2.
	 */
	public void makeMustNotConflictClassCourseList() {
		lMustNotConflictClassCourse = new ArrayList<>();
		System.out.println("\nhost-guest classcourses:");
		for (Pair_ClassCourseClass pair : lClassCourseGuestClassPair) {
			ClassCourse cc = pair.classCourse;
			if (cc == null) {
				System.out.println("nullcc");
				System.exit(1);
			}
			Course c = mClassCourse2Course.get(cc);
			ClassFU hostClass = mClassCourse2Class.get(cc);
			ClassFU guestClass = pair.guestClass;
			if (hostClass == null) {
				System.out.println("nullhost");
				System.exit(1);
			}

			ArrayList<ClassCourse> L = mClass2ClassCourseList.get(guestClass);
			System.out.print("host = " + cc.code + "-" + hostClass.code + ", guest = " + guestClass.code + ", cc = :");
			for (ClassCourse classCourse : L) {
				System.out.print(" " + classCourse.code);
				Course ci = mClassCourse2Course.get(classCourse);
				if (c != ci) {
					lMustNotConflictClassCourse.add(new Pair_ClassCourse(cc, classCourse));
				}
			}
			System.out.println();
		}

		// ------------------------------------------
		int nbPair = lClassCourseGuestClassPair.size();
		boolean[] checked = new boolean[nbPair];

		for (int i = 0; i < nbPair - 1; i++) {
			if (checked[i] == false) {
				ClassFU guesti = lClassCourseGuestClassPair.get(i).guestClass;
				ClassCourse ci = lClassCourseGuestClassPair.get(i).classCourse;
				ArrayList<ClassCourse> L = new ArrayList<>();
				L.add(ci);
				for (int j = i + 1; j < nbPair; j++) {
					if (checked[j] == false) {
						ClassFU guestj = lClassCourseGuestClassPair.get(j).guestClass;
						ClassCourse cj = lClassCourseGuestClassPair.get(j).classCourse;
						if (guesti == guestj) {
							L.add(cj);
							checked[i] = true;
							checked[j] = true;
						}
					}
				}
				// make must not conflict pair for two host-classes of guest
				// class guesti
				for (int j1 = 0; j1 < L.size() - 1; j1++) {
					ClassCourse cc1 = L.get(j1);
					for (int j2 = j1 + 1; j2 < L.size(); j2++) {
						ClassCourse cc2 = L.get(j2);
						lMustNotConflictClassCourse.add(new Pair_ClassCourse(cc1, cc2));
					}
				}
			}
		}

		// ------------------------------------------
		System.out.println(
				"\nTotal: " + lMustNotConflictClassCourse.size() + " couples of must not conflict class-course:");
		for (Pair_ClassCourse pair : lMustNotConflictClassCourse) {
			ClassCourse cc1 = pair.cc1;
			ClassCourse cc2 = pair.cc2;
			ClassFU cls1 = mClassCourse2Class.get(cc1);
			ClassFU cls2 = mClassCourse2Class.get(cc2);
			System.out.println(cc1.code + "-" + cls1.code + "," + cc2.code + "-" + cls2.code);
		}

	}

	/**
	 * Make must not conflict matrix. matrix[i][j] = 1 if cc[i] and cc[j] must
	 * not conflict to each other, 0 otherwise.
	 */
	public void makeMustNotConflictMatrix() {
		mustNotConflictMatrix = new int[nbClassCourse][nbClassCourse];

		for (Pair_ClassCourse pair : lMustNotConflictClassCourse) {
			ClassCourse cc1 = pair.cc1;
			ClassCourse cc2 = pair.cc2;
			int idx1 = mClassCourse2Index.get(cc1);
			int idx2 = mClassCourse2Index.get(cc2);
			mustNotConflictMatrix[idx1][idx2] = 1;
			mustNotConflictMatrix[idx2][idx1] = 1;
		}

		/*
		 * for (int i = 0; i < nbClassCourse; i++) { for (int j = 0; j <
		 * nbClassCourse; j++) { System.out.print(mustNotConflictMatrix[i][j]+
		 * " "); } System.out.println(); }
		 */
	}

	/**
	 * Find ClassCourse object reference from course code & class code. Return
	 * null if not found.
	 */
	public ClassCourse findClassCourseFromCode(String courseCode, String classCode) {
		ClassCourse cc = null;
		if (mCourseCode2ID.get(courseCode) != null) { // classcourse code =
														// course code
			int cID = mCourseCode2ID.get(courseCode);
			if (mID2Course.get(cID) != null) {
				Course c = mID2Course.get(cID);
				ArrayList<ClassCourse> ccList = mCourse2ClassCourseList.get(c);
				for (ClassCourse classCourse : ccList) {
					ClassFU cls = mClassCourse2Class.get(classCourse);
					if (cls.code.compareToIgnoreCase(classCode) == 0) {
						cc = classCourse;
						return cc;
					}
				}
			}
		}
		return cc;
	}

	/**
	 * Find Class object reference from class code. Return null if not found.
	 */
	public ClassFU findClassFromCode(String classCode) {
		ClassFU cls = null;
		if (mClassCode2ID.get(classCode) != null) { // if classCode exists
			int ID = mClassCode2ID.get(classCode);
			if (mID2Class.get(ID) != null) { // if ID exists
				cls = mID2Class.get(ID);
			}
		}
		return cls;
	}

	public void blockClassifyANDGenOneClassData() {
		if (BLOCKCLASSIFYING) {
			blockClassifying();

			System.out.println("\nNumber of courses in block 1 and block 2 of a class after classifying:");
			for (int cl = 0; cl < nbClass; cl++) {
				ClassFU cls = classes[cl];
				CourseCounter c = mClass2Counter.get(cls);
				System.out.println("class = " + cls.code + ": block 1: " + c.nbCourseInBlock_1 + ", block 2: "
						+ c.nbCourseInBlock_2);
			}
		}

		// make each class data file
		// write to FINAME_frefix+'classidx'.txt
		for (int cl = 0; cl < nbClass; cl++) {
			genDataOneClassFile(FILENAME_PREFIX_DATAONECLASS, cl);
		}
	}

	public void genDataOneClassFile(String fnPrefix, int clIdx) {
		File f = new File(fnPrefix + clIdx + ".txt");
		try {
			FileOutputStream out = new FileOutputStream(f);
			DataOutputStream oos = new DataOutputStream(out);
			oos.writeBytes("#nbCourses\n");
			ClassFU cls = classes[clIdx];
			ArrayList<ClassCourse> L = mClass2ClassCourseList.get(cls);
			oos.writeBytes(L.size() + "");
			oos.writeBytes("\n#courses\n");
			for (int i = 0; i < L.size(); i++) {
				ClassCourse cc = L.get(i);
				oos.writeBytes(cc.ID + " ");
			}
			oos.writeBytes("\n#status\n");
			for (int i = 0; i < L.size(); i++) {
				ClassCourse cc = L.get(i);
				oos.writeBytes(cc.stt + " ");
			}
			oos.writeBytes("\n#nbFCourses_short\n");
			int nbfc_s = L.size() * NB_FRAGMENT_PERCOURSE_SHORTMODEL;
			oos.writeBytes(nbfc_s + "");
			oos.writeBytes("\n#FCourses_short\n");
			for (int fc = 0; fc < nbfc_s; fc++) {
				oos.writeBytes(fc + " ");
			}
			oos.writeBytes("\n#FCourse_short Course\n");
			int idC = -1;
			// int nbfc = L.size()*2;
			for (int i = 0; i < nbfc_s; i++) {
				if (i % NB_FRAGMENT_PERCOURSE_SHORTMODEL == 0) {
					idC++;
				}
				oos.writeBytes(i + " " + L.get(idC).ID + "\n");
				// System.out.println(i+" "+L.get(idC).ID);
			}
			oos.writeBytes(-1 + "\n");
			oos.writeBytes("nbFCourses\n");
			int nbfc_f = L.size() * NB_FRAGMENT_PERCOURSE_FULLMODEL;
			oos.writeBytes(nbfc_f + "\n");
			oos.writeBytes("FCourses\n");
			for (int i = 0; i < nbfc_f; i++) {
				oos.writeBytes(i + " ");
			}
			oos.writeBytes("\n#FCourse Course\n");
			idC = -1;
			// int nbfc = L.size()*2;
			for (int i = 0; i < nbfc_f; i++) {
				if (i % NB_FRAGMENT_PERCOURSE_FULLMODEL == 0) {
					idC++;
				}
				oos.writeBytes(i + " " + L.get(idC).ID + "\n");
				// System.out.println(i+" "+L.get(idC).ID);
			}
			oos.writeBytes(-1 + "\n");

			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void blockClassifying() {
		System.out.println("\nCourses will be classified:");
		for (int i = 0; i < nbCourse; i++) {
			Course c = courses[i];
			ArrayList<ClassCourse> L = mCourse2ClassCourseList.get(c);

			// xem can classify ko: OK
			boolean shouldchange = true;

			// System.out.print("course = "+c.code);
			for (int j = 0; j < L.size(); j++) {
				ClassCourse cc = L.get(j);
				if (cc.stt != 0) {
					shouldchange = false;
					// System.out.print(": dont classify");
					break;
				}
			}
			// System.out.println();
			// tim free stt
			boolean block1 = true;
			boolean block2 = true;
			if (shouldchange == true) {
				for (int j = 0; j < L.size(); j++) {
					ClassCourse cc = L.get(j);
					ClassFU cls = mClassCourse2Class.get(cc);
					if (mClass2Counter.get(cls).nbCourseInBlock_1 >= 2) {
						block1 = false;
					}
					if (mClass2Counter.get(cls).nbCourseInBlock_2 >= 2) {
						block2 = false;
					}
				}

				/////
				// classifying
				if (block1 == true || block2 == true) {
					System.out.print("c = " + c.code + ": ");
					for (int j = 0; j < L.size(); j++) {
						ClassCourse cc = L.get(j);
						ClassFU cls = mClassCourse2Class.get(cc);
						System.out.print(cls.code);
						System.out.print(":<" + mClass2Counter.get(cls).nbCourseInBlock_1 + ",");
						System.out.print(mClass2Counter.get(cls).nbCourseInBlock_2 + ">, ");
					}
					System.out.println();
					if (block1 && block2) { // cho vao block 1 or 2
						// tinh can bang
						// block nao co so mon nho hon ko ke mon nay thi
						// them vao
						int nbCourseHasSTT_is1 = 0;
						int nbCourseHasSTT_is2 = 0;
						for (int j = 0; j < nbClassCourse; j++) {
							ClassCourse cc = classCourses[j];
							if (!L.contains(cc)) {
								if (cc.stt == 1) {
									nbCourseHasSTT_is1++;
								} else if (cc.stt == 2) {
									nbCourseHasSTT_is2++;
								}
							}
						}
						if (nbCourseHasSTT_is1 < nbCourseHasSTT_is2) {
							for (int j = 0; j < L.size(); j++) {
								ClassCourse cc = L.get(j);
								ClassFU cls = mClassCourse2Class.get(cc);
								CourseCounter counter = mClass2Counter.get(cls);
								counter.nbCourseInBlock_1++;
								cc.stt = 1;
							}
						} else if (nbCourseHasSTT_is1 > nbCourseHasSTT_is2) {
							for (int j = 0; j < L.size(); j++) {
								ClassCourse cc = L.get(j);
								ClassFU cls = mClassCourse2Class.get(cc);
								CourseCounter counter = mClass2Counter.get(cls);
								counter.nbCourseInBlock_2++;
								cc.stt = 2;
							}
						} else {// bang nhau thi random
							Random r = new Random();
							// boolean block = r.nextBoolean();
							int blk = r.nextInt();
							if (blk % 2 == 0) {
								for (int j = 0; j < L.size(); j++) {
									ClassCourse cc = L.get(j);
									ClassFU cls = mClassCourse2Class.get(cc);
									CourseCounter counter = mClass2Counter.get(cls);
									counter.nbCourseInBlock_1++;
									cc.stt = 1;
								}
							} else {
								for (int j = 0; j < L.size(); j++) {
									ClassCourse cc = L.get(j);
									ClassFU cls = mClassCourse2Class.get(cc);
									CourseCounter counter = mClass2Counter.get(cls);
									counter.nbCourseInBlock_2++;
									cc.stt = 2;
								}
							}
						}

					} else if (block1) { // cho vao block 1
						for (int j = 0; j < L.size(); j++) {
							ClassCourse cc = L.get(j);
							ClassFU cls = mClassCourse2Class.get(cc);
							CourseCounter counter = mClass2Counter.get(cls);
							counter.nbCourseInBlock_1++;
							cc.stt = 1;
						}
					} else { // cho vao block 2
						for (int j = 0; j < L.size(); j++) {
							ClassCourse cc = L.get(j);
							ClassFU cls = mClassCourse2Class.get(cc);
							CourseCounter counter = mClass2Counter.get(cls);
							counter.nbCourseInBlock_2++;
							cc.stt = 2;
						}
					}
				}

			}

		} // end of course for
	}

	public void loadData_SoftSkill(String fn) {
		System.out.println();
		try {
			Scanner in = new Scanner(new File(fn));
			String line = "";
			line = in.nextLine();
			System.out.println(line);
			nbSoftSkillCourse = in.nextInt();
			System.out.println("" + nbSoftSkillCourse);
			line = in.nextLine();
			line = in.nextLine();
			System.out.println(line);

			lSoftSkillCourse = new ArrayList<Course>();
			mCourse2RoomLimit = new HashMap<Course, Integer>();
			for (Course c : courses) {
				mCourse2RoomLimit.put(c, nbRoom);
			}
			aCourse2RoomLimit = new int[nbCourse];
			for (int i = 0; i < aCourse2RoomLimit.length; i++) {
				aCourse2RoomLimit[i] = nbRoom;
			}

			int idx = -1;
			while (true) {
				idx++;
				String code = in.next();
				if (code.compareToIgnoreCase("-1") == 0 || idx >= nbSoftSkillCourse) {
					break;
				}
				int roomLimit = in.nextInt();
				line = in.nextLine();

				// System.out.println(code);
				if (mCourseCode2ID.get(code) != null) { // problem
					int cID = mCourseCode2ID.get(code);
					Course c = mID2Course.get(cID);
					lSoftSkillCourse.add(c);
					int cIdx = mCourse2Index.get(c);
					aCourse2RoomLimit[cIdx] = roomLimit;
				}
			}
			for (Course c : lSoftSkillCourse) {
				int cIdx = mCourse2Index.get(c);
				System.out.println(c.code + ", room limit = " + aCourse2RoomLimit[cIdx]);

			}

			line = in.nextLine();
			line = in.nextLine();
			System.out.println(line);
			nbSoftSkillRoom = in.nextInt();
			System.out.println(nbSoftSkillRoom);
			line = in.nextLine();
			line = in.nextLine();
			System.out.println(line);

			lSoftSkillRoom = new ArrayList<Room>();
			idx = -1;
			while (true) {
				idx++;
				String code = in.next(); // line = in.nextLine();
				code = code.toLowerCase();
				if (code.compareToIgnoreCase("-1") == 0 || idx >= nbSoftSkillRoom) {
					break;
				}
				// System.out.println(code);
				if (mRoomCode2ID.get(code) != null) { // problem
					int cID = mRoomCode2ID.get(code);
					Room r = mID2Room.get(cID);
					lSoftSkillRoom.add(r);
				}
			}
			// System.out.println(softSkillRoom.size());
			for (Room r : lSoftSkillRoom) {
				System.out.println(r.code);
			}

			in.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("Done loading softskill room & classcourse!");
	}

	public void testLoadData_CourseRoom() {
		lClassifiedCourse = new ArrayList[NB_COURSETYPE];
		lCorrespondClassifiedRoom = new ArrayList[NB_COURSETYPE];
		for (int type = 0; type < NB_COURSETYPE; type++) {
			lClassifiedCourse[type] = new ArrayList<Course>();
			lCorrespondClassifiedRoom[type] = new ArrayList<Room>();
			loadData_CourseRoom("type" + (type + 1) + ".txt", type);
		}

		for (int type = 0; type < NB_COURSETYPE; type++) {
			System.out.println("\nType no." + (type + 1));
			System.out.print("Course:");
			for (Course c : lClassifiedCourse[type]) {
				System.out.print(" " + c.code);
			}
			System.out.print("\nRoom: ");
			for (Room r : lCorrespondClassifiedRoom[type]) {
				System.out.print(" " + r.code);
			}
			System.out.println();
		}
	}

	/////////////////////////////////////////////////////////////////////////
	/** Load merged cases: data format: ClassCourse-HostClass-GuestClass */
	public void loadData_mergedCases(String fn) {
		try {
			Scanner in = new Scanner(new File(fn));
			String line = new String();
			line = in.nextLine();
			System.out.println();
			System.out.println(line);
			int nbCases = in.nextInt();
			line = in.nextLine();
			System.out.println(nbCases);
			line = in.nextLine();
			System.out.println(line);

			lClassCourseGuestClassPair = new ArrayList<>();
			int idx = -1;
			while (true) {
				idx++;
				String ccCode = in.next();
				if (idx >= nbCases || ccCode.compareToIgnoreCase("-1") == 0) {
					break;
				}
				String hostClassCode = in.next();
				String guestClassCode = in.next();
				ccCode = ccCode.toUpperCase();
				hostClassCode = hostClassCode.toUpperCase();
				guestClassCode = guestClassCode.toUpperCase();
				line = in.nextLine();
				// System.out.println("case: "+ccCode+", host =
				// "+hostClassCode+", guest = "+guestClassCode);
				ClassCourse cc = findClassCourseFromCode(ccCode, hostClassCode);
				ClassFU hostClass = findClassFromCode(hostClassCode);
				ClassFU guestClass = findClassFromCode(guestClassCode);
				if (cc != null & hostClass != null && guestClass != null) {
					// System.out.println("case: "+cc.code+", host =
					// "+hostClass.code+", guest = "+guestClass.code);
					// System.out.println();
					lClassCourseGuestClassPair.add(new Pair_ClassCourseClass(cc, guestClass));
				}
			}

			for (Pair_ClassCourseClass pair : lClassCourseGuestClassPair) {
				ClassCourse cc = pair.classCourse;
				ClassFU hostClass = mClassCourse2Class.get(cc);
				ClassFU guestClass = pair.guestClass;
				System.out.println("case: " + cc.code + ", host = " + hostClass.code + ", guest = " + guestClass.code);
				// System.out.println();
			}

			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void loadData_mergedCases(List<String> mergeData) {
		try {
			int nbCases = mergeData.size();
			System.out.println(nbCases);

			lClassCourseGuestClassPair = new ArrayList<>();
			for (int i = 0; i < nbCases; i++) {
				String mergeTemp = mergeData.get(i);
				StringTokenizer stringTokenizer = new StringTokenizer(mergeTemp, "|");
				String ccCode = stringTokenizer.nextToken().toUpperCase();
				String hostClassCode = stringTokenizer.nextToken().toUpperCase();
				String guestClassCode = stringTokenizer.nextToken().toUpperCase();
				ClassCourse cc = findClassCourseFromCode(ccCode, hostClassCode);
				ClassFU hostClass = findClassFromCode(hostClassCode);
				ClassFU guestClass = findClassFromCode(guestClassCode);
				if (cc != null & hostClass != null && guestClass != null) {
					// System.out.println("case: "+cc.code+", host =
					// "+hostClass.code+", guest = "+guestClass.code);
					// System.out.println();
					lClassCourseGuestClassPair.add(new Pair_ClassCourseClass(cc, guestClass));
				}
			}

			for (Pair_ClassCourseClass pair : lClassCourseGuestClassPair) {
				ClassCourse cc = pair.classCourse;
				ClassFU hostClass = mClassCourse2Class.get(cc);
				ClassFU guestClass = pair.guestClass;
				System.out.println("case: " + cc.code + ", host = " + hostClass.code + ", guest = " + guestClass.code);
				// System.out.println();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void loadData_Course_Class(String fn) {
		// class + course
		try {
			Scanner in = new Scanner(new File(fn));
			String line = new String();
			line = in.nextLine();
			nbClass = in.nextInt();
			System.out.println("nbClass = " + nbClass);
			line = in.nextLine();
			line = in.nextLine();

			classes = new ClassFU[nbClass];
			mID2Class = new HashMap<Integer, ClassFU>();
			mClass2Index = new HashMap<ClassFU, Integer>();
			mClassID2Code = new HashMap<Integer, String>();
			mClassCode2ID = new HashMap<String, Integer>();

			int idx = -1;
			while (true) {
				int id = in.nextInt();
				if (id == -1) {
					break;
				}
				String code = in.next();
				idx++;

				ClassFU cl = new ClassFU(id, code);
				System.out.println("idx = " + idx + ", classID " + cl.ID + ", code = " + cl.code);
				classes[idx] = cl;
				mID2Class.put(id, cl);
				mClass2Index.put(cl, idx);
				mClassID2Code.put(id, code);
				mClassCode2ID.put(code, id);
			}
			line = in.nextLine();
			line = in.nextLine();
			nbCourse = in.nextInt();
			System.out.println("nbCourse = " + nbCourse);
			line = in.nextLine();
			line = in.nextLine();

			courses = new Course[nbCourse];
			mID2Course = new HashMap<Integer, Course>();
			mCourse2Index = new HashMap<Course, Integer>();
			mCourseCode2ID = new HashMap<String, Integer>();
			mCourseID2Code = new HashMap<Integer, String>();

			idx = -1;
			while (true) {
				int id = in.nextInt();
				if (id == -1)
					break;
				String code = in.next();
				idx++;
				Course c = new Course(id, code);
				courses[idx] = c;
				mID2Course.put(id, c);
				mCourse2Index.put(c, idx);
				mCourseCode2ID.put(code, id);
				mCourseID2Code.put(id, code);

				System.out.println("idx = " + idx + ", Course " + c.ID + ", code = " + c.code);
			}
			line = in.nextLine();
			line = in.nextLine();
			nbClassCourse = in.nextInt();
			System.out.println("nbClassCourse = " + nbClassCourse);
			line = in.nextLine();
			line = in.nextLine();

			classCourses = new ClassCourse[nbClassCourse];
			mID2ClassCourse = new HashMap<Integer, ClassCourse>();
			mClassCourse2Index = new HashMap<ClassCourse, Integer>();
			mClassCourse2Course = new HashMap<ClassCourse, Course>();
			mClassCourse2Class = new HashMap<ClassCourse, ClassFU>();
			mClass2Counter = new HashMap<ClassFU, CourseCounter>();

			mClass2ClassCourseList = new HashMap<ClassFU, ArrayList<ClassCourse>>();
			for (int i = 0; i < nbClass; i++) {
				mClass2ClassCourseList.put(classes[i], new ArrayList<ClassCourse>());
			}
			mCourse2ClassCourseList = new HashMap<Course, ArrayList<ClassCourse>>();
			for (int i = 0; i < nbCourse; i++) {
				mCourse2ClassCourseList.put(courses[i], new ArrayList<ClassCourse>());
			}
			mClass2CourseList = new HashMap<ClassFU, ArrayList<Course>>();
			for (int i = 0; i < nbClass; i++) {
				mClass2CourseList.put(classes[i], new ArrayList<Course>());
			}
			for (int i = 0; i < nbClass; i++) {
				mClass2Counter.put(classes[i], new CourseCounter(0, 0));
			}

			idx = -1;
			while (true) {
				int id = in.nextInt();
				if (id == -1)
					break;
				int cID = in.nextInt();
				int clID = in.nextInt();
				int stt = in.nextInt();
				idx++;

				Course C = mID2Course.get(cID);
				ClassCourse cc = new ClassCourse(id, C.code, stt);
				classCourses[idx] = cc;
				ClassFU cls = mID2Class.get(clID);

				mClassCourse2Class.put(cc, cls);
				mClassCourse2Course.put(cc, C);
				mID2ClassCourse.put(id, cc);
				mClassCourse2Index.put(cc, idx);

				mCourse2ClassCourseList.get(C).add(cc);
				mClass2ClassCourseList.get(cls).add(cc);
				mClass2CourseList.get(cls).add(C);

				//
				if (stt == 1) {
					mClass2Counter.get(cls).nbCourseInBlock_1++;
				} else if (stt == 2) {
					mClass2Counter.get(cls).nbCourseInBlock_2++;
				}

				// System.out.println("clCourse = "+id+", course = "+cID+",
				// class = "+clID+", stt = "+stt);
				System.out.println(
						"clCourse = " + cc.ID + ", course = " + C.ID + ", class = " + cls.ID + ", stt = " + cc.stt);
				// System.out.println("--");
			}

			System.out.println("\nClass-Courses:");
			for (int i = 0; i < nbClass; i++) {
				ClassFU cl = classes[i];
				ArrayList<ClassCourse> L = new ArrayList<ClassCourse>();
				L = mClass2ClassCourseList.get(cl);
				System.out.print("Class = " + cl.code + ":");
				for (ClassCourse cc : L) {
					Course c = mClassCourse2Course.get(cc);
					System.out.print(" " + c.code + "");
				}
				System.out.println();
			}

			System.out.println("\nCourse-Classes:");
			for (int i = 0; i < nbCourse; i++) {
				Course c = courses[i];
				ArrayList<ClassCourse> L = new ArrayList<ClassCourse>();
				L = mCourse2ClassCourseList.get(c);
				System.out.print("Course = " + c.code + "-<" + L.size() + ">:");
				for (ClassCourse cc : L) {
					ClassFU cl = mClassCourse2Class.get(cc);
					// Course c = mClassCourse2Course.get(cc);
					System.out.print(" " + cl.code);
				}
				System.out.println();
			}

			/*
			 * System.out.println(
			 * "\nNumber of courses in block 1 and block 2 of a class:"); for
			 * (int cl = 0; cl < nbClass; cl++) { ClassFU cls = classes[cl];
			 * CourseCounter c = mClass2Counter.get(cls); System.out.println(
			 * "class = "+cls.code+": block 1: "+c.nbCourseInBlock_1+
			 * ", block 2: "+c.nbCourseInBlock_2); }
			 */

			// make each class data file
			// write to FINAME_frefix+'classidx'.txt
			/*
			 * for (int cl = 0; cl < nbClass; cl++) {
			 * genDataOneClassFile(FINAME_frefix, cl); }
			 */

			in.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("Done loading common data!");
	}

	public void loadData_CourseRoom(String fn, int typeIdx) {
		try {
			Scanner in = new Scanner(new File(fn));
			String line = in.nextLine();
			System.out.println(line);
			int typeNo = in.nextInt();
			System.out.println(typeNo);
			line = in.nextLine();
			line = in.nextLine();
			System.out.println(line);
			int size1 = in.nextInt();
			line = in.nextLine();
			System.out.println(size1);
			line = in.nextLine();
			System.out.println(line);
			int idx = -1;
			while (true) {
				idx++;
				String cCode = in.next();
				if (cCode.compareToIgnoreCase("-1") == 0 || idx >= size1) {
					break;
				}
				if (mCourseCode2ID.get(cCode) != null) {
					System.out.println(cCode);
					int cID = mCourseCode2ID.get(cCode);
					Course c = mID2Course.get(cID);
					lClassifiedCourse[typeIdx].add(c);
				}
			}

			line = in.nextLine();
			line = in.nextLine();
			System.out.println(line);
			int size2 = in.nextInt();
			line = in.nextLine();
			System.out.println(size2);
			line = in.nextLine();
			System.out.println(line);
			idx = -1;
			while (true) {
				idx++;
				String rCode = in.next();
				if (rCode.compareToIgnoreCase("-1") == 0 || idx >= size2) {
					break;
				}
				line = in.nextLine();
				if (mRoomCode2ID.get(rCode) != null) {
					System.out.println(rCode);
					int rID = mRoomCode2ID.get(rCode);
					Room r = mID2Room.get(rID);
					lCorrespondClassifiedRoom[typeIdx].add(r);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		// load type

		// load each type
		// print out
	}

	public void loadData_Teacher(String fn) {
		System.out.println();
		try {
			Scanner in = new Scanner(new File(fn));
			String line = "";
			line = in.nextLine();
			System.out.println(line);
			nbTeacher = in.nextInt();
			System.out.println("nbTeacher = " + nbTeacher);

			line = in.nextLine();
			line = in.nextLine();
			System.out.println(line);
			teachers = new Teacher[nbTeacher];
			mID2Teacher = new HashMap<Integer, Teacher>();
			mTeacher2Index = new HashMap<Teacher, Integer>();
			mTeacherCode2ID = new HashMap<String, Integer>();
			mTeacherID2Code = new HashMap<Integer, String>();

			int idx = 0;
			while (true) {
				int tID = in.nextInt();
				if (tID == -1) {
					break;
				}
				String code = in.next();
				// System.out.println("id = "+tID+", code = "+code);
				Teacher tc = new Teacher(tID, code);
				teachers[idx] = tc;
				mTeacher2Index.put(tc, idx);
				mID2Teacher.put(tID, tc);
				mTeacherID2Code.put(tID, code);
				mTeacherCode2ID.put(code, tID);

				idx++;
			}
			for (int i = 0; i < nbTeacher; i++) {
				System.out.println("id = " + teachers[i].ID + ", code = " + teachers[i].code);
			}

			line = in.nextLine();
			line = in.nextLine();
			System.out.println(line);
			int nbcourse = in.nextInt();
			if (nbcourse != nbCourse) {
				System.out.println("BUG in teacher data, different nbCourse");
			}
			line = in.nextLine();
			line = in.nextLine();
			System.out.println(line);

			teacherSupplied = new int[nbCourse];
			mCourse2TeacherList = new HashMap<Course, ArrayList<Teacher>>();
			mTeacher2CourseList = new HashMap<Teacher, ArrayList<Course>>();
			for (int c = 0; c < nbCourse; c++) {
				mCourse2TeacherList.put(courses[c], new ArrayList<Teacher>());
			}
			for (int t = 0; t < nbTeacher; t++) {
				mTeacher2CourseList.put(teachers[t], new ArrayList<Course>());
			}

			while (true) {
				int cID = in.nextInt();
				if (cID == -1) {
					break;
				}
				int supply = in.nextInt();
				if (supply == 0) {// no supply
					System.out.println("id = " + mCourseID2Code.get(cID) + ", supply = " + supply + ", tc: None");
					// Course c = mID2Course_all.get(cID);
				} else {
					line = in.nextLine();
					Course c = mID2Course.get(cID);
					int cIdx = mCourse2Index.get(c);
					teacherSupplied[cIdx] = supply;
					String[] temp = new String[supply];
					line = line.trim();
					temp = line.split("\\s+");
					int[] tID = new int[temp.length];
					// System.out.println(temp.length +" "+ supply+"
					// "+(temp.length-supply));

					// System.out.println("id = "+cID+", supply = "+supply+",
					// line = "+line.trim());
					System.out.print("id = " + mCourseID2Code.get(cID) + ", supply = " + supply + ", tc: ");
					for (int i = 0; i < temp.length; i++) {
						tID[i] = Integer.parseInt(temp[i]);

						Teacher t = mID2Teacher.get(tID[i]);
						System.out.print(", " + mTeacherID2Code.get(t.ID));
						// System.out.print("tid = "+t.ID);
						mCourse2TeacherList.get(c).add(t);
						mTeacher2CourseList.get(t).add(c);
					}
					System.out.println();
				}

			}
			System.out.println();
			for (Teacher t : teachers) {
				ArrayList<Course> L = mTeacher2CourseList.get(t);
				System.out.print("tc = " + t.code + ": ");
				for (Course co : L) {
					System.out.print("" + co.code + " ");
				}
				System.out.println();
			}
			System.out.println();
			for (Course co : courses) {
				ArrayList<Teacher> L = mCourse2TeacherList.get(co);
				System.out.print("c = " + co.code + ": ");
				for (Teacher t : L) {
					System.out.print("" + t.code + " ");
				}
				System.out.println();
			}

			in.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("Done loading teacher data!");
	}

	public void loadData_Teacher_UsingCode(String fn) {
		System.out.println();
		try {
			Scanner in = new Scanner(new File(fn));
			String line = "";
			line = in.nextLine();
			System.out.println(line);
			nbTeacher = in.nextInt();
			System.out.println("nbTeacher = " + nbTeacher);

			line = in.nextLine();
			line = in.nextLine();
			System.out.println(line);
			teachers = new Teacher[nbTeacher];
			mID2Teacher = new HashMap<Integer, Teacher>();
			mTeacher2Index = new HashMap<Teacher, Integer>();
			mTeacherCode2ID = new HashMap<String, Integer>();
			mTeacherID2Code = new HashMap<Integer, String>();

			int idx = 0;
			while (true) {
				int tID = in.nextInt();
				if (tID == -1) {
					break;
				}
				String code = in.next();
				// System.out.println("id = "+tID+", code = "+code);
				Teacher tc = new Teacher(tID, code);
				teachers[idx] = tc;
				mTeacher2Index.put(tc, idx);
				mID2Teacher.put(tID, tc);
				mTeacherID2Code.put(tID, code);
				mTeacherCode2ID.put(code, tID);

				idx++;
			}
			for (int i = 0; i < nbTeacher; i++) {
				System.out.println("id = " + teachers[i].ID + ", code = " + teachers[i].code);
			}

			line = in.nextLine();
			line = in.nextLine();
			System.out.println(line);
			int nbcourse = in.nextInt();
			if (nbcourse != nbCourse) {
				System.out.println("BUG in teacher data, different nbCourse");
			}
			line = in.nextLine();
			line = in.nextLine();
			System.out.println(line);

			teacherSupplied = new int[nbCourse];
			mCourse2TeacherList = new HashMap<Course, ArrayList<Teacher>>();
			mTeacher2CourseList = new HashMap<Teacher, ArrayList<Course>>();
			for (int c = 0; c < nbCourse; c++) {
				mCourse2TeacherList.put(courses[c], new ArrayList<Teacher>());
			}
			for (int t = 0; t < nbTeacher; t++) {
				mTeacher2CourseList.put(teachers[t], new ArrayList<Course>());
			}

			while (true) {
				int cID = in.nextInt();
				if (cID == -1) {
					break;
				}
				int supply = in.nextInt();
				if (supply == 0) {// no supply
					System.out.println("id = " + mCourseID2Code.get(cID) + ", supply = " + supply + ", tc: None");
					// Course c = mID2Course_all.get(cID);
					line = in.nextLine();
				} else {
					// line = in.nextLine();
					Course c = mID2Course.get(cID);
					int cIdx = mCourse2Index.get(c);
					teacherSupplied[cIdx] = supply;
					String[] temp = new String[supply];
					// line = line.trim();
					// temp = line.split("\\s+");

					// System.out.print("id = "+ cID+", code = "+c.code+": ");
					for (int i_t = 0; i_t < supply; i_t++) {
						// line = in.next();
						temp[i_t] = in.next().toLowerCase();
						// System.out.print(" '"+temp[i_t]+"' ");
					}
					// System.out.println();

					// System.out.print("code = "+mCourseID2Code.get(cID)+",
					// supply = "+supply+", tc: ");
					for (int i = 0; i < temp.length; i++) {
						if (mTeacherCode2ID.containsKey(temp[i])) {
							int tID = mTeacherCode2ID.get(temp[i]);
							Teacher t = mID2Teacher.get(tID);
							// System.out.print(", "+mTeacherID2Code.get(t.ID));
							// System.out.print("["+temp[i]+","+t.code+"]");
							mCourse2TeacherList.get(c).add(t);
							mTeacher2CourseList.get(t).add(c);
						} else {
							System.out.println("There is no teacher has code = " + temp[i]);
							System.exit(1);
						}

					}

					line = in.nextLine();
					/*
					 * if (line == null) { System.out.println("null me"); } else
					 * { System.out.println("not null"); }
					 */
				}

			}
			System.out.println("\nDifferent courses a teacher can teach:");
			for (Teacher t : teachers) {
				ArrayList<Course> L = mTeacher2CourseList.get(t);
				System.out.print("tc = " + t.code + "-<" + L.size() + ">:");
				for (Course co : L) {
					System.out.print(" " + co.code + "");
				}
				System.out.println();
			}

			System.out.println("\nNumber of class-courses a teacher can teach:");
			for (Teacher t : teachers) {
				ArrayList<Course> L = mTeacher2CourseList.get(t);
				int count = 0;
				for (Course co : L) {
					ArrayList<ClassCourse> CCL = mCourse2ClassCourseList.get(co);
					count += CCL.size();
				}
				System.out.println("teacher = " + t.code + ": " + count);
			}

			System.out.println("\nLower bound of number of class-courses a teacher can teach:");
			lowerBoundnbClassCourseOfATeacher = new int[nbTeacher];
			for (Teacher t : teachers) {
				ArrayList<Course> L = mTeacher2CourseList.get(t);
				int count = 0;
				int temp1 = 0;
				int temp2 = 0;
				for (Course co : L) {
					ArrayList<ClassCourse> CCL = mCourse2ClassCourseList.get(co);
					ArrayList<Teacher> TL = mCourse2TeacherList.get(co);
					if (!TL.isEmpty()) {
						if (CCL.size() > TL.size()) {
							count += (int) Math.floor((double) CCL.size() / TL.size());
						} else {
							temp1 += CCL.size();
							if (temp2 < TL.size()) {
								temp2 = TL.size();
							}
						}
					}
				}
				count += (int) Math.floor((double) temp1 / temp2);

				/*
				 * //test co nguyenltt if
				 * (t.code.compareToIgnoreCase("nguyenltt")==0) {
				 * System.out.print("tcode = nguyenltt: "); System.out.print(
				 * "L = "+L.size()+": "); for (Course co : L) {
				 * ArrayList<ClassCourse> CCL = mCourse2ClassCourseList.get(co);
				 * ArrayList<Teacher> TL = mCourse2TeacherList.get(co); // if
				 * (!TL.isEmpty()) { // temp = (int)
				 * Math.floor((double)CCL.size()/TL.size()); // count += temp;
				 * // } System.out.print("<"+CCL.size()+","+TL.size()+"> "); }
				 * System.out.println(); }
				 */
				int tIdx = mTeacher2Index.get(t);
				if (count < 4) {
					lowerBoundnbClassCourseOfATeacher[tIdx] = count;
				} else {
					lowerBoundnbClassCourseOfATeacher[tIdx] = 4;
				}
				System.out.println("tc = " + t.code + ": " + lowerBoundnbClassCourseOfATeacher[tIdx] + "");
				/*
				 * for (Course co : L) { ArrayList<ClassCourse> CCL =
				 * mCourse2ClassCourseList.get(co); for (ClassCourse cc : CCL) {
				 * System.out.print(" "+cc.code); } } System.out.println();
				 */
			}

			System.out.println("\nDifferent teachers a course can be taught:");
			for (Course co : courses) {
				ArrayList<Teacher> L = mCourse2TeacherList.get(co);
				System.out.print("c = " + co.code + "-<" + L.size() + ">:");
				for (Teacher t : L) {
					System.out.print(" " + t.code + "");
				}
				System.out.println();
			}

			in.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("Done loading teacher data using code!");
	}

	/** bỏ */
	public void loadData_Course_Code(String fn) {
		try {
			Scanner in = new Scanner(new File(fn));
			String line = "";
			line = in.nextLine();
			System.out.println(line);
			int nbcourse = in.nextInt();
			if (nbcourse != nbCourse) {
				System.out.println("BUG in loading course-code");
			}
			line = in.nextLine();
			line = in.nextLine();
			System.out.println(line);
			mClassID2Code = new HashMap<Integer, String>();
			while (true) {
				int id = in.nextInt();
				if (id == -1) {
					break;
				}
				String code = in.next();
				// System.out.println("id = "+id+", code = "+code);
				mClassID2Code.put(id, code);
			}

			in.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("Done loading course-code data!");
	}

	public void loadData_Course_weCode(String fn) {
		try {
			Scanner in = new Scanner(new File(fn));
			String line = "";
			line = in.nextLine();
			System.out.println(line);
			int nbcourse = in.nextInt();
			if (nbcourse != nbCourse) {
				System.out.println("BUG in loading course-code");
			}
			line = in.nextLine();
			line = in.nextLine();
			System.out.println(line);
			mClassID2Code = new HashMap<Integer, String>();
			while (true) {
				int id = in.nextInt();
				if (id == -1) {
					break;
				}
				String code = in.next();
				// System.out.println("id = "+id+", code = "+code);
				mClassID2Code.put(id, code);
			}

			/*
			 * for (int c = 0; c < nbCourse; c++) { System.out.println("id = "
			 * +courses[c].ID+", code = "+mID2Code.get(courses[c].ID)); }
			 */

			in.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("Done loading course-code data!");
	}

	public void loadData_Room_Building(String fn) {
		try {
			Scanner in = new Scanner(new File(fn));
			String line = "";

			// load building data
			line = in.nextLine();
			System.out.println();
			System.out.println(line);
			nbBuilding = in.nextInt();
			System.out.println(nbBuilding);
			line = in.nextLine();
			line = in.nextLine();
			System.out.println(line);

			mBuidling2Index = new HashMap<Building, Integer>();
			buildings = new Building[nbBuilding];
			mBuidlingID2Code = new HashMap<Integer, String>();
			mBuildingCode2ID = new HashMap<String, Integer>();
			mID2Building = new HashMap<Integer, Building>();

			int idx = -1;
			while (true) {
				idx++;
				if (idx >= nbBuilding) {
					break;
				}
				int bID = in.nextInt();
				if (bID == -1) {
					break;
				}
				String code = in.next();

				Building bd = new Building(bID, code);
				buildings[idx] = bd;
				mBuidling2Index.put(bd, idx);
				mID2Building.put(bID, bd);
				mBuidlingID2Code.put(bID, code);
				mBuildingCode2ID.put(code, bID);
			}
			for (int i = 0; i < buildings.length; i++) {
				Building bd = buildings[i];
				System.out.println("bID = " + bd.ID + ", code = " + bd.code);
			}

			// load room data
			line = in.nextLine();
			line = in.nextLine();
			System.out.println(line);
			nbRoom = in.nextInt();
			System.out.println(nbRoom);
			line = in.nextLine();
			line = in.nextLine();
			System.out.println(line);

			rooms = new Room[nbRoom];
			mRoom2Index = new HashMap<Room, Integer>();
			mRoomID2Code = new HashMap<Integer, String>();
			mRoomCode2ID = new HashMap<String, Integer>();
			mID2Room = new HashMap<Integer, Room>();
			mBuidling2RoomList = new HashMap<Building, ArrayList<Room>>();
			for (int i = 0; i < buildings.length; i++) {
				Building bd = buildings[i];
				mBuidling2RoomList.put(bd, new ArrayList<Room>());
			}

			idx = -1;
			while (true) {
				idx++;
				if (idx >= nbRoom) {
					break;
				}
				int rID = in.nextInt();
				if (rID == -1) {
					break;
				}
				String rCode = in.next();
				String bCode = in.next();
				line = in.nextLine();
				// System.out.println("id = "+rID+", code = "+rCode+", building
				// = "+bCode);

				int buildingID = mBuildingCode2ID.get(bCode);
				Room r = new Room(rID, rCode, buildingID);
				rooms[idx] = r;
				mRoom2Index.put(r, idx);
				mRoomCode2ID.put(rCode, rID);
				mRoomID2Code.put(rID, rCode);
				mID2Room.put(rID, r);
				Building bd = mID2Building.get(buildingID);
				mBuidling2RoomList.get(bd).add(r);

			}
			// System.out.println("\nRoom:");
			for (int i = 0; i < rooms.length; i++) {
				Room r = rooms[i];
				String buildingCode = mBuidlingID2Code.get(r.buildingID);
				System.out.println("id = " + r.ID + ", code = " + r.code + ", building = " + buildingCode);
			}
			System.out.println("\nBuidling:");
			for (int i = 0; i < buildings.length; i++) {
				Building bd = buildings[i];
				ArrayList<Room> L = mBuidling2RoomList.get(bd);
				System.out.print("" + bd.code + ":");
				for (Room room : L) {
					System.out.print(" " + room.code);
				}
				System.out.println();
			}

			in.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("Done loading room-building data!");
	}

	public void testLoadData_RoomCluster() {
		nbRoomPerCluster = new int[NBCLUSTER];
		aRoomList = new ArrayList[NBCLUSTER];
		for (int i = 0; i < NBCLUSTER; i++) {
			aRoomList[i] = new ArrayList<>();
		}
		for (int i = 0; i < NBCLUSTER; i++) {
			loadData_RoomCluster(FILENAME_PREFIX_ROOMCLUSTER, i);
		}
		nbClassPerCluster = new int[NBCLUSTER];

		nbClassPerCluster = findNumberOfClassPerCluster();
		for (int i = 0; i < NBCLUSTER; i++) {
			System.out.print("cluster[" + (i + 1) + "]: nbroom = " + nbRoomPerCluster[i] + ", nbclass = "
					+ nbClassPerCluster[i] + ":");
			for (Room r : aRoomList[i]) {
				System.out.print(" " + r.code);
			}
			System.out.println();
		}
	}

	/**
	 * Find number of class per cluster. This function must be run after loading
	 * cluster data.
	 */
	public int[] findNumberOfClassPerCluster() {
		int[] nbClassEachCluster = new int[DataCenter.NBCLUSTER];

		// if there is only one cluster
		if (nbClassEachCluster.length == 1) {
			nbClassEachCluster[0] = nbClass;
			return nbClassEachCluster;
		}

		// find min
		int min = nbRoomPerCluster[0];
		int minIdx = 0;
		for (int i = 1; i < nbRoomPerCluster.length; i++) {
			if (nbRoomPerCluster[i] < min) {
				min = nbRoomPerCluster[i];
				minIdx = i;
			}
		}
		// calculate rate between other nbRoomPerCluster[i]s and min
		// nbRoomPerCluster
		int[] rate = new int[DataCenter.NBCLUSTER];
		rate[minIdx] = 1;
		for (int i = 0; i < rate.length; i++) {
			if (i != minIdx) {
				rate[i] = (int) ((double) nbRoomPerCluster[i] / min);
			}
		}

		// calculate number of class per cluster
		int sumRate = 0;
		for (int i = 0; i < rate.length; i++) {
			sumRate += rate[i];
		}

		int minNBClassPerCluster = (int) Math.ceil((double) nbClass / sumRate);
		int cummulative = 0;
		for (int i = 0; i < nbClassEachCluster.length - 1; i++) {
			nbClassEachCluster[i] = rate[i] * minNBClassPerCluster;
			cummulative += nbClassEachCluster[i];
		}
		if (cummulative > nbClass) {
			System.out.println("BUG in finding number of class per cluster.");
			System.exit(1);
		}
		nbClassEachCluster[nbClassEachCluster.length - 1] = nbClass - cummulative;

		return nbClassEachCluster;
	}

	public void loadData_RoomCluster(String fn_prefix, int clusterIdx) {
		try {
			Scanner in = new Scanner(new File(fn_prefix + (clusterIdx + 1) + ".txt"));
			String line = new String();
			line = in.nextLine();
			System.out.println();
			System.out.println(line);
			int clusterNo = in.nextInt();
			line = in.nextLine();
			System.out.println(clusterNo);
			line = in.nextLine();
			System.out.println(line);
			int nbRoom_PerCluster = in.nextInt();
			nbRoomPerCluster[clusterIdx] = nbRoom_PerCluster;
			line = in.nextLine();
			System.out.println(nbRoom_PerCluster);
			line = in.nextLine();
			System.out.println(line);

			int idx = -1;
			while (true) {
				idx++;
				String rCode = in.next();
				if (rCode.compareToIgnoreCase("-1") == 0 || idx >= nbRoom_PerCluster) {
					break;
				}
				if (mRoomCode2ID.get(rCode) != null) {
					int rID = mRoomCode2ID.get(rCode);
					Room r = mID2Room.get(rID);
					aRoomList[clusterIdx].add(r);
				}
			}
			System.out.print("Room cluster " + (clusterIdx + 1) + "-<" + nbRoomPerCluster[clusterIdx] + ">" + ": ");
			for (Room r : aRoomList[clusterIdx]) {
				System.out.print(" " + r.code);
			}
			System.out.println();
			in.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////////
	public void loadData_Building_v2(String fn) {
		try {
			File file = new File(fn);
			Scanner in = new Scanner(file);
			String line = new String();
			line = in.nextLine();
			System.out.println();
			System.out.println(line);
			nbBuilding = in.nextInt();
			System.out.println(nbBuilding);
			line = in.nextLine();
			line = in.nextLine();
			System.out.println(line);

			buildings = new Building[nbBuilding];
			mBuidling2Index = new HashMap<>();
			mBuidlingID2Code = new HashMap<>();
			mBuildingCode2ID = new HashMap<>();
			mID2Building = new HashMap<>();
			mCode2Building = new HashMap<>();

			int idx = -1;
			while (true) {
				idx++;
				int bID = in.nextInt();
				if (bID == -1 || idx >= nbBuilding) {
					break;
				}
				String bCode = in.next();
				// System.out.println("id = "+bID+", code = "+bCode);
				Building bd = new Building(bID, bCode);
				buildings[idx] = bd;
				mBuidling2Index.put(bd, idx);
				mBuidlingID2Code.put(bID, bCode);
				mBuildingCode2ID.put(bCode, bID);
				mID2Building.put(bID, bd);
				mCode2Building.put(bCode, bd);
			}
			for (Building bd : buildings) {
				System.out.println("id = " + bd.ID + ", code = " + bd.code);
			}

			in.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * Loading room data. This function must be invoked after loading building.
	 */
	public void loadData_Room_v2(String fn) {
		try {
			File file = new File(fn);
			Scanner in = new Scanner(file);
			String line = new String();

			line = in.nextLine();
			System.out.println();
			System.out.println(line);

			nbRoom = in.nextInt();
			System.out.println(nbRoom);
			line = in.nextLine();
			line = in.nextLine();
			System.out.println(line);

			rooms = new Room[nbRoom];
			mRoom2Index = new HashMap<Room, Integer>();
			mRoomID2Code = new HashMap<Integer, String>();
			mRoomCode2ID = new HashMap<String, Integer>();
			mID2Room = new HashMap<Integer, Room>();
			mBuidling2RoomList = new HashMap<Building, ArrayList<Room>>();
			mCode2Room = new HashMap<>();

			for (int i = 0; i < buildings.length; i++) {
				Building bd = buildings[i];
				mBuidling2RoomList.put(bd, new ArrayList<Room>());
			}

			// line = in.nextLine();
			// System.out.println(line);
			int idx = -1;
			while (true) {
				idx++;
				if (idx >= nbRoom) {
					break;
				}
				int rID = in.nextInt();
				if (rID == -1) {
					break;
				}
				String rCode = in.next();
				String bCode = in.next();
				int capacity = in.nextInt();
				rCode = rCode.toUpperCase();
				bCode = bCode.toUpperCase();
				// line = in.nextLine();
				System.out.println("id = " + rID + ", code = " + rCode + ", building = " + bCode);

				int buildingID = -1;
				if (mBuildingCode2ID.get(bCode) == null) {
					System.out.println("There is no building has code = " + bCode);
					System.exit(1);
				} else {
					buildingID = mBuildingCode2ID.get(bCode);
				}

				Room r = new Room(rID, rCode, buildingID, capacity);
				rooms[idx] = r;
				mRoom2Index.put(r, idx);
				mRoomCode2ID.put(rCode, rID);
				mRoomID2Code.put(rID, rCode);
				mID2Room.put(rID, r);
				mCode2Room.put(rCode, r);

				Building bd = mID2Building.get(buildingID);
				if (bd != null) {
					mBuidling2RoomList.get(bd).add(r);
				} else {
					System.out.println("There is no building has ID = " + buildingID);
					System.exit(1);
				}
			}
			// System.out.println("\nRoom:");
			for (int i = 0; i < rooms.length; i++) {
				Room r = rooms[i];
				String buildingCode = mBuidlingID2Code.get(r.buildingID);
				System.out.println("id = " + r.ID + ", code = " + r.code + ", building = " + buildingCode);
			}
			System.out.println("\nBuidling:");
			for (int i = 0; i < buildings.length; i++) {
				Building bd = buildings[i];
				ArrayList<Room> L = mBuidling2RoomList.get(bd);
				System.out.print("" + bd.code + ":");
				for (Room room : L) {
					System.out.print(" " + room.code);
				}
				System.out.println();
			}

			in.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void loadData_Course_v2(String fn) {
		try {
			Scanner in = new Scanner(new File(fn));
			String line = new String();
			line = in.nextLine();
			System.out.println();
			System.out.println(line);
			nbCourse = in.nextInt();
			line = in.nextLine();
			line = in.nextLine();
			System.out.println(nbCourse);
			System.out.println(line);

			courses = new Course[nbCourse];
			mID2Course = new HashMap<>();
			mCourse2Index = new HashMap<>();
			mCourseCode2ID = new HashMap<>();
			mCourseID2Code = new HashMap<>();
			mCode2Course = new HashMap<>();
			mCourse2Department = new HashMap<>();
			mDepartment2CourseList = new HashMap<>();
			for (Department d : departments) {
				mDepartment2CourseList.put(d, new ArrayList<>());
			}

			int idx = -1;
			while (in.hasNextLine()) {
				idx++;
				int cID = in.nextInt();
				if (cID == -1 || idx >= nbCourse) {
					break;
				}
				String cCode = in.next();
				String dCode = in.next();
				line = in.nextLine();
				dCode = dCode.toUpperCase();
				cCode = cCode.toUpperCase();
				Department d = mCode2Department.get(dCode);

				if (d != null) {
					Course c = new Course(cID, cCode);
					courses[idx] = c;
					mCourse2Index.put(c, idx);
					mID2Course.put(cID, c);
					mCourseCode2ID.put(cCode, cID);
					mCourseID2Code.put(cID, cCode);
					mCode2Course.put(cCode, c);

					mCourse2Department.put(c, d);
					mDepartment2CourseList.get(d).add(c);

				} else {
					// ko ton tai department
					System.out.println("There is no department: " + dCode);
					System.exit(1);
				}
				//
			}

			for (int i = 0; i < nbCourse; i++) {
				Course c = courses[i];
				Department d = mCourse2Department.get(c);
				System.out.println(c.ID + " " + c.code + " " + d.code);
			}
			System.out.println();
			for (Department d : departments) {
				ArrayList<Course> L = mDepartment2CourseList.get(d);
				System.out.print(d.code + "-<" + L.size() + ">:");
				for (Course c : L) {
					System.out.print(" " + c.code);
				}
				System.out.println();
			}

			System.out.println();
			in.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void loadData_Course_v2(List<String> courseData) {
		try {
			nbCourse = courseData.size();
			courses = new Course[nbCourse];
			mID2Course = new HashMap<>();
			mCourse2Index = new HashMap<>();
			mCourseCode2ID = new HashMap<>();
			mCourseID2Code = new HashMap<>();
			mCode2Course = new HashMap<>();
			mCourse2Department = new HashMap<>();
			mDepartment2CourseList = new HashMap<>();
			for (Department d : departments) {
				mDepartment2CourseList.put(d, new ArrayList<>());
			}
			for (int i = 0; i < nbCourse; i++) {
				String courseTemp = courseData.get(i);
				StringTokenizer stringTokenizer = new StringTokenizer(courseTemp, "|");
				int ID = Integer.parseInt(stringTokenizer.nextToken());
				String cCode = stringTokenizer.nextToken().toUpperCase();
				String dCode = stringTokenizer.nextToken().toUpperCase();
				Department d = mCode2Department.get(dCode);

				if (d != null) {
					Course c = new Course(ID, cCode);
					courses[i] = c;
					mCourse2Index.put(c, i);
					mID2Course.put(ID, c);
					mCourseCode2ID.put(cCode, ID);
					mCourseID2Code.put(ID, cCode);
					mCode2Course.put(cCode, c);

					mCourse2Department.put(c, d);
					mDepartment2CourseList.get(d).add(c);

				} else {
					// ko ton tai department
					System.out.println("There is no department: " + dCode);
					System.exit(1);
				}
			}

			for (int i = 0; i < nbCourse; i++) {
				Course c = courses[i];
				Department d = mCourse2Department.get(c);
				System.out.println(c.ID + " " + c.code + " " + d.code);
			}
			System.out.println();
			for (Department d : departments) {
				ArrayList<Course> L = mDepartment2CourseList.get(d);
				System.out.print(d.code + "-<" + L.size() + ">:");
				for (Course c : L) {
					System.out.print(" " + c.code);
				}
				System.out.println();
			}
			System.out.println();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void loadData_Class_v2(String fn) {
		try {
			Scanner in = new Scanner(new File(fn));
			String line = new String();
			line = in.nextLine();
			System.out.println(line);
			nbClass = in.nextInt();
			line = in.nextLine();
			line = in.nextLine();
			System.out.println(nbClass);
			System.out.println(line);

			classes = new ClassFU[nbClass];
			mID2Class = new HashMap<>();
			mClass2Index = new HashMap<>();
			mClassCode2ID = new HashMap<>();
			mClassID2Code = new HashMap<>();
			mCode2Class = new HashMap<>();

			int idx = -1;
			while (true) {
				idx++;
				int clID = in.nextInt();
				if (clID == -1 || idx >= nbClass) {
					break;
				}
				String code = in.next();
				code = code.toUpperCase();
				ClassFU cls = new ClassFU(clID, code);
				classes[idx] = cls;
				mClass2Index.put(cls, idx);
				mID2Class.put(clID, cls);
				mClassCode2ID.put(code, clID);
				mClassID2Code.put(clID, code);
				mCode2Class.put(code, cls);
			}

			for (int cl = 0; cl < nbClass; cl++) {
				ClassFU cls = classes[cl];
				System.out.println(cls.ID + " " + cls.code);
			}
			// System.out.println();
			in.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		System.out.println("Done loading class data!\n");
	}

	public void loadData_Class_v2_hasStudent(String fn) {
		try {
			Scanner in = new Scanner(new File(fn));
			String line = new String();
			line = in.nextLine();
			System.out.println(line);
			nbClass = in.nextInt();
			line = in.nextLine();
			line = in.nextLine();
			System.out.println(nbClass);
			System.out.println(line);

			classes = new ClassFU[nbClass];
			mID2Class = new HashMap<>();
			mClass2Index = new HashMap<>();
			mClassCode2ID = new HashMap<>();
			mClassID2Code = new HashMap<>();
			mCode2Class = new HashMap<>();
			// mClass2NBStudent = new HashMap<>();

			int idx = -1;
			while (true) {
				idx++;
				int clID = in.nextInt();
				if (clID == -1 || idx >= nbClass) {
					break;
				}
				String code = in.next();
				code = code.toUpperCase();
				int nbStudent = in.nextInt();
				ClassFU cls = new ClassFU(clID, code, nbStudent);
				classes[idx] = cls;
				mClass2Index.put(cls, idx);
				mID2Class.put(clID, cls);
				mClassCode2ID.put(code, clID);
				mClassID2Code.put(clID, code);
				mCode2Class.put(code, cls);
			}

			for (int cl = 0; cl < nbClass; cl++) {
				ClassFU cls = classes[cl];
				System.out.println(cls.ID + " " + cls.code + ", nbstudent = " + cls.nbStudent);
			}
			// System.out.println();
			in.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		System.out.println("Done loading class data!\n");
	}

	public void loadData_Class_v2(List<String> classData) {
		try {
			nbClass = classData.size();

			classes = new ClassFU[nbClass];
			mID2Class = new HashMap<>();
			mClass2Index = new HashMap<>();
			mClassCode2ID = new HashMap<>();
			mClassID2Code = new HashMap<>();
			mCode2Class = new HashMap<>();
			for (int i = 0; i < nbClass; i++) {
				String classTemp = classData.get(i);
				StringTokenizer stringTokenizer = new StringTokenizer(classTemp, "|");
				int ID = Integer.parseInt(stringTokenizer.nextToken());
				String code = stringTokenizer.nextToken();
				ClassFU cls = new ClassFU(ID, code);
				classes[i] = cls;
				mClass2Index.put(cls, i);
				mID2Class.put(ID, cls);
				mClassCode2ID.put(code, ID);
				mClassID2Code.put(ID, code);
				mCode2Class.put(code, cls);
			}

			for (int cl = 0; cl < nbClass; cl++) {
				ClassFU cls = classes[cl];
				System.out.println(cls.ID + " " + cls.code);
			}
			// System.out.println();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		System.out.println("Done loading class data!\n");
	}

	/**
	 * Loading classcoruse data. This function must be invoked after loading
	 * course and class data.
	 */
	public void loadData_ClassCourse_v2(String fn) {
		try {
			File file = new File(fn);
			Scanner in = new Scanner(file);
			String line = in.nextLine();
			System.out.println();
			System.out.println(line);
			nbClassCourse = in.nextInt();
			System.out.println(nbClassCourse);
			line = in.nextLine();
			line = in.nextLine();
			System.out.println(line);

			/*
			 * public int nbClassCourse; public ClassCourse[] classCourses;
			 * public HashMap<Integer, ClassCourse> mID2ClassCourse_all; public
			 * HashMap<ClassCourse, Integer> mClassCourse2Index; public
			 * HashMap<Course, ArrayList<ClassCourse>> mCourse2ClassCourseList;
			 * public HashMap<ClassCourse, Course> mClassCourse2Course; public
			 * HashMap<ClassCourse, ClassFU> mClassCourse2Class; public
			 * HashMap<ClassFU, ArrayList<ClassCourse>> mClass2ClassCourseList;
			 * public HashMap<ClassFU, ArrayList<Course>> mClass2_CourseList;
			 */

			classCourses = new ClassCourse[nbClassCourse];
			mID2ClassCourse = new HashMap<>();
			mClassCourse2Index = new HashMap<>();
			mCourse2ClassCourseList = new HashMap<>();
			mClassCourse2Course = new HashMap<>();
			mClassCourse2Class = new HashMap<>();
			mClass2ClassCourseList = new HashMap<>();
			mClass2CourseList = new HashMap<>();
			mCode2ClassCourse = new HashMap<>();
			mClassCourse2Department = new HashMap<>();
			mDepartment2ClassCourseList = new HashMap<>();

			for (Course c : courses) {
				mCourse2ClassCourseList.put(c, new ArrayList<>());
			}
			for (ClassFU cl : classes) {
				mClass2ClassCourseList.put(cl, new ArrayList<>());
				mClass2CourseList.put(cl, new ArrayList<>());
			}
			for (Department d : departments) {
				mDepartment2ClassCourseList.put(d, new ArrayList<>());
			}

			int idx = -1;
			while (true) {
				idx++;
				int ccID = in.nextInt();
				if (ccID == -1 || idx >= nbClassCourse) {
					break;
				}
				String cCode = in.next();
				String clCode = in.next();
				int stt = in.nextInt();
				cCode = cCode.toUpperCase();
				cCode = cCode.trim();
				clCode = clCode.toUpperCase();
				clCode = clCode.trim();
				// System.out.println(ccID+", "+cCode+", "+clCode+", "+stt);
				if (mCode2Course.get(cCode) != null && mCode2Class.get(clCode) != null) {
					Course c = mCode2Course.get(cCode);
					ClassFU cls = mCode2Class.get(clCode);
					ClassCourse cc = new ClassCourse(ccID, cCode, stt, cls);

					classCourses[idx] = cc;
					mClassCourse2Index.put(cc, idx);
					mClassCourse2Class.put(cc, cls);
					mClassCourse2Course.put(cc, c);
					mID2ClassCourse.put(ccID, cc);
					if (mCode2ClassCourse.get(clCode + cCode) == null) {
						mCode2ClassCourse.put(clCode + cCode, cc);
					} else {
						System.out.println("Duplicate course in one class.");
						System.exit(1);
					}

					mCourse2ClassCourseList.get(c).add(cc);
					mClass2ClassCourseList.get(cls).add(cc);
					if (mClass2CourseList.get(cls).contains(c) == false) {
						mClass2CourseList.get(cls).add(c);
					}
					Department d = mCourse2Department.get(c);
					if (d != null) {
						mClassCourse2Department.put(cc, d);
						mDepartment2ClassCourseList.get(d).add(cc);
					} else {
						System.out.println("There is no department of " + c.code);
						System.exit(1);
					}

				} else {
					System.out.println("No course: " + ccID + ", c = " + cCode + ",  cls = " + clCode + ", " + stt);
					System.exit(1);
				}
				//
			}

			for (ClassCourse cc : classCourses) {
				Course c = mClassCourse2Course.get(cc);
				ClassFU cls = mClassCourse2Class.get(cc);
				if (mCode2ClassCourse == null || cls == null || c == null) {
					System.out.println(cc.code);
					System.exit(1);
				}
				if (mCode2ClassCourse.get(cls.code + c.code) != null) {
					ClassCourse cc1 = mCode2ClassCourse.get(cls.code + c.code);
					System.out.println(cc.ID + ", " + cls.code + ",  " + cc.code + ", " + cc.stt + ", "
							+ mClassCourse2Department.get(cc1).code + ", "
							+ (cc1.code.compareToIgnoreCase(cc.code) == 0));
				} else {
					System.out.println(cc.ID + ", " + cls.code + ",  " + cc.code + ", " + cc.stt);
					System.exit(1);
				}
			}

			System.out.println();
			for (Course c : courses) {
				ArrayList<ClassCourse> L = mCourse2ClassCourseList.get(c);
				System.out.print(c.code + "-<" + L.size() + ">: ");
				for (ClassCourse cc : L) {
					ClassFU cls = mClassCourse2Class.get(cc);
					System.out.print(" " + cc.code + "-" + cls.code);
				}
				System.out.println();
			}

			in.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public void savedata_ClassCourse(String fn){
		File f = new File(fn);
		try {
			FileWriter fw = new FileWriter(f);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("#nbClassCourse\n");
			bw.write(""+nbClassCourse);
			bw.write("\n#ID #course #class #stt\n");
			for (Pair_ClassCourseClass pair : lClassCourseGuestClassPair) {
				ClassCourse cc = pair.classCourse;
//				ClassFU 
				bw.write(cc.ID+" "+cc.code+" "+cc.classFU.code+" "+cc.stt+"\n");				
			}			
			bw.write("-1\n");
			bw.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void savedata_MergedList(String fn){
		File f = new File(fn);
		try {
			FileWriter fw = new FileWriter(f);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("#nbMergedCase\n");
			bw.write(""+nbClassCourse);
			bw.write("\n#course #hostClass #guestClass\n");
			for (ClassCourse cc : classCourses) {
				bw.write(cc.code+" "+cc.classFU.code+" "+cc.stt+"\n");				
			}			
			bw.write("-1\n");
			bw.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void reloadData_ClassCourse_v2(String fn) {
		try {
			File file = new File(fn);
			Scanner in = new Scanner(file);
			String line = in.nextLine();
			System.out.println();
			System.out.println(line);
			nbClassCourse = in.nextInt();
			System.out.println(nbClassCourse);
			line = in.nextLine();
			line = in.nextLine();
			System.out.println(line);

			/*
			 * public int nbClassCourse; public ClassCourse[] classCourses;
			 * public HashMap<Integer, ClassCourse> mID2ClassCourse_all; public
			 * HashMap<ClassCourse, Integer> mClassCourse2Index; public
			 * HashMap<Course, ArrayList<ClassCourse>> mCourse2ClassCourseList;
			 * public HashMap<ClassCourse, Course> mClassCourse2Course; public
			 * HashMap<ClassCourse, ClassFU> mClassCourse2Class; public
			 * HashMap<ClassFU, ArrayList<ClassCourse>> mClass2ClassCourseList;
			 * public HashMap<ClassFU, ArrayList<Course>> mClass2_CourseList;
			 */

			// classCourses = new ClassCourse[nbClassCourse];
			ArrayList<ClassCourse> ccList = new ArrayList<>();
			mID2ClassCourse = new HashMap<>();
			mClassCourse2Index = new HashMap<>();
			mCourse2ClassCourseList = new HashMap<>();
			mClassCourse2Course = new HashMap<>();
			mClassCourse2Class = new HashMap<>();
			mClass2ClassCourseList = new HashMap<>();
			mClass2CourseList = new HashMap<>();
			mCode2ClassCourse = new HashMap<>();
			mClassCourse2Department = new HashMap<>();
			mDepartment2ClassCourseList = new HashMap<>();

			for (Course c : courses) {
				mCourse2ClassCourseList.put(c, new ArrayList<>());
			}
			for (ClassFU cl : classes) {
				mClass2ClassCourseList.put(cl, new ArrayList<>());
				mClass2CourseList.put(cl, new ArrayList<>());
			}
			for (Department d : departments) {
				mDepartment2ClassCourseList.put(d, new ArrayList<>());
			}

			int idx = -1;
			while (true) {

				int ccID = in.nextInt();
				if (ccID == -1 || idx >= nbClassCourse) {
					break;
				}
				String cCode = in.next();
				String clCode = in.next();
				int stt = in.nextInt();
				cCode = cCode.toUpperCase();
				cCode = cCode.trim();
				clCode = clCode.toUpperCase();
				clCode = clCode.trim();
				// System.out.println(ccID+", "+cCode+", "+clCode+", "+stt);
				// tim xem co trong deletedLsit ko
				boolean x = false;
				boolean y = false;

				for (Pair_ClassCourseClass pair : lneedToBeDeletedClassCourse) {
					ClassCourse classCourseInPair = pair.classCourse;
					ClassFU classFUInPair = pair.guestClass;
					if (cCode.compareToIgnoreCase(classCourseInPair.code) == 0
							&& clCode.compareToIgnoreCase(classFUInPair.code) == 0) {
						System.out.println("Deleted " + cCode + " " + clCode);
						x = true;
						break;
					}
				}
				for (Pair_ClassCourseClass pair : lClassCourseGuestClass_Temp) {
					ClassCourse classCourseInPair = pair.classCourse;
					ClassFU classFUInPair = pair.guestClass;
					if (cCode.compareToIgnoreCase(classCourseInPair.code) == 0
							&& clCode.compareToIgnoreCase(classFUInPair.code) == 0) {
						System.out.println("Merged " + cCode + " " + clCode);
						y = true;
						break;
					}
				}

				if (x == false && y == false) {
					// neu ko co
					if (mCode2Course.get(cCode) != null && mCode2Class.get(clCode) != null) {

						Course c = mCode2Course.get(cCode);
						ClassFU cls = mCode2Class.get(clCode);
						ClassCourse cc = new ClassCourse(ccID, cCode, stt, cls);

						// classCourses[idx] = cc;
						if (c != null && cls != null) {
							// check if class and course are valid
							idx++;
							ccList.add(cc);
							mClassCourse2Index.put(cc, idx);
							mClassCourse2Class.put(cc, cls);
							mClassCourse2Course.put(cc, c);
							mID2ClassCourse.put(ccID, cc);
							if (mCode2ClassCourse.get(clCode + cCode) == null) {
								mCode2ClassCourse.put(clCode + cCode, cc);
							} else {
								System.out.println("Duplicate course in one class.");
								System.exit(1);
							}

							mCourse2ClassCourseList.get(c).add(cc);
							mClass2ClassCourseList.get(cls).add(cc);
							if (mClass2CourseList.get(cls).contains(c) == false) {
								mClass2CourseList.get(cls).add(c);
							}
							Department d = mCourse2Department.get(c);
							if (d != null) {
								mClassCourse2Department.put(cc, d);
								mDepartment2ClassCourseList.get(d).add(cc);
							} else {
								System.out.println("There is no department of " + c.code);
								System.exit(1);
							}

						}

					} else {
						System.out.println("No course: " + ccID + ", c = " + cCode + ",  cls = " + clCode + ", " + stt);
						System.exit(1);
					}
				}
			}

			nbClassCourse = ccList.size();
			classCourses = new ClassCourse[nbClassCourse];
			for (int i = 0; i < ccList.size(); i++) {
				classCourses[i] = ccList.get(i);
			}

			for (ClassCourse cc : classCourses) {
				Course c = mClassCourse2Course.get(cc);
				ClassFU cls = mClassCourse2Class.get(cc);
				if (mCode2ClassCourse == null || cls == null || c == null) {
					System.out.println(cc.code);
					System.exit(1);
				}
				if (mCode2ClassCourse.get(cls.code + c.code) != null) {
					ClassCourse cc1 = mCode2ClassCourse.get(cls.code + c.code);
					System.out.println(cc.ID + ", " + cls.code + ",  " + cc.code + ", " + cc.stt + ", "
							+ mClassCourse2Department.get(cc1).code + ", "
							+ (cc1.code.compareToIgnoreCase(cc.code) == 0));
				} else {
					System.out.println(cc.ID + ", " + cls.code + ",  " + cc.code + ", " + cc.stt);
					System.exit(1);
				}
			}

			System.out.println();
			for (Course c : courses) {
				ArrayList<ClassCourse> L = mCourse2ClassCourseList.get(c);
				System.out.print(c.code + "-<" + L.size() + ">: ");
				for (ClassCourse cc : L) {
					ClassFU cls = mClassCourse2Class.get(cc);
					System.out.print(" " + cc.code + "-" + cls.code);
				}
				System.out.println();
			}
			System.out.println(nbClassCourse);
			in.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		// move data
		lClassCourseGuestClassPair = new ArrayList<>();
		for (Pair_ClassCourseClass pair : lClassCourseGuestClass_Temp) {
			ClassCourse cc = pair.classCourse;
			ClassFU hcls = cc.classFU;
			ClassFU cls = pair.guestClass;
			ClassFU newClass = findClassFromCode(cls.code);
			ClassCourse newClassCourse = findClassCourseFromCode(cc.code, hcls.code);
			lClassCourseGuestClassPair.add(new Pair_ClassCourseClass(newClassCourse, newClass));
		}
		for (Pair_ClassCourseClass pair : lClassCourseGuestClass_Temp) {
			ClassCourse cc = pair.classCourse;
			ClassFU hcls = cc.classFU;
			ClassFU cls = pair.guestClass;
			System.out.println("c = " + cc.code + ", host = " + hcls.code + ", guest = " + cls.code);
		}

	}

	public void loadData_ClassCourse_v2(List<String> classCourseData) {
		try {
			nbClassCourse = classCourseData.size();
			System.out.println(nbClassCourse);

			/*
			 * public int nbClassCourse; public ClassCourse[] classCourses;
			 * public HashMap<Integer, ClassCourse> mID2ClassCourse_all; public
			 * HashMap<ClassCourse, Integer> mClassCourse2Index; public
			 * HashMap<Course, ArrayList<ClassCourse>> mCourse2ClassCourseList;
			 * public HashMap<ClassCourse, Course> mClassCourse2Course; public
			 * HashMap<ClassCourse, ClassFU> mClassCourse2Class; public
			 * HashMap<ClassFU, ArrayList<ClassCourse>> mClass2ClassCourseList;
			 * public HashMap<ClassFU, ArrayList<Course>> mClass2_CourseList;
			 */

			classCourses = new ClassCourse[nbClassCourse];
			mID2ClassCourse = new HashMap<>();
			mClassCourse2Index = new HashMap<>();
			mCourse2ClassCourseList = new HashMap<>();
			mClassCourse2Course = new HashMap<>();
			mClassCourse2Class = new HashMap<>();
			mClass2ClassCourseList = new HashMap<>();
			mClass2CourseList = new HashMap<>();
			mCode2ClassCourse = new HashMap<>();
			mClassCourse2Department = new HashMap<>();
			mDepartment2ClassCourseList = new HashMap<>();

			for (Course c : courses) {
				mCourse2ClassCourseList.put(c, new ArrayList<>());
			}
			for (ClassFU cl : classes) {
				mClass2ClassCourseList.put(cl, new ArrayList<>());
				mClass2CourseList.put(cl, new ArrayList<>());
			}
			for (Department d : departments) {
				mDepartment2ClassCourseList.put(d, new ArrayList<>());
			}
			for (int i = 0; i < nbClassCourse; i++) {
				String classCourseTemp = classCourseData.get(i);
				StringTokenizer stringTokenizer = new StringTokenizer(classCourseTemp, "|");
				int ccID = Integer.parseInt(stringTokenizer.nextToken());
				String cCode = stringTokenizer.nextToken().toUpperCase();
				String clCode = stringTokenizer.nextToken().toUpperCase();
				int stt = Integer.parseInt(stringTokenizer.nextToken());
				if (mCode2Course.get(cCode) != null && mCode2Class.get(clCode) != null) {
					ClassCourse cc = new ClassCourse(ccID, cCode, stt);
					Course c = mCode2Course.get(cCode);
					ClassFU cls = mCode2Class.get(clCode);

					classCourses[i] = cc;
					mClassCourse2Index.put(cc, i);
					mClassCourse2Class.put(cc, cls);
					mClassCourse2Course.put(cc, c);
					mID2ClassCourse.put(ccID, cc);
					if (mCode2ClassCourse.get(clCode + cCode) == null) {
						mCode2ClassCourse.put(clCode + cCode, cc);
					} else {
						System.out.println("Duplicate course in one class.");
						System.exit(1);
					}

					mCourse2ClassCourseList.get(c).add(cc);
					mClass2ClassCourseList.get(cls).add(cc);
					if (mClass2CourseList.get(cls).contains(c) == false) {
						mClass2CourseList.get(cls).add(c);
					}
					Department d = mCourse2Department.get(c);
					if (d != null) {
						mClassCourse2Department.put(cc, d);
						mDepartment2ClassCourseList.get(d).add(cc);
					} else {
						System.out.println("There is no department of " + c.code);
						System.exit(1);
					}

				} else {
					System.out.println("No course: " + ccID + ", " + cCode + ",  " + clCode + ", " + stt);
					System.exit(1);
				}
			}

			for (ClassCourse cc : classCourses) {
				Course c = mClassCourse2Course.get(cc);
				ClassFU cls = mClassCourse2Class.get(cc);
				if (mCode2ClassCourse == null || cls == null || c == null) {
					System.out.println(cc.code);
					System.exit(1);
				}
				if (mCode2ClassCourse.get(cls.code + c.code) != null) {
					ClassCourse cc1 = mCode2ClassCourse.get(cls.code + c.code);
					System.out.println(cc.ID + ", " + cls.code + ",  " + cc.code + ", " + cc.stt + ", "
							+ mClassCourse2Department.get(cc1).code + ", "
							+ (cc1.code.compareToIgnoreCase(cc.code) == 0));
				} else {
					System.out.println(cc.ID + ", " + cls.code + ",  " + cc.code + ", " + cc.stt);
					System.exit(1);
				}
			}

			System.out.println();
			for (Course c : courses) {
				ArrayList<ClassCourse> L = mCourse2ClassCourseList.get(c);
				System.out.print(c.code + "-<" + L.size() + ">: ");
				for (ClassCourse cc : L) {
					ClassFU cls = mClassCourse2Class.get(cc);
					System.out.print(" " + cc.code + "-" + cls.code);
				}
				System.out.println();
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public void loadData_Teacher_v2(String fn) {
		try {
			Scanner in = new Scanner(new File(fn));
			String line = new String();
			line = in.nextLine();
			System.out.println(line);
			nbTeacher = in.nextInt();
			System.out.println(nbTeacher);
			line = in.nextLine();
			line = in.nextLine();
			System.out.println(line);

			teachers = new Teacher[nbTeacher];
			mTeacher2Index = new HashMap<>();
			mTeacherCode2ID = new HashMap<>();
			mTeacherID2Code = new HashMap<>();
			mID2Teacher = new HashMap<>();
			mCode2Teacher = new HashMap<>();
			mDepartment2TeacherList = new HashMap<>();
			for (Department dep : departments) {
				mDepartment2TeacherList.put(dep, new ArrayList<>());
			}

			int idx = -1;
			while (true) {
				idx++;
				int id = in.nextInt();
				if (id == -1 || idx >= nbTeacher) {
					break;
				}
				String code = in.next();
				code = code.toUpperCase();
				String department = in.next();
				department = department.toUpperCase();
				Department dep = mCode2Department.get(department);
				if (dep != null) {
					Teacher tc = new Teacher(id, code, dep);
					teachers[idx] = tc;
					mTeacher2Index.put(tc, idx);
					mTeacherCode2ID.put(code, id);
					mTeacherID2Code.put(id, code);
					mID2Teacher.put(id, tc);
					mCode2Teacher.put(code, tc);
					mDepartment2TeacherList.get(dep).add(tc);

				}

			}
			for (Teacher tc : teachers) {
				System.out.println(tc.ID + " " + tc.code + " " + tc.department.code);
			}
			System.out.println();

			for (Department dep : departments) {
				ArrayList<Teacher> L = mDepartment2TeacherList.get(dep);
				System.out.print(dep.code + "-<" + L.size() + ">:");
				for (Teacher teacher : L) {
					System.out.print(" " + teacher.code);
				}
				System.out.println();
			}

			in.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void loadData_Course_Teacher_v2(String fn) {
		try {
			System.out.println();
			Scanner in = new Scanner(new File(fn));
			String line = in.nextLine();
			System.out.println(line);
			nbCourse = in.nextInt();
			System.out.println(nbCourse);
			line = in.nextLine();
			line = in.nextLine();
			System.out.println(line);

			mTeacher2CourseList = new HashMap<>();
			mCourse2TeacherList = new HashMap<>();
			teacherSupplied = new int[nbCourse];
			//mDepartment2TeacherList = new HashMap<Department, ArrayList<Teacher>>();

			for (Teacher tc : teachers) {
				mTeacher2CourseList.put(tc, new ArrayList<Course>());
			}
			for (Course c : courses) {
				mCourse2TeacherList.put(c, new ArrayList<Teacher>());
			}

			int idx = -1;
			while (true) {
				idx++;
				String cCode = in.next();
				// cCode = cCode.toLowerCase();
				if (cCode.compareToIgnoreCase("-1") == 0 || idx >= nbCourse) {
					break;
				}
				if (mCourseCode2ID.get(cCode) != null) {
					int cID = mCourseCode2ID.get(cCode);
					Course c = mID2Course.get(cID);
					int cIdx = mCourse2Index.get(c);
					int supply = in.nextInt();
					int count = 0;
					for (int i = 0; i < supply; i++) {
						String tcCode = in.next();
						tcCode = tcCode.toUpperCase();
						if (mTeacherCode2ID.get(tcCode) != null) {
							int tID = mTeacherCode2ID.get(tcCode);
							Teacher tc = mID2Teacher.get(tID);
							mCourse2TeacherList.get(c).add(tc);
							mTeacher2CourseList.get(tc).add(c);
							count++;
						}
					} //
					line = in.nextLine();
					teacherSupplied[cIdx] = count;
				} else {
					System.out.println("ko co course" + cCode);
					System.exit(1);
				}
				//
			}

			for (int i = 0; i < nbCourse; i++) {
				Course c = courses[i];
				ArrayList<Teacher> L = mCourse2TeacherList.get(c);
				System.out.print(c.code + "-<" + L.size() + ">:");
				for (Teacher teacher : L) {
					System.out.print(" " + teacher.code);
				}
				System.out.println();
			}

			System.out.println();
			for (int i = 0; i < nbTeacher; i++) {
				Teacher tc = teachers[i];
				ArrayList<Course> L = mTeacher2CourseList.get(tc);
				System.out.print(tc.code + "-<" + L.size() + ">:");
				for (Course course : L) {
					System.out.print(" " + course.code);
				}
				System.out.println();
			}

			in.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void loadData_MergedClass_v2(String fn) {

	}

	/**
	 * Loading data from mannually made timetable. This function cannot be run
	 * before loading all other data.
	 */
	public void loadData_mannualTimetable(String fn) {
		/*
		 * //manualData public HashMap<Course, Integer> mCourse2DemandMannual;
		 * public ArrayList<ClassCourse> lClassCourseMannual; public
		 * HashMap<ClassCourse, Integer> mClassCourse2SlotMannual; public
		 * HashMap<ClassCourse, Integer> mClassCourse2SessionMannual; public
		 * HashMap<ClassCourse, Integer> mClassCourse2RoomMannual; public
		 * HashMap<ClassCourse, Integer> mClassCourse2BlockMannual;
		 */
		try {

			File file = new File(fn);
			Scanner in = new Scanner(file);
			String line = new String();
			line = in.nextLine();
			System.out.println();
			System.out.println(line);
			// line = in.nextLine();

			lClassCourseMannual = new ArrayList<>();
			mClassCourse2SlotMannual = new HashMap<>();
			mClassCourse2SessionMannual = new HashMap<>();
			mClassCourse2RoomMannual = new HashMap<>();
			mClassCourse2BlockMannual = new HashMap<>();
			mClassCourse2CourseMannual = new HashMap<>();
			mClassCourse2ClassMannual = new HashMap<>();

			int idx = -1;
			int count = 0;
			while (true) {
				int cID = in.nextInt();
				if (cID == -1) {
					// System.out.println(cID +"-"+nbClassCourse);
					break;
				}
				String cCode = in.next();
				String clCode = in.next();
				String rCode = in.next();
				int block = in.nextInt();
				int slot = in.nextInt();
				// System.out.println(cID+", "+cCode+", "+clCode+", "+rCode+",
				// "+block+", "+slot);
				if (mCode2Class.get(clCode) != null && mCode2Course.get(cCode) != null
						&& mCode2ClassCourse.get(clCode + cCode) != null) {
					// System.out.println(cID+", "+cCode+", "+clCode+",
					// "+rCode+", "+block+", "+slot);
					ClassFU cls = mCode2Class.get(clCode);
					Course c = mCode2Course.get(cCode);
					// ClassCourse cc2 = mCode2ClassCourse.get(clCode+cCode);
					ClassCourse cc = new ClassCourse(cID, cCode, block);
					mClassCourse2CourseMannual.put(cc, c);
					mClassCourse2ClassMannual.put(cc, cls);
					lClassCourseMannual.add(cc);
					count++;

					/*
					 * if (cCode.compareToIgnoreCase(cc2.code) != 0) { //
					 * System.out.println(cID+", "+cCode+", "+clCode+", "+rCode+
					 * ", "+block+", "+slot); System.out.println(cID+"-"+cc2.ID+
					 * ", "+cCode+"-"+cc2.code); System.out.println("mis match"
					 * ); System.exit(1); }
					 */
					mClassCourse2SlotMannual.put(cc, slot);
					int session = 0;
					if (slot > 3) {
						session = 1;
					}
					mClassCourse2SessionMannual.put(cc, session);
					mClassCourse2RoomMannual.put(cc, rCode);
					mClassCourse2BlockMannual.put(cc, block);
				} else {
					System.out.println("no course: " + cID + ", " + cCode + ", " + clCode + ", " + rCode + ", " + block
							+ ", " + slot);
					System.exit(1);
				}
				//
			}

			in.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

		for (ClassCourse cc : lClassCourseMannual) {
			ClassFU cls = mClassCourse2ClassMannual.get(cc);
			if (cls == null) {
				System.out.println("There is no class of " + cc.code);
				System.exit(1);
			}
			Course c = mClassCourse2Course.get(cc);
			String roomCode = mClassCourse2RoomMannual.get(cc);
			int block = mClassCourse2BlockMannual.get(cc);
			int slot = mClassCourse2SlotMannual.get(cc);
			System.out.println(cc.ID + ", " + cc.code + ", " + cls.code + ", " + roomCode + ", " + block + ", " + slot);
		}
		System.out.println("Total " + lClassCourseMannual.size() + " classcourse including duplicated.\n");
	}

	public void calculateMannualDemand() {
		mCourse2DemandMannual = new HashMap<>();
		for (Course c : courses) {
			mCourse2DemandMannual.put(c, new Integer(0));
		}
		int nbCC = lClassCourseMannual.size();
		// System.out.println("size = "+nbCC);
		boolean[] checked = new boolean[nbCC];
		for (int i = 0; i < nbCC - 1; i++) {
			if (checked[i] == false) {
				ClassCourse cc1 = lClassCourseMannual.get(i);
				Course c1 = mClassCourse2CourseMannual.get(cc1);
				int block1 = mClassCourse2BlockMannual.get(cc1);
				int slot1 = mClassCourse2SlotMannual.get(cc1);
				int count = 1;
				for (int j = i + 1; j < nbCC; j++) {
					if (checked[j] == false) {
						ClassCourse cc2 = lClassCourseMannual.get(j);
						Course c2 = mClassCourse2CourseMannual.get(cc2);
						int block2 = mClassCourse2BlockMannual.get(cc2);
						int slot2 = mClassCourse2SlotMannual.get(cc2);

						if (c1 == c2 && slot1 == slot2 && block1 == block2) {
							System.out.println(c1.code + "-" + c1.code + ", block = " + block1 + "-" + block2
									+ ", slot = " + slot1 + "-" + slot2);
							count++;
							checked[j] = true;
						}
					}
					//
				}
				// update demand
				if (count > mCourse2DemandMannual.get(c1)) {
					mCourse2DemandMannual.replace(c1, count);
				}
			}
			checked[i] = true;
		}
		if (checked[nbCC - 1] == false) {
			Course c = mClassCourse2Course.get(lClassCourseMannual.get(nbCC - 1));
			mCourse2DemandMannual.replace(c, 1);
		}

		System.out.println("\nCalculating demand result: ");
		int sum = 0;
		for (Course c : courses) {
			if (mCourse2DemandMannual.get(c) != null) {
				int demand = mCourse2DemandMannual.get(c);
				sum += demand;
				System.out.println("id = " + c.ID + ", code = " + c.code + ", demand = " + demand);
			} else {
				System.out.println("id = " + c.ID + ", code = " + c.code + ", demand = none");
			}
		}
		System.out.println("total demand = " + sum);
	}

	public void loadData_Department_v2(String fn) {
		try {
			File f = new File(fn);
			Scanner in = new Scanner(f);
			String line = new String();
			line = in.nextLine();
			System.out.println(line);
			nbDepartment = in.nextInt();
			System.out.println(nbDepartment);
			line = in.nextLine();
			line = in.nextLine();
			System.out.println(line);

			departments = new Department[nbDepartment];
			mDepartment2Index = new HashMap<>();
			mID2Department = new HashMap<>();
			mDepartmentCode2ID = new HashMap<>();
			mCode2Department = new HashMap<>();
			mDepartmentID2Code = new HashMap<>();

			int idx = -1;
			while (in.hasNext()) {
				idx++;
				int dID = in.nextInt();
				if (dID == -1 || idx >= nbDepartment) {
					break;
				}
				String dCode = in.next();
				line = in.nextLine();
				dCode = dCode.toUpperCase();
				Department d = new Department(dID, dCode);
				// System.out.println(d.ID+" "+d.code);
				departments[idx] = d;
				mDepartment2Index.put(d, idx);
				mID2Department.put(dID, d);
				mCode2Department.put(dCode, d);
				mDepartmentCode2ID.put(dCode, dID);
				mDepartmentID2Code.put(dID, dCode);
			}

			for (Department d : departments) {
				System.out.println(d.ID + " " + d.code);

			}

			in.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void loadData_Department_v2(List<String> departmentData) {
		try {
			nbDepartment = departmentData.size();

			departments = new Department[nbDepartment];
			mDepartment2Index = new HashMap<>();
			mID2Department = new HashMap<>();
			mDepartmentCode2ID = new HashMap<>();
			mCode2Department = new HashMap<>();
			mDepartmentID2Code = new HashMap<>();
			for (int i = 0; i < nbDepartment; i++) {
				String departmentTemp = departmentData.get(i);
				StringTokenizer stringTokenizer = new StringTokenizer(departmentTemp, "|");
				int ID = Integer.parseInt(stringTokenizer.nextToken());
				String dCode = stringTokenizer.nextToken().toUpperCase();
				Department d = new Department(ID, dCode);
				// System.out.println(d.ID+" "+d.code);
				departments[i] = d;
				mDepartment2Index.put(d, i);
				mID2Department.put(ID, d);
				mCode2Department.put(dCode, d);
				mDepartmentCode2ID.put(dCode, ID);
				mDepartmentID2Code.put(ID, dCode);
			}

			for (Department d : departments) {
				System.out.println(d.ID + " " + d.code);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	////////////////////////////////////////////////////////////////////////////////////////////////
	// POST IMPROVEMEMT
	public void makePlace() {
		nbPlace = 12;
		places = new Place[nbPlace];
		int idx = -1;
		for (int block = 0; block < 2; block++) {
			// for (int session = 0; session < 2; session++) {
			for (int slot = 0; slot < nbSlotsPerHalfDay * 2; slot++) {
				int session = 0;
				if (slot > 2) {
					session = 1;
				}
				Place p = new Place(block, session, slot);
				idx++;
				places[idx] = p;
				System.out.println(p.block + "-" + p.session + "-" + p.slot);
			}
			// }
		}

	}

	public void makeCoupleCoursePlace() {
		lcoupleCoursePlaceList = new ArrayList<>();

		mCourse2CoupleCPList = new HashMap<>();
		for (Course c : courses) {
			ArrayList<ClassCourse> L = mCourse2ClassCourseList.get(c);
			boolean ok = true;
			for (ClassCourse cc : L) {
				if (cc.stt == 3) {
					ok = false;
				}
			}
			if (ok) {
				mCourse2CoupleCPList.put(c, new ArrayList<>());
			} else {
				System.out.println("not ok: " + c.code);
			}
		}

		int idx = -1;
		for (Place p : places) {
			for (Course c : courses) {
				ArrayList<ClassCourse> L = mCourse2ClassCourseList.get(c);
				boolean ok = true;
				for (ClassCourse cc : L) {
					if (cc.stt == 3) {
						ok = false;
					}
				}
				if (ok) {
					CoupleCoursePlace couple = new CoupleCoursePlace(c, p);
					if (couple.c == null) {
						System.out.println("c null");
						System.exit(1);
					}
					idx++;
					// coupleCoursePlaces[idx] = couple;
					lcoupleCoursePlaceList.add(couple);
					mCourse2CoupleCPList.get(c).add(couple);
				}
			}
		}

		/*
		 * for (Course c : courses) { ArrayList<CoupleCoursePlace> L =
		 * mCourse2CoupleCPList.get(c); if (L != null) {
		 * System.out.print(c.code+": "); for (CoupleCoursePlace cp : L) { Place
		 * p = cp.p; System.out.print(" "+p); } System.out.println(); }
		 * 
		 * }
		 */
		for (CoupleCoursePlace cp : lcoupleCoursePlaceList) {
			if (cp == null) {
				System.out.println("cp null");
				System.exit(1);
			}
			Course c = cp.c;
			Place p = cp.p;
			// System.out.println(c.code);
		}
	}

	public void buildMapPlace2DaySlotList() {
		mPlace2DaySlotList = new HashMap<>();
		for (Place p : places) {
			mPlace2DaySlotList.put(p, new ArrayList<>());
		}

		for (Place p : places) {
			int block = p.block;
			int slot = p.slot;

			ArrayList<DaySlot> L = mPlace2DaySlotList.get(p);
			//
			if (block == 0) {
				// block 1
				for (int d = 0; d < 10; d++) {
					DaySlot ds = new DaySlot(d, slot);
					L.add(ds);
				}
			} else {
				// block 2
				for (int d = 10; d < 20; d++) {
					DaySlot ds = new DaySlot(d, slot);
					L.add(ds);
				}
			}
			//
			System.out.print(p + ": ");
			for (DaySlot daySlot : L) {
				System.out.print("" + " " + daySlot.day + "-" + daySlot.slot);
			}
			System.out.println();
		}

	}

	public void testFindPlace() {
		Place p = findPlace(1, 1, 2);
		System.out.println("result:");
		if (p == null) {
			System.out.println("not found");
		} else {
			System.out.println(p);
		}
	}

	public Place findPlace(int block, int session, int slot) {
		for (Place p : places) {
			if (p.block == block && p.session == session && p.slot == slot) {
				return p;
			}
		}
		return null;
	}

	public void testFindCouple() {
		Course c = courses[0];
		Place p = findPlace(1, 1, 4);
		CoupleCoursePlace cp = findCouple(c, p);
		System.out.println(cp);

	}

	public CoupleCoursePlace findCouple(Course c, Place p) {
		for (CoupleCoursePlace cp : lcoupleCoursePlaceList) {
			if (cp.c == c && cp.p == p) {
				return cp;
			}
		}
		return null;
	}

	public boolean isDataValidForTemplate2() {
		for (ClassFU cls : classes) {
			ArrayList<ClassCourse> ccList = mClass2ClassCourseList.get(cls);
			int nbCC = ccList.size();
			boolean hasCourseTwoBlock = false;
			for (ClassCourse cc : ccList) {
				if (cc.stt == 3) {
					hasCourseTwoBlock = true;
				}
			}
			if (nbCC == 5 && hasCourseTwoBlock == false) {
				return false;
			}
		}
		return true;
	}

	public void removeMergedClassCourse(String fn_data_classcourse, String fn_data_mergedcases, String fn_target) {

	}

	////////////////////////////////////////////////////////////////////////////////////////////
	// calculate demand of manually timetabling - template 2
	public void loadData_ClassCourse_DaySlot_temp2(String fn) {
		try {
			File f = new File(fn);
			Scanner in = new Scanner(f);
			String line = new String();
			line = in.nextLine();
			System.out.println();
			System.out.println(line);
			int nbcc = in.nextInt();
			line = in.nextLine();
			System.out.println(nbcc);
			line = in.nextLine();
			System.out.println(line);
			mClassCourse2DaySlotList_manual = new HashMap<>();

			int idx = -1;
			while (in.hasNext()) {
				idx++;
				String clCode = (String) in.next();
				clCode = clCode.trim();
				clCode = clCode.toUpperCase();
				if (clCode.compareToIgnoreCase("-1") == 0 || idx >= nbcc) {
					break;
				}
				if (mCode2Class.get(clCode) == null) {
					System.out.println("There is no class " + clCode);
					System.exit(1);
				}
				// System.out.print(clCode);
				String cCode = in.next();
				cCode = cCode.trim();
				cCode = cCode.toUpperCase();
				if (mCode2Course.get(cCode) == null) {
					System.out.println("There is no class " + cCode);
					System.exit(1);
				}

				ClassCourse cc = findClassCourseFromCode(cCode, clCode);
				int block = in.nextInt();
				// System.out.print(", "+cCode+", "+block+":");
				ArrayList<DaySlot> dsList = new ArrayList<>();
				for (int i = 0; i < NB_FRAGMENT_PERCOURSE_FULLMODEL; i++) {
					String ds = in.next();
					String[] temp = ds.split(",");
					if (temp.length != 2) {
						System.out.println(temp.length);
						System.exit(1);
					}
					// System.out.print(" <"+temp[0]+","+temp[1]+">");
					int d = Integer.parseInt(temp[0]) - 1;
					int s = Integer.parseInt(temp[1]) - 1;
					DaySlot daySlot = new DaySlot(d, s);
					dsList.add(daySlot);
					// System.out.print(" <"+daySlot.day+","+daySlot.slot+">");
				}
				mClassCourse2DaySlotList_manual.put(cc, dsList);

				// System.out.println();
				line = in.nextLine();
			}
			for (ClassCourse cc : classCourses) {
				ArrayList<DaySlot> L = mClassCourse2DaySlotList_manual.get(cc);
				ClassFU cls = mClassCourse2Class.get(cc);
				System.out.print(cc.code + "-" + cls.code + ": ");
				for (DaySlot ds : L) {
					System.out.print(" <" + ds.day + "," + ds.slot + ">");
				}
				System.out.println();
			}

			in.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void mergeClassCourse() {
		mClassCourse2nbStudent = new HashMap<ClassCourse, Integer>();
		lClassCourseGuestClass_Temp = new ArrayList<>();
		lneedToBeDeletedClassCourse = new ArrayList<>();
		// tim nbstudent/cc
		for (int i = 0; i < nbClass; i++) {
			ArrayList<ClassCourse> ccList = mClass2ClassCourseList.get(classes[i]);

			for (int j = 0; j < ccList.size(); j++) {
				System.out.println(ccList.get(j).code + " has " + classes[i].nbStudent);
				mClassCourse2nbStudent.put(ccList.get(j), classes[i].nbStudent);
			}

		}

		// find course need to be merged
		// Course c = mCode2Course.get("JPS132");
		for (int i = 0; i < nbCourse; i++) {
			ArrayList<ClassCourse> ccList = mCourse2ClassCourseList.get(courses[i]);
			// ArrayList<ClassCourse> ccList = mCourse2ClassCourseList.get(c);
			if (ccList.size() > 0) {
				boolean flag = false;
				int n = ccList.size();
				int[] w = new int[n];
				for (int j = 0; j < n; j++) {
					System.out.println(ccList.get(j).code);

					if (mClassCourse2nbStudent.get(ccList.get(j)) == null) {
						System.out.println("null");
						System.exit(1);
					}
					w[j] = mClassCourse2nbStudent.get(ccList.get(j));
					if (w[j] < 15) {
						flag = true;
					}
				}
				// if needed -> merge
				if (flag) {
					MultiKnapsack mkn = new MultiKnapsack(n, w, lClassCourseGuestClass_Temp, ccList, mClassCourse2Class,
							lneedToBeDeletedClassCourse);
				}
			}
			//
		}
		System.out.println();
		for (Pair_ClassCourseClass pair : lneedToBeDeletedClassCourse) {
			ClassCourse classCourse = pair.classCourse;
			ClassFU classFU = pair.guestClass;
			System.out.println(classCourse.code + "-" + classFU.code);
		}
	}

	// find room limt for each course
	public void testfindRoomLimitEachCourse() {
		loadData_CourseRoom();
		findRoomLimitEachCourse();
	}

	public void findRoomLimitEachCourse() {
		aCourse2RoomLimit = new int[nbCourse];
		// int nbType = lClassifiedCourse.length;
		for (int i = 0; i < NB_COURSETYPE; i++) {
			ArrayList<Course> cList = lClassifiedCourse[i];
			ArrayList<Room> rList = lCorrespondClassifiedRoom[i];
			int roomLimit = rList.size();
			for (Course course : cList) {
				int cIdx = mCourse2Index.get(course);
				aCourse2RoomLimit[cIdx] = roomLimit;
			}
		}
		for (Course c : courses) {
			int idx = mCourse2Index.get(c);
			System.out.println(c.code + ", room limit = " + aCourse2RoomLimit[idx]);
		}
	}

	public void loadData_CourseRoom() {
		lClassifiedCourse = new ArrayList[NB_COURSETYPE];
		lCorrespondClassifiedRoom = new ArrayList[NB_COURSETYPE];
		for (int type = 0; type < NB_COURSETYPE; type++) {
			lClassifiedCourse[type] = new ArrayList<Course>();
			lCorrespondClassifiedRoom[type] = new ArrayList<Room>();
			loadData_CourseRoom("datafall/type" + (type + 1) + ".txt", type);
		}
		findRoomLimitEachCourse();
	}

	//////////////////////////////////////////////////////////////////////////////////////////////
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DataCenter DA = new DataCenter();

		/*
		 * DA.loadData_Course_Class("data_all_sm_merged.txt"); //
		 * DA.loadData_Teacher_UsingCode("teacherDataCode.txt"); //
		 * //DA.genDataOneClassFile("demo",30);
		 * 
		 * DA.loadData_Room_Building("roomdata.txt");
		 * DA.testLoadData_RoomCluster(); //
		 * DA.loadData_RoomCluster(DA.ROOMCLUSTER_FILENAME_frefix); //
		 * DA.loadData_SoftSkill("data_softskill.txt"); //
		 * DA.testLoadData_CourseRoom();
		 * 
		 * // DA.loadData_mergedCases("data_mergedCases.txt"); //
		 * DA.makeMustNotConflictClassCourseList(); //
		 * DA.makeMustNotConflictMatrix();
		 */

		DA.loadData_Class_v2_hasStudent("datafall/data_class_fall.txt");
		// DA.loadData_Class_v2("datafall/data_class_fall.txt");
		DA.loadData_Department_v2("datafall/data_department_fall.txt");
		DA.loadData_Course_v2("datafall/data_course_fall.txt");

		DA.loadData_Teacher_v2("datafall/data_teacher_fall.txt");

		DA.loadData_Course_Teacher_v2("datafall/data_course_teacher_fall.txt");
		// DA.loadData_Building_v2("datafall/data_building_fall.txt");
		// DA.loadData_Room_v2("datafall/data_room_fall.txt");

		 DA.loadData_ClassCourse_v2("datafall/data_classcourse_fall.txt");
		 DA.savedata_ClassCourse("datafall/data_classcourse_fall_saved.txt");
		System.out.println(DA.nbClassCourse);
		// DA.loadData_ClassCourse_v2("datafall/data_classcourse_fall.txt");
		// DA.mergeClassCourse();
		// DA.reloadData_ClassCourse_v2("datafall/data_classcourse_fall.txt");
		System.out.println(DA.nbClassCourse);

		// DA.testfindRoomLimitEachCourse();
		// DA.loadData_mergedCases("datasm/data_mergedcases_sm.txt");
		// DA.loadData_mergedCases("data_mergedCases_sample.txt");
		// DA.makeMustNotConflictClassCourseList();
		// DA.makeMustNotConflictMatrix();
		// DA.loadData_ClassCourse_DaySlot_temp2("datasm/data_ttb_sm.txt");

		// DA.loadData_mannualTimetable("data_timetable_mannual.txt");
		// DA.calculateMannualDemand();

		/*
		 * DA.makePlace(); DA.buildMapPlace2DaySlotList();
		 * DA.makeCoupleCoursePlace(); DA.testFindCouple();
		 */
	}

}
