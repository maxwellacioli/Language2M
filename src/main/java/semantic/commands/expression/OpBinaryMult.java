package semantic.commands.expression;

import analyzer.LLVMConfiguration;
import lexical.Token;
import org.bytedeco.javacpp.LLVM;
import semantic.SemanticAnalyzer;
import semantic.SymbolTable;
import semantic.VarType;
import semantic.commands.Node;
import semantic.commands.Printout;

import static org.bytedeco.javacpp.LLVM.*;

public class OpBinaryMult extends OpBinary {

    public OpBinaryMult(Token tk, Node exp1, Node exp2) {
        super(tk, exp1, exp2);
    }

    //TODO Verificação de compatibilidade de tipos é na análise semântica
    @Override
    public LLVMValueRef codeGen(LLVMModuleRef moduleRef, LLVMContextRef contextRef, LLVMBuilderRef builderRef, SymbolTable symbolTable, LLVMValueRef func) {
        checkOperandsType();

        String operator = getToken().getLexValue();
        LLVM.LLVMValueRef left = getChildren().get(0).getLlvmValueRef();
        LLVM.LLVMValueRef right = getChildren().get(1).getLlvmValueRef();
        LLVM.LLVMValueRef result = null;

        //Verificar tipo da operação para usar UDiv ou FDiv

        if(getType().equals(VarType.INTEIRO)) {
            if(operator.equals("*")) {
                result = LLVMBuildMul(builderRef, left, right, SemanticAnalyzer.getInstance().tempGenerator());
            } else if(operator.equals("/")) {
                result = LLVMBuildSDiv(builderRef, left, right, SemanticAnalyzer.getInstance().tempGenerator());
            }
        }else if(getType().equals(VarType.REAL)) {
            if(operator.equals("*")) {
                result = LLVMBuildFMul(builderRef, left, right, SemanticAnalyzer.getInstance().tempGenerator());
            } else if(operator.equals("/")) {
                result = LLVMBuildFDiv(builderRef, left, right, SemanticAnalyzer.getInstance().tempGenerator());
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
