package semantic.commands;

import org.bytedeco.javacpp.LLVM;

public class Cmd extends Node {

    public Cmd() {}

    @Override
    public LLVM.LLVMValueRef codeGen(LLVM.LLVMModuleRef moduleRef, LLVM.LLVMContextRef contextRef, LLVM.LLVMBuilderRef builderRef) {
        return null;
    }
}
