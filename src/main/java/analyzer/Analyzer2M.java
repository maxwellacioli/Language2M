package analyzer;

import lexical.LexicalAnalyzer;
import semantic.AST;
import semantic.SemanticAnalyzer;
import semantic.commands.Node;
import syntactic.SyntaticAnalyzer;
import org.bytedeco.javacpp.*;
import static org.bytedeco.javacpp.LLVM.*;


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
		LLVMConfiguration.getInstance().initLLVM();

		syntaticAnalyzer = new SyntaticAnalyzer(lexicalAnalyzer);
		syntaticAnalyzer.analyze();

//		System.out.println("################# AST #################");
		System.out.println("##################################");
		System.out.println();
		System.out.println();

		for (AST ast: syntaticAnalyzer.getASTList()
			 ) {
			LLVMBuilderRef builder = LLVMCreateBuilder();
			LLVMContextRef context = LLVMContextCreate();

			ast.allocateSymbols(builder);

			LLVMTypeRef mainType = LLVMFunctionType(LLVMInt32Type(), new PointerPointer((Pointer)null), 0, 0);
			LLVMValueRef mainFunc = LLVMAddFunction(LLVMConfiguration.getInstance().getGlobalMod(), ast.getName(), mainType);
			LLVMBasicBlockRef entry = LLVMAppendBasicBlock(mainFunc, "entry");
			LLVMPositionBuilderAtEnd(builder, entry);

			Node.visitor(ast.getRoot(), LLVMConfiguration.getInstance().getGlobalMod(), context, builder);

			//FIXME Resolver para retorno de função que não seja a função principal

			if(ast.getName().equals("principal")) {
				LLVMValueRef ret = LLVMConstInt(LLVMInt32Type(), 1, 1);
				LLVMBuildRet(builder, ret);
			}

			LLVMDisposeBuilder(builder);
			LLVMContextDispose(context);
		}

		//Executa a pilha de execução do LLVM
		LLVMConfiguration.getInstance().runLLVM();
	}
}
