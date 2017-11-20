package diamond.run.jbridge;

import diamond.run.core.model.Function;
import diamond.run.core.model.Value;
import io.github.coalangsoft.reflect.AbstractMultipleCallableSequence;
import io.github.coalangsoft.reflect.CasterImpl;
import io.github.coalangsoft.reflect.SingleCallable;

public class JavaFunction implements Function {

	private SingleCallable[] array;
	private int argCount;

	public JavaFunction(SingleCallable[] array, int argCount) {
		this.array = array;
		this.argCount = argCount;
	}

	@Override
	public Value take(Value v) {
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
