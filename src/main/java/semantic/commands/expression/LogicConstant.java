package semantic.commands.expression;

import lexical.Token;
import semantic.VarType;

public class LogicConstant extends  Exp {

    public LogicConstant(Token token) {
        super(token, VarType.LOGICO);
    }
}
