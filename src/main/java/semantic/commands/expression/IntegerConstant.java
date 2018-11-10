package semantic.commands.expression;

import lexical.Token;
import org.bytedeco.javacpp.LLVM;
import semantic.VarType;
import org.bytedeco.javacpp.*;
import static org.bytedeco.javacpp.LLVM.*;

public class IntegerConstant extends Exp {

    public IntegerConstant(Token token) {
        super(token, VarType.INTEIRO);
    }

    @Override
    public LLVM.LLVMValueRef codeGen(LLVM.LLVMModuleRef moduleRef, LLVM.LLVMContextRef contextRef, LLVM.LLVMBuilderRef builderRef) {
        return LLVMConstInt(LLVMInt32Type(), Integer.parseInt(getToken().getLexValue()), 1);
    }
}
