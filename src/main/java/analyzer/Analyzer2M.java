package analyzer;

import lexical.LexicalAnalyzer;
import semantic.SemanticAnalyzer;
import syntactic.SyntaticAnalyzer;


public class Analyzer2M {
	private static LexicalAnalyzer lexicalAnalyzer;
	private static SyntaticAnalyzer syntaticAnalyzer;
	private static SemanticAnalyzer semanticAnalyzer;

	private static String filePath = "files/input/fibonacci.2m";

	public Analyzer2M() {

	}

	public static void main(String[] args) {

		lexicalAnalyzer = new LexicalAnalyzer(filePath);
		lexicalAnalyzer.readFile();
		
		syntaticAnalyzer = new SyntaticAnalyzer(lexicalAnalyzer);
		syntaticAnalyzer.analyze();

	}
}
