package semantic.commands.expression;

import lexical.Token;
import semantic.VarType;

public class RealConstant extends Exp {

    public RealConstant(Token token) {
        super(token, VarType.REAL);
    }
}
