package diamond.run.jbridge;

import java.util.ArrayList;

import diamond.run.core.impl.ArrayImpl;
import diamond.run.core.model.Array;
import diamond.run.core.model.Value;
import io.github.coalangsoft.reflect.MultipleCallableSequence;
import io.github.coalangsoft.reflect.SingleCallable;

public class JavaFunctionArray implements Array {

	private MultipleCallableSequence<? extends SingleCallable,?> methods;

	public JavaFunctionArray(MultipleCallableSequence<? extends SingleCallable,?> methods) {
		this.methods = methods;
	}

	@Override
	public Object get() {
		return methods;
	}

	@Override
	public int length() {
		return -1;
	}

	@Override
	public Value get(int index) {
		ArrayList<SingleCallable> ms = new ArrayList<>();
		
		int[] counts = methods.getParameterCounts();
		for(int i = 0; i < counts.length; i++){
			if(counts[i] == index){
				ms.add(methods.at(i));
			}
		}
		
		return new JavaFunction(ms.toArray(new SingleCallable[0]), index);
	}

	@Override
	public Array makeArray(Value... values) {
		return new ArrayImpl(values);
	}

}
