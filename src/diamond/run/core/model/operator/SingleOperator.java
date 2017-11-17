package diamond.run.core.model.operator;

import diamond.run.core.model.Array;
import diamond.run.core.model.Type;
import diamond.run.core.model.Value;

public interface SingleOperator extends Operator {
	
	default Value operate(Value a, Value b){
		if(a.getType() == Type.ARRAY || b.getType() == Type.ARRAY){
			return operateArray(a.castArray(),b.castArray());
		}
		return operateSingle(a,b);
	}
	
	default Value operateArray(Array a, Array b) {
		return a.take(this).take(b);
	}

	Value operateSingle(Value a, Value b);
	
	default Value callZeroArg() {
		return this;
	}
	
}
