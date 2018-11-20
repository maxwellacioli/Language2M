package semantic.commands.expression;

import lexical.Token;
import org.bytedeco.javacpp.LLVM;
import semantic.LiteralTable;
import semantic.SymbolTable;
import semantic.VarType;

import org.bytedeco.javacpp.*;
import static org.bytedeco.javacpp.LLVM.*;

public class LogicConstant extends  Exp {

    private final String TRUE = "verdade";
    private final String FALSE = "falso";

    private final int TRUE_VALUE = 1;
    private final int FALSE_VALUE = 0;

    public LogicConstant(Token token) {
        super(token, VarType.LOGICO);
    }

    @Override
    public LLVMValueRef codeGen(LLVMModuleRef moduleRef, LLVMContextRef contextRef, LLVMBuilderRef builderRef, SymbolTable symbolTable, LLVMValueRef func) {
        LLVMValueRef value = null;

        if(getToken().getLexValue().equals(TRUE)) {
            value = LLVMConstInt(LLVMInt1Type(), TRUE_VALUE, 1);
            LiteralTable.getInstance().insertLiteral(getToken().getLexValue(), value);
        } else if(getToken().getLexValue().equals(FALSE)) {
            value = LLVMConstInt(LLVMInt1Type(), FALSE_VALUE, 1);
            LiteralTable.getInstance().insertLiteral(getToken().getLexValue(), value);
        }

        return value;
    }
}
