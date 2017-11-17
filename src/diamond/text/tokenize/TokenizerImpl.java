package diamond.text.tokenize;

public class TokenizerImpl implements Tokenizer{
	
	private String current = "";
	
	@Override
	public Token feed(char c) {
		if(Character.isWhitespace(c)){
			if(!current.isEmpty()){
				String s = current;
				current = "";
				return new TokenImpl(s);
			}
			return null;
		}else{
			current += c;
		}
		return null;
	}

	@Override
	public Token finish(){
		Token t = finish0();
		current = "";
		return t;
	}
	
	private Token finish0() {
		if(current.isEmpty()){
			return null;
		}
		return new TokenImpl(current);
	}
	
}
