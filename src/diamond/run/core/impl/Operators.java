package diamond.run.core.impl;

import diamond.run.core.model.operator.ArrayOperator;
import diamond.run.core.model.operator.NumberOperator;

public interface Operators {
	
	NumberOperator ADD = (a,b) -> a + b;
	NumberOperator SUB = (a,b) -> a - b;
	NumberOperator MUL = (a,b) -> a * b;
	NumberOperator DIV = (a,b) -> a / b;
	NumberOperator POW = Math::pow;
	
	ArrayOperator ARR_CONCAT = Arrays::concat;
	
}
