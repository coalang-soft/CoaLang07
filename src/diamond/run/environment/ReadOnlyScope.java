package diamond.run.environment;

import diamond.run.core.model.Value;

public interface ReadOnlyScope {
	
	Value flatLookup(String name);
	ReadOnlyScope parent();
	
	default Value deepLookup(String name){
		Value v = flatLookup(name);
		if(v != null){
			return v;
		}
		if(parent() == null){
			throw new RuntimeException("Variable " + name + " not found.");
		}
		return parent().deepLookup(name);
	}
	
}
