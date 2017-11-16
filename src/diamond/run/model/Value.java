package diamond.run.model;

public interface Value {
	
	Type getType();
	Value take(Value v);
	Object get();

}
