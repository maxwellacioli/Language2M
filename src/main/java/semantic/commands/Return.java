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
    public LLVMValueRef codeGen(LLVMModuleRef moduleRef, LLVMContextRef contextRef, LLVMBuilderRef builderRef, SymbolTable symbolTable, LLVMValueRef func) {
        LLVMValueRef ret = Node.visitorExp(getChildren().get(0), LLVMConfiguration.getInstance().getGlobalMod(), contextRef, builderRef, symbolTable, func);

        return LLVMBuildRet(builderRef, ret);
    }
}
