package semantic.commands;

import org.bytedeco.javacpp.LLVM;

public class Return extends Node {

    public Return (Node child) {
        addChild(child);
    }

    @Override
    public LLVM.LLVMValueRef codeGen(LLVM.LLVMModuleRef moduleRef, LLVM.LLVMContextRef contextRef, LLVM.LLVMBuilderRef builderRef) {
        return null;
    }
}
