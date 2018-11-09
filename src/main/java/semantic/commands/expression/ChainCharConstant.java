package semantic.commands.expression;

import lexical.Token;
import org.bytedeco.javacpp.LLVM;
import semantic.VarType;

public class ChainCharConstant extends Exp {

    public ChainCharConstant(Token token) {
        super(token, VarType.CADEIA);
    }

    @Override
    public LLVM.LLVMValueRef codeGen(LLVM.LLVMModuleRef moduleRef, LLVM.LLVMContextRef contextRef, LLVM.LLVMBuilderRef builderRef) {
        System.out.println(getToken().getLexValue());
        return null;
    }
}
