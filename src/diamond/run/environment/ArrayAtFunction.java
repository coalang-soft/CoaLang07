package diamond.run.environment;

import diamond.run.core.model.Array;
import diamond.run.core.model.ArrayFunction;
import diamond.run.core.model.SingleFunction;
import diamond.run.core.model.Value;

public class ArrayAtFunction implements ArrayFunction {
	
	@Override
	public Value takeArray(Scope s, Array array) {
		return new SingleFunction(){
			@Override
			public Value takeSingle(Scope s, Value a) {
				return array.get((int) a.castNumber());
			}
		};
	}

}
