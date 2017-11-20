package diamond.run.environment;

import diamond.run.core.model.Array;
import diamond.run.core.model.SingleFunction;
import diamond.run.core.model.Value;

public class ArrayAtFunction implements SingleFunction {

	private Array array;

	public ArrayAtFunction(Array array) {
		this.array = array;
	}

	@Override
	public Value callZeroArg() {
		return this;
	}

	@Override
	public Value takeSingle(Value a) {
		return array.get((int) a.castNumber());
	}

}
