package diamond.run.jbridge;

import java.util.Arrays;

import diamond.run.core.model.SingleFunction;
import diamond.run.core.model.Value;
import diamond.run.environment.Scope;
import io.github.coalangsoft.reflect.AbstractMultipleCallableSequence;
import io.github.coalangsoft.reflect.CasterImpl;
import io.github.coalangsoft.reflect.SingleCallable;

public class JavaFunction implements SingleFunction {

	private SingleCallable[] array;
	private int argCount;
	private Object[] arguments;

	public JavaFunction(SingleCallable[] array, int argCount, Object... arguments) {
		this.array = array;
		this.argCount = argCount;
		this.arguments = arguments;
	}

	@Override
	public Value takeSingle(Scope s, Value v) {
		Object[] copy = Arrays.copyOf(arguments, arguments.length + 1);;
		copy[copy.length - 1] = v.get();
		return new JavaFunction(array, argCount, copy);
	}

	@Override
	public Value callZeroArg() {
		if(argCount == arguments.length){
			return JavaValues.make(new AbstractMultipleCallableSequence(array).call(arguments, new CasterImpl()));
		}
		return this;
	}

}
