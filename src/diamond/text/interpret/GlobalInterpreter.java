package diamond.text.interpret;

import java.io.IOException;
import java.io.InputStream;

import diamond.run.core.model.Value;
import diamond.run.environment.Scope;
import diamond.text.tokenize.StreamTokenReader;
import diamond.text.tokenize.SplitTokenizerImpl;

public class GlobalInterpreter {
	
	public static Value interpret(InputStream s, Scope scope) throws IOException{
		TokenHandlerImpl handler = new TokenHandlerImpl(scope);
		new StreamTokenReader(new SplitTokenizerImpl(), handler).readAndParse(s);
		return handler.getCurrentValue();
	}
	
}
