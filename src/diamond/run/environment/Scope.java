package diamond.run.environment;

import java.io.Serializable;

import diamond.run.core.model.Value;

public interface Scope extends ReadOnlyScope, Serializable{
	
	void put(String name, Value value);
	Scope chain();
	
}
