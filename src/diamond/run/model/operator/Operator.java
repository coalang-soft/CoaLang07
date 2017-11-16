package diamond.run.model.operator;

import diamond.run.model.Function;
import diamond.run.model.Value;

public interface Operator extends Function {
	
	default Value take(Value a){
		return new Function(){
			@Override
			public Value take(Value b) {
				return operate(a,b);
			}
		};
	}
	
	Value operate(Value a, Value b);
	
}
