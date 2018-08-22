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

		terminaisMap.put(TokenCategory.MAIN, 0);
		terminaisMap.put(TokenCategory.ID, 0);
		predectiveTableMap.put(NonTerminalName.PROGRAM, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.MAIN, 1);
		predectiveTableMap.put(NonTerminalName.MAIN, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.ID, 2);
		terminaisMap.put(TokenCategory.MAIN, 2);
		predectiveTableMap.put(NonTerminalName.FUNCTIONS, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.ID, 3);
		terminaisMap.put(TokenCategory.MAIN, 4);
		predectiveTableMap.put(NonTerminalName.FUNCTIONSREC, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.TINT, 5);
		terminaisMap.put(TokenCategory.TLOGIC, 5);
		terminaisMap.put(TokenCategory.TCHAR, 5);
		terminaisMap.put(TokenCategory.TCCHAR, 5);
		terminaisMap.put(TokenCategory.TREAL, 5);
		terminaisMap.put(TokenCategory.PARAMEND, 6);
		predectiveTableMap.put(NonTerminalName.PARAMS, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.TINT, 7);
		terminaisMap.put(TokenCategory.TLOGIC, 7);
		terminaisMap.put(TokenCategory.TCHAR, 7);
		terminaisMap.put(TokenCategory.TCCHAR, 7);
		terminaisMap.put(TokenCategory.TREAL, 7);
		predectiveTableMap.put(NonTerminalName.LISTPARAMS, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.PARAMEND, 9);
		terminaisMap.put(TokenCategory.SEP1, 8);
		predectiveTableMap.put(NonTerminalName.LISTPARAMSREC, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.TINT, 10);
		terminaisMap.put(TokenCategory.TLOGIC, 11);
		terminaisMap.put(TokenCategory.TCHAR, 12);
		terminaisMap.put(TokenCategory.TCCHAR, 13);
		terminaisMap.put(TokenCategory.TREAL, 14);
		predectiveTableMap.put(NonTerminalName.TYPE, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.ESCBEGIN, 15);
		predectiveTableMap.put(NonTerminalName.ESCOPE, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.ID, 16);
		terminaisMap.put(TokenCategory.TINT, 16);
		terminaisMap.put(TokenCategory.TLOGIC, 16);
		terminaisMap.put(TokenCategory.TCHAR, 16);
		terminaisMap.put(TokenCategory.TCCHAR, 16);
		terminaisMap.put(TokenCategory.TREAL, 16);
		terminaisMap.put(TokenCategory.PRREADIN, 16);
		terminaisMap.put(TokenCategory.PRPRINTOUT, 16);
		terminaisMap.put(TokenCategory.PRIF, 16);
		terminaisMap.put(TokenCategory.PRITERATOR, 16);
		terminaisMap.put(TokenCategory.PRDO, 16);
		terminaisMap.put(TokenCategory.PRWHILE, 16);
		terminaisMap.put(TokenCategory.PRRETURN, 16);
		predectiveTableMap.put(NonTerminalName.CMDS, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.ID, 17);
		terminaisMap.put(TokenCategory.TINT, 17);
		terminaisMap.put(TokenCategory.TLOGIC, 17);
		terminaisMap.put(TokenCategory.TCHAR, 17);
		terminaisMap.put(TokenCategory.TCCHAR, 17);
		terminaisMap.put(TokenCategory.TREAL, 17);
		terminaisMap.put(TokenCategory.PRREADIN, 17);
		terminaisMap.put(TokenCategory.PRPRINTOUT, 17);
		terminaisMap.put(TokenCategory.PRIF, 17);
		terminaisMap.put(TokenCategory.PRITERATOR, 17);
		terminaisMap.put(TokenCategory.PRDO, 17);
		terminaisMap.put(TokenCategory.PRWHILE, 17);
		terminaisMap.put(TokenCategory.PRRETURN, 17);
		terminaisMap.put(TokenCategory.ESCEND, 18);
		predectiveTableMap.put(NonTerminalName.CMDSREC, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.ID, 20);
		terminaisMap.put(TokenCategory.TINT, 19);
		terminaisMap.put(TokenCategory.TLOGIC, 19);
		terminaisMap.put(TokenCategory.TCHAR, 19);
		terminaisMap.put(TokenCategory.TCCHAR, 19);
		terminaisMap.put(TokenCategory.TREAL, 19);
		terminaisMap.put(TokenCategory.PRREADIN, 22);
		terminaisMap.put(TokenCategory.PRPRINTOUT, 21);
		terminaisMap.put(TokenCategory.PRIF, 23);
		terminaisMap.put(TokenCategory.PRITERATOR, 26);
		terminaisMap.put(TokenCategory.PRDO, 25);
		terminaisMap.put(TokenCategory.PRWHILE, 24);
		terminaisMap.put(TokenCategory.PRRETURN, 27);
		predectiveTableMap.put(NonTerminalName.CMD, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.PRELSE, 28);
		terminaisMap.put(TokenCategory.SEP2, 29);
		predectiveTableMap.put(NonTerminalName.IFELSEFAT, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.ID, 30);
		predectiveTableMap.put(NonTerminalName.LISTNAME, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

		terminaisMap.put(TokenCategory.SEP1, 31);
		terminaisMap.put(TokenCategory.SEP2, 32);
		predectiveTableMap.put(NonTerminalName.LISTNAMEREC, terminaisMap);
		terminaisMap = new HashMap<TokenCategory, Integer>();

	}
}
