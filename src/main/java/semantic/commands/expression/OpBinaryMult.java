package semantic.commands.expression;

import lexical.Token;
import semantic.commands.Node;

public class OpBinaryMult extends OpBinary {

    public OpBinaryMult(Token tk, Node exp1, Node exp2) {
        super(tk, null, exp1, exp2);
    }
}
