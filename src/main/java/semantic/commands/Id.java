package semantic.commands;

import lexical.Token;

public class Id extends Node {
    private Token token;

    public Id() {}

    public Id(Token token) {
        this.token = token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public Token getToken() {
        return token;
    }
}
