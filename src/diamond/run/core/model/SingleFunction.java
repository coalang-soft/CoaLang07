package diamond.run.core.model;

public interface SingleFunction extends Function {

	default Value take(Value v){
		if(v.getType() == Type.ARRAY){
			return v.take(this);
		}else{
			return takeSingle(v);
		}
	}
	
	Value takeSingle(Value a);
	
}
