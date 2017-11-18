package diamond.text.tokenize;

public class SplitTokenizerImpl implements Tokenizer{
	
	private String current = "";
	
	@Override
	public Token feed(char c) {
		if(Character.isWhitespace(c)){
			return makeToken();
		}else{
			current += c;
		}
		return null;
	}

	@Override
	public Token finish(){
		return makeToken();
	}
	
	private Token makeToken() {
		if(current.isEmpty()){
			return null;
		}
		Token t = new TokenImpl(current);
		current = "";
		return t;
	}
	
}
