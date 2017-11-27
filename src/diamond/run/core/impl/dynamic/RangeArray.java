package diamond.run.core.impl.dynamic;

import diamond.run.core.impl.DefaultSingleImpl;
import diamond.run.core.model.Value;

public class RangeArray extends DynamicArray {

	public RangeArray(int length) {
		super(length);
	}

	@Override
	public Value get0(int index) {
		return new DefaultSingleImpl(index);
	}

}