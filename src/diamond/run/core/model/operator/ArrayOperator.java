package diamond.run.core.model.operator;

import diamond.run.core.model.Array;
import diamond.run.core.model.ArrayFunction;
import diamond.run.core.model.Value;
import diamond.run.environment.Scope;

public interface ArrayOperator extends ArrayFunction {

	default Value takeArray(Scope s, Array a){
		return new ArrayFunction() {
			@Override
			public Value takeArray(Scope s, Array b) {
				return arrayOperate(s,a,b);
			}
		};
	}
	
	Value arrayOperate(Scope s, Array a, Array b);
	
}