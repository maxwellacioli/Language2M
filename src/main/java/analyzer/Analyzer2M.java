package analyzer;

import lexical.LexicalAnalyzer;
import semantic.FunctionAST;
import semantic.ProgramAST;
import semantic.commands.Node;
import syntactic.SyntaticAnalyzer;
import org.bytedeco.javacpp.*;
import static org.bytedeco.javacpp.LLVM.*;


public class Analyzer2M {
	private static SyntaticAnalyzer syntaticAnalyzer;

	private static String filePath = "files/input/fib.2m";

	public Analyzer2M() { }

	public static void main(String[] args) {

		//Seta o path do arquivo de entrada
		LexicalAnalyzer.getInstance().setFilePath(filePath);
		//Lê o arquivo de entrada
		LexicalAnalyzer.getInstance().readFile();

		//Inicializa o LLVM
		LLVMConfiguration.getInstance().initLLVM();

		syntaticAnalyzer = new SyntaticAnalyzer();
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
