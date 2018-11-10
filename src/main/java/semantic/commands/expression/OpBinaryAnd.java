package semantic.commands.expression;

import lexical.Token;
import semantic.commands.Node;

public class OpBinaryAnd extends OpBinary {

    public OpBinaryAnd(Token tk, Node exp1, Node exp2) {
        super(tk, exp1, exp2);
    }
}
