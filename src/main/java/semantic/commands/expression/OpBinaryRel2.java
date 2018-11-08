package semantic.commands.expression;

import lexical.Token;

public class OpBinaryRel2 extends OpBinary {

    public OpBinaryRel2(Token tk, Exp exp1, Exp exp2) {
        super(tk, null, exp1, exp2);
    }
}
