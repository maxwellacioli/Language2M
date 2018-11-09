package semantic.commands.expression;

import lexical.Token;
import semantic.VarType;

public class CharConstant extends Exp {

    public CharConstant(Token token) {
        super(token, VarType.CARACTER);
    }
}
