package semantic.commands;

import semantic.SymbolTable;
import org.bytedeco.javacpp.*;
import static org.bytedeco.javacpp.LLVM.*;

public class Return extends Node {

    public Return (Node child) {
        super(null);
        addChild(child);
    }

    @Override
    public LLVM.LLVMValueRef codeGen(LLVM.LLVMModuleRef moduleRef, LLVM.LLVMContextRef contextRef, LLVM.LLVMBuilderRef builderRef, SymbolTable symbolTable) {
        LLVMValueRef ret = getChildren().get(0).getLlvmValueRef();

        return LLVMBuildRet(builderRef, ret);
    }
}
