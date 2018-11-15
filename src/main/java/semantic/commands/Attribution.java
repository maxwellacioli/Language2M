package semantic.commands;

import org.bytedeco.javacpp.*;
import semantic.Symbol;
import semantic.SymbolTable;
import semantic.commands.expression.Exp;

import static org.bytedeco.javacpp.LLVM.*;

public class Attribution extends Node {

    private SymbolTable symbolTable;

    public Attribution (SymbolTable symbolTable) {
        super(null);
        this.symbolTable = symbolTable;
    }

    public Attribution(Node child1, Node child2, SymbolTable symbolTable) {
        super(null);
        addChild(child1);
        addChild(child2);
        this.symbolTable = symbolTable;
    }

    public void setSymbolTable(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    @Override
    public LLVMValueRef codeGen(LLVMModuleRef moduleRef, LLVMContextRef contextRef, LLVMBuilderRef builderRef, SymbolTable symbolTable) {
        Symbol target = symbolTable.getLocalSymbolTable().get(getChildren().get(0).getName());
        Exp value = (Exp)getChildren().get(1);

        if(target.getType() != value.getType()) {
            throw  new RuntimeException("Tipos Incompatíveis");
        }

        LLVMValueRef llvmValue = target.getLlvmValueRef();
        LLVMBuildStore(builderRef, value.getLlvmValueRef(), llvmValue);
        return null;
    }
}
