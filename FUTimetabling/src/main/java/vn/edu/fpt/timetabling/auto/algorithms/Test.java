package vn.edu.fpt.timetabling.auto.algorithms;

import localsearch.constraints.basic.LessOrEqual;
import localsearch.model.ConstraintSystem;
import localsearch.model.LocalSearchManager;
import localsearch.model.VarIntLS;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*Random r = new Random();
		int ri = r.nextInt(3);
//		System.out.println(ri);
		
		HashMap<Course, Integer> m = new HashMap<>();
		Course a = new Course(0, "A");
		Course b = new Course(0, "A");
		m.put(a, new Integer(12));
		m.put(a, new Integer(13));
		
		System.out.println(m.get(a));*/
		LocalSearchManager ls = new LocalSearchManager();
		VarIntLS x;
		x = new VarIntLS(ls, 0, 6);
		ConstraintSystem S = new ConstraintSystem(ls);
		S.post(new LessOrEqual(x, 4));
		ls.close();
		System.out.println(S.violations());
		x.setValuePropagate(3);
		
		System.out.println(x.getValue());
//		System.out.println(S.getAssignDelta(x, 5));
		x.setValuePropagate(5);
		System.out.println(x.getValue());
		System.out.println(S.violations(x));
		if (S.violations(x) > 0) {
			x.setValuePropagate(x.getOldValue());
		}
		System.out.println(x.getValue());
		System.out.println(S.violations(x));
	}

}



