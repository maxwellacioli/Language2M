package semantic.commands;

import org.bytedeco.javacpp.LLVM;

public class Do extends Node {

    public Do (Node child1, Node child2) {
        addChild(child1);
        addChild(child2);
    }

    @Override
    public LLVM.LLVMValueRef codeGen(LLVM.LLVMModuleRef moduleRef, LLVM.LLVMContextRef contextRef, LLVM.LLVMBuilderRef builderRef) {
        return null;
    }
}
