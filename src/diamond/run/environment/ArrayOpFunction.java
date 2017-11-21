package diamond.run.environment;

import diamond.run.core.impl.ArrayImpl;
import diamond.run.core.model.Array;
import diamond.run.core.model.ArrayFunction;
import diamond.run.core.model.SingleFunction;
import diamond.run.core.model.Value;

public class ArrayOpFunction implements ArrayFunction {

	@Override
	public Value takeArray(Scope s, Array a) {
		if(a.length() == 0){
			return new ArrayImpl();
		}else{
			return new SingleFunction() {
				@Override
				public Value takeSingle(Scope s, Value op) {
					Value v = a.get(0);
					for(int i = 1; i < a.length(); i++){
						v = op.take(s, v).take(s, a.get(i));
					}
					return v;
				}
				
				@Override
				public Value callZeroArg() {
					return this;
				}
			};
		}
	}

}
