package semantic.commands;

import org.bytedeco.javacpp.LLVM;

public class IfElseFat extends Node {

    public IfElseFat() {

    }

    @Override
    public LLVM.LLVMValueRef codeGen(LLVM.LLVMModuleRef moduleRef, LLVM.LLVMContextRef contextRef, LLVM.LLVMBuilderRef builderRef) {
        return null;
    }
}
