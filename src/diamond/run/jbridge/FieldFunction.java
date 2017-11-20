package diamond.run.jbridge;

import diamond.run.core.model.Value;
import diamond.run.core.model.operator.SingleOperator;
import io.github.coalangsoft.reflect.Clss;

public class FieldFunction implements SingleOperator {

	@Override
	public Value operateSingle(Value obj, Value field) {
		if(obj instanceof JavaClass){
			try {
				return JavaValues.make(((Clss) obj.get()).getField(field.toString()).get(null));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}else{
			try {
				return JavaValues.make(((Clss) obj.get()).getField(field.toString()).get(obj.get()));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}
	}

}
