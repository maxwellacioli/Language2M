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
		terminaisMap.put(TokenCategory.PARAMBEGIN, 24);
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
		terminaisMap.put(TokenCategory.PRRETURN, 34);
		terminaisMap.put(TokenCategory.PARAMBEGIN, 35);
		predectiveTableMap.put(NonTerminalName.CMD, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.TINT, 36);
		terminaisMap.put(TokenCategory.TLONG, 36);
		terminaisMap.put(TokenCategory.TLOGIC, 36);
		terminaisMap.put(TokenCategory.TCHAR, 36);
		terminaisMap.put(TokenCategory.TCCHAR, 36);
		terminaisMap.put(TokenCategory.TDEC, 36);
		predectiveTableMap.put(NonTerminalName.DECLARATION, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.SEP1, 37);
		terminaisMap.put(TokenCategory.OPATRIB, 38);
		terminaisMap.put(TokenCategory.ARRAYBEGIN, 38);
		terminaisMap.put(TokenCategory.TERM, 39);
		predectiveTableMap.put(NonTerminalName.VARIABLE, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.PARAMBEGIN, 41);
		terminaisMap.put(TokenCategory.ARRAYBEGIN, 40);
		terminaisMap.put(TokenCategory.OPATRIB, 40);
		predectiveTableMap.put(NonTerminalName.CMDFAT, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.ARRAYBEGIN, 42);
		terminaisMap.put(TokenCategory.OPATRIB, 42);
		predectiveTableMap.put(NonTerminalName.ATTRIBUTION, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.ARRAYBEGIN, 43);
		predectiveTableMap.put(NonTerminalName.VALUE, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.ARRAYBEGIN, 44);
		predectiveTableMap.put(NonTerminalName.ARRAY, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.ARRAYEND, 46);
		terminaisMap.put(TokenCategory.CONSTNUMINT, 45);
		terminaisMap.put(TokenCategory.CONSTNUMDEC, 45);
		terminaisMap.put(TokenCategory.CONSTLOGIC, 45);
		terminaisMap.put(TokenCategory.CONSTCHAR, 45);
		terminaisMap.put(TokenCategory.CONSTCCHAR, 45);
		predectiveTableMap.put(NonTerminalName.ARRAYFAT, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.CONSTNUMINT, 47);
		terminaisMap.put(TokenCategory.CONSTNUMDEC, 47);
		terminaisMap.put(TokenCategory.CONSTLOGIC, 47);
		terminaisMap.put(TokenCategory.CONSTCHAR, 47);
		terminaisMap.put(TokenCategory.CONSTCCHAR, 47);
		predectiveTableMap.put(NonTerminalName.ELEMENTS, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.ARRAYEND, 49);
		terminaisMap.put(TokenCategory.SEP1, 48);
		predectiveTableMap.put(NonTerminalName.ELEMENTSFAT, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.CONSTNUMINT, 50);
		terminaisMap.put(TokenCategory.CONSTNUMDEC, 51);
		terminaisMap.put(TokenCategory.CONSTLOGIC, 52);
		terminaisMap.put(TokenCategory.CONSTCHAR, 53);
		terminaisMap.put(TokenCategory.CONSTCCHAR, 54);
		predectiveTableMap.put(NonTerminalName.CONSTANT, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.PARAMBEGIN, 55);
		predectiveTableMap.put(NonTerminalName.FUNCCALL, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.ID, 56);
		terminaisMap.put(TokenCategory.CONSTNUMINT, 56);
		terminaisMap.put(TokenCategory.CONSTNUMDEC, 56);
		terminaisMap.put(TokenCategory.CONSTLOGIC, 56);
		terminaisMap.put(TokenCategory.CONSTCHAR, 56);
		terminaisMap.put(TokenCategory.CONSTCCHAR, 56);
		predectiveTableMap.put(NonTerminalName.LISTPARAMSCALL, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.PARAMEND, 58);
		terminaisMap.put(TokenCategory.SEP1, 57);
		predectiveTableMap.put(NonTerminalName.LISTPARAMSCALLFAT, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.ID, 60);
		terminaisMap.put(TokenCategory.CONSTNUMINT, 59);
		terminaisMap.put(TokenCategory.CONSTNUMDEC, 59);
		terminaisMap.put(TokenCategory.CONSTLOGIC, 59);
		terminaisMap.put(TokenCategory.CONSTCHAR, 59);
		terminaisMap.put(TokenCategory.CONSTCCHAR, 59);
		predectiveTableMap.put(NonTerminalName.PARAMITEM, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.PRPRINTOUT, 61);
		predectiveTableMap.put(NonTerminalName.PRINTOUT, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.ID, 63);
		terminaisMap.put(TokenCategory.CONSTCCHAR, 62);
		predectiveTableMap.put(NonTerminalName.MESSAGE, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.PARAMEND, 65);
		terminaisMap.put(TokenCategory.OPCONC, 64);
		predectiveTableMap.put(NonTerminalName.MESSAGEFAT, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.PRREADIN, 66);
		predectiveTableMap.put(NonTerminalName.READIN, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.PRIF, 67);
		predectiveTableMap.put(NonTerminalName.IFELSE, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.PRIF, 68);
		predectiveTableMap.put(NonTerminalName.IF, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.PRELSEIF, 69);
		terminaisMap.put(TokenCategory.PRELSE, 70);
		terminaisMap.put(TokenCategory.TERM, 70);
		predectiveTableMap.put(NonTerminalName.ELSEIF, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.PRELSE, 71);
		terminaisMap.put(TokenCategory.TERM, 72);
		predectiveTableMap.put(NonTerminalName.ELSE, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.PRWHILE, 73);
		predectiveTableMap.put(NonTerminalName.WHILE, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.PRDO, 74);
		predectiveTableMap.put(NonTerminalName.DOWHILE, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.PRITERATOR, 75);
		predectiveTableMap.put(NonTerminalName.ITERATOR, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.PRRETURN, 76);
		predectiveTableMap.put(NonTerminalName.RETURN, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.ID, 78);
		terminaisMap.put(TokenCategory.CONSTNUMINT, 77);
		terminaisMap.put(TokenCategory.CONSTNUMDEC, 77);
		terminaisMap.put(TokenCategory.CONSTLOGIC, 77);
		terminaisMap.put(TokenCategory.CONSTCHAR, 77);
		terminaisMap.put(TokenCategory.CONSTCCHAR, 77);
		predectiveTableMap.put(NonTerminalName.RETURNFAT, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.PARAMBEGIN, 79);
		predectiveTableMap.put(NonTerminalName.CASTING, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

	}
}
