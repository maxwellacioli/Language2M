package semantic.commands;

import analyzer.LLVMConfiguration;
import org.bytedeco.javacpp.*;
import semantic.Symbol;
import semantic.SymbolTable;
import semantic.VarType;
import semantic.commands.expression.Exp;
import semantic.commands.expression.FunctionCall;

import java.util.ArrayList;
import java.util.List;

import static org.bytedeco.javacpp.LLVM.*;

public class Attribution extends Node {

    public Attribution ( ) {
        super(null);
    }

    public Attribution(Node child1, Node child2) {
        super(null);
        addChild(child1);
        addChild(child2);
    }

    @Override
    public LLVMValueRef codeGen(LLVMModuleRef moduleRef, LLVMContextRef contextRef, LLVMBuilderRef builderRef, SymbolTable symbolTable, LLVMValueRef func) {
        Symbol target = symbolTable.getLocalSymbolTable().get(getChildren().get(0).getName());
        LLVMValueRef v = Node.visitorExp(getChildren().get(1), moduleRef, contextRef, builderRef, symbolTable, func);

        Exp value = (Exp)getChildren().get(1);
        if((target.getType() != value.getType()) && !(value instanceof FunctionCall)) {
            System.err.println("Atribuição com tipos incompatíveis");
            System.exit(1);
        }

        if(target.getType().equals(VarType.TEXT)) {
            target.setLlvmValueRef(v);
        } else {
            LLVMValueRef targetPtr = target.getLlvmValueRef();
            LLVMBuildStore(builderRef, v, targetPtr);
        }


        return null;
    }
}
