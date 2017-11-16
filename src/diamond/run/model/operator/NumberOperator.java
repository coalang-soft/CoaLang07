package diamond.run.model.operator;

import diamond.run.impl.DefaultSingleImpl;
import diamond.run.model.Value;

public interface NumberOperator extends Operator {
	
	default Value operate(Value a, Value b){
		return new DefaultSingleImpl(numberOperate(((Number) a.get()).doubleValue(), ((Number) b.get()).doubleValue()));
	}
	
	double numberOperate(double a, double b);
	
}
