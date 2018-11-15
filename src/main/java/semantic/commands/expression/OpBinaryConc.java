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
    public LLVMValueRef codeGen(LLVM.LLVMModuleRef moduleRef, LLVM.LLVMContextRef contextRef, LLVM.LLVMBuilderRef builderRef, SymbolTable symbolTable) {
        return null;
    }
}
