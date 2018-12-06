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

public class OpUnaryNeg extends OpUnary {

    public OpUnaryNeg(Token token, Node exp) {
        super(token, exp);
    }
 
    @Override
    public LLVMValueRef codeGen(LLVMModuleRef moduleRef, LLVMContextRef contextRef, LLVMBuilderRef builderRef, SymbolTable symbolTable, LLVMValueRef func) {
        LLVMValueRef child = getChildren().get(0).getLlvmValueRef();
        LLVMValueRef result = null;

        switch (getType()) {
            case INTEIRO:
                result = LLVMBuildMul(builderRef, child, LLVMConstInt(LLVMInt32Type(), -1, 1),SemanticAnalyzer.getInstance().tempGenerator());
                break;
            case REAL:
                result = LLVMBuildFMul(builderRef, child, LLVMConstInt(LLVMInt32Type(), -1, 1),SemanticAnalyzer.getInstance().tempGenerator());
                break;
        }

        //TODO Condição que verifica se a flag de imprimir string está habilitada
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
