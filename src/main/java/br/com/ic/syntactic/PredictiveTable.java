package br.com.ic.syntactic;

import java.util.HashMap;
import br.com.ic.lexical.TokenCategory;
import br.com.ic.syntactic.grammar.NonTerminalName;

public class PredictiveTable {
	private HashMap<NonTerminalName, HashMap<TokenCategory, Integer>> predectiveTableMap;
	private HashMap<TokenCategory, Integer> terminaisMap;

	public PredictiveTable() {
		terminaisMap = new HashMap<TokenCategory, Integer>();
		predectiveTableMap = new HashMap<NonTerminalName, HashMap<TokenCategory, Integer>>();

		loadPredectiveTableMap();
	}

	public Integer getDerivationNumber(NonTerminalName nonTerminal,
			TokenCategory terminal) {
		return predectiveTableMap.get(nonTerminal).get(terminal);
	}

	// Tabela preditiva
	private void loadPredectiveTableMap() {

		terminaisMap.put(TokenCategory.MAJOR, 0);
		terminaisMap.put(TokenCategory.ID, 0);
		predectiveTableMap.put(NonTerminalName.PROGRAM, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.ID, 1);
		terminaisMap.put(TokenCategory.MAJOR, 2);
		predectiveTableMap.put(NonTerminalName.FUNCTIONS, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.MAJOR, 3);
		predectiveTableMap.put(NonTerminalName.MAJORF, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.PARAMBEGIN, 4);
		predectiveTableMap.put(NonTerminalName.PARAMS, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.PARAMEND, 5);
		terminaisMap.put(TokenCategory.TINT, 6);
		terminaisMap.put(TokenCategory.TLONG, 6);
		terminaisMap.put(TokenCategory.TLOGIC, 6);
		terminaisMap.put(TokenCategory.TCHAR, 6);
		terminaisMap.put(TokenCategory.TCCHAR, 6);
		terminaisMap.put(TokenCategory.TDEC, 6);
		predectiveTableMap.put(NonTerminalName.PARAMSFAT, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.TINT, 7);
		terminaisMap.put(TokenCategory.TLONG, 7);
		terminaisMap.put(TokenCategory.TLOGIC, 7);
		terminaisMap.put(TokenCategory.TCHAR, 7);
		terminaisMap.put(TokenCategory.TCCHAR, 7);
		terminaisMap.put(TokenCategory.TDEC, 7);
		predectiveTableMap.put(NonTerminalName.LISTPARAMS, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.SEP1, 8);
		terminaisMap.put(TokenCategory.PARAMEND, 9);
		predectiveTableMap.put(NonTerminalName.LISTPARAMSFAT, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.TINT, 10);
		terminaisMap.put(TokenCategory.TLONG, 11);
		terminaisMap.put(TokenCategory.TLOGIC, 12);
		terminaisMap.put(TokenCategory.TCHAR, 13);
		terminaisMap.put(TokenCategory.TCCHAR, 14);
		terminaisMap.put(TokenCategory.TDEC, 15);
		predectiveTableMap.put(NonTerminalName.TYPE, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.TEMPTY, 16);
		terminaisMap.put(TokenCategory.TINT, 17);
		terminaisMap.put(TokenCategory.TLONG, 17);
		terminaisMap.put(TokenCategory.TLOGIC, 17);
		terminaisMap.put(TokenCategory.TCHAR, 17);
		terminaisMap.put(TokenCategory.TCCHAR, 17);
		terminaisMap.put(TokenCategory.TDEC, 17);
		predectiveTableMap.put(NonTerminalName.RETURNTYPE, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.ESCBEGIN, 19);
		terminaisMap.put(TokenCategory.ARRAYBEGIN, 18);
		predectiveTableMap.put(NonTerminalName.RETURNTYPEFAT, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.ID, 20);
		predectiveTableMap.put(NonTerminalName.NAME, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.PARAMEND, 22);
		terminaisMap.put(TokenCategory.ARRAYBEGIN, 21);
		terminaisMap.put(TokenCategory.TERM, 22);
		terminaisMap.put(TokenCategory.SEP1, 22);
		terminaisMap.put(TokenCategory.OPATRIB, 22);
		terminaisMap.put(TokenCategory.OPCONC, 22);
		predectiveTableMap.put(NonTerminalName.NAMEFAT, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.ESCBEGIN, 23);
		predectiveTableMap.put(NonTerminalName.ESCOPE, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.ID, 24);
		terminaisMap.put(TokenCategory.TINT, 24);
		terminaisMap.put(TokenCategory.TLONG, 24);
		terminaisMap.put(TokenCategory.TLOGIC, 24);
		terminaisMap.put(TokenCategory.TCHAR, 24);
		terminaisMap.put(TokenCategory.TCCHAR, 24);
		terminaisMap.put(TokenCategory.TDEC, 24);
		terminaisMap.put(TokenCategory.ESCEND, 25);
		terminaisMap.put(TokenCategory.PRREADIN, 24);
		terminaisMap.put(TokenCategory.PRPRINTOUT, 24);
		terminaisMap.put(TokenCategory.PRIF, 24);
		terminaisMap.put(TokenCategory.PRITERATOR, 24);
		terminaisMap.put(TokenCategory.PRDO, 24);
		terminaisMap.put(TokenCategory.PRWHILE, 24);
		terminaisMap.put(TokenCategory.PRRETURN, 24);
		predectiveTableMap.put(NonTerminalName.COMMANDS, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.ID, 27);
		terminaisMap.put(TokenCategory.TINT, 26);
		terminaisMap.put(TokenCategory.TLONG, 26);
		terminaisMap.put(TokenCategory.TLOGIC, 26);
		terminaisMap.put(TokenCategory.TCHAR, 26);
		terminaisMap.put(TokenCategory.TCCHAR, 26);
		terminaisMap.put(TokenCategory.TDEC, 26);
		terminaisMap.put(TokenCategory.PRREADIN, 29);
		terminaisMap.put(TokenCategory.PRPRINTOUT, 28);
		terminaisMap.put(TokenCategory.PRIF, 30);
		terminaisMap.put(TokenCategory.PRITERATOR, 33);
		terminaisMap.put(TokenCategory.PRDO, 32);
		terminaisMap.put(TokenCategory.PRWHILE, 31);
		terminaisMap.put(TokenCategory.PRRETURN, 29);
		predectiveTableMap.put(NonTerminalName.CMD, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.TINT, 35);
		terminaisMap.put(TokenCategory.TLONG, 35);
		terminaisMap.put(TokenCategory.TLOGIC, 35);
		terminaisMap.put(TokenCategory.TCHAR, 35);
		terminaisMap.put(TokenCategory.TCCHAR, 35);
		terminaisMap.put(TokenCategory.TDEC, 35);
		predectiveTableMap.put(NonTerminalName.DECLARATION, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.SEP1, 36);
		terminaisMap.put(TokenCategory.OPATRIB, 37);
		terminaisMap.put(TokenCategory.ARRAYBEGIN, 37);
		terminaisMap.put(TokenCategory.TERM, 38);
		predectiveTableMap.put(NonTerminalName.VARIABLE, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.PARAMBEGIN, 40);
		terminaisMap.put(TokenCategory.ARRAYBEGIN, 39);
		terminaisMap.put(TokenCategory.OPATRIB, 39);
		predectiveTableMap.put(NonTerminalName.CMDFAT, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.ARRAYBEGIN, 41);
		terminaisMap.put(TokenCategory.OPATRIB, 41);
		predectiveTableMap.put(NonTerminalName.ATTRIBUTION, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.ARRAYBEGIN, 42);
		predectiveTableMap.put(NonTerminalName.VALUE, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.ARRAYBEGIN, 43);
		predectiveTableMap.put(NonTerminalName.ARRAY, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.ARRAYEND, 45);
		terminaisMap.put(TokenCategory.CONSTNUMINT, 44);
		terminaisMap.put(TokenCategory.CONSTNUMDEC, 44);
		terminaisMap.put(TokenCategory.CONSTLOGIC, 44);
		terminaisMap.put(TokenCategory.CONSTCHAR, 44);
		terminaisMap.put(TokenCategory.CONSTCCHAR, 44);
		predectiveTableMap.put(NonTerminalName.ARRAYFAT, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.CONSTNUMINT, 46);
		terminaisMap.put(TokenCategory.CONSTNUMDEC, 46);
		terminaisMap.put(TokenCategory.CONSTLOGIC, 46);
		terminaisMap.put(TokenCategory.CONSTCHAR, 46);
		terminaisMap.put(TokenCategory.CONSTCCHAR, 46);
		predectiveTableMap.put(NonTerminalName.ELEMENTS, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.ARRAYEND, 48);
		terminaisMap.put(TokenCategory.SEP1, 47);
		predectiveTableMap.put(NonTerminalName.ELEMENTSFAT, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.CONSTNUMINT, 49);
		terminaisMap.put(TokenCategory.CONSTNUMDEC, 50);
		terminaisMap.put(TokenCategory.CONSTLOGIC, 51);
		terminaisMap.put(TokenCategory.CONSTCHAR, 52);
		terminaisMap.put(TokenCategory.CONSTCCHAR, 53);
		predectiveTableMap.put(NonTerminalName.CONSTANT, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.PARAMBEGIN, 54);
		predectiveTableMap.put(NonTerminalName.FUNCCALL, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.ID, 55);
		terminaisMap.put(TokenCategory.CONSTNUMINT, 55);
		terminaisMap.put(TokenCategory.CONSTNUMDEC, 55);
		terminaisMap.put(TokenCategory.CONSTLOGIC, 55);
		terminaisMap.put(TokenCategory.CONSTCHAR, 55);
		terminaisMap.put(TokenCategory.CONSTCCHAR, 55);
		predectiveTableMap.put(NonTerminalName.LISTPARAMSCALL, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.PARAMEND, 57);
		terminaisMap.put(TokenCategory.SEP1, 56);
		predectiveTableMap.put(NonTerminalName.LISTPARAMSCALLFAT, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.ID, 59);
		terminaisMap.put(TokenCategory.CONSTNUMINT, 58);
		terminaisMap.put(TokenCategory.CONSTNUMDEC, 58);
		terminaisMap.put(TokenCategory.CONSTLOGIC, 58);
		terminaisMap.put(TokenCategory.CONSTCHAR, 58);
		terminaisMap.put(TokenCategory.CONSTCCHAR, 58);
		predectiveTableMap.put(NonTerminalName.PARAMITEM, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.PRPRINTOUT, 60);
		predectiveTableMap.put(NonTerminalName.PRINTOUT, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.ID, 62);
		terminaisMap.put(TokenCategory.CONSTCCHAR, 61);
		predectiveTableMap.put(NonTerminalName.MESSAGE, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.PARAMEND, 64);
		terminaisMap.put(TokenCategory.OPCONC, 63);
		predectiveTableMap.put(NonTerminalName.MESSAGEFAT, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.PRREADIN, 65);
		predectiveTableMap.put(NonTerminalName.READIN, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.PRIF, 66);
		predectiveTableMap.put(NonTerminalName.IFELSE, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.PRIF, 67);
		predectiveTableMap.put(NonTerminalName.IF, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.PRELSEIF, 68);
		terminaisMap.put(TokenCategory.PRELSE, 69);
		terminaisMap.put(TokenCategory.TERM, 69);
		predectiveTableMap.put(NonTerminalName.ELSEIF, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.PRELSE, 70);
		terminaisMap.put(TokenCategory.TERM, 71);
		predectiveTableMap.put(NonTerminalName.ELSE, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.PRWHILE, 72);
		predectiveTableMap.put(NonTerminalName.WHILE, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.PRDO, 73);
		predectiveTableMap.put(NonTerminalName.DOWHILE, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.PRITERATOR, 74);
		predectiveTableMap.put(NonTerminalName.ITERATOR, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.PRRETURN, 75);
		predectiveTableMap.put(NonTerminalName.RETURN, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.ID, 77);
		terminaisMap.put(TokenCategory.CONSTNUMINT, 76);
		terminaisMap.put(TokenCategory.CONSTNUMDEC, 76);
		terminaisMap.put(TokenCategory.CONSTLOGIC, 76);
		terminaisMap.put(TokenCategory.CONSTCHAR, 76);
		terminaisMap.put(TokenCategory.CONSTCCHAR, 76);
		predectiveTableMap.put(NonTerminalName.RETURNFAT, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

	}
}
