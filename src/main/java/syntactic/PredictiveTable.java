package syntactic;

import java.util.HashMap;
import lexical.TokenCategory;
import syntactic.grammar.NonTerminalName;

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
		terminaisMap.put(TokenCategory.SEP1, 22);
		terminaisMap.put(TokenCategory.SEP2, 22);
		terminaisMap.put(TokenCategory.OPATRIB, 22);
		terminaisMap.put(TokenCategory.OPCONC, 22);
		predectiveTableMap.put(NonTerminalName.NAMEFAT1, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.ID, 23);
		terminaisMap.put(TokenCategory.CONSTNUMINT, 24);
		predectiveTableMap.put(NonTerminalName.NAMEFAT2, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.ESCBEGIN, 25);
		predectiveTableMap.put(NonTerminalName.ESCOPE, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.ID, 26);
		terminaisMap.put(TokenCategory.TINT, 26);
		terminaisMap.put(TokenCategory.TLONG, 26);
		terminaisMap.put(TokenCategory.TLOGIC, 26);
		terminaisMap.put(TokenCategory.TCHAR, 26);
		terminaisMap.put(TokenCategory.TCCHAR, 26);
		terminaisMap.put(TokenCategory.TDEC, 26);
		terminaisMap.put(TokenCategory.ESCEND, 27);
		terminaisMap.put(TokenCategory.PRREADIN, 26);
		terminaisMap.put(TokenCategory.PRPRINTOUT, 26);
		terminaisMap.put(TokenCategory.PRIF, 26);
		terminaisMap.put(TokenCategory.PRITERATOR, 26);
		terminaisMap.put(TokenCategory.PRDO, 26);
		terminaisMap.put(TokenCategory.PRWHILE, 26);
		terminaisMap.put(TokenCategory.PRRETURN, 26);
		terminaisMap.put(TokenCategory.PARAMBEGIN, 26);
		predectiveTableMap.put(NonTerminalName.COMMANDS, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.ID, 29);
		terminaisMap.put(TokenCategory.TINT, 28);
		terminaisMap.put(TokenCategory.TLONG, 28);
		terminaisMap.put(TokenCategory.TLOGIC, 28);
		terminaisMap.put(TokenCategory.TCHAR, 28);
		terminaisMap.put(TokenCategory.TCCHAR, 28);
		terminaisMap.put(TokenCategory.TDEC, 28);
		terminaisMap.put(TokenCategory.PRREADIN, 31);
		terminaisMap.put(TokenCategory.PRPRINTOUT, 30);
		terminaisMap.put(TokenCategory.PRIF, 32);
		terminaisMap.put(TokenCategory.PRITERATOR, 35);
		terminaisMap.put(TokenCategory.PRDO, 34);
		terminaisMap.put(TokenCategory.PRWHILE, 33);
		terminaisMap.put(TokenCategory.PRRETURN, 36);
		terminaisMap.put(TokenCategory.PARAMBEGIN, 37);
		predectiveTableMap.put(NonTerminalName.CMD, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.TINT, 38);
		terminaisMap.put(TokenCategory.TLONG, 38);
		terminaisMap.put(TokenCategory.TLOGIC, 38);
		terminaisMap.put(TokenCategory.TCHAR, 38);
		terminaisMap.put(TokenCategory.TCCHAR, 38);
		terminaisMap.put(TokenCategory.TDEC, 38);
		predectiveTableMap.put(NonTerminalName.DECLARATION, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.SEP1, 39);
