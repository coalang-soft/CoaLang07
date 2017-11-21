package diamond.text.interpret;

import diamond.run.core.impl.DefaultSingleImpl;
import diamond.run.core.model.Type;
import diamond.run.core.model.Value;
import diamond.run.environment.Scope;

public class RuntimeTranslater {
	
	Value current;
	
	public void feed(Scope s, Value v){
		if(current == null){
			current = v.callZeroArg();
		}else{
			current = current.take(s, v.callZeroArg()).callZeroArg();
		}
	}
	
	public void finish(){
		System.out.println("> " + current);
		current = null;
	}

	public boolean feedMacro(Scope s, String t) {
		if(current == null) return false;
		if(current.getType() == Type.MACRO){
			current = current.take(s, new DefaultSingleImpl(t));
			return true;
		}
		return false;
	}
	
}
