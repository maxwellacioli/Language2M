package semantic.commands;

import javax.smartcardio.ATR;

public class Attribution extends Node {
    private String operator;

    public Attribution () {}

    public Attribution(String operator, Node child1, Node child2) {
        this.operator = operator;
        addChild(child1);
        addChild(child2);
    }

}
