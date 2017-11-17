package diamond.run.core.impl;

import diamond.run.core.model.DefaultSingle;
import diamond.run.core.model.Value;

public class DefaultSingleImpl implements DefaultSingle{

	private Object object;

	public DefaultSingleImpl(Object o){
		this.object = o;
	}
	
	@Override
	public Object get() {
		return object;
	}
	
	public String toString(){
		return object + "";
	}

	@Override
	public Value callZeroArg() {
		return this;
	}

}
