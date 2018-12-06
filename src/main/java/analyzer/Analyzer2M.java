package analyzer;

import lexical.LexicalAnalyzer;
import semantic.FunctionAST;
import semantic.ProgramAST;
import semantic.commands.Node;
import syntactic.SyntaticAnalyzer;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.clang.IndexerCallbacks.StartedTranslationUnit_CXClientData_Pointer;

import gui.MainGUI;

import static org.bytedeco.javacpp.LLVM.*;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class Analyzer2M {


	public Analyzer2M() { }
	
	public void start() {
		LLVMConfiguration.getInstance().initLLVM();

		SyntaticAnalyzer syntaticAnalyzer = new SyntaticAnalyzer();
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

		//Inicia a pilha de execucao do LLVM
		LLVMConfiguration.getInstance().runLLVM();
	}
}
