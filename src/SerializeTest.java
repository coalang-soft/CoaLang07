import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import cpa.subos.io.IO;
import diamond.run.core.impl.DefaultSingleImpl;
import diamond.run.core.model.Type;
import diamond.run.core.model.Value;

public class SerializeTest {

	public static void main(String[] args) throws IOException {
		Value v = new DefaultSingleImpl(Type.ARRAY_FUNCTION);
		ObjectOutputStream s = new ObjectOutputStream(System.out);
		s.writeObject(v);
		s.flush();
		
		IO.clientSocket("localhost", 20);
	}

}
