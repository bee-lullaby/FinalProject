package vn.edu.fpt.timetabling.auto.entities;

import java.io.File;
import java.io.Serializable;
import java.util.*;
public class DataOneClass implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ClassFU cls;
	public int nbClasscoursePerClass;
	public ClassCourse[] classCourses;
	public int[] statusOfCourse;
	
	public int nbFCourses;
	public int nbFCourses_20;
	
	public FCourse[] fCourses;
	public FCourse[] fCourses_20;
	
	public HashMap<ClassCourse, ArrayList<FCourse>> fCoursesOfClasscourse;
	public HashMap<ClassCourse, ArrayList<FCourse>> fCoursesOfCourse_20;
	
	public HashMap<FCourse, ClassCourse> courseOfFCourse;
	public HashMap<FCourse, ClassCourse> courseOfFCourse_20;
	
	public HashMap<Integer, ClassCourse> mID2Course;
	
	public HashMap<Integer, FCourse> mID2FCourse;
	public HashMap<Integer, FCourse> mID2FCourse_20;
	
	public HashMap<ClassCourse, Integer> mCourse2Index;
	
	public HashMap<FCourse, Integer> mFCourse2Index;
	public HashMap<FCourse, Integer> mFCourse2Index_20;
	
	// data in common
	public int nbBlocks = 2;// 0, 1
	public int nbWeeks = 4;// 0, 1, 2, 3
	public ArrayList<Integer>[] weeksOfBlock;// each block is divided into 2 fragment Blocks
	public int[] blockOfWeek;// blockOfWeek[w] is the index of block of week w
	public int nbDays = 4;
	public int nbDays_20 = 20;
	public int nbSlotsPerHalfDay = 3;
	
	public void loadData(String fn){
		try{
			Scanner in = new Scanner(new File(fn));
			String line = "";
			line = in.nextLine();
			nbClasscoursePerClass = in.nextInt();
			line = in.nextLine();
			System.out.println("nbCourses = " + nbClasscoursePerClass);
			classCourses = new ClassCourse[nbClasscoursePerClass];
			mID2Course = new HashMap<Integer, ClassCourse>();
			mCourse2Index = new HashMap<ClassCourse, Integer>();
			line = in.nextLine();
			System.out.println("Line = " + line);			
			//line = in.nextLine();
			
			for(int i = 0; i < nbClasscoursePerClass; i++){
				int id = in.nextInt();
				System.out.println("Course " + id);
				ClassCourse c = new ClassCourse();
				c.ID = id;
				classCourses[i] = c;
				mID2Course.put(id, c);
				mCourse2Index.put(c, i);
			}
//			line = in.nextLine();
//			line = in.nextLine();
//			statusOfCourse = new int[nbCourses];
//			for (int i = 0; i < nbCourses; i++) {
//				int stt = in.nextInt();
//				statusOfCourse[i] = stt;
//				System.out.println("Course " + courses[i].ID +", stt = "+ stt);
//			}
			
			
			line = in.nextLine();
			line = in.nextLine();
			System.out.println("Line = " + line);
			
			
			nbFCourses = in.nextInt();
			System.out.println("nbFCourses = " + nbFCourses);
			fCourses = new FCourse[nbFCourses];
			mID2FCourse = new HashMap<Integer, FCourse>();
			mFCourse2Index = new HashMap<FCourse, Integer>();
			line = in.nextLine();
			line = in.nextLine();
			System.out.println("Line = " + line);
			for(int i = 0; i < nbFCourses; i++){
				int id = in.nextInt();
				System.out.println("FCourse " + id);
				FCourse fc = new FCourse();
				fc.ID = id;
				fCourses[i] = fc;
				mID2FCourse.put(id, fc);
				mFCourse2Index.put(fc, i);
			}
			line = in.nextLine();
			line = in.nextLine();
			
			System.out.println("Line = " + line);
			
			fCoursesOfClasscourse = new HashMap<ClassCourse, ArrayList<FCourse>>();
			for(int i = 0; i < nbClasscoursePerClass; i++){				
				fCoursesOfClasscourse.put(classCourses[i], new ArrayList<FCourse>());
			}
			courseOfFCourse= new HashMap<FCourse, ClassCourse>();
			while(true){
				int fcID = in.nextInt();
				if(fcID == -1) break;
				int cID = in.nextInt();
				System.out.println("FCourse " + fcID + ", Course " + cID);
				ClassCourse c = mID2Course.get(cID);
				FCourse fc = mID2FCourse.get(fcID);
				fCoursesOfClasscourse.get(c).add(fc);
				courseOfFCourse.put(fc, c);
			}
			in.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	/////////////////////////////////
	public void loadData_SM(String fn){
		try{
			Scanner in = new Scanner(new File(fn));
			String line = "";
			line = in.nextLine();
			nbClasscoursePerClass = in.nextInt();
			line = in.nextLine();
//			System.out.println("nbCourses = " + nbCourses);
			classCourses = new ClassCourse[nbClasscoursePerClass];
			mID2Course = new HashMap<Integer, ClassCourse>();
			mCourse2Index = new HashMap<ClassCourse, Integer>();
			line = in.nextLine();
//			System.out.println("Line = " + line);			
			
			for(int i = 0; i < nbClasscoursePerClass; i++){
				int id = in.nextInt();
//				System.out.println("Course " + id);
				ClassCourse c = new ClassCourse();
				c.ID = id;
				classCourses[i] = c;
				mID2Course.put(id, c);
				mCourse2Index.put(c, i);
			}
			
			//status
			line = in.nextLine();
			line = in.nextLine();
			statusOfCourse = new int[nbClasscoursePerClass];
			for (int i = 0; i < nbClasscoursePerClass; i++) {
				int stt = in.nextInt();
				statusOfCourse[i] = stt;
//				System.out.println("Course " + classCourses[i].ID +", stt = "+ stt);
			}
			
			
			line = in.nextLine();
			line = in.nextLine();
//			System.out.println("Line = " + line);
			
			//fcourse
			nbFCourses = in.nextInt();
//			System.out.println("nbFCourses = " + nbFCourses);
			fCourses = new FCourse[nbFCourses];
			mID2FCourse = new HashMap<Integer, FCourse>();
			mFCourse2Index = new HashMap<FCourse, Integer>();
			line = in.nextLine();
			line = in.nextLine();
//			System.out.println("Line = " + line);
			for(int i = 0; i < nbFCourses; i++){
				int id = in.nextInt();
//				System.out.println("FCourse " + id);
				FCourse fc = new FCourse();
				fc.ID = id;
				fCourses[i] = fc;
				mID2FCourse.put(id, fc);
				mFCourse2Index.put(fc, i);
			}
			line = in.nextLine();
			line = in.nextLine();
			
//			System.out.println("Line = " + line);
			
			//fcourse-course
			fCoursesOfClasscourse = new HashMap<ClassCourse, ArrayList<FCourse>>();
			for(int i = 0; i < nbClasscoursePerClass; i++){				
				fCoursesOfClasscourse.put(classCourses[i], new ArrayList<FCourse>());
			}
			courseOfFCourse= new HashMap<FCourse, ClassCourse>();
			while(true){
				int fcID = in.nextInt();
				if(fcID == -1) break;
				int cID = in.nextInt();
//				System.out.println("FCourse " + fcID + ", Course " + cID);
				ClassCourse c = mID2Course.get(cID);
				FCourse fc = mID2FCourse.get(fcID);
				fCoursesOfClasscourse.get(c).add(fc);
				courseOfFCourse.put(fc, c);
			}
			
			//fcourse-20 days
			line = in.nextLine();
			line = in.nextLine();
//			System.out.println("Line = " + line);
			nbFCourses_20 = in.nextInt();
			
			line = in.nextLine();
			line = in.nextLine();
//			System.out.println("Line = " + line);
			
			fCourses_20 = new FCourse[nbFCourses_20];
			mID2FCourse_20 = new HashMap<Integer, FCourse>();
			mFCourse2Index_20 = new HashMap<FCourse, Integer>();
			
			for(int i = 0; i < nbFCourses_20; i++){
				int id = in.nextInt();
//				System.out.println("FCourse " + id);
				FCourse fc = new FCourse();
				fc.ID = id;
				fCourses_20[i] = fc;
				mID2FCourse_20.put(id, fc);
				mFCourse2Index_20.put(fc, i);
			}
			
			//fcourse-course 20 days
			line = in.nextLine();
			line = in.nextLine();
//			System.out.println("Line = " + line);
			fCoursesOfCourse_20 = new HashMap<ClassCourse, ArrayList<FCourse>>();
			for(int i = 0; i < nbClasscoursePerClass; i++){				
				fCoursesOfCourse_20.put(classCourses[i], new ArrayList<FCourse>());
			}
			courseOfFCourse_20 = new HashMap<FCourse, ClassCourse>();
			while(true){
				int fcID = in.nextInt();
				if(fcID == -1) break;
				int cID = in.nextInt();
//				System.out.println("FCourse " + fcID + ", Course " + cID);
				ClassCourse c = mID2Course.get(cID);
				FCourse fc = mID2FCourse_20.get(fcID);
				fCoursesOfCourse_20.get(c).add(fc);
				courseOfFCourse_20.put(fc, c);
			}
						
			in.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DataOneClass d = new DataOneClass();
//		d.loadData("1.txt");
		d.loadData_SM("dataOneClass.txt");
		for (int i = 0; i < d.nbClasscoursePerClass; i++) {
			System.out.println("c = "+d.classCourses[i].ID+", stt = "+d.statusOfCourse[i]);
		}
	}

}
