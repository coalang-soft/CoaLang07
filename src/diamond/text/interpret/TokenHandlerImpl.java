package diamond.text.interpret;

import diamond.run.environment.ReadOnlyScope;
import diamond.text.tokenize.Token;
import diamond.text.tokenize.TokenHandler;

public class TokenHandlerImpl implements TokenHandler {

	private int stringDepth, bracketDepth;
	private StringBuilder stringBuilder = new StringBuilder();
	private ReadOnlyScope scope;
	private RuntimeTranslater translater = new RuntimeTranslater();
	
	public TokenHandlerImpl(ReadOnlyScope scope){
		this.scope = scope;
	}
	
	@Override
	public void handle(Token t) {
		if(stringDepth > 0){
			handleInString(t);
			return;
		}
		if(bracketDepth > 0){
			handleInBracket(t);
			return;
		}
		switch(t.getCategory()){
		case STR_OPEN: increaseStringDepth(1); break;
		case STR_CLOSE: throw new RuntimeException("Negative String depth!");
		case BRACKET_CLOSE: throw new RuntimeException("Negative Bracket depth!");
		case NAME: translater.feed(scope.deepLookup(t.raw())); break;
		default: throw new RuntimeException(t.getCategory() + "");
		}
	}

	private void handleInString(Token t) {
		switch(t.getCategory()){
		case STR_CLOSE: increaseStringDepth(-1); stateCheck(TokenCategory.STR_CLOSE); break;
		case STR_OPEN: increaseStringDepth(1); break;
		default: {
			if(stringBuilder.length() > 0){
				stringBuilder.append(" ");
			}
			stringBuilder.append(t.raw());
		}
		}
	}
	
	private void handleInBracket(Token t) {
		switch(t.getCategory()){
		case BRACKET_CLOSE: increaseBracketDepth(-1); stateCheck(TokenCategory.BRACKET_CLOSE); break;
		case BRACKET_OPEN: increaseBracketDepth(1); break;
		default: {
			if(stringBuilder.length() > 0){
				stringBuilder.append(" ");
			}
			stringBuilder.append(t.raw());
		}
		}
	}

	private void stateCheck(TokenCategory bracketClose) {
		if(stringDepth == 0 && bracketDepth == 0){
			throw new RuntimeException("State finishing not yet implemented!");
		}
	}

	private void increaseBracketDepth(int i) {
		bracketDepth += i;
	}

	private void increaseStringDepth(int i) {
		stringDepth += i;
	}

}
