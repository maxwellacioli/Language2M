package semantic.commands;

import org.bytedeco.javacpp.LLVM;
import semantic.SymbolTable;

public class Iterator extends Node {

    public Iterator (Node child1, Node child2, Node child3, Node child4) {
        super(null);
        addChild(child1);
        addChild(child2);
        addChild(child3);
        addChild(child4);
    }

    @Override
    public LLVM.LLVMValueRef codeGen(LLVM.LLVMModuleRef moduleRef, LLVM.LLVMContextRef contextRef, LLVM.LLVMBuilderRef builderRef, SymbolTable symbolTable) {
        return null;
    }
}
