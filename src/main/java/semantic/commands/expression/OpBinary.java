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

    //Verifica se os tipos dos operandos são iguais
    public void checkOperandsType() {
        Exp leftChild = (Exp)getChildren().get(0);
        Exp rightChild = (Exp)getChildren().get(1);

        if(!leftChild.getType().equals(rightChild.getType())) {
            System.err.println("Tipos dos operandos do operador " + "<'" + getToken().getLexValue() + "'> são incompatíveis | " + getToken().getLocation());
            System.exit(1);
        }
    }
}
