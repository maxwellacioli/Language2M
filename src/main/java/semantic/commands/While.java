package semantic.commands; 

import analyzer.LLVMConfiguration;
import semantic.SymbolTable;
import org.bytedeco.javacpp.*;
import static org.bytedeco.javacpp.LLVM.*;

public class While extends Node {

    public While (Node child1, Node child2) {
        super(null);
        addChild(child1);
        addChild(child2);
    }

    @Override
    public LLVM.LLVMValueRef codeGen(LLVM.LLVMModuleRef moduleRef, LLVM.LLVMContextRef contextRef, LLVM.LLVMBuilderRef builderRef, SymbolTable symbolTable, LLVM.LLVMValueRef func) {
        LLVMValueRef cond;

        LLVMBasicBlockRef whileLoop = LLVMAppendBasicBlock(func, "whileLoop");
        LLVMBasicBlockRef condTrue = LLVMAppendBasicBlock(func, "condTrue");
        LLVMBasicBlockRef condFalse = LLVMAppendBasicBlock(func, "condFalse");

        LLVMBuildBr(builderRef, whileLoop);

        LLVMPositionBuilderAtEnd(builderRef, whileLoop);
        cond = Node.visitorExp(getChildren().get(0), moduleRef, contextRef, builderRef, symbolTable, func);
        LLVMBuildCondBr(builderRef, cond, condTrue, condFalse);

        LLVMPositionBuilderAtEnd(builderRef, condTrue);
        Node.VisitCmd(getChildren().get(1),  LLVMConfiguration.getInstance().getGlobalMod(), contextRef, builderRef, symbolTable, func);
        LLVMBuildBr(builderRef, whileLoop);

        LLVMPositionBuilderAtEnd(builderRef, condFalse);

        return null;
    }
}
