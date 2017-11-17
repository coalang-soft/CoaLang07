import java.io.IOException;

import diamond.run.environment.Environment;
import diamond.run.environment.Scope;
import diamond.run.environment.ScopeImpl;
import diamond.text.interpret.GlobalInterpreter;

public class ParseTest {
	
	public static void main(String[] args) throws IOException {
		Scope s = new ScopeImpl();
		Environment.init(s);
		
		GlobalInterpreter.interpret(System.in, s);
	}
	
}
