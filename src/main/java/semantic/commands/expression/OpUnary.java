package semantic.commands.expression;

import lexical.Token;
import semantic.VarType;

public class OpUnary extends Op {
    public OpUnary(Token token, VarType type) {
        super(token, type);
    }
}
