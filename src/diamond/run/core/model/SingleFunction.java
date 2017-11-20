package diamond.run.core.model;

public interface SingleFunction extends Value {

	default Value take(Value v){
		if(v.getType() == Type.ARRAY){
			return v.take(this);
		}else{
			return takeSingle(v);
		}
	}
	
	Value takeSingle(Value a);
	
	default Type getType(){
		return Type.SINGLE_FUNCTION;
	}
	
}
