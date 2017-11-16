import java.io.IOException;

import diamond.run.impl.ArrayImpl;
import diamond.run.impl.DefaultSingleImpl;
import diamond.run.model.Function;
import diamond.run.model.Value;
import diamond.run.model.operator.NumberOperator;
import diamond.run.model.operator.Operator;

public class Test {
	
	public static void main(String[] args) throws IOException{
		Value a = new DefaultSingleImpl(1);
		Value b = new DefaultSingleImpl(2);
		Value arr = new ArrayImpl(a,b);
		
		Value res1 = arr.take(new NumberOperator(){
			@Override
			public double numberOperate(double a, double b) {
				return a + b;
			}
		});
		
		System.out.println(res1);
		
		Value res2 = res1.take(new ArrayImpl(
			new DefaultSingleImpl(2)
		));
		System.out.println(res2);
	}
	
}