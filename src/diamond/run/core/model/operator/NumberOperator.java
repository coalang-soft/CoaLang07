package diamond.run.core.model.operator;

import diamond.run.core.impl.DefaultSingleImpl;
import diamond.run.core.model.Value;
import diamond.run.environment.Scope;

public interface NumberOperator extends SingleOperator {
	
	default Value operateSingle(Scope s, Value a, Value b){
		
		return new DefaultSingleImpl(numberOperate(s, a.castNumber(), b.castNumber()));
	}
	
	double numberOperate(Scope s, double a, double b);
	
}
