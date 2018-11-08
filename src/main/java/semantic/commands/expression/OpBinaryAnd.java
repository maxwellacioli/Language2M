package semantic.commands.expression;

import lexical.Token;

public class OpBinaryAnd extends OpBinary {

    public OpBinaryAnd(Token tk, Exp exp1, Exp exp2) {
        super(tk, null, exp1, exp2);
    }
}
