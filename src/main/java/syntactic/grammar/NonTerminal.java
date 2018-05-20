package syntactic.grammar;

import semantic.VarType;

public class NonTerminal extends Symbol {

	private NonTerminalName name;
	private VarType type;
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
	
	public VarType getType() {
		return type;
	}
	
	public void setType(VarType type) {
		this.type = type;
	}
}
