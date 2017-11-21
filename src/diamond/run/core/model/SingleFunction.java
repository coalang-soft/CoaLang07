package diamond.run.core.model;

import diamond.run.environment.Scope;

public interface SingleFunction extends Value {

	default Value take(Scope s, Value v){
		if(v.getType() == Type.ARRAY){
			return v.take(s, this);
		}else{
			return takeSingle(s, v);
		}
	}
	
	Value takeSingle(Scope s, Value a);
	
	default Type getType(){
		return Type.SINGLE_FUNCTION;
	}
	
}
