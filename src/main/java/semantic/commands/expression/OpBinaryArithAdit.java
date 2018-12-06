package semantic.commands.expression;

import analyzer.LLVMConfiguration;
import lexical.Token;
import semantic.SemanticAnalyzer;
import semantic.SymbolTable;
import semantic.VarType;
import semantic.commands.Node;
import org.bytedeco.javacpp.*;
import semantic.commands.Printout;

import static org.bytedeco.javacpp.LLVM.*;

public class OpBinaryArithAdit extends OpBinary {
    public OpBinaryArithAdit(Token tk, Node exp1, Node exp2) {
        super(tk, exp1, exp2);
    } 

    @Override
    public LLVMValueRef codeGen(LLVMModuleRef moduleRef, LLVMContextRef contextRef, LLVMBuilderRef builderRef, SymbolTable symbolTable, LLVMValueRef func) {
        checkOperandsType();

        String operator = getToken().getLexValue();
        LLVMValueRef left = getChildren().get(0).getLlvmValueRef();
        LLVMValueRef right = getChildren().get(1).getLlvmValueRef();
        LLVMValueRef result = null;

        if(getType().equals(VarType.INTEIRO)) {
            if(operator.equals("+")) {
                result = LLVMBuildAdd(builderRef, left, right, SemanticAnalyzer.getInstance().tempGenerator());
            } else if(operator.equals("-")) {
                result = LLVMBuildSub(builderRef, left, right, SemanticAnalyzer.getInstance().tempGenerator());
            }
        }else if(getType().equals(VarType.REAL)) {
            if(operator.equals("+")) {
                result = LLVMBuildFAdd(builderRef, left, right, SemanticAnalyzer.getInstance().tempGenerator());
            } else if(operator.equals("-")) {
                result = LLVMBuildFSub(builderRef, left, right, SemanticAnalyzer.getInstance().tempGenerator());
            }
        }

        //Condição que verifica se a flag de imprimir string está habilitada
        if(LLVMConfiguration.getStrCodeFlag() &&
                (this.getParent() instanceof Printout || this.getParent() instanceof OpBinaryConc)) {
            LLVMConfiguration.getInstance().addPrintArg(result);
            if(getType().equals(VarType.INTEIRO)) {
                LLVMConfiguration.getInstance().addStrCode("%d");
            } else if(getType().equals(VarType.REAL)) {
                LLVMConfiguration.getInstance().addStrCode("%.2lf");
            }
        }
        return result;
    }
}


