package diamond.run.core.impl.dynamic;

import java.util.HashMap;

import diamond.run.core.model.Array;
import diamond.run.core.model.Value;
import diamond.run.environment.Scope;

public abstract class DynamicArray implements Array {

	private int length;
	private HashMap<Integer, Value> values;

	public DynamicArray(int length){
		this.length = length;
		this.values = new HashMap<>();
	}
	
	@Override
	public int length() {
		return length;
	}
	
	public final Value get(int index){
		if(length > -1){
			if(length <= index){
				throw new ArrayIndexOutOfBoundsException(index);
			}
		}
		
		Value v = values.get(index);
		if(v != null){
			return v;
		}
		v = get0(index);
		values.put(index, v);
		return v;
	}

	protected abstract Value get0(int index);

}