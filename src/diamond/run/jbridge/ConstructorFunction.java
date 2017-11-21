package diamond.run.jbridge;

import diamond.run.core.model.SingleFunction;
import diamond.run.core.model.Value;
import diamond.run.environment.Scope;
import io.github.coalangsoft.reflect.Clss;

public class ConstructorFunction implements SingleFunction{

	@Override
	public Value callZeroArg() {
		return this;
	}

	@Override
	public Value takeSingle(Scope s, Value a) {
		return new JavaFunctionArray((Clss) a.get());
	}
}