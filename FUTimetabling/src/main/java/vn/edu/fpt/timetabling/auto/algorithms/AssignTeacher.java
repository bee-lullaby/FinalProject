package vn.edu.fpt.timetabling.auto.algorithms;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeSet;

import choco.Choco;
import choco.cp.model.CPModel;
import choco.cp.solver.CPSolver;
import choco.kernel.model.constraints.Constraint;
import choco.kernel.model.variables.integer.IntegerExpressionVariable;
import choco.kernel.model.variables.integer.IntegerVariable;
import localsearch.constraints.atmost.AtMost;
import localsearch.constraints.basic.Implicate;
import localsearch.constraints.basic.IsEqual;
import localsearch.constraints.basic.LessOrEqual;
import localsearch.constraints.basic.NotEqual;
import localsearch.functions.basic.FuncMinus;
import localsearch.functions.basic.FuncPlus;
import localsearch.functions.basic.FuncVarConst;
import localsearch.functions.sum.Sum;
import localsearch.model.ConstraintSystem;
import localsearch.model.IConstraint;
import localsearch.model.IFunction;
import localsearch.model.LocalSearchManager;
import localsearch.model.VarIntLS;
import vn.edu.fpt.timetabling.auto.entities.ClassCourse;
import vn.edu.fpt.timetabling.auto.entities.ClassFU;
import vn.edu.fpt.timetabling.auto.entities.Course;
import vn.edu.fpt.timetabling.auto.entities.DataCenter;
import vn.edu.fpt.timetabling.auto.entities.SingleSolution;
import vn.edu.fpt.timetabling.auto.entities.Teacher;
import vn.edu.fpt.timetabling.auto.userdefinefunction.AtMostOneInTwo;

public class AssignTeacher {
	public DataCenter DA;
	CPSolver solver;
	CPModel model;
	IntegerVariable[] x; // x[i] = a: the a'th teacher will teach class-course i
	IntegerVariable[][] y; // y[i][j] = 1 if teacher i teachs class-course j
	IntegerVariable[] sum; // sum[i] = sum of a row of y i.e:
							// y[i][0]+...+y[i][nbCourse-1]
	int nbCourse = 4;
	int nbTeacher = 3;
	int[][] conflict = new int[][] { { 0, 1, 0, 0 }, { 1, 0, 0, 1 }, { 0, 0, 0, 1 }, { 0, 1, 1, 0 } };
	int[][] teacherCourse = new int[][] { { 1, 1, 0, 0 }, { 1, 0, 1, 1 }, { 0, 1, 0, 1 } };
	int[][] courseTeacher = new int[][] { { 1, 1, 0 }, { 1, 0, 1 }, { 0, 1, 0 }, { 0, 1, 1 } };

	public HashMap<Teacher, ArrayList<ClassCourse>> mTeacher2NBCourse;

	// public HashMap<Teacher, ArrayList<ClassCourse>>
	// mTeacher2AssignedClassCourse;
	// public HashMap<Course, ArrayList<Teacher>> mClassCourse2AssignedTeacher;
	public TreeSet<Integer>[] daySet;

	// public int[] aClassCourse2Session;
	// public int[] aClassCourse2Day;
	// public int[] aClassCourse2Block;
	// public int[] aClassCourse2Noon;
	// public int[] aClassCourse2BeginEndDay;

	boolean[] isAlreadyPicked;

	public double[][] scoreMatrix;
//	final static int WEIGHT_BLOCK = 1;
//	final static int WEIGHT_SESSION = 5;
	final static int WEIGHT_DAY = 10; // w1
	final static int WEIGHT_NOON = -5; //w2
//	final static int WEIGHT_BEGINENDDAY = -3;
//	final static int WEIGHT_TARGETREMAIN = 2;
//	final static int WEIGHT_RELATIVE = 10;
	// final static int MAX_PICKSCORE = 1000;
	final static boolean FAVOR_THEBESTCASE = false;

	final static int SCORERANGE = 8;

	IntegerVariable[][] x_cp;
	IntegerExpressionVariable[] sum_cp;
	IntegerExpressionVariable[] sum_cp_col;
	IntegerVariable obj;
	// ////////////////////////////////////////////////
	LocalSearchManager ls;
	ConstraintSystem S;
	VarIntLS[] x_LS;
	VarIntLS[][] X;
	VarIntLS[][] y_LS;
	VarIntLS[] sum_LS;
	VarIntLS[] SUM;
	IFunction objfunc;

	public void stateModel_LS2() {
		ls = new LocalSearchManager();
		X = new VarIntLS[DA.nbClassCourse][DA.nbTeacher];
		SUM = new VarIntLS[DA.nbTeacher];

		for (int c = 0; c < DA.nbClassCourse; c++) {
			for (int t = 0; t < DA.nbTeacher; t++) {
				X[c][t] = new VarIntLS(ls, 0, 1);
			}
		}
		for (int i = 0; i < DA.nbTeacher; i++) {
			SUM[i] = new VarIntLS(ls, 0, DA.nbClassCourse);
		}

		S = new ConstraintSystem(ls);

		// =============================================
		// % 2 courses conflict thi ko cung giao vien
		VarIntLS[] temp = new VarIntLS[2];
		for (int t = 0; t < DA.nbTeacher; t++) {
			for (int c1 = 0; c1 < DA.nbClassCourse - 1; c1++) {
				for (int c2 = c1 + 1; c2 < DA.nbClassCourse; c2++) {
					if (conflict[c1][c2] == 1) {
						// constraint nay sai vi neu 2 cai deu bang 0 van duoc
						// S.post(new NotEqual(X[c1][t], X[c2][t]));

						// 2 mon bi conflict thi 1 nguoi chi day toi da 1 trong
						// 2 mon thoi
						// OR ko day ca 2 mon
						S.post(new AtMostOneInTwo(X[c1][t], X[c2][t], 1));

						/*
						 * temp[0] = X[c1][t]; temp[1] = X[c2][t]; S.post(new
						 * AtMost(temp, 1, 1));
						 */
					}
				}
			}
		}

		/*
		 * mỗi course chỉ đc dạy bởi 1 giáo viên hoac ko ai day
		 */
		for (int c = 0; c < DA.nbClassCourse; c++) {
			S.post(new AtMost(X[c], 1, 1));
		}

		// mỗi course chỉ được dạy bởi giáo viên trong list dự kiến
		for (int c = 0; c < DA.nbClassCourse; c++) {
			ClassCourse cc = DA.classCourses[c];
			Course co = DA.mClassCourse2Course.get(cc);
			ArrayList<Teacher> LLL = DA.mCourse2TeacherList.get(co);
			for (int t = 0; t < DA.nbTeacher; t++) {
				Teacher tc = DA.teachers[t];
				if (LLL.contains(tc) == false) {
					S.post(new IsEqual(X[c][t], 0));
					X[c][t].setValuePropagate(0);
				}
			}
		}

		// % mỗi giáo viên dạy phải x môn trở lên 1 kì
		IFunction[] ss = new IFunction[DA.nbTeacher];
		for (int i = 0; i < DA.nbTeacher; i++) {
			ss[i] = new FuncVarConst(ls, 0);
		}

		for (int t = 0; t < DA.nbTeacher; t++) {
			for (int c = 0; c < DA.nbClassCourse; c++) {
				ss[t] = new FuncPlus(ss[t], X[c][t]);
			}
			// ko co cai nay thi ra toan 0, tuc la ko gan giao vien thi ko co vi
			// pham
			// neu co thi bat giao vien phai dc gan
			S.post(new LessOrEqual(new FuncVarConst(ls, DA.lowerBoundnbClassCourseOfATeacher[t]), ss[t]));
			S.post(new LessOrEqual(ss[t], new FuncVarConst(ls, 8)));
		}

		objfunc = new FuncVarConst(ls, 0);
		for (int i = 0; i < ss.length; i++) {
			objfunc = new FuncPlus(objfunc, ss[i]);
		}
		objfunc = new FuncMinus(new FuncVarConst(ls, 0), objfunc);

		ls.close();
	}

