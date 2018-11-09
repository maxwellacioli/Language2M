package semantic.commands.expression;

import lexical.Token;
import semantic.VarType;

public class IntegerConstant extends Exp {

    public IntegerConstant(Token token) {
        super(token, VarType.INTEIRO);
    }
}
