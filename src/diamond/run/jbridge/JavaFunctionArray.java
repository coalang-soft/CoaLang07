package diamond.run.jbridge;

import java.util.ArrayList;

import diamond.run.core.impl.ArrayImpl;
import diamond.run.core.model.Array;
import diamond.run.core.model.Value;

import io.github.coalangsoft.reflect.SpecificMethod;
import io.github.coalangsoft.reflect.SpecificMethods;

public class JavaFunctionArray implements Array {

	private SpecificMethods methods;

	public JavaFunctionArray(SpecificMethods methods) {
		this.methods = methods;
	}

	@Override
	public Value take(Value v) {
		// TODO Auto-generated method stub
		return null;
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
		ArrayList<SpecificMethod> ms = new ArrayList<>();
		
		int[] counts = methods.getParameterCounts();
		for(int i = 0; i < counts.length; i++){
			if(counts[i] == index){
				ms.add(methods.at(i));
			}
		}
		
		return new JavaFunction(ms.toArray(new SpecificMethod[0]), index);
	}

	@Override
	public Array makeArray(Value... values) {
		return new ArrayImpl(values);
	}

	@Override
	public Value callZeroArg() {
		return this;
	}

}
