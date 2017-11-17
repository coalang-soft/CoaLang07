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
	
}
