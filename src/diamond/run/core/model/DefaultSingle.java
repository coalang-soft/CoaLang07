package diamond.run.core.model;

public interface DefaultSingle extends Value{
	
	default Type getType(){
		return Type.DEFAULT_SINGLE;
	}
	
	default Value take(Value v){
		if(v.getType() == Type.DEFAULT_SINGLE){
			throw new RuntimeException(v + " can not be sent to " + this);
		}
		return v.take(this);
	}
	
}