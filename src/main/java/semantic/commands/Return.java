package semantic.commands;

import analyzer.LLVMConfiguration;
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
        LLVMValueRef ret = Node.visitorExp(getChildren().get(0), LLVMConfiguration.getInstance().getGlobalMod(), contextRef, builderRef, symbolTable);

        return LLVMBuildRet(builderRef, ret);
    }
}
