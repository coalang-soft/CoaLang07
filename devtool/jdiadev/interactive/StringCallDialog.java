package jdiadev.interactive;

import diamond.run.core.impl.DefaultSingleImpl;
import diamond.run.core.model.Value;
import diamond.run.environment.Scope;

public class StringCallDialog extends AbstractCallDialog {

	private Scope scope;

	public StringCallDialog(Scope s, Value v) {
		super(v);
		this.scope = s;
	}

	@Override
	protected Value interpretAndCall(String in) {
		return value.take(scope, new DefaultSingleImpl(in));
	}

}
