package diamond.run.core.model;

import diamond.run.core.impl.ArrayImpl;

public interface Value {
	
	Type getType();
	Value take(Value v);
	Object get();
	Value callZeroArg();
	
	default Array castArray(){
		if(this instanceof Array){
			return (Array) this;
		}
		return new ArrayImpl(this);
	}
	default double castNumber(){
		return ((Number) get()).doubleValue();
	}

}
