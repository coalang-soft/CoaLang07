package diamond.run.environment;

import diamond.run.core.model.Array;
import diamond.run.core.model.Function;
import diamond.run.core.model.Value;

public class ArrayAtFunction implements Function {

	private Array array;

	public ArrayAtFunction(Array array) {
		this.array = array;
	}

	@Override
	public Value take(Value v) {
		return array.get((int) v.castNumber());
	}

	@Override
	public Value callZeroArg() {
		return this;
	}

}
