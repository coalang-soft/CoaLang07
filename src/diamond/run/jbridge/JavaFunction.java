package diamond.run.jbridge;

import diamond.run.core.model.Function;
import diamond.run.core.model.Value;
import io.github.coalangsoft.reflect.SpecificMethod;
import io.github.coalangsoft.reflect.SpecificMethods;

public class JavaFunction implements Function {

	private SpecificMethod[] array;
	private int argCount;

	public JavaFunction(SpecificMethod[] array, int argCount) {
		this.array = array;
		this.argCount = argCount;
	}

	@Override
	public Value take(Value v) {
		if(argCount == 1){
			return JavaValues.make(new SpecificMethods(array).call(new Object[]{v.get()}));
		}else{
			throw new RuntimeException("NIy");
		}
	}

	@Override
	public Value callZeroArg() {
		if(argCount == 0){
			return JavaValues.make(new SpecificMethods(array).call(new Object[0]));
		}
		return this;
	}

}
