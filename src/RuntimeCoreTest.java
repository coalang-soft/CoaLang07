import java.io.IOException;

import diamond.run.core.impl.ArrayImpl;
import diamond.run.core.impl.DefaultSingleImpl;
import diamond.run.core.impl.Operators;
import diamond.run.core.model.Value;

public class RuntimeCoreTest {
	
	public static void main(String[] args) throws IOException{
		Value a = new DefaultSingleImpl(1);
		Value b = new DefaultSingleImpl(2);
		Value arr = new ArrayImpl(a,b);
		
		Value res1 = arr.take(Operators.ADD);
		
		System.out.println(res1);
		
		Value res2 = res1.take(new ArrayImpl(
			new DefaultSingleImpl(2.5)
		));
		System.out.println(res2);
	}
	
}