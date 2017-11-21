package diamond.run.core.model;

import diamond.run.environment.Scope;

public interface Macro extends SingleFunction {
	
	default Type getType(){
		return Type.MACRO;
	}
	
	default Value takeSingle(Scope s, Value v){
		return takeString(s, v.get() + "");
	}

	Value takeString(Scope s, String string);
	
}
