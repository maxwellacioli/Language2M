package semantic.commands.expression;

import lexical.Token;
import semantic.VarType;
import semantic.commands.Node;

public class OpUnary extends Exp {
    public OpUnary(Token token, Node child) {
        super(token, null);
        addChild(child);

        Exp c = (Exp) child;
        setType(c.getType());
    }
}
