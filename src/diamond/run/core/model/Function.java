package diamond.run.core.model;

public interface Function extends Value{

	default Type getType(){
		return Type.FUNCTION;
	}
	
	default Object get(){
		return this;
	}
	
}
