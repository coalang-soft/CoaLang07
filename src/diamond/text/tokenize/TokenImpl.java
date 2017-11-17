package diamond.text.tokenize;

public class TokenImpl implements Token {

	private String string;

	public TokenImpl(String s) {
		this.string = s;
	}
	
	public String raw(){
		return string;
	}
	
	public String toString(){
		return raw();
	}

}
