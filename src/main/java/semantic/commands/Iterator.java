package semantic.commands; 

import analyzer.LLVMConfiguration;
import semantic.SymbolTable;
import org.bytedeco.javacpp.*;
import static org.bytedeco.javacpp.LLVM.*;

public class Iterator extends Node {

    public Iterator (Node child1, Node child2, Node child3, Node child4) {
        super(null);
        addChild(child1);
        addChild(child2);
        addChild(child3);
        addChild(child4);
    }

    //iterator = atrib0 | exp | atrib1 | listCommands
    @Override
    public LLVM.LLVMValueRef codeGen(LLVM.LLVMModuleRef moduleRef, LLVM.LLVMContextRef contextRef, LLVM.LLVMBuilderRef builderRef, SymbolTable symbolTable, LLVM.LLVMValueRef func) {
        LLVMValueRef cond;

        LLVMBasicBlockRef iteratorLoop = LLVMAppendBasicBlock(func, "iteratorLoop");
        LLVMBasicBlockRef condTrue = LLVMAppendBasicBlock(func, "condTrue");
        LLVMBasicBlockRef condFalse = LLVMAppendBasicBlock(func, "condFalse");

        //Executa o codeGen da atrib0
        Node.VisitCmd(getChildren().get(0), moduleRef, contextRef, builderRef, symbolTable, func);

        LLVMBuildBr(builderRef, iteratorLoop);

        LLVMPositionBuilderAtEnd(builderRef, iteratorLoop);
        cond = Node.visitorExp(getChildren().get(1), moduleRef, contextRef, builderRef, symbolTable, func);
        LLVMBuildCondBr(builderRef, cond, condTrue, condFalse);

        LLVMPositionBuilderAtEnd(builderRef, condTrue);
        //Executa o codeGen da lista de comandos
        Node.VisitCmd(getChildren().get(3),  moduleRef, contextRef, builderRef, symbolTable, func);
        //Executa o codeGen da atrib1
        Node.VisitCmd(getChildren().get(2),  moduleRef, contextRef, builderRef, symbolTable, func);
        //desvia o fluxo para o inicio do loop
        LLVMBuildBr(builderRef, iteratorLoop);

        LLVMPositionBuilderAtEnd(builderRef, condFalse);

        return null;
    }
}
