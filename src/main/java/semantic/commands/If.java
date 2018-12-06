package semantic.commands; 

import analyzer.LLVMConfiguration;
import org.bytedeco.javacpp.LLVM;
import semantic.SymbolTable;

import static org.bytedeco.javacpp.LLVM.*;
import static org.bytedeco.javacpp.LLVM.LLVMBuildBr;
import static org.bytedeco.javacpp.LLVM.LLVMPositionBuilderAtEnd;

public class If extends Node {

    public If(Node child1, Node child2, Node child3) {
        super(null);
        addChild(child1);
        addChild(child2);
        addChild(child3);
    }

    @Override
    public LLVM.LLVMValueRef codeGen(LLVM.LLVMModuleRef moduleRef, LLVM.LLVMContextRef contextRef, LLVM.LLVMBuilderRef builderRef, SymbolTable symbolTable, LLVM.LLVMValueRef func) {
        //analisa a expressao de condição
        LLVM.LLVMValueRef cond = Node.visitorExp(getChildren().get(0), moduleRef, contextRef, builderRef, symbolTable, func);

        //cria os labels para condição verdadeira e fim do comando
        LLVM.LLVMBasicBlockRef iftrue = LLVMAppendBasicBlock(func, "iftrue");
        LLVM.LLVMBasicBlockRef end = LLVMAppendBasicBlock(func, "end");

        //verifica a condição e faz o desvio de flixo
        LLVMBuildCondBr(builderRef, cond, iftrue, end);

        LLVMPositionBuilderAtEnd(builderRef, iftrue);
        Node.VisitCmd(getChildren().get(1),  LLVMConfiguration.getInstance().getGlobalMod(), contextRef, builderRef, symbolTable, func);
        LLVMBuildBr(builderRef, end);

        LLVMPositionBuilderAtEnd(builderRef, end);

        return null;
    }
}
