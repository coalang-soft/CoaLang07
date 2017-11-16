package diamond.parse;

import java.io.IOException;
import java.io.InputStream;

public class StreamTokenReader {
	
	private Tokenizer tokenizer;
	private TokenHandler handler;

	public StreamTokenReader(Tokenizer tokenizer, TokenHandler handler){
		this.tokenizer = tokenizer;
		this.handler = handler;
	}
	
	public void handle(InputStream s) throws IOException{
		int i;
		while((i = s.read()) != -1){
			Token t = tokenizer.feed((char) i);
			if(t != null){
				handler.handle(t);
			}
		}
	}
	
}
