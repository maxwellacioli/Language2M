package semantic.commands.expression;

import lexical.Token;
import semantic.VarType;
import semantic.commands.Node;

public class OpBinary extends Exp {

    public OpBinary(Token token, Node lefOperand, Node rightOperand) {
        super(token, null);
        addChild(lefOperand);
        addChild(rightOperand);

        //Seta o tipo do operador de acordo com o tipo do operando da esquerda
        Exp lo = (Exp) lefOperand;
        setType(lo.getType());
    }
}
