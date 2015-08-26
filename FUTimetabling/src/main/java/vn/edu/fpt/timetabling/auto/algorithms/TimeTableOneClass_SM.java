package vn.edu.fpt.timetabling.auto.algorithms;

import java.io.PrintWriter;
import java.util.ArrayList;

import choco.Choco;
import choco.cp.model.CPModel;
import choco.cp.solver.CPSolver;
import choco.kernel.model.constraints.Constraint;
import choco.kernel.model.variables.integer.IntegerVariable;
import localsearch.constraints.basic.Implicate;
import localsearch.constraints.basic.IsEqual;
import localsearch.constraints.basic.NotEqual;
import localsearch.model.ConstraintSystem;
import localsearch.model.IConstraint;
import localsearch.model.LocalSearchManager;
import localsearch.model.VarIntLS;
import vn.edu.fpt.timetabling.auto.entities.ClassCourse;
import vn.edu.fpt.timetabling.auto.entities.DataOneClass;
import vn.edu.fpt.timetabling.auto.entities.FCourse;
import vn.edu.fpt.timetabling.auto.entities.SingleSolution;
import vn.edu.fpt.timetabling.auto.userdefinefunction.Abs;

public class TimeTableOneClass_SM {
	/**
	 * 
	 */
	public CPModel model;// reference from outside
	public CPSolver solver;// reference from outside;

	public DataOneClass D;
	// Solution [] sol;
	SingleSolution[] sol;

	public VarIntLS[] x_day_20;
	public IntegerVariable[] x_day;// x_day[fc] is the day assign to FCourse
									// D.fCourses[fc]
	public VarIntLS[] x_slot_20;
	public IntegerVariable[] x_slot;// x_slot[fc] is the slot assigned to
									// FCourse D.fCourses[fc]

	public VarIntLS[] x_session_20;
	// public VarIntLS x_ss_20;
	// public IntegerVariable x_ss;
	public IntegerVariable[] x_session;// x_session[fc] is the morning (0) or
										// afternoon where FCourse
										// D.fCourses[fc] is assigned
	LocalSearchManager ls;
	ConstraintSystem S;

	public void stateModel_20_Template1() {
		ls = new LocalSearchManager();
		x_day_20 = new VarIntLS[D.nbFCourses_20];
		x_slot_20 = new VarIntLS[D.nbFCourses_20];
		x_session_20 = new VarIntLS[D.nbFCourses_20];
		// x_ss_20 = new VarIntLS(ls, 0, 1);

		for (int i = 0; i < D.nbFCourses_20; i++) {
			x_day_20[i] = new VarIntLS(ls, -2, D.nbDays_20 - 1);
			x_slot_20[i] = new VarIntLS(ls, -2, D.nbSlotsPerHalfDay - 1);
			x_session_20[i] = new VarIntLS(ls, 0, 1);
		}

		S = new ConstraintSystem(ls);
		for (int i = 0; i < D.nbFCourses_20 - 1; i++) {
			for (int j = i + 1; j < D.nbFCourses_20; j++) {
				FCourse fc1 = D.fCourses_20[i];
				FCourse fc2 = D.fCourses_20[j];
				int fci = D.mFCourse2Index_20.get(fc1);
				int fcj = D.mFCourse2Index_20.get(fc2);

				IConstraint c1 = new IsEqual(x_day_20[fci], x_day_20[fcj]);
				IConstraint c2 = new NotEqual(x_slot_20[fci], x_slot_20[fcj]);
				S.post(new Implicate(c1, c2));

				IConstraint c4 = new IsEqual(new Abs(x_slot_20[fci], x_slot_20[fcj]), 1);
				IConstraint c3 = new IsEqual(x_day_20[fci], x_day_20[fcj]);
				ClassCourse c = D.courseOfFCourse_20.get(fc1);
				ClassCourse cc = D.courseOfFCourse_20.get(fc2);
				int id1 = D.mCourse2Index.get(c);
				int id2 = D.mCourse2Index.get(cc);
				if (id1 == id2) {
					S.post(new Implicate(c3, c4));
				}
			}
		}

		for (int i = 0; i < D.nbFCourses_20 - 1; i++) {
			S.post(new IsEqual(x_session_20[i], x_session_20[i + 1]));
		}

		ls.close();
	}

	public void stateModel_20_Template2() {
		ls = new LocalSearchManager();
		x_day_20 = new VarIntLS[D.nbFCourses_20];
		x_slot_20 = new VarIntLS[D.nbFCourses_20];
		x_session_20 = new VarIntLS[D.nbFCourses_20];
		// x_ss_20 = new VarIntLS(ls, 0, 1);

		for (int i = 0; i < D.nbFCourses_20; i++) {
			x_day_20[i] = new VarIntLS(ls, -2, D.nbDays_20 - 1);
			x_slot_20[i] = new VarIntLS(ls, -2, D.nbSlotsPerHalfDay - 1);
			x_session_20[i] = new VarIntLS(ls, 0, 1);
		}

		S = new ConstraintSystem(ls);
		for (int i = 0; i < D.nbFCourses_20 - 1; i++) {
			for (int j = i + 1; j < D.nbFCourses_20; j++) {
				FCourse fc1 = D.fCourses_20[i];
				FCourse fc2 = D.fCourses_20[j];
				int fci = D.mFCourse2Index_20.get(fc1);
				int fcj = D.mFCourse2Index_20.get(fc2);

				IConstraint c1 = new IsEqual(x_day_20[fci], x_day_20[fcj]);
				IConstraint c2 = new NotEqual(x_slot_20[fci], x_slot_20[fcj]);
				S.post(new Implicate(c1, c2));

				IConstraint c4 = new IsEqual(new Abs(x_slot_20[fci], x_slot_20[fcj]), 1);
				IConstraint c3 = new IsEqual(x_day_20[fci], x_day_20[fcj]);
				ClassCourse c = D.courseOfFCourse_20.get(fc1);
				ClassCourse cc = D.courseOfFCourse_20.get(fc2);
				int id1 = D.mCourse2Index.get(c);
				int id2 = D.mCourse2Index.get(cc);
				if (id1 == id2) {
					S.post(new Implicate(c3, c4));
				}
			}
		}

		for (int i = 0; i < D.nbFCourses_20 - 1; i++) {
			S.post(new IsEqual(x_session_20[i], x_session_20[i + 1]));
		}

		ls.close();
	}

