package diamond.run.environment;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import cpa.subos.io.IO;
import cpa.subos.io.IOBase;
import diamond.run.core.impl.dynamic.RangeArray;
import diamond.run.core.impl.ArrayImpl;
import diamond.run.core.impl.DefaultSingleImpl;
import diamond.run.core.impl.Operators;
import diamond.run.core.model.Array;
import diamond.run.core.model.ArrayFunction;
import diamond.run.core.model.Macro;
import diamond.run.core.model.SingleFunction;
import diamond.run.core.model.Value;
import diamond.run.core.model.operator.SingleOperator;
import diamond.run.jbridge.ConstructorFunction;
import diamond.run.jbridge.FieldFunction;
import diamond.run.jbridge.JavaClass;
import diamond.run.jbridge.MethodFunction;
import diamond.text.interpret.GlobalInterpreter;

public class Environment {
	
	public static void initGlobal(Scope global){
		global.put("+", Operators.ADD);
		global.put("\\", Operators.SUB);
		global.put("*", Operators.MUL);
		global.put("%", Operators.DIV);
		global.put("^", Operators.POW);
		global.put("<", Operators.LSS);
		global.put(">", Operators.GTR);
		global.put("=", Operators.EQU);
		global.put("%%", Operators.MOD);
		global.put(",", Operators.ARR_CONCAT);
		global.put("arrayAt", new ArrayAtFunction());
		global.put("length", new ArrayFunction() {
			@Override
			public Value takeArray(Scope s, Array a) {
				return new DefaultSingleImpl(a.length());
			}
		});
		global.put("!", new SingleFunction() {
			@Override
			public Value takeSingle(Scope scope, Value v) {
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
		global.put("range", new SingleFunction() {
			@Override
			public Value takeSingle(Scope s, Value v) {
				int len = (int) v.castNumber();
				return new RangeArray(len);
			}
			
			@Override
			public Value callZeroArg() {
				return this;
			}
		});
		global.put("strConcat", new SingleOperator() {
			@Override
			public Value operateSingle(Scope s, Value a, Value b) {
				return new DefaultSingleImpl(a.get() + "" + b.get());
			}
		});
		global.put("join", new ArrayFunction() {
			@Override
			public Value takeArray(Scope s, Array arr) {
				return new SingleFunction() {
					@Override
					public Value takeSingle(Scope s, Value sep) {
						String[] strs = new String[arr.length()];
						for(int i = 0; i < arr.length(); i++){
							strs[i] = arr.get(i).get() + "";
						}
						return new DefaultSingleImpl(String.join(sep.get() + "", strs));
					}
				};
			}
		});
		global.put("ref", new SingleFunction() {
			@Override
			public Value takeSingle(Scope s, Value a) {
				return new DefaultSingleImpl(new Reference(a));
			}
		});
		global.put("unpack", new SingleFunction() {
			@Override
			public Value takeSingle(Scope s, Value a) {
				return ((Reference) a.get()).getValue();
			}
		});
		global.put(":=", new SingleOperator() {
			@Override
			public Value operateSingle(Scope s, Value a, Value b) {
				((Reference) b.get()).setValue(a);
				return a;
			}
		});
		
		//Java bridge
		global.put("java", new SingleFunction() {
			
			@Override
			public Value callZeroArg() {
				return this;
			}

			@Override
			public Value takeSingle(Scope s, Value a) {
				return new JavaClass(a.get() + "");
			}
		});
		global.put("field", new FieldFunction());
		global.put("constructor", new ConstructorFunction());
		global.put("method", new MethodFunction());
		global.put("/", new ArrayOpFunction());
		global.put("$", new SingleOperator() {
			@Override
			public Value operateSingle(Scope s, Value a, Value b) {
				Scope scope = s;
				String code = a.toString();
				String arg = b.toString();
				return new SingleFunction() {
					@Override
					public Value takeSingle(Scope s, Value v) {
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
		global.put("$$", new SingleOperator() {
			@Override
			public Value operateSingle(Scope s, Value a, Value b) {
				Scope scope = s;
				String code = a.toString();
				String arg = b.toString();
				return new ArrayFunction() {
					@Override
					public Value takeArray(Scope s, Array v) {
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
		global.put("'", new SingleOperator() {
			
			@Override
			public Value callZeroArg() {
				return this;
			}

			@Override
			public Value operateSingle(Scope s, Value a, Value b) {
				Scope scope = s;
				String code = a.toString();
				String arg = b.toString();
				return new Macro() {
					@Override
					public Value takeString(Scope s, String v) {
						Scope sc = scope.chain();
						sc.put(arg, new DefaultSingleImpl(v));
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
		global.put(":", new StoreConstFunction());
		global.put(":!", new StoreConstFunction2());
		global.put("jSerialize", new ArrayFunction() {
			@Override
			public Value takeArray(Scope s, Array a) {
				try {
					IOBase<?> buffer = IO.buffer();
					ObjectOutputStream stream = new ObjectOutputStream(buffer.writer());
					stream.writeObject(a);
					stream.close();
					return new DefaultSingleImpl(buffer);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		});
		global.put("jDeserialize", new SingleFunction() {
			@Override
			public Value takeSingle(Scope s, Value a) {
				try {
					ObjectInputStream ois = new ObjectInputStream(((IOBase<?>) a.get()).reader());
					Object o = ois.readObject();
					ois.close();
					return (Value) o;
				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
		});
		global.put("portListener", new SingleOperator() {
			@Override
			public Value operateSingle(Scope s, Value port, Value action) {
				Thread t = new Thread(){
					public void run(){
						ServerSocket socket;
						try {
							socket = new ServerSocket((int) port.castNumber());
						} catch (IOException e) {
							throw new RuntimeException(e);
						}
						while(true){
							try {
								Socket client = socket.accept();
								action.take(s, new DefaultSingleImpl(IO.clientSocket(client)));
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				};
				t.start();
				return new DefaultSingleImpl(t);
			}
		});
	}
	
}
