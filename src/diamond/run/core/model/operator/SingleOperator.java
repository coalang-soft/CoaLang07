package diamond.run.core.model.operator;

import diamond.run.core.model.Array;
import diamond.run.core.model.SingleFunction;
import diamond.run.core.model.Type;
import diamond.run.core.model.Value;
import diamond.run.environment.Scope;

public interface SingleOperator extends SingleFunction {
	
	default Value takeSingle(Scope s, Value a){
		return new SingleFunction(){
			@Override
			public Value takeSingle(Scope s, Value b) {
				return operate(s,a,b);
			}
		};
	}
	
	default Value operate(Scope s, Value a, Value b){
		if(a.getType() == Type.ARRAY || b.getType() == Type.ARRAY){
			return operateArray(s,a.castArray(),b.castArray());
		}
		return operateSingle(s,a,b);
	}
	
	default Value operateArray(Scope s, Array a, Array b) {
		return a.take(s,this).take(s,b);
	}

	Value operateSingle(Scope s, Value a, Value b);
	
	default Value callZeroArg() {
		return this;
	}
	
}
