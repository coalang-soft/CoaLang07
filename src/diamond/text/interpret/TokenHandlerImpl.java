package diamond.text.interpret;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;

import diamond.run.core.impl.ArrayImpl;
import diamond.run.core.impl.DefaultSingleImpl;
import diamond.run.core.model.Value;
import diamond.run.environment.Scope;
import diamond.text.tokenize.Token;
import diamond.text.tokenize.TokenHandler;

public class TokenHandlerImpl implements TokenHandler {

	private int stringDepth, bracketDepth;
	private StringBuilder stringBuilder = new StringBuilder();
	private ArrayList<String> stringList = new ArrayList<String>();
	private Scope scope;
	private RuntimeTranslater translater = new RuntimeTranslater();
	private boolean unescaped;
	
	public TokenHandlerImpl(Scope scope){
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
		if(!(t.getCategory() == TokenCategory.STR_OPEN)){
			if(translater.feedMacro(t.raw())){
				return;
			}
		}
		switch(t.getCategory()){
		case STR_OPEN: increaseStringDepth(1); break;
		case STR_CLOSE: throw new RuntimeException("Negative String depth!");
		case BRACKET_OPEN: increaseBracketDepth(1); break;
		case BRACKET_CLOSE: throw new RuntimeException("Negative Bracket depth!");
		case NAME: translater.feed(scope.deepLookup(t.raw())); break;
		case NUMBER: translater.feed(new DefaultSingleImpl(Double.parseDouble(t.raw()))); break;
		case LIST_OPERATION: translater.arrayOp(); break;
		case SEMICOLON: translater.finish(); break;
		case LIST_AT: translater.arrayAt(); break;
		default: throw new RuntimeException(t.getCategory() + "");
		}
	}

	private void handleInString(Token t) {
		
		if(unescaped){
			switch(t.raw()) {
			case "s": stringBuilder.append(" "); break;
			default: stringBuilder.append(t.raw());
			}
			unescaped = false;
			return;
		}
		
		switch(t.getCategory()){
		case STR_CLOSE: increaseStringDepth(-1); stateCheck(TokenCategory.STR_CLOSE); break;
		case STR_OPEN: increaseStringDepth(1); appendString("["); break;
		case SEMICOLON: stringList.add(stringBuilder.toString()); stringBuilder = new StringBuilder(); break;
		default: {
			appendString(t.raw());
		}
		}
	}
	
	private void appendString(String t) {
		if(t.equals("|")){
			unescaped = true;
			return;
		}
		if(stringBuilder.length() > 0){
			stringBuilder.append(" ");
		}
		stringBuilder.append(t);
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

	private void stateCheck(TokenCategory closer) {
		if(stringDepth == 0 && bracketDepth == 0){
			switch(closer){
			case BRACKET_CLOSE: try {
					translater.feed(
							GlobalInterpreter.interpret(new ByteArrayInputStream(stringBuilder.toString().getBytes()), scope)
						);
				} catch (IOException e) {
					//Should not happen
					throw new RuntimeException(e);
				} stringBuilder = new StringBuilder(); break;
			case STR_CLOSE:
				if(stringBuilder.length() != 0){
					stringList.add(stringBuilder.toString());
					stringBuilder = new StringBuilder();
				}
				Value[] strings = new Value[stringList.size()];
				for(int i = 0; i < stringList.size(); i++){
					strings[i] = new DefaultSingleImpl(stringList.get(i));
				}
				stringList.clear();
				translater.feed(new ArrayImpl(strings)); stringBuilder = new StringBuilder(); break;
			default: throw new RuntimeException("State finishing not yet implemented for " + closer);
			}
		}else if(closer == TokenCategory.STR_CLOSE){
			appendString("]");
		}else if(closer == TokenCategory.STR_OPEN){
			appendString("[");
		}
	}

	private void increaseBracketDepth(int i) {
		bracketDepth += i;
	}

	private void increaseStringDepth(int i) {
		stringDepth += i;
	}

	public Value getCurrentValue() {
		return translater.current;
	}

}
