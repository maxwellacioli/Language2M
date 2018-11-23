package semantic.commands.expression;

import analyzer.LLVMConfiguration;
import lexical.Token;
import semantic.SemanticAnalyzer;
import semantic.SymbolTable;
import semantic.commands.Node;
import org.bytedeco.javacpp.*;
import semantic.commands.Printout;

import static org.bytedeco.javacpp.LLVM.*;

public class OpBinaryExp extends OpBinary {

    public OpBinaryExp(Token tk, Node exp1, Node exp2) {
        super(tk, exp1, exp2);
    }

    @Override
    public LLVMValueRef codeGen(LLVMModuleRef moduleRef, LLVMContextRef contextRef, LLVMBuilderRef builderRef, SymbolTable symbolTable, LLVMValueRef func) {
        return null;
    }
}
