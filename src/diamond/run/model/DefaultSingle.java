package diamond.run.model;

public interface DefaultSingle extends Value{
	
	default Type getType(){
		return Type.DEFAULT_SINGLE;
	}
	
	default Value take(Value v){
		return v.take(this);
	}
	
}