package semantic.commands.expression;

import analyzer.LLVMConfiguration;
import lexical.Token;
import org.bytedeco.javacpp.LLVM;
import semantic.LiteralTable;
import semantic.SymbolTable;
import semantic.VarType;
import semantic.commands.Printout;

import static org.bytedeco.javacpp.LLVM.*;

public class RealConstant extends Exp {

    public RealConstant(Token token) {
        super(token, VarType.REAL);
    }
 
    @Override
    public LLVMValueRef codeGen(LLVMModuleRef moduleRef, LLVMContextRef contextRef, LLVMBuilderRef builderRef, SymbolTable symbolTable, LLVMValueRef func) {
        LLVM.LLVMValueRef value = LiteralTable.getInstance().getLiteral(getToken().getLexValue());
        if(value == null) {
            value = LLVMConstReal(LLVMDoubleType(), Double.parseDouble(getToken().getLexValue()));
            LiteralTable.getInstance().insertLiteral(getToken().getLexValue(), value);
        }

        if(LLVMConfiguration.getStrCodeFlag() &&
                (this.getParent() instanceof Printout || this.getParent() instanceof OpBinaryConc)) {
            LLVMConfiguration.getInstance().addPrintArg(value);
            LLVMConfiguration.getInstance().addStrCode("%.2lf");
        }

        return value;
    }
}
