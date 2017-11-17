package diamond.run.environment;

import diamond.run.core.model.Value;

public interface Scope extends ReadOnlyScope{
	
	void put(String name, Value value);
	Scope chain();
	
}
