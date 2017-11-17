package diamond.text.tokenize;

import diamond.text.interpret.TokenCategory;

public interface Token {
	
	String raw();
	
	default TokenCategory getCategory(){
		return TokenCategory.compute(raw());
	}
	
}
