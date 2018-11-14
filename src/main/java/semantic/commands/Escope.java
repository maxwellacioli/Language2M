package semantic.commands;

import org.bytedeco.javacpp.LLVM;

public class Escope extends Node {

    public Escope () {}

    @Override
    public LLVM.LLVMValueRef codeGen(LLVM.LLVMModuleRef moduleRef, LLVM.LLVMContextRef contextRef, LLVM.LLVMBuilderRef builderRef) {
        return null;
    }
}
