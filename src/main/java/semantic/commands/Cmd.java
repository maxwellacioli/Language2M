package semantic.commands; 

import org.bytedeco.javacpp.LLVM;
import semantic.SymbolTable;

public class Cmd extends Node {

    public Cmd() {
        super(null);
    }

    @Override
    public LLVM.LLVMValueRef codeGen(LLVM.LLVMModuleRef moduleRef, LLVM.LLVMContextRef contextRef, LLVM.LLVMBuilderRef builderRef, SymbolTable symbolTable, LLVM.LLVMValueRef func) {
        return null;
    }
}
