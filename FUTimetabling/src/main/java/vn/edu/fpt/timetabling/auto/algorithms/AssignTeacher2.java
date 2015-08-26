package vn.edu.fpt.timetabling.auto.algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

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

public class AssignTeacher2 {
	DataCenter DA;
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
	public int[][] teacherSolution;

	public int[] aClassCourse2Session;
	public int[] aClassCourse2Day;
	public int[] aClassCourse2Block;
	public int[] aClassCourse2Noon;
	public static final int LIMIT_NBSEARCHITERATION_LS = 500;
	public static final int LIMIT_NBSEARCHITERATION_OLS = 300;

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
		// VarIntLS[] temp = new VarIntLS[2];
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

	@SuppressWarnings("unchecked")
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
		// VarIntLS[] tt = y_LS[0];

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
		ts.search(S, 50, 3000, LIMIT_NBSEARCHITERATION_LS, 30);

	}

	public void search_OLS() {
		localsearch.search.TabuSearch ts = new localsearch.search.TabuSearch();
		ts.searchMaintainConstraints(objfunc, S, 100, 3000, LIMIT_NBSEARCHITERATION_OLS, 30);
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

	// //////////////////////////////////////////////////
	@SuppressWarnings("unchecked")
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

	public void assignTeacherUsingLS(String fn_conflictMatrix) {
		// DA = new DataCenter();
		// DA.loadData_Course_Class("data_all_sm_merged.txt");
		// DA.loadData_Teacher_UsingCode("teacherDataCode.txt"); //data da thay
		// doi
		conflict = loadConflictMatrix(fn_conflictMatrix);

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

	///////////////////////////////////////////////////////////////////

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AssignTeacher2 a = new AssignTeacher2();
		a.assignTeacherUsingLS("");

	}// end of main

}
