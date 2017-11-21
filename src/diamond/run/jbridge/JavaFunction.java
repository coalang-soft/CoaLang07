package diamond.run.jbridge;

import diamond.run.core.model.SingleFunction;
import diamond.run.core.model.Value;
import diamond.run.environment.Scope;
import io.github.coalangsoft.reflect.AbstractMultipleCallableSequence;
import io.github.coalangsoft.reflect.CasterImpl;
import io.github.coalangsoft.reflect.SingleCallable;

public class JavaFunction implements SingleFunction {

	private SingleCallable[] array;
	private int argCount;

	public JavaFunction(SingleCallable[] array, int argCount) {
		this.array = array;
		this.argCount = argCount;
	}

	@Override
	public Value takeSingle(Scope s, Value v) {
		if(argCount == 1){
			return JavaValues.make(new AbstractMultipleCallableSequence(array).call(new Object[]{v.get()}, new CasterImpl()));
		}else{
			throw new RuntimeException("NIy");
		}
	}

	@Override
	public Value callZeroArg() {
		if(argCount == 0){
			return JavaValues.make(new AbstractMultipleCallableSequence(array).call(new Object[0]));
		}
		return this;
	}

}
