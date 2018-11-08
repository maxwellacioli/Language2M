package semantic.commands.expression;

import lexical.Token;
import semantic.commands.Node;

public class OpBinaryConc extends OpBinary {

    public OpBinaryConc(Token tk, Node exp1, Node exp2) {
        super(tk, null, exp1, exp2);
    }
}
