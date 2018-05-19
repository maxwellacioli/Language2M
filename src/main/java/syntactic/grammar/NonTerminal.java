package syntactic.grammar;

public class NonTerminal extends Symbol {

	private NonTerminalName name;
	private String type;
	private int index;

	public NonTerminal(NonTerminalName name) {
		super(false, name.getNonTerminalValue());
		this.name = name;
	}

	public NonTerminalName getName() {
		return name;
	}
	
	public int getIndex() {
		return index;
	}
	
	public void setIndex(int index) {
		this.index = index;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
}
