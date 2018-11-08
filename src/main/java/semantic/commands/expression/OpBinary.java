package semantic.commands.expression;

import lexical.Token;
import semantic.VarType;
import semantic.commands.Node;

public class OpBinary extends Exp {
//    Exp lefOperand, rightOperand;

    public OpBinary(Token token, VarType type, Node lefOperand, Node rightOperand) {
        super(token, type);
//        this.lefOperand = lefOperand;
//        this.rightOperand = rightOperand;
        addChild(lefOperand);
        addChild(rightOperand);
    }
}
