package diamond.run.core.model.operator;

import diamond.run.core.model.Array;
import diamond.run.core.model.Value;

public interface ArrayOperator extends Operator {
	
	default Value operate(Value a, Value b){
		return arrayOperate(a.castArray(), b.castArray());
	}

	Value arrayOperate(Array a, Array b);
	
}