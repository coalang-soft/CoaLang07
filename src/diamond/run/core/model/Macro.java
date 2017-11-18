package diamond.run.core.model;

public interface Macro extends Function {
	
	default Type getType(){
		return Type.MACRO;
	}
	
	default Value take(Value v){
		return takeString(v.get() + "");
	}

	Value takeString(String string);
	
}
