package diamond.run.core.impl;

import java.util.Arrays;

import diamond.run.core.model.Array;
import diamond.run.core.model.Value;

public class ArrayImpl implements Array {

	private final Value[] list;

	public ArrayImpl(Value... args){
		this.list = args;
	}
	
	@Override
	public Object get() {
		return list;
	}

	@Override
	public int length() {
		return list.length;
	}

	@Override
	public Value get(int index) {
		return list[index];
	}

	@Override
	public Array makeArray(Value... values) {
		return new ArrayImpl(values);
	}
	
	public String toString(){
		return Arrays.toString(list);
	}

	@Override
	public Value callZeroArg() {
		return this;
	}

}
