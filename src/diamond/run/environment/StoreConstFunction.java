package diamond.run.environment;

import diamond.run.core.model.Value;
import diamond.run.core.model.operator.SingleOperator;

public class StoreConstFunction implements SingleOperator {
	
	@Override
	public Value operateSingle(Scope scope, Value a, Value b) {
		scope.put(b.toString(), a);
		return a;
	}

}
