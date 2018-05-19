package analyzer;

import lexical.LexicalAnalyzer;
//import semantic.DerivationTree;
import semantic.SemanticAnalyzer;
import syntactic.SyntaticAnalyzer;


public class Analyzer2M {
	private static LexicalAnalyzer lexicalAnalyzer;
	private static SyntaticAnalyzer syntaticAnalyzer;
	private static SemanticAnalyzer semanticAnalyzer;
//	private static DerivationTree derivationTree;

	private static String filePath = "files/input/test.2m";

	public Analyzer2M() {

	}

	public static void main(String[] args) {

		lexicalAnalyzer = new LexicalAnalyzer(filePath);
		lexicalAnalyzer.readFile();
		
		syntaticAnalyzer = new SyntaticAnalyzer(lexicalAnalyzer);
		syntaticAnalyzer.analyze();

//		derivationTree.getInstance();
//		System.out.println("Test");

//		semanticAnalyzer = new SemanticAnalyzer();
//		semanticAnalyzer.print();
	}
}
