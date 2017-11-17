package diamond.text.interpret;

public enum TokenCategory {
	STR_OPEN("["), STR_CLOSE("]"), BRACKET_OPEN("("), BRACKET_CLOSE(")"),
	COLON(":"),
	SEMICOLON(";"),
	
	NUMBER, LIST_OPERATION, NAME;

	private String look;

	private TokenCategory(){
		
	}
	private TokenCategory(String look){
		this.look = look;
	}
	
	public static TokenCategory compute(String raw) {
		TokenCategory[] vals = values();
		for(int i = 0; i < vals.length; i++){
			if(vals[i].look != null){
				if(vals[i].look.equals(raw)){
					return vals[i];
				}
			}
		}
		if(Character.isDigit(raw.charAt(0)) || raw.charAt(0) == '-'){
			return NUMBER;
		}
		if(raw.charAt(0) == '/'){
			return TokenCategory.LIST_OPERATION;
		}
		return NAME;
	}

}
