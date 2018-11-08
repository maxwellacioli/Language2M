package semantic.commands.expression;

import lexical.Token;

public class OpBinaryMult extends OpBinary {

    public OpBinaryMult(Token tk, Exp exp1, Exp exp2) {
        super(tk, null, exp1, exp2);
    }
}
