package diamond.run.jbridge;

import diamond.run.core.impl.ArrayImpl;
import diamond.run.core.model.Value;

public class JavaValues {
	
	public static Value make(Object o){
		if(o == null){
			return new ArrayImpl();
		}
		throw new RuntimeException(o + "");
	}
	
}
