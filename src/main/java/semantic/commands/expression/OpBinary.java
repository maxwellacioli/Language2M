package semantic.commands.expression;

import lexical.Token;
import semantic.VarType;
import semantic.commands.Node;

public class OpBinary extends Exp {

    public OpBinary(Token token, VarType type, Node lefOperand, Node rightOperand) {
        super(token, type);
        addChild(lefOperand);
        addChild(rightOperand);
    }
}
