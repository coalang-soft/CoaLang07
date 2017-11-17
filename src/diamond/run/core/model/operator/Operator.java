package diamond.run.core.model.operator;

import diamond.run.core.model.Function;
import diamond.run.core.model.Value;

public interface Operator extends Function {
	
	default Value take(Value a){
		return new Function(){
			@Override
			public Value take(Value b) {
				return operate(a,b);
			}

			@Override
			public Value callZeroArg() {
				return this;
			}
		};
	}

	Value operate(Value a, Value b);
	
	default Value callZeroArg() {
		return this;
	}
	
}
