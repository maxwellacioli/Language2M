package syntactic.grammar;

public class GrammarSymbol {
	private Boolean isTerminal;
	private Integer value;

	public GrammarSymbol(Boolean isTerminal, Integer value) {
		this.isTerminal = isTerminal;
		this.value = value;
	}

	public boolean isTerminal() {
		return isTerminal;
	}

	public void setTerminal(Boolean isTerminal) {
		this.isTerminal = isTerminal;
	}

	public int getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
}
