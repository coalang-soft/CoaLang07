package diamond.run.core.impl.dynamic;

import diamond.run.core.impl.DefaultSingleImpl;
import diamond.run.core.model.Value;
import diamond.run.environment.Scope;

public class FunctionBasedArray extends DynamicArray {

	private Value func;
	private Scope scope;
	
	public FunctionBasedArray(Scope s, int length, Value func) {
		super(length);
		this.func = func;
		this.scope = s;
	}

	@Override
	public Value get0(int index) {
		return func.take(scope, new DefaultSingleImpl(index));
	}

}
