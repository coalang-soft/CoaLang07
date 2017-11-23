package diamond.run.jbridge;

import diamond.run.core.impl.ArrayImpl;
import diamond.run.core.model.Value;
import diamond.run.core.model.operator.SingleOperator;
import diamond.run.environment.Scope;
import io.github.coalangsoft.reflect.Clss;

public class MethodFunction implements SingleOperator{

	@Override
	public Value operateSingle(Scope s, Value obj, Value method) {
		if(obj instanceof JavaClass){
			try {
				return new JavaFunctionArray(((Clss) obj.get()).getMethods(null, method.toString()));
			} catch (IllegalArgumentException e) {
				return new ArrayImpl();
			}
		}else{
			try {
				return new JavaFunctionArray((new Clss(obj.get().getClass())).getMethods(obj.get(), method.toString()));
			} catch (IllegalArgumentException e) {
				return new ArrayImpl();
			}
		}
	}

}
