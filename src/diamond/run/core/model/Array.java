package diamond.run.core.model;

public interface Array extends Value{
	
	default Type getType(){
		return Type.ARRAY;
	}
	
	default Value take(Value v){
		if(v.getType() == Type.ARRAY){
			return takeArray((Array) v);
		}else{
			return takeSingle(v);
		}
	}
	
	default Array takeSingle(Value v){
		Value[] vs = new Value[length()];
		for(int i = 0; i < vs.length; i++){
			vs[i] = get(i).take(v);
		}
		return makeArray(vs);
	}
	
	default Array takeArray(Array v){
		int len = length() < v.length() ? v.length() : length();
		Value[] vs = new Value[len];
		for(int i = 0; i < len; i++){
			vs[i] = get(i % length()).take(v.get(i % v.length()));
		}
		return makeArray(vs);
	}
	
	default Value callZeroArg(){
		if(length() == 1){
			return get(0).callZeroArg();
		}
		return this;
	}

	int length();
	Value get(int index);
	Array makeArray(Value... values);

}
