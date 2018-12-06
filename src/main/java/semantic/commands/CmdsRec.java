package semantic.commands; 

import org.bytedeco.javacpp.LLVM;
import semantic.SymbolTable;

public class CmdsRec extends Node {
    public CmdsRec() {
        super(null);
    }

    public CmdsRec(Node child1, Node child2) {
        super(null);
        addChild(child1);
        addChild(child2);
    }

    @Override
    public LLVM.LLVMValueRef codeGen(LLVM.LLVMModuleRef moduleRef, LLVM.LLVMContextRef contextRef, LLVM.LLVMBuilderRef builderRef, SymbolTable symbolTable, LLVM.LLVMValueRef func) {
        return null;
    }
}