	public void stateModel_LS() {
		ls = new LocalSearchManager();
		x_LS = new VarIntLS[nbCourse];
		y_LS = new VarIntLS[nbTeacher][nbCourse];
		sum_LS = new VarIntLS[nbTeacher];

		for (int i = 0; i < nbCourse; i++) {
			x_LS[i] = new VarIntLS(ls, 0, nbTeacher - 1);
		}
		for (int i = 0; i < nbTeacher; i++) {
			for (int j = 0; j < nbCourse; j++) {
				y_LS[i][j] = new VarIntLS(ls, 0, 1);
			}
		}
		for (int i = 0; i < nbTeacher; i++) {
			sum_LS[i] = new VarIntLS(ls, 0, nbCourse);
		}

		S = new ConstraintSystem(ls);

		ArrayList<Integer>[] L = new ArrayList[nbCourse];
		L[0] = new ArrayList<Integer>();
		L[0].add(0);
		L[0].add(1);// L[0].add(2);

		L[1] = new ArrayList<Integer>();
		L[1].add(0);
		// L[1].add(1);
		L[1].add(2);

		L[2] = new ArrayList<Integer>();
		L[2].add(1);// L[0].add(1); L[0].add(2);

		L[3] = new ArrayList<Integer>();
		L[3].add(1);
		L[3].add(2);// L[3].add(0);
		for (int c = 0; c < nbCourse; c++) {
			for (int t = 0; t < nbTeacher; t++) {
				if (!L[c].contains(t)) {
					S.post(new NotEqual(x_LS[c], t));
				}
			}
		}
		VarIntLS[] tt = y_LS[0];

		for (int c1 = 0; c1 < nbCourse - 1; c1++) {
			for (int c2 = c1 + 1; c2 < nbCourse; c2++) {
				if (conflict[c1][c2] == 1) {
					// S.post(new NotEqual(x_LS[c1],x_LS[c2]));
					// S.post(new AtMostOneInTwo(x_LS[c1],x_LS[c2],));
					for (int t = 0; t < nbTeacher; t++) {
						S.post(new AtMostOneInTwo(x_LS[c1], x_LS[c2], t));
					}
				}
			}
		}

		for (int c = 0; c < nbCourse; c++) {
			for (int t = 0; t < nbTeacher; t++) {
				// if x[c]>=0 && t is in L[c] then y[t][x[c]] = 1;
				// else y[t][c] = 0
				// if x[c] = t, y[t][c] = 1;
				IConstraint c1 = new IsEqual(new FuncVarConst(ls, t), x_LS[c]);
				IConstraint c2 = new IsEqual(new FuncVarConst(ls, 1), y_LS[t][c]);
				S.post(new Implicate(c1, c2));

				IConstraint c3 = new NotEqual(new FuncVarConst(ls, t), x_LS[c]);
				IConstraint c4 = new NotEqual(new FuncVarConst(ls, t), y_LS[t][c]);
				S.post(new Implicate(c3, c4));
			}

		}

		// sumall = sum(y[t]); maximize sumall
		IFunction[] temp = new IFunction[nbTeacher];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = new FuncVarConst(ls, 0);
		}

		for (int i = 0; i < nbTeacher; i++) {
			temp[i] = new Sum(y_LS[i]);
		}
		objfunc = new FuncVarConst(ls, 0);
		for (int i = 0; i < temp.length; i++) {
			objfunc = new FuncPlus(objfunc, temp[i]);
		}

