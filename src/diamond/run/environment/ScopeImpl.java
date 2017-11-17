package diamond.run.environment;

import java.util.HashMap;

import diamond.run.core.model.Value;

public class ScopeImpl implements ReadOnlyScope {
	
	{
		values = new HashMap<>();
	}
	
	private final HashMap<String, Value> values;
	
	@Override
	public Value flatLookup(String name) {
		return values.get(name);
	}

	@Override
	public ReadOnlyScope parent() {
		// TODO Auto-generated method stub
		return null;
	}

}
