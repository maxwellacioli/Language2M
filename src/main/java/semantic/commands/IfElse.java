package semantic.commands;

import analyzer.LLVMConfiguration;
import semantic.SymbolTable;
import org.bytedeco.javacpp.*;
import static org.bytedeco.javacpp.LLVM.*;

public class IfElse extends Node {

    public IfElse(Node child1, Node child2, Node child3) {
        super(null);
        addChild(child1);
        addChild(child2);
        addChild(child3);
    }

    @Override
    public LLVMValueRef codeGen(LLVMModuleRef moduleRef, LLVMContextRef contextRef, LLVMBuilderRef builderRef, SymbolTable symbolTable, LLVMValueRef func) {
        //TODO verificar se é um inteiro de 1 bit
        LLVMValueRef cond = Node.visitorExp(getChildren().get(0), moduleRef, contextRef, builderRef, symbolTable, func);

        LLVMBasicBlockRef iftrue = LLVMAppendBasicBlock(func, "iftrue");
        LLVMBasicBlockRef iffalse = LLVMAppendBasicBlock(func, "iffalse");
        LLVMBasicBlockRef end = LLVMAppendBasicBlock(func, "end");

        LLVMBuildCondBr(builderRef, cond, iftrue, iffalse);

        //TODO verificar elementos que podem ser adicionados na função Phi
        LLVMPositionBuilderAtEnd(builderRef, iftrue);
        Node.VisitCmd(getChildren().get(1),  LLVMConfiguration.getInstance().getGlobalMod(), contextRef, builderRef, symbolTable, func);
        LLVMBuildBr(builderRef, end);

        //TODO verificar elementos que podem ser adicionados na função Phi
        LLVMPositionBuilderAtEnd(builderRef, iffalse);
        Node.VisitCmd(getChildren().get(2),  LLVMConfiguration.getInstance().getGlobalMod(), contextRef, builderRef, symbolTable, func);
        LLVMBuildBr(builderRef, end);

        //TODO Adicionar elementos da função Phi
        LLVMPositionBuilderAtEnd(builderRef, end);

        return null;
    }
}
