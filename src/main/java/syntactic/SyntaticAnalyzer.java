package syntactic;

import semantic.AST;
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

	public SyntaticAnalyzer(LexicalAnalyzer lexicalAnalyzer) {

		grammar = Grammar.getInstance();
		predictiveTable = new PredictiveTable();
		predictiveAnalyzer = new PredictiveAnalyzer(grammar, predictiveTable,
				lexicalAnalyzer);

		//Tabela de simbolo global
		symbolTables = new ArrayList<SymbolTable>();
		globalSymbolTable = new SymbolTable("global");
		symbolTables.add(globalSymbolTable);
		// TEST
//		 printTokens(lexicalAnalyzer);

	}

	// TEST
	private void printTokens(LexicalAnalyzer lexicalAnalyzer) {
		Token token;
		while (lexicalAnalyzer.hasMoreTokens()) {
			token = lexicalAnalyzer.nextToken();
			System.out.println(token.toString());
		}
		System.out.println();
		System.out.println();
		lexicalAnalyzer.readFile();
	}

	public void analyze() {
		predictiveAnalyzer.predictiveAnalyze();
	}

	public List<AST> getASTList() {
		return predictiveAnalyzer.getProgramASTList();
	}

	public static void printError(Token token) {
		System.err.println("Erro no token " + token.getCategory() + "("
				+ token.getLexValue() + ")," + " na linha " + token.getLine()
				+ " e coluna " + token.getColumn() + ".");
	}
}
