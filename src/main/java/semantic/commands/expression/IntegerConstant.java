package semantic.commands.expression;

import analyzer.LLVMConfiguration;
import lexical.Token;
import org.bytedeco.javacpp.LLVM;
import semantic.LiteralTable;
import semantic.SymbolTable;
import semantic.VarType;
import org.bytedeco.javacpp.*;
import semantic.commands.Printout;

import static org.bytedeco.javacpp.LLVM.*;

public class IntegerConstant extends Exp {

    public IntegerConstant(Token token) {
        super(token, VarType.INTEIRO);
    }

    @Override
    public LLVM.LLVMValueRef codeGen(LLVM.LLVMModuleRef moduleRef, LLVM.LLVMContextRef contextRef, LLVM.LLVMBuilderRef builderRef, SymbolTable symbolTable) {
        LLVMValueRef value = LiteralTable.getInstance().getLiteral(getToken().getLexValue());
        if(value == null) {
            value = LLVMConstInt(LLVMInt32Type(), Integer.parseInt(getToken().getLexValue()), 1);
            LiteralTable.getInstance().insertLiteral(getToken().getLexValue(), value);
        }

        if(LLVMConfiguration.getStrCodeFlag() &&
                (this.getParent() instanceof Printout || this.getParent() instanceof OpBinaryConc)) {
            LLVMConfiguration.getInstance().addPrintArg(value);
            LLVMConfiguration.getInstance().addStrCode("%d");
        }

        return value;
    }
}