//		terminaisMap.put(TokenCategory.OPATRIB, 40);
		terminaisMap.put(TokenCategory.ARRAYBEGIN, 40);
		terminaisMap.put(TokenCategory.SEP2, 41);
		predectiveTableMap.put(NonTerminalName.VARIABLE, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.PARAMBEGIN, 43);
		terminaisMap.put(TokenCategory.ARRAYBEGIN, 42);
		terminaisMap.put(TokenCategory.OPATRIB, 42);
		predectiveTableMap.put(NonTerminalName.CMDFAT, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.ARRAYBEGIN, 44);
		terminaisMap.put(TokenCategory.OPATRIB, 44);
		predectiveTableMap.put(NonTerminalName.ATTRIBUTION, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.ARRAYBEGIN, 45);
		predectiveTableMap.put(NonTerminalName.VALUE, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.ARRAYBEGIN, 46);
		predectiveTableMap.put(NonTerminalName.ARRAY, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.ARRAYEND, 48);
		terminaisMap.put(TokenCategory.CONSTNUMINT, 47);
		terminaisMap.put(TokenCategory.CONSTNUMDEC, 47);
		terminaisMap.put(TokenCategory.CONSTLOGIC, 47);
		terminaisMap.put(TokenCategory.CONSTCHAR, 47);
		terminaisMap.put(TokenCategory.CONSTCCHAR, 47);
		predectiveTableMap.put(NonTerminalName.ARRAYFAT, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.CONSTNUMINT, 49);
		terminaisMap.put(TokenCategory.CONSTNUMDEC, 49);
		terminaisMap.put(TokenCategory.CONSTLOGIC, 49);
		terminaisMap.put(TokenCategory.CONSTCHAR, 49);
		terminaisMap.put(TokenCategory.CONSTCCHAR, 49);
		predectiveTableMap.put(NonTerminalName.ELEMENTS, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.ARRAYEND, 51);
		terminaisMap.put(TokenCategory.SEP1, 50);
		predectiveTableMap.put(NonTerminalName.ELEMENTSFAT, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.CONSTNUMINT, 52);
		terminaisMap.put(TokenCategory.CONSTNUMDEC, 53);
		terminaisMap.put(TokenCategory.CONSTLOGIC, 54);
		terminaisMap.put(TokenCategory.CONSTCHAR, 55);
		terminaisMap.put(TokenCategory.CONSTCCHAR, 56);
		predectiveTableMap.put(NonTerminalName.CONSTANT, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.PARAMBEGIN, 57);
		predectiveTableMap.put(NonTerminalName.FUNCCALL, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.ID, 58);
		terminaisMap.put(TokenCategory.CONSTNUMINT, 58);
		terminaisMap.put(TokenCategory.CONSTNUMDEC, 58);
		terminaisMap.put(TokenCategory.CONSTLOGIC, 58);
		terminaisMap.put(TokenCategory.CONSTCHAR, 58);
		terminaisMap.put(TokenCategory.CONSTCCHAR, 58);
		predectiveTableMap.put(NonTerminalName.LISTPARAMSCALL, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.PARAMEND, 60);
		terminaisMap.put(TokenCategory.SEP1, 59);
		predectiveTableMap.put(NonTerminalName.LISTPARAMSCALLFAT, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.ID, 62);
		terminaisMap.put(TokenCategory.CONSTNUMINT, 61);
		terminaisMap.put(TokenCategory.CONSTNUMDEC, 61);
		terminaisMap.put(TokenCategory.CONSTLOGIC, 61);
		terminaisMap.put(TokenCategory.CONSTCHAR, 61);
		terminaisMap.put(TokenCategory.CONSTCCHAR, 61);
		predectiveTableMap.put(NonTerminalName.PARAMITEM, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.PRPRINTOUT, 63);
		predectiveTableMap.put(NonTerminalName.PRINTOUT, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.ID, 65);
		terminaisMap.put(TokenCategory.CONSTCCHAR, 64);
		predectiveTableMap.put(NonTerminalName.MESSAGE, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.PARAMEND, 67);
		terminaisMap.put(TokenCategory.OPCONC, 66);
		predectiveTableMap.put(NonTerminalName.MESSAGEFAT, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.PRREADIN, 68);
		predectiveTableMap.put(NonTerminalName.READIN, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.PRIF, 69);
		predectiveTableMap.put(NonTerminalName.IFELSE, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.PRIF, 70);
		predectiveTableMap.put(NonTerminalName.IF, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.PRELSEIF, 71);
		terminaisMap.put(TokenCategory.PRELSE, 72);
		terminaisMap.put(TokenCategory.SEP2, 72);
		predectiveTableMap.put(NonTerminalName.ELSEIF, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.PRELSE, 73);
		terminaisMap.put(TokenCategory.SEP2, 74);
		predectiveTableMap.put(NonTerminalName.ELSE, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.PRWHILE, 75);
		predectiveTableMap.put(NonTerminalName.WHILE, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.PRDO, 76);
		predectiveTableMap.put(NonTerminalName.DOWHILE, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.PRITERATOR, 77);
		predectiveTableMap.put(NonTerminalName.ITERATOR, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.PRRETURN, 78);
		predectiveTableMap.put(NonTerminalName.RETURN, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.PARAMBEGIN, 79);
		predectiveTableMap.put(NonTerminalName.CASTING, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

	}
}
