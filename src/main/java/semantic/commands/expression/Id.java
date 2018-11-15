package semantic.commands.expression;

import lexical.Token;
import org.bytedeco.javacpp.*;
import static org.bytedeco.javacpp.LLVM.*;

import semantic.SymbolTable;
import semantic.VarType;

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
        return  null;
    }
}
