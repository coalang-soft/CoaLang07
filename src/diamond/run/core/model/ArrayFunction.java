package diamond.run.core.model;

import diamond.run.environment.Scope;

public interface ArrayFunction extends Value {
	
	default Type getType(){
		return Type.ARRAY_FUNCTION;
	}
	
	default Value take(Scope s, Value v){
		return takeArray(s, v.castArray());
	}

	Value takeArray(Scope s, Array a);
	
}
