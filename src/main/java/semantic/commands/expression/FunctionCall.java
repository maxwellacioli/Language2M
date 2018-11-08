package semantic.commands.expression;

import lexical.Token;

public class FunctionCall extends Exp {

    //TODO Implementar verificação de tipo da função na sua chamada
    public FunctionCall(Token tk) {
        super(tk, null);
    }
}
