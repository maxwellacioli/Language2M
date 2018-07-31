package syntactic.grammar;

import semantic.VarType;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class NonTerminal extends GrammarSymbol {

	private NonTerminalName name;
	private VarType type;
	private int index;

	public NonTerminal(NonTerminalName name) {
		super(false, name.getValue());
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
