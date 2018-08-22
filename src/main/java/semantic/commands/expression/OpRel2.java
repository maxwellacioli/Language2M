package semantic.commands.expression;

import lexical.Token;

public class OpRel2 extends Op {
    public Exp exp1, exp2;

    public OpRel2(Token tk, Exp exp1, Exp exp2) {
        super(tk, null);
        this.exp1 = exp1;
        this.exp2 = exp2;
    }
}
