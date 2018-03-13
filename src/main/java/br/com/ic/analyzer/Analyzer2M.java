package br.com.ic.analyzer;

import br.com.ic.lexical.LexicalAnalyzer;
import br.com.ic.syntactic.SyntaticAnalyzer;


public class Analyzer2M {
	private static LexicalAnalyzer lexicalAnalyzer;
	private static SyntaticAnalyzer syntaticAnalyzer;

	private static String filePath = "files/input/test.2m";

	public Analyzer2M() {

	}

	public static void main(String[] args) {

		lexicalAnalyzer = new LexicalAnalyzer(filePath);
		lexicalAnalyzer.readFile();
		
		syntaticAnalyzer = new SyntaticAnalyzer(lexicalAnalyzer);
		syntaticAnalyzer.analyze();
	}
}
