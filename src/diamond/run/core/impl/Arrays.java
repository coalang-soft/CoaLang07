package diamond.run.core.impl;

import diamond.run.core.model.Array;
import diamond.run.core.model.Value;

public class Arrays {
	
	public static Array concat(Array a, Array b){
		Value[] raw = new Value[a.length() + b.length()];
		for(int i = 0; i < a.length(); i++){
			raw[i] = a.get(i);
		}
		for(int i = 0; i < b.length(); i++){
			raw[i + a.length()] = b.get(i);
		}
		return new ArrayImpl(raw);
	}
	
}