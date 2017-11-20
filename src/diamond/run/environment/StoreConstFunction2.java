package diamond.run.environment;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import diamond.run.core.model.Value;
import diamond.run.core.model.operator.SingleOperator;
import diamond.text.interpret.GlobalInterpreter;

public class StoreConstFunction2 implements SingleOperator{
	private Scope scope;

	public StoreConstFunction2(Scope s) {
		this.scope = s;
	}

	@Override
	public Value operateSingle(Value a, Value b) {
		try {
			Value v = GlobalInterpreter.interpret(new ByteArrayInputStream(a.toString().getBytes()), scope);
			scope.put(b.toString(), v);
			return a;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
