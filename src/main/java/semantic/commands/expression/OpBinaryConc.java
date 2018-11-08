package semantic.commands.expression;

import lexical.Token;

public class OpBinaryConc extends OpBinary {

    public OpBinaryConc(Token tk, Exp exp1, Exp exp2) {
        super(tk, null, exp1, exp2);
    }
}