	public void copyResult_Template2() {
		// this.stateModel_20();
		// int[] daylist_2bl_even = { 0, 2, 4, 5, 7, 9, 10, 12, 15, 17 };
		// int[] daylist_2bl_odd = { 1, 3, 6, 8, 11, 13, 14, 16, 18, 19 };
		int[] daylist_stt3_block1 = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		int[] daylist_stt3_block2 = { 10, 11, 12, 13, 14, 15, 16, 17, 18, 19 };

		int[] daylist_b1_even;
		int[] daylist_b1_odd;
		int[] daylist_b2_even;
		int[] daylist_b2_odd;
		// Random r = new Random();
		// int a = r.nextInt();
		// if (a%2 == 0) {
		daylist_b1_even = new int[] { 0, 2, 4, 5, 7 };
		daylist_b1_odd = new int[] { 1, 3, 6, 8, 9 };
		daylist_b2_even = new int[] { 10, 12, 14, 15, 17 };
		daylist_b2_odd = new int[] { 11, 13, 16, 18, 19 };
		// } else {
		// daylist_b1_even = new int[]{0,2,4,7,9};
		// daylist_b1_odd = new int[]{1,3,5,6,8};
		// daylist_b2_even = new int[]{10,12,14,17,19};
		// daylist_b2_odd = new int[]{11,13,15,16,18};
		// }

		/**/

		// int courseIn2Block = -1;
		ArrayList<Integer> twoBlockCourses = new ArrayList<>();
		for (int c = 0; c < D.nbClasscoursePerClass; c++) {
			if (D.statusOfCourse[c] == 3) {
				twoBlockCourses.add(c);
				// courseIn2Block = c;
				// System.out.println("c = "+D.courses[c].ID+", stt =
				// "+D.statusOfCourse[c]);

				ClassCourse course = D.classCourses[c];
				ArrayList<FCourse> L = D.fCoursesOfClasscourse.get(course);
				ArrayList<FCourse> L_20 = D.fCoursesOfCourse_20.get(course);
				// System.out.println("size L = "+L.size()+", size L20 = "+
				// L_20.size());

				FCourse fc = L.get(0);
				int fcIdx = D.mFCourse2Index.get(fc);
				int day = solver.getVar(x_day[fcIdx]).getVal();
				int slot = solver.getVar(x_slot[fcIdx]).getVal();
				int session = solver.getVar(x_session[fcIdx]).getVal();
				slot = D.nbSlotsPerHalfDay * session + slot;

				// System.out.println("idx = "+fcIdx+", day = "+day+", slot =
				// "+slot+", session = "+session);

				// if (day % 2 == 0) {
				if (day < 2) {
					for (int i = 0; i < L_20.size(); i++) {
						/*
						 * %int fc_20 = fcList_full.get(i);
						 * %x_day_full[fc_20].setValuePropagate
						 * (daylist_2bl_even[i]);
						 * %x_slot_full[fc_20].setValuePropagate
						 * (x_slot[fc].getValue());
						 */

						FCourse fc_20 = L_20.get(i);
						int fcIdx_20 = D.mFCourse2Index_20.get(fc_20);
						x_day_20[fcIdx_20].setValuePropagate(daylist_stt3_block1[i]);
						x_slot_20[fcIdx_20].setValuePropagate(slot);
						x_session_20[fcIdx_20].setValuePropagate(session);
						// System.out.println("idx = "+fcIdx_20+", day =
						// "+daylist_2bl_even[i]+", slot = "+slot+", session =
						// "+session);
					}
				} else {
					for (int i = 0; i < L_20.size(); i++) {
						FCourse fc_20 = L_20.get(i);
						int fcIdx_20 = D.mFCourse2Index_20.get(fc_20);
						x_day_20[fcIdx_20].setValuePropagate(daylist_stt3_block2[i]);
						x_slot_20[fcIdx_20].setValuePropagate(slot);
						x_session_20[fcIdx_20].setValuePropagate(session);
						// System.out.println("idx = "+fcIdx_20+", day =
						// "+daylist_2bl_even[i]+", slot = "+slot+", session =
						// "+session);
					}
				}

				// break;
			}
		}
		// System.out.println("course in 2 blocks: "+courseIn2Block);
		// nhung mon con lai
		for (int c = 0; c < D.nbClasscoursePerClass; c++) {
			if (!twoBlockCourses.contains(c)) {
				ClassCourse course = D.classCourses[c];
				ArrayList<FCourse> L = D.fCoursesOfClasscourse.get(course);
				ArrayList<FCourse> L_20 = D.fCoursesOfCourse_20.get(course);
				FCourse fc1 = L.get(0);
				FCourse fc2 = L.get(1);
				int fc1_Idx = D.mFCourse2Index.get(fc1);
				int fc2_Idx = D.mFCourse2Index.get(fc2);

				int day = solver.getVar(x_day[fc1_Idx]).getVal();
				int slotFc1 = solver.getVar(x_slot[fc1_Idx]).getVal();
				int slotFc2 = solver.getVar(x_slot[fc2_Idx]).getVal();
				int session = solver.getVar(x_session[fc1_Idx]).getVal();
				slotFc1 = D.nbSlotsPerHalfDay * session + slotFc1;
				slotFc2 = D.nbSlotsPerHalfDay * session + slotFc2;

				// ClassCourse t = D.classCourses[c];

				// System.out.println("c = "+t.ID+", slot fc1 = "+slotFc1+",
				// slot fc2 = "+slotFc2);

				// ---------------------------
				int setIdx = -1;
				FCourse fc = null;
				for (int i = 0; i < L_20.size(); i++) {
					if (i % 2 == 0) { // vi set co 5 ngay thoi nhung co 10 fc
						setIdx++;
					}
					fc = L_20.get(i);
					int fcIdx = D.mFCourse2Index_20.get(fc);

					if (day == 0) {
						x_day_20[fcIdx].setValuePropagate(daylist_b1_even[setIdx]);
						// if (day%2 == 0) {
						if (i % 2 == 0) {
							int count = 0;
							int fr = slotFc2;
							do {
								count++;
								if (count == 4) {
									break;
								}
								x_slot_20[fcIdx].setValuePropagate(fr);
								fr = D.nbSlotsPerHalfDay * session + (fr + 1) % 3;
							} while (S.violations(x_slot_20[fcIdx]) > 0);
						} else {
							int count = 0;
							int fr = slotFc1;
							do {
								count++;
								if (count == 4) {
									break;
								}
								x_slot_20[fcIdx].setValuePropagate(fr);
								fr = D.nbSlotsPerHalfDay * session + (fr + 1) % 3;
							} while (S.violations(x_slot_20[fcIdx]) > 0);
						}
					}
					if (day == 1) {
						x_day_20[fcIdx].setValuePropagate(daylist_b1_odd[setIdx]);
						// if (day%2 == 0) {
						if (i % 2 == 0) {
							int count = 0;
							int fr = slotFc2;
							do {
								count++;
								if (count == 4) {
									break;
								}
								x_slot_20[fcIdx].setValuePropagate(fr);
								fr = D.nbSlotsPerHalfDay * session + (fr + 1) % 3;
							} while (S.violations(x_slot_20[fcIdx]) > 0);
						} else {
							int count = 0;
							int fr = slotFc1;
							do {
								count++;
								if (count == 4) {
									break;
								}
								x_slot_20[fcIdx].setValuePropagate(fr);
								fr = D.nbSlotsPerHalfDay * session + (fr + 1) % 3;
							} while (S.violations(x_slot_20[fcIdx]) > 0);
						}
					}
					if (day == 2) {
						x_day_20[fcIdx].setValuePropagate(daylist_b2_even[setIdx]);
						// if (day%2 == 0) {
						if (i % 2 == 0) {
							int count = 0;
							int fr = slotFc2;
							do {
								count++;
								if (count == 4) {
									break;
								}
								x_slot_20[fcIdx].setValuePropagate(fr);
								fr = D.nbSlotsPerHalfDay * session + (fr + 1) % 3;
							} while (S.violations(x_slot_20[fcIdx]) > 0);
						} else {
							int count = 0;
							int fr = slotFc1;
							do {
								count++;
								if (count == 4) {
									break;
								}
								x_slot_20[fcIdx].setValuePropagate(fr);
								fr = D.nbSlotsPerHalfDay * session + (fr + 1) % 3;
							} while (S.violations(x_slot_20[fcIdx]) > 0);
						}
					}
					if (day == 3) {
						x_day_20[fcIdx].setValuePropagate(daylist_b2_odd[setIdx]);
						// if (day%2 == 0) {
						if (i % 2 == 0) {
							int count = 0;
							int fr = slotFc2;
							do {
								count++;
								if (count == 4) {
									break;
								}
								x_slot_20[fcIdx].setValuePropagate(fr);
								fr = D.nbSlotsPerHalfDay * session + (fr + 1) % 3;
							} while (S.violations(x_slot_20[fcIdx]) > 0);
						} else {
							int count = 0;
							int fr = slotFc1;
							do {
								count++;
								if (count == 4) {
									break;
								}
								x_slot_20[fcIdx].setValuePropagate(fr);
								fr = D.nbSlotsPerHalfDay * session + (fr + 1) % 3;
							} while (S.violations(x_slot_20[fcIdx]) > 0);
						}
					}

				}

				// ---------------------------
			}
		}

	}

