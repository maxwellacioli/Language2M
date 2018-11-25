package analyzer;

import lexical.LexicalAnalyzer;
import semantic.FunctionAST;
import semantic.ProgramAST;
import semantic.commands.Node;
import syntactic.SyntaticAnalyzer;
import org.bytedeco.javacpp.*;
import static org.bytedeco.javacpp.LLVM.*;


public class Analyzer2M {
	private static LexicalAnalyzer lexicalAnalyzer;
	private static SyntaticAnalyzer syntaticAnalyzer;
	//private static SemanticAnalyzer semanticAnalyzer;

	private static String filePath = "files/input/power.2m";

	public Analyzer2M() {

	}

	public static void main(String[] args) {

		lexicalAnalyzer = new LexicalAnalyzer(filePath);
		lexicalAnalyzer.readFile();

		//Inicializa o LLVM
		LLVMConfiguration.getInstance().initLLVM();

		syntaticAnalyzer = new SyntaticAnalyzer(lexicalAnalyzer);
		syntaticAnalyzer.analyze();

		System.out.println("##################################");
		System.out.println();
		System.out.println();

		for (FunctionAST functionAst : ProgramAST.getInstance().getAstList()
			 ) {
			LLVMBuilderRef builder = LLVMCreateBuilder();
			LLVMContextRef context = LLVMContextCreate();

			functionAst.functionCodeGen(LLVMConfiguration.getInstance().getGlobalMod(), context, builder);

			LLVMDisposeBuilder(builder);
			LLVMContextDispose(context);
		}

		//Inicia a pilha de execução do LLVM
		LLVMConfiguration.getInstance().runLLVM();
	}
}
