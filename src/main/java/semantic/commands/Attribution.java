package semantic.commands;

import analyzer.LLVMConfiguration;
import org.bytedeco.javacpp.*;
import semantic.Symbol;
import semantic.SymbolTable;
import semantic.commands.expression.Exp;
import semantic.commands.expression.FunctionCall;

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
        LLVMValueRef v = Node.visitorExp(getChildren().get(1), LLVMConfiguration.getInstance().getGlobalMod(), contextRef, builderRef, symbolTable);

        Exp value = (Exp)getChildren().get(1);

        if((target.getType() != value.getType()) && !(value instanceof FunctionCall)) {
            throw  new RuntimeException("Tipos Incompatíveis");
        }

        LLVMValueRef llvmValue = target.getLlvmValueRef();
        LLVMBuildStore(builderRef, v, llvmValue);
        return null;
    }
}
