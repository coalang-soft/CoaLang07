package diamond.run.environment;

import java.util.HashMap;

import diamond.run.core.impl.Arrays;
import diamond.run.core.model.Value;

public class ScopeImpl implements Scope {
	
	private final HashMap<String, Value> values;
	private final Scope parent;
	
	{
		values = new HashMap<>();
		Environment.init(this);
	}
	
	public ScopeImpl(){
		this.parent = null;
	}
	public ScopeImpl(Scope parent){
		this.parent = parent;
	}
	
	@Override
	public Value flatLookup(String name) {
		return values.get(name);
	}

	@Override
	public ReadOnlyScope parent() {
		return parent;
	}

	@Override
	public void put(String name, Value value) {
		Value v = values.get(name);
		if(v != null){
			v = Arrays.concat(v.castArray(), value.castArray());
		}else{
			v = value;
		}
		values.put(name, v);
	}

	@Override
	public Scope chain() {
		return new ScopeImpl(this);
	}
	
	@Override
	public String toString() {
		return "ScopeImpl [values=" + values + ", parent=" + parent + "]";
	}

}
