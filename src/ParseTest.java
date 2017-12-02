import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import diamond.run.environment.Environment;
import diamond.run.environment.Scope;
import diamond.run.environment.ScopeImpl;
import diamond.text.interpret.GlobalInterpreter;

public class ParseTest {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Scope s = new ScopeImpl();
//		s = (Scope) new ObjectInputStream(new FileInputStream("scope.srimg")).readObject();
//		
//		System.out.println("Scope loaded.");
		
		Environment.initGlobal(s);
		
		GlobalInterpreter.interpret(ParseTest.class.getResourceAsStream("/diamond/lib/core.cndm"), s);
		GlobalInterpreter.interpret(ParseTest.class.getResourceAsStream("/diamond/lib/jbridge.cndm"), s);
		GlobalInterpreter.interpret(ParseTest.class.getResourceAsStream("/diamond/lib/arrays.cndm"), s);
		GlobalInterpreter.interpret(ParseTest.class.getResourceAsStream("/diamond/lib/io.cndm"), s);
//		GlobalInterpreter.interpret(ParseTest.class.getResourceAsStream("/diamond/lib/jserialize.cndm"), s);
		GlobalInterpreter.interpret(System.in, s);
		
//		GlobalInterpreter.interpret(new FileInputStream("samples/ioSample.cndm"), s);
		
//		ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream("scope.srimg"));
//		stream.writeObject(s);
//		stream.close();
	}
	
}