package diamond.run.jbridge;

import diamond.run.core.impl.ArrayImpl;
import diamond.run.core.model.Value;
import diamond.run.core.model.operator.SingleOperator;
import diamond.run.environment.Scope;
import io.github.coalangsoft.reflect.Clss;

public class FieldFunction implements SingleOperator {

	@Override
	public Value operateSingle(Scope s, Value obj, Value field) {
		if(obj instanceof JavaClass){
			try {
				return JavaValues.make(((Clss) obj.get()).getField(field.toString()).get(null));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				return new ArrayImpl();
			}
		}else{
			try {
				return JavaValues.make((new Clss(obj.get().getClass())).getField(field.toString()).get(obj.get()));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				return new ArrayImpl();
			}
		}
	}

}
