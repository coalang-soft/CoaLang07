package diamond.run.jbridge;

import java.lang.reflect.Array;

import diamond.run.core.impl.ArrayImpl;
import diamond.run.core.impl.DefaultSingleImpl;
import diamond.run.core.model.Value;

public class JavaValues {
	
	public static Value make(Object o){
		if(o == null){
			return new ArrayImpl();
		}
		if(o.getClass().isArray()){
			int len = Array.getLength(o);
			Value[] vals = new Value[len];
			for(int i = 0; i < len; i++){
				vals[i] = make(Array.get(o, i));
			}
			return new ArrayImpl(vals);
		}
		return new DefaultSingleImpl(o);
	}
	
}