		objfunc = new FuncMinus(new FuncVarConst(ls, 0), objfunc);
		ls.close();
	}

	public void search_LS() {
		localsearch.search.TabuSearch ts = new localsearch.search.TabuSearch();
		ts.search(S, 50, 3000, 400, 30);

	}

	public void search_OLS() {
		localsearch.search.TabuSearch ts = new localsearch.search.TabuSearch();
		ts.searchMaintainConstraints(objfunc, S, 100, 3000, 400, 30);
	}

	// //////////////////////////////////////////////////
	public void stateModel_CP() {
		x_cp = new IntegerVariable[nbTeacher][nbCourse];
		sum_cp = new IntegerExpressionVariable[nbTeacher];
		sum_cp_col = new IntegerExpressionVariable[nbCourse];
		obj = new IntegerVariable("obj", 0, nbCourse);

		for (int t = 0; t < nbTeacher; t++) {
			for (int c = 0; c < nbCourse; c++) {
				x_cp[t][c] = Choco.makeIntVar("", 0, 1);
			}
			sum_cp[t] = Choco.makeIntVar("", 0, nbCourse);
		}
		obj = Choco.makeIntVar("", 0, nbCourse);
		// -------
		for (int t = 0; t < nbTeacher; t++) {
			for (int c1 = 0; c1 < nbCourse - 1; c1++) {
				for (int c2 = c1 + 1; c2 < nbCourse; c2++) {
					Constraint c_0 = Choco.eq(x_cp[t][c1], 0);
					Constraint c_1 = Choco.eq(x_cp[t][c2], 0);
					model.addConstraint(Choco.or(c_0, c_1));
				}
			}
		}
		// --------
		ArrayList<Integer>[] L = new ArrayList[nbCourse];
		L[0] = new ArrayList<Integer>();
		L[0].add(0);
		L[0].add(1);// L[0].add(2);

		L[1] = new ArrayList<Integer>();
		L[1].add(0);
		// L[1].add(1);
		L[1].add(2);

		L[2] = new ArrayList<Integer>();
		L[2].add(1);// L[0].add(1); L[0].add(2);

		L[3] = new ArrayList<Integer>();
		L[3].add(1);
		L[3].add(2);// L[3].add(0);

		for (int c = 0; c < nbCourse; c++) {
			for (int t = 0; t < nbTeacher; t++) {
				if (L[c].contains(t) == false) {
					model.addConstraint(Choco.neq(x_cp[t][c], 1));
				}
			}
		}
		// =============================================
		for (int t = 0; t < nbTeacher; t++) {
			sum_cp[t] = Choco.sum(x_cp[t]);
			model.addConstraint(Choco.leq(1, sum_cp[t]));
		}

		IntegerVariable[] temp2 = new IntegerVariable[nbTeacher];
		for (int c = 0; c < nbCourse; c++) {
			for (int t = 0; t < nbTeacher; t++) {
				temp2[t] = x_cp[t][c];
			}
			sum_cp_col[c] = Choco.sum(temp2);
			model.addConstraint(Choco.leq(sum_cp_col[c], 1));
		}

		model.addConstraint(Choco.eq(Choco.sum(sum_cp), obj));

	}

	public void stateModel() {
		// x_day[i] = Choco.makeIntVar("x_day[" + i + "]", 0, 3);
		nbCourse = DA.nbClassCourse;
		nbTeacher = DA.nbTeacher;

		x = new IntegerVariable[nbCourse];
		y = new IntegerVariable[nbTeacher][nbCourse];
		sum = new IntegerVariable[nbTeacher];

		/*
		 * x[0] = Choco.makeIntVar("x_" + 0, new int[] { 0, 1 }, ""); x[1] =
		 * Choco.makeIntVar("x_" + 1, new int[] { 0, 2 }, ""); x[2] =
		 * Choco.makeIntVar("x_" + 2, new int[] { 1 }, ""); x[3] =
		 * Choco.makeIntVar("x_" + 3, new int[] { 1, 2 }, "");
		 */
		for (int ci = 0; ci < DA.nbClassCourse; ci++) {
			ClassCourse cc = DA.classCourses[ci];
			Course c = DA.mClassCourse2Course.get(cc);
			ArrayList<Teacher> L = DA.mCourse2TeacherList.get(c);
			ArrayList<Integer> list = new ArrayList<Integer>();
			// int[] gv = new int[L.size()];
			// int i = 0;
			for (Teacher tc : L) {
				int idx = DA.mTeacher2Index.get(tc);
				// gv[i] = idx;
				// i++;
				list.add(idx);
			}

			if (L.size() == 0) {
				x[ci] = Choco.makeIntVar("x_" + ci, -1, -1, "");
				// System.out.println("");
			} else {

				x[ci] = Choco.makeIntVar("x_" + ci, list, "");
			}
		}

		/*
		 * for (int i = 0; i < nbTeacher; i++) { for (int j = 0; j < nbCourse;
		 * j++) { y[i][j] = Choco.makeIntVar("", 0, 1); } }
		 */

		for (int t = 0; t < nbTeacher; t++) {
			for (int c1 = 0; c1 < nbCourse - 1; c1++) {
				for (int c2 = c1 + 1; c2 < nbCourse; c2++) {
					if (conflict[c1][c2] == 1) {
						Constraint c_0 = Choco.neq(x[c1], t);
						Constraint c_1 = Choco.neq(x[c2], t);
						model.addConstraint(Choco.or(c_0, c_1));
					}
				}
			}
		}

		for (int c = 0; c < nbCourse; c++) {
			// y[x[c]][c];
			// int temp = x[c].getValues()[0];
			// model.addConstraint(Choco.eq(y[temp][c], 1));
			for (int t = 0; t < nbTeacher; t++) {
				// if x[c]>=0 && t is in L[c] then y[t][x[c]] = 1;
				// else y[t][c] = 0
			}

		}
		// for (int i = 0; i < nbTeacher; i++) {
		// for (int j = 0; j < nbCourse; j++) {
		// model.addConstraint(Choco.leq(y[i][j], 1));
		// }
		//
		// }

	}

	public void search() {
		solver.read(model);
		// solver.maximize(solver.getVar(obj), false);
		// solver.minimize(solver.getVar(obj), false);
		// System.out.println("Objective : " + (solver.getVar(obj).getVal()));
		Boolean ok = solver.solveAll();
		System.out.println("Solve = " + ok + ", nbSolutions = " + solver.getNbSolutions());
	}

	public void search_CP() {
		solver.read(model);
		solver.maximize(solver.getVar(obj), true);
		System.out.println("Solution:");
		if (this.solver.solve()) {
			int idx = -1;
			do {
				idx++;
				System.out.println(idx);
				for (int tc = 0; tc < nbTeacher; tc++) {
					for (int c = 0; c < nbCourse; c++) {
						System.out.print(solver.getVar(x_cp[tc][c]).getVal() + " ");
					}
					System.out.println();
				}
				System.out.println();
			} while (solver.nextSolution());

		}

	}

	// ///////////////////////////////////////////////////
	public void addTeacher(int[] demand, int[] supply) {

	}

	public int[][] loadConflictMatrix(String fn) {
		int nbc = DA.nbClassCourse;
		int[][] conflict1 = new int[nbc][nbc];
		try {
			Scanner in = new Scanner(new File(fn));
			// String line = in.nextLine();
			// System.out.println(line);
			int stt = -1;
			for (int i = 0; i < nbc; i++) {
				for (int j = 0; j < nbc; j++) {
					stt = in.nextInt();
					conflict1[i][j] = stt;
				}
			}

			/*
			 * for (int i = 0; i < nbc; i++) { for (int j = 0; j < nbc; j++) {
			 * System.out.print(" "+conflict[i][j]); if (conflict[i][j]==-1) {
			 * System.out.println("BUG"); System.exit(1);; } }
			 * System.out.println(); }
			 */
			in.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("Done loading conflict matrix!");
		return conflict1;
	}

	public void stateModelFull() {

		nbCourse = DA.nbClassCourse;
		nbTeacher = DA.nbTeacher;
		ls = new LocalSearchManager();
		x_LS = new VarIntLS[nbCourse];
		y_LS = new VarIntLS[nbTeacher][nbCourse];
		sum_LS = new VarIntLS[nbTeacher];

		for (int i = 0; i < nbCourse; i++) {
			x_LS[i] = new VarIntLS(ls, 0, nbTeacher - 1);
		}
		for (int i = 0; i < nbTeacher; i++) {
			for (int j = 0; j < nbCourse; j++) {
				y_LS[i][j] = new VarIntLS(ls, 0, 1);
			}
		}
		for (int i = 0; i < nbTeacher; i++) {
			sum_LS[i] = new VarIntLS(ls, 0, nbCourse);
		}

		S = new ConstraintSystem(ls);

		for (int c = 0; c < nbCourse; c++) {
			ClassCourse cc = DA.classCourses[c];
			Course co = DA.mClassCourse2Course.get(cc);
			ArrayList<Teacher> L = DA.mCourse2TeacherList.get(co);
			for (int t = 0; t < nbTeacher; t++) {
				Teacher tc = DA.teachers[t];
				if (!L.contains(tc)) {
					S.post(new NotEqual(x_LS[c], t));
				}
			}
		}

		for (int c1 = 0; c1 < nbCourse - 1; c1++) {
			for (int c2 = c1 + 1; c2 < nbCourse; c2++) {
				if (conflict[c1][c2] == 1) {
					// S.post(new NotEqual(x_LS[c1],x_LS[c2]));
					// S.post(new AtMostOneInTwo(x_LS[c1],x_LS[c2],));
					for (int t = 0; t < nbTeacher; t++) {
						S.post(new AtMostOneInTwo(x_LS[c1], x_LS[c2], t));
					}
				}
			}
		}

		for (int c = 0; c < nbCourse; c++) {
			for (int t = 0; t < nbTeacher; t++) { // if x[c]>=0 && t is in L[c]
													// then y[t][x[c]] = 1; //
													// else
													// y[t][c] = 0; //if x[c] =
													// t, y[t][c] = 1;
				IConstraint c1 = new IsEqual(new FuncVarConst(ls, t), x_LS[c]);
				IConstraint c2 = new IsEqual(new FuncVarConst(ls, 1), y_LS[t][c]);
				S.post(new Implicate(c1, c2));

				IConstraint c3 = new NotEqual(new FuncVarConst(ls, t), x_LS[c]);
				IConstraint c4 = new IsEqual(new FuncVarConst(ls, 0), y_LS[t][c]);
				S.post(new Implicate(c3, c4));
			}

		}

		// sumall = sum(y[t]); maximize sumall
		IFunction[] temp = new IFunction[nbTeacher];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = new FuncVarConst(ls, 0);
		}

		for (int i = 0; i < nbTeacher; i++) {
			temp[i] = new Sum(y_LS[i]);
			// S.post(new LessOrEqual(new FuncVarConst(ls, 1),temp[i]));
			S.post(new LessOrEqual(new FuncVarConst(ls, DA.lowerBoundnbClassCourseOfATeacher[i]), temp[i]));
			S.post(new LessOrEqual(temp[i], new FuncVarConst(ls, 12)));
		}

		objfunc = new FuncVarConst(ls, 0);
		for (int i = 0; i < temp.length; i++) {
			objfunc = new FuncPlus(objfunc, temp[i]);
		}

		objfunc = new FuncMinus(new FuncVarConst(ls, 0), objfunc);

		ls.close();
	}

	public void findSol() {
		DA = new DataCenter();
		DA.loadData_Course_Class("data_all_sm_merged.txt");
		DA.loadData_Teacher_UsingCode("teacherDataCode.txt"); // data da thay
																// doi
		conflict = loadConflictMatrix("conflictm.txt");

		/*
		 * System.out.println("conflict matrix"); for (int i = 0; i <
		 * conflict.length; i++) { for (int j = 0; j < conflict.length; j++) {
		 * System.out.print(conflict[i][j] + " "); } System.out.println(); }
		 * System.out.println(); System.out.println("prospective matrix");
		 * 
		 * // course-teacherList for (int i = 0; i < DA.nbClassCourse; i++) {
		 * ClassCourse cc = DA.classCourses[i]; Course c =
		 * DA.mClassCourse2Course.get(cc); ArrayList<Teacher> L =
		 * DA.mCourse2TeacherList.get(c); for (int t = 0; t < DA.nbTeacher; t++)
		 * { if (L.contains(DA.teachers[t])) { System.out.print("1 "); } else {
		 * System.out.print("0 "); }
		 * 
		 * }
		 * 
		 * System.out.println(); }
		 */

		// a.stateModelFull();
		// a.search_LS();
		// a.search_OLS();
		// a.printsolHTML("assignTeacherResult.html");

		stateModel_LS2();
		search_LS();
		printsolHTML2("");
		// System.out.println("\nMore optimized solution:");
		search_OLS();

		printsolHTML2("");
		SingleSolution[] sol = loadbeingUsedTimeTable("beingusedTT.dat");
		printAllTeacherHTML("teachersol.html", sol);

	}

	public void completeMapClassCourse2AssignedTeacher() {
		DA.mClassCourse2AssignedTeacher = new HashMap<>();
		DA.mTeacher2AssignedClassCourse = new HashMap<>();
		for (Teacher tc : DA.teachers) {
			DA.mTeacher2AssignedClassCourse.put(tc, new ArrayList<>());
		}

		for (int ic = 0; ic < DA.nbClassCourse; ic++) {
			for (int it = 0; it < DA.nbTeacher; it++) {
				if (X[ic][it].getValue() == 1) {
					ClassCourse cc = DA.classCourses[ic];
					Teacher t = DA.teachers[it];
					DA.mClassCourse2AssignedTeacher.put(cc, t);
					DA.mTeacher2AssignedClassCourse.get(t).add(cc);
					break; // tim thay teacher roi thi break
				}
				//
			}
		}
	}

	public void findSol2() {
		DA = new DataCenter();
		DA.loadData_Course_Class("data_all_sm.txt");
		DA.loadData_Teacher_UsingCode("teacherDataCode.txt");
		conflict = loadConflictMatrix("conflict_matrix.txt");
		model = new CPModel();
		stateModel();
		solver = new CPSolver();
		solver.read(model);

		for (int ci = 0; ci < DA.nbClassCourse; ci++) {
			ClassCourse cc = DA.classCourses[ci];
			Course c = DA.mClassCourse2Course.get(cc);
			ArrayList<Teacher> L = DA.mCourse2TeacherList.get(c);
			System.out.println("code = " + c.code + ", supply = " + L.size());

			// if (L.size() == 0) {
			// System.out.println("L size = "+L.size());
			// } else {
			// System.out.println("L size = "+L.size());
			// }
			// int[] tc = new int[L.size()];

		}
		boolean ok = solver.solve();
		System.out.println(ok);
		printsolCP();
	}

	public void printsolHTML2(String fn) {
		DA.mTeacher2AssignedClassCourse = new HashMap<Teacher, ArrayList<ClassCourse>>();
		DA.mCourse2AssignedTeacher = new HashMap<Course, ArrayList<Teacher>>();
		// ArrayList<ClassCourse> NY = new ArrayList<ClassCourse>();
		for (int t = 0; t < DA.nbTeacher; t++) {
			Teacher tc = DA.teachers[t];
			DA.mTeacher2AssignedClassCourse.put(tc, new ArrayList<ClassCourse>());
		}
		for (int c = 0; c < DA.nbCourse; c++) {
			Course co = DA.courses[c];
			DA.mCourse2AssignedTeacher.put(co, new ArrayList<Teacher>());
		}
		int count = 0;
		int nbwrong = 0;
		for (int c = 0; c < DA.nbClassCourse; c++) {
			ClassCourse cc = DA.classCourses[c];
			ClassFU cls = DA.mClassCourse2Class.get(cc);
			boolean gd = false;
			for (int t = 0; t < DA.nbTeacher; t++) {
				if (X[c][t].getValue() == 1) {
					gd = true;
					Teacher tc = DA.teachers[t];
					if (S.violations(X[c][t]) > 0) {
						System.out.println(cls.code + "-" + cc.code + ": (x)" + tc.code);
						nbwrong++;
					} else {
						System.out.println(cls.code + "-" + cc.code + ": " + tc.code);
						DA.mTeacher2AssignedClassCourse.get(tc).add(cc);
						Course co = DA.mClassCourse2Course.get(cc);
						if (!DA.mCourse2AssignedTeacher.get(co).contains(tc)) {
							DA.mCourse2AssignedTeacher.get(co).add(tc);
						}
					}
					//
				}
			}
			if (gd == false) {
				System.out.println(cls.code + "-" + cc.code + ": ...");
				count++;
			}
		}
		System.out.println("\nTotal " + DA.nbClassCourse + " class-course.");
		System.out.println("Number of assigned class-course: " + (DA.nbClassCourse - count) + ".");
		System.out.println("Number of not yet assigned class-course: " + count + ".");
		System.out.println("Number of wrong assigned case: " + nbwrong + ".");
		System.out.println("Accuracy (= (total-wrong)/total): "
				+ ((double) (DA.nbClassCourse - nbwrong) / (DA.nbClassCourse)) * 100 + "%.\n");

		for (Teacher tc : DA.teachers) {
			ArrayList<ClassCourse> L = DA.mTeacher2AssignedClassCourse.get(tc);
			System.out.print("tc = " + tc.code + "-<" + L.size() + ">:");
			for (ClassCourse cc : L) {
				ClassFU cls = DA.mClassCourse2Class.get(cc);
				System.out.print(" " + cls.code + "-" + cc.code);
			}
			System.out.println();
		}
		for (Course co : DA.courses) {
			ArrayList<Teacher> L = DA.mCourse2AssignedTeacher.get(co);
			System.out.print("c = " + co.code + "-<" + L.size() + ">:");
			for (Teacher tc : L) {
				System.out.print(" " + tc.code);
			}
			System.out.println();
		}

	}

	public void printsolHTML(String fn) {
		System.out.println("\n(x): la ghep sai, (*): mon hoc 2 blocks\n");
		mTeacher2NBCourse = new HashMap<Teacher, ArrayList<ClassCourse>>();
		for (int t = 0; t < DA.nbTeacher; t++) {
			mTeacher2NBCourse.put(DA.teachers[t], new ArrayList<ClassCourse>());
		}

		try {
			for (int i = 0; i < DA.nbClassCourse; i++) {

				ClassCourse cc = DA.classCourses[i];
				ClassFU cl = DA.mClassCourse2Class.get(cc);
				System.out.print(i + "-" + cl.code + "-");
				Course c = DA.mClassCourse2Course.get(cc);
				System.out.print(c.code + ": ");
				int tIdx = x_LS[i].getValue();

				Teacher tc = DA.teachers[tIdx];
				mTeacher2NBCourse.get(tc).add(cc);
				System.out.print(tc.ID + "-");
				if (S.violations(x_LS[i]) > 0) {
					System.out.println(tc.code + "(x)");
				} else {
					System.out.println(tc.code);
				}
			}

			for (int t = 0; t < DA.nbTeacher; t++) {
				Teacher tc = DA.teachers[t];
				ArrayList<ClassCourse> L = mTeacher2NBCourse.get(tc);
				System.out.print(tc.ID + "-" + tc.code + "-<" + L.size() + ">: ");
				int wrong = 0;
				for (ClassCourse clc : L) {
					int idx = DA.mClassCourse2Index.get(clc);

					Course c = DA.mClassCourse2Course.get(clc);
					ClassFU cl = DA.mClassCourse2Class.get(clc);
					// System.out.println(cl.code);
					if (S.violations(x_LS[idx]) > 0) {
						wrong++;
						/*
						 * if (clc.stt == 3) { System.out.print("(*)"); }
						 * System.out.print(cl.code + "-" + c.code + "(x), ");
						 */
					} else {
						if (clc.stt == 3) {
							System.out.print("(*)");
						}
						System.out.print(cl.code + "-" + c.code + ", ");
					}

				}
				System.out.println(": <" + (L.size() - wrong) + ">");
				// System.out.println();
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void teacherViewer(SingleSolution[] sol, int nbTeacher) {
		// int[][] TT = new int[DA.nbDays_20][DA.nbSlotsPerHalfDay*2];
		for (int tc = 0; tc < nbTeacher; tc++) {
			Teacher t = DA.teachers[tc];
			System.out.println();
			ArrayList<ClassCourse> L = DA.mTeacher2AssignedClassCourse.get(t);
			for (ClassCourse cc : L) {
				ClassFU cls = DA.mClassCourse2Class.get(cc);
				int clIdx = DA.mClass2Index.get(cls);
				SingleSolution ss = sol[clIdx];
				for (int d = 0; d < DA.nbDays_20; d++) {
					for (int sl = 0; sl < DA.nbSlotsPerHalfDay * 2; sl++) {
						if (ss.T[sl][d] == cc.ID) {
							System.out.println("tc = " + t.code + ", d = " + d + ", sl = " + sl + ", cc = " + cc.code
									+ ", cls = " + cls.code);
						}
					}
				}
			}
		}
	}

	public void printOneTeacherHTML(int[][] T, PrintWriter out, int tIdx) {
		out.println("Teacher = " + DA.teachers[tIdx].code);
		out.println("<table border = 1>");
		for (int i = 0; i < 2 * DA.nbSlotsPerHalfDay; i++) {
			out.print("<tr>");
			for (int j = 0; j < DA.nbDays_20; j++) {
				out.print("<td height = 40 width = 80 ");
				// String str = "";
				if (T[i][j] >= 0) {
					ClassCourse cc = DA.mID2ClassCourse.get(T[i][j]);
					ClassFU cls = DA.mClassCourse2Class.get(cc);
					Course c = DA.mClassCourse2Course.get(cc);
					// str = c.code;
					int stt = cc.stt;
					if (stt == 3) {
						out.println("bgcolor='yellow'> ");
					} else {
						out.println("bgcolor='green'> ");
					}
					out.println(c.code);
					out.println(cls.code);
				}
				out.println("</td>");
			}
			out.println("</tr>");
		}
		out.println("</table>");
		out.println();
	}

	public void printAllTeacherHTML(String fn, SingleSolution[] sol) {
		PrintWriter out = null;
		try {
			out = new PrintWriter(new File(fn));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		for (int tc = 0; tc < DA.nbTeacher; tc++) {
			int[][] teacherSol = new int[DA.nbSlotsPerHalfDay * 2][DA.nbDays_20];
			for (int i = 0; i < DA.nbSlotsPerHalfDay * 2; i++) {
				for (int j = 0; j < DA.nbDays_20; j++) {
					teacherSol[i][j] = -1;
				}
			}
			Teacher t = DA.teachers[tc];
			int tIdx = DA.mTeacher2Index.get(t);
			// System.out.println();
			ArrayList<ClassCourse> L = DA.mTeacher2AssignedClassCourse.get(t);
			for (ClassCourse cc : L) {
				ClassFU cls = DA.mClassCourse2Class.get(cc);
				int clIdx = DA.mClass2Index.get(cls);
				SingleSolution ss = sol[clIdx];
				for (int d = 0; d < DA.nbDays_20; d++) {
					for (int sl = 0; sl < DA.nbSlotsPerHalfDay * 2; sl++) {
						if (ss.T[sl][d] == cc.ID) {
							teacherSol[sl][d] = cc.ID;
							// System.out.println("tc = "+t.code+", d = "+d+",
							// sl = "+sl+", cc = "+cc.code+", cls = "+cls.code);
						}
					}
				}
			}
			try {

				printOneTeacherHTML(teacherSol, out, tIdx);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		out.close();
	}

	public void printsolCP() {
		mTeacher2NBCourse = new HashMap<Teacher, ArrayList<ClassCourse>>();
		for (int t = 0; t < DA.nbTeacher; t++) {
			mTeacher2NBCourse.put(DA.teachers[t], new ArrayList<ClassCourse>());
		}

		for (int i = 0; i < DA.nbClassCourse; i++) {

			ClassCourse cc = DA.classCourses[i];
			ClassFU cl = DA.mClassCourse2Class.get(cc);
			System.out.print(i + "-" + cl.code + "-");
			Course c = DA.mClassCourse2Course.get(cc);
			System.out.print(c.code + ": ");
			// int tIdx = x_LS[i].getValue();
			int tIdx = solver.getVar(x[i]).getVal();

			if (tIdx != -1) {
				Teacher tc = DA.teachers[tIdx];
				mTeacher2NBCourse.get(tc).add(cc);
				System.out.print(tc.ID + "-");
				System.out.println(tc.code);
			}

		}

		for (int t = 0; t < DA.nbTeacher; t++) {
			Teacher tc = DA.teachers[t];
			ArrayList<ClassCourse> L = mTeacher2NBCourse.get(tc);
			System.out.print(tc.ID + "-" + tc.code + "-<" + L.size() + ">: ");
			for (ClassCourse clc : L) {
				// int idx = DA.mClassCourseAll2Index.get(clc);

				Course c = DA.mClassCourse2Course.get(clc);
				ClassFU cl = DA.mClassCourse2Class.get(clc);
				System.out.print(cl.code + "-" + c.code + ", ");
			}
			System.out.println();
		}
	}

	////////////////////////////////////////////////////////////////////
	public void assignTeacherUsingScore(String fn_conflictMatrix, String fn_beingUsedTimeTable,
			String fn_courseScoreMatrix, ClassCourse[] arrayClassCourse) {
		// DA = new DataCenter();
		// DA.loadData_Course_Class("data_all_sm_merged.txt");
		// DA.loadData_Teacher_UsingCode("teacherDataCode.txt"); //data da thay
		// doi
		conflict = loadConflictMatrix(fn_conflictMatrix);
		SingleSolution[] sol = loadbeingUsedTimeTable(fn_beingUsedTimeTable);
		findScoreElement(sol);
		build_MatrixScore();
		normalizeMatrixScore();
		writeScoreMatrix2File(fn_courseScoreMatrix);
		isAlreadyPicked = new boolean[DA.nbClassCourse];
		for (int i = 0; i < isAlreadyPicked.length; i++) {
			isAlreadyPicked[i] = false;
		}

		assignTeacherScore(arrayClassCourse, conflict);
		printAllTeacherHTML("teachersol_new.html", sol);
	}

	public void assignTeacherScore(ClassCourse[] ccList,
			// HashMap<Teacher, ArrayList<ClassCourse>> mTeacher2AssignedCC,
			int[][] conflict) {

		DA.mClassCourse2AssignedTeacher = new HashMap<ClassCourse, Teacher>();
		DA.mTeacher2AssignedClassCourse = new HashMap<Teacher, ArrayList<ClassCourse>>();
		for (Teacher tc : DA.teachers) {
			DA.mTeacher2AssignedClassCourse.put(tc, new ArrayList<ClassCourse>());
		}

		HashSet<ClassCourse> bannedCC = new HashSet<ClassCourse>();

		int iteration = -1;
		do {
			iteration++;
			System.out.println("\nIteration = " + iteration);
			if (bannedCC.size() == ccList.length) {
				System.out.println("All cc banned.");
				break;
			}

			// pick a class-course
			// input: ccList, mCC2Status
			// output: ClassCourse pickCC
			ClassCourse pickedCC = null;
			for (ClassCourse cc : ccList) {
				int idx = DA.mClassCourse2Index.get(cc);
				boolean pickSTT = isAlreadyPicked[idx];
				if (pickSTT == false) {
					pickedCC = cc;
				}
			}
			if (pickedCC == null) {
				System.out.println("Cannot pick class-course anymore.");
				break;
			} else {
				System.out.println("picked cc = " + DA.mClassCourse2Index.get(pickedCC));
			}

			// pick a teacher
			// input: assignedCC,pickCC
			// output: Teacher pickedTeachers
			/*
			 * Course c = DA.mClassCourse2Course.get(pickCC); ArrayList<Teacher>
			 * tcList = DA.mCourse2TeacherList.get(c); Teacher pickedTeacher =
			 * null; for (Teacher t : tcList) { ArrayList<ClassCourse>
			 * assignedCC = mTeacher2AssignedCC.get(t); boolean pickable = true;
			 * if (!assignedCC.isEmpty()) { for (ClassCourse cc : assignedCC) {
			 * if (conflict(conflict, pickCC, cc)) { pickable = false; } } } if
			 * (pickable == true) { pickedTeacher = t; break; //lay teacher dau
			 * tien tim duoc } }
			 */
			Teacher pickedTeacher = null;
			pickedTeacher = pickMostPromisingTeacher(pickedCC);

			//

			// ban or assign
			if (pickedTeacher == null) {
				bannedCC.add(pickedCC);
				ClassFU cls = DA.mClassCourse2Class.get(pickedCC);
				int idx = DA.mClassCourse2Index.get(pickedCC);
				isAlreadyPicked[idx] = true;
				// System.out.println("ko tim dc teacher
				// cho"+idx+"-"+pickCC.code+"-"+cls.code);
				System.out.println(idx + "-" + pickedCC.code + "-" + cls.code + " has been banned: not found");
				System.out.println(bannedCC.size() + " cc banned.");
			} else {
				DA.mClassCourse2AssignedTeacher.put(pickedCC, pickedTeacher);
				DA.mTeacher2AssignedClassCourse.get(pickedTeacher).add(pickedCC);
				bannedCC.add(pickedCC);
				ClassFU cls = DA.mClassCourse2Class.get(pickedCC);
				int idx = DA.mClassCourse2Index.get(pickedCC);
				isAlreadyPicked[idx] = true;
				System.out
						.println("assign: " + idx + "-" + pickedCC.code + "-" + cls.code + " to " + pickedTeacher.code);
				System.out.println(idx + "-" + pickedCC.code + "-" + cls.code + " has been banned. ok");
				System.out.println(bannedCC.size() + " cc banned.");
			}
		} while (iteration < 1000);

		for (ClassCourse cc : ccList) {
			Teacher tc = DA.mClassCourse2AssignedTeacher.get(cc);
			ClassFU cls = DA.mClassCourse2Class.get(cc);
			if (tc == null) {
				System.out.println("cls = " + cls.code + ", cc = " + cc.code + ", tc = ......");
			} else {
				System.out.println("cls = " + cls.code + ", cc = " + cc.code + ", tc = " + tc.code);
			}
		}

		System.out.println("\n");
		for (Teacher tc : DA.teachers) {
			ArrayList<ClassCourse> ccL = DA.mTeacher2AssignedClassCourse.get(tc);
			System.out.print("tc = " + tc.code + "-<" + ccL.size() + ">" + ":");
			for (ClassCourse cc : ccL) {
				ClassFU cls = DA.mClassCourse2Class.get(cc);
				System.out.print(" " + cc.code + "-" + cls.code);
			}
			System.out.println();
		}
		System.out.println("\nResult:");
		System.out.println("Total: " + DA.nbClassCourse);
		System.out.println("Number of remain: " + (DA.nbClassCourse - DA.mClassCourse2AssignedTeacher.size()));
		System.out.println("Number of assigned: " + DA.mClassCourse2AssignedTeacher.size());
	}

	public ClassCourse pickMostPromisingClassCourse() {

		return null;
	}

	public Teacher pickMostPromisingTeacher2(ClassCourse pickedCC) {
		Teacher pickedTeacher = null;

		Course c = DA.mClassCourse2Course.get(pickedCC);
		ArrayList<Teacher> tcList = DA.mCourse2TeacherList.get(c);
		if (!tcList.isEmpty()) {
			return null;
		}
		// find all candidates
		ArrayList<Teacher> pickableList = new ArrayList<Teacher>();
		for (Teacher t : tcList) {
			ArrayList<ClassCourse> assignedCC = DA.mTeacher2AssignedClassCourse.get(t);
			boolean pickable = true;
			if (!assignedCC.isEmpty()) {
				for (ClassCourse cc : assignedCC) {
					// check if candidate teacher can teach picked cc
					if (conflict(conflict, pickedCC, cc)) {
						pickable = false;
					}
				}
			}
			if (pickable == true) {
				pickableList.add(t);
			}
			//
		}
		if (pickableList.isEmpty()) { // ko chon dc giao vien nua
			return null;
		}

		// find all target remain
		int nbCandidate = pickableList.size();
		int[] target = new int[nbCandidate];
		int[] current = new int[nbCandidate];
		double[] remain = new double[nbCandidate];
		for (int i = 0; i < target.length; i++) {
			Teacher tc = pickableList.get(i);
			int tIdx = DA.mTeacher2Index.get(tc);
			target[i] = DA.lowerBoundnbClassCourseOfATeacher[tIdx];
			current[i] = DA.mTeacher2AssignedClassCourse.get(tc).size();
			remain[i] = target[i] - current[i];
		}

		// find all min/max

		// find normalize term of all min + target remain

		// normalize all

		// find the most promising one

		//

		return pickedTeacher;
	}

	public ArrayList<Double> findRelativeScoreList(ArrayList<Teacher> pickableTeacher, ClassCourse pickedCC) {
		ArrayList<Double> scoreList = new ArrayList<>();
		for (Teacher tc : pickableTeacher) {
			int tcListIdx = pickableTeacher.indexOf(tc);
			ArrayList<Double> relativeScoreList = calRelativeScore_TeacherCC(DA.mTeacher2AssignedClassCourse.get(tc),
					pickedCC);
			double score = 0;
			if (FAVOR_THEBESTCASE) {
				score = Collections.max(relativeScoreList); // tranh truong hop
															// xau nhat
			} else {
				score = Collections.min(relativeScoreList); // tranh truong hop
															// xau nhat
			}
			scoreList.add(tcListIdx, score);
		}

		return scoreList;
	}
	// --------------------------------------------------------------

	public Teacher pickMostPromisingTeacher(ClassCourse pickedCC) {

		// pick a teacher
		// input: assignedCC,pickCC,w1,w2,w3
		// output: Teacher pickedTeachers
		Teacher pickedTeacher = null;

		Course c = DA.mClassCourse2Course.get(pickedCC);
		ArrayList<Teacher> tcList = DA.mCourse2TeacherList.get(c);
		if (!tcList.isEmpty()) {
			ArrayList<Teacher> pickableList = new ArrayList<Teacher>();
			for (Teacher t : tcList) {
				ArrayList<ClassCourse> assignedCC = DA.mTeacher2AssignedClassCourse.get(t);
				boolean pickable = true;
				if (!assignedCC.isEmpty()) {
					for (ClassCourse cc : assignedCC) {
						// check if candidate teacher can teach picked cc
						if (conflict(conflict, pickedCC, cc)) {
							pickable = false;
						}
					}
				}
				if (pickable == true) {
					pickableList.add(t);
				}
				//
			}
			if (pickableList.isEmpty()) { // ko chon dc giao vien nua
				return null;
			}
			// int[] picked_Score = new int[pickableList.size()];
			ArrayList<Double> pickScore = new ArrayList<Double>();
			// end of finding feasible teacher

			// calculate score(pickCC,each ClassCourse cc : assignedCC)
			// calculate relative(t,pickCC)
			// calculate targetrmain(t);
			// cal pick score;
			/*
			 * for (int i = 0; i < picked_Score.length; i++) { picked_Score[i] =
			 * calpickScore(pickableList.get(i), pickedCC); }
			 */

			for (Teacher teacher : pickableList) {
				// double score = calpickScore(teacher, pickedCC);
				double score = calpickScore2(teacher, pickedCC);
				int at = pickableList.indexOf(teacher);
				pickScore.add(at, score);
			}
			// compare pickscore to pick
			double max = Collections.max(pickScore);

			// random teacher trong so dat max
			ArrayList<Teacher> maxList = new ArrayList<Teacher>();
			ArrayList<Integer> conflictScoreList = new ArrayList<Integer>();
			ArrayList<Integer> idxList = new ArrayList<Integer>();
			// HashMap<Teacher, Integer> mTeacher2PickIndex = new
			// HashMap<Teacher, Integer>();
			int idx = -1;
			for (Double double1 : pickScore) {
				idx++;
				if (double1 == max) {
					// mTeacher2PickIndex.put(pickableList.get(idx), idx);
					maxList.add(pickableList.get(idx));
					idxList.add(idx);
					int conflictScore = nbConflictClassCourse(pickableList.get(idx), pickedCC);
					conflictScoreList.add(conflictScore);
				}
			}
			/*
			 * Random r = new Random(); int ridx = r.nextInt(maxList.size()) +
			 * 0; int tcIdx = idxList.get(ridx);
			 */

			// int tcIdx = pickScore.indexOf(max);
			int minConflictScore = Collections.min(conflictScoreList);
			int minIdx = conflictScoreList.indexOf(minConflictScore);
			// int tcIdx = idxList.get(minIdx);

			// tim nhung giao vien co min conflict score trong maxList
			ArrayList<Teacher> bestminList = new ArrayList<>();
			ArrayList<Integer> bestminIdxList = new ArrayList<Integer>();
			int idx1 = -1;
			for (Integer min : conflictScoreList) {
				idx1++;
				if (min == minConflictScore) {
					bestminList.add(maxList.get(idx1));
					bestminIdxList.add(idxList.get(idx1));
				}
			}
			// random trong so best min conflict score
			Random r = new Random();
			int ridx = r.nextInt(bestminList.size()) + 0;
			int tcIdx = bestminIdxList.get(ridx);

			// picked
			pickedTeacher = pickableList.get(tcIdx);

			System.out.print("picked conflict score: " + conflictScoreList.get(minIdx));
			System.out.print(": candidates:");
			for (int i = 0; i < conflictScoreList.size(); i++) {
				System.out.print(" " + conflictScoreList.get(i));
			}
			System.out.println("\nRandom = " + ridx + ", size = " + bestminList.size());

			System.out.print("picked = " + pickedTeacher.code + ", candidates:");
			for (int t = 0; t < pickableList.size(); t++) {
				System.out.print(" <" + pickableList.get(t).code + "-" + pickScore.get(t) + ">");
			}
			System.out.println();
		} // end of if

		// test: pickCC = ..., <candidates,pick-score> = ..., pickedT = ...
		return pickedTeacher;
	}

	public int nbConflictClassCourse(Teacher tc, ClassCourse cc) {
		ArrayList<Course> cList = DA.mTeacher2CourseList.get(tc);
		ArrayList<ClassCourse> ccList = new ArrayList<ClassCourse>();
		for (Course c : cList) {
			ArrayList<ClassCourse> L = DA.mCourse2ClassCourseList.get(c);
			ccList.addAll(L);
		}

		int nbConflict = 0;

		/*
		 * for (ClassCourse cci : ccList) { if (conflict(conflict, cc, cci)) {
		 * nbConflict++; } }
		 */

		int ccIdx = DA.mClassCourse2Index.get(cc);
		for (ClassCourse cci : ccList) {
			int cciIdx = DA.mClassCourse2Index.get(cci);
			if (isAlreadyPicked[cciIdx] == false && conflict[ccIdx][cciIdx] == 1) {
				nbConflict++;
			}
		}

		return nbConflict;
	}

	public void testCalPickScore() {
		int cid = DA.mCourseCode2ID.get("PRF192");
		ArrayList<ClassCourse> ccList = DA.mCourse2ClassCourseList.get(DA.mID2Course.get(cid));
		ClassCourse pickedCC = null;
		ClassFU pcls = null;
		for (ClassCourse classCourse : ccList) {
			pcls = DA.mClassCourse2Class.get(classCourse);
			if (pcls.code.compareToIgnoreCase("SE1008") == 0) {
				pickedCC = classCourse;
				break;
			}
		}
		int tIdx = DA.mTeacherCode2ID.get("chilp");
		Teacher teacher = DA.teachers[tIdx];
		DA.mTeacher2AssignedClassCourse = new HashMap<Teacher, ArrayList<ClassCourse>>();
		DA.mTeacher2AssignedClassCourse.put(teacher, new ArrayList<ClassCourse>());
		/*
		 * for (int i = 0; i < 0; i++) {
		 * mTeacher2AssignedCC.get(teacher).add(DA.classCourses[i]); }
		 */
		double piScore = calpickScore(teacher, pickedCC);
		System.out.println(
				"tc = " + teacher.code + ", cc = " + pickedCC.code + "-" + pcls.code + ": pick score = " + piScore);
	}

	public double calpickScore(Teacher tc, ClassCourse pickedCC) {
		ArrayList<Double> relativeScoreList = calRelativeScore_TeacherCC(DA.mTeacher2AssignedClassCourse.get(tc),
				pickedCC);
		int tIdx = DA.mTeacher2Index.get(tc);
		int target = DA.lowerBoundnbClassCourseOfATeacher[tIdx];
		if (relativeScoreList.isEmpty()) { // teacher chua duoc assign
			// System.out.println("chua day mon nao ca, nbc =
			// "+mTeacher2AssignedCC.get(tc).size());
			// return MAX_PICKSCORE;
			return  target; // co 2 nguoi chua dc gan thi
													// nguoi nao can nhieu hon
													// dc gan truoc
		} else {
			double current = DA.mTeacher2AssignedClassCourse.get(tc).size();
			double remain = target - current;
			double relaScore = 0;
			if (FAVOR_THEBESTCASE) {
				relaScore = Collections.max(relativeScoreList); // tranh truong
																// hop xau nhat
			} else {
				relaScore = Collections.min(relativeScoreList); // tranh truong
																// hop xau nhat
			}

			/*
			 * System.out.println("current = "+current); System.out.println(
			 * "remain = "+TARGETREMAIN_WEIGHT*remain); System.out.println(
			 * "minscore = "+RELATIVE_WEIGHT*minscore); System.out.println(
			 * "pick score = "+(TARGETREMAIN_WEIGHT*remain +
			 * RELATIVE_WEIGHT*minscore));
			 */
			if (remain == 0 && relaScore == 0) {
				return 0;
			}
			double S = Math.sqrt(remain * remain + relaScore * relaScore);
			return (double) remain / S +  (double) relaScore / S;
		}
	}

	public double calpickScore2(Teacher tc, ClassCourse pickedCC) {
		ArrayList<Double> relativeScoreList = calRelativeScore_TeacherCC(DA.mTeacher2AssignedClassCourse.get(tc),
				pickedCC);
		int tIdx = DA.mTeacher2Index.get(tc);
		int target = DA.lowerBoundnbClassCourseOfATeacher[tIdx];
		if (relativeScoreList.isEmpty()) { // teacher chua duoc assign
			// System.out.println("chua day mon nao ca, nbc =
			// "+mTeacher2AssignedCC.get(tc).size());
			// return MAX_PICKSCORE;
			return target; // co 2 nguoi chua dc gan thi
													// nguoi nao can nhieu hon
													// dc gan truoc
		} else {
			double current = DA.mTeacher2AssignedClassCourse.get(tc).size();
			double remain = target - current;
			double relaScore = 0;
			if (FAVOR_THEBESTCASE) {
				relaScore = Collections.max(relativeScoreList); // tranh truong
																// hop xau nhat
			} else {
				relaScore = Collections.min(relativeScoreList); // tranh truong
																// hop xau nhat
			}

			/*
			 * System.out.println("current = "+current); System.out.println(
			 * "remain = "+TARGETREMAIN_WEIGHT*remain); System.out.println(
			 * "minscore = "+RELATIVE_WEIGHT*minscore); System.out.println(
			 * "pick score = "+(TARGETREMAIN_WEIGHT*remain +
			 * RELATIVE_WEIGHT*minscore));
			 */
			if (remain == 0 && relaScore == 0) {
				return 0;
			}
			double S = Math.sqrt(remain * remain + relaScore * relaScore);
			// return WEIGHT_TARGETREMAIN*(double)remain/S +
			// WEIGHT_RELATIVE*(double)relaScore/S;
			return  (double) remain + (double) relaScore;
		}
	}

	/**
	 * tinh relative xem candidate teacher nay co phu hop vs mon picked
	 * class-course ko output: int score(tc,cc);
	 */
	public void testRelativeScore() {
		int cid = DA.mCourseCode2ID.get("PRF192");
		ArrayList<ClassCourse> ccList = DA.mCourse2ClassCourseList.get(DA.mID2Course.get(cid));
		ClassCourse pickedCC = null;
		for (ClassCourse classCourse : ccList) {
			ClassFU pcls = DA.mClassCourse2Class.get(classCourse);
			if (pcls.code.compareToIgnoreCase("SE1011") == 0) {
				pickedCC = classCourse;
				break;
			}
		}

		// pickedCC = DA.classCourses[144];
		ClassFU cls = DA.mClassCourse2Class.get(pickedCC);
		ArrayList<ClassCourse> ccL = new ArrayList<ClassCourse>();
		for (int i = 0; i < 5; i++) {
			ccL.add(DA.classCourses[i]);
		}
		ArrayList<Double> iL = new ArrayList<Double>();
		iL = calRelativeScore_TeacherCC(ccL, pickedCC);
		for (ClassCourse cc : ccL) {
			ClassFU clsi = DA.mClassCourse2Class.get(cc);
			System.out.println("<" + cls.code + "-" + clsi.code + "," + pickedCC.code + "-" + cc.code + ">: score = "
					+ iL.get(ccL.indexOf(cc)));
		}
	}

	public ArrayList<Double> calRelativeScore_TeacherCC(ArrayList<ClassCourse> assignedCC, ClassCourse pickedCC) {
		ArrayList<Double> scoreList = new ArrayList<>();
		for (ClassCourse cc : assignedCC) {
			int idx = assignedCC.indexOf(cc);
			int ccIdx = DA.mClassCourse2Index.get(pickedCC);
			int cciIdx = DA.mClassCourse2Index.get(cc);
			double score = scoreMatrix[ccIdx][cciIdx];
			scoreList.add(idx, score);
		}

		return scoreList;
	}

	/** Normalize matrix score */
	public void normalizeMatrixScore() {
		HashSet<Double> set = new HashSet<>();
		double min = Double.MAX_VALUE;
		for (int i = 0; i < DA.nbClassCourse - 1; i++) {
			for (int j = i + 1; j < DA.nbClassCourse; j++) {
				if (scoreMatrix[i][j] < min) {
					min = scoreMatrix[i][j];
				}
			}
		}

		min = Math.abs(min);
		double S = 0;
		double max = Double.MIN_VALUE;
		for (int i = 0; i < DA.nbClassCourse - 1; i++) {
			for (int j = i + 1; j < DA.nbClassCourse; j++) {
				scoreMatrix[i][j] += min;
				scoreMatrix[j][i] += min;
				if (scoreMatrix[i][j] > max) {
					max = scoreMatrix[i][j];
				}
				/*
				 * if (set.add(scoreMatrix[i][j])) { S +=
				 * scoreMatrix[i][j]*scoreMatrix[i][j]; }
				 */
			}
		}
		S = Math.sqrt(S);
		for (int i = 0; i < DA.nbClassCourse; i++) {
			for (int j = 0; j < DA.nbClassCourse; j++) {
				scoreMatrix[i][j] /= max;
				scoreMatrix[i][j] *= SCORERANGE;
				scoreMatrix[i][j] -= SCORERANGE / 2;
			}
		}

		/*
		 * System.out.println("Score matrix after normalization:"); for (int i =
		 * 0; i < DA.nbClassCourse; i++) { for (int j = 0; j < DA.nbClassCourse;
		 * j++) { System.out.print(" "+scoreMatrix[i][j]); }
		 * System.out.println(); }
		 */
	}

	public void writeScoreMatrix2File(String fn) {
		try {
			File file = new File(fn);

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			for (int i = 0; i < DA.nbClassCourse; i++) {
				for (int j = 0; j < DA.nbClassCourse; j++) {
					bw.write(scoreMatrix[i][j] + " ");
				}
				bw.write("\n");
			}

			bw.close();

			System.out.println("Done writing score matrix to file!");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * calculate score between 2 class-courses basing on day, block, session
	 * output: scoreMatrix = new int[DA.nbClassCourse][DA.nbClassCourse];
	 */
	public void build_MatrixScore() {
		scoreMatrix = new double[DA.nbClassCourse][DA.nbClassCourse];

		for (int cc1 = 0; cc1 < DA.nbClassCourse - 1; cc1++) {
			for (int cc2 = cc1 + 1; cc2 < DA.nbClassCourse; cc2++) {
				ClassCourse clc1 = DA.classCourses[cc1];
				ClassCourse clc2 = DA.classCourses[cc2];

				int b1 = DA.aClassCourse2Block[cc1];
				int b2 = DA.aClassCourse2Block[cc2];
				int ss1 = DA.aClassCourse2Session[cc1];
				int ss2 = DA.aClassCourse2Session[cc2];
				int d1 = DA.aClassCourse2Day[cc1];
				int d2 = DA.aClassCourse2Day[cc2];
				int n1 = DA.aClassCourse2Noon[cc1];
				int n2 = DA.aClassCourse2Noon[cc2];
				int be1 = DA.aClassCourse2BeginEndDay[cc1];
				int be2 = DA.aClassCourse2BeginEndDay[cc2];

				int sameblock = 0;
				if (clc1.stt == 3 || clc2.stt == 3) {
					sameblock = 1;
				} else if ((b1 + b2) % 2 == 0) {
					sameblock = 1;
				}

				int sameday = 0;
				int notSameDay = 0;
				// if (sameblock == 1) {
				if (clc1.stt == 3 || clc2.stt == 3) {
					sameday = 0;
					notSameDay = 1;
				} else if ((d1 + d2) % 2 == 0) {
					sameday = 1;
				} else {
					notSameDay = 1;
				}
				// }

				int samesession = 0;
				if ((ss1 + ss2) % 2 == 0) {
					samesession = 1;
				}
				int samenoon = n1 * n2;

				int sameBeginEndDay = be1 * be2;

				scoreMatrix[cc1][cc2] = // WEIGHT_BLOCK*sameblock
										// + WEIGHT_SESSION*samesession*sameday
				+WEIGHT_DAY * sameday * sameblock - WEIGHT_DAY * notSameDay
				// +samesession+sameblock
				// + WEIGHT_NOON*samenoon*sameday*sameblock// +
				// WEIGHT_BEGINENDDAY*sameBeginEndDay*sameday*sameblock;//+samesession+sameblock;
						+ 0;
				scoreMatrix[cc2][cc1] = scoreMatrix[cc1][cc2];
			}
		}

		for (int cc1 = 0; cc1 < DA.nbClassCourse - 1; cc1++) {
			for (int cc2 = cc1 + 1; cc2 < DA.nbClassCourse; cc2++) {
				ClassCourse clc1 = DA.classCourses[cc1];
				ClassCourse clc2 = DA.classCourses[cc2];
				ClassFU cls1 = DA.mClassCourse2Class.get(clc1);
				ClassFU cls2 = DA.mClassCourse2Class.get(clc2);

				int b1 = DA.aClassCourse2Block[cc1];
				int b2 = DA.aClassCourse2Block[cc2];
				int ss1 = DA.aClassCourse2Session[cc1];
				int ss2 = DA.aClassCourse2Session[cc2];
				int d1 = DA.aClassCourse2Day[cc1];
				int d2 = DA.aClassCourse2Day[cc2];
				int n1 = DA.aClassCourse2Noon[cc1];
				int n2 = DA.aClassCourse2Noon[cc2];

				int sameblock = 0;
				if (clc1.stt == 3 || clc2.stt == 3) {
					sameblock = 1;
				} else if ((b1 + b2) % 2 == 0) {
					sameblock = 1;
				}

				int sameday = 0;
				// if (sameblock == 1) {
				if ((d1 + d2) % 2 == 0) {
					sameday = 1;
				}
				// }

				int samesession = 0;
				if ((ss1 + ss2) % 2 == 0) {
					samesession = 1;
				}
				int samenoon = n1 * n2;

				/*
				 * System.out.print("<"+clc1.code+"-"+clc2.code+","+cls1.code+
				 * "-"+cls2.code+">: "); System.out.println("score = "
				 * +scoreMatrix[cc1][cc2] +" OR = "+(BLOCK_WEIGHT*sameblock +
				 * SESSION_WEIGHT*samesession + DAY_WEIGHT*sameday +
				 * NOON_WEIGHT*samesession)); System.out.println("b1 = "+b1+
				 * ", s1 = "+ss1+", d1 = "+d1+", n1 = "+n1);//score =
				 * "+scoreMatrix[cc1][cc2]); System.out.println("b2 = "+b2+
				 * ", s2 = "+ss2+", d2 = "+d2+", n2 = "+n2);//score =
				 * "+scoreMatrix[cc1][cc2]); System.out.println("sday = "
				 * +sameday+", sblk = "+sameblock+", sss = "+samesession+
				 * ", snoon = "+samenoon);//score = "+scoreMatrix[cc1][cc2]);
				 * System.out.println();
				 */
			}
		}

	}

	/** find some data (day, block, session) to calculate scoress */
	public void findScoreElement(SingleSolution[] sol) {
		// tìm block, session, ngày chẵn/lẽ của cc
		DA.aClassCourse2Block = new int[DA.nbClassCourse];
		DA.aClassCourse2Day = new int[DA.nbClassCourse];
		DA.aClassCourse2Session = new int[DA.nbClassCourse];
		DA.aClassCourse2Noon = new int[DA.nbClassCourse];
		DA.aClassCourse2BeginEndDay = new int[DA.nbClassCourse];

		daySet = new TreeSet[DA.nbClassCourse];
		boolean[] markNoon = new boolean[DA.nbClassCourse];
		boolean[] markBeginEndDay = new boolean[DA.nbClassCourse];
		for (int i = 0; i < DA.nbClassCourse; i++) {
			daySet[i] = new TreeSet<Integer>();
		}

		for (int cl = 0; cl < DA.nbClass; cl++) {
			ClassFU cls = DA.classes[cl];
			ArrayList<ClassCourse> L = DA.mClass2ClassCourseList.get(cls);
			// tìm set day cho các class-course:
			for (int d = 0; d < DA.nbDays_20; d++) {
				for (int sl = 0; sl < DA.nbSlotsPerHalfDay * 2; sl++) {
					if (sol[cl].T[sl][d] >= 0) {
						for (ClassCourse cc : L) {
							ClassFU clas = DA.mClassCourse2Class.get(cc);
							if (sol[cl].T[sl][d] == cc.ID) {
								int ccIdx = DA.mClassCourse2Index.get(cc);
								daySet[ccIdx].add(d);
								if (markNoon[ccIdx] == false) {
									if ((sl == TimeTableAllClass.SLOT_3 || sl == TimeTableAllClass.SLOT_4)) {
										DA.aClassCourse2Noon[ccIdx] = 1;
										markNoon[ccIdx] = true;
										System.out.println(ccIdx + ", cc = " + cc.code + "-" + clas.code + ": noon = "
												+ DA.aClassCourse2Noon[ccIdx] + ", slot = " + (sl + 1));
									}
								}
								if (markBeginEndDay[ccIdx] == false) {
									if (sl == TimeTableAllClass.SLOT_1 || sl == TimeTableAllClass.SLOT_6) {
										DA.aClassCourse2BeginEndDay[ccIdx] = 1;
									} else {
										DA.aClassCourse2BeginEndDay[ccIdx] = 0;
									}
									markBeginEndDay[ccIdx] = true;
									System.out
											.println(ccIdx + ", cc = " + cc.code + "-" + clas.code + ": BeginEndDay = "
													+ DA.aClassCourse2BeginEndDay[ccIdx] + ", slot = " + (sl + 1));
								}
							}
						}
					}
				}
			}
			// tìm session + noon cho từng class-course.
			// tim block, ngày chẵn/lẻ cho tung class-course:
			for (int d = 0; d < DA.nbDays_20; d++) {
				for (int sl = 0; sl < DA.nbSlotsPerHalfDay * 2; sl++) {
					if (sol[cl].T[sl][d] >= 0) {
						for (ClassCourse cc : L) {
							if (sol[cl].T[sl][d] == cc.ID) {
								int ccIdx = DA.mClassCourse2Index.get(cc);
								if (sl > 2) {
									DA.aClassCourse2Session[ccIdx] = 1;
								} else {
									DA.aClassCourse2Session[ccIdx] = 0;
								}
								switch (d) {
								case 0:
									DA.aClassCourse2Day[ccIdx] = 0;
									if (cc.stt == 3) {
										DA.aClassCourse2Block[ccIdx] = 1;
									} else {
										DA.aClassCourse2Block[ccIdx] = 0;
									}

									break;
								case 1:
									DA.aClassCourse2Day[ccIdx] = 1;
									if (cc.stt == 3) {
										DA.aClassCourse2Block[ccIdx] = 1;
									} else {
										DA.aClassCourse2Block[ccIdx] = 0;
									}
									break;
								case 10:
									DA.aClassCourse2Day[ccIdx] = 0;
									DA.aClassCourse2Block[ccIdx] = 1;
									break;
								case 11:
									DA.aClassCourse2Day[ccIdx] = 1;
									DA.aClassCourse2Block[ccIdx] = 1;
									break;
								}
								// chi lay ngay dau tien
								break;
							}
						}
					}
				}
			}

		}

		for (int cc = 0; cc < DA.nbClassCourse; cc++) {
			// Collections.sort(daySet[cc]);
			ClassCourse clc = DA.classCourses[cc];
			ClassFU cls = DA.mClassCourse2Class.get(clc);
			System.out.print("cc = " + cc + ", " + clc.code + "-" + cls.code + ", stt = " + clc.stt + ", dayset: ");
			// System.out.print(" "+daySet[cc].first());
			for (Integer i : daySet[cc]) {
				System.out.print(" " + i);
			}
			System.out.print(":    block = " + (DA.aClassCourse2Block[cc] + 1));
			if (DA.aClassCourse2Day[cc] == 0) {
				System.out.print(", chan");
			} else {
				System.out.print(", le  ");
			}
			if (DA.aClassCourse2Noon[cc] == 0) {
				System.out.print(", not noon");
			} else {
				System.out.print(", 3 or 4  ");
			}
			if (DA.aClassCourse2Session[cc] == 0) {
				System.out.print(", sang ");
			} else {
				System.out.print(", chieu");
			}
			if (DA.aClassCourse2BeginEndDay[cc] == 0) {
				System.out.print(", giua ngay");
			} else {
				System.out.print(", dau+cuoi ");
			}
			System.out.println();
		}
	}

	public boolean conflict(int[][] conflict, ClassCourse cc1, ClassCourse cc2) {
		int cc1Idx = DA.mClassCourse2Index.get(cc1);
		int cc2Idx = DA.mClassCourse2Index.get(cc2);
		if (conflict[cc1Idx][cc2Idx] == 1 || conflict[cc2Idx][cc1Idx] == 1) {
			return true;
		}
		return false;
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

	public void test() {
		String fn_conflictMatrix = "data_conflictMatrix.txt";
		conflict = loadConflictMatrix(fn_conflictMatrix);

	}
	///////////////////////////////////////////////////////////////////

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AssignTeacher a = new AssignTeacher();
		a.findSol();
		String fn_data_course_class = "data_course_class_merged.txt";
		String fn_data_teacher = "data_teacher.txt";
		String fn_conflictMatrix = "data_conflictMatrix.txt";
		String fn_courseScoreMatrix = "data_scoreMatrix.txt";
		String fn_beingUsedTimeTable_Template2 = "data_beingusedTT_Temp2.dat";
		String fn_beingUsedTimeTable_Template1 = "data_beingusedTT_Temp1.dat";
		String fn_allTeacherTimeTable = "timetableAllTeacher.html";

		a.DA = new DataCenter();
		a.DA.loadData_Course_Class(fn_data_course_class);
		a.DA.loadData_Teacher_UsingCode(fn_data_teacher);
		a.assignTeacherUsingScore(fn_conflictMatrix, fn_beingUsedTimeTable_Template2, fn_courseScoreMatrix,
				a.DA.classCourses);
		SingleSolution[] sol = a.loadbeingUsedTimeTable(fn_beingUsedTimeTable_Template2);
		a.printAllTeacherHTML(fn_allTeacherTimeTable, sol);

	}// end of main

}
