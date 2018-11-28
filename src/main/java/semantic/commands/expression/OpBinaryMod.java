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

public class OpBinaryMod extends OpBinary {

    public OpBinaryMod(Token tk, Node exp1, Node exp2) {
        super(tk, exp1, exp2);
    }

    public LLVM.LLVMValueRef codeGen(LLVM.LLVMModuleRef moduleRef, LLVM.LLVMContextRef contextRef, LLVM.LLVMBuilderRef builderRef, SymbolTable symbolTable, LLVM.LLVMValueRef func) {
        checkOperandsType();

        LLVM.LLVMValueRef left = getChildren().get(0).getLlvmValueRef();
        LLVM.LLVMValueRef right = getChildren().get(1).getLlvmValueRef();
        LLVM.LLVMValueRef result = null;

        if(getType().equals(VarType.INTEIRO)) {
            result = LLVMBuildSRem(builderRef, left, right, SemanticAnalyzer.getInstance().tempGenerator());
        }else if(getType().equals(VarType.REAL)) {
            result = LLVMBuildFRem(builderRef, left, right, SemanticAnalyzer.getInstance().tempGenerator());
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
