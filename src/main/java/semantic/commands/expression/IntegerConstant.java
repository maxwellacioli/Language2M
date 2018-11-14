package semantic.commands.expression;

import lexical.Token;
import org.bytedeco.javacpp.LLVM;
import semantic.LiteralTable;
import semantic.VarType;
import org.bytedeco.javacpp.*;
import static org.bytedeco.javacpp.LLVM.*;

public class IntegerConstant extends Exp {

    public IntegerConstant(Token token) {
        super(token, VarType.INTEIRO);
    }

    @Override
    public LLVM.LLVMValueRef codeGen(LLVM.LLVMModuleRef moduleRef, LLVM.LLVMContextRef contextRef, LLVM.LLVMBuilderRef builderRef) {
        LLVMValueRef value = LiteralTable.getInstance().getLiteral(getToken().getLexValue());
        if(value == null) {
            value = LLVMConstInt(LLVMInt32Type(), Integer.parseInt(getToken().getLexValue()), 1);
            LiteralTable.getInstance().insertLiteral(getToken().getLexValue(), value);
        }

        return value;
    }
}
