package diamond.text.interpret;

import diamond.run.core.model.Value;

public class RuntimeTranslater {
	
	private Value current;
	
	public void feed(Value v){
		if(current == null){
			current = v;
		}else{
			current.take(v);
		}
	}
	
	public void finish(){
		current = null;
	}
	
}
