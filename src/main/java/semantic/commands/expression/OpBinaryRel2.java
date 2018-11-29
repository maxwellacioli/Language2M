package semantic.commands.expression;

import analyzer.LLVMConfiguration;
import lexical.Token;
import semantic.SemanticAnalyzer;
import semantic.SymbolTable;
import semantic.VarType;
import semantic.commands.Node;
import semantic.commands.Printout;

import org.bytedeco.javacpp.*;
import static org.bytedeco.javacpp.LLVM.*;

public class OpBinaryRel2 extends OpBinary {

    public OpBinaryRel2(Token tk, Node exp1, Node exp2) {
        super(tk, exp1, exp2);
    }

    @Override
    public LLVM.LLVMValueRef codeGen(LLVM.LLVMModuleRef moduleRef, LLVM.LLVMContextRef contextRef, LLVM.LLVMBuilderRef builderRef, SymbolTable symbolTable, LLVM.LLVMValueRef func) {
        checkOperandsType();

        String operator = getToken().getLexValue();
        LLVM.LLVMValueRef left = getChildren().get(0).getLlvmValueRef();
        LLVM.LLVMValueRef right = getChildren().get(1).getLlvmValueRef();
        LLVM.LLVMValueRef result = null;

        if(getType().equals(VarType.INTEIRO)) {
            if (operator.equals("==")) {
                result = LLVMBuildICmp(builderRef, LLVMIntEQ, left, right, SemanticAnalyzer.getInstance().tempGenerator());
            } else if (operator.equals("~=")) {
                result = LLVMBuildICmp(builderRef, LLVMIntNE, left, right, SemanticAnalyzer.getInstance().tempGenerator());
            }
        } else if(getType().equals(VarType.REAL)) {
            if (operator.equals("==")) {
                result = LLVMBuildFCmp(builderRef, LLVMRealOEQ, left, right, SemanticAnalyzer.getInstance().tempGenerator());
            } else if (operator.equals("~=")) {
                result = LLVMBuildFCmp(builderRef, LLVMRealONE, left, right, SemanticAnalyzer.getInstance().tempGenerator());
            }
        }
        //TODO Condição que verifica se a flag de imprimir string está habilitada
        if(LLVMConfiguration.getStrCodeFlag() &&
                (this.getParent() instanceof Printout || this.getParent() instanceof OpBinaryConc)) {
            LLVMConfiguration.getInstance().addPrintArg(result);
            LLVMConfiguration.getInstance().addStrCode("%d");
        }
        return result;
    }
}
