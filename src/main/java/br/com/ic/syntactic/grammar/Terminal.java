package br.com.ic.syntactic.grammar;

import br.com.ic.lexical.Token;
import br.com.ic.lexical.TokenCategory;

public class Terminal extends Symbol {

	private TokenCategory category;
	private Token token;

	public Terminal(TokenCategory category) {

		super(true, category.getCategoryValue());
		this.category = category;
		token = null;
	}
	
	public Terminal(TokenCategory category, String value) {

		super(true, category.getCategoryValue());
		this.category = category;
		token = new Token();;
	}

	public Terminal(Token token) {
		super(true, token.getCategory().getCategoryValue());
		this.token = token;
		this.category = token.getCategory();
	}

	public TokenCategory getCategory() {
		return category;
	}

	public String getTerminalValue() {
		return token.getValue();
	}

	public void setTerminalValue(String value) {
		token.setValue(value);
	}

	public Token getTerminalToken() {
		return token;
	}
}
