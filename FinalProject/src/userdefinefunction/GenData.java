package userdefinefunction;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class GenData {

	public static void loadData(String fn) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		try {
			Scanner in = new Scanner(new File(fn));
			while (true) {
				int t = in.nextInt();
				if (t == -1)
					break;
				list.add(t);

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		int idx = 0;
		for (Integer i : list) {
			for (int j = 0; j < 3; j++) {
				idx++;
				System.out.println(idx + " " + i);
			}
		}
	}

	public static void a() {
		int fc = -1;
		int c = 0;
		boolean flag1 = true;
		boolean flag2 = false;
		boolean flag3 = false;
		int count = 0;
		while (true) {
			if (fc == 669) {
				break;
			}
			
			if (flag1) {
				fc++;
				System.out.println(fc + " "+ c);
				count++;
				if(count == 2) {
					flag1 = false;
					flag2 = true;
					c++;
					count = 0;
				}
			} else if (flag2) {
				fc++;
				System.out.println(fc + " "+ c);
				count++;
				if(count == 3) {
					flag2 = false;
					flag3 = true;
					c++;
					count = 0;
				}
			} else if (flag3){
				fc++;
				System.out.println(fc + " "+ c);
				count++;
				if(count == 6) {
					flag3 = false;
					flag1 = true;
					c++;
					count = 0;
				}
			}
		}
	}

	public static void gen_fc_c(int nbCourse, int[] course, int nbfcPerCourse){
		int idC = -1;		
		int nbfc = nbCourse*nbfcPerCourse;
		for (int i = 0; i < nbfc; i++) {
			if (i%nbfcPerCourse==0) {
				idC++;
			}
			System.out.println(i+" "+course[idC]);
		}
	}
	public static void gen_fc(int nbFc){
		for (int i = 0; i < nbFc; i++) {
			System.out.print(i+" ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// loadData("temp_course.txt");
		int nbC = 3;		
		int nbFc = 10;
		
		int start = 142;
	   int finish = 144;
		
		int[] course = new int[3];
		int idx = 0;
		for (int c = start; c <= finish; c++) {
			course[idx] = c;
			idx++;
		}
		
//		int[] course = {5	,6,	7,	8,	9};
//		gen_fc(nbFc*nbC);
//		System.out.println();
		System.out.println("#nbCourses\n5\ncourses");
		for (int i = 0; i < course.length; i++) {
			System.out.print(course[i]+" ");
		}
		System.out.println("\nstatus\n0 0 3");
		System.out.println("#nbFCourses_short");
		System.out.println("10");
		System.out.println("FCourses_short");
		System.out.println("0 1 2 3 4 5 6 7 8 9");
		System.out.println("#FCourse_short Course");
		gen_fc_c(nbC,course,2);
		System.out.println("-1\n#nbFCourses\n50\nFCourses");
		gen_fc(nbFc*nbC);
		System.out.println("#FCourse Course");
		gen_fc_c(nbC,course,10);
		System.out.print("-1");
	}

}
