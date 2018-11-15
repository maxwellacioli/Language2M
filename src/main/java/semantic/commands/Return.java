package semantic.commands;

import org.bytedeco.javacpp.LLVM;
import semantic.SymbolTable;

public class Return extends Node {

    public Return (Node child) {
        super(null);
        addChild(child);
    }

    @Override
    public LLVM.LLVMValueRef codeGen(LLVM.LLVMModuleRef moduleRef, LLVM.LLVMContextRef contextRef, LLVM.LLVMBuilderRef builderRef, SymbolTable symbolTable) {
        return null;
    }
}
