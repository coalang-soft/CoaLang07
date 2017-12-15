package jdiadev.interactive;

import diamond.run.core.impl.DefaultSingleImpl;
import diamond.run.core.model.Value;
import diamond.run.environment.Scope;

public class NumberCallDialog extends AbstractCallDialog {

	private Scope scope;

	public NumberCallDialog(Scope s, Value v) {
		super(v);
		this.scope = s;
	}

	@Override
	protected Value interpretAndCall(String in) {
		return value.take(scope, new DefaultSingleImpl(Double.parseDouble(in)));
	}

}