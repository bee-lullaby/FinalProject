package vn.edu.fpt.timetabling.auto.entities;

import java.util.Random;

public class ADSF {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random r = new Random();
		
		int[] mang = new int[10];
		for (int i = 0; i < mang.length; i++) {
			mang[i] = r.nextInt(10)+0;
		}
		
		
		for (int i = 0; i < mang.length; i++) {
			System.out.println("mang["+i+"] = "+mang[i]);
		}
		
		int max = mang[0];
		int vitri = 0;
		for (int i = 1; i < mang.length; i++) {
			if (max < mang[i]) {
				max = mang[i];
				vitri = i;
			}
		}
		System.out.println("max = "+max+", tai vitri = "+(vitri+1));
	}

}
