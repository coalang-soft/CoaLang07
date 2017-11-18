package diamond.text.tokenize;

import diamond.text.interpret.TokenCategory;

public class CategoryTokenizerImpl implements Tokenizer {

	private String current = "";
	private TokenCategory category;
	
	@Override
	public Token feed(char c) {
		if(Character.isWhitespace(c)){
			return makeToken();
		}
		if(category == null){
			current += c;
			category = TokenCategory.compute(current);
			return null;
		}else{
			TokenCategory cat = TokenCategory.compute(c + "");
			if(cat != category){
				Token t = makeToken();
				current += c;
				category = cat;
				return t;
			}
			current += c;
			return null;
		}
	}
	
	private Token makeToken(){
		if(!current.isEmpty()){
			Token t = new TokenImpl(current);
			current = "";
			category = null;
			return t;
		}
		return null;
	}

	@Override
	public Token finish() {
		return makeToken();
	}

}
