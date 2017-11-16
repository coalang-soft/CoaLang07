package diamond.parse;

public class TokenImpl implements Token {

	private String string;

	public TokenImpl(String s) {
		this.string = s;
	}
	
	public String toString(){
		return string;
	}

}
