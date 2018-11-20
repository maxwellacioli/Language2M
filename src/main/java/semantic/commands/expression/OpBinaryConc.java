package semantic.commands.expression;

import lexical.Token;
import semantic.SymbolTable;
import semantic.commands.Node;
import org.bytedeco.javacpp.*;
import static org.bytedeco.javacpp.LLVM.*;

public class OpBinaryConc extends OpBinary {

    public OpBinaryConc(Token tk, Node exp1, Node exp2) {
        super(tk, exp1, exp2);
    }

    @Override
    public LLVMValueRef codeGen(LLVMModuleRef moduleRef, LLVMContextRef contextRef, LLVMBuilderRef builderRef, SymbolTable symbolTable, LLVMValueRef func) {
        return null;
    }
}