	public void copyResult_Template1() {
		int[] daylist_2bl_even = { 0, 2, 4, 5, 7, 9, 10, 12, 15, 17 };
		int[] daylist_2bl_odd = { 1, 3, 6, 8, 11, 13, 14, 16, 18, 19 };

		int[] daylist_b1_even;
		int[] daylist_b1_odd;
		int[] daylist_b2_even;
		int[] daylist_b2_odd;
		// Random r = new Random();
		// int a = r.nextInt();
		// if (a%2 == 0) {
		/*
		 * daylist_b1_even = new int[]{0,2,4,5,7}; daylist_b1_odd = new
		 * int[]{1,3,6,8,9}; daylist_b2_even = new int[]{10,12,14,15,17};
		 * daylist_b2_odd = new int[]{11,13,16,18,19};
		 */
		daylist_b1_even = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		daylist_b1_odd = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		daylist_b2_even = new int[] { 10, 11, 12, 13, 14, 15, 16, 17, 18, 19 };
		daylist_b2_odd = new int[] { 10, 11, 12, 13, 14, 15, 16, 17, 18, 19 };
		// } else {
		// daylist_b1_even = new int[]{0,2,4,7,9};
		// daylist_b1_odd = new int[]{1,3,5,6,8};
		// daylist_b2_even = new int[]{10,12,14,17,19};
		// daylist_b2_odd = new int[]{11,13,15,16,18};
		// }

		/**/

		int courseIn2Block = -1;
		for (int c = 0; c < D.nbClasscoursePerClass; c++) {
			if (D.statusOfCourse[c] == 3) {
				courseIn2Block = c;
				// System.out.println("c = "+D.courses[c].ID+", stt =
				// "+D.statusOfCourse[c]);

				ClassCourse course = D.classCourses[c];
				ArrayList<FCourse> L = D.fCoursesOfClasscourse.get(course);
				ArrayList<FCourse> L_20 = D.fCoursesOfCourse_20.get(course);
				// System.out.println("size L = "+L.size()+", size L20 = "+
				// L_20.size());

				FCourse fc = L.get(0);
				int fcIdx = D.mFCourse2Index.get(fc);
				int day = solver.getVar(x_day[fcIdx]).getVal();
				int slot = solver.getVar(x_slot[fcIdx]).getVal();
				int session = solver.getVar(x_session[fcIdx]).getVal();
				slot = D.nbSlotsPerHalfDay * session + slot;

				// System.out.println("idx = "+fcIdx+", day = "+day+", slot =
				// "+slot+", session = "+session);

				if (day % 2 == 0) {
					for (int i = 0; i < L_20.size(); i++) {
						/*
						 * %int fc_20 = fcList_full.get(i);
						 * %x_day_full[fc_20].setValuePropagate
						 * (daylist_2bl_even[i]);
						 * %x_slot_full[fc_20].setValuePropagate
						 * (x_slot[fc].getValue());
						 */

						FCourse fc_20 = L_20.get(i);
						int fcIdx_20 = D.mFCourse2Index_20.get(fc_20);
						x_day_20[fcIdx_20].setValuePropagate(daylist_2bl_even[i]);
						x_slot_20[fcIdx_20].setValuePropagate(slot);
						x_session_20[fcIdx_20].setValuePropagate(session);
						// System.out.println("idx = "+fcIdx_20+", day =
						// "+daylist_2bl_even[i]+", slot = "+slot+", session =
						// "+session);
					}
				} else {
					for (int i = 0; i < L_20.size(); i++) {
						FCourse fc_20 = L_20.get(i);
						int fcIdx_20 = D.mFCourse2Index_20.get(fc_20);
						x_day_20[fcIdx_20].setValuePropagate(daylist_2bl_odd[i]);
						x_slot_20[fcIdx_20].setValuePropagate(slot);
						x_session_20[fcIdx_20].setValuePropagate(session);
						// System.out.println("idx = "+fcIdx_20+", day =
						// "+daylist_2bl_even[i]+", slot = "+slot+", session =
						// "+session);
					}
				}

				break;
			}
		}
		// System.out.println("course in 2 blocks: "+courseIn2Block);
		// nhung mon con lai
		for (int c = 0; c < D.nbClasscoursePerClass; c++) {
			if (c != courseIn2Block) {
				ClassCourse course = D.classCourses[c];
				ArrayList<FCourse> L = D.fCoursesOfClasscourse.get(course);
				ArrayList<FCourse> L_20 = D.fCoursesOfCourse_20.get(course);
				FCourse fc1 = L.get(0);
				FCourse fc2 = L.get(1);
				int fc1_Idx = D.mFCourse2Index.get(fc1);
				int fc2_Idx = D.mFCourse2Index.get(fc2);

				int day = solver.getVar(x_day[fc1_Idx]).getVal();
				int slotFc1 = solver.getVar(x_slot[fc1_Idx]).getVal();
				int slotFc2 = solver.getVar(x_slot[fc2_Idx]).getVal();
				int session = solver.getVar(x_session[fc1_Idx]).getVal();
				slotFc1 = D.nbSlotsPerHalfDay * session + slotFc1;
				slotFc2 = D.nbSlotsPerHalfDay * session + slotFc2;

				// ClassCourse t = D.classCourses[c];

				// System.out.println("c = "+t.ID+", slot fc1 = "+slotFc1+",
				// slot fc2 = "+slotFc2);

				// ---------------------------
				int setIdx = -1;
				FCourse fc = null;
				for (int i = 0; i < L_20.size(); i++) {
					// if (i % 2 == 0) { // vi set co 5 ngay thoi nhung co 10 fc
					setIdx++;
					// }
					fc = L_20.get(i);
					int fcIdx = D.mFCourse2Index_20.get(fc);

					if (day == 0) {
						x_day_20[fcIdx].setValuePropagate(daylist_b1_even[setIdx]);
						// if (day%2 == 0) {
						if (i % 2 == 0) {
							int count = 0;
							int fr = slotFc2;
							do {
								count++;
								if (count == 4) {
									break;
								}
								x_slot_20[fcIdx].setValuePropagate(fr);
								fr = D.nbSlotsPerHalfDay * session + (fr + 1) % 3;
							} while (S.violations(x_slot_20[fcIdx]) > 0);
						} else {
							int count = 0;
							int fr = slotFc1;
							do {
								count++;
								if (count == 4) {
									break;
								}
								x_slot_20[fcIdx].setValuePropagate(fr);
								fr = D.nbSlotsPerHalfDay * session + (fr + 1) % 3;
							} while (S.violations(x_slot_20[fcIdx]) > 0);
						}
					}
					if (day == 1) {
						x_day_20[fcIdx].setValuePropagate(daylist_b1_odd[setIdx]);
						// if (day%2 == 0) {
						if (i % 2 == 0) {
							int count = 0;
							int fr = slotFc2;
							do {
								count++;
								if (count == 4) {
									break;
								}
								x_slot_20[fcIdx].setValuePropagate(fr);
								fr = D.nbSlotsPerHalfDay * session + (fr + 1) % 3;
							} while (S.violations(x_slot_20[fcIdx]) > 0);
						} else {
							int count = 0;
							int fr = slotFc1;
							do {
								count++;
								if (count == 4) {
									break;
								}
								x_slot_20[fcIdx].setValuePropagate(fr);
								fr = D.nbSlotsPerHalfDay * session + (fr + 1) % 3;
							} while (S.violations(x_slot_20[fcIdx]) > 0);
						}
					}
					if (day == 2) {
						x_day_20[fcIdx].setValuePropagate(daylist_b2_even[setIdx]);
						// if (day%2 == 0) {
						if (i % 2 == 0) {
							int count = 0;
							int fr = slotFc2;
							do {
								count++;
								if (count == 4) {
									break;
								}
								x_slot_20[fcIdx].setValuePropagate(fr);
								fr = D.nbSlotsPerHalfDay * session + (fr + 1) % 3;
							} while (S.violations(x_slot_20[fcIdx]) > 0);
						} else {
							int count = 0;
							int fr = slotFc1;
							do {
								count++;
								if (count == 4) {
									break;
								}
								x_slot_20[fcIdx].setValuePropagate(fr);
								fr = D.nbSlotsPerHalfDay * session + (fr + 1) % 3;
							} while (S.violations(x_slot_20[fcIdx]) > 0);
						}
					}
					if (day == 3) {
						x_day_20[fcIdx].setValuePropagate(daylist_b2_odd[setIdx]);
						// if (day%2 == 0) {
						if (i % 2 == 0) {
							int count = 0;
							int fr = slotFc2;
							do {
								count++;
								if (count == 4) {
									break;
								}
								x_slot_20[fcIdx].setValuePropagate(fr);
								fr = D.nbSlotsPerHalfDay * session + (fr + 1) % 3;
							} while (S.violations(x_slot_20[fcIdx]) > 0);
						} else {
							int count = 0;
							int fr = slotFc1;
							do {
								count++;
								if (count == 4) {
									break;
								}
								x_slot_20[fcIdx].setValuePropagate(fr);
								fr = D.nbSlotsPerHalfDay * session + (fr + 1) % 3;
							} while (S.violations(x_slot_20[fcIdx]) > 0);
						}
					}

				}

				// ---------------------------
			}
		}
	}

