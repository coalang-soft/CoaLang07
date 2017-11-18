package diamond.run.environment;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import diamond.run.core.impl.ArrayImpl;
import diamond.run.core.impl.DefaultSingleImpl;
import diamond.run.core.impl.MacroImpl;
import diamond.run.core.impl.Operators;
import diamond.run.core.model.Array;
import diamond.run.core.model.Function;
import diamond.run.core.model.SingleFunction;
import diamond.run.core.model.Value;
import diamond.run.core.model.operator.SingleOperator;
import diamond.run.jbridge.JavaFunctionArray;
import diamond.text.interpret.GlobalInterpreter;
import io.github.coalangsoft.reflect.Clss;

public class Environment {
	
	public static void initGlobal(Scope scope){
		scope.put("out", new JavaFunctionArray(
			new Clss(System.out.getClass()).getMethods(System.out, "println")
		));
		scope.put("+", Operators.ADD);
		scope.put("\\", Operators.SUB);
		scope.put("*", Operators.MUL);
		scope.put("%", Operators.DIV);
		scope.put("<", Operators.LSS);
		scope.put(">", Operators.GTR);
		scope.put("=", Operators.EQU);
		scope.put("!", new Function() {
			@Override
			public Value take(Value v) {
				Array a = v.castArray();
				Value[] res = new Value[a.length()];
				for(int i = 0; i < a.length(); i++){
					try {
						res[i] = GlobalInterpreter.interpret(new ByteArrayInputStream(a.get(i).toString().getBytes()), scope);
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
				}
				return new ArrayImpl(res);
			}
			
			@Override
			public Value callZeroArg() {
				return this;
			}
		});
		scope.put("range", new SingleFunction() {
			@Override
			public Value takeSingle(Value v) {
				int len = (int) v.castNumber();
				Value[] arr = new Value[len];
				for(int i = 0; i < len; i++){
					arr[i] = new DefaultSingleImpl(i);
				}
				return new ArrayImpl(arr);				
			}
			
			@Override
			public Value callZeroArg() {
				return this;
			}
		});
		scope.put("exit", new Function() {
			@Override
			public Value take(Value v) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Value callZeroArg() {
				System.exit(0);
				return null;
			}
		});
		scope.put("'", new Function() {
			@Override
			public Value take(Value v) {
				return new MacroImpl(v);
			}
			
			@Override
			public Value callZeroArg() {
				return this;
			}
		});
	}
	
	public static void init(Scope scope){
		scope.put("$", new SingleOperator() {
			@Override
			public Value operateSingle(Value a, Value b) {
				String code = a.toString();
				String arg = b.toString();
				return new Function() {
					@Override
					public Value take(Value v) {
						Scope sc = scope.chain();
						sc.put(arg, v);
						try {
							return GlobalInterpreter.interpret(new ByteArrayInputStream(code.getBytes()), sc);
						} catch (IOException e) {
							throw new RuntimeException(e);
						}
					}
					
					@Override
					public Value callZeroArg() {
						return this;
					}
				};
			}
		});
	}
	
}
