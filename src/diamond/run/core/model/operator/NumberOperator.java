package diamond.run.core.model.operator;

import diamond.run.core.impl.DefaultSingleImpl;
import diamond.run.core.model.Value;

public interface NumberOperator extends Operator {
	
	default Value operate(Value a, Value b){
		return new DefaultSingleImpl(numberOperate(a.castNumber(), b.castNumber()));
	}
	
	double numberOperate(double a, double b);
	
}
