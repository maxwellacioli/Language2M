package analyzer;

import lexical.LexicalAnalyzer;
import semantic.AST;
import semantic.SemanticAnalyzer;
import semantic.commands.Node;
import syntactic.SyntaticAnalyzer;

public class Analyzer2M {
	private static LexicalAnalyzer lexicalAnalyzer;
	private static SyntaticAnalyzer syntaticAnalyzer;
	//private static SemanticAnalyzer semanticAnalyzer;

	private static String filePath = "files/input/hello.2m";

	public Analyzer2M() {

	}

	public static void main(String[] args) {

		lexicalAnalyzer = new LexicalAnalyzer(filePath);
		lexicalAnalyzer.readFile();

		//Inicializa o LLVM
//		LLVMConfiguration.getInstance().initLLVM();

		syntaticAnalyzer = new SyntaticAnalyzer(lexicalAnalyzer);
		syntaticAnalyzer.analyze();

		System.out.println("################# AST #################");
		System.out.println();
		System.out.println();

		for (AST ast: syntaticAnalyzer.getASTList()
			 ) {
			Node.visitor(ast.getRoot(), null, null, null);
		}

		//Executa a pilha de execução do LLVM
//		LLVMConfiguration.getInstance().runLLVM();
	}
}
