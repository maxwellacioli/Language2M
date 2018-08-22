package semantic.commands.expression;

import lexical.Token;
import semantic.VarType;
import semantic.commands.expression.Exp;

public class Id extends Exp {

    public Id() {}

    public Id(Token token) {
        super(token, null);
    }

    public Id(Token token, VarType type) {
        super(token, type);
    }
}
