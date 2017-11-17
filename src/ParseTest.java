import java.io.IOException;

import diamond.run.core.model.Value;
import diamond.run.environment.ReadOnlyScope;
import diamond.text.interpret.GlobalInterpreter;

public class ParseTest {
	
	public static void main(String[] args) throws IOException {
		GlobalInterpreter.interpret(System.in, new ReadOnlyScope() {
			@Override
			public ReadOnlyScope parent() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Value flatLookup(String name) {
				// TODO Auto-generated method stub
				return null;
			}
		});
	}
	
}
