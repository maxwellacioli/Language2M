package semantic.commands.expression;

import lexical.Token;
import semantic.commands.Node;

public class OpUnaryNegLogic extends OpUnary{

    public OpUnaryNegLogic(Token token, Node exp) {
        super(token, exp);
    }
}
