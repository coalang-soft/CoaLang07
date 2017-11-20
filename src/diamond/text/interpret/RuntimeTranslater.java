package diamond.text.interpret;

import diamond.run.core.impl.ArrayImpl;
import diamond.run.core.impl.DefaultSingleImpl;
import diamond.run.core.model.Array;
import diamond.run.core.model.SingleFunction;
import diamond.run.core.model.Type;
import diamond.run.core.model.Value;
import diamond.run.environment.ArrayAtFunction;
import diamond.run.environment.Scope;
import diamond.run.environment.StoreConstFunction;

public class RuntimeTranslater {
	
	Value current;
	private Scope storeConst;
	
	public void feed(Value v){
		if(current == null){
			current = v.callZeroArg();
		}else if(storeConst != null){
			current = new StoreConstFunction(storeConst).take(current).take(v);
			storeConst = null;
		}else{
			current = current.take(v.callZeroArg()).callZeroArg();
		}
	}
	
	public void finish(){
		System.out.println("> " + current);
		current = null;
	}

	public void arrayAt() {
		current = new ArrayAtFunction(current.castArray());
	}

	public void storeConst(Scope s) {
		feed(new StoreConstFunction(s));
	}

	public void arrayOp() {
		Array a = current.castArray();
		if(a.length() == 0){
			current = new ArrayImpl();
		}else{
			current = new SingleFunction() {
				@Override
				public Value takeSingle(Value op) {
					Value v = a.get(0);
					for(int i = 1; i < a.length(); i++){
						v = op.take(v).take(a.get(i));
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

	public boolean feedMacro(String t) {
		if(current == null) return false;
		if(current.getType() == Type.MACRO){
			current = current.take(new DefaultSingleImpl(t));
			return true;
		}
		return false;
	}
	
}
