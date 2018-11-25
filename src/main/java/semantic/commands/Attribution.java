package semantic.commands;

import analyzer.LLVMConfiguration;
import org.bytedeco.javacpp.*;
import semantic.Symbol;
import semantic.SymbolTable;
import semantic.VarType;
import semantic.commands.expression.Exp;
import semantic.commands.expression.FunctionCall;

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
        LLVMValueRef v = Node.visitorExp(getChildren().get(1), LLVMConfiguration.getInstance().getGlobalMod(), contextRef, builderRef, symbolTable, func);

        Exp value = (Exp)getChildren().get(1);
        if((target.getType() != value.getType()) && !(value instanceof FunctionCall)) {
            throw  new RuntimeException("Tipos Incompatíveis");
        }

        if(target.getType().equals(VarType.TEXT)) {
            LLVMTypeRef rightType = LLVMTypeOf(v);
            int rightLength = LLVMGetArrayLength(rightType);
            int typeKind = LLVMGetTypeKind(rightType);

            //Verifica se o pai do nó da direita é uma atribuição ou uma concatenação
            //se for uma atribuição, então teremos um array de inteiro, caso contrário, teremos um vetor de inteiro
            LLVMValueRef llvmTargetValue;
            if(typeKind == LLVMArrayTypeKind) {
                llvmTargetValue = LLVMBuildAlloca(builderRef, LLVMArrayType(LLVMInt8Type(), rightLength), getChildren().get(0).getName());
            } else {
                llvmTargetValue = LLVMBuildAlloca(builderRef, LLVMVectorType(LLVMInt8Type(), rightLength), getChildren().get(0).getName());
            }
            target.setLlvmValueRef(llvmTargetValue);
        }

        LLVMValueRef targetPtr = target.getLlvmValueRef();
        LLVMBuildStore(builderRef, v, targetPtr);

        return null;
    }
}
