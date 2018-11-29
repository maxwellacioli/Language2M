package semantic.commands;

import org.bytedeco.javacpp.LLVM;
import semantic.Symbol;
import semantic.SymbolTable;
import org.bytedeco.javacpp.*;
import static org.bytedeco.javacpp.LLVM.*;


import static org.bytedeco.javacpp.LLVM.LLVMBuildGlobalString;

public class ReadIn extends Node {

    public ReadIn(Node child) {
        super(null);
        addChild(child);
    }

    @Override
    public LLVM.LLVMValueRef codeGen(LLVM.LLVMModuleRef moduleRef, LLVM.LLVMContextRef contextRef, LLVM.LLVMBuilderRef builderRef, SymbolTable symbolTable, LLVM.LLVMValueRef func) {
        Symbol symbol = symbolTable.getLocalSymbolTable().get(getChildren().get(0).getName());

        if(symbol == null) {
            System.err.println("Variável " + "<'" + getChildren().get(0).getName()  + "'> não declarada.");
            System.exit(1);;
        }

        LLVMValueRef str = null;

        switch (symbol.getType()) {
            case INTEIRO:
                str = LLVMBuildGlobalString(builderRef, "%d", "scanfCode");
                break;
            case REAL:
                str = LLVMBuildGlobalString(builderRef, "%lf", "scanfCode");
                break;
            case LOGICO:
                str = LLVMBuildGlobalString(builderRef, "%d", "scanfCode");
                break;
            case TEXT:
                str = LLVMBuildGlobalString(builderRef, "%s", "scanfCode");
                break;
        }

        LLVMValueRef valueRef = symbol.getLlvmValueRef();
        LLVMValueRef scanfFunction = LLVMGetNamedFunction(moduleRef, "scanf");
        LLVMValueRef[] scanfArgs = { str, valueRef };
        LLVMBuildCall(builderRef, scanfFunction, new PointerPointer(scanfArgs), 2, "scanfCall");

        return null;
    }
}
