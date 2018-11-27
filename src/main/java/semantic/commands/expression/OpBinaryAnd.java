package semantic.commands.expression;

import analyzer.LLVMConfiguration;
import lexical.Token;
import org.bytedeco.javacpp.LLVM;
import semantic.SemanticAnalyzer;
import semantic.SymbolTable;
import semantic.commands.Node;
import semantic.commands.Printout;

import org.bytedeco.javacpp.*;
import static org.bytedeco.javacpp.LLVM.*;

public class OpBinaryAnd extends OpBinary {

    public OpBinaryAnd(Token tk, Node exp1, Node exp2) {
        super(tk, exp1, exp2);
    }

    //TODO Verificar tipos
    @Override
    public LLVMValueRef codeGen(LLVMModuleRef moduleRef, LLVMContextRef contextRef, LLVMBuilderRef builderRef, SymbolTable symbolTable, LLVMValueRef func) {
        checkOperandsType();

        LLVMValueRef left = getChildren().get(0).getLlvmValueRef();
        LLVMValueRef right = getChildren().get(1).getLlvmValueRef();

        LLVMValueRef result = LLVMBuildAnd(builderRef, left, right, SemanticAnalyzer.getInstance().tempGenerator());

        if(LLVMConfiguration.getStrCodeFlag() &&
                (this.getParent() instanceof Printout || this.getParent() instanceof OpBinaryConc)) {
            LLVMConfiguration.getInstance().addPrintArg(result);
            LLVMConfiguration.getInstance().addStrCode("%d");
        }

        return result;
    }
}
