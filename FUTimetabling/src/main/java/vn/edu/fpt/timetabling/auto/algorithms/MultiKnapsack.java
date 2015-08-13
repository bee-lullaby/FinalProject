package vn.edu.fpt.timetabling.auto.algorithms;

import java.util.ArrayList;
import java.util.HashMap;

import localsearch.constraints.basic.LessOrEqual;
import localsearch.functions.conditionalsum.ConditionalSum;
import localsearch.model.ConstraintSystem;
import localsearch.model.LocalSearchManager;
import localsearch.model.VarIntLS;
import localsearch.search.TabuSearch;
import vn.edu.fpt.timetabling.auto.entities.ClassCourse;
import vn.edu.fpt.timetabling.auto.entities.ClassFU;
import vn.edu.fpt.timetabling.auto.entities.Pair_ClassCourseClass;

public class MultiKnapsack {

	public MultiKnapsack(int n, int[] w, 
			ArrayList<Pair_ClassCourseClass> lClassCourseGuestClassPair,
			ArrayList<ClassCourse> ccList, 
			HashMap<ClassCourse,ClassFU> mCc2Class,
			ArrayList<Pair_ClassCourseClass> needToBeDeleteClassCourse
			) {
		LocalSearchManager ls = new LocalSearchManager();
		ConstraintSystem S = new ConstraintSystem(ls);
		int k = 0;
		VarIntLS[] x = new VarIntLS[n];
		int sum = 0;
		for (Integer weight : w) {
			sum += weight;
		}
		k = (int) Math.ceil((double) sum / 30);
		int upperBoundOfK = (int) Math.ceil((double) sum / 15);
		System.out.println("upper = "+upperBoundOfK);
		if (upperBoundOfK>0){
			do {
				System.out.println("k = " + k);
				ls = new LocalSearchManager();
				S = new ConstraintSystem(ls);
				for (int i = 0; i < n; i++)
					x[i] = new VarIntLS(ls, 0, k - 1);

				for (int j = 0; j < k; j++) {
					ConditionalSum s = new ConditionalSum(x, w, j);
					S.post(new LessOrEqual(s, 30));
					S.post(new LessOrEqual(15, s));
				}
				ls.close();
				TabuSearch ts = new TabuSearch();
				ts.search(S, 20, 10, 10000, 200);
				k++;
			} while (S.violations() > 0 && k <= upperBoundOfK);
			k--;
		}
		

		//
		System.out.println("k = " + k);
		for (int j = 0; j < k; j++) {
			
			System.out.print("Bin " + j + " : ");
			int max = -1;
			int idx = -1;
			for (int i = 0; i < n; i++) {
				if (x[i].getValue() == j) {
					if (w[i] > max) {
						idx = i;
						max = w[i];
					}
					System.out.print("(" + i + ", " + w[i] + ")");
				}
			}
			if (idx > -1) {//co hay ko
				//neu co, xet tong lon hon 15 ko
				int tongSoSV = 0;
				for (int i = 0; i < n; i++) {
					if (x[i].getValue() == j) {
						tongSoSV += w[i];
					}
				}
				
				//neu lon hon
				if (tongSoSV >= 15) {
					ClassCourse cchost = ccList.get(idx);
					for (int i = 0; i < n; i++) {
						if (x[i].getValue() == j && i!=idx) {					
							ClassCourse ccguest = ccList.get(i);
							ClassFU guest = mCc2Class.get(ccguest);
							Pair_ClassCourseClass pair= new Pair_ClassCourseClass(cchost, guest);
							lClassCourseGuestClassPair.add(pair);
							
						}
					}
				}else{
					//cho tat ca vao deleteList
//					
					for (int i = 0; i < n; i++) {
						if (x[i].getValue() == j) {
							ClassCourse cci = ccList.get(i);
							ClassFU cli = mCc2Class.get(cci);
							needToBeDeleteClassCourse.add(new Pair_ClassCourseClass(cci, cli));
						}
					}
				}
			}			
			//
		}
	}

	public static void stateModel() {
		LocalSearchManager ls = new LocalSearchManager();
		int n = 7;
		int k = 3;
		int[] w = new int[] { 10, 5, 8, 9, 16, 10, 10 };
		VarIntLS[] x = new VarIntLS[n];
		for (int i = 0; i < n; i++)
			x[i] = new VarIntLS(ls, 0, k - 1);

		ConstraintSystem S = new ConstraintSystem(ls);
		for (int j = 0; j < k; j++) {
			ConditionalSum s = new ConditionalSum(x, w, j);
			S.post(new LessOrEqual(s, 30));
			S.post(new LessOrEqual(15, s));
		}
		ls.close();
		TabuSearch ts = new TabuSearch();
		ts.search(S, 20, 10, 10000, 200);

		for (int j = 0; j < k; j++) {
			System.out.print("Bin " + j + " : ");
			for (int i = 0; i < n; i++)
				if (x[i].getValue() == j) {
					System.out.print("(" + i + ", " + w[i] + ")");
				}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MultiKnapsack.stateModel();
	}

}