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

public class OpUnaryNegLogic extends OpUnary{

    public OpUnaryNegLogic(Token token, Node exp) {
        super(token, exp);
    }

    @Override
    public LLVMValueRef codeGen(LLVMModuleRef moduleRef, LLVMContextRef contextRef, LLVMBuilderRef builderRef, SymbolTable symbolTable, LLVMValueRef func) {
        LLVMValueRef child = getChildren().get(0).getLlvmValueRef();
        LLVMValueRef result = LLVMBuildICmp(builderRef, LLVMIntEQ, child, LLVMConstInt(LLVMInt1Type(), 0, 0),
                SemanticAnalyzer.getInstance().tempGenerator());

        return result;
    }
}
