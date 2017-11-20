package diamond.run.core.model;

public interface ArrayFunction extends Value {
	
	default Type getType(){
		return Type.ARRAY_FUNCTION;
	}
	
	default Value take(Value v){
		return takeArray(v.castArray());
	}

	Value takeArray(Array a);
	
}
