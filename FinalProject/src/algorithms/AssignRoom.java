package algorithms;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import localsearch.constraints.basic.Implicate;
import localsearch.constraints.basic.IsEqual;
import localsearch.functions.basic.FuncMinus;
import localsearch.functions.basic.FuncPlus;
import localsearch.functions.basic.FuncVarConst;
import localsearch.functions.sum.Sum;
import localsearch.model.ConstraintSystem;
import localsearch.model.IConstraint;
import localsearch.model.IFunction;
import localsearch.model.LocalSearchManager;
import localsearch.model.VarIntLS;
import entities.ClassCourse;
import entities.ClassFU;
import entities.Course;
import entities.DataCenter;
import entities.DaySlot;
import entities.Pair_CourseRoom;
import entities.Room;
import entities.SingleSolution;
import entities.Teacher;

public class AssignRoom {
	DataCenter DA;
	SingleSolution[] sol;
	
	public HashMap<Room, ArrayList<DaySlot>> mRoom2DaySlotList; 
	public HashMap<ClassCourse, ArrayList<DaySlot>> mClassCourse2DaySlotList;
	public HashMap<Room, ArrayList<ClassCourse>> mRoom2ClassCourseList;
	public HashMap<ClassFU, HashSet<Room>> mClass2RoomList;
//	public ArrayList<CourseRoomPair> result;
//	public ArrayList<ClassCourse> notyetassignedClassCourse;
//	public boolean[] ccIsAssigned;
//	public int[] availableCapacity;
	
	final static int LIMIT_NBROOM_PERCOURSETYPE = 30;	
//////////////////////////////////////////////////////////////////////
	
//////////////////////////////////////////////////////////////////////
	
	
	public void init(String fn_beingUsedTimeTable){
		//load data
//		DA = new DataCenter();
//		DA.loadData_Course_Class("data_all_sm_merged.txt");
//		DA.loadData_Teacher_UsingCode("teacherDataCode.txt");
//		DA.loadData_Room_Building("roomdata.txt");
		sol = new SingleSolution[DA.nbClass];
		sol = loadbeingUsedTimeTable(fn_beingUsedTimeTable);
//		DA.loadData_SoftSkill("data_softskill.txt");
		
		//calculate supply
		init_roomSupply();
		
		//calculate demand
		init_roomDemand();
		
		
		/*System.out.println();
		Room r = DA.rooms[0];
		for (int cc = 0; cc < 20; cc++) {
			ClassCourse clc = DA.classCourses[cc];
//			System.out.println(r.availableSlot +"-"+currentDemandOfAClassCourse(clc));
			if (isAvailableAsRequired(clc,r)) {
				markDaySlotOfRoom_AsAssigned(clc,DA.rooms[0]);
			}
			ClassFU cls = DA.mClassCourse2Class.get(clc);
			ArrayList<DaySlot> L = mClassCourse2DaySlotList.get(clc);
			System.out.print(clc.ID+ ", cc = "+cls.code+"-"+clc.code+", current demand = "+currentDemandOfAClassCourse(clc)+": ");
			for (DaySlot daySlot : L) {
				System.out.print(" <"+daySlot.day+"-"+daySlot.slot+"-"+daySlot.isAssigned +">");
			}
			System.out.println("");			
		}
		
		System.out.println("Room = "+r.code+", capacity = "+r.capacity+", available = "+r.availableSlot+": ");
		ArrayList<DaySlot> L = mRoom2DaySlotList.get(r);
		for (DaySlot daySlot : L) {
			System.out.print(" <"+daySlot.day+"-"+daySlot.slot+"-"+daySlot.isAssigned +">");
		}
		System.out.println("");		*/
	}
	
