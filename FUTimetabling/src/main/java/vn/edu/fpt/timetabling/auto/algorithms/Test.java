package vn.edu.fpt.timetabling.auto.algorithms;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Integer> L = new ArrayList<>();
		File f = new File("data/cls1.txt");
		try {
			Scanner in = new Scanner(f);
			String line = in.nextLine();
			System.out.println(line);
			in.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}

