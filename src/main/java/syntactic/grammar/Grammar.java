package syntactic.grammar;

import java.util.ArrayList;

import lexical.TokenCategory;

public class Grammar {
	
	private static Grammar grammarSingleton;
	private ArrayList<Derivation> grammarMap;
	private Derivation derivationAux;

	//TODO remover número mágico
	public static int EXPRESSION;
	
	private Grammar() { 
		
		grammarMap = new ArrayList<Derivation>();
		loadGrammar();
		
	}

    public static Grammar getInstance() {
        if (grammarSingleton == null ) {
        	grammarSingleton = new Grammar();
        }
        return grammarSingleton;
    }

	public ArrayList<Derivation> getGrammarMap() {
		return grammarMap;
	}

	private void loadGrammar() {
		
		// FUNCTIONS MAIN
		derivationAux = new Derivation();
		derivationAux.addDerivationSymbols(
				new NonTerminal(NonTerminalName.FUNCTIONS),
				new NonTerminal(NonTerminalName.MAIN));
		grammarAddDerivation(derivationAux);

		// principal ( ) inteiro ESCOPE
		derivationAux.addDerivationSymbols(
				new Terminal(TokenCategory.MAIN),
				new Terminal(TokenCategory.PARAMBEGIN),
				new Terminal(TokenCategory.PARAMEND),
				new Terminal(TokenCategory.TINT),
				new NonTerminal(NonTerminalName.ESCOPE));
		grammarAddDerivation(derivationAux);

		// FUNCTIONSREC
		derivationAux.addSymbol(new NonTerminal(NonTerminalName.FUNCTIONSREC));
		grammarAddDerivation(derivationAux);


		// id ( PARAMS ) TYPE ESCOPE FUNCTIONSREC
		derivationAux.addDerivationSymbols(
				new Terminal(TokenCategory.ID),
				new Terminal(TokenCategory.PARAMBEGIN),
				new NonTerminal(NonTerminalName.PARAMS),
				new Terminal(TokenCategory.PARAMEND),
				new NonTerminal(NonTerminalName.TYPE),
				new NonTerminal(NonTerminalName.ESCOPE),
				new NonTerminal(NonTerminalName.FUNCTIONSREC));
		grammarAddDerivation(derivationAux);

		// Epsilon
		grammarAddDerivation(null);

		// LISTPARAMS
		derivationAux.addSymbol(new NonTerminal(NonTerminalName.LISTPARAMS));
		grammarAddDerivation(derivationAux);

		// Epsilon
		grammarAddDerivation(null);

		// TYPE id LISTPARAMSREC
		derivationAux.addDerivationSymbols(
				new NonTerminal(NonTerminalName.TYPE),
				new Terminal(TokenCategory.ID),
				new NonTerminal(NonTerminalName.LISTPARAMSREC));
		grammarAddDerivation(derivationAux);

		// , TYPE id LISTPARAMSREC
		derivationAux.addDerivationSymbols(
				new Terminal(TokenCategory.SEP1),
				new NonTerminal(NonTerminalName.TYPE),
				new Terminal(TokenCategory.ID),
				new NonTerminal(NonTerminalName.LISTPARAMSREC));
		grammarAddDerivation(derivationAux);

		// Epsilon
		grammarAddDerivation(null);
		
		// inteiro
		derivationAux.addSymbol(new Terminal(TokenCategory.TINT));		
		grammarAddDerivation(derivationAux);
		
		// logico
		derivationAux.addSymbol(new Terminal(TokenCategory.TLOGIC));		
		grammarAddDerivation(derivationAux);

		// letra
		derivationAux.addSymbol(new Terminal(TokenCategory.TCHAR));
		grammarAddDerivation(derivationAux);

		// texto
		derivationAux.addSymbol(new Terminal(TokenCategory.TCCHAR));		
		grammarAddDerivation(derivationAux);

		// real
		derivationAux.addSymbol(new Terminal(TokenCategory.TREAL));
		grammarAddDerivation(derivationAux);

		// { CMDS }
		derivationAux.addDerivationSymbols(
				new Terminal(TokenCategory.ESCBEGIN),
				new NonTerminal(NonTerminalName.CMDS),
				new Terminal(TokenCategory.ESCEND));
		grammarAddDerivation(derivationAux);

		// CMD CMDSREC
		derivationAux.addDerivationSymbols(
				new NonTerminal(NonTerminalName.CMD),
				new NonTerminal(NonTerminalName.CMDSREC));
		grammarAddDerivation(derivationAux);

		// CMD CMDSREC
		derivationAux.addDerivationSymbols(
				new NonTerminal(NonTerminalName.CMD),
				new NonTerminal(NonTerminalName.CMDSREC));
		grammarAddDerivation(derivationAux);

		// Epsilon
		grammarAddDerivation(null);

		// TYPE LISTNAME ;
		derivationAux.addDerivationSymbols(
				new NonTerminal(NonTerminalName.TYPE),
				new NonTerminal(NonTerminalName.LISTNAME),
				new Terminal(TokenCategory.SEP2));
		grammarAddDerivation(derivationAux);

		// ATTRIB ;
		derivationAux.addDerivationSymbols(
				new NonTerminal(NonTerminalName.ATTRIB),
				new Terminal(TokenCategory.SEP2));
		grammarAddDerivation(derivationAux);

		// imprima ( EXP ) ;
		derivationAux.addDerivationSymbols(
				new Terminal(TokenCategory.PRPRINTOUT),
				new Terminal(TokenCategory.PARAMBEGIN),
				new NonTerminal(NonTerminalName.EXP),
				new Terminal(TokenCategory.PARAMEND),
				new Terminal(TokenCategory.SEP2));
		grammarAddDerivation(derivationAux);

		// leia ( id ) ;
		derivationAux.addDerivationSymbols(
				new Terminal(TokenCategory.PRREADIN),
				new Terminal(TokenCategory.PARAMBEGIN),
				new Terminal(TokenCategory.ID),
				new Terminal(TokenCategory.PARAMEND),
				new Terminal(TokenCategory.SEP2));
		grammarAddDerivation(derivationAux);

		// se ( EXP ) ESCOPE CMDELSE
		derivationAux.addDerivationSymbols(
				new Terminal(TokenCategory.PRIF),
				new Terminal(TokenCategory.PARAMBEGIN),
				new NonTerminal(NonTerminalName.EXP),
				new Terminal(TokenCategory.PARAMEND),
				new NonTerminal(NonTerminalName.ESCOPE),
				new NonTerminal(NonTerminalName.IFELSEFAT),
				new Terminal(TokenCategory.SEP2));
		grammarAddDerivation(derivationAux);

		// enquanto ( EXP ) ESCOPE ;
		derivationAux.addDerivationSymbols(
				new Terminal(TokenCategory.PRWHILE),
				new Terminal(TokenCategory.PARAMBEGIN),
				new NonTerminal(NonTerminalName.EXP),
				new Terminal(TokenCategory.PARAMEND),
				new NonTerminal(NonTerminalName.ESCOPE),
				new Terminal(TokenCategory.SEP2));
		grammarAddDerivation(derivationAux);

		// faca ESCOPE enquanto ( EXP ) ;
		derivationAux.addDerivationSymbols(
				new Terminal(TokenCategory.PRDO),
				new NonTerminal(NonTerminalName.ESCOPE),
				new Terminal(TokenCategory.PRWHILE),
				new Terminal(TokenCategory.PARAMBEGIN),
				new NonTerminal(NonTerminalName.EXP),
				new Terminal(TokenCategory.PARAMEND),
				new Terminal(TokenCategory.SEP2));
		grammarAddDerivation(derivationAux);

		// repita ( ATTRIB ; EXP ; ATTRIB ) ESCOPE ;
		derivationAux.addDerivationSymbols(
				new Terminal(TokenCategory.PRITERATOR),
				new Terminal(TokenCategory.PARAMBEGIN),
				new NonTerminal(NonTerminalName.ATTRIB),
				new Terminal(TokenCategory.SEP2),
				new NonTerminal(NonTerminalName.EXP),
				new Terminal(TokenCategory.SEP2),
				new NonTerminal(NonTerminalName.ATTRIB),
				new Terminal(TokenCategory.PARAMEND),
				new NonTerminal(NonTerminalName.ESCOPE),
				new Terminal(TokenCategory.SEP2));
		grammarAddDerivation(derivationAux);

		// retorne EXP ;
		derivationAux.addDerivationSymbols(
				new Terminal(TokenCategory.PRRETURN),
				new NonTerminal(NonTerminalName.EXP),
				new Terminal(TokenCategory.SEP2));
		grammarAddDerivation(derivationAux);

		// senao ESCOPE
		derivationAux.addDerivationSymbols(
				new Terminal(TokenCategory.PRELSE),
				new NonTerminal(NonTerminalName.ESCOPE));
		grammarAddDerivation(derivationAux);

		// Epsilon
		grammarAddDerivation(null);


		// id = EXP
		derivationAux.addDerivationSymbols(
				new Terminal(TokenCategory.ID),
				new Terminal(TokenCategory.OPATRIB),
				new NonTerminal(NonTerminalName.EXP));
		grammarAddDerivation(derivationAux);

		// id LISTNAMEREC
		derivationAux.addDerivationSymbols(
				new Terminal(TokenCategory.ID),
				new NonTerminal(NonTerminalName.LISTNAMEREC));
		grammarAddDerivation(derivationAux);

		// , id LISTNAMEREC
		derivationAux.addDerivationSymbols(
				new Terminal(TokenCategory.SEP1),
				new Terminal(TokenCategory.ID),
				new NonTerminal(NonTerminalName.LISTNAMEREC));
		grammarAddDerivation(derivationAux);

		// Epsilon
		grammarAddDerivation(null);

		// EXP
		derivationAux.addSymbol(new NonTerminal(NonTerminalName.EXP));
		grammarAddDerivation(derivationAux);

		EXPRESSION = grammarMap.size() - 1;
	}

	private void grammarAddDerivation(Derivation derivation) {		
		grammarMap.add(derivation);		
		if(derivation != null) {
			derivationAux = new Derivation();
		}
	}

	//De acordo com a documentacao da gramatica as derivacoes que
	//tem acoes semanticas sao: 0-4 e 15-32
	public boolean hasSemantincAction(int derivation) {
		if(derivation >= 0 && derivation <= 4 || derivation >= 15 && derivation <= 32) {
			return true;
		}
		return false;
	}
}
