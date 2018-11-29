package semantic.commands.expression;

import lexical.Token;
import org.bytedeco.javacpp.LLVM;
import semantic.SymbolTable;
import semantic.VarType;
import semantic.commands.Node;

public class Exp extends Node {
    private Token token;
    private VarType type;

    public Exp() {
        super(null);
    }

    public Exp(Token token, VarType type) {
        super(null);
        this.token = token;
        this.type = type;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public void setType(VarType type) {
        this.type = type;
    }

    public Token getToken() {
        return token;
    }

    public VarType getType() {
        return type;
    }

    public boolean isNumeric() {
        return (type.equals(VarType.INTEIRO) || type.equals(VarType.REAL));
    }

    @Override
    public LLVM.LLVMValueRef codeGen(LLVM.LLVMModuleRef moduleRef, LLVM.LLVMContextRef contextRef, LLVM.LLVMBuilderRef builderRef, SymbolTable symbolTable, LLVM.LLVMValueRef func) {
        return null;
    }
}
