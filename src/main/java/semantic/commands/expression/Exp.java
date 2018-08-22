package semantic.commands.expression;

import lexical.Token;
import semantic.VarType;
import semantic.commands.Node;

public class Exp extends Node {
    private Token token;
    private VarType type;

    public Exp() {}

    public Exp(Token token, VarType type) {
        this.token = token;
        this.type = type;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public void setType(VarType type) {
        this.type = type;
    }

    public Token getToken() {
        return token;
    }

    public VarType getType() {
        return type;
    }

    public Exp generator() {
        return this;
    }

    public Exp reduce() {
        return this;
    }

    //TODO
    public void jumping() {}

    //TODO
    public void emitJumps() {}
}
