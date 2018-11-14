package semantic.commands;

import org.bytedeco.javacpp.LLVM;

public class IfElse extends Node {

    public IfElse(Node child1, Node child2, Node child3) {
        addChild(child1);
        addChild(child2);
        addChild(child3);
    }

    @Override
    public LLVM.LLVMValueRef codeGen(LLVM.LLVMModuleRef moduleRef, LLVM.LLVMContextRef contextRef, LLVM.LLVMBuilderRef builderRef) {
        return null;
    }
}
