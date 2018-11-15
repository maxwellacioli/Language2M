package lexical;

public class Token {
	
	private String lexValue;
	private TokenCategory category;
	private int line;
	private int column;

	public Token(TokenCategory tokenCategory, String lexValue) {
		this.category = tokenCategory;
		this.lexValue = lexValue;
	}

	public Token() { }
	
	@Override
	public String toString() {
		return "<" + line + "," + column + "> " + category + " = '" + lexValue + "'";
	}

	public String getLocation() {
		return "<linha: " + line + ", " + "coluna: "+ column + ">";
	}

	public String getLexValue() {
		return lexValue;
	}

	public void setLexValue(String lexValue) {
		this.lexValue = lexValue;
	}

	public TokenCategory getCategory() {
		return category;
	}

	public void setCategory(TokenCategory category) {
		this.category = category;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

}
