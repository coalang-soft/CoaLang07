package diamond.run.core.impl;

import diamond.run.core.model.Macro;
import diamond.run.core.model.Value;

public class MacroImpl implements Macro {

	private Value base;

	public MacroImpl(Value base){
		this.base = base;
	}
	
	@Override
	public Value takeString(String s) {
		return base.take(new DefaultSingleImpl(s));
	}

	@Override
	public Value callZeroArg() {
		return this;
	}

}
