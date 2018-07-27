package syntactic.grammar;

import lexical.Token;
import lexical.TokenCategory;

public class Terminal extends GrammarSymbol implements Cloneable {

	private TokenCategory category;
	private Token token;

	public Terminal(TokenCategory category, String value) {

		super(true, category.getValue());
		this.category = category;
		token = new Token();
	}

	public Terminal(TokenCategory category) {

		super(true, category.getValue());
		this.category = category;
		token = null;
	}

	public Terminal(Token token) {
		super(true, token.getCategory().getValue());
		this.token = token;
		this.category = token.getCategory();
	}

	@Override
	protected Object clone()  {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return  null;
		}
	}

	public TokenCategory getCategory() {
		return category;
	}

	public String getTerminalValue() {
		return token.getLexValue();
	}

	public void setToken(Token token) {
		this.token = token;
	}

	public Token getToken() {
		return token;
	}

	public Token getTerminalToken() {
		return token;
	}
}
