package semantic.commands.expression;

import lexical.Token;
import semantic.VarType;

public class OpBinary extends Exp {
//    Exp lefOperand, rightOperand;

    public OpBinary(Token token, VarType type, Exp lefOperand, Exp rightOperand) {
        super(token, type);
//        this.lefOperand = lefOperand;
//        this.rightOperand = rightOperand;
        addChild(lefOperand);
        addChild(rightOperand);
    }
}
