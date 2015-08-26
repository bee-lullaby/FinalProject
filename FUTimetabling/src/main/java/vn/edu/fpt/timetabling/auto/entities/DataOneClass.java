package vn.edu.fpt.timetabling.auto.entities;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class DataOneClass implements Serializable {

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
	public ArrayList<Integer>[] weeksOfBlock;// each block is divided into 2
												// fragment Blocks
	public int[] blockOfWeek;// blockOfWeek[w] is the index of block of week w
	public int nbDays = 4;
	public int nbDays_20 = 20;
	public int nbSlotsPerHalfDay = 3;

	public void loadData(String fn) {
		try {
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
			// line = in.nextLine();

			for (int i = 0; i < nbClasscoursePerClass; i++) {
				int id = in.nextInt();
				System.out.println("Course " + id);
				ClassCourse c = new ClassCourse();
				c.ID = id;
				classCourses[i] = c;
				mID2Course.put(id, c);
				mCourse2Index.put(c, i);
			}
			// line = in.nextLine();
			// line = in.nextLine();
			// statusOfCourse = new int[nbCourses];
			// for (int i = 0; i < nbCourses; i++) {
			// int stt = in.nextInt();
			// statusOfCourse[i] = stt;
			// System.out.println("Course " + courses[i].ID +", stt = "+ stt);
			// }

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
			for (int i = 0; i < nbFCourses; i++) {
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
			for (int i = 0; i < nbClasscoursePerClass; i++) {
				fCoursesOfClasscourse.put(classCourses[i], new ArrayList<FCourse>());
			}
			courseOfFCourse = new HashMap<FCourse, ClassCourse>();
			while (true) {
				int fcID = in.nextInt();
				if (fcID == -1)
					break;
				int cID = in.nextInt();
				System.out.println("FCourse " + fcID + ", Course " + cID);
				ClassCourse c = mID2Course.get(cID);
				FCourse fc = mID2FCourse.get(fcID);
				fCoursesOfClasscourse.get(c).add(fc);
				courseOfFCourse.put(fc, c);
			}
			in.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	/////////////////////////////////

	public void loadData(List<String> data) {
		nbClasscoursePerClass = Integer.parseInt(data.get(0));
		String row = data.get(1);
		classCourses = new ClassCourse[nbClasscoursePerClass];
		mID2Course = new HashMap<Integer, ClassCourse>();
		mCourse2Index = new HashMap<ClassCourse, Integer>();
		String[] str1 = row.split(" ");

		for (int i = 0; i < nbClasscoursePerClass; i++) {
			int id = Integer.parseInt(str1[i]);
			// System.out.println("Course " + id);
			ClassCourse c = new ClassCourse();
			c.ID = id;
			classCourses[i] = c;
			mID2Course.put(id, c);
			mCourse2Index.put(c, i);
		}

		statusOfCourse = new int[nbClasscoursePerClass];
		row = data.get(2);
		String[] str2 = row.split(" ");
		for (int i = 0; i < nbClasscoursePerClass; i++) {
			int stt = Integer.parseInt(str2[i]);
			statusOfCourse[i] = stt;
			// System.out.println("Course " + classCourses[i].ID +", stt = "+
			// stt);
		}
		row = data.get(3);
		nbFCourses = Integer.parseInt(row);

		fCourses = new FCourse[nbFCourses];
		mID2FCourse = new HashMap<Integer, FCourse>();
		mFCourse2Index = new HashMap<FCourse, Integer>();

		for (int i = 0; i < nbFCourses; i++) {
			int id = i;
			FCourse fc = new FCourse();
			fc.ID = id;
			fCourses[i] = fc;
			mID2FCourse.put(id, fc);
			mFCourse2Index.put(fc, i);
		}

		row = data.get(4);
		String[] str3 = row.split(" ");
		fCoursesOfClasscourse = new HashMap<ClassCourse, ArrayList<FCourse>>();
		for (int i = 0; i < nbClasscoursePerClass; i++) {
			fCoursesOfClasscourse.put(classCourses[i], new ArrayList<FCourse>());
		}
		courseOfFCourse = new HashMap<FCourse, ClassCourse>();
		for (int i = 0; i < nbClasscoursePerClass; i++) {
			int fcID = i;
			int cID = Integer.parseInt(str3[i]);
			ClassCourse c = mID2Course.get(cID);
			FCourse fc = mID2FCourse.get(fcID);
			fCoursesOfClasscourse.get(c).add(fc);
			courseOfFCourse.put(fc, c);
		}

		row = data.get(5);
		nbFCourses_20 = Integer.parseInt(row);
		fCourses_20 = new FCourse[nbFCourses_20];
		mID2FCourse_20 = new HashMap<Integer, FCourse>();
		mFCourse2Index_20 = new HashMap<FCourse, Integer>();

		for (int i = 0; i < nbFCourses_20; i++) {
			int id = i;
			// System.out.println("FCourse " + id);
			FCourse fc = new FCourse();
			fc.ID = id;
			fCourses_20[i] = fc;
			mID2FCourse_20.put(id, fc);
			mFCourse2Index_20.put(fc, i);
		}

		row = data.get(6);
		String[] str4 = row.split(" ");

		fCoursesOfCourse_20 = new HashMap<ClassCourse, ArrayList<FCourse>>();
		for (int i = 0; i < nbClasscoursePerClass; i++) {
			fCoursesOfCourse_20.put(classCourses[i], new ArrayList<FCourse>());
		}
		courseOfFCourse_20 = new HashMap<FCourse, ClassCourse>();
		for (int i = 0; i < nbFCourses_20; i++) {
			int fcID = i;
			int cID = Integer.parseInt(str4[i]);
			// System.out.println("FCourse " + fcID + ", Course " + cID);
			ClassCourse c = mID2Course.get(cID);
			FCourse fc = mID2FCourse_20.get(fcID);
			fCoursesOfCourse_20.get(c).add(fc);
			courseOfFCourse_20.put(fc, c);
		}

	}

	public void loadData_SM(String fn) {
		try {
			Scanner in = new Scanner(new File(fn));
			in.nextLine();
			nbClasscoursePerClass = in.nextInt();
			in.nextLine();
			// System.out.println("nbCourses = " + nbCourses);
			classCourses = new ClassCourse[nbClasscoursePerClass];
			mID2Course = new HashMap<Integer, ClassCourse>();
			mCourse2Index = new HashMap<ClassCourse, Integer>();
			in.nextLine();
			// System.out.println("Line = " + line);

			for (int i = 0; i < nbClasscoursePerClass; i++) {
				int id = in.nextInt();
				// System.out.println("Course " + id);
				ClassCourse c = new ClassCourse();
				c.ID = id;
				classCourses[i] = c;
				mID2Course.put(id, c);
				mCourse2Index.put(c, i);
			}

			// status
			in.nextLine();
			in.nextLine();
			statusOfCourse = new int[nbClasscoursePerClass];
			for (int i = 0; i < nbClasscoursePerClass; i++) {
				int stt = in.nextInt();
				statusOfCourse[i] = stt;
				// System.out.println("Course " + classCourses[i].ID +", stt =
				// "+ stt);
			}

			in.nextLine();
			in.nextLine();
			// System.out.println("Line = " + line);

			// fcourse
			nbFCourses = in.nextInt();
			// System.out.println("nbFCourses = " + nbFCourses);
			fCourses = new FCourse[nbFCourses];
			mID2FCourse = new HashMap<Integer, FCourse>();
			mFCourse2Index = new HashMap<FCourse, Integer>();
			in.nextLine();
			in.nextLine();
			// System.out.println("Line = " + line);
			for (int i = 0; i < nbFCourses; i++) {
				int id = in.nextInt();
				// System.out.println("FCourse " + id);
				FCourse fc = new FCourse();
				fc.ID = id;
				fCourses[i] = fc;
				mID2FCourse.put(id, fc);
				mFCourse2Index.put(fc, i);
			}
			in.nextLine();
			in.nextLine();

			// System.out.println("Line = " + line);

			// fcourse-course
			fCoursesOfClasscourse = new HashMap<ClassCourse, ArrayList<FCourse>>();
			for (int i = 0; i < nbClasscoursePerClass; i++) {
				fCoursesOfClasscourse.put(classCourses[i], new ArrayList<FCourse>());
			}
			courseOfFCourse = new HashMap<FCourse, ClassCourse>();

			while (true) {
				int fcID = in.nextInt();
				if (fcID == -1)
					break;
				int cID = in.nextInt();
				// System.out.println("FCourse " + fcID + ", Course " + cID);
				ClassCourse c = mID2Course.get(cID);
				FCourse fc = mID2FCourse.get(fcID);
				fCoursesOfClasscourse.get(c).add(fc);
				courseOfFCourse.put(fc, c);
			}

			// fcourse-20 days
			in.nextLine();
			in.nextLine();
			// System.out.println("Line = " + line);
			nbFCourses_20 = in.nextInt();

			in.nextLine();
			in.nextLine();
			// System.out.println("Line = " + line);

			fCourses_20 = new FCourse[nbFCourses_20];
			mID2FCourse_20 = new HashMap<Integer, FCourse>();
			mFCourse2Index_20 = new HashMap<FCourse, Integer>();

			for (int i = 0; i < nbFCourses_20; i++) {
				int id = in.nextInt();
				// System.out.println("FCourse " + id);
				FCourse fc = new FCourse();
				fc.ID = id;
				fCourses_20[i] = fc;
				mID2FCourse_20.put(id, fc);
				mFCourse2Index_20.put(fc, i);
			}
			// fcourse-course 20 days
			in.nextLine();
			in.nextLine();
			// System.out.println("Line = " + line);
			fCoursesOfCourse_20 = new HashMap<ClassCourse, ArrayList<FCourse>>();
			for (int i = 0; i < nbClasscoursePerClass; i++) {
				fCoursesOfCourse_20.put(classCourses[i], new ArrayList<FCourse>());
			}
			courseOfFCourse_20 = new HashMap<FCourse, ClassCourse>();
			while (true) {
				int fcID = in.nextInt();
				if (fcID == -1)
					break;
				int cID = in.nextInt();
				// System.out.println("FCourse " + fcID + ", Course " + cID);
				ClassCourse c = mID2Course.get(cID);
				FCourse fc = mID2FCourse_20.get(fcID);
				fCoursesOfCourse_20.get(c).add(fc);
				courseOfFCourse_20.put(fc, c);
			}

			in.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		DataOneClass d = new DataOneClass();

		d.loadData_SM("dataOneClass.txt");
		for (int i = 0; i < d.nbClasscoursePerClass; i++) {
			System.out.println("c = " + d.classCourses[i].ID + ", stt = " + d.statusOfCourse[i]);
		}

	}

}
