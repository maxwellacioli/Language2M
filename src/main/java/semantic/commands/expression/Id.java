package semantic.commands.expression;

import analyzer.LLVMConfiguration;
import lexical.Token;
import org.bytedeco.javacpp.*;
import static org.bytedeco.javacpp.LLVM.*;

import semantic.Symbol;
import semantic.SymbolTable;
import semantic.VarType;
import semantic.commands.Printout;

public class Id extends Exp {

    public Id() {}

    public Id(Token token) {
        super(token, null);
    }

    public Id(Token token, VarType type) {
        super(token, type);
    }

    @Override
    public LLVMValueRef codeGen(LLVMModuleRef moduleRef, LLVMContextRef contextRef, LLVMBuilderRef builderRef, SymbolTable symbolTable) {
        Symbol symbol = symbolTable.getLocalSymbolTable().get(getName());

        if(symbol == null) {
            throw new RuntimeException("Variavel nao declarada!");
        }

        LLVMValueRef valueRef = symbol.getLlvmValueRef();
        LLVMValueRef value = LLVMBuildLoad(builderRef, valueRef, getName());

        //TODO VERIFICAR TIPO
        if(LLVMConfiguration.getStrCodeFlag() &&
                (this.getParent() instanceof Printout || this.getParent() instanceof OpBinaryConc)) {
            LLVMConfiguration.getInstance().addPrintArg(value);
            if(getType().equals(VarType.INTEIRO)) {
                LLVMConfiguration.getInstance().addStrCode("%d");
            } else if(getType().equals(VarType.REAL)) {
                LLVMConfiguration.getInstance().addStrCode("%.2lf");
            }
        }

        return  value;
    }
}