	public void stateModel_Template1() {
		x_day = new IntegerVariable[D.nbFCourses];
		x_slot = new IntegerVariable[D.nbFCourses];
		x_session = new IntegerVariable[D.nbFCourses];

		for (int i = 0; i < D.nbFCourses; i++) {
			x_day[i] = Choco.makeIntVar("x_day[" + i + "]", 0, 3);
			x_slot[i] = Choco.makeIntVar("x_slot[" + i + "]", 0, D.nbSlotsPerHalfDay - 1);
			x_session[i] = Choco.makeIntVar("x_session[" + i + "]", 0, 1);
		}

		for (int i = 0; i < D.nbClasscoursePerClass; i++) {
			ArrayList<FCourse> fCourses = D.fCoursesOfClasscourse.get(D.classCourses[i]);
			if (D.statusOfCourse[i] == 3) {
				for (int j = 0; j < fCourses.size() - 1; j++) {
					FCourse fc = fCourses.get(j);
					FCourse fc1 = fCourses.get(j + 1);
					int fci = D.mFCourse2Index.get(fc);
					int fc1i = D.mFCourse2Index.get(fc1);

					model.addConstraint(Choco.lt(x_day[fci], x_day[fc1i]));
					model.addConstraint(Choco.eq(x_slot[fci], x_slot[fc1i]));
					model.addConstraint(Choco.neq(x_slot[fci], 1));
					model.addConstraint(Choco.neq(x_slot[fc1i], 1));

					// Constraint c1 = Choco.eq(Choco.mod(x_day[fci], 2),0);
					// Constraint c2 = Choco.eq(Choco.mod(x_day[fc1i], 2),0);
					// model.addConstraint(Choco.implies(c1, c2));
					//
					// Constraint c3 = Choco.eq(Choco.mod(x_day[fci], 2),1);
					// Constraint c4 = Choco.eq(Choco.mod(x_day[fc1i], 2),1);
					//
					// model.addConstraint(Choco.implies(c3, c4));
					model.addConstraint(Choco.eq(Choco.plus(x_day[fci], 2), x_day[fc1i]));
				}
			} else {
				for (int j = 0; j < fCourses.size() - 1; j++) {
					FCourse fc = fCourses.get(j);
					FCourse fc1 = fCourses.get(j + 1);
					int fci = D.mFCourse2Index.get(fc);
					int fc1i = D.mFCourse2Index.get(fc1);

					model.addConstraint(Choco.eq(x_slot[fci], x_slot[fc1i]));
					model.addConstraint(Choco.eq(Choco.plus(x_day[fci], 1), x_day[fc1i]));

					/*
					 * Constraint c1 = Choco.eq(x_day[fci], x_day[fcj]);
					 * Constraint c2 = Choco.neq(x_slot[fci], x_slot[fcj]);
					 * model.addConstraint(Choco.implies(c1, c2));
					 */

					Constraint c1 = Choco.leq(x_day[fci], 1);
					Constraint c2 = Choco.leq(x_day[fc1i], 1);
					model.addConstraint(Choco.implies(c1, c2));

					Constraint c3 = Choco.leq(2, x_day[fci]);
					Constraint c4 = Choco.leq(2, x_day[fc1i]);
					model.addConstraint(Choco.implies(c3, c4));

					if (D.statusOfCourse[i] == 1) {
						model.addConstraint(Choco.leq(x_day[fci], 1));
						model.addConstraint(Choco.leq(x_day[fc1i], 1));
					} else if (D.statusOfCourse[i] == 2) {
						model.addConstraint(Choco.leq(2, x_day[fci]));
						model.addConstraint(Choco.leq(2, x_day[fc1i]));
					}

				}
			}

		}

		for (int i = 0; i < D.nbFCourses - 1; i++) {
			for (int j = i + 1; j < D.nbFCourses; j++) {
				FCourse fc1 = D.fCourses[i];
				FCourse fc2 = D.fCourses[j];
				int fci = D.mFCourse2Index.get(fc1);
				int fcj = D.mFCourse2Index.get(fc2);

				Constraint c1 = Choco.eq(x_day[fci], x_day[fcj]);
				Constraint c2 = Choco.neq(x_slot[fci], x_slot[fcj]);
				model.addConstraint(Choco.implies(c1, c2));
			}
		}

		for (int i = 0; i < D.nbFCourses - 1; i++) {
			model.addConstraint(Choco.eq(x_session[i], x_session[i + 1]));
		}

	}

