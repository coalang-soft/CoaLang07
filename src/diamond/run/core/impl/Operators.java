package diamond.run.core.impl;

import diamond.run.core.model.operator.ArrayOperator;
import diamond.run.core.model.operator.NumberOperator;
import diamond.run.core.model.operator.SingleOperator;

public interface Operators {
	
	NumberOperator ADD = (s,a,b) -> a + b;
	NumberOperator SUB = (s,a,b) -> a - b;
	NumberOperator MUL = (s,a,b) -> a * b;
	NumberOperator DIV = (s,a,b) -> a / b;
	NumberOperator POW = (s,a,b) -> Math.pow(a,b);
	NumberOperator MOD = (s,a,b) -> a % b;
	
	NumberOperator LSS = (s,a,b) -> a<b?1:0;
	NumberOperator GTR = (s,a,b) -> a>b?1:0;
	SingleOperator EQU = (s,a,b) -> {
		Object av = a.get();
		Object bv = b.get();
		if(av instanceof Number && bv instanceof Number){
			return new DefaultSingleImpl(a.castNumber() == b.castNumber()?1:0);
		}
		return new DefaultSingleImpl(av.equals(bv)?1:0);
	};
	
	ArrayOperator ARR_CONCAT = (s,a,b) -> Arrays.concat(a,b);
	
}
