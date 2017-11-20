package diamond.run.core.model;

public interface Macro extends SingleFunction {
	
	default Type getType(){
		return Type.MACRO;
	}
	
	default Value takeSingle(Value v){
		return takeString(v.get() + "");
	}

	Value takeString(String string);
	
}
