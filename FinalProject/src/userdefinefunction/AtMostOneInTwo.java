package userdefinefunction;

import localsearch.model.AbstractInvariant;
import localsearch.model.ConstraintSystem;
import localsearch.model.IConstraint;
import localsearch.model.LocalSearchManager;
import localsearch.model.VarIntLS;

public class AtMostOneInTwo extends AbstractInvariant implements IConstraint {
	private LocalSearchManager _ls;
	private VarIntLS _x1;
	private VarIntLS _x2;
	private int _n;
	private int _violations;
	
	public AtMostOneInTwo(VarIntLS x, VarIntLS y, int n) {
		// nhieu nhat 1 trong 2 bien x, y co gia tri n
		this._ls = x.getLocalSearchManager();
		_x1 = x;
		_x2 = y;
		_n = n;
		post();
	}
	
	private void post(){
		_ls.post(this);
	}
	@Override
	public LocalSearchManager getLocalSearchManager() {
		// TODO Auto-generated method stub
		return _ls;
	}

	@Override
	public VarIntLS[] getVariables() {
		// TODO Auto-generated method stub
		VarIntLS[] X = new VarIntLS[2];
		X[0] = _x1;
		X[1] = _x2;
		return X;
	}

	@Override
	public void initPropagate() {
		// TODO Auto-generated method stub
		if (_x1.getValue() == _n && _x2.getValue() == _n) {
			_violations = 1;
		} else {
			_violations = 0;
		}
	}

	@Override
	public void propagateInt(VarIntLS x, int val) {
		// TODO Auto-generated method stub
		if (x == _x1 || x == _x2) {
			if (_x1.getValue() == _n && _x2.getValue() == _n) {
				_violations = 1;
			} else {
				_violations = 0;
			}
		}
	}

	@Override
	public boolean verify() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getAssignDelta(VarIntLS x, int val) {
		// TODO Auto-generated method stub
		if (x != _x1 && x != _x2) {
			return 0;
		}else if (_x1 == _x2){
			if (val == _n) {
				return 1 -_violations;
			} else {
				return 0 -_violations;
			}
		}else{
			if (x == _x1) {
				if (val == _n && _x2.getValue() == _n) {
					return 1-_violations;
				} else {
					return 0-_violations;
				}
			} else {
				if (val == _n && _x1.getValue() == _n) {
					return 1-_violations;
				} else {
					return 0-_violations;
				}
			}
		}
	}

	@Override
	public int getSwapDelta(VarIntLS x, VarIntLS y) {
		// TODO Auto-generated method stub
		if (x != _x1 && x != _x2 && y != _x1 && y != _x2) {
			return 0;
		} 
		if(x == y){
			return 0;
		}else{
			if ((x==_x1 && y == _x2) || (x==_x2 && y == _x1)) {
				return 0;
			} 
			if((x==_x1 && y != _x2) || (x==_x2 && y != _x1)){
				return getAssignDelta(x, y.getValue());
			}
			if((y==_x1 && x != _x2) || (y==_x2 && x != _x1)){
				return getAssignDelta(y, x.getValue());
			}
		}
		return 0;
	}

	@Override
	public int violations() {
		// TODO Auto-generated method stub
		return _violations;
	}

	@Override
	public int violations(VarIntLS x) {
		// TODO Auto-generated method stub
		if (x == _x1 || x == _x2) {
			return _violations;
		}
		return 0;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LocalSearchManager ls = new LocalSearchManager();
		VarIntLS x1 = new VarIntLS(ls, 0, 1);
		VarIntLS x2 = new VarIntLS(ls, 0, 1);
		VarIntLS x = new VarIntLS(ls, 0, 1);
		
		
		ConstraintSystem S = new ConstraintSystem(ls);
		S.post(new AtMostOneInTwo(x1,x2,1));
		ls.close();
		x1.setValuePropagate(1);
		x2.setValuePropagate(1);
		x.setValuePropagate(0);
		System.out.println(S.violations());
		
		
		System.out.println(S.getSwapDelta(x1, x));
	}

}
