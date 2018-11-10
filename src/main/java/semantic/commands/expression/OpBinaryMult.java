package semantic.commands.expression;

import analyzer.LLVMConfiguration;
import lexical.Token;
import org.bytedeco.javacpp.LLVM;
import semantic.commands.Node;
import semantic.commands.Printout;

import static org.bytedeco.javacpp.LLVM.*;

public class OpBinaryMult extends OpBinary {

    public OpBinaryMult(Token tk, Node exp1, Node exp2) {
        super(tk, exp1, exp2);
    }

    //TODO Verificação de compatibilidade de tipos é na análise semântica
    @Override
    public LLVM.LLVMValueRef codeGen(LLVM.LLVMModuleRef moduleRef, LLVM.LLVMContextRef contextRef, LLVM.LLVMBuilderRef builderRef) {
        String operator = getToken().getLexValue();
        LLVM.LLVMValueRef left = getChildren().get(0).getLlvmValueRef();
        LLVM.LLVMValueRef right = getChildren().get(1).getLlvmValueRef();
        LLVM.LLVMValueRef result = null;

        //Verificar tipo da operação para usar UDiv ou FDiv
        if(operator.equals("*")) {
            result = LLVMBuildMul(builderRef, left, right, "result");
        } else if(operator.equals("/")) {
            result = LLVMBuildSDiv(builderRef, left, right, "result");
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