	public void stateModel_Template2() {
		// D.nbDays = 4;
		x_day = new IntegerVariable[D.nbFCourses];
		x_slot = new IntegerVariable[D.nbFCourses];
		x_session = new IntegerVariable[D.nbFCourses];

		for (int i = 0; i < D.nbFCourses; i++) {
			x_day[i] = Choco.makeIntVar("x_day[" + i + "]", 0, 3);
			x_slot[i] = Choco.makeIntVar("x_slot[" + i + "]", 0, D.nbSlotsPerHalfDay - 1);
			x_session[i] = Choco.makeIntVar("x_session[" + i + "]", 0, 1);
		}

		for (int i = 0; i < D.nbClasscoursePerClass; i++) {
			ArrayList<FCourse> fCourses = D.fCoursesOfClasscourse.get(D.classCourses[i]);
			if (D.statusOfCourse[i] == 3) {
				for (int j = 0; j < fCourses.size() - 1; j++) {
					FCourse fc = fCourses.get(j);
					FCourse fc1 = fCourses.get(j + 1);
					int fci = D.mFCourse2Index.get(fc);
					int fc1i = D.mFCourse2Index.get(fc1);

					model.addConstraint(Choco.lt(x_day[fci], x_day[fc1i]));
					model.addConstraint(Choco.eq(x_slot[fci], x_slot[fc1i]));
					// model.addConstraint(Choco.neq(x_slot[fci], 1));
					// model.addConstraint(Choco.neq(x_slot[fc1i], 1));

					Constraint c1 = Choco.leq(x_day[fci], 1);
					Constraint c2 = Choco.leq(x_day[fc1i], 1);
					model.addConstraint(Choco.implies(c1, c2));
					Constraint c3 = Choco.leq(2, x_day[fci]);
					Constraint c4 = Choco.leq(2, x_day[fc1i]);
					model.addConstraint(Choco.implies(c3, c4));

					model.addConstraint(Choco.eq(Choco.plus(x_day[fci], 1), x_day[fc1i]));
				}
			} else {
				for (int j = 0; j < fCourses.size() - 1; j++) {
					FCourse fc = fCourses.get(j);
					FCourse fc1 = fCourses.get(j + 1);
					int fci = D.mFCourse2Index.get(fc);
					int fc1i = D.mFCourse2Index.get(fc1);

					model.addConstraint(Choco.eq(x_day[fci], x_day[fc1i]));
					model.addConstraint(Choco.eq(Choco.plus(x_slot[fci], 1), x_slot[fc1i]));
					if (D.statusOfCourse[i] == 1) {
						model.addConstraint(Choco.leq(x_day[fci], 1));
						model.addConstraint(Choco.leq(x_day[fc1i], 1));
					} else if (D.statusOfCourse[i] == 2) {
						model.addConstraint(Choco.leq(2, x_day[fci]));
						model.addConstraint(Choco.leq(2, x_day[fc1i]));
					}

				}
			}

		}

		for (int i = 0; i < D.nbFCourses - 1; i++) {
			for (int j = i + 1; j < D.nbFCourses; j++) {
				FCourse fc1 = D.fCourses[i];
				FCourse fc2 = D.fCourses[j];
				int fci = D.mFCourse2Index.get(fc1);
				int fcj = D.mFCourse2Index.get(fc2);

				Constraint c1 = Choco.eq(x_day[fci], x_day[fcj]);
				Constraint c2 = Choco.neq(x_slot[fci], x_slot[fcj]);
				model.addConstraint(Choco.implies(c1, c2));
			}
		}

		for (int i = 0; i < D.nbFCourses - 1; i++) {
			model.addConstraint(Choco.eq(x_session[i], x_session[i + 1]));
		}
	}

