package diamond.run.core.model.operator;

import diamond.run.core.model.Array;
import diamond.run.core.model.ArrayFunction;
import diamond.run.core.model.Value;

public interface ArrayOperator extends ArrayFunction {
	
	default Value operate(Value a, Value b){
		return arrayOperate(a.castArray(), b.castArray());
	}

	default Value takeArray(Array a){
		return new ArrayFunction() {
			@Override
			public Value takeArray(Array b) {
				return arrayOperate(a,b);
			}
		};
	}
	
	Value arrayOperate(Array a, Array b);
	
}