	public void assignRoom3For(){
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
		
		DA.ccIsAssigned = new boolean[DA.nbClassCourse]; //mark if cc is assigned
		DA.notyetassignedClassCourse = new ArrayList<ClassCourse>(); //nhung cc nao ko tim dc phong
		//============================================
		for (int cl = 0; cl < DA.nbClass; cl++) {
			ArrayList<ClassCourse> ccList = DA.mClass2ClassCourseList.get(DA.classes[cl]);
			boolean hasFullRoom = false;
			int fullRoomIdx = -1;
			
			//cal demand of ccList
			int totalDemand = 0;
			for (ClassCourse cc : ccList) {
				totalDemand += currentDemandOfAClassCourse(cc);
			}
			
			//find full room			
			for (int ri = 0; ri < DA.nbRoom; ri++) {
				Room ro = DA.rooms[ri];		
//				System.out.println("demand = "+totalDemand+", sp = "+ro.availableSlot);
//				if (totalDemand <= ro.availableSlot) { //neu tong so available slot >= demand moi xet tiep
				if (totalDemand <= DA.availableCapacity[ri]) { //neu tong so available slot >= demand moi xet tiep
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
			
			//if found -> gan het
			if (hasFullRoom) {
				Room r = DA.rooms[fullRoomIdx];
				for (ClassCourse cc : ccList) {
					if (isAvailableAsRequired(cc, r)) {
						assignCC2Room(cc,r);
//						ccIsAssigned[DA.mClassCourse2Index.get(cc)] = true;
					}
				}
			}else{ //gan tuan tu tung cc cho tung r
				for (ClassCourse cc : ccList) {
					boolean thereIsARoom = false;
					for (int ri = 0; ri < DA.nbRoom; ri++) {
						Room r = DA.rooms[ri];
						if (isAvailableAsRequired(cc, r)) {
							assignCC2Room(cc,r);
							thereIsARoom = true;
//							ccIsAssigned[DA.mClassCourse2Index.get(cc)] = true;
						}
					}					
					if (thereIsARoom == false) {
						DA.notyetassignedClassCourse.add(cc);
					}
				}	
				//
			}			
		}
		//============================================
		System.out.println("\nResult: ");
		for (Pair_CourseRoom p : DA.result) {
			ClassCourse cc = p.cc;
			ClassFU cls = DA.mClassCourse2Class.get(cc);
			Room r = p.r;
			System.out.println("cc = "+cc.code+"-"+cls.code+": "+r.code);			
		}
		
		System.out.println("\nNot yet assigned class-course: ");
		int count = 0;
		for (int i = 0; i < DA.ccIsAssigned.length; i++) {
			if (DA.ccIsAssigned[i] == false) {
				count++;
				ClassCourse cc = DA.classCourses[i];
				ClassFU cls = DA.mClassCourse2Class.get(cc);
				System.out.println(count+", "+cc.code+"-"+cls.code);
			}
		}
		System.out.println("Total "+count+" not yet assigned.\n");
		
		for (int ri = 0; ri < DA.nbRoom; ri++) {
			Room r = DA.rooms[ri];
			ArrayList<ClassCourse> ccList = mRoom2ClassCourseList.get(r);
//			System.out.println("mRoom2ClassCourseList size = "+mRoom2ClassCourseList.size());
			System.out.print("room = "+r.code+":<"+DA.availableCapacity[ri]+">"+"-<"+ccList.size()+">: ");
			for (ClassCourse cc : ccList) {
				ClassFU cls = DA.mClassCourse2Class.get(cc);
				System.out.print(" "+cls.code+"-"+cc.code);				
			}
			System.out.println();
		}
		
		
	}
	
	
	public void assignRoomUsingFor(String fn_beingUsedTimeTable){
		init(fn_beingUsedTimeTable);
		DA.result = new ArrayList<Pair_CourseRoom>();
		mRoom2ClassCourseList = new HashMap<Room, ArrayList<ClassCourse>>();
		DA.mClassCourse2AssignedRoom = new HashMap<>();
		
		for (int r = 0; r < DA.nbRoom; r++) {
			Room ro = DA.rooms[r];
			mRoom2ClassCourseList.put(ro, new ArrayList<ClassCourse>());
		}
		DA.ccIsAssigned = new boolean[DA.nbClassCourse]; //mark if cc is assigned
		DA.notyetassignedClassCourse = new ArrayList<ClassCourse>(); //nhung cc nao ko tim dc phong
		mClass2RoomList = new HashMap<ClassFU, HashSet<Room>>();
		for (ClassFU cls : DA.classes) {
			mClass2RoomList.put(cls, new HashSet<Room>());
		}
		//====================================================		
		/*ArrayList<Course> cList = DA.lSoftSkillCourse;
		ArrayList<ClassCourse> classcourseList1 = new ArrayList<ClassCourse>();
		System.out.println("\nSoft skills class-courses:");
		for (Course course : cList) {
			ArrayList<ClassCourse> L = DA.mCourse2ClassCourseList.get(course);
			classcourseList1.addAll(L);
			System.out.print(course.code+"-<"+L.size()+">: ");
			for (ClassCourse cc : L) {
				ClassFU cls = DA.mClassCourse2Class.get(cc);
				System.out.print(cc.code+"-"+cls.code+" ");
			}
			System.out.println();
		}
		System.out.println();		*/
		//====================================================
		DA.lClassifiedCourse = new ArrayList[DataCenter.NB_COURSETYPE];
		DA.lCorrespondClassifiedRoom = new ArrayList[DataCenter.NB_COURSETYPE];
		for (int type = 0; type < DataCenter.NB_COURSETYPE; type++) {
			DA.lClassifiedCourse[type] = new ArrayList<Course>();
			DA.lCorrespondClassifiedRoom[type] = new ArrayList<Room>();
			DA.loadData_CourseRoom(DataCenter.DATAFOLDER+"/"+DataCenter.FILENAME_PREFIX_ROOMTYPE+(type+1)+".txt",type);
			
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
			
			System.out.println("RoomList size = "+roomList.size());
			assignCC2Room(classcourseList,roomList);
		}
		
		
		for (int type = 0; type < DataCenter.NB_COURSETYPE; type++) {
			System.out.println("\nType no."+(type+1)+": ");
			System.out.print("Course:"+"<"+DA.lClassifiedCourse[type].size()+">: ");
			for (Course c : DA.lClassifiedCourse[type]) {
				System.out.print(" "+c.code);
			}
			System.out.print("\nRoom: "+"<"+DA.lCorrespondClassifiedRoom[type].size()+">: ");
			for (Room r : DA.lCorrespondClassifiedRoom[type]) {
				System.out.print(" "+r.code);
			}
			System.out.println();
		}
		
		//====================================================
		System.out.println("\nResult: ");
		for (Pair_CourseRoom p : DA.result) {
			ClassCourse cc = p.cc;
			ClassFU cls = DA.mClassCourse2Class.get(cc);
			Room r = p.r;
			System.out.println("cc = "+cc.code+"-"+cls.code+": "+r.code);			
		}
		
		System.out.println("\nNot yet assigned class-course: ");
		int count = 0;
		for (int i = 0; i < DA.ccIsAssigned.length; i++) {
			if (DA.ccIsAssigned[i] == false) {
				count++;
				ClassCourse cc = DA.classCourses[i];
				ClassFU cls = DA.mClassCourse2Class.get(cc);
				System.out.println(count+", "+cc.code+"-"+cls.code);
			}
		}
		System.out.println("Total "+count+" not yet assigned.\n");
		
		for (int ri = 0; ri < DA.nbRoom; ri++) {
			Room r = DA.rooms[ri];
			ArrayList<ClassCourse> ccList = mRoom2ClassCourseList.get(r);
//			System.out.println("mRoom2ClassCourseList size = "+mRoom2ClassCourseList.size());
			System.out.print("room = "+r.code+":<"+DA.availableCapacity[ri]+">"+"-<"+ccList.size()+">: ");
			for (ClassCourse cc : ccList) {
				ClassFU cls = DA.mClassCourse2Class.get(cc);
				System.out.print(" "+cls.code+"-"+cc.code);				
			}
			System.out.println();
		}
	}
	/**Assign some class-courses to some rooms.*/
	public void assignCC2Room(ArrayList<ClassCourse> classcourseList, ArrayList<Room> roomList){
		for (int cl = 0; cl < DA.nbClass; cl++) {
			//find class-courses have the same class
			ClassFU cls = DA.classes[cl];
			ArrayList<ClassCourse> sameClass_CCList = new ArrayList<ClassCourse>();
			for (ClassCourse cc : classcourseList) {
				ClassFU clsi = DA.mClassCourse2Class.get(cc);
				int ccIdx = DA.mClassCourse2Index.get(cc);
				if (cls == clsi && DA.ccIsAssigned[ccIdx] == false) {
					sameClass_CCList.add(cc);
				}
			}
			
			//cal demand of ccList
			int totalDemand = 0;
			for (ClassCourse cc : sameClass_CCList) {
				totalDemand += currentDemandOfAClassCourse(cc);
			}			
			
			//find full room
			boolean hasFullRoom = false;
			int fullRoomIdx = -1;
			for (int ri = 0; ri < roomList.size(); ri++) {
				Room ro = roomList.get(ri);		
				if (totalDemand <= DA.availableCapacity[ri]) { //neu tong so available slot >= demand moi xet tiep
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
			
			//if found -> gan het
			if (hasFullRoom) {
				Room r = roomList.get(fullRoomIdx);
				for (ClassCourse cc : sameClass_CCList) {
					if (isAvailableAsRequired(cc, r)) {
						assignCC2Room(cc,r);
						/*int ccIdx = DA.mClassCourse2Index.get(cc);
						ccIsAssigned[ccIdx] = true;*/
					}
				}
			}else{ //gan tuan tu tung cc cho tung r
				for (ClassCourse cc : sameClass_CCList) {
					boolean thereIsARoom = false;
					for (int ri = 0; ri < roomList.size(); ri++) {
						Room r = roomList.get(ri);	
						if (isAvailableAsRequired(cc, r)) {
							assignCC2Room(cc,r);
							thereIsARoom = true;
							break;
							/*int ccIdx = DA.mClassCourse2Index.get(cc);
							ccIsAssigned[ccIdx] = true;*/
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
	
	public void assignCC2Room(ClassCourse cc, Room r){
		DA.result.add(new Pair_CourseRoom(cc, r));
		DA.mClassCourse2AssignedRoom.put(cc, r);
		//
		mRoom2ClassCourseList.get(r).add(cc);
		
		//
		markDaySlot_AsAssigned(cc, r);
		
		//mark as assigned
		int ccIdx = DA.mClassCourse2Index.get(cc);
		DA.ccIsAssigned[ccIdx] = true;
		
		//
		ClassFU cls = DA.mClassCourse2Class.get(cc);
//		System.out.println(cc.code);
//		System.out.println(cls.code);
		mClass2RoomList.get(cls).add(r);
	}
	
	public SingleSolution[] loadbeingUsedTimeTable(String fn){
		SingleSolution[] sol = new SingleSolution[DA.nbClass];
		FileInputStream fis;
		try {
			fis = new FileInputStream (fn);
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

	
	public void init_roomDemand(){
		mClassCourse2DaySlotList = new HashMap<ClassCourse, ArrayList<DaySlot>>();
		for (int cc = 0; cc < DA.nbClassCourse; cc++) {
			ClassCourse clc = DA.classCourses[cc];
			mClassCourse2DaySlotList.put(clc, new ArrayList<DaySlot>());
		}
		
		for (int cl = 0; cl < DA.nbClass; cl++) {
//			ClassFU cls = DA.classes[cl];
			SingleSolution ssol = sol[cl];
//			ArrayList<ClassCourse> ccList = DA.mClass2ClCourseList.get(cls);
			for (int day = 0; day < DA.nbDays_20; day++) {
				for (int slot = 0; slot < DA.nbSlotsPerHalfDay*2; slot++) {
					int ccID = ssol.T[slot][day]; 
					if (ccID >= 0) { //neu co mon dang hoc
						DaySlot ds = new DaySlot(day,slot,false);
						ClassCourse cc = DA.mID2ClassCourse_all.get(ccID);
						mClassCourse2DaySlotList.get(cc).add(ds);
					} 					
				}
			}			
		}
		/*for (int cc = 0; cc < 5; cc++) {
			ClassCourse clc = DA.classCourses[cc];
			ClassFU cls = DA.mClassCourse2Class.get(clc);
			ArrayList<DaySlot> L = mClassCourse2DaySlotList.get(clc);
			System.out.print(clc.ID+ ", cc = "+cls.code+"-"+clc.code+", current demand = "+currentDemandOfAClassCourse(clc)+": ");
			for (DaySlot daySlot : L) {
				System.out.print(" <"+daySlot.day+"-"+daySlot.slot+"-"+daySlot.isAssigned +">");
			}
			System.out.println("");
		}*/
	}
	public void init_roomSupply(){
		DA.availableCapacity = new int[DA.nbRoom];
		mRoom2DaySlotList = new HashMap<Room, ArrayList<DaySlot>>();
		for (int r = 0; r < DA.nbRoom; r++) {
			mRoom2DaySlotList.put(DA.rooms[r], new ArrayList<DaySlot>());				
		}
		for (int r = 0; r < DA.nbRoom; r++) {
			Room room = DA.rooms[r]; 
			ArrayList<DaySlot> supplyList = mRoom2DaySlotList.get(room);
			for (int day = 0; day < DA.nbDays_20; day++) {
				for (int slot = 0; slot < DA.nbSlotsPerHalfDay*2; slot++) {
					DaySlot ds = new DaySlot(day, slot, false);
					supplyList.add(ds);
				}
			}
			room.capacity = supplyList.size();
//			room.availableSlot = room.capacity;
			DA.availableCapacity[r] = room.capacity;
//			System.out.println("room = "+room.code+", capacity = "+room.capacity+", available-slot = "+room.availableSlot);
			System.out.println("room = "+room.code+", capacity = "+room.capacity+", available capacity = "+DA.availableCapacity[r]);
		}
		
	}
	
	/**Calculate demand of a set of cc*/
	public HashMap<ClassCourse, ArrayList<DaySlot>> init_roomDemand(ArrayList<ClassCourse> ccList){
		
		return null;
	}
	/**Calculate supply of a set of room*/
	public HashMap<Room, ArrayList<DaySlot>> init_roomSupply(ArrayList<Room> rList){
		
		return null;
	}
	/**Return current demand of a class course: number of slots that have not yet assigned to anyroom.*/
	public int currentDemandOfAClassCourse(ClassCourse cc){
		int demand = 0;
		ArrayList<DaySlot> L = mClassCourse2DaySlotList.get(cc);
		for (DaySlot ds : L) {
			if (ds.isAssigned == false) {
				demand++;
			}
		}		
		return demand;
	}
	
	public boolean isAvailableAsRequired(ClassCourse cc, Room r){
		boolean asRequired = true;
		ArrayList<DaySlot> demandList = mClassCourse2DaySlotList.get(cc);
		ArrayList<DaySlot> supplyList = mRoom2DaySlotList.get(r);
		
		for (DaySlot dds : demandList) {
			if (dds.isAssigned == false) { //neu chua assign
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
	
	
	/**Chi mark as assigned, chua assign.*/
	public void markDaySlot_AsAssigned(ClassCourse cc, Room r){
		ArrayList<DaySlot> demandList = mClassCourse2DaySlotList.get(cc);
		ArrayList<DaySlot> supplyList = mRoom2DaySlotList.get(r);
		int rIdx = DA.mRoom2Index.get(r);
		
		int count = 0;
		for (DaySlot dds : demandList) {
			if (dds.isAssigned == false) { //neu chua assign
				for (DaySlot sds : supplyList) {
					if (dds.compareAllElement(sds) == true) {
						count++;
						dds.isAssigned = true;
						sds.isAssigned = true;
					}
				}
			}			
		}
//		r.availableSlot -= count; //available slot of room r is reduced by demand of cc.
		DA.availableCapacity[rIdx] -= count;
	}
	
	
	
	//////////////////////////////////////////////////////////////////////
	public static void main(String[] args){
		AssignRoom ar = new AssignRoom();
		ar.DA = new DataCenter();
//		ar.assignRoom3For();
		ar.assignRoomUsingFor("beingusedTT2.dat");

		
	}
}
