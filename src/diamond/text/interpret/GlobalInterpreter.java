package diamond.text.interpret;

import java.io.IOException;
import java.io.InputStream;

import diamond.run.environment.ReadOnlyScope;
import diamond.text.tokenize.StreamTokenReader;
import diamond.text.tokenize.TokenizerImpl;

public class GlobalInterpreter {
	
	public static void interpret(InputStream s, ReadOnlyScope scope) throws IOException{
		new StreamTokenReader(new TokenizerImpl(), new TokenHandlerImpl(scope)).readAndParse(s);
	}
	
}
