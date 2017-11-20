package diamond.run.jbridge;

import diamond.run.core.model.DefaultSingle;
import diamond.run.core.model.Value;
import io.github.coalangsoft.reflect.Clss;

public class JavaClass implements DefaultSingle {

	private String name;

	public JavaClass(String name) {
		this.name = name;
	}

	@Override
	public Value callZeroArg() {
		return this;
	}

	@Override
	public Object get() {
		try {
			return Clss.make(name);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

}