	public void search() {
		solver.read(model);
		boolean ok = solver.solve();
		// sol[0] = solver.recordSolution();
		// Boolean ok = solver.solveAll();
		System.out.println("Solved = " + ok + ", nbSolutions = " + solver.getNbSolutions());

	}

	public void printHTML(String fn) {
		PrintWriter out = null;
		// int n = 0;
		try {
			out = new PrintWriter(fn);

			int[][] s = new int[2 * D.nbSlotsPerHalfDay][D.nbDays];
			for (int i = 0; i < 2 * D.nbSlotsPerHalfDay; i++)
				for (int j = 0; j < D.nbDays; j++)
					s[i][j] = -1;

			for (int i = 0; i < D.nbFCourses; i++) {
				FCourse fc = D.fCourses[i];

				int d = solver.getVar(x_day[i]).getVal();
				int slot = solver.getVar(x_slot[i]).getVal();
				int session = solver.getVar(x_session[i]).getVal();
				slot = D.nbSlotsPerHalfDay * session + slot;
				s[slot][d] = D.courseOfFCourse.get(fc).ID;

			}

			out.println("<table border = 1>");
			for (int i = 0; i < 2 * D.nbSlotsPerHalfDay; i++) {
				out.print("<tr>");
				for (int j = 0; j < D.nbDays; j++) {
					String str = "";
					if (s[i][j] >= 0)
						str = "M" + s[i][j];
					out.print("<td height = 20 width = 20>" + str + "</td>\n");
				}
				out.print("</tr>");
			}
			out.println("</table>");
			out.println();
			out.println();

			out.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void printHTML_all_Template2(String fn, int limit) {
		PrintWriter out = null;
		PrintWriter out2 = null;
		int n1 = 0;
		sol = new SingleSolution[limit];
		for (int i = 0; i < sol.length; i++) {
			sol[i] = new SingleSolution();
			sol[i].T = new int[2 * D.nbSlotsPerHalfDay][D.nbDays];
		}

		try {
			out = new PrintWriter("s" + fn);
			out2 = new PrintWriter(fn);
			if (this.solver.solve()) {
				do {
					this.stateModel_20_Template2();
					copyResult_Template2();
					printHTML_20(out2, n1);

					int session = 0;
					sol[n1].position = n1;
					int[][] s = new int[2 * D.nbSlotsPerHalfDay][D.nbDays];
					for (int i = 0; i < 2 * D.nbSlotsPerHalfDay; i++)
						for (int j = 0; j < D.nbDays; j++) {
							s[i][j] = -1;
							sol[n1].T[i][j] = -1;
						}

					for (int i = 0; i < D.nbFCourses; i++) {
						FCourse fc = D.fCourses[i];

						int d = solver.getVar(x_day[i]).getVal();
						// d = d % 5;
						int slot = solver.getVar(x_slot[i]).getVal();
						session = solver.getVar(x_session[i]).getVal();
						slot = D.nbSlotsPerHalfDay * session + slot;
						// System.out.println("i = " + i + ", w = " + w +
						// ", slot = " + slot + ", d = " + d);
						s[slot][d] = D.courseOfFCourse.get(fc).ID;
						sol[n1].T[slot][d] = D.courseOfFCourse.get(fc).ID;

					}

					// out.println("");
					out.println("no." + n1);
					out.println("<table border = 1>");
					for (int i = 0; i < 2 * D.nbSlotsPerHalfDay; i++) {
						out.print("<tr>");
						for (int j = 0; j < D.nbDays; j++) {
							out.print("<td height = 20 width = 20 ");
							String str = "";
							/*
							 * if (s[i][j] >= 0){ str = "M" + s[i][j];
							 * ClassCourse c = D.mID2Course.get(s[i][j]);
							 */
							if (sol[n1].T[i][j] >= 0) {
								str = "M" + sol[n1].T[i][j];
								ClassCourse c = D.mID2Course.get(sol[n1].T[i][j]);
								int idx = D.mCourse2Index.get(c);
								int stt = D.statusOfCourse[idx];
								if (stt == 3) {
									if (session == 0) {
										out.println("bgcolor='green'> ");
									} else {
										out.println("bgcolor='blue'> ");
									}

								} else {
									out.println("bgcolor='yellow'> ");
								}

								out.println(str);
							}
							out.println("</td>");
							// out.print("<td height = 20 width = 20
							// bgcolor='green'>"
							// + str + "</td>\n");
						}
						out.println("</tr>");
					}
					out.println("</table>");
					out.println("-----------------------\n");
					out.println();

					n1++;
					if (n1 > limit - 1) {
						break;
					}

				} while (this.solver.nextSolution());
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		out.close();
		out2.close();
	}

	public void printHTML_all_Template1(String fn, int limit) {
		PrintWriter out = null;
		PrintWriter out2 = null;
		int n1 = 0;
		sol = new SingleSolution[limit];
		for (int i = 0; i < sol.length; i++) {
			sol[i] = new SingleSolution();
			sol[i].T = new int[2 * D.nbSlotsPerHalfDay][D.nbDays];
		}

		try {
			out = new PrintWriter("s" + fn);
			out2 = new PrintWriter(fn);
			if (this.solver.solve()) {
				do {
					this.stateModel_20_Template1();
					copyResult_Template1();
					printHTML_20(out2, n1);

					int session = 0;
					sol[n1].position = n1;
					int[][] s = new int[2 * D.nbSlotsPerHalfDay][D.nbDays];
					for (int i = 0; i < 2 * D.nbSlotsPerHalfDay; i++)
						for (int j = 0; j < D.nbDays; j++) {
							s[i][j] = -1;
							sol[n1].T[i][j] = -1;
						}

					for (int i = 0; i < D.nbFCourses; i++) {
						FCourse fc = D.fCourses[i];

						int d = solver.getVar(x_day[i]).getVal();
						// d = d % 5;
						int slot = solver.getVar(x_slot[i]).getVal();
						session = solver.getVar(x_session[i]).getVal();
						slot = D.nbSlotsPerHalfDay * session + slot;
						// System.out.println("i = " + i + ", w = " + w +
						// ", slot = " + slot + ", d = " + d);
						s[slot][d] = D.courseOfFCourse.get(fc).ID;
						sol[n1].T[slot][d] = D.courseOfFCourse.get(fc).ID;

					}

					// out.println("");
					out.println("no." + n1);
					out.println("<table border = 1>");
					for (int i = 0; i < 2 * D.nbSlotsPerHalfDay; i++) {
						out.print("<tr>");
						for (int j = 0; j < D.nbDays; j++) {
							out.print("<td height = 20 width = 20 ");
							String str = "";
							/*
							 * if (s[i][j] >= 0){ str = "M" + s[i][j];
							 * ClassCourse c = D.mID2Course.get(s[i][j]);
							 */
							if (sol[n1].T[i][j] >= 0) {
								str = "M" + sol[n1].T[i][j];
								ClassCourse c = D.mID2Course.get(sol[n1].T[i][j]);
								int idx = D.mCourse2Index.get(c);
								int stt = D.statusOfCourse[idx];
								if (stt == 3) {
									if (session == 0) {
										out.println("bgcolor='green'> ");
									} else {
										out.println("bgcolor='blue'> ");
									}

								} else {
									out.println("bgcolor='yellow'> ");
								}

								out.println(str);
							}
							out.println("</td>");
							// out.print("<td height = 20 width = 20
							// bgcolor='green'>"
							// + str + "</td>\n");
						}
						out.println("</tr>");
					}
					out.println("</table>");
					out.println("-----------------------\n");
					out.println();

					n1++;
					if (n1 > limit - 1) {
						break;
					}

				} while (this.solver.nextSolution());
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		out.close();
		out2.close();
	}

	public int findFCourse_full(int d, int sl) {
		// matrix 1(size: nbFcousre x 4): fcourse course x_day x_slot
		// matrix 2(size: nbFcousre x 3): course teacher class
		for (int index = 0; index < D.nbFCourses_20; index++) {
			if (x_day_20[index].getValue() == d && x_slot_20[index].getValue() == sl) {
				// int cc = D.courseOfFCourse_full[i];
				// if (D.statusOfCourse[cc]==3) {
				// return i;
				// }
				return index;
			}

		}
		return -1;
	}

	public void printHTML_20(PrintWriter out, int sol) {
		// System.out.println("n = "+sol);
		try {
			// PrintWriter out = new PrintWriter(fn);
			out.println("<table border = 1>");

			// for (int i_cl = 0; i_cl < D.nbClasses; i_cl++) { // for each
			// class
			out.println("<tr>");
			out.println("<td colspan=20>" + "Solution " + sol + "</td>");
			// for (int i_sl = 0; i_sl < D.nbSlotOfHalfDay * 2; i_sl++) { //for
			// each slot sl
			for (int i_sl = 0; i_sl < D.nbSlotsPerHalfDay * 2; i_sl++) { // for
																			// each
																			// slot
																			// sl
				out.println("<tr>");
				for (int i_d = 0; i_d < 20; i_d++) { // for each day
														// at slot sl

					out.println(" <td height = 20 width = 20");
					int idxFC = findFCourse_full(i_d, i_sl);

					if (idxFC >= 0) {
						FCourse fc = D.fCourses_20[idxFC];
						ClassCourse c = D.courseOfFCourse_20.get(fc);
						int cID = c.ID;
						VarIntLS x = x_slot_20[idxFC];
						VarIntLS y = x_day_20[idxFC];
						// if (S.violations(x) != 0) {
						if (S.violations(x) != 0 || S.violations(y) != 0) {
							out.println("bgcolor='red'>");
						} else {
							// out.println("bgcolor='green'>");

							int idex = D.mCourse2Index.get(c);
							int stt = D.statusOfCourse[idex];
							if (stt == 3) {
								out.println("bgcolor='yellow'>");
							} else {
								out.println("bgcolor='green'>");
							}
						}
						out.println("C" + cID);
						// out.println(": T"+ D.teacherOfFCourse[fc]);
					}

					out.println("</td>");
				}
				out.println("</tr>");
			}
			out.println("</tr>");
			// }

			out.println("</table>");

			// int count = 0;
			for (int i = 0; i < x_slot_20.length; i++) {
				VarIntLS x = x_slot_20[i];
				VarIntLS y = x_day_20[i];
				if (S.violations(x) != 0 || S.violations(y) != 0) {
					// count++;
				}
			}
			// out.println("number of violated fc : "+ count);
			// out.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void findSolution_Template2(String fn, int limit) {
		ArrayList<SingleSolution> solutionList = new ArrayList<SingleSolution>();
		int n = 0;
		// sol = new SingleSolution[limit];
		SingleSolution[] solArray = new SingleSolution[limit];
		for (int i = 0; i < solArray.length; i++) {
			// sol[i] = new SingleSolution();
			solArray[i] = new SingleSolution();
			// sol[i].T = new int[2*D.nbSlotsPerHalfDay][D.nbDays_20];
			solArray[i].T = new int[2 * D.nbSlotsPerHalfDay][D.nbDays_20];
		}
		// PrintWriter out = null;
		try {
			// out = new PrintWriter(fn);
			if (this.solver.solve()) {
				do {
					this.stateModel_20_Template2();
					copyResult_Template2();
					SingleSolution sSol = new SingleSolution();
					sSol.T = new int[2 * D.nbSlotsPerHalfDay][D.nbDays_20];
					// int session = 0;
					// session = x_session_20[0].getValue();
					// System.out.println("session["+n+"] = "+ session);
					// sol[n].position = n;
					solArray[n].position = n;
					sSol.position = n;
					for (int i = 0; i < 2 * D.nbSlotsPerHalfDay; i++)
						for (int j = 0; j < D.nbDays_20; j++) {
							// sol[n].T[i][j] = -1;
							solArray[n].T[i][j] = -1;
							sSol.T[i][j] = -1;
						}

					int idxFc = -1;
					FCourse fc = null;
					ClassCourse c = null;
					for (int i_slot = 0; i_slot < D.nbSlotsPerHalfDay * 2; i_slot++) {
						for (int i_day = 0; i_day < D.nbDays_20; i_day++) {
							idxFc = findFCourse_full(i_day, i_slot);
							if (idxFc >= 0) {
								fc = D.fCourses_20[idxFc];
								c = D.courseOfFCourse_20.get(fc);
								// sol[n].T[i_slot][i_day] = c.ID;
								solArray[n].T[i_slot][i_day] = c.ID;
								sSol.T[i_slot][i_day] = c.ID;
								// session = x_session_20[idxFc].getValue();
							}
						}
					}
					solutionList.add(sSol);

					/*
					 * out.println("no."+ n + ": position = "+ sol[n].position);
					 * out.println("<table border = 1>"); for (int i = 0; i < 2
					 * * D.nbSlotsPerHalfDay; i++) { out.print("<tr>"); for (int
					 * j = 0; j < D.nbDays_20; j++) { out.print(
					 * "<td height = 20 width = 20 "); String str = ""; if
					 * (s[i][j] >= 0){ str = "M" + s[i][j]; ClassCourse c =
					 * D.mID2Course.get(s[i][j]); if(sol[n].T[i][j] >= 0 ){ str
					 * = "M"+sol[n].T[i][j]; ClassCourse cc =
					 * D.mID2Course.get(sol[n].T[i][j]); int idx =
					 * D.mCourse2Index.get(cc); int stt = D.statusOfCourse[idx];
					 * if (stt == 3) { out.println("bgcolor='yellow'> "); } else
					 * { // out.println("bgcolor='green'> "); // if (session ==
					 * 0) { out.println("bgcolor='green'> "); // } else { //
					 * out.println("bgcolor='blue'> "); // } }
					 * 
					 * out.println(str); } out.println("</td>"); // out.print(
					 * "<td height = 20 width = 20 bgcolor='green'>" + str +
					 * "</td>\n"); } out.println("</tr>"); }
					 * out.println("</table>");
					 * out.println("-----------------------\n"); out.println();
					 */

					n++;
					if (n > limit - 1) {
						break;
					}

				} while (this.solver.nextSolution());
			}
			if (solutionList.size() < solArray.length) {
				System.out.println("list size = " + solutionList.size() + ", sol array size = " + solArray.length);
				if (solutionList.size() <= 0) {
					System.out.println("Not feasible.");
					System.exit(1);
				}
				sol = new SingleSolution[solutionList.size()];
				// int idx = 0;
				// for (SingleSolution singleSolution : solArray) {
				// sol[idx] = singleSolution;
				// idx++;
				// }
				for (int i = 0; i < solutionList.size(); i++) {
					sol[i] = solutionList.get(i);
				}

			} else {
				sol = solArray;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// out.close();
	}

	public void findSolution_Template1(String fn, int limit) {
		ArrayList<SingleSolution> solutionList = new ArrayList<SingleSolution>();
		int n = 0;
		// sol = new SingleSolution[limit];
		SingleSolution[] solArray = new SingleSolution[limit];
		for (int i = 0; i < solArray.length; i++) {
			// sol[i] = new SingleSolution();
			solArray[i] = new SingleSolution();
			// sol[i].T = new int[2*D.nbSlotsPerHalfDay][D.nbDays_20];
			solArray[i].T = new int[2 * D.nbSlotsPerHalfDay][D.nbDays_20];
		}
		// PrintWriter out = null;
		try {
			// out = new PrintWriter(fn);
			if (this.solver.solve()) {
				do {
					this.stateModel_20_Template1();
					copyResult_Template1();
					SingleSolution sSol = new SingleSolution();
					sSol.T = new int[2 * D.nbSlotsPerHalfDay][D.nbDays_20];
					// int session = 0;
					// session = x_session_20[0].getValue();
					// System.out.println("session["+n+"] = "+ session);
					// sol[n].position = n;
					solArray[n].position = n;
					sSol.position = n;
					for (int i = 0; i < 2 * D.nbSlotsPerHalfDay; i++)
						for (int j = 0; j < D.nbDays_20; j++) {
							// sol[n].T[i][j] = -1;
							solArray[n].T[i][j] = -1;
							sSol.T[i][j] = -1;
						}

					int idxFc = -1;
					FCourse fc = null;
					ClassCourse c = null;
					for (int i_slot = 0; i_slot < D.nbSlotsPerHalfDay * 2; i_slot++) {
						for (int i_day = 0; i_day < D.nbDays_20; i_day++) {
							idxFc = findFCourse_full(i_day, i_slot);
							if (idxFc >= 0) {
								fc = D.fCourses_20[idxFc];
								c = D.courseOfFCourse_20.get(fc);
								// sol[n].T[i_slot][i_day] = c.ID;
								solArray[n].T[i_slot][i_day] = c.ID;
								sSol.T[i_slot][i_day] = c.ID;
								// session = x_session_20[idxFc].getValue();
							}
						}
					}
					solutionList.add(sSol);

					/*
					 * out.println("no."+ n + ": position = "+ sol[n].position);
					 * out.println("<table border = 1>"); for (int i = 0; i < 2
					 * * D.nbSlotsPerHalfDay; i++) { out.print("<tr>"); for (int
					 * j = 0; j < D.nbDays_20; j++) { out.print(
					 * "<td height = 20 width = 20 "); String str = ""; if
					 * (s[i][j] >= 0){ str = "M" + s[i][j]; ClassCourse c =
					 * D.mID2Course.get(s[i][j]); if(sol[n].T[i][j] >= 0 ){ str
					 * = "M"+sol[n].T[i][j]; ClassCourse cc =
					 * D.mID2Course.get(sol[n].T[i][j]); int idx =
					 * D.mCourse2Index.get(cc); int stt = D.statusOfCourse[idx];
					 * if (stt == 3) { out.println("bgcolor='yellow'> "); } else
					 * { // out.println("bgcolor='green'> "); // if (session ==
					 * 0) { out.println("bgcolor='green'> "); // } else { //
					 * out.println("bgcolor='blue'> "); // } }
					 * 
					 * out.println(str); } out.println("</td>"); // out.print(
					 * "<td height = 20 width = 20 bgcolor='green'>" + str +
					 * "</td>\n"); } out.println("</tr>"); }
					 * out.println("</table>");
					 * out.println("-----------------------\n"); out.println();
					 */

					n++;
					if (n > limit - 1) {
						break;
					}

				} while (this.solver.nextSolution());
			}

			if (solutionList.size() < solArray.length) {
				System.out.println("list size = " + solutionList.size() + ", sol array size = " + solArray.length);
				sol = new SingleSolution[solutionList.size()];
				// int idx = 0;
				// for (SingleSolution singleSolution : solArray) {
				// sol[idx] = singleSolution;
				// idx++;
				// }
				for (int i = 0; i < solutionList.size(); i++) {
					sol[i] = solutionList.get(i);
				}

			} else {
				sol = solArray;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// out.close();
	}

	public void find() {
		TimeTableOneClass_SM TT = new TimeTableOneClass_SM();
		TT.D = new DataOneClass();

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TimeTableOneClass_SM TT = new TimeTableOneClass_SM();
		TT.D = new DataOneClass();
		TT.D.loadData_SM("class6.txt");
		/*
		 * TT.D.cls = new entities.Class(); TT.D.cls.ID = 1; Course[] c =
		 * TT.D.courses; for (Course course : c) { System.out.println("c = "
		 * +course.ID); }
		 */
		// TT.printHTML_all("a.html", 10);
		TT.model = new CPModel();
		TT.solver = new CPSolver();
		TT.stateModel_Template2();
		TT.solver.read(TT.model);
		// boolean ok = TT.solver.solveAll();
		// System.out.println("solveall = "+ok+", nbSolution =
		// "+TT.solver.getNbSolutions());
		// TT.findSolution("sol_20.html",100);
		TT.printHTML_all_Template2("sol.html", 3000);

	}

}
