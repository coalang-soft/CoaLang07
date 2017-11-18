package diamond.run.core.impl;

import diamond.run.core.model.operator.NumberOperator;
import diamond.run.core.model.operator.SingleOperator;

public interface Operators {
	
	NumberOperator ADD = (a,b) -> a + b;
	NumberOperator SUB = (a,b) -> a - b;
	NumberOperator MUL = (a,b) -> a * b;
	NumberOperator DIV = (a,b) -> a / b;
	NumberOperator POW = Math::pow;
	NumberOperator MOD = (a,b) -> a % b;
	
	NumberOperator LSS = (a,b) -> a<b?1:0;
	NumberOperator GTR = (a,b) -> a>b?1:0;
	SingleOperator EQU = (a,b) -> new DefaultSingleImpl(a.get().equals(b.get())?1:0);
	
}
