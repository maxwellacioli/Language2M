package semantic.commands;

import org.bytedeco.javacpp.LLVM;

public class Else extends Node {

    public Else() {

    }

    @Override
    public LLVM.LLVMValueRef codeGen(LLVM.LLVMModuleRef moduleRef, LLVM.LLVMContextRef contextRef, LLVM.LLVMBuilderRef builderRef) {
        return null;
    }
}
