package syntactic.grammar;

import java.util.ArrayList;

public class Derivation implements Cloneable {

	private ArrayList<GrammarSymbol> symbolsList;

	public ArrayList<GrammarSymbol> getSymbolsList() {
		return symbolsList;
	}

	public Derivation() {
		symbolsList = new ArrayList<GrammarSymbol>();
	}

	@Override
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}

	private void setSymbolsList(ArrayList<GrammarSymbol> symbolsList) {
		this.symbolsList = symbolsList;
	}

	public void clearDerivationList() {
		symbolsList.clear();
	}

	public void addSymbol(GrammarSymbol symb1) {
		symbolsList.add(symb1);
	}

	public void addDerivationSymbols(GrammarSymbol symb1, GrammarSymbol symb2) {
		addSymbol(symb1);
		symbolsList.add(symb2);

	}

	public void addDerivationSymbols(GrammarSymbol symb1, GrammarSymbol symb2, GrammarSymbol symb3) {
		addDerivationSymbols(symb1, symb2);
		symbolsList.add(symb3);
	}

	public void addDerivationSymbols(GrammarSymbol symb1, GrammarSymbol symb2, GrammarSymbol symb3,
                                     GrammarSymbol symb4) {
		addDerivationSymbols(symb1, symb2, symb3);
		symbolsList.add(symb4);
	}

	public void addDerivationSymbols(GrammarSymbol symb1, GrammarSymbol symb2, GrammarSymbol symb3,
                                     GrammarSymbol symb4, GrammarSymbol symb5) {
		addDerivationSymbols(symb1, symb2, symb3, symb4);
		symbolsList.add(symb5);
	}

	public void addDerivationSymbols(GrammarSymbol symb1, GrammarSymbol symb2, GrammarSymbol symb3,
                                     GrammarSymbol symb4, GrammarSymbol symb5, GrammarSymbol symb6) {
		addDerivationSymbols(symb1, symb2, symb3, symb4, symb5);
		symbolsList.add(symb6);
	}

	public void addDerivationSymbols(GrammarSymbol symb1, GrammarSymbol symb2, GrammarSymbol symb3,
                                     GrammarSymbol symb4, GrammarSymbol symb5, GrammarSymbol symb6, GrammarSymbol symb7) {
		addDerivationSymbols(symb1, symb2, symb3, symb4, symb5, symb6);
		symbolsList.add(symb7);
	}

	public void addDerivationSymbols(GrammarSymbol symb1, GrammarSymbol symb2, GrammarSymbol symb3,
                                     GrammarSymbol symb4, GrammarSymbol symb5, GrammarSymbol symb6, GrammarSymbol symb7, GrammarSymbol symb8) {
		addDerivationSymbols(symb1, symb2, symb3, symb4, symb5, symb6, symb7);
		symbolsList.add(symb8);
	}

	public void addDerivationSymbols(GrammarSymbol symb1, GrammarSymbol symb2, GrammarSymbol symb3,
									 GrammarSymbol symb4, GrammarSymbol symb5, GrammarSymbol symb6, GrammarSymbol symb7,
									 GrammarSymbol symb8, GrammarSymbol symb9, GrammarSymbol symb10) {
		addDerivationSymbols(symb1, symb2, symb3, symb4, symb5, symb6, symb7);
		symbolsList.add(symb8);
		symbolsList.add(symb9);
		symbolsList.add(symb10);
	}

	public boolean isEmpty() {
		return symbolsList.isEmpty();
	}
}
