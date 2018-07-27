package syntactic.grammar;

import java.util.ArrayList;
import java.util.List;
import lexical.TokenCategory;

public class OperatorsGrammar {
	private List<Derivation> operatorGrammarArray;
	private List<TokenCategory> operatorsGrammarSymbols;
	private static OperatorsGrammar operatorsGrammarSingleton;

	private OperatorsGrammar() {
		operatorGrammarArray = new ArrayList<Derivation>();
		operatorsGrammarSymbols = new ArrayList<TokenCategory>();

		loadOperatorGrammar();
		loadOperatorsGrammarSymbols();
	}

	public List<TokenCategory> getOperatorsGrammarSymbols() {
		return operatorsGrammarSymbols;
	}

	private void loadOperatorsGrammarSymbols() {
		operatorsGrammarSymbols.add(TokenCategory.OPARITADIT);// 0
		operatorsGrammarSymbols.add(TokenCategory.OPCONC);// 1
		operatorsGrammarSymbols.add(TokenCategory.OPARITMULT);// 2
		operatorsGrammarSymbols.add(TokenCategory.OPARITEXP);// 3
		operatorsGrammarSymbols.add(TokenCategory.OPNEGUN);// 4
		operatorsGrammarSymbols.add(TokenCategory.OPNEGLOGIC);// 5
		operatorsGrammarSymbols.add(TokenCategory.PARAMBEGIN);// 6
		operatorsGrammarSymbols.add(TokenCategory.PARAMEND);// 7
		operatorsGrammarSymbols.add(TokenCategory.OPLOGICOR);// 8
		operatorsGrammarSymbols.add(TokenCategory.OPLOGICAND);// 9
		operatorsGrammarSymbols.add(TokenCategory.OPREL1);// 10
		operatorsGrammarSymbols.add(TokenCategory.OPREL2);// 11
		operatorsGrammarSymbols.add(TokenCategory.CONSTNUMINT);// 12
		operatorsGrammarSymbols.add(TokenCategory.CONSTNUMREAL);// 13
		operatorsGrammarSymbols.add(TokenCategory.CONSTLOGIC);// 14
		operatorsGrammarSymbols.add(TokenCategory.CONSTCHAR);// 15
		operatorsGrammarSymbols.add(TokenCategory.CONSTCCHAR);// 16
		operatorsGrammarSymbols.add(TokenCategory.ID);// 17
		operatorsGrammarSymbols.add(TokenCategory.ARRAYBEGIN);// 18
		operatorsGrammarSymbols.add(TokenCategory.ARRAYEND);// 19
		operatorsGrammarSymbols.add(TokenCategory.SEP1);// 20
	}

	public static OperatorsGrammar getInstance() {
		if (operatorsGrammarSingleton == null) {
			operatorsGrammarSingleton = new OperatorsGrammar();
		}
		return operatorsGrammarSingleton;
	}

	public Derivation getOperatorDerivation(int index) {
		return operatorGrammarArray.get(index);
	}

