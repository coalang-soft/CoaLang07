package diamond.run.core.model;

import java.io.Serializable;

import diamond.run.core.impl.ArrayImpl;
import diamond.run.environment.Scope;

public interface Value extends Serializable {
	
	Type getType();
	Value take(Scope s, Value v);
	default Object get(){
		return this;
	}
	default Value callZeroArg(){
		return this;
	}
	
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
