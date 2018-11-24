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
        LLVM.LLVMValueRef cond = Node.visitorExp(getChildren().get(0), moduleRef, contextRef, builderRef, symbolTable, func);

        LLVM.LLVMBasicBlockRef iftrue = LLVMAppendBasicBlock(func, "iftrue");
        LLVM.LLVMBasicBlockRef end = LLVMAppendBasicBlock(func, "end");

        LLVMBuildCondBr(builderRef, cond, iftrue, end);

        LLVMPositionBuilderAtEnd(builderRef, iftrue);
        Node.VisitCmd(getChildren().get(1),  LLVMConfiguration.getInstance().getGlobalMod(), contextRef, builderRef, symbolTable, func);
        LLVMBuildBr(builderRef, end);

        LLVMPositionBuilderAtEnd(builderRef, end);

        return null;
    }
}