	private void loadOperatorGrammar() {
		Derivation operatorDerivation = new Derivation();

		// (0)EXP = EXP OPLOGICOR EXP
		operatorDerivation
				.addSymbol(new NonTerminal(NonTerminalName.EXP));
		operatorDerivation.addSymbol(new Terminal(TokenCategory.OPLOGICOR));
		operatorDerivation.addSymbol(new Terminal(TokenCategory.OPLOGICOR));
		operatorDerivation
				.addSymbol(new NonTerminal(NonTerminalName.EXP));
		operatorGrammarArray.add(operatorDerivation);

		// (1)EXP = EXP OPLOGICAND EXP
		operatorDerivation = new Derivation();
		operatorDerivation
				.addSymbol(new NonTerminal(NonTerminalName.EXP));
		operatorDerivation.addSymbol(new Terminal(TokenCategory.OPLOGICAND));
		operatorDerivation
				.addSymbol(new NonTerminal(NonTerminalName.EXP));
		operatorGrammarArray.add(operatorDerivation);

		// (2)EXP = EXP OPREL2 EXP
		operatorDerivation = new Derivation();
		operatorDerivation
				.addSymbol(new NonTerminal(NonTerminalName.EXP));
		operatorDerivation.addSymbol(new Terminal(TokenCategory.OPREL2));
		operatorDerivation
				.addSymbol(new NonTerminal(NonTerminalName.EXP));
		operatorGrammarArray.add(operatorDerivation);

		// (3)EXP = EXP OPREL1 EXP
		operatorDerivation = new Derivation();
		operatorDerivation
				.addSymbol(new NonTerminal(NonTerminalName.EXP));
		operatorDerivation.addSymbol(new Terminal(TokenCategory.OPREL1));
		operatorDerivation
				.addSymbol(new NonTerminal(NonTerminalName.EXP));
		operatorGrammarArray.add(operatorDerivation);

		// (4)EXP = EXP OPARITADIT EXP
		operatorDerivation = new Derivation();
		operatorDerivation
				.addSymbol(new NonTerminal(NonTerminalName.EXP));
		operatorDerivation.addSymbol(new Terminal(TokenCategory.OPCONC));
		operatorDerivation
				.addSymbol(new NonTerminal(NonTerminalName.EXP));
		operatorGrammarArray.add(operatorDerivation);

		// (5)EXP = EXP OPARITADIT EXP
		operatorDerivation = new Derivation();
		operatorDerivation
				.addSymbol(new NonTerminal(NonTerminalName.EXP));
		operatorDerivation.addSymbol(new Terminal(TokenCategory.OPARITADIT));
		operatorDerivation
				.addSymbol(new NonTerminal(NonTerminalName.EXP));
		operatorGrammarArray.add(operatorDerivation);

		// (6)EXP = EXP OPARITMULT EXP
		operatorDerivation = new Derivation();
		operatorDerivation
				.addSymbol(new NonTerminal(NonTerminalName.EXP));
		operatorDerivation.addSymbol(new Terminal(TokenCategory.OPARITMULT));
		operatorDerivation
				.addSymbol(new NonTerminal(NonTerminalName.EXP));
		operatorGrammarArray.add(operatorDerivation);

		// (7)EXP = EXP OPNEGUN EXP
		operatorDerivation = new Derivation();
		operatorDerivation.addSymbol(new Terminal(TokenCategory.OPNEGUN));
		operatorDerivation
				.addSymbol(new NonTerminal(NonTerminalName.EXP));
		operatorGrammarArray.add(operatorDerivation);

		// (8)EXP = EXP OPNEGLOGIC EXP
		operatorDerivation = new Derivation();
		operatorDerivation.addSymbol(new Terminal(TokenCategory.OPNEGLOGIC));
		operatorDerivation
				.addSymbol(new NonTerminal(NonTerminalName.EXP));
		operatorGrammarArray.add(operatorDerivation);

		// (9)EXP = EXP OPARITEXP EXP
		operatorDerivation = new Derivation();
		operatorDerivation
				.addSymbol(new NonTerminal(NonTerminalName.EXP));
		operatorDerivation.addSymbol(new Terminal(TokenCategory.OPARITEXP));
		operatorDerivation
				.addSymbol(new NonTerminal(NonTerminalName.EXP));
		operatorGrammarArray.add(operatorDerivation);

		// (10)EXP = PARAMBEGIN EXP PARAMEND
		operatorDerivation = new Derivation();
		operatorDerivation.addSymbol(new Terminal(TokenCategory.PARAMBEGIN));
		operatorDerivation
				.addSymbol(new NonTerminal(NonTerminalName.EXP));
		operatorDerivation.addSymbol(new Terminal(TokenCategory.PARAMEND));
		operatorGrammarArray.add(operatorDerivation);

		// (11)EXP = CONSTNUMINT
		operatorDerivation = new Derivation();
		operatorDerivation.addSymbol(new Terminal(TokenCategory.CONSTNUMINT));
		operatorGrammarArray.add(operatorDerivation);

		// (12)EXP = CONSTNUMREAL
		operatorDerivation = new Derivation();
		operatorDerivation.addSymbol(new Terminal(TokenCategory.CONSTNUMREAL));
		operatorGrammarArray.add(operatorDerivation);

		// (13)EXP = CONSTLOGIC
		operatorDerivation = new Derivation();
		operatorDerivation.addSymbol(new Terminal(TokenCategory.CONSTLOGIC));
		operatorGrammarArray.add(operatorDerivation);

		// (14)EXP = CONSTCHAR
		operatorDerivation = new Derivation();
		operatorDerivation.addSymbol(new Terminal(TokenCategory.CONSTCHAR));
		operatorGrammarArray.add(operatorDerivation);

		// (15)EXP = CONSTCCHAR
		operatorDerivation = new Derivation();
		operatorDerivation.addSymbol(new Terminal(TokenCategory.CONSTCCHAR));
		operatorGrammarArray.add(operatorDerivation);

		// (16)EXP = ID
		operatorDerivation = new Derivation();
		operatorDerivation.addSymbol(new Terminal(TokenCategory.ID));
		operatorGrammarArray.add(operatorDerivation);

		// (17)EXP = ID ARRAYBEGIN EXP ARRAYEND
		operatorDerivation = new Derivation();
		operatorDerivation.addSymbol(new Terminal(TokenCategory.ID));
		operatorDerivation.addSymbol(new Terminal(TokenCategory.ARRAYBEGIN));
		operatorDerivation
				.addSymbol(new NonTerminal(NonTerminalName.EXP));
		operatorDerivation.addSymbol(new Terminal(TokenCategory.ARRAYEND));
		operatorGrammarArray.add(operatorDerivation);

		// (18)EXP = ID PARAMBEGIN EXP PARAMEND
		operatorDerivation = new Derivation();
		operatorDerivation.addSymbol(new Terminal(TokenCategory.ID));
		operatorDerivation.addSymbol(new Terminal(TokenCategory.PARAMBEGIN));
		operatorDerivation
				.addSymbol(new NonTerminal(NonTerminalName.EXP));
		operatorDerivation.addSymbol(new Terminal(TokenCategory.PARAMEND));
		operatorGrammarArray.add(operatorDerivation);

		// (19)EXP = EXP SEP1 EXP
		operatorDerivation = new Derivation();
		operatorDerivation
				.addSymbol(new NonTerminal(NonTerminalName.EXP));
		operatorDerivation.addSymbol(new Terminal(TokenCategory.SEP1));
		operatorDerivation
				.addSymbol(new NonTerminal(NonTerminalName.EXP));
		operatorGrammarArray.add(operatorDerivation);
	}

}
