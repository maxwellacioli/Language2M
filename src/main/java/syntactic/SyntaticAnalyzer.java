package syntactic; 

import semantic.SymbolTable;
import syntactic.grammar.Grammar;
import lexical.LexicalAnalyzer;
import lexical.Token;

import java.util.ArrayList;
import java.util.List;

public class SyntaticAnalyzer {

	private Grammar grammar;
	private PredictiveAnalyzer predictiveAnalyzer;
	private PredictiveTable predictiveTable;
	private SymbolTable globalSymbolTable;
	private List<SymbolTable> symbolTables;

	public SyntaticAnalyzer() {

		grammar = Grammar.getInstance();
		predictiveTable = new PredictiveTable();
		predictiveAnalyzer = new PredictiveAnalyzer(grammar, predictiveTable);

		//Tabela de simbolo global
		symbolTables = new ArrayList<SymbolTable>();
		globalSymbolTable = new SymbolTable("global");
		symbolTables.add(globalSymbolTable);
		// TEST
//		 printTokens();

	}

	// TEST
//	private void printTokens() {
//		LexicalAnalyzer lexicalAnalyzer = LexicalAnalyzer.getInstance();
//
//		Token token;
//		while (lexicalAnalyzer.hasMoreTokens()) {
//			token = lexicalAnalyzer.nextToken();
//			System.out.println(token.toString());
//		}
//		System.out.println("<<<<<<<<<>>>>>>>>>");
//		lexicalAnalyzer.readFile();
//	}

	public void analyze() {
		predictiveAnalyzer.predictiveAnalyze();
	}

	public static void printError(Token token) {
		System.err.println("Erro sintático no token " + "<'" + token.getLexValue() + "'> | " + token.getLocation());
	}
}
