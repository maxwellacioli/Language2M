package semantic.commands;

import org.bytedeco.javacpp.LLVM;
import semantic.SymbolTable;

public class Escope extends Node {

    public Escope () {
        super(null);
    }

    @Override
    public LLVM.LLVMValueRef codeGen(LLVM.LLVMModuleRef moduleRef, LLVM.LLVMContextRef contextRef, LLVM.LLVMBuilderRef builderRef, SymbolTable symbolTable) {
        return null;
    }
}
