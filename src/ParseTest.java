import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;

import diamond.run.environment.Environment;
import diamond.run.environment.Scope;
import diamond.run.environment.ScopeImpl;
import diamond.text.interpret.GlobalInterpreter;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import jdiadev.ScopeExplorer;

public class ParseTest {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Scope s = new ScopeImpl();
//		s = (Scope) new ObjectInputStream(new FileInputStream("scope.srimg")).readObject();
//		
//		System.out.println("Scope loaded.");
		
		Environment.initGlobal(s);
		
		GlobalInterpreter.interpret(ParseTest.class.getResourceAsStream("/diamond/lib/load.cndm"), s);
		
//		JFXPanel p = new JFXPanel();
//		p.setScene(new Scene(new ScopeExplorer(s)));
//		JFrame f = new JFrame("Scope explorer");
//		f.add(p);
//		f.setVisible(true);
//		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GlobalInterpreter.interpret(System.in, s);
		
//		GlobalInterpreter.interpret(new FileInputStream("samples/ioSample.cndm"), s);
		
//		ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream("scope.srimg"));
//		stream.writeObject(s);
//		stream.close();
	}
	
}